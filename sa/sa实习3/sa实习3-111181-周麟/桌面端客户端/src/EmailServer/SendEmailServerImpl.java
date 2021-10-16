/**
 * SendEmailServerImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package EmailServer;

public interface SendEmailServerImpl extends java.rmi.Remote {
    public boolean sendMoreeEmail(java.lang.String arg0, java.lang.String[] arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public boolean judge(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public boolean sendSimpleEmail(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public java.lang.String[] parseString(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean checkEmail(java.lang.String arg0) throws java.rmi.RemoteException;
}
