<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="jp.co.axiz.web.entity.User"%>
<%
	String msg = (String) request.getAttribute("msg");
	User user = (User) session.getAttribute("deleteUser");
%>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
<p>このデータでよろしいですか？</p>

<form action="delete" method="post">
  <fieldset>
    <div>
      <label>ID</label><input type="text" name="id" value=<%=user.getUserId() %> readonly>
    </div>
    <div>
      <label>名前</label><input type="text" name="name" value=<%=user.getUserName() %> readonly>
    </div>
    <div>
      <label>TEL</label><input type="text" name="tel" value=<%=user.getTelephone() %> readonly>
    </div>
  </fieldset>
  <div>
    <input type="submit" name="button" value="削除">
    <input type="submit" name="button" value="戻る" onclick="location.href='delete'; return false;">
  </div>
</form>
<div>
  <a href="menu">メニューに戻る</a>
</div>
</body>
</html>
