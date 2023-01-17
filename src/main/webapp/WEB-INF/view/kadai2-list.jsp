<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.KanninEntity" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz一覧</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>氏名</th>
			<th>年齢</th>
			<th>性別</th>
			<th>電話</th>
			<th>メールアドレス</th>
		</tr>
	<%
	List<KanninEntity> list = (ArrayList<KanninEntity>)request.getAttribute("list");
	for(KanninEntity s : list) {
	%>
		<tr>
			<td><%=s.getName() %></td>
			<td><%=s.getAge() %></td>
			<td><%=s.getGender() %></td>
			<td><%=s.getTel() %></td>
			<td><%=s.getMail() %></td>
		</tr>
	<%} %>
	</table>
	
	<a href="./">戻る</a>
</body>
</html>