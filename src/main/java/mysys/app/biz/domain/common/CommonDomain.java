package mysys.app.biz.domain.common;

import java.sql.Timestamp;

import org.springframework.security.core.context.SecurityContextHolder;

public class CommonDomain {

    protected Timestamp entryDate;
    protected String entryUser;
    protected Timestamp updateDate;
    protected String updateUser;
    protected Boolean deleteFlg;

    /**
     *  登録時の情報をセットする
     * @author Ryuji
     */
    public final void setEntryData() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.entryDate = now;
        this.entryUser = SecurityContextHolder.getContext().getAuthentication().getName();
        this.updateDate = now;
        this.updateUser = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     *  更新時の情報をセットする
     * @author Ryuji
     */
    public final void setUpdateData() {
        this.updateDate = new Timestamp(System.currentTimeMillis());
        this.updateUser = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * @return updateDate
     */
    public final Timestamp getUpdateDate() {
        return updateDate;
    }
    /**
     * @return updateUser
     */
    public final String getUpdateUser() {
        return updateUser;
    }
    /**
     * @param updateDate セットする updateDate
     */
    public final void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * @param updateUser セットする updateUser
     */
    public final void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return entryDate
     */
    public final Timestamp getEntryDate() {
        return entryDate;
    }
    /**
     * @return entryUser
     */
    public final String getEntryUser() {
        return entryUser;
    }
    /**
     * @return deleteFlg
     */
    public final Boolean getDeleteFlg() {
        return deleteFlg;
    }
    /**
     * @param entryDate セットする entryDate
     */
    public final void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }
    /**
     * @param entryUser セットする entryUser
     */
    public final void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }
    /**
     * @param deleteFlg セットする deleteFlg
     */
    public final void setDeleteFlg(Boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }




}
