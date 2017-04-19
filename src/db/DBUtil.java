package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBUtil {
	//table
	public static final String TABLE_LOSE = "table_lose";
	public static final String TABLE_USER = "table_user";
	public static final String TABLE_PICK = "table_pick";
	public static final String TABLE_TEST = "table_test";
	
	//connect to MySQL database
	public static Connection getConnect(){
		String url = "jdbc:mysql://120.24.220.176:3306/second_mysql_test";
		Connection connecter = null;
		try {  
            Class.forName("com.mysql.jdbc.Driver"); // java反射，固定写法  
            connecter = (Connection) DriverManager.getConnection(url, "root", "581825");  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            System.out.println("SQLException: " + e.getMessage());  
            System.out.println("SQLState: " + e.getSQLState());  
            System.out.println("VendorError: " + e.getErrorCode());  
        }  
        return connecter;  
		
	}
	// 查询；
	public static ResultSet querySql(String sql){
		Connection con=null;
		Statement st=null;
		ResultSet result = null;
		try{
			con = getConnect();
			st = con.createStatement();
			result =  st.executeQuery(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			closeConnectionAndStatement(con,st);
		}
		return result;
	}
	
	public static int executeSql(String sql){
			Connection con=null;
			Statement st=null;
			try{
				con = getConnect();
				st = con.createStatement();
				return st.executeUpdate(sql);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				closeConnectionAndStatement(con,st);
			}
			return 0;
		}
		//关闭数据库链接
		public static void closeConnectionAndStatement(Connection con,Statement st){
					
			try {
			
				if(st!=null)st.close();
				
				if(con!=null)con.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
				
			}
			
		}

}
