package EmailServer;

public class SendEmailServerImplProxy implements EmailServer.SendEmailServerImpl {
  private String _endpoint = null;
  private EmailServer.SendEmailServerImpl sendEmailServerImpl = null;
  
  public SendEmailServerImplProxy() {
    _initSendEmailServerImplProxy();
  }
  
  public SendEmailServerImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendEmailServerImplProxy();
  }
  
  private void _initSendEmailServerImplProxy() {
    try {
      sendEmailServerImpl = (new EmailServer.SendEmailServerImplServiceLocator()).getSendEmailServerImplPort();
      if (sendEmailServerImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendEmailServerImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendEmailServerImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendEmailServerImpl != null)
      ((javax.xml.rpc.Stub)sendEmailServerImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public EmailServer.SendEmailServerImpl getSendEmailServerImpl() {
    if (sendEmailServerImpl == null)
      _initSendEmailServerImplProxy();
    return sendEmailServerImpl;
  }
  
  public boolean sendMoreeEmail(java.lang.String arg0, java.lang.String[] arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (sendEmailServerImpl == null)
      _initSendEmailServerImplProxy();
    return sendEmailServerImpl.sendMoreeEmail(arg0, arg1, arg2);
  }
  
  public boolean judge(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (sendEmailServerImpl == null)
      _initSendEmailServerImplProxy();
    return sendEmailServerImpl.judge(arg0, arg1, arg2);
  }
  
  public boolean sendSimpleEmail(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (sendEmailServerImpl == null)
      _initSendEmailServerImplProxy();
    return sendEmailServerImpl.sendSimpleEmail(arg0, arg1, arg2);
  }
  
  public java.lang.String[] parseString(java.lang.String arg0) throws java.rmi.RemoteException{
    if (sendEmailServerImpl == null)
      _initSendEmailServerImplProxy();
    return sendEmailServerImpl.parseString(arg0);
  }
  
  public boolean checkEmail(java.lang.String arg0) throws java.rmi.RemoteException{
    if (sendEmailServerImpl == null)
      _initSendEmailServerImplProxy();
    return sendEmailServerImpl.checkEmail(arg0);
  }
  
  
}