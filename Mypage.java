//Mypageの表示とDB更新用
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import dao.MatchDao;
import dao.ProfDao;
import model.Prof;

@WebServlet("/Mypage")
@MultipartConfig(
		location="/tmp/files",
		maxFileSize=1000000,
		maxRequestSize=1000000,
		fileSizeThreshold=1000000
		)
public class Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//DAOで登録済みデータを作成
//IDをセッションスコープより取得
		HttpSession session = request.getSession();
		String self_id = (String) session.getAttribute("loginUser");
		//String self_id ="tstid033";
		
		//mainからの初回登録離脱者の場合、アラート情報を受け取り、再度リクエストスコープ保存
		String alert = (String)request.getAttribute("alert");
		if(alert==null) {
		}else	if (alert.equals("Y")) {
		request.setAttribute("alert",alert);
		}
				
		//自分のgender,id,name,photoを取得
		ProfDao pd = new ProfDao();
		Prof selfprof = pd. myProf(self_id);
		if (selfprof == null) {
			System.out.println("ユーザーIDがDBに存在しません");
		}
		//リクエストスコープに保存してjspを開く
		request.setAttribute("myprof", selfprof);
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
		
	}
	//doPostはjspからの呼び出しでのみ実行
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//IDをセッションスコープより取得
		HttpSession session = request.getSession();
		String self_id = (String) session.getAttribute("loginUser");
		//String self_id ="tstid033";
		
		//押されたボタンの名前で分岐
		request.setCharacterEncoding("utf-8");
		try {
			String btn =request.getParameter("btn");

			if(btn==null) {
				btn="photo";
			}
		switch (btn){
		//valueがStringのもの
		case "NAME":
			//MATCHテーブルの反映作業追加
			MatchDao md = new MatchDao();
			md.updateMatch(self_id,"NAME",(String) request.getParameter(btn));
		case "AREA":
		case "HOBBY1":
		case "HOBBY2":
		case "HOBBY3":
		case "COMMENT":
			//画像以外Stringまとめ処理（値チェックはjavasciptで）
			//nameのみ notnull,"","user"をはぶくこと！
			String svalue = (String) request.getParameter(btn);
			ProfDao spd = new ProfDao();
			spd. updateString(self_id,btn,svalue);
			//リダイレクトマイページを再読み込み
			response.sendRedirect("/matchingApp/Mypage");
			break;
		//valueがintまとめ処理
		case "AGE":
		case "GENDER":
			//画像以外int
			int ivalue =Integer.parseInt(request.getParameter(btn));
			ProfDao ipd = new ProfDao();
			ipd. updateInt(self_id,btn,ivalue);
			//リダイレクトマイページを再読み込み
			response.sendRedirect("/matchingApp/Mypage");
			break;
		case "photo":
			//name属性がPHOTOのファイルをPartオブジェクトとして取得
			Part part=request.getPart("PHOTO"); //分岐が必要な際はpart.getSize()==0
			//ファイル名を取得
			//String filename=part.getSubmittedFileName();//ie対応が不要な場合
			String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
			String extension=FilenameUtils.getExtension(filename);
			//アップロードするフォルダ
			String path=getServletContext().getRealPath("/userpic");
			String fn =path+File.separator+ self_id +"."+ extension;
			//実際にファイルが保存されるパス確認
			System.out.println(path);
			//C:\Users\class\Desktop\matchingApp\.metadata\.plugins
			//\org.eclipse.wst.server.core\tmp1\wtpwebapps\matchingApp/userpic
			System.out.println(extension);
			System.out.println(fn);
			//書き込み
			part.write(fn);
			//DBのphotoを更新
			ProfDao pd = new ProfDao();
			pd. myPhoto(self_id,self_id +"."+ extension);
			//MATCHテーブルの反映作業追加
			MatchDao md2 = new MatchDao();
			md2.updateMatch(self_id,"PHOTO",self_id +"."+ extension);
			//リダイレクトマイページを再読み込み
			response.sendRedirect("/matchingApp/Mypage");
			break;
		}	
		}catch(Exception e) {
			response.sendRedirect("/matchingApp/Mypage");
			return;
		}	
	}

}
