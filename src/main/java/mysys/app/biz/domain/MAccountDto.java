package mysys.app.biz.domain;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Sun Nov 19 18:14:34 JST 2017
 */
import java.io.Serializable;

import mysys.app.biz.domain.common.CommonDomain;

/**
 * MAccountDto.
 * @author Ryuji
 * @version 1.0
 * history
 * Symbol	Date		Person		Note
 * [1]		2017/11/19	Ryuji		Generated.
 */
public class MAccountDto extends CommonDomain implements Serializable{

    /** ACCOUNT_ID*/
    private Long accountId;

    /** ACCOUNT_NUMBER*/
    private String accountNumber;

    /** USER_ID*/
    private Long userId;

    /** ACCOUNT_KUBUN*/
    private String accountKubun;

    /**
     * @return accountId
     */
    public final Long getAccountId() {
        return accountId;
    }

    /**
     * @return accountNumber
     */
    public final String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @return userId
     */
    public final Long getUserId() {
        return userId;
    }

    /**
     * @return accountKubun
     */
    public final String getAccountKubun() {
        return accountKubun;
    }

    /**
     * @param accountId セットする accountId
     */
    public final void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param accountNumber セットする accountNumber
     */
    public final void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @param userId セットする userId
     */
    public final void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @param accountKubun セットする accountKubun
     */
    public final void setAccountKubun(String accountKubun) {
        this.accountKubun = accountKubun;
    }

}
