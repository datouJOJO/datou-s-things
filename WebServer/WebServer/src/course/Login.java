package course;

import java.io.IOException;

import MyWebServer.MyRequest;
import MyWebServer.MyResponse;
import MyWebServer.MyServlet;

/**
 * Servlet implementation class Login
 */
public class Login extends MyServlet {
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
	protected void doGet(MyRequest req, MyResponse rep) {
	}

	@Override
	protected void doPost(MyRequest req, MyResponse rep) {
		// TODO Auto-generated method stub
		System.out.println("login work!!");
		String userName="";
		String password="";
		try {
			userName = req.getParameter("username");
			password=req.getParameter("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("userName:"+userName+"\t password:"+password);
		if("admin".equals(userName) && "admin".equals(password)) {
			  req.getSession().setAttribute("username","admin");
			  System.out.println("======"+String.valueOf(req.getSession().getAttribute("username")));
		}
		else {
			 req.getSession().setAttribute("username","Unknown User");
			  System.out.println("======"+String.valueOf(req.getSession().getAttribute("username")));
		}
		rep.sendRedirect("test6.jsp",req,rep);
			
	//	doGet(request, response);
	}
}
