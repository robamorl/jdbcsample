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
import mysys.app.biz.domain.TBalanceOfPaymentsDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/bop/list/{balanceOfPaymentsId}")
@SessionAttributes(value = {"editBop", "transactionAccountList", "bopKubunList", "expensesKubunList"})
public class BopEditController {

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
     * 更新画面遷移
     *
     *@param sessionStatus {@link SessionStatus}
     * @return URI
     */
    @RequestMapping(path = "/edit", method = GET)
    public String redirectToEntryForm(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:enter";
    }

    /**
     *
     * 更新画面表示
     *
     * @param balanceOfPaymentsId 収支ID
     * @param model {@link Model}
     * @return URI
     * @throws DataNotFoundException
     * @throws SystemException
     */
    @RequestMapping(path = "/enter", method = GET)
    public String showEntryForm(@PathVariable Long balanceOfPaymentsId, Model model) throws DataNotFoundException, SystemException {
        // ----------------------------------------------------------------------
        // 基本情報のセット
        TBalanceOfPaymentsDto bop = bopService.execFind(balanceOfPaymentsId);
        MAccountDto account = accountService.execFind(bop.getAccountId());
        TBalanceDto balance = balanceService.execFindByAccountId(account.getAccountId());
        // Formへ詰替
        BalanceOfPaymentsForm form = new BalanceOfPaymentsForm();
        form.copyFrom(bop, account.getAccountName(), account.getAccountNumber(), balance.getBalance());
        ProjectCommonUtil.addModelAttribute(model, "editBop", form);
        // 選択されたデータを取得
        // ユーザに紐づく口座を取得
        List<MAccountDto> accountList;
        try {
            accountList = accountService.execFindAllByUserId(LoginUserUtil.getLoginUserId());
        } catch (DataNotFoundException e) {
            ProjectCommonUtil.addGeneralMessage(model, "口座が更新されていません。");
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

        return "bop/edit/enter";
    }

    /**
     *
     * 更新画面 更新イベント
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
            return "bop/edit/enter";
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
        // 残高に更新前金額を加減算する。
        BigDecimal balance = ProjectCommonUtil.calcBalance(balanceDto.getBalance(), bopForm.getBeforeAmount(),
                ProjectCommonUtil.getBalanceOfPaymentsSign(bopForm.getBeforeBalanceOfPaymentsKubun()) * -1);
        // 収支区分によって残高を計算
        balance = ProjectCommonUtil.calcBalance(balance, bopForm.getAmount(),
                ProjectCommonUtil.getBalanceOfPaymentsSign(bopForm.getBalanceOfPaymentsKubun()));
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
     * 更新確認画面表示
     *
     * @return URI
     */
    @RequestMapping(path = "/review", method = GET)
    public String showReview() {
        return "bop/edit/review";
    }

    /**
     *
     * 更新画面へ戻る
     *
     * @return URI
     */
    @RequestMapping(path = "/review", params = "_event_revise", method = POST)
    public String backEnter() {
        return "redirect:enter";
    }

    /**
     *
     * 更新実行
     *
     * @param bopForm
     * @return URI
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/review", params = "_event_confirmed", method = POST)
    public String execUpdate(@ModelAttribute("editBop") BalanceOfPaymentsForm bopForm) throws DataNotFoundException {
        // 口座の更新
        bopService.execUpdate(bopForm.createDto());
        return "redirect:edited";
    }

    /**
     *
     * 更新完了画面表示
     *
     * @param sessionStatus
     * @param model
     * @return URI
     */
    @RequestMapping(path = "/edited", method = GET)
    public String showEdited(SessionStatus sessionStatus, Model model) {
        sessionStatus.setComplete();
        ProjectCommonUtil.addInsertDoneMessage(model);
        return "bop/edit/edited";
    }
}
