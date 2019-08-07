package com.sqllite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SqliteTest {
    public static void main(String[] args) {
		new SqliteTest().testHelper();
	}
    public void testHelper() {
        
            try {
				SqliteHelper h = new SqliteHelper("testHelper.db");
				h.executeUpdate("drop table if exists test;");
				h.executeUpdate("create table test(name varchar(20));");
				for(int i=0;i<100;i++){
					h.executeSql("insert into test values('sqliteHelper test');");
					System.out.println(i);
				}
				List<String> sList = h.executeQuery("select name from test", new RowMapper<String>() {
				    @Override
				    public String mapRow(ResultSet rs, int index)
				            throws SQLException {
				        return rs.getString("name");
				    }
				});
				System.out.println(sList.get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        
    }
}