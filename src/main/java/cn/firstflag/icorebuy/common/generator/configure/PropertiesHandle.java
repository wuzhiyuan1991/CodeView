package cn.firstflag.icorebuy.common.generator.configure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import cn.firstflag.icorebuy.common.generator.commons.StringUtils;
import cn.firstflag.icorebuy.common.generator.database.JdbcDriverParam;
import cn.firstflag.icorebuy.common.generator.generator.GenerateFilesCfg;
import cn.firstflag.icorebuy.common.generator.generator.GeneratorParam;

/**
 * 配置文件装载处理
 * @author yong
 *
 */
public class PropertiesHandle {
	
	/**
	 * 装载Properties 文件
	 * @param filePath
	 * @return
	 */
	public static Properties loadPropertiesFile(String filePath){
		
		Properties p = null;
		
		InputStream inStream = null;
		try {
			inStream = PropertiesHandle.class.getResourceAsStream(filePath);
			p = new Properties();
			p.load(inStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null !=inStream){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}
	
	/**
	 * 加载jdbc驱动
	 * @return
	 */
	public static JdbcDriverParam getJdbcDriverParam(){
		Properties properties = loadPropertiesFile("/cn/firstflag/icorebuy/common/generator/configure/jdbc.properties");
		
		if(null != properties && !properties.isEmpty()){
			JdbcDriverParam p = new JdbcDriverParam();
			
			p.setDRIVER(properties.getProperty("jdbc.driver", ""));
			p.setURL(properties.getProperty("jdbc.url", ""));
			p.setUSER(properties.getProperty("jdbc.username", ""));
			p.setPASS(properties.getProperty("jdbc.password", ""));
			p.setREMARKFLG(properties.getProperty("jdbc.remarksFlag", "false"));
			return p;
		}
		return null;
	}
	
	
	/**
	 * 加载Freemark设置
	 * @return
	 */
	public static GeneratorParam getGeneratorParam(){
		Properties properties = loadPropertiesFile("/cn/firstflag/icorebuy/common/generator/configure/generator.properties");
		
		if(null != properties && !properties.isEmpty()){
			GeneratorParam p = new GeneratorParam();
			p.setDefaultEncoding(properties.getProperty("genertor.defaultEncoding", "UTF-8"));
			p.setTemplatepostfix(properties.getProperty("genertor.templatePostfix", "ftl"));
			if(properties.getProperty("genertor.overExistFile", "false").toLowerCase().equals("false")){
				p.setOverExistFile(false);
			}else{
				p.setOverExistFile(true);
			}
			p.setIgnoreTablePrefix(properties.getProperty("genertor.ignoreTablePrefix", ""));
			p.setTableMetaDataSymb(properties.getProperty("genertor.tableMetaDataSymb", ""));
			p.setNotLetterReplace(properties.getProperty("genertor.notLetterReplace", ""));
			p.setBaseTempDirect(properties.getProperty("genertor.baseTempDirect", ""));
			p.setApplTempDirect(properties.getProperty("genertor.applTempDirect", ""));
			p.setGeneratorPath(properties.getProperty("genertor.generatorPath", "/"));
			p.setExcludeTable(properties.getProperty("genertor.exclude.table", ""));
			p.setAuthor(properties.getProperty("genertor.author", "firstflag"));
			
			//得到包含的表
			Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();
			Map<String,String>includeTableMap = new HashMap<String,String>();
			while(iterator.hasNext()){
				Entry<Object, Object> next = iterator.next();
				String key = next.getKey().toString();
				if(key.startsWith("genertor.include.table")){
					String keyStr = next.getKey().toString().trim();
					Object value = next.getValue();
					keyStr = keyStr.substring(22,keyStr.length());
					String module = null;
					if(null !=keyStr){
						keyStr = keyStr.replace(".", "").trim();
						module = keyStr.length()>0?keyStr:null;
					}
					
					String tables = "";
					if(null!=value){
						tables = value.toString().trim();
					}
					
					String[] includeTable = tables.split(",");
					if(null != includeTable){
						for(int i=0;i<includeTable.length;i++){
							if(StringUtils.isEmpty(includeTable[i].trim())){
								continue;
							}
							
							if(module != null){
								includeTableMap.put(includeTable[i].trim(), module);
							}else{
								includeTableMap.put(includeTable[i].trim(),"");
							}
						}
					}
				}
			}
			p.setIncludeTable(includeTableMap);
			
			return p;
		}
		return null;
	}
	
	
	/**
	 * 加载生成文件配置
	 * @return
	 */
	public static GenerateFilesCfg getGenerateFiles(){
		Properties properties = loadPropertiesFile("/cn/firstflag/icorebuy/common/generator/configure/generateFiles.properties");
		
		if(null != properties && !properties.isEmpty()){
			GenerateFilesCfg p = new GenerateFilesCfg();
			
			Map<String,String> baseMap = new HashMap<String,String>();
			Map<String, String> appMap = new HashMap<String,String>();
			Map<String,String> variableMap = new HashMap<String,String>();
			Map<String,String> impMap = new HashMap<String,String>();
			
			Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();
			
			while(iterator.hasNext()){
				Entry<Object, Object> next = iterator.next();
				String key = next.getKey().toString();
				if(key.startsWith("base.")){
					String keyStr = next.getKey().toString().trim();
					keyStr = keyStr.substring(5,keyStr.length());
					Object value = next.getValue();
					baseMap.put(keyStr, null == value?"":value.toString().trim());
				}else if(key.startsWith("app.")){
					String keyStr = next.getKey().toString().trim();
					keyStr = keyStr.substring(4,keyStr.length());
					Object value = next.getValue();
					appMap.put(keyStr, null == value?"":value.toString().trim());					
				}else if(key.startsWith("variable.")){
					String keyStr = next.getKey().toString().trim();
					keyStr = keyStr.substring(9,keyStr.length());
					Object value = next.getValue();
					variableMap.put(keyStr, null == value?"":value.toString().trim());					
				}else if(key.startsWith("import.")){
					String keyStr = next.getKey().toString().trim();
					keyStr = keyStr.substring(7,keyStr.length());
					Object value = next.getValue();
					impMap.put(keyStr,null == value?"":value.toString().trim());					
				}
			}
			
			p.setBaseFiles(baseMap);
			
			p.setAppFiles(appMap);
			
			p.setVariables(variableMap);
			
			p.setImports(impMap);
			
			return p;
		}
		return null;
	}
	
	
	/**
	 * 得到数据库类型配置
	 * @return
	 */
	public static Map<String,String> getDataTypeCfg(){
		Properties properties = loadPropertiesFile("/cn/firstflag/icorebuy/common/generator/configure/dataType2JavaType.properties");
		
		if(null != properties && !properties.isEmpty()){
			Map<String,String> typeMap = new HashMap<String,String>();
			
			Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();
			
			while(iterator.hasNext()){
				Entry<Object, Object> next = iterator.next();
				String key = next.getKey().toString();
				Object value = next.getValue();
				typeMap.put(key.trim(), null == value?"":value.toString().trim());
			}
			
			return typeMap;
		}
		return null;
	}
	
}
