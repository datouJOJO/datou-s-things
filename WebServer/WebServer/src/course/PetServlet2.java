package course;

//   The Servlet library is needed to compile this code.
//   That is NOT included in the JDK download.

//   For servlets, you need to download J2EE (currently 1.4)
//   from http://java.sun.com/j2ee/1.4/download.html#sdk
//      or from http://jakarta.apache.org

// compile with javac -Djava.ext.dirs=%TOMCAT_HOME%\common\lib PetServlet.java 
// or make sure the servlet-api.jar is in the CLASSPATH

import java.io.*; 
import java.text.*; 
import java.util.*;

import MyWebServer.MyRequest;
import MyWebServer.MyResponse;
import MyWebServer.MyServlet;
import MyWebServer.RequestDispatcher; 

public class PetServlet2 extends MyServlet { 

    private String recommendedPet(int weight, int legs) { 
        if (legs ==0) return "a goldfish"; 
        if (legs ==4) { 
           if (weight<20) return "a cat"; 
           if (weight<100) return "a dog"; 
        } 
        return "a house plant"; 
    } 

    public void doPost(MyRequest req,MyResponse resp ){ 

        // get the input field values 
        int petWeight = 0, petLegs = 0; 
        try { 
          petWeight = Integer.parseInt(req.getParameter("weight")); 
          petLegs = Integer.parseInt(req.getParameter("legs")); 
        } catch (NumberFormatException | IOException nfe) { 
          petWeight=petLegs=-1; // indicates that we got an invalid number
        } 

        String pet = recommendedPet(petWeight, petLegs); 
        req.setAttribute("pet",pet);
    	RequestDispatcher requestDispatcher = req.getRequestDispatcher("show3.jsp");
		try {
			requestDispatcher.forward(req, resp);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	protected void doGet(MyRequest req, MyResponse rep) {
		// TODO Auto-generated method stub
		
	} 
} 
