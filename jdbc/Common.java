package com.gr;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class Common {
public static String XHJ_COMMAND="xhj_command";
public static String XHJ_DT="xhj_dt";
public static String NULL="null";
public static String XHJ_TQAUTO="xhj_tqauto";

public static String fwqip="fwqip";
public static String bdip="bdip";
public static String tq_lcid="tq_lcid";
public static String tq_stage="tq_stage";
public static String tq_sort="tq_sort";
public static String delaystart="delaystart";
public static String delaystop="delaystop";

private static String PLAN="planid";
private static String RunFlag="autoid";

public static String STARTVIP="1";
public static String STOPVIP="2";

public static final String STARTVIP_ACTION="STARTVIP";
public static final String STOPVIP_ACTION="STOPVIP";
public static final String STARTRED_ACTION="STARTRED";
public static final String STOPRED_ACTION="STOPRED";


//获取配置文件 
public static Properties getProperties(){
	Properties pro=null;
	try {
		 InputStream file = Properties.class.getClassLoader().getResourceAsStream("config.properties");
//		 InputStream file = new FileInputStream(Server.class.getClassLoader().getResource("config.properties").getFile());
		 pro = new Properties();
		 pro.load(file);
//		 System.out.println(port);
//		 Logger.getLogger("port:"+port);
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());	
		}
     return pro;
	}
}
