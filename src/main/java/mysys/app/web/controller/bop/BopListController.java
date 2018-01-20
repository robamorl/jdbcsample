package mysys.app.web.controller.bop;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bop")
@Controller
public class BopListController {

    @Autowired
    private TBalanceOfPaymentsService balanceOfPaymentsService;
    @Autowired
    private MAccountService accountService;
    @Autowired
    private TBalanceService balanceService;

    /**
     *
     * 一覧画面遷移
     *
     * @param message 各種メッセージ
     * @param model {@link Model}
     * @return URI
     * @throws SystemException
     */
    @RequestMapping(path = {"/", "/list"}, method = GET)
    public String showAllBalanceOfPayments(@ModelAttribute("message") String message, Model model) throws SystemException {
        ProjectCommonUtil.addMessage(model, message);

        List<BalanceOfPaymentsForm> formList = new ArrayList<BalanceOfPaymentsForm>();
        List<TBalanceOfPaymentsDto> bopList;

        // 最初にユーザに紐づく口座を取得
        List<MAccountDto> accountList;
        try {
            accountList = accountService.execFindAllByUserId(LoginUserUtil.getLoginUserId());
        } catch (DataNotFoundException e) {
            // 口座自体が存在しなかったらこの時点で空のFormリストを返却して終了
            model.addAttribute("bops", formList);
            return "bop/list";
        }
        // 取得した口座情報から口座IDのリストを作成
        List<Long> accountIds = new ArrayList<Long>();
        for (MAccountDto dto : accountList) {
            accountIds.add(dto.getAccountId());
        }

        // 口座IDのリストを元に全ての収支を取得
        try {
            bopList = balanceOfPaymentsService.execFindByAccountIdList(accountIds);
        } catch (DataNotFoundException e) {
            bopList = new ArrayList<TBalanceOfPaymentsDto>();
        }

        // 取得した内容をFormへコピー
        for (TBalanceOfPaymentsDto dto : bopList) {
            // 口座番号が同一の口座情報から口座名と口座番号を取得
            String accountName = null;
            String accountNumber = null;
            for (MAccountDto accountDto : accountList) {
                if (dto.getAccountId().equals(accountDto.getAccountId())) {
                    accountName = accountDto.getAccountName();
                    accountNumber = accountDto.getAccountNumber();
                    break;
                }
            }
            // 一致する口座がないのはおかしい
            if (ProjectCommonUtil.isBlank(accountName)
                    || ProjectCommonUtil.isBlank(accountNumber)) {
                throw new SystemException("想定外：口座IDに紐づく収支が取得できない。");
            }

            // formへコピー
            BalanceOfPaymentsForm form = new BalanceOfPaymentsForm();
            form.copyFrom(dto, accountName, accountNumber, null);
            formList.add(form);
        }

        model.addAttribute("bops", formList);
        return "bop/list";
    }

    /**
     *
     * 詳細画面遷移
     *
     * @param balanceOfPaymentsId 収支ID
     * @param model {@link Model}
     * @return URI
     * @throws DataNotFoundException
     * @throws SystemException
     */
    @RequestMapping(value = "/list/{balanceOfPaymentsId}", method = GET)
    public String showAcctountDetail(@PathVariable Long balanceOfPaymentsId, Model model)
                                        throws DataNotFoundException,SystemException{
        TBalanceOfPaymentsDto bop = balanceOfPaymentsService.execFind(balanceOfPaymentsId);
        MAccountDto account = accountService.execFind(bop.getAccountId());
        TBalanceDto balance = balanceService.execFindByAccountId(account.getAccountId());
        // Formへ詰替
        BalanceOfPaymentsForm form = new BalanceOfPaymentsForm();
        form.copyFrom(bop, account.getAccountName(), account.getAccountNumber(), balance.getBalance());
        model.addAttribute("bop", form);
        return "bop/detail";
    }

    /**
     *
     * 削除処理
     *
     * @param accountId 口座ID
     * @param model {@link Model}
     * @return URI
     * @throws DataNotFoundException
     */
    @RequestMapping(value = "/list/{accountId}/delete", method = GET)
    public String execDelete(@PathVariable Long accountId, Model model)
                                        throws DataNotFoundException{
        balanceOfPaymentsService.execLogicalDelete(accountId);
        ProjectCommonUtil.addDeleteDoneMessage(model);
        return "redirect:/account/";
    }
}
