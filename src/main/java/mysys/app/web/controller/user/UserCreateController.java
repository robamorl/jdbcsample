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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/user/list")
@SessionAttributes(value = "editUser")
public class UserCreateController {

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
     * @param sessionStatus
     * @return
     */
    @RequestMapping(path = "/create", method = GET)
    public  String redirectToEntryForm(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
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
        ProjectCommonUtil.addModelAttribute(model, "editUser", new UserForm());
        return "user/create/enter";
    }

    /**
     *
     * validate
     *
     * @param userFrom
     * @param errors
     * @return
     */
    @RequestMapping(path = "/enter", params="_event_proceed", method=POST)
    public String verify(@Valid @ModelAttribute("editUser") UserForm userFrom, Errors errors) {
        if (errors.hasErrors()) {
            return "user/create/enter";
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
        return "user/create/review";
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
     * @param userFrom
     * @return
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/review", params = "_event_confirmed", method=POST)
    public String execRegister(@ModelAttribute("editUser") UserForm userFrom) throws DataNotFoundException {
        mUserService.execInsert(userFrom.createDto());
        return "redirect:created";
    }

    /**
     *
     * edited
     *
     * @param sessionStatus
     * @param model
     * @return
     */
    @RequestMapping(path = "/created", method = GET)
    public String showEdited(SessionStatus sessionStatus, Model model) {
        sessionStatus.setComplete();
        ProjectCommonUtil.addInsertDoneMessage(model);
        return "user/create/edited";
    }
}
