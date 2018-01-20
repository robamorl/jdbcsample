package mysys.app.dao.common;

import java.util.List;

public class SqlCondition {

    private String column;
    private String condition;
    private Object compared;
    private List<Object> comparedList;

    public static final String EQ = "=";
    public static final String GT = "<";
    public static final String GE = "<=";
    public static final String LT = ">";
    public static final String LE = ">=";
    public static final String IN = "IN";

    @SuppressWarnings("unchecked")
    public SqlCondition(String column, String conditon, Object compared) {
        this.column = column;
        this.condition = conditon;
        if (compared instanceof List) {
            this.comparedList = (List<Object>)compared;
        } else {
            this.compared = compared;
        }
    }

    public final String getConditionQuery() {
        StringBuffer query = new StringBuffer();
        if (IN.equals(this.getCondition())) {
            // IN句の場合
            if (!this.getComparedList().isEmpty()) {
                // 検索するリストが空でない場合は追加
                query.append(this.getColumn());
                query.append(" ");
                query.append(this.getCondition());
                query.append(" (");
                int sizeCount = 1;
                for (Object comparedElm : this.getComparedList()) {
                    query.append("'");
                    query.append(comparedElm.toString());
                    query.append("'");
                    if (sizeCount < this.getComparedList().size()) {
                        query.append(",");
                    }
                    sizeCount++;
                }
                query.append(")");
            }
        } else {
            // 上記以外
            query.append(this.getColumn());
            query.append(" ");
            query.append(this.getCondition());
            query.append(" ");
            query.append("'");
            query.append(this.getCompared());
            query.append("'");
        }
        return query.toString();
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

    /**
     * @return comparedList
     */
    public final List<Object> getComparedList() {
        return comparedList;
    }

    /**
     * @param comparedList セットする comparedList
     */
    public final void setComparedList(List<Object> comparedList) {
        this.comparedList = comparedList;
    }


}
