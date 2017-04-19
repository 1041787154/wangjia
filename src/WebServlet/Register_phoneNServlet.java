package WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.infoDao;
import db.DBUtil;
import net.sf.json.JSONObject;
import util.info_user;

/**
 * Servlet implementation class Register_phoneNServlet
 */
@WebServlet("/Register_phoneNServlet")
public class Register_phoneNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register_phoneNServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "null", "static-access" })
	String code = "";
    String message = "";
	info_user in ;
	DBUtil db;
	DBUtil db1;
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	    
		infoDao dao = new Dao.infoDao();
		String phoneN = request.getParameter("phoneN");
		String sqlInsert_ph = "insert into " + DBUtil.TABLE_USER + "(phoneN) values('"  
                + phoneN + "')";  
//		String sqlquery_id = "select * from " + DBUtil.TABLE_USER + " where phoneN='" + phoneN + "'";  
//		System.out.println(sqlquery_id);
//		info_user in ;
//		DBUtil db;
//		DBUtil db1;
//		ResultSet result;
		
		db = new DBUtil();
		
		in = new info_user();
		
		if(db.executeSql(sqlInsert_ph) > 0){
			    code = "100";
			    message = "号码插入成功";
			    System.out.println(message);
//				    ResultSet result;
//				    db1 = new DBUtil();
//					result = db1.querySql(sqlquery_id);
//					if(result.next()){
//						
////						while(result.next()){
//						in.setUser_id(result.getInt("user_id"));
//						in.setPhoneN(result.getString("phoneN"));
//						JSONObject object = JSONObject.fromObject(in);
//						out.print(object.toString());						
//						message = "id和号码封装成功";	
//						System.out.println(message);
////						}
//					}else{
//						
//						message = "id和号码封装失败";
//						System.out.println(message);
//						out.print("false");
//					}
//				
			    in = query_id(phoneN);
			    JSONObject object = JSONObject.fromObject(in);
				out.print(object.toString());	
			}else{
				code = "200";
				message = "号码插入失败";
				System.out.println(message);
				out.print("false");
			}

	}
	
	
	@SuppressWarnings("static-access")
	public info_user query_id(String  phoneN){
		String sqlquery_id = "select * from " + DBUtil.TABLE_USER + " where phoneN='" + phoneN + "'";  
		System.out.println(sqlquery_id);
		Connection con=null;
		DBUtil db1 = null;
		Statement st=null;
		ResultSet result = null;
		try{
			in = new info_user();
			con = db1.getConnect();
			st = con.createStatement();
			result =  st.executeQuery(sqlquery_id);
            if(result.next()){
				
//				while(result.next()){
				in.setUser_id(result.getInt("user_id"));
				in.setPhoneN(result.getString("phoneN"));
				System.out.println("id:            "+in.getUser_id()+";"+"phone:               "+in.getPhoneN()+";");
				
				message = "id和号码封装成功";	
				System.out.println(message);
				return in;
									
				
//				}
			}else{
				
				message = "id和号码封装失败";
				System.out.println(message);
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			db1.closeConnectionAndStatement(con,st);
		}
		return in;
			
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
