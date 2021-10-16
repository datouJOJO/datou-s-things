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

	@Override
	public boolean sendEmailBatch(String s1, String s2, String s3) {
		// TODO Auto-generated method stub
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
