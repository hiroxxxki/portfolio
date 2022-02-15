<%-- 呼び出し元Match.java --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.io.*,java.util.ArrayList,java.util.List,
model.Match,java.util.Arrays" %> 
<%
//リクエストスコープからインスタンスを取得
ArrayList<Match> matchList = (ArrayList<Match>) session.getAttribute("matchlist");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"content="width=device-width,initial-scale=1">
<title>MATCH一覧</title>
<!-- 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/stylet.css"> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link href="http://www.jiyu-kobo.co.jp/library/ygf" rel="stylsheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">

</head>
<body class="main-body">

<header>
<h1>Tinber</h1>
<%--遷移ボタン--%>
	<nav class="nav">
 		<ul>
            <li><a href="<%=request.getContextPath() %>/search.jsp" class="header-a">
            	<i class="fas fa-search fa-lg"></i><br>SEARCH</a></li>
			<li><a href="<%=request.getContextPath() %>/Mypage" class="header-a">
				<i class="far fa-address-card fa-lg"></i><br>MYPAGE </a></li>
			<li><a href="<%=request.getContextPath() %>/Main" class="header-a">
				<i class="fas fa-undo-alt fa-lg"></i><br>MAIN </a></li>
			<li><a href="http://localhost:8080/matchingApp/help.jsp" target="_new" class="header-a">
				<i class="far fa-question-circle fa-lg"></i><br>HELP</a></li>
		</ul>
	</nav>
</header>

<div class="all">
<div class="flexbox_v">

<div class="boxitem2">
<div class="match">
<h2>マッチした人一覧</h2>
<!--forここから　要素数回-->
	<% 
	for (int i = 0 ;i<matchList.size();i++) {
	Match m = matchList.get(i);
	//System.out.println(matchList);
	//System.out.println( m.getTo_photo());
	%>
		<form action="<%=request.getContextPath() %>/chatMsg" method="get">
		<button name='btn'>
		<%-- 画像 --%>
			<img src="<%=request.getContextPath() %>/userpic/<%= m.getTo_photo()%>" 
			onerror="this.src='<%=request.getContextPath() %>/userpic/noimage.JPG';" 
			width="50" height="50" class="img1">
			<p><%= m.getTo_name()%></p>
			<%-- リスト番号の取得とパラメ格納--%>
			<input type="hidden" name="elem" value="<%= i %>">
		</button>
		</form>
		<br>
	<% } %>
<!--forここまで-->
		<%-- newのフラグ?newを上に持ってくるならjavascriptか配列格納時？--%>
</div>
</div>
</div>
</div>
</body>
</html>