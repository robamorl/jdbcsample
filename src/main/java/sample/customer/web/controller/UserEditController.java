package sample.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

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

import sample.customer.biz.domain.MUser;
import sample.customer.biz.service.DataNotFoundException;
import sample.customer.biz.service.MUserService;

@Controller
@RequestMapping("/user/{userId}")
@SessionAttributes(value = "editUser")
public class UserEditController {

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
     * @param userId
     * @param model
     * @return
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/edit", method = GET)
    public  String redirectToEntryForm(@PathVariable Long userId, Model model) throws DataNotFoundException {
        model.addAttribute("editUser", mUserService.execFind(userId));
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
     * @param user
     * @param errors
     * @return
     */
    @RequestMapping(path = "/enter", params="_event_proceed", method=POST)
    public String verify(@Valid @ModelAttribute("editUser") MUser user, Errors errors) {
        if (errors.hasErrors()) {
            return "user/edit/enter";
        }
        return "redirect:review";
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
     * @param user
     * @return
     * @throws DataNotFoundException
     */
    @RequestMapping(path = "/review", params = "_event_confirmed", method=POST)
    public String execRegister(@ModelAttribute("editUser") MUser user) throws DataNotFoundException {
        mUserService.execUpdate(user);
        return "redirect:edited";
    }

    /**
     *
     * edited
     *
     * @param sessionStatus
     * @return
     */
    @RequestMapping(path = "/edited", method = GET)
    public String showEdited(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "user/edit/edited";
    }
}
