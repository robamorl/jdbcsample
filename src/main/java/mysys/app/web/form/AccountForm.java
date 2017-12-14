package mysys.app.web.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import mysys.app.biz.common.kubun.AccountKubun;
import mysys.app.biz.common.util.KubunUtil;
import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.service.exception.SystemException;
import mysys.security.util.LoginUserUtil;

import org.springframework.beans.BeanUtils;

/**
 *
 * AccountのForm
 *
 * @author Ryuji
 *
 */
public class AccountForm {

    /** 口座ID */
    private Long accountId;
    /** 口座名 */
    @NotNull
    @Size(max=30)
    private String accountName;
    /** 口座番号 */
    @NotNull
    @Size(min=16, max=16)
    @Pattern(regexp="[0-9]*")
    private String accountNumber;
    /** 口座番号表示用 */
    private String accountNumberForDisplay;
    /** ユーザID */
    private Long userId;
    /** 口座区分 */
    private String accountKubun;
    /**  口座区分名 */
    private String accountKubunMei;
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
     * @param dto MAccountDto
     * @throws SystemException
     */
    public void copyFrom(MAccountDto dto) throws SystemException {
        // 基本は全てコピー
        BeanUtils.copyProperties(dto, this);
        // 口座番号表示用を設定
        String aNForDisp = ProjectCommonUtil.getAccountNumberForDisplay(this.getAccountNumber());
        this.setAccountNumberForDisplay(aNForDisp);
        // 区分名は取得
        String accountKubunMei = KubunUtil.getKubunMei(AccountKubun.ACCOUNT_KUBUN_LIST, this.getAccountKubun());
        this.setAccountKubunMei(accountKubunMei);
    }

    /**
     *
     * Formの内容からDTOを作成する
     *
     * @return Formの内容をコピーしたDTO
     */
    public MAccountDto createDto() {
        MAccountDto dto = new  MAccountDto();
        BeanUtils.copyProperties(this, dto);
        // ユーザIDをセット
        dto.setUserId(LoginUserUtil.getLoginUserId());
        return dto;
    }

    /**
     * @return entryUser
     */
    public final String getEntryUser() {
        return entryUser;
    }

    /**
     * @return updateUser
     */
    public final String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param entryUser セットする entryUser
     */
    public final void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }

    /**
     * @param updateUser セットする updateUser
     */
    public final void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
     * @return entryDate
     */
    public final Date getEntryDate() {
        return entryDate;
    }

    /**
     * @return updateDate
     */
    public final Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param entryDate セットする entryDate
     */
    public final void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @param updateDate セットする updateDate
     */
    public final void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return accountId
     */
    public final Long getAccountId() {
        return accountId;
    }
    /**
     * @return accountKubunMei
     */
    public final String getAccountKubunMei() {
        return accountKubunMei;
    }
    /**
     * @param accountId セットする accountId
     */
    public final void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    /**
     * @param accountKubunMei セットする accountKubunMei
     */
    public final void setAccountKubunMei(String accountKubunMei) {
        this.accountKubunMei = accountKubunMei;
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

}
