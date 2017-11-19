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
     * @param accountNumber 口座番号(Long)
     * @return 画面表示用へ変換した口座番号
     * @throws SystemException
     */
    public static final String getAccountNumberForDisplay(Long accountNumber) throws SystemException {
        if (accountNumber == null) {
            throw new SystemException("想定外：口座番号がnull");
        }
        String accountNumberStr = accountNumber.toString();

        if (accountNumberStr.length() != 16) {
            throw new SystemException("想定外：口座番号が16桁でないl");
        }

        return accountNumberStr.substring(0, 6) + "-" + accountNumberStr.substring(6, 14) + "-" + accountNumberStr.substring(14, 16);
    }
}
