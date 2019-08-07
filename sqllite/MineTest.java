package com.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class MineTest {
	private static Connection connection;
	public MineTest() {
		// TODO Auto-generated constructor stub
		
		try {
			Class.forName(Drivde);
			connection=DriverManager.getConnection("jdbc:sqlite:C:/Users/Public/Desktop/test.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static String Drivde="org.sqlite.JDBC";
    public static void main(String[] args) {
        // TODO Auto-generated method stub
            try {
                Class.forName(Drivde); 
                Connection connection=DriverManager.getConnection("jdbc:sqlite:C:/Users/Public/Desktop/test.db"); 
                
                Statement statement=connection.createStatement();   
                StringBuilder sb=new StringBuilder();
                sb.append(" create table MRS_VEH_SNAP_2018 ");
                sb.append(" ( ");
                sb.append("id                  varchar(64) not null,");
                sb.append("create_by           varchar(64),");
                sb.append("create_date         varchar(20),");
                sb.append("update_by           varchar(64),");
                sb.append("update_date         varchar(20),");
                sb.append("remarks             varchar(255),");
                sb.append("del_flag            varchar(64),");
                sb.append("clicensetype        varchar(2),");
                sb.append("ccarnumber          varchar(16),");
                sb.append("dcollectiondate     varchar(20),");
                sb.append("caddresscode        varchar(64),");
                sb.append("ccollectionaddress  varchar(200),");
                sb.append("ccollectionagencies varchar(12),");
                sb.append("cdatasource         varchar(2),");
                sb.append("csnaptype           varchar(64),");
                sb.append("cdevicecode         varchar(18),");
                sb.append("clanenumber        varchar(2),");
                sb.append("nvehiclespeed       varchar(3),");
                sb.append("cpic1path           varchar(400),");
                sb.append("cpic2path           varchar(400),");
                sb.append("cpic3path           varchar(400),");
                sb.append("cdirection          varchar(1),");
                sb.append("clicensepicpath     varchar(400),");
                sb.append("ctargettype         varchar(2),");
                sb.append("cvehcolor           varchar(2),");
                sb.append("cvehlogo            varchar(2)");
                sb.append(")");
                
                statement.executeUpdate("drop table if exists MRS_VEH_SNAP_2018");
                statement.executeUpdate(sb.toString());
                connection.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    
    public static void saveValue(String[] vheical){
    	 try {
    		 Statement statement=connection.createStatement();
        	StringBuilder sql=new StringBuilder();
        	sql.append("insert into MRS_VEH_SNAP_2018 values(");
        	for(int i=0;i<26;i++){
        		sql.append("'");
        		sql.append(vheical[i]);
        		sql.append("',");
        	}
        	String sts = sql.toString();
        	sts=sts.substring(0, sts.length()-1)+")";
        	statement.executeUpdate("PRAGMA synchronous = OFF;PRAGMA journal_mode = OFF;");
//        	String ss="insert into MRS_VEH_SNAP_2018 values('30204629','null','null','null','null','null','null','99','车牌','1-01-01 00:14:02.0','203286039100','S328黄张路-S217朱诸路（开元阀门）测速','null','null','1','370281000000035125','2','null','http://10.49.193.183:8088/image/3/3/60d52989c6264d07b38fb29fbd416090/119/20/7c3b/1255743256/160252.jpg','http://10.49.193.183:8088/image/3/3/60d52989c6264d07b38fb29fbd416090/119/20/7c3c/1255903588/152788.jpg','null','1','null','null','11','null')";
        	System.out.println(sts);
        	statement.executeUpdate(sts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
