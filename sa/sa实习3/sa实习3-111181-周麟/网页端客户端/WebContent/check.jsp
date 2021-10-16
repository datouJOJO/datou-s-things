<%@page import="EmailServer.SendEmailServerImpl"%>
<%@page import="EmailServer.SendEmailServerImplProxy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>验证邮箱</title>
</head>
<body>
<%
String email = request.getParameter("email");
email = new String(email.getBytes("ISO-8859-1"), "utf-8");

SendEmailServerImplProxy sendEmailServerImplProxy = new SendEmailServerImplProxy();
SendEmailServerImpl sendEmailServerImpl = sendEmailServerImplProxy.getSendEmailServerImpl();

if(sendEmailServerImpl.checkEmail(email)){
	out.print("<script>alert('邮箱有效'); location='checkEmail.jsp';</script>");
}else{
	out.print("<script>alert('邮箱无效');  location='checkEmail.jsp';</script>");
}
%>
</body>
</html>