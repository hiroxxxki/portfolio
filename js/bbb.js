
 function clickFilstCheck() {
 	// データベースからID参照でname,gebder,ageの入力値を受け取る
  	let name = document.getElementById("id").value;   // jspの<inputのid="id"のをとっている
  	// ラジオボタンがクリックされていたらture , されてなかったらfalse
   	let radio0 = document.all_from.gender[0].checked; // ラジオボタンの取得方法
   	let radio1 = document.all_from.gender[1].checked; // ラジオボタンの取得方法
  	let age = document.getElementById("AGE").value;   // リストにしている年齢の取得方法
  	let msg ="" ;			// 表示させたいmsgをいったん空にしておく
    let msg2 ="" ;
	
	let bool = false;
	let bool2= false;
	
   		if(!name ){
		msg =  "名前" ;
	   	bool = true;
   		}
   		   		
   		if(radio0 == false && radio1 == false){
   		msg = msg + "性別" ;
   		bool = true;
   		}

   		if(age < 18 ){
   		msg = msg + "年齢" ;
   		bool = true;
   		}
   		
   	if(bool){
   		msg = "未登録事項があります" + msg + "を\n登録してください。"
	}
	
   	if(name.length>10 ){
		msg2 =  "\n名前の文字数は10文字以内です" ;
	   	bool2 = true;
   	}

	if(bool || bool2){
		alert(msg+msg2);
	}else{
		document.all_form.submit();
	}

}