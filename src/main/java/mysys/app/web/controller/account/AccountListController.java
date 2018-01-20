package mysys.app.web.controller.account;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.domain.TBalanceDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.TBalanceService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.biz.service.exception.SystemException;
import mysys.app.web.form.AccountForm;
import mysys.security.util.LoginUserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountListController {

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
    public String showAllAccount(@ModelAttribute("message") String message, Model model) throws SystemException {
        ProjectCommonUtil.addMessage(model, message);
        List<MAccountDto> accountList = null;
        try {
            accountList = accountService.execFindAllByUserId(LoginUserUtil.getLoginUserId());
        } catch (DataNotFoundException e) {
            accountList = new ArrayList<MAccountDto>();
        }

        // 取得した内容をFormへコピー
        List<AccountForm> formList = new ArrayList<AccountForm>();
        for (MAccountDto dto : accountList) {
            AccountForm form = new AccountForm();
            TBalanceDto balance;
            try {
                balance = balanceService.execFindByAccountId(dto.getAccountId());
            } catch (DataNotFoundException e) {
                throw new SystemException("想定外：口座に紐づく残高が取得できない。");
            }
            form.copyFrom(dto, balance.getBalance());
            formList.add(form);
        }

        model.addAttribute("accounts", formList);
        return "account/list";
    }

    /**
     *
     * 詳細画面遷移
     *
     * @param accountId 口座ID
     * @param model {@link Model}
     * @return URI
     * @throws DataNotFoundException
     * @throws SystemException
     */
    @RequestMapping(value = "/list/{accountId}", method = GET)
    public String showAcctountDetail(@PathVariable Long accountId, Model model)
                                        throws DataNotFoundException,SystemException{
        MAccountDto account = accountService.execFind(accountId);
        // Formへ詰替
        AccountForm form = new AccountForm();
        TBalanceDto balance = balanceService.execFindByAccountId(accountId);
        form.copyFrom(account, balance.getBalance());
        model.addAttribute("account", form);
        return "account/detail";
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
        accountService.execLogicalDelete(accountId);
        ProjectCommonUtil.addDeleteDoneMessage(model);
        return "redirect:/account/";
    }
}
