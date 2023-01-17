<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	if(request.getParameter("error") != null){
	%>
		<p style="color:red">ログイン失敗</p>
		<form action="Kadai4loginServlet" method="post">
		メール:<input type="email" name="mail"><br>
		PW:<input type="password" name="pw" ><br>
			<input type="submit" name="ログイン"><br>
		</form>
		<%
	} else {
		%>
		<form action="Kadai4loginServlet" method="post">
		メール:<input type="email" name="mail"><br>
		PW:<input type="password" name="pw" ><br>
			<input type="submit" name="ログイン"><br>
		</form>
		<%
	}
		%>
</body>
</html>