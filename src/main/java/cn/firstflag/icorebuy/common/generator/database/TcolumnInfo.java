package cn.firstflag.icorebuy.common.generator.database;

/**
 * 列元素信息
 * @author yong
 *
 */
public class TcolumnInfo {
	
	private String columnName;      //列表
	private String columnType;      //列类型
	private Integer columnLength;   //列长度
	private Integer decimalDigits;  //小数位数
	private boolean isNullAble;     //是否可以为空
	private String defaultValue;    //默认值
	private String remark;          //列备注信息
	
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
	 * @return the decimalDigits
	 */
	public Integer getDecimalDigits() {
		return decimalDigits;
	}
	/**
	 * @param decimalDigits the decimalDigits to set
	 */
	public void setDecimalDigits(Integer decimalDigits) {
		this.decimalDigits = decimalDigits;
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
}
