package mysys.app.biz.common.kubun.stationery;

/**
 *
 * 区分値を管理するクラス
 *
 * @author Ryuji
 *
 */
public class Kubun {

    /** 区分値 */
    private String id;
    /** 区分表示名 */
    private String value;

    /**
     *
     * コンストラクタ
     *
     * @param id 区分値
     * @param value  区分表示名
     */
    public Kubun(String id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * @return id
     */
    public final String getId() {
        return id;
    }
    /**
     * @return value
     */
    public final String getValue() {
        return value;
    }
    /**
     * @param id セットする id
     */
    public final void setId(String id) {
        this.id = id;
    }
    /**
     * @param value セットする value
     */
    public final void setValue(String value) {
        this.value = value;
    }
}
