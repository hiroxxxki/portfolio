<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width,initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/mian.css">
<title>退会処理</title>
<!-- 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/stylet.css">
 -->
</head>
<body class="main-body">
<header>
<h1>Tinber</h1>
</header>
<br><br><br><br>
<div class="main">
	${msg}
 	<c:if test="${empty msg}">
 		処理が正常に終了しませんでした。<br>
 		再度<a href="<%= request.getContextPath() %>/Mypage" class="not">こちら</a>のページから退会処理を行ってください。
 	</c:if>
 	<br><br><br>
 	<a href="http://localhost:8080/matchingApp/Start">topページへ</a>
 	</div>
</div>
</body>
</html>