<%--
年齢、エリア、趣味はリストより選択する要jstファイル格納
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
  	import="java.io.*,java.util.ArrayList"
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% //マスタ読み込みとリクエストスコープ保存
String[] master = {"age","area","hb"}; 
for (String m : master){
	ArrayList<String> list = new ArrayList<>();
	String path = application.getRealPath("/")+"master/" + m + ".csv";
	try(BufferedReader bf = new BufferedReader(new FileReader(path))){
	String line;
		while ((line = bf.readLine()) != null) {
			list.add(line);
		}
		bf.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
request.setAttribute(m + "list", list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width,initial-scale=1">
<title>検索設定</title>
<!-- 
<link rel="stylesheet" type="text/css" href="css/stylet.css">
 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<link href="http://www.jiyu-kobo.co.jp/library/ygf" rel="stylsheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">

</head>
<body class="main-body">

<header>
<h1>Tinber</h1>
<%--遷移ボタン--%>
	<nav class="nav">
 		<ul>
            
			<li><a href="<%=request.getContextPath() %>/Mypage" class="header-a">
				<i class="far fa-address-card fa-lg"></i><br>MYPAGE </a></li>
			<li><a href="<%=request.getContextPath() %>/Main" class="header-a">
				<i class="fas fa-undo-alt fa-lg"></i><br>MAIN</a></li>
			<li><a href="http://localhost:8080/matchingApp/help.jsp" target="_new" class="header-a">
				<i class="far fa-question-circle fa-lg"></i><br>HELP</a></li>
		</ul>
	</nav>
</header>

<div class="flexbox_v">
	<noscript>
		<p>
		このサイトではJavaScriptを使用しています
		</p>
	</noscript>

<div class="all">
<div class="boxitem1">
<div class="searchmypage">
	<h2>検索設定</h2>
	<h3>検索条件</h3>
	<br>
	<form action="/matchingApp/Main" method="get" name="serch_form" onsubmit="clickEvent(); return false;" >
	<table>
		<tr><th>年齢</th>
		<td class="search-age">下限<br>
		<select id="minage" name="minage">
		<option value=""></option>
		<c:forEach var="line" items="${agelist}">
		<option value="${line}">${line}</option>
		</c:forEach>
		</select>歳
		</td>
		<td class="search-age">上限<br>
		<select id="maxage" name="maxage">
		<option value=""></option>
		<c:forEach var="line" items="${agelist}">
		<option value="${line}">${line}</option>
		</c:forEach>
		</select>歳
		</td></tr>
			
		<tr><th>エリア</th>
		<td>
		<select id="area" name="area">
		<option value=""></option>
		<c:forEach var="line2" items="${arealist}">
		<option value="${line2}">${line2}</option>
		</c:forEach>
		</select>
		</td></tr>
		
		<tr><th>趣味</th>
		<td>
		<select id="hobby" name="hobby">
		<option value=""></option>
		<c:forEach var="line3" items="${hblist}">
		<option value="${line3}">${line3}</option>
		</c:forEach>
		</select>
		</td></tr>
	</table>
	<br><br>
	<button>検索</button>
	<button type="reset">リセット</button>
	</form>
	<form action="/matchingApp/Main" method="get" >
	<button>Mainに戻る</button>
	</form>

</div>	
</div>	
</div>
		<!-- ↓JavaScriptの処理を追加 
		https://techacademy.jp/magazine/36146-->
	<script type="text/javascript" src="js/check.js"></script>
</div>
</body>
</html>