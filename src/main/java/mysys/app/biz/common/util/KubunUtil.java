package mysys.app.biz.common.util;

import java.util.List;

import mysys.app.biz.common.kubun.stationery.Kubun;

/**
 *
 * 区分に関するUtil
 *
 * @author Ryuji
 *
 */
public class KubunUtil {

    /**
     *
     * 区分リストの中から引数のkubunIdに該当する区分名を取得する
     *
     * @param kubunList 区分リスト
     * @param kubunId 区分値
     * @return 区分名
     */
    public static String getKubunMei(List<Kubun> kubunList, String kubunId) {
        // 引数のリストをループで回して検索
        for (Kubun kubun : kubunList) {
            if (kubun.getId().equals(kubunId)) {
                // 見つかった時点で区分名返却
                return kubun.getValue();
            }
        }
        // 見つからなかったらnull
        return null;
    }
}
