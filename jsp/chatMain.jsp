
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.Chat,model.Match" %>
<%@ page import ="java.util.List" %>

<%
List<Chat> chat = (List<Chat>) request.getAttribute("chat"); 
String path = (String) request.getAttribute("pict");

%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">

<title>MATCHING CHAT</title>
<link rel= "stylesheet" type="text/css"  href = "/matchingApp/css/style.css">
<link rel= "stylesheet" type="text/css"  href = "/matchingApp/css/chat.css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">

</head>
<body class="chat-body">

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
				<i class="fas fa-undo-alt fa-lg"></i><br>BACK</a></li>
			<li><a href="http://localhost:8080/matchingApp/help.jsp" target="_new" class="header-a">
				<i class="far fa-question-circle fa-lg"></i><br>HELP</a></li>
		</ul>
	</nav>
</header>
 



<div class="talk">
<div id="talk_name">  ${match.to_name} </div>
<div id="scroll-inner">

<form action="/matchingApp/chatMsg" method ="POST" id="chat_form" onsubmit="clickchatcheck(); return false">



<% 
//System.out.println(chat);System.out.println(chat==null); 
if(chat!=null){
String before="";
for (Chat ch : chat){ 
 if ( ch.isAlign()){ 
 %>
	
	<%	//日付の表示管理
	if(before.equals(ch.getDate())){
		before =ch.getDate();
	}else{ 
	%>
	<div class="date">
	<%= ch.getDate() %></div>

	<%	before =ch.getDate();
	}%>

	<div class="mycomment">
	
	
	<div class="set">
	<span class="time-right">
	<%= ch.getTime() %>
	</span>
	
	<p><%= ch.getMsg() %></p>
	</div>
	</div>
	
	

<% }else{ %>
<%
		if(before.equals(ch.getDate())){
		before =ch.getDate();
		}else{ %>
		<div class="date">
		<%= ch.getDate() %></div>

		<% before =ch.getDate();
		}
		%>
		
<div class="balloon6">
	<div class="faceicon">
	<!-- 写真がなかった場合の回避 -->
	<img src="<%=  path %>" onerror="this.src='<%=request.getContextPath() %>/userpic/noimage.JPG';" > 


	</div>
	<%-- <%= ch.getName_b() --%>

	<div class="chatting">

	<div class="says">
	<%= ch.getMsg() %>
	</div>
	

	
	<span class="time_left">
	<%= ch.getTime() %></span>
	</div>
		</div>
	
<% } %>
<% }%>
<% }%><br>


<div id="send">
<input type="text" id="text"   name="msg" placeholder="メッセージを入力">
<button id="send_btn">送信</button>
</div>

</form>
</div>
</div>




<script type="text/javascript" src="js/check.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" 
integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" 
crossorigin="anonymous"></script>

</body>
</html>