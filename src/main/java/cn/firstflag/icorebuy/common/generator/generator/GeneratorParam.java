package cn.firstflag.icorebuy.common.generator.generator;

import java.util.Map;

/**
 * 生成器参数
 * @author yong
 *
 */
public class GeneratorParam {
	
	private String defaultEncoding;  //默认编码集
	
	private String templatepostfix;  //模板文件后缀名
	
	private boolean overExistFile;    //覆盖已存在的文件
	
	private String ignoreTablePrefix;  //忽略表前缀
	
	private String tableMetaDataSymb;  //表元数据特殊符号
	
	private String notLetterReplace;   //表元数据非字母开头替换字符，数字不替换但在前面加以下字符
	
	private String baseTempDirect;   //基础类模板目录
	
	private String applTempDirect;   //应用类模板目录
	
	private String generatorPath;    //生成代码目录

	private String author;         //作者

	private String excludeTable;  //排除的数据库表
	
	private Map<String,String> includeTable;  //包含的数据库表，key表名，value 模块名(没有模块为空)

	/**
	 * @return the defaultEncoding
	 */
	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	/**
	 * @param defaultEncoding the defaultEncoding to set
	 */
	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	/**
	 * @return the templatepostfix
	 */
	public String getTemplatepostfix() {
		return templatepostfix;
	}

	/**
	 * @param templatepostfix the templatepostfix to set
	 */
	public void setTemplatepostfix(String templatepostfix) {
		this.templatepostfix = templatepostfix;
	}

	/**
	 * @return the overExistFile
	 */
	public boolean isOverExistFile() {
		return overExistFile;
	}

	/**
	 * @param overExistFile the overExistFile to set
	 */
	public void setOverExistFile(boolean overExistFile) {
		this.overExistFile = overExistFile;
	}

	/**
	 * @return the ignoreTablePrefix
	 */
	public String getIgnoreTablePrefix() {
		return ignoreTablePrefix;
	}

	/**
	 * @param ignoreTablePrefix the ignoreTablePrefix to set
	 */
	public void setIgnoreTablePrefix(String ignoreTablePrefix) {
		this.ignoreTablePrefix = ignoreTablePrefix;
	}

	/**
	 * @return the tableMetaDataSymb
	 */
	public String getTableMetaDataSymb() {
		return tableMetaDataSymb;
	}

	/**
	 * @param tableMetaDataSymb the tableMetaDataSymb to set
	 */
	public void setTableMetaDataSymb(String tableMetaDataSymb) {
		this.tableMetaDataSymb = tableMetaDataSymb;
	}

	/**
	 * @return the notLetterReplace
	 */
	public String getNotLetterReplace() {
		return notLetterReplace;
	}

	/**
	 * @param notLetterReplace the notLetterReplace to set
	 */
	public void setNotLetterReplace(String notLetterReplace) {
		this.notLetterReplace = notLetterReplace;
	}

	/**
	 * @return the baseTempDirect
	 */
	public String getBaseTempDirect() {
		return baseTempDirect;
	}

	/**
	 * @param baseTempDirect the baseTempDirect to set
	 */
	public void setBaseTempDirect(String baseTempDirect) {
		this.baseTempDirect = baseTempDirect;
	}

	/**
	 * @return the applTempDirect
	 */
	public String getApplTempDirect() {
		return applTempDirect;
	}

	/**
	 * @param applTempDirect the applTempDirect to set
	 */
	public void setApplTempDirect(String applTempDirect) {
		this.applTempDirect = applTempDirect;
	}

	/**
	 * @return the generatorPath
	 */
	public String getGeneratorPath() {
		return generatorPath;
	}

	/**
	 * @param generatorPath the generatorPath to set
	 */
	public void setGeneratorPath(String generatorPath) {
		this.generatorPath = generatorPath;
	}

	/**
	 * @return the excludeTable
	 */
	public String getExcludeTable() {
		return excludeTable;
	}

	/**
	 * @param excludeTable the excludeTable to set
	 */
	public void setExcludeTable(String excludeTable) {
		this.excludeTable = excludeTable;
	}

	/**
	 * @return the includeTable
	 */
	public Map<String,String> getIncludeTable() {
		return includeTable;
	}

	/**
	 * @param includeTable the includeTable to set
	 */
	public void setIncludeTable(Map<String,String> includeTable) {
		this.includeTable = includeTable;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "GeneratorParam [defaultEncoding=" + defaultEncoding
				+ ", templatepostfix=" + templatepostfix + ", overExistFile="
				+ overExistFile + ", ignoreTablePrefix=" + ignoreTablePrefix
				+ ", tableMetaDataSymb=" + tableMetaDataSymb
				+ ", notLetterReplace=" + notLetterReplace
				+ ", baseTempDirect=" + baseTempDirect + ", applTempDirect="
				+ applTempDirect + ", generatorPath=" + generatorPath
				+ ", excludeTable=" + excludeTable + ", includeTable="
				+ includeTable + "]";
	}

}
