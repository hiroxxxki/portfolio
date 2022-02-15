<%--
Main.javaから表示
画像はwebapp/userpicにあり
ボタンは①いいね②よくないね③検索④マイページ⑤メッセージ
ボタン毎の動作は
①いいねはDBにデータ保存して次を表示
②よくないねは次を表示
Listの最終の場合は(flug=last)Mainへgetで返す
いいねの場合は○○テーブルに自ID、表示している相手のIDを追加　…データベース構造を要確認
いいね又はよくないねボタンを押されたら、次の相手データを表示

③④⑤はサーブレットへリダイレクト
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.io.*, model.Prof" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
//リクエストスコープから要素を取得
	Prof showProf = (Prof) request.getAttribute("showProf");
	String[] hobbies =(String[]) session.getAttribute("hobbies");
//趣味欄の枠色有無と表示非表示フラグを作成
int h1=0;
int h2=0;
int h3=0;
String sph1=showProf.getHobby1();
String sph2=showProf.getHobby2();
String sph3=showProf.getHobby3();
for(String h : hobbies){ //自分の趣味
	if(h == null || h ==""){
		continue;
	}
	if(sph1!=null){
		if(h.equals(sph1)){
			h1=2;
		}else if(sph1.length()>0){
			h1=1;
		}
	}
	if(sph2!=null){
		if(h.equals(sph2)){
			h2=2;
		}else if(sph2.length()>0){
			h2=1;
		}
	}
	if(sph3!=null){
		if(h.equals(sph3)){
			h3=2;
		}else if(sph3.length()>0){
			h3=1;
		}
	}
}
request.setAttribute("h1",h1);
request.setAttribute("h2",h2);
request.setAttribute("h3",h3);
String newlist=(String)request.getAttribute("re");
boolean isnewlist =false;
if (newlist!=null){
 isnewlist = newlist.equals("nextlist");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width,initial-scale=1">
<title>MachingAppMAIN</title>
<!-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/stylet.css"> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link href="http://www.jiyu-kobo.co.jp/library/ygf" rel="stylsheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<script>
		function loadFinished(){
			if(<%=isnewlist%>){
				alert( "検索結果が終了したため、Mainリストに戻ります。");
				}
		}
		window.onload = loadFinished;
	</script>
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
				<i class="far fa-address-card fa-lg"></i><br>MYPAGE</a></li>
			<li><a href="<%=request.getContextPath() %>/Chat1" class="header-a"> 
				<i class="far fa-comments fa-lg"></i><br>CHAT</a></li>
			<li><a href="http://localhost:8080/matchingApp/help.jsp" target="_new" class="header-a">
				<i class="far fa-question-circle fa-lg"></i><br>HELP</a></li>
		</ul>
	</nav>
</header>


<div class="all">

 <%--写真--%>
	<div class="pop-box">
  	<label for="popup-on"><label for="popup-on"><div class="btn-open"><img class="ofi-hover10" src="<%=request.getContextPath() %>/userpic/${showProf.photo}" 
		onerror="this.src='<%=request.getContextPath() %>/userpic/noimage.JPG';"></div></label></label>
	<input type="checkbox" id="popup-on">
		<div class="popup">
  		<label for="popup-on" class="icon-close">×</label>
   			<div class="popup-content">
       		<!-- ポップアップの中身 -->
      		<img src="<%=request.getContextPath() %>/userpic/${showProf.photo}" 
				onerror="this.src='<%=request.getContextPath() %>/userpic/noimage.JPG';" class="popup-img">
        	<!-- ./ポップアップの中身　ここまで -->
    		</div>
    		<label for="popup-on"><div class="btn-close">閉じる</div></label>
  		</div>
	</div>
	
	
	<div class="name">名前:${showProf.name}
  	<c:if test="${!empty showProf.age}">(${showProf.age})</c:if>
	</div>
	
  	<table>
		<%-- https://text-img.cman.jp/ --%>
		<tr><td>
		<form action="/matchingApp/Main" method = "post">
		<input type="image" src="<%=request.getContextPath() %>/btns/like.png" >
		<input type="hidden" value="like" name="ans">
		</form></td>
		<td>
		<form action="/matchingApp/Main" method= "post">
		<input type="image" src="<%=request.getContextPath() %>/btns/next.png">
		<input type="hidden" value="next" name="ans">
		</form></td>
	</table>
	<table>
		<th>エリア</th><td>${showProf.area}</td>
		</tr><tr>
		<th>趣味</th>
		<td>
			<c:if test="${ h1==2 }">
				<div class="mark_hobby">${showProf.hobby1}</div></c:if>
			<c:if test="${ h1==1 }">
				<div class="hobby">${showProf.hobby1}</div></c:if>
			<c:if test="${ h2==2 }" >
				<div class="mark_hobby">${showProf.hobby2}</div></c:if>
			<c:if test="${ h2==1 }">
				<div class="hobby">${showProf.hobby2}</div></c:if>
			<c:if test="${ h3==2 }" >
				<div class="mark_hobby">${showProf.hobby3}</div></c:if>
			<c:if test="${ h3==1 }">
				<div class="hobby">${showProf.hobby3}</div></c:if>
		</td>			 
		</tr><tr>
		<th>コメント</th>
		<td>${showProf.comment}<br><br><br></td>
		</tr>
	</table>
	<br><br>
</div>
</body>
</html>