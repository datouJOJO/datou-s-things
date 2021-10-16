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
        request.setFromAlias("˧ͷ");
        request.setAddressType(1);
        request.setTagName("hi");
        request.setReplyToAddress(true);
        request.setToAddress(s1);
        //���Ը�����ռ��˷����ʼ����ռ���֮���ö��ŷֿ����������Ž���ʹ��BatchSendMailRequest��ʽ
        //request.setToAddress("����1,����2");
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
        request.setFromAlias("˧ͷ");
        request.setAddressType(1);
        request.setTagName("hi");
        request.setReplyToAddress(true);
        //���Ը�����ռ��˷����ʼ����ռ���֮���ö��ŷֿ����������Ž���ʹ��BatchSendMailRequest��ʽ
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
    	sendEmail("2226556614@qq.com","����","����");
    }
}
