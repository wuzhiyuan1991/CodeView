package cn.firstflag.icorebuy.common.generator.classdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类元素信息
 * @author yong
 *
 */
public class ClassElement {
	
	private String className;     //类名
	private String firstUpperName;//类名首字母大写
	private String moduleGroup;   //模块组
	private String tableName;     //表名
	private String tableType;     //表类型
	private List<IndexInfo> primaryKey;    //主键
	private List<IndexInfo> importedKeys;  //外键
	private List<ElementInfo> elements; //列元素
	private String remarks;             //备注
	
	private ElementInfo primaryKeyElement; //主键字段的元素信息对象
	private boolean hasDeletedFlag; //是否有删除标识的字段
	private ElementInfo deletedFlagElement; //删除标识字段的元素信息对象
	
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the firstUpperName
	 */
	public String getFirstUpperName() {
		return firstUpperName;
	}
	/**
	 * @param firstUpperName the firstUpperName to set
	 */
	public void setFirstUpperName(String firstUpperName) {
		this.firstUpperName = firstUpperName;
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
	 * @return the primaryKey
	 */
	public List<IndexInfo> getPrimaryKey() {
		return primaryKey;
	}
	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(List<IndexInfo> primaryKey) {
		this.primaryKey = primaryKey;
	}
	/**
	 * @return the importedKeys
	 */
	public List<IndexInfo> getImportedKeys() {
		return importedKeys;
	}
	/**
	 * @param importedKeys the importedKeys to set
	 */
	public void setImportedKeys(List<IndexInfo> importedKeys) {
		this.importedKeys = importedKeys;
	}
	/**
	 * @return the elements
	 */
	public List<ElementInfo> getElements() {
		return elements;
	}
	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<ElementInfo> elements) {
		this.elements = elements;
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
	
	/**
	 * 取得主键的信息
	 * @return
	 */
	public ElementInfo getPrimaryKeyElement() {
		if(this.primaryKeyElement == null) {
			if(this.elements != null) {
				for(ElementInfo el : this.elements) {
					if(el != null && el.getIsPrimaryKey()) {
						this.primaryKeyElement = el;
						break;
					}
				}
			}
		}
		return this.primaryKeyElement;
	}
	
	/**
	 * 返回需导入的java类型
	 * @return
	 */
	public List<String> getImportedJavaTypes() {
		List<String> list = new ArrayList<String>();
		if(this.elements != null) {
			for(ElementInfo el : this.elements) {
				String javaType = el.getJavaTypeDetail();
				if(javaType != null && !javaType.startsWith("java.lang") && !list.contains(javaType)) {
					list.add(javaType);
				}
			}
		}
		Collections.sort(list);
		
		return list;
	}
	
	/**
	 * 返回本表是否有删除标识的字段（有改字段的，应该做逻辑删除）
	 * @return
	 */
	public boolean getHasDeletedFlag() {
		if(this.hasDeletedFlag) {
			return true;
		}
		boolean res = false;
		
		if(this.elements != null) {
			for(ElementInfo el : this.elements) {
				String cl = el.getColumnName();
				if("IS_DELETED".equalsIgnoreCase(cl) || "IS_DELETE".equalsIgnoreCase(cl) || "DELETED_FLAG".equalsIgnoreCase(cl) || "DELETE_FLAG".equalsIgnoreCase(cl)) {
					res = true;
					this.hasDeletedFlag = true;
					this.deletedFlagElement = el;
					break;
				}
			}
		}
		return res;
	}
	
	/**
	 * 返回删除标识字段的元素信息对象
	 * @return
	 */
	public ElementInfo getDeletedFlagElement() {
		if(this.deletedFlagElement == null) {
			if(this.elements != null) {
				for(ElementInfo el : this.elements) {
					String cl = el.getColumnName();
					if("IS_DELETED".equalsIgnoreCase(cl) || "IS_DELETE".equalsIgnoreCase(cl) || "DELETED_FLAG".equalsIgnoreCase(cl) || "DELETE_FLAG".equalsIgnoreCase(cl)) {
						this.hasDeletedFlag = true;
						this.deletedFlagElement = el;
						break;
					}
				}
			}
		}
		return this.deletedFlagElement;
	}
}
