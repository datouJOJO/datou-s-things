package MyWebServer;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer extends Thread{
	int port = 8888;	//Ĭ�϶˿�
	//��ʼ���˿�
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
			System.out.println("���ڼ���");
			while(Status.listen) {
			Socket socket = serverSocket.accept();
			System.out.println("���豸����");
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
    	//�������ֲ���
    	//jsp����
    	File f= new File(Status.path+request.getUrl());
    	if(request.getUrl()!=null&&request.getUrl().indexOf("jsp")!=-1) {
    		System.out.println("-----------------jsp-------------------");
    		RequestDispatcher test = new RequestDispatcher(request.getUrl());
    		test.forward(request, response);
    		return;
    	}
    	//filter��������ʱ����д��
    	
    	//����̬��Դ����
    	else if(request.getUrl()!=null) {
	    	if(request.getUrl().indexOf(".")!=-1||request.getUrl().length()==0||f.isDirectory()) {
	    		response.run(request.getUrl());
	    		return;
	    	}
	    	//Ȼ����servlet����
	    	//�����Ӧ��servlet��
	    	WebApp w = new WebApp();
	    	MyServlet servlet = (MyServlet)w.getServletFromUrl(request.getUrl());
	    	System.out.println(servlet);
	    	servlet.service(request, response);
    	} 
    }
	public static void main(String[] args) throws Exception {
		MyServer ms=null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("������˿ں� ��������� Ĭ��ȱʡ�˿ں�Ϊ8888");
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
