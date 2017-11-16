package mysys.app.dao.common;

public class SqlCondition {

    private String column;
    private String condition;
    private Object compared;

    public static final String EQ = "=";
    public static final String GT = "<";
    public static final String GE = "<=";
    public static final String LT = ">";
    public static final String LE = ">=";

    public SqlCondition(String column, String conditon, Object compared) {
        this.column = column;
        this.condition = conditon;
        this.compared = compared;
    }

    public final String getConditionQuery() {
        return this.getColumn() + " " + this.getCondition() + " " + this.getCompared().toString();
    }

    /**
     * @return column
     */
    public final String getColumn() {
        return column;
    }
    /**
     * @return condition
     */
    public final String getCondition() {
        return condition;
    }
    /**
     * @return compared
     */
    public final Object getCompared() {
        return compared;
    }
    /**
     * @param column セットする column
     */
    public final void setColumn(String column) {
        this.column = column;
    }
    /**
     * @param condition セットする condition
     */
    public final void setCondition(String condition) {
        this.condition = condition;
    }
    /**
     * @param compared セットする compared
     */
    public final void setCompared(Object compared) {
        this.compared = compared;
    }


}
