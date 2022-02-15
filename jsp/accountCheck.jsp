<!-- ユーザー登録確認画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = (String)session.getAttribute("checkId");
String pw = (String)session.getAttribute("checkPass");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了画面</title>
</head>
<body>
<h1>入力内容に間違いはありませんか？</h1>

	<form action="/matchingApp/AccountCheck" method="post">
		<p>ログインID:<%=id %></p><br>
		<p>パスワード:<%=pw %></p><br>
	<input type="submit" value="登録"><br><br>
	</form>

 	<a href="/matchingApp/NewAccount">戻る</a>
 
</body>
</html>