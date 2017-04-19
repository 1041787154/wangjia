package Dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.info_user;
import db.DBUtil;


public class infoDao {
	public int user_insert(String name, String password, String email, String qq,
			String weixin,String age,String phoneN,String pict) {
		String sql = "insert into "+DBUtil.TABLE_USER +"(name,password,email,qq,weixin,age,phoneN,pict) values('" + name
				+ "','" + password + "','" + email + "','" + qq + "','" + weixin 
				+ "','" + age + "','" + phoneN + "','" + pict + "')";
		return DBUtil.executeSql(sql);

	}

	// 登录成功后传入后台
	public info_user user_query(String name, String password) throws SQLException {
		
		String sql = "select * from " + DBUtil.TABLE_USER + " where name='" + name  
                + "' and password='" + password + "'";  
		System.out.println(sql);
		Connection oConnection = DBUtil.getConnect();//连接数据库	
		System.out.println("                                        ddddddddddddddddddddddddddddddddddddd");
		info_user in = null;
		if (!oConnection.isClosed()) {
			try{
				in = new info_user();
				PreparedStatement oStatement = oConnection
						.prepareStatement(sql);
				ResultSet oSet = oStatement.executeQuery();
				if (oSet.next()) {	
					in = new info_user();
					in.setUser_id(oSet.getInt("user_id"));
					in.setName(oSet.getString("name"));
					in.setPassword(oSet.getString("password"));
					in.setSex(oSet.getString("sex"));					
					in.setAge(oSet.getString("age"));                
					in.setPhoneN(oSet.getString("phoneN"));
					in.setEmail(oSet.getString("email"));
					in.setWeixin(oSet.getString("weixin"));
					in.setQq(oSet.getString("qq"));                    
                    in.setPict(oSet.getString("pict"));            				
                    
					}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return in;
		
	}
	
	
	

	public int lose_insert(int user_id,String thing, String time, String place, String style,
			String pict) {
		String sql = "insert into "+DBUtil.TABLE_LOSE +"(user_id,thing,time,place,style,pict) values('" + user_id
				+ "','" + thing + "','" + time + "','" + place + "','" + style 
				+ "','" + pict + "')";
		return DBUtil.executeSql(sql);
    
	}
	public int pick_insert(int user_id,String thing, String time, String place, String style,
			String pict) {
		String sql = "insert into "+DBUtil.TABLE_PICK +"(user_id,thing,time,place,style,pict) values('" + user_id
				+ "','" + thing + "','" + time + "','" + place + "','" + style 
				+ "','" + pict + "')";
		return DBUtil.executeSql(sql);
    
	}

	
	
}
