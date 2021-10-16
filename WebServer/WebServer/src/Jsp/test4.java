package Jsp;

import java.io.*;
import MyWebServer.MyRequest;
import MyWebServer.MyResponse;
import MyWebServer.MyServlet;

public class test4 extends MyServlet {

public void doGet(MyRequest request, MyResponse response){
PrintWriter out = new PrintWriter(response.getOut());
out.println("HTTP-1.0 200 OK\r\n");
out.println("");
out.println("<html> ");
out.println("此工作属于提高要�?");
out.println("<body bgcolor=\"white\"> ");
out.println("<h1>The Echo JSP - Testing for Jsp tasks</h1> ");
   java.util.Enumeration eh = request.getHeaderNames(); 
     while (eh.hasMoreElements()) { 
         String h = (String) eh.nextElement(); 
         out.print("<br> header: " + h ); 
         out.println(" value: " + request.getHeader(h)); 
     } 

out.println("");
out.println("</body>");
out.println("</html>");
out.flush();
out.close();
}

protected void doPost(MyRequest request, MyResponse resp){}
}