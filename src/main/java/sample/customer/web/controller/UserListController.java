package sample.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.customer.biz.domain.MUser;
import sample.customer.biz.service.MUserService;
import sample.customer.biz.service.exception.DataNotFoundException;

@Controller
public class UserListController {

    @Autowired
    private MUserService mUserService;

    @RequestMapping(value = "/", method = GET)
    public String home() {
        return "forward:/user";
    }

    @RequestMapping(value = "/user", method = GET)
    public String showAllUsers(Model model) {
        List<MUser> userList = null;
        try {
            userList = mUserService.execFindAll();
        } catch (DataNotFoundException e) {
            userList = new ArrayList<MUser>();
        }
        model.addAttribute("users", userList);
        return "user/list";
    }

    @RequestMapping(value = "/user/{userId}", method = GET)
    public String showCustomerDetail(@PathVariable Long userId, Model model)
                                        throws DataNotFoundException{
        MUser user = mUserService.execFind(userId);
        model.addAttribute("user", user);
        return "user/detail";
    }
}
