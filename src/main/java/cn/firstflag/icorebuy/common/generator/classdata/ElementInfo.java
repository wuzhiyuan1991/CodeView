package cn.firstflag.icorebuy.common.generator.classdata;

import java.util.HashSet;
import java.util.Set;

/**
 * 列元素信息
 * @author yong
 *
 */
public class ElementInfo {
	
	private static final Set<String> COLUMN_TYPES_OF_STRING = new HashSet<String>();
	
	private static final Set<String> COLUMN_TYPES_OF_CLOB = new HashSet<String>();
	
	private static final Set<String> COLUMN_TYPES_OF_BLOB = new HashSet<String>();
	
	private static final Set<String> COLUMN_TYPES_OF_OBJECT = new HashSet<String>();
	
	static {
		COLUMN_TYPES_OF_STRING.add("VARCHAR");
		COLUMN_TYPES_OF_STRING.add("VARCHAR2");
		COLUMN_TYPES_OF_STRING.add("NVARCHAR");
		COLUMN_TYPES_OF_STRING.add("LONGVARCHAR");
		COLUMN_TYPES_OF_STRING.add("CHAR");
		COLUMN_TYPES_OF_STRING.add("NCHAR");
		
		COLUMN_TYPES_OF_CLOB.add("CLOB");
		COLUMN_TYPES_OF_CLOB.add("TEXT");
		
		COLUMN_TYPES_OF_BLOB.add("BLOB");
		COLUMN_TYPES_OF_BLOB.add("BINARY");
		COLUMN_TYPES_OF_BLOB.add("IMAGE");
		COLUMN_TYPES_OF_BLOB.add("LONGVARBINARY");
		COLUMN_TYPES_OF_BLOB.add("VARBINARY");
		
		COLUMN_TYPES_OF_OBJECT.add("JAVA_OBJECT");
	}
	
	private String javaName;        //元素名
	private String firstUpperName;  //首字母大写
	private String columnName;      //列表
	private String columnType;      //列类型
	private Integer columnLength;   //列长度
	private boolean isNullAble;     //是否可以为空
	private String javaType;        //java类型
	private String javaTypeDetail;  //java类型详细
	private String defaultValue;    //默认值
	private String remark;          //列备注信息
	private boolean isPrimaryKey;   //是否是主键
	private boolean isImportedKey;  //是否是外键
	
	/**
	 * @return the javaName
	 */
	public String getJavaName() {
		return javaName;
	}
	/**
	 * @param javaName the javaName to set
	 */
	public void setJavaName(String javaName) {
		this.javaName = javaName;
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
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the columnType
	 */
	public String getColumnType() {
		return columnType;
	}
	/**
	 * @param columnType the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	/**
	 * @return the columnLength
	 */
	public Integer getColumnLength() {
		return columnLength;
	}
	/**
	 * @param columnLength the columnLength to set
	 */
	public void setColumnLength(Integer columnLength) {
		this.columnLength = columnLength;
	}
	/**
	 * @return the isNullAble
	 */
	public boolean getIsNullAble() {
		return isNullAble;
	}
	/**
	 * @param isNullAble the isNullAble to set
	 */
	public void setIsNullAble(boolean isNullAble) {
		this.isNullAble = isNullAble;
	}
	/**
	 * @return the javaType
	 */
	public String getJavaType() {
		return javaType;
	}
	/**
	 * @param javaType the javaType to set
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	/**
	 * @return the javaTypeDetail
	 */
	public String getJavaTypeDetail() {
		return javaTypeDetail;
	}
	/**
	 * @param javaTypeDetail the javaTypeDetail to set
	 */
	public void setJavaTypeDetail(String javaTypeDetail) {
		this.javaTypeDetail = javaTypeDetail;
	}
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the isPrimaryKey
	 */
	public boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}
	/**
	 * @param isPrimaryKey the isPrimaryKey to set
	 */
	public void setIsPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	/**
	 * @return the isImportedKey
	 */
	public boolean getIsImportedKey() {
		return isImportedKey;
	}
	/**
	 * @param isImportedKey the isImportedKey to set
	 */
	public void setIsImportedKey(boolean isImportedKey) {
		this.isImportedKey = isImportedKey;
	}
	
	/**
	 * 是否列表所需的字段（列表一般指list查询、分页查询，如果是超长的varchar型字段、text、clob、blob等类型的字段一般不需要在这种场景下列出的）
	 * @return
	 */
	public boolean getIsNeededColumnOfList() {
		boolean res = true;
		
		String type = this.columnType == null ? "" : this.columnType.trim().toUpperCase();
		
		if(type.length() == 0) { //字段类型未知，不用于列表
			res = false;
		} else if (COLUMN_TYPES_OF_STRING.contains(type) && this.columnLength > 200 ) { //较长的字符型字段，不用于列表
			res = false;
		} else if(COLUMN_TYPES_OF_CLOB.contains(type)) { //Clob型字段，不用于列表
			res = false;
		} else if(COLUMN_TYPES_OF_BLOB.contains(type)) { //Blob型字段，不用于列表
			res = false;
		} else if(COLUMN_TYPES_OF_OBJECT.contains(type)) { //对象行字段，不用于列表
			res = false;
		}
		
		return res;
	}
	
	/**
	 * 本字段是否是时间类型
	 * @return
	 */
	public boolean getIsDateOrTime() {
		boolean res = false;
		if(this.javaType != null) {
			res = this.javaType.equalsIgnoreCase("Date") || this.javaType.equalsIgnoreCase("Time") 
					|| this.javaType.equalsIgnoreCase("java.util.Date") || this.javaType.equalsIgnoreCase("java.sql.Date") ||
					this.javaType.equalsIgnoreCase("java.sql.Time") || this.javaType.equalsIgnoreCase("java.sql.Timestamp");
			
		}
		return res;
	}
}
