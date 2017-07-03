<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<%@ page import="jp.co.axiz.web.entity.User"%>
<%@ page import="java.util.List"%>
<%
	List<User> findData = (List<User>) request.getAttribute("findData");
%>
<head>
<meta charset="UTF-8">
<title>検索結果画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<table border="1">
		<caption>検索結果</caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>TEL</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < findData.size(); i++) {
					out.print("<tr>");
					out.print("<td>" + findData.get(i).getUserId() + "</td>");
					out.print("<td>" + findData.get(i).getUserName() + "</td>");
					out.print("<td>" + findData.get(i).getTelephone() + "</td>");
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>

	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>