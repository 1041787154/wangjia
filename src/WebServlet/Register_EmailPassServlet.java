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



import db.DBUtil;
import util.info_user;


@WebServlet(description = "注册使用的Servlet", urlPatterns = { "/Register_EmailPassServlet" })  
public class Register_EmailPassServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    /** 
     * Default constructor. 
     */  
    public Register_EmailPassServlet() {  
         
        super();
    }  
  
  /*  @Override  
    protected void service(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        String method = request.getMethod();  
        if ("GET".equals(method)) {  
            log("请求方法：GET");  
            doGet(request, response);  
        } else if ("POST".equals(method)) {  
            log("请求方法：POST");  
            doPost(request, response);  
        } else {  
            log("请求方法分辨失败！");  
        }  
    }  
  */
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */  
    @SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        String code = "";  
        String message = "";  
        
        request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
  
        String email = request.getParameter("email");  
        String password = request.getParameter("password");  
        log(email + ";" + password);  
        info_user in ;
        Connection connect = DBUtil.getConnect();  
        try {  
        	in = new info_user();
            Statement statement =  connect.createStatement();  
            String sqlIns_EP = "update " +DBUtil.TABLE_USER+ " set password='" + password +"',"+"email='"+email+ "' where user_id='" + in.getUser_id() + "'";
            log(sqlIns_EP);  
            int result = statement.executeUpdate(sqlIns_EP);
            if (result > 0 ) { // 密码和邮箱插入成功
            	in = new info_user();
            	in.setPassword(password);
            	in.setEmail(email);
                code = "100";  
                message = "密码和邮箱插入成功 ";
                System.out.println(message);
                response.getWriter().append("true");
            } else { 
                    code = "200";  
                    message = "插入失败"; 
                    System.out.println(message);
                    response.getWriter().append("false");
              
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
  
        
        
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	doGet(request,response);
  
    }  
  
    @Override  
    public void destroy() {  
        log("Register_EmailPassServlet destory.");  
        super.destroy();  
    }  
  
}  