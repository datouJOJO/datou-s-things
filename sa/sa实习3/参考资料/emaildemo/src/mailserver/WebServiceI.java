package mailserver;

 import javax.jws.WebMethod;
 import javax.jws.WebService;
 
//使用@WebService注解标注WebServiceI接口
 @WebService
 public interface WebServiceI {
 
    //使用@WebMethod注解标注WebServiceI接口中的方法
    @WebMethod
    boolean sendEmail(String s1,String s2,String s3);
    
    @WebMethod
    boolean sendEmailBatch(String s1,String s2,String s3);
    
    @WebMethod
    boolean validateEmailAddress(String str);
}