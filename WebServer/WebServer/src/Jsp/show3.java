package Jsp;

import java.io.*;
import MyWebServer.MyRequest;
import MyWebServer.MyResponse;
import MyWebServer.MyServlet;

public class show3 extends MyServlet {

public void doPost(MyRequest request, MyResponse response){
PrintWriter out = new PrintWriter(response.getOut());
out.println("HTTP-1.0 200 OK\r\n");
try {
out.println("");
out.println("<html>");
out.println("<head>");
out.println("<title>Testing for Servlet-MVC</title>");
out.println("<body> <h1>Recommended Pet - Testing for Web-MVC</h1> ");
out.println("<p>");
out.println("You want a ");
out.println(request.getParameter("legs"));
out.println("-legged pet weighing ");
out.println(request.getParameter("weight"));
out.println("lbs.");
out.println("</p>");
out.println("<p> We recommend getting <b>");
out.println(request.getAttribute("pet"));
out.println("</b>");
out.println("</p>");
out.println("</body> ");
out.println("");
out.println("</html>");
} catch (IOException e) {
	e.printStackTrace();
}out.flush();
out.close();
}

protected void doGet(MyRequest request, MyResponse resp){}
}