<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.io.*,java.util.ArrayList,model.Prof"
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
String alert=(String)request.getAttribute("alert");
boolean isalert =false;
if (alert!=null){
 isalert = alert.equals("Y");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width,initial-scale=1">
<title>Mypage</title>
<!-- 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/stylet.css"> 
-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link href="https://fonts.google.com/specimen/Noto+Sans+JP" rel="stylsheet">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<link href="http://www.jiyu-kobo.co.jp/library/ygf" rel="stylsheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<script>
		function loadFinished(){
			if(<%=isalert%>){
				alert( "初回プロフィールが未完了で終了しました。\n名前・年齢・性別を更新してください。");
				}
		}
		window.onload = loadFinished;
	</script>
</head>
<body class="scroll-body">
<script type="text/javascript" src="js/check.js"></script>


<header>
<h1>Tinber</h1>
<%--遷移ボタン--%>
	<nav class="nav">
 		<ul>
            <li><a href="<%=request.getContextPath() %>/search.jsp" class="header-a">
            	<i class="fas fa-search fa-lg"></i><br>SEARCH</a></li>
			<li><a href="<%=request.getContextPath() %>/Main" class="header-a">
				<i class="fas fa-undo-alt fa-lg"></i><br>MAIN</a></li>
			<li><a href="<%=request.getContextPath() %>/Membership" onClick="disp(); return false;" class="header-a">
				<i class="far fa-frown fa-lg"></i><br> &ensp; 退会 &ensp;</a></li>
			<li><a href="<%=request.getContextPath() %>/Logout" onClick="logout(); return false;" class="header-a">
				<i class="fas fa-sign-out-alt fa-lg"></i><br>LOGOUT</a></li>
			<li><a href="http://localhost:8080/matchingApp/help.jsp" target="_new" class="header-a">
				<i class="far fa-question-circle fa-lg"></i><br>HELP</a></li>
		</ul>
	</nav>
</header>

	<div class="all">
	
		<h2>${myprof.id}さんマイページ編集</h2>
	<div class="searchmypage">
	
	<p>名前： ${myprof.name}</p>     ※必須
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post" 
	name="name_form" onsubmit="clicknamenull(); return false;">
		<!--名前表示-->
		<input type ="text" name="NAME">
		<button type="submit" name="btn" value="NAME">変更する</button>
	</form><br><br>
	
	
		<!-- 年齢表示-->
		<p>年齢: <c:if test="${myprof.age<=17}">未登録</c:if>   
		<c:if test="${myprof.age>17}">${myprof.age}</c:if></p>   ※必須
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post" 
	name="age_form" onsubmit="clickagenull(); return false;">	
		<select id="age" name="AGE">
		<option value=""></option>
		<c:forEach var="line" items="${agelist}">
		<option value="${line}">${line}</option>
		</c:forEach>
		</select>歳
		<button type='submit' name='btn' value='AGE'>変更する</button>
	</form><br><br>
	
	
		<!--性別表示-->
		<p>性別：※必須</p>
		<c:if test="${myprof.gender==0}">男性</c:if>
		<c:if test="${myprof.gender==1}">女性</c:if>
		<c:if test="${myprof.gender==3}">未登録</c:if><br>
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post" 
	name="gender_form" onsubmit="clickgendernull(); return false;">
		<p>
			<input type = "radio" name="GENDER" value="0">男性<br>
			<input type = "radio" name="GENDER" value="1">女性
		</p>
		<button type='submit' name='btn' value='GENDER'>変更する</button>
	</form><br><br>
	
	
		<p>エリア：  ${myprof.area}</p>
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post">	
		<select id="area" name="AREA">
		<option value=""></option>
		<c:forEach var="line2" items="${arealist}">
		<option value="${line2}">${line2}</option>
		</c:forEach>
		</select>
		<button type='submit' name='btn' value='AREA'>変更する</button>
	</form><br><br>
	
	
		<!--趣味表示-->
		<p>趣味1</p>${myprof.hobby1}
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post">	
		<select id="hobby" name="HOBBY1">
		<option value=""></option>
		<c:forEach var="line3" items="${hblist}">
		<option value="${line3}">${line3}</option>
		</c:forEach>
		</select>
		<button type='submit' name='btn' value='HOBBY1'>変更する</button>
	</form><br><br>
	
	
		<p>趣味2</p>${myprof.hobby2}
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post">	
		<select id="hobby" name="HOBBY2">
		<option value=""></option>
		<c:forEach var="line3" items="${hblist}">
		<option value="${line3}">${line3}</option>
		</c:forEach>
		</select>
		<button type='submit' name='btn' value='HOBBY2'>変更する</button>
	</form><br><br>
	
	
		<p>趣味3</p>${myprof.hobby3}
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post">	
		<select id="hobby" name="HOBBY3">
		<option value=""></option>
		<c:forEach var="line3" items="${hblist}">
		<option value="${line3}">${line3}</option>
		</c:forEach>
		</select>
		<button type='submit' name='btn' value='HOBBY3'>変更する</button>
	</form><br><br>
	
		<!--自己紹介文表示-->
	<form action = "<%=request.getContextPath() %>/Mypage" method = "post"
	name="comment_form" onsubmit="clickcommentcheck(); return false;">
		<p>自己紹介文</p><br>
		${myprof.comment}<br>	
		<textarea id="textarea" name="COMMENT" cols="50" rows="5" placeholder="ここに記載"></textarea>
		<button type='submit' name='btn' value='COMMENT'>変更する</button>
	</form><br><br>
	
		<!--写真表示-->
	<p>写真：<img class="ofi" src="/matchingApp/userpic/${myprof.photo}" 
		onerror="this.src='<%=request.getContextPath() %>/userpic/noimage.JPG';"></p>
		<form action="<%=request.getContextPath() %>/Mypage" method="post" 		enctype="multipart/form-data" id="photo_form" onsubmit="clickphotosize(); return false;">
		<input type="file"  id="photofile" name="PHOTO">
		<button type="submit" name="btn" value="photo">アップロード</button>
		</form>
	<br><br>

		<!--その他処理-->
		<form action = "<%=request.getContextPath() %>/Main" method = "get" 
		id="mypage" onsubmit="clickmypagecheck(); return false;">
		<input type="hidden" id="dbage" value="${myprof.age}">
		<input type="hidden" id="dbgender" value="${myprof.gender}">
		<button class="back-button">戻る</button>
		</form>
	</div>
	</div>
</body>
</html>
