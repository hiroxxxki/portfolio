<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"content="width=device-width,initial-scale=1">
	<title>ログイン</title>
	<!-- <link rel="stylesheet" type="text/css" href="css/stylet.css"> 
	 -->
	<link rel="stylesheet" type="text/css" href="css/mian.css">
	<link rel= "stylesheet" type="text/css"  href = "/matchingApp/css/style.css">
	<link href="http://www.jiyu-kobo.co.jp/library/ygf" rel="stylsheet">
	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
		<%--外部CSSから目のアイコンを取得するスタイルシート --%>
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script></script>
	<!-- jQuery UI -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
  	integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
	crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
	
</head>
<body class="login-body">
	<script type="text/javascript" src="js/check.js"></script>

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
 


<div class="main">

	<h2>IDとパスワードを入力してください</h2><br>
	<%-- ログインできなかった場合のメッセージ表示 --%>
	<c:if test ="${!empty error}">
	<span class="red">	${error}</span>
	</c:if>	<br>
	
	<div class="boxitem1">
	<%-- 入力フォーム --%>
	<form action="<%= request.getContextPath() %>/Login" method="post" 
			id="login_form" onsubmit="clicklogincheck(); return false">
			
		ＩＤ<br>
		<input type="text" name="ID" id="ID"><br> <br>
		<div>パスワード</div>
		<div class="pass"> <input type="password" name="PASSWORD" id="PASSWORD">
		<span style="font-size: 0.9rem;">
		<i id="buttonEye" class="fas fa-eye" onclick="pushHideButton();"></i></span>
		<br><br>
		</div>
	</div>
	
	<br> 
	<div class="boxitem1">
	<button class="register-button">ログイン</button>
	</form><br><br>
	</div>

	<%-- その他リンクの記載 --%>
	<div class="boxitem1">	
	<form action="<%= request.getContextPath() %>/NewAccount" method="get"
	id = "new_form" onsubmit="clicknacheck();return false">
	<input type ="hidden" name="register" value ="new">
	<button class="register-button">新規登録</button>

</form>
</div>
</body>
</html>
</html>