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
 * TBalanceOfPaymentsDto.
 * @author Ryuji
 * @version 1.0
 * history
 * Symbol	Date		Person		Note
 * [1]		2017/11/30	Ryuji		Generated.
 */
public class TBalanceOfPaymentsDto extends CommonDomain implements Serializable {
    /** EARNINGS_AND_EXPENSES_ID*/
    private Long balanceOfPaymentsId;

    /** ACCOUNT_ID*/
    private Long accountId;

    /** EARNINGS_AND_EXPENSES_KUBUN*/
    private String balanceOfPaymentsKubun;

    /** BALANCE_OF_PAYMENTS_SIGN*/
    private Long balanceOfPaymentsSign;

    /** EXPENSES_KUBUN*/
    private String expensesKubun;

    /** AMOUNT*/
    private BigDecimal amount;

    /** TRANSACTION_DATE*/
    private Date transactionDate;

    /**
     * @return balanceOfPaymentsId
     */
    public final Long getBalanceOfPaymentsId() {
        return balanceOfPaymentsId;
    }

    /**
     * @return accountId
     */
    public final Long getAccountId() {
        return accountId;
    }

    /**
     * @return balanceOfPaymentsKubun
     */
    public final String getBalanceOfPaymentsKubun() {
        return balanceOfPaymentsKubun;
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
     * @param balanceOfPaymentsId セットする balanceOfPaymentsId
     */
    public final void setBalanceOfPaymentsId(Long balanceOfPaymentsId) {
        this.balanceOfPaymentsId = balanceOfPaymentsId;
    }

    /**
     * @param accountId セットする accountId
     */
    public final void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param balanceOfPaymentsKubun セットする balanceOfPaymentsKubun
     */
    public final void setBalanceOfPaymentsKubun(String balanceOfPaymentsKubun) {
        this.balanceOfPaymentsKubun = balanceOfPaymentsKubun;
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

    /**
     * @return expensesKubun
     */
    public final String getExpensesKubun() {
        return expensesKubun;
    }

    /**
     * @param expensesKubun セットする expensesKubun
     */
    public final void setExpensesKubun(String expensesKubun) {
        this.expensesKubun = expensesKubun;
    }

    /**
     * @return balanceOfPaymentsSign
     */
    public final Long getBalanceOfPaymentsSign() {
        return balanceOfPaymentsSign;
    }

    /**
     * @param balanceOfPaymentsSign セットする balanceOfPaymentsSign
     */
    public final void setBalanceOfPaymentsSign(Long balanceOfPaymentsSign) {
        this.balanceOfPaymentsSign = balanceOfPaymentsSign;
    }


}
