package mysys.app.biz.common.util;

import java.math.BigDecimal;

import javax.jws.WebParam.Mode;

import mysys.app.biz.common.kubun.BalanceOfPaymentsKubun;
import mysys.app.biz.service.exception.SystemException;

import org.springframework.ui.Model;

/**
 *
 * プロジェクト固有のUtil
 *
 * @author Ryuji
 *
 */
public class ProjectCommonUtil {

    /**
     *
     * 口座番号を画面表示用へ変換します。
     *
     * @param accountNumber 口座番号(String)
     * @return 画面表示用へ変換した口座番号
     * @throws SystemException
     */
    public static final String getAccountNumberForDisplay(String accountNumber) throws SystemException {
        if (accountNumber == null) {
            throw new SystemException("想定外：口座番号がnull");
        }
        String accountNumberStr = accountNumber.toString();

        if (accountNumberStr.length() != 16) {
            throw new SystemException("想定外：口座番号が16桁でないl");
        }

        return accountNumberStr.substring(0, 6) + "-" + accountNumberStr.substring(6, 14) + "-" + accountNumberStr.substring(14, 16);
    }

    /**
     *
     * 引数のObjectを文字列へ変換します。
     *
     * @param obj Object
     * @return 文字列
     */
    public static final String convertObjectToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     *
     * 引数がnull/blankか判定する。
     *
     * @param arg 検査するObject
     * @return true:null/blank / false:null/balnkでない
     */
    public static final boolean isBlank(Object arg) {
        if (arg == null) {
            return true;
        }

        return arg.toString().length() == 0;
    }

    /**
     *
     * 引数がnull/blankでないか判定する。
     *
     * @param arg 検査するObject
     * @return true:null/blankでない / false:null/balnk
     */
    public static final boolean isNotBlank(Object arg) {
        return !isBlank(arg);
    }

    /**
     *
     * モデルアトリビュートにparamStringが存在しない場合のみ、paramObjectを設定します。
     *
     * @param model {@link Model}
     * @param paramString 設定する名前
     * @param paramObject 設定するObject
     */
    public static final void addModelAttribute(Model model, String paramString, Object paramObject) {
        if (!model.containsAttribute(paramString)) {
            model.addAttribute(paramString, paramObject);
        }
    }

    /**
     *
     * 引数のメッセージをモデルアトリビュートへセットします
     *
     * @param model {@link Mode}
     * @param message メッセージ
     */
    public static final void addMessage(Model model, String message) {
        if (message != null
                && message.length() != 0) {
            model.addAttribute("message", message);
        }
    }

    /**
     *
     * 登録完了メッセージをモデルアトリビュートへセットします。
     *
     * @param model {@link Model}
     */
    public static final void addInsertDoneMessage(Model model) {
        addMessage(model, "正常に登録が完了しました。");
    }

    /**
     *
     * 更新完了メッセージをモデルアトリビュートへセットします。
     *
     * @param model {@link Model}
     */
    public static final void addUpdateDoneMessage(Model model) {
        addMessage(model, "正常に更新が完了しました。");
    }

    /**
     *
     * 削除完了メッセージをモデルアトリビュートへセットします。
     *
     * @param model {@link Model}
     */
    public static final void addDeleteDoneMessage(Model model) {
        addMessage(model, "正常に削除が完了しました。");
    }

    /**
    *
    * 引数のメッセージをモデルアトリビュートへセットします。
    *
    * @param model {@link Model}
    * @param message メッセージ
    */
   public static final void addGeneralMessage(Model model, String message) {
       addMessage(model, message);
   }

   /**
    *
    * 引数の収支区分より収支サインを取得する。
    *
    * @param balanceOfPaymentsKubun 収支区分
    * @return 収支サイン
    */
   public static final Long getBalanceOfPaymentsSign(String balanceOfPaymentsKubun) {
       if (BalanceOfPaymentsKubun.BOP_KUBUN_EXPENSE.equals(balanceOfPaymentsKubun)) {
           return Long.valueOf(-1L);
       }
       return Long.valueOf(1L);
   }

   /**
    *
    * 引数の残高、金額、サインから残高を計算します。
    *
    * @param balance 残高
    * @param amount 金額
    * @param sign サイン
    * @return 残高
    */
   public static final BigDecimal calcBalance(BigDecimal balance, BigDecimal amount, Long sign) {
       return balance.add(amount.multiply(BigDecimal.valueOf(sign)));
   }
}
