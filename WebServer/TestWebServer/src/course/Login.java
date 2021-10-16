package course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        System.out.println("login!!");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("login work!!");
		String userName="";
		String password="";
		userName = req.getParameter("username");
		password=req.getParameter("password");
		System.out.println("userName:"+userName+"\t password:"+password);
		if("admin".equals(userName) && "admin".equals(password)) {
			  req.getSession().setAttribute("username","admin");
			  System.out.println("======"+String.valueOf(req.getSession().getAttribute("username")));
		}
		else {
			 req.getSession().setAttribute("username","Unknown User");
			  System.out.println("======"+String.valueOf(req.getSession().getAttribute("username")));
		}
		
		rep.sendRedirect("test6.jsp");
	//	doGet(request, response);
	}
}
