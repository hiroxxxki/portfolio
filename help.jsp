<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サポートページ</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
<!-- 
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/stylet.css">
  -->
</head>
<body class="scroll-body">
<header>
<h1>Tinber</h1>
</header>


<h2 class="h2">どのようなご用件ですか？</h2>
<br>
<br>
<div class="help-main">
<!-- 目次 -->
<h2><a href="http://localhost:8080/matchingApp/help.jsp#htu" class="help-h2">使い方</a> ・
<a href="http://localhost:8080/matchingApp/help.jsp#ts" class="help-h2">トラブルシューティング</a></h2>

<div class="content">
<a href="http://localhost:8080/matchingApp/help.jsp#wt" class="help-a1">Tinberについて</a>
<a href="http://localhost:8080/matchingApp/help.jsp#ac" class="help-a2">アカウント作成について</a>
<a href="http://localhost:8080/matchingApp/help.jsp#op" class="help-a3">基本操作について</a>
<a href="http://localhost:8080/matchingApp/help.jsp#mc" class="help-a4">マッチとメッセージ</a>
</div>


<br><br><br><br><br><br><br><br>
<!--==================================-->
<div id="target">
<h2 id="htu" class="sample">使い方</h2>
<!--==================================-->
<h3 id="wt" class="sample">Tinberについて</h3>
<details>
	 <summary>Tinberのサービスについて </summary>
<p>Tinberには、ここから広がるかもしれない繋がりの可能性があふれています。<br>
新しい人たちに出会いたい。つきあいの範囲を広げたい。旅先で現地の人に会ってみたい。
あるいはただ、いまここで楽しみたい。Tinberはそんなあなたに、ぴったりのアプリです。
Tinberは楽しくてとっても簡単。素敵な人がいたらLIKE。お互いに気に入れば、マッチが成立！
無限に広がる可能性を楽しもう。</p>
</details>
<details>
	<summary>サポートするプラットフォームおよびデバイス</summary>
<p>主要なウェブブラウザ（Chrome、Firefox、Edgeなど）をプラットフォームとしてサポートしています。</p>
</details>
<details>
	<summary>最低年齢要件とはなんですか？</summary>
<p>ご利用いただける年齢は、18歳以上です。</p>
</details>
<details>
	<summary>サインアップと使用開始</summary>
<p>Tinberへようこそ！マッチングやチャット、デートを始める前にまずはTinber
アカウントを作成しましょう。
</p>
</details>
<br>
<br>
<!--==================================-->
<h3 id="ac" class="sample">アカウント作成について</h3>
<details>
<summary>Tinberのアカウントの作り方を教えてください。</summary>
<p>1.<a href="http://localhost:8080/matchingApp/Start">Tinber</a>を開きます<br>
2.「はじめる」をクリックします<br>
3.「新規登録」をクリックします<br>
 &emsp; 年齢認証画面が表示されますので、ご確認ください<br>
4.希望するIDとパスワードを入力し、「登録」をクリックします<br>
5.確認画面に記載のIDとパスワードを確認し、「登録」をクリックします<br>
6.マイページ作成画面よりプロフィールをセットアップします<br>
 &emsp; 写真はファイルを選択して「アップロード」をクリックします<br>
 &emsp; その他必要項目を記入・選択して「作成」をクリックします<br> 
 &emsp; 名前、性別、年齢は必ず登録が必要です<br>
これでご利用を開始できるようになりました。</p>
</details>
<details>
<summary>年齢確認が必要なのはなぜですか？</summary>
<p>なりすましや青少年がトラブルに巻き込まれることを防ぐため、18歳以上であることを確認しています。</p>
</details>
<details>
<summary>年齢制限を満たさない場合はどうなりますか？</summary>
<p>Tinberをご利用いただける年齢は、18歳以上です。
お客様が18歳になるまで登録することはできません。
18歳になってからのご利用をお待ちしております。</p>
</details>
<br><br>

<!--==================================-->
<h3 id="op" class="sample">基本操作について</h3>
<details>
<summary>プロフィールを編集するにはどうしたらいいですか？</summary>
<p>プロフィール情報を編集する場合は、MYPAGEアイコンをクリックします。
この画面では、写真を更新したり、ジェンダーや性的指向を含めるよう自己紹介を編集したり、
興味を追加できます。</p>
</details>
<details>
<summary>検索はどのように設定するのですか？</summary>
<p>検索する場合は、SEARCHアイコンをクリックします。
フィルタリング対象の項目を選択し、「検索」の順に操作してください。
年齢やエリア、趣味で表示するユーザーをフィルタリングできます。</p>
</details>
<details>
<summary>LIKEを取り消しできますか？</summary>
<p>操作の間違いによる取り消しはできません。</p>
</details>
<details>
<summary>メッセージはどうやったら送信できるの？</summary>
<p>Tinberでは旅先の現地メンバーと無料でチャットを楽しむことができます。
ただし、チャットできるのはメンバーがお互いを気に入った場合のみ。
まだマッチが成立していない人は、まずはたくさんスワイプしましょう。。</p>
</details>
<br><br>

<!--==================================-->
<h3 id="mc" class="sample">マッチとメッセージ</h3>
<details>
<summary>マッチ相手にメッセージを送る</summary>
<p>マッチが成立したら、そのユーザーとすぐにチャットを始められます。
main画面にあるchatアイコンをクリックして表示されるマッチした人の中から
メッセージを送りたい人を選んでクリックします。</p>
</details>
<details>
<summary>メッセージは削除することができますか？</summary>
<p>Tinberのメッセージは個別削除ができません。</p>
</details>
<!--==================================-->
<br><br><br><br>
<h2 id="ts" class="sample">トラブルシューティング</h2>
<details>
<summary>アカウントにログインできない場合</summary>
<p>IDには４～８文字の半角英数字と「.」「_」を使用できます。
パスワードには8文字の半角英数字と「.」「_」を使用できます。</p>
</details>
<details>
<summary>表示されるプロフィールのジェンダーや年齢が、自分の希望と違う場合</summary>
<p>検索条件が自分の希望と合っているか確認して下さい。
main画面に表示されるプロフィールはご自身がMYPAGEに登録している性別をもとに
異性のプロフィールを表示します。
年齢、エリア、趣味で絞り込みをする場合はSEARCH画面から設定ください。</p>
</details>
<details>
<summary>プロフィール写真アップロードできません。</summary>
<p>登録できる画像は1MB以内のjpgまたはpngです。拡張子と画像サイズをご確認ください。
現在登録されている画像はMYPAGEで確認することができます。</p>
</details>
<details>
<summary>マッチの相手が消えてしまいました。</summary>
<p>マッチ相手が退会した場合、マッチ画面から削除されます。
チャットの履歴も見ることができなくなります。</p>
</details>
<details>
<summary>アカウント削除に関するトラブル</summary>
<p>次の手順で現在のアカウントを削除できます。<br>
1.MYPAGEをクリック<br>
2.退会をクリック<br>
 &emsp; 確認画面がでますので、「OK」をクリックすると退会が完了します。<br>
 技術的なトラブルが発生した場合<br>
技術的な問題やエラーメッセージが起きた場合は、インターネット接続が十分に確立されているか確認してから、
もう一度やり直してください。</p>
</details>
<br><br>
<a href='http://localhost:8080/matchingApp/help.jsp'" class="back">topへ戻る</a>
<br><br><br><br><br><br><br><br><br><br><br><br>
</div>
</div>
</body>
</html>