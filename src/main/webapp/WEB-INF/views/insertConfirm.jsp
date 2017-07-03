<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%
	String name = (String) request.getAttribute("name");
	String tel = (String) request.getAttribute("tel");
	String pass = (String) request.getAttribute("pass");
	String msg = (String) request.getAttribute("msg");
%>
<meta charset="UTF-8">
<title>登録確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>これでよろしいですか？</p>
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
	<form action="insertConfirm" method="post">
		<fieldset class="label-110">
			<div>
				<label>名前</label><input type="text" name="name" value="<%=name%>"
					readonly>
			</div>
			<div>
				<label>TEL</label><input type="text" name="tel" value="<%=tel%>"
					readonly>
			</div>
			<div>
				<label>PASS（再入力）</label><input type="password" name="rePass">
			</div>
		</fieldset>
		<div>
			<input type="submit" name="button" value="登録"> <input
				type="submit" name="button" value="戻る"
				onclick="location.href='insert'; return false;">
		</div>
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>