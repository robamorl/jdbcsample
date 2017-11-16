package mysys.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

    private String description;

    public LoginUser(String userCode, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(userCode, password, authorities);
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
