package mysys.app.dao.common;

public class SqlColumn {
	private String columnName;
	private String javaPropertyName;
	public SqlColumn(String columnName, String javaPropertyName) {
		this.columnName = columnName;
		this.javaPropertyName = javaPropertyName;
	}
	/**
	 * @return columnName
	 */
	public final String getColumnName() {
		return columnName;
	}
	/**
	 * @return javaPropertyName
	 */
	public final String getJavaPropertyName() {
		return javaPropertyName;
	}
	/**
	 * @param columnName セットする columnName
	 */
	public final void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @param javaPropertyName セットする javaPropertyName
	 */
	public final void setJavaPropertyName(String javaPropertyName) {
		this.javaPropertyName = javaPropertyName;
	}
}
