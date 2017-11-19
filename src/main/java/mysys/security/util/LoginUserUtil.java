package mysys.security.util;

import mysys.security.LoginUser;

import org.springframework.security.core.context.SecurityContextHolder;

public class LoginUserUtil {

    /**
     *
     * ログインユーザのIDを取得する
     *
     * @return ログインユーザのId
     */
    public static Long getLoginUserId() {
        LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser.getUserId();
    }
}
