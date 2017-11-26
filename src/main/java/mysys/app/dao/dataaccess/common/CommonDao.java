package mysys.app.dao.dataaccess.common;

import java.text.MessageFormat;
import java.util.List;

import mysys.app.biz.domain.common.CommonDomain;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;

import org.springframework.dao.EmptyResultDataAccessException;

public class CommonDao {

    public static final SqlColumn ENTRY_DATE = new SqlColumn("ENTRY_DATE", "entryDate");
    public static final SqlColumn ENTRY_USER = new SqlColumn("ENTRY_USER", "entryUser");
    public static final SqlColumn UPDATE_DATE = new SqlColumn("UPDATE_DATE", "updateDate");
    public static final SqlColumn UPDATE_USER = new SqlColumn("UPDATE_USER", "updateUser");
    public static final SqlColumn DELETE_FLG = new SqlColumn("DELETE_FLG", "deleteFlg");

    /** SELECT QUERY */
    private static final String SELECT_NORMAL_QUERY = "SELECT * FROM {0} WHERE DELETE_FLG = 0 ";

    /** SELECT QUERY */
    private static final String SELECT_QUERY = "SELECT * FROM {0} ";

    /** SELECT SEQUENCE QUERY */
    private static final String SELECT_SEQUENCE_QUERY = "SELECT {0} FROM DUAL ";

    /** UPDATE QUERY */
    private static final String UPDATE_QUERY = "UPDATE {0} SET {1} WHERE  DELETE_FLG = 0 AND {2}";

    /** INSERT QUERY */
    private static final String INSERT_QUERY = "INSERT INTO {0} ({1}) VALUES ({2}) ";

    /** DELETE QUERY */
    private static final String DELETE_QUERY = "DELETE FROM {0} WHERE {1}";

    /** SEQUENCE NEXT VAL */
    private static final String SEQUENCE_NEXT_VAL = "{0}.NEXTVAL";

    /** SEQUENCE_NAME */
    private static final String SEQUENCE_NAME = "{0}_SEQ";


    /** COLUMN LIST 変換時タイプ指定用 */
    private static final int TYPE_INSERT_COLUMNS = 0;
    private static final int TYPE_INSERT_VALUES= 1;
    private static final int TYPE_UPDATE = 2;

    /**
     *
     * 全件検索用SQL発行
     *
     * @param tableName テーブル名
     * @return クエリー
     */
    protected String getSelectQueryByAll(String tableName) {
        String query = MessageFormat.format(SELECT_NORMAL_QUERY, (Object)tableName);
       return  query;
    }

    /**
     *
     * 条件付き検索用SQL発行
     *
     * @param tableName テーブル名
     * @param conditions 条件
     * @return クエリー
     */
    protected String getSelectQueryByCondition(String tableName, SqlCondition... conditions) {
        StringBuffer query = new StringBuffer();
        query.append(MessageFormat.format(SELECT_NORMAL_QUERY, (Object)tableName));
        if (conditions != null) {
            query.append("AND ");
            query.append(this.getCondition(conditions));
        }
        return query.toString();
    }


    /**
    *
    * 条件付き検索用SQL発行(削除フラグONを含む)
    *
    * @param tableName テーブル名
    * @param conditions 条件
    * @return クエリー
    */
    protected String getSelectQueryByConditionWithContainsDeletedRecord(String tableName, SqlCondition... conditions) {
        StringBuffer query = new StringBuffer();
        query.append(MessageFormat.format(SELECT_QUERY, (Object)tableName));
        if (conditions != null) {
            query.append("WHERE ");
            query.append(this.getCondition(conditions));
        }
        return query.toString();
    }

    /**
     *
     * 引数の条件を元に条件文を作成します。
     *
     * @param conditions 条件
     * @return 条件文のクエリー
     */
    private String getCondition(SqlCondition... conditions) {
        StringBuffer conditionQuery = new StringBuffer();
        for (SqlCondition condition : conditions) {
            if (conditionQuery.length() > 0) {
                conditionQuery.append(" AND ");
            }
            conditionQuery.append(condition.getConditionQuery());
        }
        return conditionQuery.toString();
    }

    /**
     *
     * 更新用SQLを発行します。
     *
     * @param tableName テーブル名
     * @param columns カラムリスト
     * @param pk 更新条件のPK
     * @return クエリー
     */
    protected String getUpdateQuery(String tableName, List<SqlColumn> columns, Object pk) {
        StringBuffer query = new StringBuffer();
        SqlCondition[] conditions = new SqlCondition[]{this.getPkConditionByEqual(columns.get(0), pk)};
        query.append(MessageFormat.format(
                UPDATE_QUERY,
                new Object[] { tableName, this.convertColumnListToQuery(columns, TYPE_UPDATE),
                        this.getCondition(conditions) }));
        return query.toString();
    }

