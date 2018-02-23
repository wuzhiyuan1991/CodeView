package cn.firstflag.icorebuy.common.generator.database;

/**
 * 数据库驱动参数类
 * @author yong
 *
 */
public class JdbcDriverParam {
	
	//获得驱动  
    private String DRIVER;  
    //获得url  
    private String URL;  
    //获得连接数据库的用户名  
    private String USER;
    //获得连接数据库的密码  
    private String PASS;
    //是否读取表的注释信息
    private String REMARKFLG;
    
	/**
	 * @return the dRIVER
	 */
	public String getDRIVER() {
		return DRIVER;
	}
	/**
	 * @param dRIVER the dRIVER to set
	 */
	public void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}
	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}
	/**
	 * @param uRL the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}
	/**
	 * @return the uSER
	 */
	public String getUSER() {
		return USER;
	}
	/**
	 * @param uSER the uSER to set
	 */
	public void setUSER(String uSER) {
		USER = uSER;
	}
	/**
	 * @return the pASS
	 */
	public String getPASS() {
		return PASS;
	}
	/**
	 * @param pASS the pASS to set
	 */
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	/**
	 * @return the REMARKFLG
	 */
	public String getREMARKFLG() {
		return REMARKFLG;
	}
	/**
	 * @param rEMARKFLG the rEMARKFLG to set
	 */
	public void setREMARKFLG(String rEMARKFLG) {
		REMARKFLG = rEMARKFLG;
	}
}
