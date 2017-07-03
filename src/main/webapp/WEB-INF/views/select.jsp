<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
<%@ page import="jp.co.axiz.web.entity.User"%>
<%
	String msg = (String) request.getAttribute("msg");
	User updateUser = (User) session.getAttribute("updateUser");
	Integer insertUserId = (Integer) session.getAttribute("insertUserId");
%>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		検索したいデータ情報を入力してください<br> ※全て空白の場合は全検索を行います
	</p>
	<p>
		<%
			try {
				if (msg != null || msg.length() != 0)
					out.print(msg);
			} catch (Exception e) {
				//何もしない
			}
		%>
	</p>
	<form action="list">
		<fieldset>
			<div>
				<label>ID</label><input type="text" name="id"
					value=<%try {
				if (updateUser != null && updateUser.getUserId() != null) {
					out.print(updateUser.getUserId());
				} else if (insertUserId != null) {
					out.print(insertUserId);
				}
			} catch (Exception e) {
				//何もしない
			}%>>
			</div>
			<div>
				<label>名前</label><input type="text" name="name">
			</div>
			<div>
				<label>TEL</label><input type="text" name="tel">
			</div>
		</fieldset>
		<input type="submit" value="検索">
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
