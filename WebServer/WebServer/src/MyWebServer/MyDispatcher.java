package MyWebServer;

import java.io.IOException;
import java.net.Socket;

public class MyDispatcher implements Runnable{
	private MyRequest req;
	private MyResponse rep;
	public MyDispatcher(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			req = new MyRequest(socket.getInputStream());
			rep = new MyResponse(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			rep.run(req.getUrl());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
