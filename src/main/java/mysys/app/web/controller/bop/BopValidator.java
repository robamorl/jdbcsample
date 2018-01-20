package mysys.app.web.controller.bop;

import java.math.BigDecimal;

import mysys.app.biz.common.kubun.BalanceOfPaymentsKubun;
import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.domain.TBalanceDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.TBalanceService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.web.form.BalanceOfPaymentsForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BopValidator implements Validator {

    @Autowired
    private MAccountService accountService;
    @Autowired
    private TBalanceService balanceService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BalanceOfPaymentsForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        BalanceOfPaymentsForm form = (BalanceOfPaymentsForm)target;

        /* === 選択された口座が存在すること === */
        MAccountDto accountDto;
        try {
            accountDto = accountService.execFind(form.getAccountId());
        } catch (DataNotFoundException e) {
            // 存在しない場合エラー
            errors.rejectValue("accountId", "data.notfound", new Object[]{"口座"},
                    "選択された口座は存在しません。");
            return;
        }

        /* === 口座に紐づく残高が存在すること === */
        TBalanceDto balanceDto;
        try {
            balanceDto = balanceService.execFindByAccountId(accountDto.getAccountId());
        } catch (DataNotFoundException e) {
            // 存在しない場合エラー
            errors.rejectValue("accountId", "data.notfound", new Object[]{"口座に紐づく残高"},
                    "選択された口座に紐づく残高は存在しません。");
            return;
        }

        /* === 収支区分が支出の場合、残高がマイナスにならないこと === */
        if (BalanceOfPaymentsKubun.BOP_KUBUN_EXPENSE.equals(form.getBalanceOfPaymentsKubun())) {
            // 現在の残高から支出を引く
            BigDecimal resultBalance = balanceDto.getBalance().subtract(form.getAmount());
            // 引いた結果が0以下でないか
            if (BigDecimal.ZERO.compareTo(resultBalance) > 0) {
                errors.rejectValue("amount", "orign", "残高がマイナスになってしまう為、登録できません。");
            }
        }
    }

}
