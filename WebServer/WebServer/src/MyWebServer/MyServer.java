package MyWebServer;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer extends Thread{
	int port = 8888;	//默认端口
	//初始化端口
	public MyServer(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
	}
	
	public MyServer() {
		super();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			server();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void server() throws Exception {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(this.port);
			System.out.println("正在监听");
			while(Status.listen) {
			Socket socket = serverSocket.accept();
			System.out.println("有设备连接");
//			new Thread(new MyDispatcher(socket)).start();
			MyRequest request = new MyRequest(socket.getInputStream());
			MyResponse response = new MyResponse(socket.getOutputStream());
			
			dispatch(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
    private void dispatch(MyRequest request,MyResponse response) throws Exception {
    	//解析各种操作
    	//jsp操作
    	File f= new File(Status.path+request.getUrl());
    	if(request.getUrl()!=null&&request.getUrl().indexOf("jsp")!=-1) {
    		System.out.println("-----------------jsp-------------------");
    		RequestDispatcher test = new RequestDispatcher(request.getUrl());
    		test.forward(request, response);
    		return;
    	}
    	//filter操作（有时间再写）
    	
    	//请求静态资源操作
    	else if(request.getUrl()!=null) {
	    	if(request.getUrl().indexOf(".")!=-1||request.getUrl().length()==0||f.isDirectory()) {
	    		response.run(request.getUrl());
	    		return;
	    	}
	    	//然后是servlet操作
	    	//获得相应的servlet类
	    	WebApp w = new WebApp();
	    	MyServlet servlet = (MyServlet)w.getServletFromUrl(request.getUrl());
	    	System.out.println(servlet);
	    	servlet.service(request, response);
    	} 
    }
	public static void main(String[] args) throws Exception {
		MyServer ms=null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("请输入端口号 如果不输入 默认缺省端口号为8888");
		String port = "";
		port = input.nextLine();
		if(!port.equals("")) {
			ms = new MyServer(Integer.parseInt(port));
		}
		else {
			ms = new MyServer();
		}
		ms.start();
	}
}
