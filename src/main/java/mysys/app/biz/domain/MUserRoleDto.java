package mysys.app.biz.domain;

import mysys.app.biz.domain.common.CommonDomain;

/**
 *
 * M_USER_ROLE ドメイン
 *
 * @author Ryuji
 *
 */
public class MUserRoleDto extends CommonDomain {
    /** ユーザID */
    private Long userId;
    /** ロールID */
    private Long roleId;
    /** デフォルトフラグ */
    private Boolean defaultFlg;

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
     * @return roleId
     */
    public final Long getRoleId() {
        return roleId;
    }
    /**
     * @param roleId セットする roleId
     */
    public final void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    /**
     * @return defaultFlg
     */
    public final Boolean getDefaultFlg() {
        return defaultFlg;
    }
    /**
     * @param defaultFlg セットする defaultFlg
     */
    public final void setDefaultFlg(Boolean defaultFlg) {
        this.defaultFlg = defaultFlg;
    }
}
