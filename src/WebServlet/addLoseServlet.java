package WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.infoDao;
import util.info_user;

/**
 * Servlet implementation class addLoseServlet
 */
@WebServlet("/addLoseServlet")
public class addLoseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addLoseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
    	
        infoDao dao = new Dao.infoDao();
        int user_id = util.info_user.user_id;
        String thing = request.getParameter("thing");  
        String time = request.getParameter("time");
        String place = request.getParameter("place");  
        String style = request.getParameter("style");
        String pict = request.getParameter("pict");  
        
        int count = dao.lose_insert(user_id, thing, time, place, style, pict);
		if (count>0) {
			out.print("true");
		}else{
			out.print("false");
		}
		
		out.flush();
		out.close();
    
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
