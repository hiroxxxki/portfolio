<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport"content="width=device-width,initial-scale=1">
<title>ERROR</title>
	<!-- <link rel="stylesheet" type="text/css" href="css/stylet.css">
	 -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/mian.css">
</head>
<body>
<body class="main-body">
<header>
<h1>Tinber</h1>
</header>
<div class="main">
	<br>
	<h2>検索結果</h2>
	
	該当者がいませんでした。<br><br><br>
	
	<a href="<%=request.getContextPath() %>/Main"> Mainに戻る </a>
	<a href="<%=request.getContextPath() %>/search.jsp"> 検索画面に戻る </a>
</div>
</body>
</html>