<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="jp.co.axiz.web.entity.Admin"%>
<%
	Admin user = (Admin) session.getAttribute("loginAdmin");
%>
<head>
<meta charset="UTF-8">
<title>登録結果確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		実行者：<%=user.getAdminName()%>
	</p>
	<p>正常に登録されました</p>
	<form action="select">
		<input type="submit" value="検索">
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>