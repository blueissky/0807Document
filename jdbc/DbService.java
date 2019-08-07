package com.gr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqllite.MineTest;

public class DbService {
	private static DbService service;
	public static DbService getInstance() {
		service=new DbService();
		return service;
	}
	// 连接对象
    private Connection conn;
    // 传递sql语句
    private Statement stt;
    // 结果集
    private ResultSet set;
	public  void updateState(String fwqip,String tq_type) {
		// 使用Statement接口的executeUpdate()方法向数据库添加数据
	        try {
	            //获取连接
	            conn = DBHelper.getConnection();
	            if(conn==null) {return;}
	            //定义sql语句
				String sql=" update XT_CK_SB_JTXHJ xhj set xhj.tq_type='"+tq_type+"' where xhj.fwqip='"+fwqip+"'";
	            //获取Statement对象
	            stt = conn.createStatement();
	            //执行sql语句
	            stt.executeUpdate(sql);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            //释放资源
	            try {
	                conn.close();
	            } catch (Exception e2) {
	            	
	            }
	        }
	}
	
	public  void updateDeviceState(String devicestatus,String tq_lcid) {
		// 使用Statement接口的executeUpdate()方法向数据库添加数据
	        try {
	            //获取连接
	            conn = DBHelper.getConnection();
	            if(conn==null) {return;}
	            //定义sql语句
				String sql="update XT_CK_SB_JTXHJ xhj set xhj.devicestatus='"+devicestatus+"' where xhj.tq_lcid='"+tq_lcid+"' ";
				//System.out.println(sql);
	            //获取Statement对象
	            stt = conn.createStatement();
	            //执行sql语句
	            stt.executeUpdate(sql);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            //释放资源
	            try {
	                conn.close();
	            } catch (Exception e2) {
	            	
	            }
	        }
	}

	public  String[] getMqUrl() {
		// 使用Statement接口的executeUpdate()方法向数据库添加数据
		String vheical[]=new String[2];
	        try {
	            //获取连接
	            conn = DBHelper.getConnection();
	            if(conn==null) {return null;}
	            //定义sql语句
				String sql=" select * from XT_CK_ZC_FW_MQ ";
	            //获取Statement对象
	            stt = conn.createStatement();
	            //执行sql语句
	            ResultSet rs = stt.executeQuery(sql);
	            while(rs.next()){
	            	vheical[0]=rs.getString("wwip");
	            	vheical[1]=rs.getString("wwport");
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            //释放资源
	            try {
	                conn.close();
	            } catch (Exception e2) {
	            }
	        }
	        return vheical;
	}
	
	public  String[] get_MRS_VEH_SNAP_2018() {
		// 使用Statement接口的executeUpdate()方法向数据库添加数据
	        try {
	        	MineTest mt=new MineTest();
	            //获取连接
	            conn = DBHelper.getConnection();
	            if(conn==null) {return null;}
	            //定义sql语句
				String sql=" select * from MRS_VEH_SNAP_2018 where rownum<10000000 ";
	            //获取Statement对象
	            stt = conn.createStatement();
	            //执行sql语句
	            ResultSet rs = stt.executeQuery(sql);
	            while(rs.next()){
	            	String vheical[]=new String[26];
	            	vheical[0]=rs.getString("id");
	            	vheical[1]=rs.getString("create_by");
	            	vheical[2]=rs.getString("create_date");
	            	vheical[3]=rs.getString("update_by");
	            	vheical[4]=rs.getString("update_date");
	            	vheical[5]=rs.getString("remarks");
	            	vheical[6]=rs.getString("del_flag");
	            	vheical[7]=rs.getString("clicensetype");
	            	vheical[8]=rs.getString("ccarnumber");
	            	vheical[9]=rs.getString("dcollectiondate");
	            	vheical[10]=rs.getString("caddresscode");
	            	vheical[11]=rs.getString("ccollectionaddress");
	            	vheical[12]=rs.getString("ccollectionagencies");
	            	vheical[13]=rs.getString("cdatasource");
	            	vheical[14]=rs.getString("csnaptype");
	            	vheical[15]=rs.getString("cdevicecode");
	            	vheical[16]=rs.getString("clanenumber");
	            	vheical[17]=rs.getString("nvehiclespeed");
	            	vheical[18]=rs.getString("cpic1path");
	            	vheical[19]=rs.getString("cpic2path");
	            	vheical[20]=rs.getString("cpic3path");
	            	vheical[21]=rs.getString("cdirection");
	            	vheical[22]=rs.getString("clicensepicpath");
	            	vheical[23]=rs.getString("ctargettype");
	            	vheical[24]=rs.getString("cvehcolor");
	            	vheical[25]=rs.getString("cvehlogo");
	            	mt.saveValue(vheical);
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            //释放资源
	            try {
	                conn.close();
	            } catch (Exception e2) {
	            }
	        }
	        return null;
	}
	
	public  List<Map> getTSC(String planid) {
		// 使用Statement接口的executeUpdate()方法向数据库添加数据
	        try {
	            //获取连接
	            conn = DBHelper.getConnection();
	            if(conn==null) {return null;}
	            //定义sql语句
//				String sql=" select * from XT_CK_ZC_FW_MQ ";
				StringBuilder sql=new StringBuilder();
				sql.append(" select x.fwqip,x.bdip,x.tq_lcid,t.tq_stage,t.tq_sort,t.delaystart,t.delaystop ");
				sql.append(" from XT_XHJ_PLAN_TSC t ");
				sql.append(" left join xt_ck_sb_jtxhj x on t.xhjid=x.id ");
				sql.append(" where t.planid="+planid);
				sql.append(" order by tq_sort asc ");
				
	            //获取Statement对象
	            stt = conn.createStatement();
	            //执行sql语句
	            ResultSet rs = stt.executeQuery(sql.toString());
	            List<Map> list=new ArrayList<Map>();
	            Map map=null;
	            while(rs.next()){
	            	map=new HashMap();
	            	map.put(Common.fwqip, rs.getString(Common.fwqip));
	            	map.put(Common.bdip, rs.getString(Common.bdip));
	            	map.put(Common.tq_lcid, rs.getString(Common.tq_lcid));
	            	map.put(Common.tq_stage, rs.getString(Common.tq_stage));
	            	map.put(Common.tq_sort, rs.getString(Common.tq_sort));
	            	map.put(Common.delaystart, rs.getString(Common.delaystart));
	            	map.put(Common.delaystop, rs.getString(Common.delaystop));
	            	list.add(map);
	            }
	            rs.close();
	            return list;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            //释放资源
	            try {
	                conn.close();
	            } catch (Exception e2) {
	            }
	        }
			return null;
	}
}
