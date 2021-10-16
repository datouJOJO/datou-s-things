<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>邮件服务</title>

</head>
<body>

	<center>
	<p><img src="main.png" alt = "main_img"></p>
	<form action="sendemail.jsp" method="post">
		<table>
		<tr>
			<th>收件人:</th><th><input type = "text" name = "email"></th>
		</tr>
		<tr>
			<th>主题:</th><th><input type = "text" name = "topic"></th>
		</tr>
		</table>
		<textarea name = "msg" rows="30" cols="40">请输入要发送内容</textarea><br>
		<input type = "submit" value = "发送"><br>
	</form>
		<a href="checkEmail.jsp"><button>邮箱验证</button></a>
	</center>

	</form>

</body>
</html>