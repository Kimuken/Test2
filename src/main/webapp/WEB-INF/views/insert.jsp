<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%
	String msg = (String)request.getAttribute("msg");
%>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		登録情報を入力してください<br> <span class="required"></span>は必須です
	</p>
	<%
		if(msg != null && !"".equals(msg))out.print(msg);
	%>
	<form action="insert" method="post">
		<fieldset class="label-60">
			<div>
				<label class="required">名前</label><input type="text" name="name">
			</div>
			<div>
				<label class="required">TEL</label><input type="text" name="tel">
			</div>
			<div>
				<label class="required">PASS</label><input type="password"
					name="pass">
			</div>
		</fieldset>
		<input type="submit" value="確認">
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>