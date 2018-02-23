package cn.firstflag.icorebuy.common.generator.database;

/**
 * 索引信息
 * @author yong
 *
 */
public class TindexInfo {
	
	private String columnName;  //列表
	private String indexname;   //索引名
	private short keySeq;      //主外键序列号(1表示第一列，2代表第二列)
	
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
	 * @return the indexname
	 */
	public String getIndexname() {
		return indexname;
	}
	/**
	 * @param indexname the indexname to set
	 */
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	/**
	 * @return the keySeq
	 */
	public short getKeySeq() {
		return keySeq;
	}
	/**
	 * @param keySeq the keySeq to set
	 */
	public void setKeySeq(short keySeq) {
		this.keySeq = keySeq;
	}
}
