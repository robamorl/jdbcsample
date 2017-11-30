package mysys.app.biz.domain;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Thu Nov 30 23:10:47 JST 2017
 */
import java.io.Serializable;
import java.math.BigDecimal;

import mysys.app.biz.domain.common.CommonDomain;

/**
 * T_balanceVo.
 * @author Ryuji
 * @version 1.0
 * history
 * Symbol	Date		Person		Note
 * [1]		2017/11/30	Ryuji		Generated.
 */
public class TBalanceDto extends CommonDomain implements Serializable{

    /** ACCOUNT_ID*/
    private Long accountId;

    /** BALANCE*/
    private BigDecimal balance;

    /** LATEST_BALANCE_HISTORY_ID*/
    private Long latestBalanceHistoryId;

    /**
     * @return accountId
     */
    public final Long getAccountId() {
        return accountId;
    }

    /**
     * @return balance
     */
    public final BigDecimal getBalance() {
        return balance;
    }

    /**
     * @return latestBalanceHistoryId
     */
    public final Long getLatestBalanceHistoryId() {
        return latestBalanceHistoryId;
    }

    /**
     * @param accountId セットする accountId
     */
    public final void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param balance セットする balance
     */
    public final void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @param latestBalanceHistoryId セットする latestBalanceHistoryId
     */
    public final void setLatestBalanceHistoryId(Long latestBalanceHistoryId) {
        this.latestBalanceHistoryId = latestBalanceHistoryId;
    }

}
