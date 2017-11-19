package mysys.app.biz.common.kubun;

import java.util.Arrays;
import java.util.List;

import mysys.app.biz.common.kubun.stationery.Kubun;

/**
 *
 * 口座区分を管理するクラス
 *
 * @author Ryuji
 *
 */
public class AccountKubun {

    /** 口座区分：普通 */
    public static final String ACCOUNT_KUBUN_NORMAL = "0";

    /** 口座区分：貯金 */
    public static final String ACCOUNT_KUBUN_SAVINGS = "1";

    /** 口座区分のリスト */
    public static final List<Kubun> ACCOUNT_KUBUN_LIST = Arrays.asList(new Kubun[]{
            new Kubun(ACCOUNT_KUBUN_NORMAL, "普通"),
            new Kubun(ACCOUNT_KUBUN_SAVINGS, "貯金"),
    });

}
