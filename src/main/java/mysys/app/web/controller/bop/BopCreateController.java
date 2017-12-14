package mysys.app.web.controller.bop;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import mysys.app.biz.common.kubun.AccountKubun;
import mysys.app.biz.common.kubun.BalanceOfPaymentsKubun;
import mysys.app.biz.common.kubun.ExpensesKubun;
import mysys.app.biz.common.kubun.stationery.Kubun;
import mysys.app.biz.common.util.KubunUtil;
import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.TBalanceOfPaymentsService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.web.form.AccountForm;
import mysys.app.web.form.BalanceOfPaymentsForm;
import mysys.security.util.LoginUserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/bop/list")
@SessionAttributes(value = {"editBop", "transactionAccountList", "bopKubunList", "expensesKubunList"})
public class BopCreateController {

    @Autowired
    private TBalanceOfPaymentsService tBalanceOfPaymentsService;
    @Autowired
    private MAccountService mAccountService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     *
     * 登録画面遷移
     *
     * @return URI
     */
    @RequestMapping(path = "/create", method = GET)
    public String redirectToEntryForm() {
        return "redirect:enter";
    }

    /**
     *
     * 登録画面表示
     *
     * @param model {@link Model}
     * @return URI
     */
    @RequestMapping(path = "/enter", method = GET)
    public String showEntryForm(Model model) {
        ProjectCommonUtil.addModelAttribute(model, "editBop", new BalanceOfPaymentsForm());
        // ユーザに紐づく口座を取得
        List<MAccountDto> accountList;
        try {
            accountList = mAccountService.execFindAllByUserId(LoginUserUtil.getLoginUserId());
        } catch (DataNotFoundException e) {
            ProjectCommonUtil.addGeneralMessage(model, "口座が登録されていません。");
            return "bop/list";
        }
        // 取得した口座を元にリストを作成
        List<Kubun> transactionAccountList = new ArrayList<Kubun>();
        for (MAccountDto dto : accountList) {
            transactionAccountList.add(
                    new Kubun(ProjectCommonUtil.convertObjectToString(dto.getAccountId()), dto
                            .getAccountName()));
        }
        // 口座リストをセット
        model.addAttribute("transactionAccountList", transactionAccountList);
        // 収支区分をセット
        model.addAttribute("bopKubunList", BalanceOfPaymentsKubun.BOP_KUBUN_LIST);
        // 費目区分をセット
        model.addAttribute("expensesKubunList", ExpensesKubun.EXPENSES_KUBUN_LIST);
        return "bop/create/enter";
    }

    /**
     *
     * 登録画面 登録イベント
     *
     * @param bopForm {@link AccountForm}
     * @param errors {@link Errors}
     * @param model {@link Model}
     * @return URI
     */
    @RequestMapping(path = "/enter", params = "_event_proceed", method = POST)
    public String verify(@Valid @ModelAttribute("editBop") AccountForm bopForm, Errors errors, Model model) {
        // Validate
        this.validateForRegister(bopForm, errors);
        if (errors.hasErrors()) {
            // 口座区分をセット
            model.addAttribute("bopKubunList", AccountKubun.ACCOUNT_KUBUN_LIST);
            return "bop/create/enter";
        }
        // 問題なければ選択された区分値を元に区分名をセット
        bopForm.setAccountKubunMei(KubunUtil.getKubunMei(AccountKubun.ACCOUNT_KUBUN_LIST,
                bopForm.getAccountKubun()));
        return "redirect:review";
    }

    /**
     *
     * 一覧画面へ戻る
     *
     * @return URI
     */
    @RequestMapping(path = "/enter", params = "_event_back", method = POST)
    public String backList() {
        return "redirect:/bop/list";
    }

    /**
     *
     * 登録確認画面表示
     *
     * @return URI
     */
    @RequestMapping(path = "/review", method = GET)
    public String showReview() {
        return "bop/create/review";
    }

    /**
     *
     * 登録画面へ戻る
     *
     * @return URI
     */
    @RequestMapping(path = "/review", params = "_event_revise", method = POST)
    public String backEnter() {
        return "redirect:enter";
    }

    /**
     *
     * 登録実行
     *
     * @param bopForm
     * @return URI
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/review", params = "_event_confirmed", method = POST)
    public String execRegister(@ModelAttribute("editBop") AccountForm bopForm) throws DataNotFoundException {
        // 口座の登録
        mAccountService.execInsert(bopForm.createDto()).getAccountId();
        return "redirect:created";
    }

    /**
     *
     * 登録完了画面表示
     *
     * @param sessionStatus
     * @param model
     * @return URI
     */
    @RequestMapping(path = "/created", method = GET)
    public String showEdited(SessionStatus sessionStatus, Model model) {
        sessionStatus.setComplete();
        ProjectCommonUtil.addInsertDoneMessage(model);
        return "bop/create/edited";
    }

    /**
     *
     * 登録時のValidate
     *
     * @param bopForm {@link AccountForm}
     * @param errors {@link Errors}
     */
    private void validateForRegister(AccountForm bopForm, Errors errors) {
        /* === 同じ口座番号が存在していないか === */
        try {
            mAccountService.execFindByAccountNumber(bopForm.getAccountNumber());
            // 存在していたらエラー
            errors.rejectValue("bopNumber", "exists.true");
        } catch (DataNotFoundException e) {
            // 存在していなければOK
        }
    }
}
