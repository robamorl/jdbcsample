package sample.customer.biz.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import sample.customer.biz.domain.common.CommonDomain;

/**
 *
 * M_USER ドメイン
 *
 * @author Ryuji
 *
 */
public class MUser extends CommonDomain {

    private Long userId;

    @NotNull
    @Size(min=8, max=8)
    @Pattern(regexp="[a-zA-Z0-9]*")
    private String userCode;

    @NotNull
    @Size(max=16)
    @Pattern(regexp="[a-zA-Z0-9]*")
    private String password;

    @NotNull
    @Size(max=20)
    private String userName;

    @Size(max=100)
    @Email
    private String mailAddress;

    /**
     * @return userId
     */
    public final Long getUserId() {
        return userId;
    }

    /**
     * @return userCode
     */
    public final String getUserCode() {
        return userCode;
    }

    /**
     * @return password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @return userName
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * @return mailAddress
     */
    public final String getMailAddress() {
        return mailAddress;
    }

    /**
     * @param userId セットする userId
     */
    public final void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @param userCode セットする userCode
     */
    public final void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @param password セットする password
     */
    public final void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param userName セットする userName
     */
    public final void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param mailAddress セットする mailAddress
     */
    public final void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }



}
