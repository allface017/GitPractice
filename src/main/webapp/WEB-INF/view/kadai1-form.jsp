<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dto.Kannin" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			Kannin kn = (Kannin)session.getAttribute("input_data"); 
		

	%>
			<p style="color:red">登録に失敗しました。</p>
					<h3>新規会員登録</h3>
	<form action="KadaiComnfirm" method="post">
	名前:<input type="text" name="name"><br>
	年齢:<input type="number" name="age"><br>
	性別:<br>男:<input type="radio" name="gender" value="男"><br>
	女:<input type="radio" name="gender" value="女"><br>
	電話番号:<input type="tel" name="tel"><br>
	メールアドレス:<input type="email" name="mail"><br>
	パスワード:<input type="password" name="password"><br>
	<input type="submit" name="送信">	
	</form>
	<%
	} else {	
	%>
						<h3>新規会員登録</h3>
		<form action="KadaiComnfirm" method="post">
	名前:<input type="text" name="name"><br>
	年齢:<input type="number" name="age"><br>
	性別:<br>男:<input type="radio" name="gender" value="男"><br>
	女:<input type="radio" name="gender" value="女"><br>
	電話番号:<input type="tel" name="tel"><br>
	メールアドレス:<input type="email" name="mail"><br>
	パスワード:<input type="password" name="password"><br>
	<input type="submit" name="送信">	
</form>
	<% 
	}
	%>

</body>
</html>