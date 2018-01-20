package mysys.app.web.controller.user;

import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.MUserDto;
import mysys.app.biz.service.MUserService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.web.form.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class UserValidator implements Validator {

    @Autowired
    private MUserService mUserService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object target, Errors errors) {
        UserForm form = (UserForm)target;
        if (errors.hasErrors()) {
            return;
        }

        /* === 同じユーザコードが存在していないか === */
        if (ProjectCommonUtil.isNotBlank(form.getUserCode())) {
            try {
                MUserDto userDto = mUserService.execFindByUserCode(form.getUserCode());
                // 自身以外で存在していたらエラー
                if (!userDto.getUserId().equals(form.getUserId())) {
                    errors.rejectValue("userCode", "exists.true", "既に登録されているユーザコードです。");
                }
            } catch (DataNotFoundException e) {
                // 存在していなければOK
            }
        }
    }

}
