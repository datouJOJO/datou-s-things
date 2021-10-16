package MyWebServer;

public abstract class MyServlet {
	protected abstract void doPost(MyRequest req,MyResponse rep);
	protected abstract void doGet(MyRequest req,MyResponse rep);
	public void service(MyRequest req,MyResponse rep) throws NoSuchMethodException{
		if(req.getMethod().equals("POST")) {
			doPost(req, rep);
		}else if(req.getMethod().equals("GET")) {
			doGet(req, rep);
		}else {
			throw new NoSuchMethodException("Error!");
		}
	}
}
