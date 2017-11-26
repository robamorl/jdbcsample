package mysys.app.web.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.MUserDto;
import mysys.app.biz.service.MUserService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.biz.service.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserListController {

    @Autowired
    private MUserService mUserService;

    /**
     *
     * 一覧画面を表示する
     *
     * @param message 各種メッセージ
     * @param model {@link Model}
     * @return URI
     */
    @RequestMapping(path = {"/", "/list"}, method = GET)
    public String showAllUsers(@ModelAttribute("message") String message, Model model) {
        ProjectCommonUtil.addMessage(model, message);
        List<MUserDto> userList = null;
        try {
            userList = mUserService.execFindAll();
        } catch (DataNotFoundException e) {
            userList = new ArrayList<MUserDto>();
        }
        model.addAttribute("users", userList);
        return "user/list";
    }

    /**
    *
    * 詳細画面遷移
    *
    * @param userId ユーザID
    * @param model {@link Model}
    * @return URI
    * @throws DataNotFoundException
    * @throws SystemException
    */
    @RequestMapping(value = "/list/{userId}", method = GET)
    public String showCustomerDetail(@PathVariable Long userId, Model model)
                                        throws DataNotFoundException{
        MUserDto user = mUserService.execFind(userId);
        model.addAttribute("user", user);
        return "user/detail";
    }

    /**
    *
    * 削除処理
    *
    * @param userId ユーザID
    * @param model {@link Model}
    * @return URI
    * @throws DataNotFoundException
    */
    @RequestMapping(value = "/list/{userId}/delete", method = GET)
    public String execDelete(@PathVariable Long userId, Model model)
                                        throws DataNotFoundException{
        mUserService.execLogicalDelete(userId);
        ProjectCommonUtil.addDeleteDoneMessage(model);
        return "redirect:/user/";
    }
}
