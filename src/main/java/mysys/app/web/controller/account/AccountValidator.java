package mysys.app.web.controller.account;

import mysys.app.biz.common.util.ProjectCommonUtil;
import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.web.form.AccountForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class AccountValidator implements Validator {

    @Autowired
    MAccountService mAccountService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return AccountForm.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object target, Errors errors) {
        AccountForm form = (AccountForm)target;
        if (errors.hasErrors()) {
            return;
        }

        /* === 同じ口座番号が存在していないか === */
        if (ProjectCommonUtil.isNotBlank(form.getAccountNumber())) {
            try {
                MAccountDto accountDto = mAccountService.execFindByAccountNumber(form.getAccountNumber());
                // 自身以外で存在していたらエラー
                if (!accountDto.getAccountId().equals(form.getAccountId())) {
                    errors.rejectValue("accountNumber", "exists.true", "既に登録されている口座番号です。");
                }
            } catch (DataNotFoundException e) {
                // 存在していなければOK
            }
        }
    }

}
