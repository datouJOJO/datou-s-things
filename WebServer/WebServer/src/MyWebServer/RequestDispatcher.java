package MyWebServer;

import java.io.IOException;

import Jsp.JspIfoParse;
public class RequestDispatcher {
	String string;
	public RequestDispatcher(String string) {
		this.string = string;
	}
	public void forward(MyRequest req, MyResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String filePath = Status.path +string;	//.jspµÄÂ·¾¶Ãû
		JspIfoParse jsp = new JspIfoParse(filePath,req.getMethod());
		Class jspServlet = Class.forName(jsp.getClsName());
		MyServlet test = (MyServlet)jspServlet.newInstance();
		System.out.println(test);
		test.service(req, resp);
	}
}
