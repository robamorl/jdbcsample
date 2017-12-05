package mysys.app.biz.domain;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Thu Nov 30 23:20:57 JST 2017
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mysys.app.biz.domain.common.CommonDomain;

/**
 * T_earnings_and_expensesVo.
 * @author Ryuji
 * @version 1.0
 * history
 * Symbol	Date		Person		Note
 * [1]		2017/11/30	Ryuji		Generated.
 */
public class TEarningsAndExpensesDto extends CommonDomain implements Serializable {
    /** EARNINGS_AND_EXPENSES_ID*/
    private Long earningsAndExpensesId;

    /** ACCOUNT_ID*/
    private Long accountId;

    /** EARNINGS_AND_EXPENSES_KUBUN*/
    private String earningsAndExpensesKubun;

    /** AMOUNT*/
    private BigDecimal amount;

    /** TRANSACTION_DATE*/
    private Date transactionDate;

    /**
     * @return earningsAndExpensesId
     */
    public final Long getEarningsAndExpensesId() {
        return earningsAndExpensesId;
    }

    /**
     * @return accountId
     */
    public final Long getAccountId() {
        return accountId;
    }

    /**
     * @return earningsAndExpensesKubun
     */
    public final String getEarningsAndExpensesKubun() {
        return earningsAndExpensesKubun;
    }

    /**
     * @return amount
     */
    public final BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return transactionDate
     */
    public final Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param earningsAndExpensesId セットする earningsAndExpensesId
     */
    public final void setEarningsAndExpensesId(Long earningsAndExpensesId) {
        this.earningsAndExpensesId = earningsAndExpensesId;
    }

    /**
     * @param accountId セットする accountId
     */
    public final void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param earningsAndExpensesKubun セットする earningsAndExpensesKubun
     */
    public final void setEarningsAndExpensesKubun(String earningsAndExpensesKubun) {
        this.earningsAndExpensesKubun = earningsAndExpensesKubun;
    }

    /**
     * @param amount セットする amount
     */
    public final void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @param transactionDate セットする transactionDate
     */
    public final void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }


}
