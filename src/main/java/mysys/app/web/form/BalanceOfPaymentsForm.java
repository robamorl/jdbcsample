package mysys.app.web.form;

import java.util.Date;

import mysys.app.biz.common.kubun.BalanceOfPaymentsKubun;
import mysys.app.biz.common.kubun.ExpensesKubun;
import mysys.app.biz.common.util.KubunUtil;
import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.TBalanceOfPaymentsDto;
import mysys.app.biz.service.exception.SystemException;

import org.springframework.beans.BeanUtils;

/**
 *
 * 収支のForm
 *
 * @author Ryuji
 *
 */
public class BalanceOfPaymentsForm {

    /** 収支ID */
    private Long balanceOfPaymentsId;
    /** 口座ID*/
    private Long accountId;
    /** 口座名 */
    private String accountName;
    /** 口座番号表示用 */
    private String accountNumberForDisplay;
    /** 収支区分 */
    private String balanceOfPaymentsKubun;
    /** 収支区分名 */
    private String balanceOfPaymentsKubunMei;
    /** 費目区分*/
    private String expensesKubun;
    /** 費目区分名*/
    private String expensesKubunMei;
    /**  金額 */
    private String amount;
    /** 処理日 */
    private Date transactionDate;
    /** 登録日 */
    private Date entryDate;
    /** 登録者 */
    private String entryUser;
    /** 更新日 */
    private Date updateDate;
    /** 更新者 */
    private String updateUser;

    /**
    *
    * DTOの内容をコピーする
    *
    * @param dto TBalanceOfPaymentsDto
     * @param accountName 口座名
     * @param accountNumber 口座番号
    * @throws SystemException
    */
   public void copyFrom(TBalanceOfPaymentsDto dto, String accountName, String accountNumber) throws SystemException {
       // 基本は全てコピー
       BeanUtils.copyProperties(dto, this);
       // 口座名
       this.setAccountName(accountName);
       // 口座番号
       this.setAccountNumberForDisplay(ProjectCommonUtil.getAccountNumberForDisplay(accountNumber));
       // 区分名は取得
       // 収支区分
       this.setBalanceOfPaymentsKubunMei(KubunUtil.getKubunMei(BalanceOfPaymentsKubun.BOP_KUBUN_LIST, this.getBalanceOfPaymentsKubun()));
       // 費目区分
       this.setBalanceOfPaymentsKubunMei(KubunUtil.getKubunMei(ExpensesKubun.EXPENSES_KUBUN_LIST, this.getExpensesKubun()));
   }

    /**
     * @return balanceOfPaymentsId
     */
    public final Long getBalanceOfPaymentsId() {
        return balanceOfPaymentsId;
    }
    /**
     * @param balanceOfPaymentsId セットする balanceOfPaymentsId
     */
    public final void setBalanceOfPaymentsId(Long balanceOfPaymentsId) {
        this.balanceOfPaymentsId = balanceOfPaymentsId;
    }
    /**
     * @return accountId
     */
    public final Long getAccountId() {
        return accountId;
    }
    /**
     * @param accountId セットする accountId
     */
    public final void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    /**
     * @return balanceOfPaymentsKubun
     */
    public final String getBalanceOfPaymentsKubun() {
        return balanceOfPaymentsKubun;
    }
    /**
     * @param balanceOfPaymentsKubun セットする balanceOfPaymentsKubun
     */
    public final void setBalanceOfPaymentsKubun(String balanceOfPaymentsKubun) {
        this.balanceOfPaymentsKubun = balanceOfPaymentsKubun;
    }
    /**
     * @return balanceOfPaymentsKubunMei
     */
    public final String getBalanceOfPaymentsKubunMei() {
        return balanceOfPaymentsKubunMei;
    }
    /**
     * @param balanceOfPaymentsKubunMei セットする balanceOfPaymentsKubunMei
     */
    public final void setBalanceOfPaymentsKubunMei(String balanceOfPaymentsKubunMei) {
        this.balanceOfPaymentsKubunMei = balanceOfPaymentsKubunMei;
    }
    /**
     * @return amount
     */
    public final String getAmount() {
        return amount;
    }
    /**
     * @param amount セットする amount
     */
    public final void setAmount(String amount) {
        this.amount = amount;
    }
    /**
     * @return transactionDate
     */
    public final Date getTransactionDate() {
        return transactionDate;
    }
    /**
     * @param transactionDate セットする transactionDate
     */
    public final void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    /**
     * @return entryDate
     */
    public final Date getEntryDate() {
        return entryDate;
    }
    /**
     * @param entryDate セットする entryDate
     */
    public final void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    /**
     * @return entryUser
     */
    public final String getEntryUser() {
        return entryUser;
    }
    /**
     * @param entryUser セットする entryUser
     */
    public final void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }
    /**
     * @return updateDate
     */
    public final Date getUpdateDate() {
        return updateDate;
    }
    /**
     * @param updateDate セットする updateDate
     */
    public final void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * @return updateUser
     */
    public final String getUpdateUser() {
        return updateUser;
    }
    /**
     * @param updateUser セットする updateUser
     */
    public final void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
     * @return expensesKubunMei
     */
    public final String getExpensesKubunMei() {
        return expensesKubunMei;
    }

    /**
     * @param expensesKubunMei セットする expensesKubunMei
     */
    public final void setExpensesKubunMei(String expensesKubunMei) {
        this.expensesKubunMei = expensesKubunMei;
    }

    /**
     * @return accountName
     */
    public final String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName セットする accountName
     */
    public final void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return accountNumberForDisplay
     */
    public final String getAccountNumberForDisplay() {
        return accountNumberForDisplay;
    }

    /**
     * @param accountNumberForDisplay セットする accountNumberForDisplay
     */
    public final void setAccountNumberForDisplay(String accountNumberForDisplay) {
        this.accountNumberForDisplay = accountNumberForDisplay;
    }

}
