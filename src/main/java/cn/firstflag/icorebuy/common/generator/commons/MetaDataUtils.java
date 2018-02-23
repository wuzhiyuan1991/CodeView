package cn.firstflag.icorebuy.common.generator.commons;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库元数据处理类
 * @author yong
 *
 */
public class MetaDataUtils {
	
	/**
	 * 从类型集合Map中得到指定的java类型。
	 * @param dataTypeMap
	 * @param dataTypeStr
	 * @return
	 */
	public static String getJavaType(Map<String,String> dataTypeMap,String dataTypeStr){
		
		String javaType = null;
		
		if(null != dataTypeMap && !dataTypeMap.isEmpty()
				&&  null !=dataTypeStr && !"".equals(dataTypeStr.trim())){
			javaType = dataTypeMap.get(dataTypeStr.toUpperCase());
		}
		
		if(null == javaType || "".equals(javaType.trim())){
			javaType = dataTypeStr;
		}
		
		return javaType;
	}
	
	/**
	 * 将java类型包路径移除,保留类型类名。
	 * @param javaType
	 * @return
	 */
	public static String getSimpleType(String javaType){
		
		if(null != javaType && !"".equals(javaType.trim())){
			return javaType.substring(1+javaType.trim().lastIndexOf("."), javaType.length());
		}
		return javaType;
	}
	
