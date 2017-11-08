package sample.customer.biz.domain.common;

import java.sql.Date;

public class CommonDomain {

    protected Date entryDate;
    protected String entryUser;
    protected Boolean deleteFlg;
    /**
     * @return entryDate
     */
    public final Date getEntryDate() {
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
    public final void setEntryDate(Date entryDate) {
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
