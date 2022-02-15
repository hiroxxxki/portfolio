 //searchページ年齢上限チェック
 function clickEvent() {
	let minage = document.getElementById("minage").value;
	let maxage = document.getElementById("maxage").value;
	let min = parseInt(minage,10);
	let max = parseInt(maxage,10);
	if(min > max){
		alert("年齢上限が下限を上回っています");
	 }else{
	    document.serch_form.submit();
	 }
 }
 
//loginページID、PWチェック
 function clicklogincheck() {
   let id = document.getElementById("ID").value;
   let pw = document.getElementById("PASSWORD").value;
   var idpattern = /[A-Za-z0-9_.]{4,8}/;
   var pwpattern = /[A-Za-z0-9]{6,8}/;
    if(id.match(idpattern)){
    	if(pw.match(pwpattern)){
        	document.formJS.submit();
        }else{
        alert("パスワードは6～8文字の半角英数と\n「_（アンダースコア）」と「.（ピリオド）」です");
        }
    }else{
    	alert("IDは4～8文字の半角英数と\n「_（アンダースコア）」と「.（ピリオド）」です");
    }
 }

//loginページパスワード可視化
  function pushHideButton() {
        	var txtPass = document.getElementById("PASSWORD");
        	var btnEye = document.getElementById("buttonEye");
            if (txtPass.type == "text") {
                txtPass.type = "password";
                btnEye.className = "fas fa-eye";
              } else {
                txtPass.type = "text";
                btnEye.className = "fas fa-eye-slash";
              }
	  	}
	  	
 //chatページの空送信無効
 function clickchatcheck() {
   let text = document.getElementById("text").value;
   if(text.length > 0 || text.length <= 100){
   document.chat_form.submit();
    }
 }
 
   //mypage name欄nullチェック
 function clicknamenull() {
   let text = document.getElementById("NAME").value;
    if(text.length > 0 && text.length <= 10){
       document.name_form.submit();
    }else{
       alert("名前は空欄で登録できません\n1～10文字で記載してください");
 	}
 }
  //mypage age欄nullチェック
 function clickagenull() {
   let value = document.age_form.AGE.value;
    if(value.length > 0){
       document.name_form.submit();
    }else{
       alert("年齢は空欄で登録できません");
 	}
 }
 //mypage gender欄nullチェック
 function clickgendernull() {
   let radio0 = document.gender_form.GENDER[0].checked;
   let radio1 = document.gender_form.GENDER[1].checked;
    if(radio0 || radio1){
    	 document.name_form.submit();
    }else{
         alert("性別が選択されていません");
 	}
 }
   //mypage Comment50文字チェック
 function clickcommentcheck() {
   let textarea = document.getElementById('textarea');
    if(textarea.length >= 50){
       document.name_form.submit();
    }else{
       alert("コメントは50文字までです");
 	}
 }
//mypage 戻るボタンの必須項目抜けチェック
 function clickmypagecheck() {
   let age = document.getElementById("dbage").value;
    if(age===null || age.length==0 ||age==0){
    	alert("サービスの利用には年齢の登録が必要です");
    }else{
         document.name_form.submit();
 	}
 }

 
// 新規登録　18才の以上確認
 function clicknacheck() {
    var result = window.confirm("サービスの利用は18歳以上からです。\nあなたは18歳以上ですか？");
    if( result ) {
  		document.new_form.submit();
    }
}
//mypage 戻るボタンの必須項目抜けチェック
 function clickmypagecheck() {
   let age = document.getElementById("dbage").value;
   let gender = document.getElementById("dbgender").value;
   let msg ="";
    if(age<18){
    	msg = " 年齢 ";
    }else if( dbgender==3){
    	msg = msg +" 性別 ";
    }
    if(msg.length>0){
    alert("未登録事項があります。\n" + msg +"を登録してください。");
    }else{
         document.name_form.submit();
 	}
}


// 確認ダイアログの表示  +「OK」時の処理開始
	function disp(){
		if(window.confirm('本当に退会しますか？')){
		location.href = "/matchingApp/Membership";
			 // membershipへジャンプして退会処理完了
	}
}


	function logout(){
		if(window.confirm('ログアウトしますか？')){
		location.href = "/matchingApp/Logout";
			 // Logoutへジャンプしてログアウト処理完了
	}
}



//スクロール下固定
let target = document.getElementById('scroll-inner');
target.scrollIntoView(false);


//mypage 画像サイズオーバー
function clickphotosize(){
	var fileList= document.getElementById("photofile").files;
	var list= 0;
	for(var i=0; i<fileList.length; i++){
		list += fileList[i].size ;
	}
	if (list>1000000){
         alert("アップロード可能なファイルサイズは1MB以下です。");
	}
		 document.photo_form.submit();
}

