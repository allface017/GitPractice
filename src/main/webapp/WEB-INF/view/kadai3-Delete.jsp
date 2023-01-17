<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除画面</title>
</head>
<body>

<h1>削除するメールアドレスを入力してください。</h1>
	<form action="Kadi3Deletemail" method="post">
		メールアドレス：<input type="email" name="mail"><br>
		<input type="submit" value="検索">
	</form>
</body>
</html>