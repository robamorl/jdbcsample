package mysys.app.web.controller.account;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.biz.service.exception.SystemException;
import mysys.app.web.form.AccountForm;
import mysys.security.util.LoginUserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountListController {

    @Autowired
    private MAccountService mAccountService;

    /**
     *
     * 一覧画面遷移
     *
     * @param model {@link Model}
     * @return URI
     * @throws SystemException
     */
    @RequestMapping(path = {"/", "/list"}, method = GET)
    public String showAllAccount(Model model) throws SystemException {
        List<MAccountDto> accountList = null;
        try {
            accountList = mAccountService.execFindAllByUserId(LoginUserUtil.getLoginUserId());
        } catch (DataNotFoundException e) {
            accountList = new ArrayList<MAccountDto>();
        }

        // 取得した内容をFormへコピー
        List<AccountForm> formList = new ArrayList<AccountForm>();
        for (MAccountDto dto : accountList) {
            AccountForm form = new AccountForm();
            form.copyFrom(dto);
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
        MAccountDto account = mAccountService.execFind(accountId);
        // Formへ詰替
        AccountForm form = new AccountForm();
        form.copyFrom(account);
        model.addAttribute("account", form);
        return "account/detail";
    }
}
