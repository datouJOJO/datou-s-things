package mailserver;

 import javax.jws.WebMethod;
 import javax.jws.WebService;
 
//ʹ��@WebServiceע���עWebServiceI�ӿ�
 @WebService
 public interface WebServiceI {
 
    //ʹ��@WebMethodע���עWebServiceI�ӿ��еķ���
    @WebMethod
    boolean sendEmail(String s1,String s2,String s3);
    
    @WebMethod
    boolean sendEmailBatch(String s1,String s2,String s3);
    
    @WebMethod
    boolean validateEmailAddress(String str);
}