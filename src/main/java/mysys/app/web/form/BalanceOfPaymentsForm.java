package mysys.app.web.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import mysys.app.biz.common.kubun.BalanceOfPaymentsKubun;
import mysys.app.biz.common.kubun.ExpensesKubun;
import mysys.app.biz.common.util.KubunUtil;
import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.TBalanceOfPaymentsDto;
import mysys.app.biz.service.exception.SystemException;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NotNull
    private Long accountId;
    /** 口座名 */
    private String accountName;
    /** 口座番号表示用 */
    private String accountNumberForDisplay;
    /** 収支区分 */
    @NotNull(message="selected.none")
    private String balanceOfPaymentsKubun;
    /** 収支区分名 */
    private String balanceOfPaymentsKubunMei;
    /** 費目区分*/
    @NotNull(message="selected.none")
    private String expensesKubun;
    /** 費目区分名*/
    private String expensesKubunMei;
    /**  金額 */
    @NotNull
    @Range(min=1)
    private BigDecimal amount;
    /** 処理日 */
    @NotNull
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date transactionDate;
    /** 口座残高 */
    private BigDecimal balance;
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
     * @param balance 残高
     * @throws SystemException
    */
   public void copyFrom(TBalanceOfPaymentsDto dto, String accountName, String accountNumber, BigDecimal balance) throws SystemException {
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
       this.setExpensesKubunMei(KubunUtil.getKubunMei(ExpensesKubun.EXPENSES_KUBUN_LIST, this.getExpensesKubun()));
       // 残高
       this.setBalance(balance);
   }

   public TBalanceOfPaymentsDto createDto() {
       TBalanceOfPaymentsDto dto = new TBalanceOfPaymentsDto();
       // 基本は全てコピー
       BeanUtils.copyProperties(this, dto);
       // 収支サインを設定
        dto.setBalanceOfPaymentsSign(ProjectCommonUtil.getBalanceOfPaymentsSign(dto.getBalanceOfPaymentsKubun()));

       return dto;
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
    public final BigDecimal getAmount() {
        return amount;
    }
    /**
     * @param amount セットする amount
     */
    public final void setAmount(BigDecimal amount) {
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

    /**
     * @return balance
     */
    public final BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance セットする balance
     */
    public final void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
