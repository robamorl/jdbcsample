package mysys.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

    /** ユーザID  */
    private Long userId;

    /** ユーザ名 */
    private String userNameJp;

    /** ロール名 */
    private String description;

    public LoginUser(String userCode, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(userCode, password, authorities);
    }

    /**
     * @return userId
     */
    public final Long getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public final void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return userNameJp
     */
    public final String getUserNameJp() {
        return userNameJp;
    }

    /**
     * @param userNameJp セットする userNameJp
     */
    public final void setUserNameJp(String userNameJp) {
        this.userNameJp = userNameJp;
    }

    /**
     * @return description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param description セットする description
     */
    public final void setDescription(String description) {
        this.description = description;
    }

    private static final long serialVersionUID = 5869301720811314860L;
}
