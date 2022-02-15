<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
  	import="java.io.*,java.util.ArrayList"
 %>
 <%
 String i = (String)request.getAttribute("a");
String errorMsg = (String)request.getAttribute("errorMsg");
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
<meta charset = "UTF-8">
<title>my page作成</title>
<link rel= "stylesheet" type="text/css"  href = "/matchingApp/css/style.css">
<link href="http://fonts.googleapis.com/css2?family=Caveat&display=swap" rel="stylsheet">
<link href="http://www.jiyu-kobo.co.jp/library/ygf" rel="stylsheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">

</head>
<body class="scroll-body">
	
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
 
	<div class="all">
		
			<h2>マイページ作成</h2>
			
				<!--写真表示-->
				<p>写真：</p>
		<form action = "/matchingApp/Profile" method = "post" enctype="multipart/form-data" >
			<input type="file" name="PHOTO" accept="image/jpeg, image/png">
			<button type="submit" name="btn" value="photo" >アップロード</button>
			<br>
			※写真を選択したらアップロードボタンを押してください
		</form>
		
			<%if (errorMsg != null)
			out.print(errorMsg);%> <br><br> 
			
		<img src="http://localhost:8080/matchingApp/userpic/<%=i%>"
		onerror="this.src='<%=request.getContextPath() %>/userpic/noimage.JPG';">
			<!--  ドキュメントとは<from 全体のこと (指定場所みたいなの) -->
			<!--  ここではドキュメントの名前をallFromとする -->
			<form action = "/matchingApp/Profile" name="all_from" method = "GET"
		id="mypage" onsubmit="clickFilstCheck(); return false;">
			<input type="hidden" name="photo" value="<%=i%>">
			<br><br>
			
				<!--名前表示-->
				<p>名前：(必須)</p>
				<input type="text" id="id" name="name" class="new-button"><br>
			<!-- type="text"のvalueは入力値のこと いちいち書かなくてもいい -->
				<!--性別表示-->
				<p>性別：(必須)</p>
				男:<input type="radio" name="gender" value="0" style="transform:scale(1.5);">
				女:<input type="radio" name="gender" value="1" style="transform:scale(1.5);"><br>
				
				<!--年齢表示-->
				<p>年齢：</p>
					<select name="age" id="AGE"class="age-button">
						<option value=""></option>
						<c:forEach var="line" items="${agelist}">
						<option value="${line}">${line}</option>
						</c:forEach>
					</select>歳
							
				<!--エリア表示-->
				<p>エリア：</p>
					<select name="area" class="area-button">
						<option value=""></option>
						<c:forEach var="line2" items="${arealist}">
						<option value="${line2}">${line2}</option>
						</c:forEach>
					</select>
					
				 <!--趣味表示-->
				<p>趣味1:</p>
					<select name="hobby1" class="hobby-button">
						<option value=""></option>
						<c:forEach var="line3" items="${hblist}">
						<option value="${line3}">${line3}</option>
						</c:forEach>
					</select>
				<p>趣味2:</p>
					<select name="hobby2" class="hobby-button">
						<option value=""></option>
						<c:forEach var="line3" items="${hblist}">
						<option value="${line3}">${line3}</option>
						</c:forEach>
					</select>
				<p>趣味3:</p>
					<select name="hobby3" class="hobby-button">
						<option value=""></option>
						<c:forEach var="line3" items="${hblist}">
						<option value="${line3}">${line3}</option>
						</c:forEach>
					</select>
				
				<!--自己紹介文表示-->
				<p>自己紹介文を入力してください（50字以内）<br>
					<textarea name="comment" cols="50" rows="5" placeholder="ここに記載"></textarea>
				<p><button type ="submit" name="btn" class="create-button" >作成</button></p>
			</form>
			<!-- <button onclick="clickEvent()">送信</button>-->

<script type="text/javascript" src="js/bbb.js"></script>
		</div>
</body>
</html>
