package mysys.app.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 *
 * UserRoleのForm
 *
 * @author Ryuji
 */
public class UserRoleForm {

    @NotNull
    @Size(min=8, max=8)
    @Pattern(regexp="[a-zA-Z0-9]*")
    /** ユーザID */
    private Long userId;

    /** ユーザコード */
    private String userCode;

    /** ロールID */
    private Long roleId;

    /** ロール名 */
    private String description;

    /** デフォルトフラグ */
    private Boolean defaultFlg;

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
     * @return roleId
     */
    public final Long getRoleId() {
        return roleId;
    }

    /**
     * @return description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @return defaultFlg
     */
    public final Boolean getDefaultFlg() {
        return defaultFlg;
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
     * @param roleId セットする roleId
     */
    public final void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @param description セットする description
     */
    public final void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param defaultFlg セットする defaultFlg
     */
    public final void setDefaultFlg(Boolean defaultFlg) {
        this.defaultFlg = defaultFlg;
    }



}
