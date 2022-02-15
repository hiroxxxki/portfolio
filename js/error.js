
function clickEvent() {
    let name = document.getElementById("NAME").value;
    let gender = document.getElementById("GENDER").value;
    let age = document.getElementById("AGE").value;
    let comment = document.getElementById("COMMENT").value;
		// if文で必須項目が未入力の場合の処理を行っている
		 if(name == null && name.length() == 0){
			alert("名前が未入力です！");
		}if(gender == null && gender.length() == 0) {
			alert("性別が未入力です！");
		}if(age == null && age.length() == 0) {
			alert("年齢が未入力です！");
		}if(comment.length() > 50) {
			alert("コメントの文字数がオーバーしています！");
		// elseでちゃんと入力されている時の処理
		}else{
	       document.filst_form.submit();
	}
}