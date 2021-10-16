package mailserver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@WebService
public class WebServiceImpl implements WebServiceI {

	@Override
	public boolean sendEmail(String s1, String s2, String s3) {
		// TODO Auto-generated method stub
	    if(!validateEmailAddress(s1)) return false;
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G7q9rSLbFYJtoZMdxcp", "O0L5OhLoAX50JFwtZGwzcIztDJpQdm");
	    IAcsClient client = new DefaultAcsClient(profile);
	    SingleSendMailRequest request = new SingleSendMailRequest();
	    try {
	        request.setAccountName("jojo@cool526.datoudidi.xyz");
	        request.setFromAlias("帅头");
	        request.setAddressType(1);
	        request.setTagName("hi");
	        request.setReplyToAddress(true);
	        request.setToAddress(s1);
	        //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
	        //request.setToAddress("邮箱1,邮箱2");
	        request.setSubject(s2);
	        request.setHtmlBody(s3);
	        SingleSendMailResponse httpResponse = client.getAcsResponse(request);
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
	public boolean sendEmailBatch(String s1, String s2, String s3) {
		// TODO Auto-generated method stub
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G7q9rSLbFYJtoZMdxcp", "O0L5OhLoAX50JFwtZGwzcIztDJpQdm");
	    IAcsClient client = new DefaultAcsClient(profile);
	    SingleSendMailRequest request = new SingleSendMailRequest();
	    try {
	        request.setAccountName("jojo@cool526.datoudidi.xyz");
	        request.setFromAlias("帅头");
	        request.setAddressType(1);
	        request.setTagName("hi");
	        request.setReplyToAddress(true);
	        //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
	        request.setToAddress(s1);
	        request.setSubject(s2);
	        request.setHtmlBody(s3);
	        SingleSendMailResponse httpResponse = client.getAcsResponse(request);
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
	public boolean validateEmailAddress(String str) {
		// TODO Auto-generated method stub
    	String pat="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
    	Pattern p=Pattern.compile(pat);
    	Matcher m=p.matcher(str);
    	if(m.matches()){
    	return true;
    	}else{
    	return false;
    	}
	}

}
