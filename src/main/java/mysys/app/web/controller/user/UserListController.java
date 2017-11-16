package mysys.app.web.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import mysys.app.biz.domain.MUser;
import mysys.app.biz.service.MUserService;
import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserListController {

    @Autowired
    private MUserService mUserService;

    @RequestMapping(path = {"/", "/list"}, method = GET)
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

    @RequestMapping(value = "/list/{userId}", method = GET)
    public String showCustomerDetail(@PathVariable Long userId, Model model)
                                        throws DataNotFoundException{
        MUser user = mUserService.execFind(userId);
        model.addAttribute("user", user);
        return "user/detail";
    }
}
