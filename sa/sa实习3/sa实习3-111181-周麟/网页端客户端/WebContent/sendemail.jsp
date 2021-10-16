<%@page import="EmailServer.SendEmailServerImpl"%>
<%@page import="EmailServer.SendEmailServerImplProxy"%>
<%@page import="org.apache.axis.components.script.Script"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发送邮件</title>
</head>
<body>
<%
String email = request.getParameter("email");
String topic = request.getParameter("topic");
String msg = request.getParameter("msg");

email = new String(email.getBytes("ISO-8859-1"), "utf-8");
topic = new String(topic.getBytes("ISO-8859-1"), "utf-8");
msg = new String(msg.getBytes("ISO-8859-1"), "utf-8");

if(email == ""){
	out.print("<script>alert('邮箱为空，发送失败');history.go(-1);</script>");	
}
if(topic == ""){
	out.print("<script>alert('内容为空，发送失败');history.go(-1);</script>");	
}
if(msg == ""){
	out.print("<script>alert('正文为空，发送失败');history.go(-1);</script>");	
}
SendEmailServerImplProxy sendEmailServerImplProxy = new SendEmailServerImplProxy();
SendEmailServerImpl sendEmailServerImpl = sendEmailServerImplProxy.getSendEmailServerImpl();

if(sendEmailServerImpl.judge(topic, email, msg)){
	out.print("<script>alert('发送成功');location='index.jsp';</script>");	
}else{
	out.print("<script>alert('发送失败');history.go(-1);</script>");
}
%>
</body>
</html>