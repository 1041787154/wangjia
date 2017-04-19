package WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.infoDao;
import util.info_user;


import net.sf.json.JSONObject;



/**
 * Servlet implementation class LoginServelt
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
    	
        infoDao dao = new Dao.infoDao();
        String name = request.getParameter("name");  
        String password = request.getParameter("password");
        log(name + ";" + password); 
		try {
			info_user in = dao.user_query(name, password);
			if ((in.getName() == null)&&(in.getPassword() == null)) {
//				(in.getName().equals("")&&in.getPassword().equals(""))||(in.getName().equals(null)&&in.getPassword().equals(null))||in.getUser_id() == 0
				out.print("false");
				
			} else {
				
				JSONObject object = JSONObject.fromObject(in);
				out.print(object.toString());
			}

			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	doPost(request, response);  
    }  
  

}
