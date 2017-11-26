package mysys.app.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import mysys.app.biz.domain.MUserDto;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.BeanUtils;

/**
 *
 * UserのForm
 *
 * @author Ryuji
 */
public class UserForm {

    private Long userId;

    @NotNull
    @Size(min=8, max=8)
    @Pattern(regexp="[a-zA-Z0-9]*")
    private String userCode;

    @NotNull
    @Size(max=16)
    @Pattern(regexp="[a-zA-Z0-9]*")
    private String password;

    @NotNull
    @Size(max=20)
    private String userName;

    @Size(max=100)
    @Email
    private String mailAddress;

    /**
    *
    * DTOの内容をコピーする
    *
    * @param dto MUserDto
    */
   public void copyFrom(MUserDto dto) {
       // 基本は全てコピー
       BeanUtils.copyProperties(dto, this);
   }

   /**
    *
    * Formの内容からDTOを作成する
    *
    * @return Formの内容をコピーしたDTO
    */
   public MUserDto createDto() {
       MUserDto dto = new  MUserDto();
       BeanUtils.copyProperties(this, dto);
       return dto;
   }

    /**
     * @return userId
     */
    public final Long getUserId() {
        return userId;
    }

    /**
     * @return password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @return userName
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * @return mailAddress
     */
    public final String getMailAddress() {
        return mailAddress;
    }

    /**
     * @param password セットする password
     */
    public final void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param userName セットする userName
     */
    public final void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param mailAddress セットする mailAddress
     */
    public final void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * @return userCode
     */
    public final String getUserCode() {
        return userCode;
    }

    /**
     * @param userId セットする userId
     */
    public final void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @param userCode セットする userCode
     */
    public final void setUserCode(String userCode) {
        this.userCode = userCode;
    }


}
