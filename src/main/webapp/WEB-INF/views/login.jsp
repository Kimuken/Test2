<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String msg = (String) request.getAttribute("msg");
%>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<form action="login" method="post">
		<fieldset>
			<div>
				<label>ID</label><input type="text" name="id">
			</div>
			<div>
				<label>PASS</label><input type="password" name="pass">
			</div>
		</fieldset>
		<input type="submit" value="ログイン">
	</form>
	<%
		if (msg != null && !"".equals(msg))
			out.print(msg);
	%>
	<div>
		<a href="index">TOP画面に戻る</a>
	</div>
</body>
</html>