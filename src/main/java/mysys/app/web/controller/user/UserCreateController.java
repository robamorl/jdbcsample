package mysys.app.web.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import mysys.app.biz.domain.MUserDto;
import mysys.app.biz.service.MUserService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.web.form.UserForm;

import org.springframework.beans.BeanUtils;
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
    MUserService mUserService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     *
     * edit
     *
     * @param model
     * @return
     */
    @RequestMapping(path = "/create", method = GET)
    public  String redirectToEntryForm(Model model) {
        model.addAttribute("editUser", new UserForm());
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
        MUserDto user = new MUserDto();
        BeanUtils.copyProperties(userFrom, user);
        mUserService.execInsert(user);
        return "redirect:created";
    }

    /**
     *
     * edited
     *
     * @param sessionStatus
     * @return
     */
    @RequestMapping(path = "/created", method = GET)
    public String showEdited(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "user/create/edited";
    }
}
