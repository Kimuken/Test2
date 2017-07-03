<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String msg = (String)request.getAttribute("msg");
%>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		更新を行うデータのIDを入力してください<br> <span class="required"></span>は必須です
	</p>
	<%
		if(msg != null && !"".equals(msg))out.print(msg);
	%>
	<form action="updateInput" method="post">
		<fieldset>
			<div>
				<label class="required">ID</label><input type="text" name="id">
			</div>
		</fieldset>
		<input type="submit" value="確認">
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
