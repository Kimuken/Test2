<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容確認画面</title>
<%@ page import="jp.co.axiz.web.entity.User"%>
<%
	User updateUser = (User) session.getAttribute("updateUser");
	String newName = (String) session.getAttribute("newName");
	String newTel = (String) session.getAttribute("newTel");
	String newPass = (String) session.getAttribute("newPass");
	String msg = (String) request.getAttribute("msg");
	String oldPass = (String) session.getAttribute("oldPass");
%>
<link href="css/commons.css" rel="stylesheet">
<style>
.col {
	float: left;
}

.col-clear {
	clear: both;
}

#arrow {
	margin-top: 60px;
}
</style>
</head>
<body>
	<p>
		<%
			try {
				if (msg != null || msg.length() != 0)
					out.print(msg);
			} catch (Exception e) {
				//なんもしない
			}
		%>
	</p>

	<p>これでよろしいですか？</p>

	<form action="update" method="post">
		<fieldset>
			<div>
				<label>ID</label><input type="text" name="id"
					value=<%=updateUser.getUserId()%> readonly>
			</div>
		</fieldset>

		<fieldset class="col">
			<legend>変更前</legend>
			<div>
				<label>名前</label><input type="text"
					value=<%=updateUser.getUserName()%> disabled>
			</div>
			<div>
				<label>TEL</label><input type="text"
					value=<%=updateUser.getTelephone()%> disabled>
			</div>
			<div>
				<label>PASS</label><input type="password"
					value=<%=updateUser.getPassword()%> disabled>
			</div>
		</fieldset>

		<div id="arrow" class="col">⇒</div>

		<fieldset class="col label-110">
			<legend>変更後</legend>
			<div>
				<label>名前</label><input type="text" name="newName"
					value=<%=newName%> readonly>
			</div>
			<div>
				<label>TEL</label><input type="text" name="newTel"
					value=<%=newTel%> readonly>
			</div>
			<div>
		<label>PASS(再入力)</label><input type="password" name="rePass" value=<%=oldPass%>>

			</div>
		</fieldset>

		<div class="col-clear">
			<input type="submit" name="button" value="更新"> <input
				type="submit" name="button" value="戻る"
				onclick="location.href='updateInput'; return false;">
		</div>
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
