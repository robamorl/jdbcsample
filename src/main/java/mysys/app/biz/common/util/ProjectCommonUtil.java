package mysys.app.biz.common.util;

import mysys.app.biz.service.exception.SystemException;

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
}
