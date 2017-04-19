package WebServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.infoDao;
import db.DBUtil;

/**
 * Servlet implementation class CheckPhoneNServlet
 */
@WebServlet("/CheckPhoneNServlet")
public class CheckPhoneNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBUtil db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPhoneNServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	    String code = "";
	    String message = "";
		infoDao dao = new Dao.infoDao();
		String phoneN = request.getParameter("phoneN");
		String sqlquery_ph = "select * from " + DBUtil.TABLE_USER + " where phoneN='" + phoneN + "'"; 
		Connection connect = DBUtil.getConnect();  
		try {
			Statement statement =  connect.createStatement();
			if(statement.executeQuery(sqlquery_ph).next()){
				code = "200";
				message = "此号码已注册";
			}else{
				code = "100";
				message = "此号码可以注册";
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*db = new DBUtil();
		try {
			if(db.querySql(sqlquery_ph).next()){
				code = "200";
				message = "此号码已注册";
			}else{
				code = "100";
				message = "此号码可以注册";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		response.getWriter().append(code);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
