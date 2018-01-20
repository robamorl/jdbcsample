package mysys.app.web.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.service.MUserService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.web.form.UserForm;

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
@RequestMapping("/user/list/{userId}")
@SessionAttributes(value = "editUser")
public class UserEditController {

    @Autowired
    private MUserService mUserService;
    @Autowired
    private UserValidator validator;

    @InitBinder("editUser")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(validator);
    }

    /**
     *
     * edit
     *
     * @param userId
     * @param model
     * @return
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/edit", method = GET)
    public  String redirectToEntryForm(@PathVariable Long userId, Model model) throws DataNotFoundException {
        UserForm form = new UserForm();
        form.copyFrom(mUserService.execFind(userId));
        model.addAttribute("editUser", form);
        return "redirect:enter";
    }

    /**
     *
     * enter
     *
     * @return
     */
    @RequestMapping(path = "/enter", method = GET)
    public String showEntryForm() {
        return "user/edit/enter";
    }

    /**
     *
     * validate
     *
     * @param form
     * @param errors
     * @return
     */
    @RequestMapping(path = "/enter", params="_event_proceed", method=POST)
    public String verify(@Valid @ModelAttribute("editUser") UserForm form, Errors errors) {
        if (errors.hasErrors()) {
            return "user/edit/enter";
        }
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
        return "redirect:/user/list";
    }

    /**
     *
     * review
     *
     * @return
     */
    @RequestMapping(path = "/review", method=GET)
    public String showReview() {
        return "user/edit/review";
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
     * @param form
     * @return
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/review", params = "_event_confirmed", method=POST)
    public String execRegister(@ModelAttribute("editUser") UserForm form) throws DataNotFoundException {
        mUserService.execUpdate(form.createDto());
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
        return "user/edit/edited";
    }
}
