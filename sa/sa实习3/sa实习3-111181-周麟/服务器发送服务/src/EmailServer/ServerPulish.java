package EmailServer;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;


@WebService
public class ServerPulish {
	
	public static void main(String[] args) {
		//发布服务 让客户端调用
		String address = "http://127.0.0.1:233/EmailServer";
		Endpoint.publish(address , new SendEmailServerImpl());
        System.out.println("发布服务成功!");
	}
}
