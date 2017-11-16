package mysys.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class LoginUserJdbcDaoImpl extends JdbcDaoImpl {

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { username },
                new RowMapper<UserDetails>() {
                        public UserDetails mapRow(ResultSet rs, int rowNum)
                                        throws SQLException {
                                String userCode = rs.getString("USER_CODE");
                                String password = rs.getString("PASSWORD");
                                String description = rs.getString("DESCRIPTION");

                                LoginUser user = new LoginUser(userCode, password,
                                                AuthorityUtils.NO_AUTHORITIES);

                                user.setDescription(description);

                                return user;
                        }
                });
    }

    @Override
    protected UserDetails createUserDetails(
            String username, UserDetails userFromUserQuery,
            List<GrantedAuthority> combinedAuthorities) {
        LoginUser origin = (LoginUser) userFromUserQuery;
        String userCode = origin.getUsername();
        String password = origin.getPassword();
        String description = origin.getDescription();
        LoginUser user = new LoginUser(userCode, password, combinedAuthorities);
        user.setDescription(description);
        return user;
    }
}
