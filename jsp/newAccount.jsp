<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width,initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/mian.css">	
<link rel= "stylesheet" type="text/css"  href = "/matchingApp/css/style.css">
<link href="http://www.jiyu-kobo.co.jp/library/ygf" rel="stylsheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<title>新規登録画面</title>
</head>

<body class="login-body">

<header>
<h1>Tinber</h1>
<%--遷移ボタン--%>
	<nav class="nav">
 		<ul>
			<li><a href="http://localhost:8080/matchingApp/help.jsp" target="_new" class="header-a">
				<i class="far fa-question-circle fa-lg"></i><br>HELP</a></li>
		</ul>
	</nav>
</header>
 
 <div class="main-new">
 <div class="boxitem1">
<h2>IDとパスワードを入力してください。</h2><br>
	*IDは4～8文字  (半角英数字,「_（アンダースコア）」と「.（ピリオド）)が使えます。 <br>
	*パスワードは6～8文字 (半角英数字,「_（アンダースコア）」と「.（ピリオド）)が使えます。<br><br>
	
	<form action="/matchingApp/NewAccount" method="post">
		ＩＤ:(必須)<br>
		<input type="text" name="ID"> <br><br>                  
		パスワード:（必須）<br>
		<input type="password" name="パスワード"><br><br>
	
		<%if (errorMsg != null)
			out.print(errorMsg);%> <br><br> 
	
	<input type="submit" value="登録" class="register-button"><br><br>
	</form>
</div>
</div>
</body>
</html>