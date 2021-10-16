package emaildemo;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class sendemail {
    public static boolean sendEmail(String s1,String s2,String s3) {    
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
  
    public static boolean sendEmailBatch(String s1,String s2,String s3) {    
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
    
    public static boolean validateEmailAddress(String str) {
    	String pat="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
    	Pattern p=Pattern.compile(pat);
    	Matcher m=p.matcher(str);
    	if(m.matches()){
    	return true;
    	}else{
    	return false;
    	}

    }
    
	public static void main(String[] atrs) {
    	sendEmail("2226556614@qq.com","主题","正文");
    }
}
