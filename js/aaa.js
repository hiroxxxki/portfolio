
 function clickFilstCheck() {
 	// データベースからID参照でname,gebder,ageの入力値を受け取る
  	let name = document.getElementById("id").value;   // jspの<inputのid="id"のをとっている
  	// ラジオボタンがクリックされていたらture , されてなかったらfalse
   	let radio0 = document.all_from.gender[0].checked; // ラジオボタンの取得方法
   	let radio1 = document.all_from.gender[1].checked; // ラジオボタンの取得方法
  	let age = document.getElementById("AGE").value;   // リストにしている年齢の取得方法
  	let msg ="" ;			// 表示させたいmsgをいったん空にしておく
   
	let bool = true;

   		if(!name ){
		msg =  "名前" ;
	   	bool = false;
   		}
   		   		
   		if(radio0 == false && radio1 == false){
   		msg = msg + "性別" ;
   		bool = false;
   		}

   		if(age < 18 ){
   		msg = msg + "年齢" ;
   		bool = false;
   		}
   		
   		if(bool){
   		document.all_form.submit();
   		}else{
   		msg = "未登録事項があります" + msg + "を\n登録してください。"
   		
	}
}





