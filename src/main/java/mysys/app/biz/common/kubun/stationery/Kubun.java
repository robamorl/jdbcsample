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
    private String value;
    /** 区分表示名 */
    private String label;

    /**
     *
     * コンストラクタ
     *
     * @param value 区分値
     * @param label  区分表示名
     */
    public Kubun(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * @return value
     */
    public final String getValue() {
        return value;
    }

    /**
     * @return label
     */
    public final String getLabel() {
        return label;
    }

    /**
     * @param value セットする value
     */
    public final void setValue(String value) {
        this.value = value;
    }

    /**
     * @param label セットする label
     */
    public final void setLabel(String label) {
        this.label = label;
    }

}
