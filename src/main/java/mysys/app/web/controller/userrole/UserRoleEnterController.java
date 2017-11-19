package mysys.app.web.controller.userrole;

import mysys.app.biz.service.MUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user.role")
@Controller
public class UserRoleEnterController {

    @Autowired
    private MUserService mUserService;

}
