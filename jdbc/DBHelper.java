package com.gr;

import java.sql.*; //导包
import java.util.Properties;

/** 
 * ClassName: DBHerpel
 * @Description: TODO 数据库辅助类
 * @author 
 */
public class DBHelper {

    private static Connection Conn; // 数据库连接对象
    // 数据库连接地址
//    private static String URL = "jdbc:oracle:thin:@172.24.216.74:1521:MCSJT";
//    private static String URL = "jdbc:oracle:thin:@192.168.10.223:1521:MCSJT";
//    // 数据库的用户名
//    private static String UserName = "MCS_JT0320";
//    // 数据库的密码
//    private static String Password = "MCS_JT";

    /**
     * * @Description: TODO 获取访问数据库的Connection对象
     * @param @return
     * @return Connection 连接数据的对象
     * @author 情绪i
     */
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载驱动
            System.out.println("加载驱动成功!!!");
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        try {
        	//Properties pro = Common.getProperties();
	   		String URL = "jdbc:oracle:thin:@192.168.10.224:1521:itmsjz"; 
	   		String UserName = "itmsjz";
	   		String Password = "itmsjz";
            //通过DriverManager类的getConenction方法指定三个参数,连接数据库
            Conn = DriverManager.getConnection(URL, UserName, Password);
            System.out.println("连接数据库成功!!!");
            //返回连接对象
            return Conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
		getConnection();
	}
}