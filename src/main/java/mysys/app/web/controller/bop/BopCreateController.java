package mysys.app.web.controller.bop;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import mysys.app.biz.common.kubun.BalanceOfPaymentsKubun;
import mysys.app.biz.common.kubun.ExpensesKubun;
import mysys.app.biz.common.kubun.stationery.Kubun;
import mysys.app.biz.common.util.KubunUtil;
import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.domain.TBalanceDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.TBalanceOfPaymentsService;
import mysys.app.biz.service.TBalanceService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.biz.service.exception.SystemException;
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
    private TBalanceOfPaymentsService bopService;
    @Autowired
    private MAccountService accountService;
    @Autowired
    private TBalanceService balanceService;
    @Autowired
    private BopValidator validator;

    @InitBinder("editBop")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(validator);
    }

    /**
     *
     * 登録画面遷移
     *
     *@param sessionStatus {@link SessionStatus}
     * @return URI
     */
    @RequestMapping(path = "/create", method = GET)
    public String redirectToEntryForm(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
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
        // ----------------------------------------------------------------------
        // 基本情報のセット
        // ユーザに紐づく口座を取得
        List<MAccountDto> accountList;
        try {
            accountList = accountService.execFindAllByUserId(LoginUserUtil.getLoginUserId());
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
     * @param bopForm {@link BalanceOfPaymentsForm}
     * @param errors {@link Errors}
     * @param model {@link Model}
     * @return URI
     * @throws SystemException
     */
    @RequestMapping(path = "/enter", params = "_event_proceed", method = POST)
    public String verify(@Valid @ModelAttribute("editBop") BalanceOfPaymentsForm bopForm, Errors errors, Model model) throws SystemException {
        if (errors.hasErrors()) {
            return "bop/create/enter";
        }

        // ----------------------------------------------------------------------
        // 問題なければ選択された区分値を元に区分名をセット
        // 収支
        bopForm.setBalanceOfPaymentsKubunMei(KubunUtil.getKubunMei(BalanceOfPaymentsKubun.BOP_KUBUN_LIST,
                bopForm.getBalanceOfPaymentsKubun()));

        // 費目
        bopForm.setExpensesKubunMei(KubunUtil.getKubunMei(ExpensesKubun.EXPENSES_KUBUN_LIST, bopForm.getExpensesKubun()));

        // 口座名・口座番号
        MAccountDto accountDto;
        try {
            accountDto = accountService.execFind(bopForm.getAccountId());
        } catch (DataNotFoundException e) {
            throw new SystemException("想定外：口座IDに紐づく口座が見つからない。"
                                                      + " 口座ID:" + bopForm.getAccountId());
        }
        bopForm.setAccountNumberForDisplay(ProjectCommonUtil.getAccountNumberForDisplay(accountDto.getAccountNumber()));
        bopForm.setAccountName(accountDto.getAccountName());

        // 残高
        TBalanceDto balanceDto;
        try {
            balanceDto = balanceService.execFindByAccountId(accountDto.getAccountId());
        } catch (DataNotFoundException e) {
            // 存在しない場合エラー
            throw new SystemException("想定外：口座IDに紐づく残高が見つからない。"
                                                       + " 口座ID:" + bopForm.getAccountId());
        }
        // 収支区分によって残高を計算
        BigDecimal balance = BigDecimal.ZERO;
        if (BalanceOfPaymentsKubun.BOP_KUBUN_EXPENSE.equals(bopForm.getBalanceOfPaymentsKubun())) {
            // 支出の場合
            balance = balanceDto.getBalance().subtract(bopForm.getAmount());
        } else if (BalanceOfPaymentsKubun.BOP_KUBUN_INCOME.equals(bopForm.getBalanceOfPaymentsKubun())) {
            // 収入の場合
            balance = balanceDto.getBalance().add(bopForm.getAmount());
        }
        bopForm.setBalance(balance);

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
    public String execRegister(@ModelAttribute("editBop") BalanceOfPaymentsForm bopForm) throws DataNotFoundException {
        // 口座の登録
        bopService.execInsert(bopForm.createDto());
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
}
