/**
 * SendEmailServerImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package EmailServer;

public class SendEmailServerImplServiceLocator extends org.apache.axis.client.Service implements EmailServer.SendEmailServerImplService {

    public SendEmailServerImplServiceLocator() {
    }


    public SendEmailServerImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendEmailServerImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendEmailServerImplPort
    private java.lang.String SendEmailServerImplPort_address = "http://127.0.0.1:233/EmailServer";

    public java.lang.String getSendEmailServerImplPortAddress() {
        return SendEmailServerImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendEmailServerImplPortWSDDServiceName = "SendEmailServerImplPort";

    public java.lang.String getSendEmailServerImplPortWSDDServiceName() {
        return SendEmailServerImplPortWSDDServiceName;
    }

    public void setSendEmailServerImplPortWSDDServiceName(java.lang.String name) {
        SendEmailServerImplPortWSDDServiceName = name;
    }

    public EmailServer.SendEmailServerImpl getSendEmailServerImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendEmailServerImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendEmailServerImplPort(endpoint);
    }

    public EmailServer.SendEmailServerImpl getSendEmailServerImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            EmailServer.SendEmailServerImplPortBindingStub _stub = new EmailServer.SendEmailServerImplPortBindingStub(portAddress, this);
            _stub.setPortName(getSendEmailServerImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendEmailServerImplPortEndpointAddress(java.lang.String address) {
        SendEmailServerImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (EmailServer.SendEmailServerImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                EmailServer.SendEmailServerImplPortBindingStub _stub = new EmailServer.SendEmailServerImplPortBindingStub(new java.net.URL(SendEmailServerImplPort_address), this);
                _stub.setPortName(getSendEmailServerImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SendEmailServerImplPort".equals(inputPortName)) {
            return getSendEmailServerImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://EmailServer/", "SendEmailServerImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://EmailServer/", "SendEmailServerImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendEmailServerImplPort".equals(portName)) {
            setSendEmailServerImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
