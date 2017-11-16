package mysys.app.web.controller.home;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(path = {"/", "/top"}, method = GET)
    public String home() {
        return "/top";
    }

    @RequestMapping(value = "/login", method = GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/error/accessDenied", method = GET)
    public String accessDenied() {
        return "/error/accessDenied";
    }
}
