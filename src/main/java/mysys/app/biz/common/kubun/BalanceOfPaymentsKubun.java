package mysys.app.biz.common.kubun;

import java.util.Arrays;
import java.util.List;

import mysys.app.biz.common.kubun.stationery.Kubun;

/**
 *
 * 収支区分を管理するクラス
 *
 * @author Ryuji
 *
 */
public class BalanceOfPaymentsKubun {

    /** 収支区分：収入 */
    public static final String BOP_KUBUN_INCOME = "1";

    /** 収支区分：支出 */
    public static final String BOP_KUBUN_EXPENSE = "0";

    /** 収支区分のリスト */
    public static final List<Kubun> BOP_KUBUN_LIST = Arrays.asList(new Kubun[]{
            new Kubun(BOP_KUBUN_INCOME, "収入"),
            new Kubun(BOP_KUBUN_EXPENSE, "支出"),
    });

}
