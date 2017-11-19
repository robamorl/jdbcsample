package mysys.app.web.controller.account;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import mysys.app.biz.common.kubun.AccountKubun;
import mysys.app.biz.common.util.KubunUtil;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.web.form.AccountForm;

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
@RequestMapping("/account/list")
@SessionAttributes(value = "editAccount")
public class AccountCreateController {

    @Autowired
    MAccountService mAccountService;

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
    public  String redirectToEntryForm() {
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
        model.addAttribute("editAccount", new AccountForm());
        // 口座区分をセット
        model.addAttribute("accountKubunList", AccountKubun.ACCOUNT_KUBUN_LIST);
        return "account/create/enter";
    }

    /**
     *
     * 登録画面 登録イベント
     *
     * @param accountForm {@link AccountForm}
     * @param errors {@link Errors}
     * @return URI
     */
    @RequestMapping(path = "/enter", params="_event_proceed", method=POST)
    public String verify(@Valid @ModelAttribute("editAccount") AccountForm accountForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/create/enter";
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
     * @return URI
     */
    @RequestMapping(path = "/enter", params = "_event_back", method = POST)
    public String backList() {
        return "redirect:/account/list";
    }

    /**
     *
     * 登録確認画面表示
     *
     * @return URI
     */
    @RequestMapping(path = "/review", method=GET)
    public String showReview() {
        return "account/create/review";
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
     * @param accountForm
     * @return URI
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/review", params = "_event_confirmed", method=POST)
    public String execRegister(@ModelAttribute("editAccount") AccountForm accountForm) throws DataNotFoundException {
        mAccountService.execInsert(accountForm.createDto());
        return "redirect:created";
    }

    /**
     *
     * 登録完了画面表示
     *
     * @param sessionStatus
     * @return URI
     */
    @RequestMapping(path = "/created", method = GET)
    public String showEdited(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "account/create/edited";
    }
}
