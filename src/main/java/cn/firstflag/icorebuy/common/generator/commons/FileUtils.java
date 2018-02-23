package cn.firstflag.icorebuy.common.generator.commons;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件处理工具类
 * @author yong
 *
 */
public class FileUtils {
	
	/**
	 * 检查多级目录
	 * @param path
	 * @return
	 */
	public static boolean checkDirectory(String path){
		File f = new File(path);
		if(!f.exists()){
			return f.mkdirs();
		}else{
			return true;
		}
	}
	
	/**
	 * 根据包结构得到文件目录
	 * @param javaPackage
	 * @return
	 */
	public static String getDirectByPackage(String javaPackage){
		
		String resultStr = getPackage(javaPackage);
		
		if(StringUtils.isNotEmpty(resultStr)){
			resultStr = resultStr.replace(".", File.separator);
		}
		
		return resultStr;
	}
	
	/**
	 * 得到包结构
	 * @param javaPackage
	 * @return
	 */
	public static String getPackage(String javaPackage){
		if(StringUtils.isEmpty(javaPackage)){
			return "";
		}
		
		Pattern pattern = Pattern.compile("^.*(?=\\[.*?\\]$)");
		Matcher matcher = pattern.matcher(javaPackage.trim());
		String resultStr = "";
		if(matcher.find()){
			resultStr = matcher.group().trim();
			if(resultStr.endsWith(".")){
				resultStr = resultStr.substring(0, resultStr.length()-1);
			}
		}
		return resultStr;
	}
	
	/**
	 * 根据包结构得到文件名
	 * @param javaPackage
	 * @return
	 */
	public static String getFileNameByPackage(String javaPackage){
		if(StringUtils.isEmpty(javaPackage)){
			return "";
		}
		
		Pattern pattern = Pattern.compile("\\[.*?\\]$");
		Matcher matcher = pattern.matcher(javaPackage.trim());
		String resultStr = "";
		if(matcher.find()){
			resultStr = matcher.group().replaceAll("(\\[|\\])", "");
		}
		return resultStr;
	}
	
	/**
	 * 得到完整Java包结构
	 * 只能在变量及星号全部替换后用
	 * @param javaPackage
	 * @return
	 */
	public static String getFullPackage(String javaPackage){
		if(StringUtils.isEmpty(javaPackage)){
			return "";
		}
		String javaPackageStr= javaPackage.replaceAll("(\\(|\\)|\\{|\\}|\\[|\\])", "");
		if(javaPackageStr.toLowerCase().endsWith(".java")){
			return javaPackageStr.substring(0, javaPackageStr.length()-5);
		}
		return javaPackageStr;
	}
	/**
	 * 删除Java文件后缀
	 * @param javaFile
	 * @return
	 */
	public static String removeJavaPostFix(String javaFile){
		if(StringUtils.isEmpty(javaFile)){
			return "";
		}
		if(javaFile.toLowerCase().endsWith(".java")){
			return javaFile.substring(0, javaFile.length()-5);
		}
		return javaFile;
	}
	
	/**
	 * 根据配置文件的路径得到真实路径
	 * @param cfgPath
	 * @return
	 */
	public static String getCfgDirectory(String cfgPath){
		
		if(StringUtils.isEmpty(cfgPath)){
			return FileUtils.class.getResource("/").getPath();
		}
		
		cfgPath = cfgPath.trim();
		if (!cfgPath.toLowerCase().startsWith("classpath:")){
			return cfgPath;
		}
		
		cfgPath = cfgPath.substring(10).trim();
		if(!cfgPath.startsWith("/")){
			cfgPath = "/"+cfgPath;
		}
		cfgPath = FileUtils.class.getResource("/").getPath() + cfgPath.substring(1);
		
		return cfgPath;
	}
	
	/**
	 * 增加路径分隔符
	 * @param path
	 * @return
	 */
	public static String addPathSeparator(String path,String separator){
		if(StringUtils.isEmpty(path)){
			return "";
		}
		
		if(StringUtils.isEmpty(separator)){
			separator = File.separator;
		}
		
		if(!path.endsWith(separator)){
			path = path + separator;
		}
		return path;
	}
	
	/**
	 * 连接两个文件地址组成新的路径
	 * @param prefixPath
	 * @param postfixPath
	 * @param separator
	 * @return
	 */
	public static String concatPath(String prefixPath,String postfixPath,String separator){
		if(StringUtils.isEmpty(prefixPath)){
			prefixPath = "";
		}
		
		if(StringUtils.isEmpty(postfixPath)){
			postfixPath = "";
		}
		
		if(StringUtils.isEmpty(separator)){
			separator = File.separator;
		}
		
		if(prefixPath.endsWith(separator)){
			prefixPath = prefixPath.substring(0,prefixPath.length()-1);
		}
		
		if(postfixPath.startsWith(separator)){
			postfixPath = postfixPath.substring(1);
		}
		String newPath = prefixPath + separator + postfixPath;
		
		return newPath;
	}
	
	
	
	public static void main(String[] args){
		
		//System.out.println(getDirectByPackage("fadsfads.fdsa.{a32e}.fdsafa.(12{*}sf.java) "));
		
		
		//System.out.println("aaa.vv.ss".replace(".", "\\"));
		
		System.out.println(concatPath("","bb","\\"));
	}
}