	/**
	 * 将元数据字串转换成java数据名
	 * @param metaData
	 * @param igorefix
	 * @param dataSymb
	 * @param letterReplace
	 * @return
	 */
	public static String getJavaName(String metaData,String igorefix,String dataSymb,String letterReplace){
		
		if(StringUtils.isEmpty(metaData)){
			return metaData;
		}
		
		metaData = metaData.trim();
		if(StringUtils.isNotEmpty(igorefix)){
			String[] split = igorefix.split(",");
			StringBuffer igorefixsb = new StringBuffer("");
			for(int i=0;i<split.length;i++){
				if(StringUtils.isNotEmpty(split[i])){
					igorefixsb.append(split[i].trim()+"|");
				}
			}
			igorefix = igorefixsb.toString();
			if(igorefix.endsWith("|")){
				igorefix = igorefix.replaceAll("\\|$", "");
			}
			Pattern pattern = Pattern.compile("^("+igorefix+")",Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(metaData);
			while(matcher.find()){
				String group = matcher.group();
				if(StringUtils.isNotEmpty(group)){
					metaData = metaData.substring(group.length(),metaData.length());
					break;
				}
			}
		}
		
		if(StringUtils.isNotEmpty(dataSymb)){
			
			dataSymb = dataSymb.replace("\\", "\\\\");
			dataSymb = dataSymb.replace("^", "\\^");
			dataSymb = dataSymb.replace("-", "\\-");
			dataSymb = dataSymb.replace("[", "\\[");
			dataSymb = dataSymb.replace("]", "\\]");
			
			String[] split = metaData.split("["+dataSymb+"]");
			if(null != split && split.length>0){
				StringBuffer splitBuffer = new StringBuffer();
				for(int i=0;i<split.length;i++){
					if(StringUtils.isNotEmpty(split[i])){
						splitBuffer.append(StringUtils.firstUpperStr(split[i].toLowerCase()));
					}
				}
				metaData = splitBuffer.toString();
			}
		}
		
		if(StringUtils.isNotEmpty(letterReplace)){
			metaData = metaData.replaceAll("[^0-9a-zA-Z_]", letterReplace);
			if(dataSymb.matches("^0-9")){
				metaData = letterReplace+metaData;
			}
		}
		
		return metaData;
	}
	
	/**
	 * 替换数据中的变量值
	 * @param targetData
	 * @param variables
	 * @return
	 */
	public static String replaceVariable(String targetData ,Map<String,String> variables){
		if(StringUtils.isEmpty(targetData)){
			return targetData;
		}
		Pattern pattern = Pattern.compile("(\\{.*?\\})");
		Matcher matcher = pattern.matcher(targetData);
		while(matcher.find()){
			String group = matcher.group();
			String groupValue = group.replaceAll("(\\{|\\})", "");
			String value = variables.get(groupValue);
			if(StringUtils.isNotEmpty(value)){
				targetData = targetData.replace(group, value);
			}
		}
		
		return targetData;
	}
	
	/**
	 * 替换数据中的星号值
	 * @param targetData
	 * @param asteriskValue
	 * @return
	 */
	public static String replaceAsterisk(String targetData ,String asteriskValue){
		if(StringUtils.isEmpty(targetData)){
			return targetData;
		}
		targetData = targetData.replaceAll("\\{\\*\\}",null == asteriskValue ? "" :asteriskValue.trim());
		targetData = targetData.replaceAll("\\.{2,}",".");
		return targetData;
	}
	
	/**
	 * 将模块设置到java包字符串中
	 * @param targetData
	 * @param moduleValue
	 * @return
	 */
	public static String replaceModule(String targetData ,String moduleValue){
		if(StringUtils.isEmpty(targetData)){
			return targetData;
		}
		targetData = targetData.replaceAll("\\{\\#\\}",null == moduleValue ? "" : moduleValue.trim());
		targetData = targetData.replaceAll("\\.{2,}",".");
		return targetData;
	}
	
	
	/**
	 * 自定义字符串函数处理
	 * @param dataStr
	 * @return
	 */
	public static String StringFuncHandle(String dataStr){
		if(StringUtils.isEmpty(dataStr)){
			return dataStr;
		}

		Pattern pattern = Pattern.compile("firstUpper\\(.*?\\)");
		Matcher matcher = pattern.matcher(dataStr);
		//验证是否邮箱
		while(matcher.find()){
		  //得到@符号前的邮箱名  out: test
		  String group = matcher.group();
		  String groupValue = group.substring(11, group.length()-1);
		  dataStr = dataStr.replace(group, StringUtils.firstUpperStr(groupValue));
		}
		
		matcher.reset();
		pattern = Pattern.compile("firstLower\\(.*?\\)");
		matcher = pattern.matcher(dataStr);
		//验证是否邮箱
		while(matcher.find()){
		  //得到@符号前的邮箱名  out: test
		  String group = matcher.group();
		  String groupValue = group.substring(11, group.length()-1);
		  dataStr = dataStr.replace(group, StringUtils.firstLowerStr(groupValue));
		}
		
		matcher.reset();
		pattern = Pattern.compile("allUpper\\(.*?\\)");
		matcher = pattern.matcher(dataStr);
		//验证是否邮箱
		while(matcher.find()){
		  //得到@符号前的邮箱名  out: test
		  String group = matcher.group();
		  String groupValue = group.substring(11, group.length()-1);
		  dataStr = dataStr.replace(group, groupValue.toUpperCase());
		}
		
		matcher.reset();
		pattern = Pattern.compile("allLower\\(.*?\\)");
		matcher = pattern.matcher(dataStr);
		//验证是否邮箱
		while(matcher.find()){
		  //得到@符号前的邮箱名  out: test
		  String group = matcher.group();
		  String groupValue = group.substring(11, group.length()-1);
		  dataStr = dataStr.replace(group, groupValue.toLowerCase());
		}
		return dataStr;
	}
	
	
	public static void main(String[] args){
		
//		  String emailPattern = "firstUpper\\(.*?\\)";
//		  String str = "com.yulong.firstUpper(me).model.{module}.(firstUpper({*}).java)";
//		 
//		  str = replaceAsterisk(str, "a32sd");
//		  Pattern pattern = Pattern.compile(emailPattern);
//		  Matcher matcher = pattern.matcher(str);
//		  
//		  //验证是否邮箱
//		  while(matcher.find()){
//		    //得到@符号前的邮箱名  out: test
//		    String group = matcher.group();
//		    String groupValue = group.substring(11, group.length()-1);
//		    str = str.replace(group, StringUtils.firstUpperStr(groupValue));
//		  }
		 String aa ="com.yulong.{project}.mapper....{#}.[firstUpper({*})Mapper.java]";
		 System.out.println(replaceModule(aa,null));
		    
		
		
//		String ss="org.aa{32b}.{tdt}.cc.({*}.java)";
//		Map<String,String> variables = new HashMap<String,String>();
//		variables.put("32b", "{*}");
//		variables.put("tdt", "RDE");
//		String javaName = replaceVariable(ss,variables);
//		javaName = replaceAsterisk(javaName,"fads");
//		System.out.println(javaName);
//		System.out.println(ss.replaceAll("(\\{.*?\\})", "12345"));
	}
}
