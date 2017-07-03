<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="jp.co.axiz.web.entity.User"%>
<head>
<meta charset="UTF-8">
<title>更新内容入力画面</title>
<link href="css/commons.css" rel="stylesheet">
<%
	String msg = (String) request.getAttribute("msg");
	User user = (User) session.getAttribute("updateUser");
%>
</head>
<body>
	<p>
		１箇所以上の項目を変更してください<br> ※IDは変更できません
	</p>
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
	<form action="updateConfirm" method="post">
		<fieldset>
			<div>
				<label>ID</label><input type="text" name="id"
					value=<%=user.getUserId()%> readonly>
			</div>
			<div>
				<label>名前</label><input type="text" name="newName"
					value=<%=user.getUserName()%>>
			</div>
			<div>
				<label>TEL</label><input type="text" name="newTel"
					value=<%=user.getTelephone()%>>
			</div>
			<div>
				<label>PASS</label><input type="password" name="newPass"
					value=<%=user.getPassword()%>>
			</div>
		</fieldset>
		<div>
			<input type="submit" name="button" value="確認"> <input
				type="submit" name="button" value="戻る"
				onclick="location.href='update'; return false;">
		</div>
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
