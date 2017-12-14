package mysys.app.biz.domain;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Thu Nov 30 23:34:23 JST 2017
 */
import java.io.Serializable;
import java.math.BigDecimal;

import mysys.app.biz.domain.common.CommonDomain;

import org.springframework.beans.BeanUtils;

/**
 * T_balance_historyVo.
 * @author Ryuji
 * @version 1.0
 * history
 * Symbol	Date		Person		Note
 * [1]		2017/11/30	Ryuji		Generated.
 */
public class TBalanceHistoryDto extends CommonDomain implements Serializable {

    /** BALANCE_HISTORY_ID*/
    private Long balanceHistoryId;

    /** ACCOUNT_ID*/
    private Long accountId;

    /** BALANCE*/
    private BigDecimal balance;

    /** LATEST_BALANCE_HISTORY_ID*/
    private Long latestBalanceHistoryId;

    /** EARNINGS_AND_EXPENSES_ID*/
    private Long balanceOfPaymentsId;

    /**
     *
     * デフォルトコンストラクタ
     *
     */
    public TBalanceHistoryDto() {
        super();
    }

    /**
     *
     * コピー用コンストラクタ
     *
     * @param balance 残高
     */
    public TBalanceHistoryDto(TBalanceDto balance) {
        super();
        BeanUtils.copyProperties(balance, this);
    }

    /**
     * @return balanceHistoryId
     */
    public final Long getBalanceHistoryId() {
        return balanceHistoryId;
    }

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
     * @return balanceOfPaymentsId
     */
    public final Long getBalanceOfPaymentsId() {
        return balanceOfPaymentsId;
    }

    /**
     * @param balanceHistoryId セットする balanceHistoryId
     */
    public final void setBalanceHistoryId(Long balanceHistoryId) {
        this.balanceHistoryId = balanceHistoryId;
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

    /**
     * @param balanceOfPaymentsId セットする balanceOfPaymentsId
     */
    public final void setBalanceOfPaymentsId(Long balanceOfPaymentsId) {
        this.balanceOfPaymentsId = balanceOfPaymentsId;
    }


}
