package cn.firstflag.icorebuy.common.generator.generator;

import java.util.Map;

/**
 * 生成类文件配置
 * @author yong
 *
 */
public class GenerateFilesCfg {
	
	private Map<String,String> baseFiles; //基础类文件配置
	
	private Map<String,String> appFiles;  //应用类文件配置
	
	private Map<String,String> variables; //文件配置变量
	
	private Map<String,String> imports;  //类文件import配置

	/**
	 * @return the baseFiles
	 */
	public Map<String, String> getBaseFiles() {
		return baseFiles;
	}

	/**
	 * @param baseFiles the baseFiles to set
	 */
	public void setBaseFiles(Map<String, String> baseFiles) {
		this.baseFiles = baseFiles;
	}

	/**
	 * @return the appFiles
	 */
	public Map<String, String> getAppFiles() {
		return appFiles;
	}

	/**
	 * @param appFiles the appFiles to set
	 */
	public void setAppFiles(Map<String, String> appFiles) {
		this.appFiles = appFiles;
	}

	/**
	 * @return the variables
	 */
	public Map<String, String> getVariables() {
		return variables;
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	/**
	 * @return the imports
	 */
	public Map<String, String> getImports() {
		return imports;
	}

	/**
	 * @param imports the imports to set
	 */
	public void setImports(Map<String, String> imports) {
		this.imports = imports;
	}
}
