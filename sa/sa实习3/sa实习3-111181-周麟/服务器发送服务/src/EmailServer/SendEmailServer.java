package EmailServer;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SendEmailServer{

	@WebMethod
	public boolean sendSimpleEmail(String tag,String email,String msg);
	 
	@WebMethod
	public boolean checkEmail(String email);
	
	@WebMethod
	public boolean sendMoreEmail(String tag,String[] email,String msg);
	
	@WebMethod
	public String[] parseString(String email);
	
	@WebMethod
	public boolean judge(String tag,String email,String msg);

}