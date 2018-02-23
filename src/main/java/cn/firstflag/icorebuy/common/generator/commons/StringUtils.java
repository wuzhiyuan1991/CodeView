package cn.firstflag.icorebuy.common.generator.commons;

public class StringUtils {
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String firstUpperStr(String str){
		
		if(isNotEmpty(str)){
			
			String upperCase = str.substring(0,1).toUpperCase();
			
			return upperCase + str.subSequence(1,str.length());
		}
		
		return str;
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String firstLowerStr(String str){
		
		if(isNotEmpty(str)){
			
			String upperCase = str.substring(0,1).toLowerCase();
			
			return upperCase + str.subSequence(1,str.length());
		}
		
		return str;
	}
	
	/**
	 * 判断是否是空字段
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		if(null == str || str.trim().length()<1){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是空字段
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		
		if(null == str || str.trim().length()<1){
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args){
		String str = "1";
		
		System.out.println(firstUpperStr(str));
	}
	
}