    /**
     *
     * 挿入用SQLを発行します。
     *
     * @param tableName テーブル名
     * @param columns カラムリスト
     * @return クエリー
     */
    protected String getInsertQuery(String tableName, List<SqlColumn> columns) {
        StringBuffer query = new StringBuffer();
        query.append(MessageFormat.format(
                INSERT_QUERY,
                new Object[] { tableName, this.convertColumnListToQuery(columns, TYPE_INSERT_COLUMNS),
                        this.convertColumnListToQuery(columns, TYPE_INSERT_VALUES)}));
        return query.toString();
    }

    /**
     *
     * 削除用SQLを発行します。
     *
     * @param tableName テーブル名
     * @param columns カラムリスト
     * @param pk PK
     * @return クエリー
     */
    protected String getDeleteQuery(String tableName, List<SqlColumn> columns, Object pk) {
        String query = MessageFormat.format(DELETE_QUERY,
                new Object[] { tableName, this.getCondition(this.getPkConditionByEqual(columns.get(0), pk)) });
        return query;
    }

    /**
     *
     * 引数のカラムリストをタイプで指定した形式のクエリへ変換します。
     *
     * @param columns カラムリスト
     * @param convType 変換タイプ
     * @return クエリー
     */
    private String convertColumnListToQuery(List<SqlColumn> columns, int convType) {
        StringBuffer result = new StringBuffer();
        int count = 1;
        for (SqlColumn column : columns) {
            switch (convType) {
            case TYPE_INSERT_COLUMNS:
                result.append(column.getColumnName());
                break;
            case TYPE_INSERT_VALUES:
                    result.append(":" + column.getJavaPropertyName());
                break;
            case TYPE_UPDATE:
                result.append(column.getColumnName());
                result.append(SqlCondition.EQ);
                result.append(":" + column.getJavaPropertyName());
                break;
            default:
                break;
            }
            if (count != columns.size()) {
                result.append(",");
            }
            count++;
        }
        return result.toString();
    }

    /**
     *
     * PKに対するイコール条件のSqlConditonを作成します。
     *
     * @param pkColumn PKカラム
     * @param pk PK
     * @return SQLConditon
     */
    protected SqlCondition getPkConditionByEqual(SqlColumn pkColumn,  Object pk) {
        return new SqlCondition(pkColumn.getColumnName(), SqlCondition.EQ, pk.toString());
    }

    /**
     *
     * シーケンス値取得用のSELECT文を作成します。
     *
     * @param pk pkカラム
     * @return シーケンス値取得用のSELECT文
     */
    protected String getSequenceSelectQuery(SqlColumn pk) {
        return MessageFormat.format(SELECT_SEQUENCE_QUERY,
                (Object[]) new String[] {
                this.getSequenceNextValQuery(pk)});
    }

    /**
     *
     * シーケンス値取得用のSQL文を作成します。
     *
     * @param pk pkカラム
     * @return シーケンス値取得用のSQL文
     */
    private String getSequenceNextValQuery(SqlColumn pk) {
        return MessageFormat.format(SEQUENCE_NEXT_VAL, (Object)this.getSequenceName(pk));
    }

    /**
     *
     * シーケンス名を取得します。
     * シーケンス名は「[PK名]_SEQ」であること
     *
     * @param pk PKカラム
     * @return シーケンス名
     */
    private String getSequenceName(SqlColumn pk) {
        return MessageFormat.format(SEQUENCE_NAME, (Object)pk.getColumnName());
    }

    /**
     *
     * sourceからログ系の情報をtargetへコピーします。
     *
     * @param source コピー元
     * @param target コピー先
     * @throws EmptyResultDataAccessException
     */
    protected void copyLogData(CommonDomain source, CommonDomain target) throws EmptyResultDataAccessException {
        if (source.getDeleteFlg().booleanValue()) {
            // 削除済の場合
            throw new EmptyResultDataAccessException("想定外：対象データが削除済の可能性があります。", 0);
        }
        target.setEntryDate(source.getEntryDate());
        target.setEntryUser(source.getEntryUser());
        target.setUpdateDate(source.getUpdateDate());
        target.setUpdateUser(source.getUpdateUser());
        if (target.getDeleteFlg() == null
                || !target.getDeleteFlg().booleanValue()) {
            target.setDeleteFlg(source.getDeleteFlg());
        }
    }
}
