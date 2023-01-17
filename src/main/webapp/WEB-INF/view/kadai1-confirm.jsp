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
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
		Kannin kannin = (Kannin)session.getAttribute("input_data");
	%>
	名前：<%=kannin.getName() %><br>
	年齢:<%=kannin.getAge()  %><br>
	性別:<%=kannin.getGender() %><br>
	電話番号:<%=kannin.getTel() %>
	メール：<%=kannin.getMail() %><br>
	パスワード：********<br>
	<a href="Kadai1Execute">OK</a><br>
	<a href="Kadai1Form">戻る</a>
</body>
</html>