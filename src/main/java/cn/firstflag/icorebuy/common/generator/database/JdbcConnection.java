package cn.firstflag.icorebuy.common.generator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cn.firstflag.icorebuy.common.generator.configure.PropertiesHandle;

/**
 * 数据库连接类
 * @author yong
 *
 */
public class JdbcConnection {
	
	/**
	 * 得到数据库连接
	 * @return
	 */
	public static Connection getConnection(boolean hasRemarks) {

		JdbcDriverParam driverParam = PropertiesHandle.getJdbcDriverParam();
		
		if (hasRemarks) { //这个连接会影响元数据查询，所以不做为公共连接
			Connection conns = null;
			try {
				// 加载MySql的驱动类
				Class.forName(driverParam.getDRIVER());

				Properties props = new Properties();
				props.setProperty("user", driverParam.getUSER());
				props.setProperty("password", driverParam.getPASS());
				props.setProperty("remarks", "true"); // 设置可以获取remarks信息
				props.setProperty("useInformationSchema", "true");// 设置可以获取tables remarks信息
				conns = DriverManager.getConnection(driverParam.getURL(), props);
			} catch (ClassNotFoundException e) {
				System.out.println("找不到驱动程序类 ，加载驱动失败！");
				e.printStackTrace();

			} catch (SQLException e) {
				System.out.println("数据库连接异常 ！");
				e.printStackTrace();
			}
			return conns;
		} else{
			Connection conn = null;
			try {
				// 加载MySql的驱动类
				Class.forName(driverParam.getDRIVER());

				Properties props = new Properties();
				props.setProperty("user", driverParam.getUSER());
				props.setProperty("password", driverParam.getPASS());
				props.setProperty("remarks", "false"); // 设置可以获取remarks信息
				props.setProperty("useInformationSchema", "false");// 设置可以获取tables remarks信息
				conn = DriverManager.getConnection(driverParam.getURL(), props);
			} catch (ClassNotFoundException e) {
				System.out.println("找不到驱动程序类 ，加载驱动失败！");
				e.printStackTrace();

			} catch (SQLException e) {
				System.out.println("数据库连接异常 ！");
				e.printStackTrace();
			}
			return conn;
		}
	}

	/**
	 * 关闭数据库连接
	 * @param o
	 */
	public static void close(Object o) {
		if (o == null) {
			return;
		}
		if (o instanceof ResultSet) {
			try {
				((ResultSet) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Statement) {
			try {
				((Statement) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Connection) {
			Connection c = (Connection) o;
			try {
				if (!c.isClosed()) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据库操作连接
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if(null != rs){
			close(rs);
		}
		if(null != stmt){
			close(stmt);
		}
		if(null != conn){
			close(conn);
		}
	}
	
	/**
	 * 关闭数据库操作连接
	 * @param rs
	 * @param conn
	 */
	public static void close(ResultSet rs, Connection conn) {
		if(null != rs){
			close(rs);
		}
		if(null != conn){
			close(conn);
		}
	}
}
