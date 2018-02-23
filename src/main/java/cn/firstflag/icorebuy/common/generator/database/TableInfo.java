package cn.firstflag.icorebuy.common.generator.database;


/**
 * 表元素信息
 * @author yong
 *
 */
public class TableInfo {
	
	private String tableName;     //表名
	private String moduleGroup;   //模块组
	private String tableType;     //表类型
	private String remarks;       //备注
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the moduleGroup
	 */
	public String getModuleGroup() {
		return moduleGroup;
	}
	/**
	 * @param moduleGroup the moduleGroup to set
	 */
	public void setModuleGroup(String moduleGroup) {
		this.moduleGroup = moduleGroup;
	}
	/**
	 * @return the tableType
	 */
	public String getTableType() {
		return tableType;
	}
	/**
	 * @param tableType the tableType to set
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
