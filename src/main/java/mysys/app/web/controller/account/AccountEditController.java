package mysys.app.web.controller.account;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import mysys.app.biz.common.kubun.AccountKubun;
import mysys.app.biz.common.util.KubunUtil;
import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.biz.service.exception.SystemException;
import mysys.app.web.form.AccountForm;

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
@RequestMapping("/account/list/{accountId}")
@SessionAttributes(value = {"editAccount", "accountKubunList"})
public class AccountEditController {

    @Autowired
    private MAccountService mAccountService;
    @Autowired
    private AccountValidator accountValidator;

    @InitBinder("editAccount")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(accountValidator);
    }

    /**
     *
     * edit
     *
     * @param accountId
     * @param model
     * @return
     * @throws DataNotFoundException
     * @throws SystemException
     */
    @RequestMapping(path = "/edit", method = GET)
    public  String redirectToEntryForm(@PathVariable Long accountId, Model model) throws DataNotFoundException, SystemException {
        AccountForm accountForm = new AccountForm();
        accountForm.copyFrom(mAccountService.execFind(accountId), null);
        model.addAttribute("editAccount", accountForm);
        return "redirect:enter";
    }

    /**
     *
     * enter
     *
     * @param model
     * @return
     */
    @RequestMapping(path = "/enter", method = GET)
    public String showEntryForm(Model model) {
        // 口座区分をセット
        model.addAttribute("accountKubunList", AccountKubun.ACCOUNT_KUBUN_LIST);
        return "account/edit/enter";
    }

    /**
     *
     * validate
     *
     * @param accountForm
     * @param errors
     * @param model
     * @return
     */
    @RequestMapping(path = "/enter", params="_event_proceed", method=POST)
    public String verify(@Valid @ModelAttribute("editAccount") AccountForm accountForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "account/edit/enter";
        }

        // 問題なければ選択された区分値を元に区分名をセット
        accountForm.setAccountKubunMei(KubunUtil.getKubunMei(AccountKubun.ACCOUNT_KUBUN_LIST,
                accountForm.getAccountKubun()));
        return "redirect:review";
    }

    /**
     *
     * 一覧画面へ戻る
     *
     * @return
     */
    @RequestMapping(path = "/enter", params = "_event_back", method = POST)
    public String backList() {
        return "redirect:/account/list";
    }

    /**
     *
     * review
     *
     * @return
     */
    @RequestMapping(path = "/review", method=GET)
    public String showReview() {
        return "account/edit/review";
    }

    /**
     *
     * 戻る
     *
     * @return
     */
    @RequestMapping(path = "/review", params = "_event_revise", method = POST)
    public String backEnter() {
        return "redirect:enter";
    }

    /**
     *
     * 登録実行
     *
     * @param accountForm
     * @return
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/review", params = "_event_confirmed", method=POST)
    public String execRegister(@ModelAttribute("editAccount") AccountForm accountForm) throws DataNotFoundException {
        mAccountService.execUpdate(accountForm.createDto());
        return "redirect:edited";
    }

    /**
     *
     * edited
     *
     * @param sessionStatus
     * @param model
     * @return
     */
    @RequestMapping(path = "/edited", method = GET)
    public String showEdited(SessionStatus sessionStatus, Model model) {
        sessionStatus.setComplete();
        ProjectCommonUtil.addUpdateDoneMessage(model);
        return "account/edit/edited";
    }
}
