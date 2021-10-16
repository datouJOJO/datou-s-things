package EmailServer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@WebService
public class SendEmailServerImpl implements SendEmailServer{

    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G7q9rSLbFYJtoZMdxcp", "O0L5OhLoAX50JFwtZGwzcIztDJpQdm");
    IAcsClient client = new DefaultAcsClient(profile);
	
    final static String nickName = "大头弟弟";
    final static String tagName = "hi";
    final static String ipName = "jojo@cool526.datoudidi.xyz";
	@Override
	public boolean sendSimpleEmail(String tag, String email, String msg) {
		// TODO Auto-generated method stub
		if(!checkEmail(email)) return false;
		SingleSendMailRequest request = new SingleSendMailRequest();
	    try {
	        request.setAccountName(ipName);
	        request.setFromAlias(nickName);
	        request.setAddressType(1);
	        request.setTagName(tagName);
	        request.setReplyToAddress(true);
	        //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
	        request.setToAddress(email);
	        request.setSubject(tag);
	        request.setHtmlBody(msg);
	        client.getAcsResponse(request);
	    } catch (ServerException e) {
	    	return false;
	       // e.printStackTrace();
	    }
	    catch (ClientException e) {
	    	return false;
	        //e.printStackTrace();
	    }
		return true;
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		
		//统一验证邮箱格式方法
		Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = emailPattern.matcher(email);
		if(matcher.find()){
			return true;
		}
		return false;
	}

	@Override
	public boolean sendMoreEmail(String tag, String[] email, String msg) {
		// TODO Auto-generated method stub
		SingleSendMailRequest request = new SingleSendMailRequest();
	    try {
	        request.setAccountName(ipName);
	        request.setFromAlias(nickName);
	        request.setAddressType(1);
	        request.setTagName(tagName);
	        request.setReplyToAddress(true);
	        //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
	        for(String e:email) {
		        request.setToAddress(e);
		        request.setSubject(tag);
		        request.setHtmlBody(msg);
		        client.getAcsResponse(request);
	        }
	    } catch (ServerException e) {
	    	return false;
	       // e.printStackTrace();
	    }
	    catch (ClientException e) {
	    	return false;
	        //e.printStackTrace();
	    }
		return true;
	}

	@Override
	public String[] parseString(String email) {
		// TODO Auto-generated method stub
		
		String test = "765327432@qq.com 765327432@qq.com,765327432@qq.com";
		//邮箱正则式
		String parse = "[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+";
		Pattern pattern = Pattern.compile(parse);
		Matcher matcher = pattern.matcher(email);
		
		String[] array = new String[0];
		while(matcher.find()) {
			String[] clone = new String[array.length+1];
			System.arraycopy(array, 0, clone, 0, clone.length-1);
			clone[clone.length-1] = matcher.group();
//			System.out.println(matcher.group());
			array = clone;
		}
		return array;
	}

	@Override
	public boolean judge(String tag, String email, String msg) {
		// TODO Auto-generated method stub
		boolean flag;
		
		if(email.indexOf(" ")==-1&&email.indexOf(",")==-1) {
			flag = sendSimpleEmail(tag, email, msg);
		}else {
			String[]emails = parseString(email);
			flag = sendMoreEmail(tag, emails, msg);
		}
		return flag;
	}
	
	public static void main(String[] args) {
		SendEmailServerImpl sendEmailServerImpl = new SendEmailServerImpl();
		sendEmailServerImpl.parseString("?");
		String test = "765327432@qq.com 765327432@qq.com,765327432@qq.com";
		System.out.println(sendEmailServerImpl.judge("无语了", test, "爆炸无语"));
	}
}
