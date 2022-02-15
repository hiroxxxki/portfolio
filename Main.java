//Main画面表示とボタン操作
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MatchDao;
import dao.ProfDao;
import model.Match;
import model.Prof;
import model.Search;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // サーブレット呼び出し時に実行(main画面を他から呼んだとき)  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//＝＝＝＝＝＝表示するリストを作成＝＝＝＝＝＝
		//IDをセッションスコープより取得
		HttpSession session = request.getSession();
		String self_id = (String) session.getAttribute("loginUser");
		//自分のgender,id,name,photoを取得
		ProfDao pd = new ProfDao();
		Prof selfprof = pd.selfValues(self_id);
		if (selfprof == null) {
			System.out.println("ユーザーIDがDBに存在しません");
			response.sendRedirect("/matchingApp/NewAccount");
			return;
		}
		String fid = selfprof.getId();
		String fname = selfprof.getName();
		String fphoto = selfprof.getPhoto();
		String fhobby1 =selfprof.getHobby1();
		String fhobby2 =selfprof.getHobby2();
		String fhobby3 =selfprof.getHobby3();
		int self_gender =  selfprof.getGender();
		int self_age = selfprof.getAge();
				
		//初回プロフィール登録前離脱チェック(=ageがnull gender3）
		if(self_age==0||self_gender==3) {	
			String alert = "Y";
			request.setAttribute("alert",alert);
		    RequestDispatcher dispatch = request.getRequestDispatcher("/Mypage");
		    dispatch.forward(request, response);
			return;
		}
		
		//セッションスコープに保存
		Match nm = new Match(fid,fname,fphoto);
		String[] hobbies= {fhobby1,fhobby2,fhobby3};
		session.setAttribute("frommatch",nm);
		session.setAttribute("hobbies",hobbies);
		
		//selfと反対のgenderを検索条件にする
		Search s = new Search();
		if (self_gender == 0) {
			s.setGender(1);
		}else if (self_gender == 1) {
			s.setGender(0);
		}
		//Getパラメータあり（検索からの遷移）なら内容を
		//なしならデフォルト値をserchインスタンスに格納
		int smin_age;
		int smax_age;
		String sarea;
		String shobby;
		String areanull="";
			
		String param1 = request.getParameter("minage");	
		//?minage=&maxage=&area=&hobby=
		if(param1==null || param1.length() == 0){
			smin_age = 18;
		}else if(param1.equals("99~")){		//99~はエラーかなを先に除外
			smin_age = 99;	
		}else {
	    	try{
	    		smin_age = Integer.parseInt(param1);	
	    	}catch (NumberFormatException e){
	    		smin_age = 18;
	    	}
		}	
		s.setMinage(smin_age);
		
		String param2 = request.getParameter("maxage");
		if (param2 == null || param2.length() == 0){
			smax_age = 999;
		}else{
			try{
				smax_age = Integer.parseInt(param2);
			}catch (NumberFormatException e){
				smax_age = 999;
			}
		}
		s.setMaxage(smax_age);
		//javascriptで除外対応しているが、念のため
		if(smin_age>smax_age) {
			response.sendRedirect("/matchingApp/Search.jsp");
			return;
		}	
			
		String param3 = request.getParameter("area");
		if (param3 == null || param3.length() == 0){
			sarea = "";
			areanull = " OR AREA IS NULL)";
		}else{
			sarea = param3;
		}
		s.setArea(sarea);
		
		String param4 = request.getParameter("hobby");
		if (param4 == null || param4.length() == 0){
			shobby = "";
		}else{
			shobby = param4;
		}
		s.setHobby(shobby);	
		
		//ProfDaoを引数s(serch型)で実行してproflistに格納
			//PROF型は(String id,String name,String gender,String area,int age, 
			//String photo,String[] hobby,String comment)
		//この時serchインスタンスのIDは初期値のまま未setです
		ArrayList<Prof> profList =new ArrayList<>();
		profList = pd.searchProf(s,areanull);
		
		//リストがカラの場合はエラーページに誘導→mainかsearchへ
		if(profList==null || profList.size()== 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profListError.jsp");
			dispatcher.forward(request, response);
		}
		
		//＝＝＝＝＝＝LIKE済みの人と照合＝＝＝＝＝＝
		//MATCHテーブルよりLIKEリスト作成
		MatchDao pd2 = new MatchDao();
		ArrayList<Match> likeList= pd2.likeList(self_id);
		if (likeList.size() > 0) {
			//likeListのToIDがあればprofListから除外
			for(Match m : likeList){
				for(Prof p : profList) {
					if(m.getTo_id().equals(p.getId())){
						profList.remove(profList.indexOf(p));
						break;
					}
				}
			}
		}
		//リストがカラの場合はエラーページに誘導→mainかsearchへ
		if(profList==null || profList.size()== 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profListError.jsp");
			dispatcher.forward(request, response);
		}
		//＝＝＝＝＝＝表示＝＝＝＝＝＝
		//カウンター(main.jspへフォワードする要素番号）を0にリセットしてスコープに保存
		int counter = 0;
		//HttpSession session = request.getSession();
		session.setAttribute("counter",counter);
		//リストをセッションスコープに保存
		//LISTにしてシャッフル（import必要）
		Collections.shuffle(profList);
		session.setAttribute("profList",profList);
		
		//EndListからの遷移か確認
		//スコープ保存
		String re = (String)request.getAttribute("re");
		request.setAttribute("re",re);
		
		//jspで0要素目データをリクエストスコープに入れて
		request.setAttribute("showProf",profList.get(counter));
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	
	//doPostはjspからの呼び出しでのみ実行
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//ボタン内容の確認（LIKE/NEXT)
		String ans = request.getParameter("ans");
		//セッションスコープのcounter(フォワードする要素番号）呼び出し
		HttpSession session = request.getSession();
		int counter = (int) session.getAttribute("counter");
		//セッションスコープのprofList呼び出し
		List<Prof> profList = (List<Prof>) session.getAttribute("profList");

		if (ans.equals("like")) {
			//自分のデータを取得
			Match match = (Match) session.getAttribute("frommatch");
			//相手のデータとあわせてデータベースに追加
			match.setTo_id(profList.get(counter).getId());
			match.setTo_name(profList.get(counter).getName());
			match.setTo_photo(profList.get(counter).getPhoto());
			match.setMatch("N");
			MatchDao pd = new MatchDao();
			pd.addList(match);
		}
		//counterは１アップして保存
		counter = counter + 1;
		session.setAttribute("counter",counter);
		//リストがなくなったときはEndListからもう一度Mainを呼び出し
		if (counter < profList.size()) {
			//次のデータをリクエストスコープに入れて
			request.setAttribute("showProf",profList.get(counter));
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request,response);
		}else {
			response.sendRedirect("/matchingApp/EndList");
		}
	}
}
	//リストがなくなったときの処理を確認してください
	//リストがなくなったときはMAINに戻る処理を追加
	//or リストが最後まで行った再読み込み用のJSPをつくる？
	//LIKEリストとの称号作業を追加してください
	


