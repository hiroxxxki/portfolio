//package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;

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

import dao.AccountDAO;
import dao.ProfDao;
import model.GetFindAll;
import model.Test;

@WebServlet("/Profile")
@MultipartConfig(
	location="/tmp/files",
	maxFileSize=1000000,
	maxRequestSize=1000000,
	fileSizeThreshold=1000000
)
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// パラメーターの取得
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");			// いったんStringで受け取る
		String name = request.getParameter("name");			// notnull
		String area = request.getParameter("area");
		String hobby1 = request.getParameter("hobby1");
		String hobby2 = request.getParameter("hobby2");
		String hobby3 = request.getParameter("hobby3");
		String photo = request.getParameter("photo");
		String comment = request.getParameter("comment");	// 50文字以内

		try {
			// 入力値のチェック
		if((name.length() <= 50 && name != null && name.length() != 0) && (gender != null && gender.length() != 0)
				&& (age != null && age.length() != 0 )) {
		}else {
			request.setAttribute("errorMsg","必須項目を埋めてください！");
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/filst.jsp");
			dispatcher.forward(request, response);
			return ;
		}
		if(comment.length() <= 50) {
		}else {
			request.setAttribute("errorMsg","コメントの文字数が長すぎます！");
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/filst.jsp");
			dispatcher.forward(request, response);
			return ;
		}
		}catch(Exception e) {
			request.setAttribute("errorMsg","必須項目を埋めてください！");
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/filst.jsp");
			dispatcher.forward(request, response);
			return ;
	}
		
			try {
				// Stringで受けてたageをintに変換
				int age2 = Integer.parseInt(request.getParameter("age"));
				int gender2 = Integer.parseInt(request.getParameter("gender")) ;// 0.男  1.女
				// セッションスコープに保存
				HttpSession session = request.getSession();
				// ログインユーザーのidを照合に使うためgetしとく
				String id = (String)session.getAttribute("loginUser");

				// 保存した値をtestインスタンスにセット
				Test test = new Test();
				test.setId(id);
				test.setName(name);
				test.setGender(gender2);
				test.setAge(age2);
				test.setArea(area);
				test.setHobby1(hobby1);
				test.setHobby2(hobby2);
				test.setHobby3(hobby3);
				test.setPhoto(photo);
				test.setComment(comment);

				// GetFindAllメソッドを実行
				GetFindAll getFindAll = new GetFindAll() ; // UPDATE文を実行させるDAOを読んでる
				getFindAll.update(test);
				//ProfDaoでid照合
				ProfDao pd = new ProfDao();
				//String dbpw = pd.loginCheck(id);
				request.getAttribute(id);
				if(id.equals(id)) {
					//登録できた時の処理
					//timestapの登録…新規登録時も追加するかな？
					Timestamp tstmp = new Timestamp(System.currentTimeMillis());
					boolean result = pd.timestampUd(id,tstmp); //ProfDaoのtimestampに反映
					if (result == false) {
						System.out.println("timestampの登録に失敗");
					}
				// メイン画面へリダイレクト
				 response.sendRedirect("/matchingApp/Main");
				}
			}catch(Exception e) {
				request.setAttribute("errorMsg","必須項目を埋めてください！");
				RequestDispatcher dispatcher = request.getRequestDispatcher
						("/WEB-INF/jsp/filst.jsp");
				dispatcher.forward(request, response);
		}
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//name属性がpictのファイルをPartオブジェクトとして取得
		// submitのボタンをリクエスト
		try {
		String btn =request.getParameter("btn");
		if( btn == null) {
		}
		HttpSession session = request.getSession();
		//IDをセッションスコープより取得
		String self_id = (String) session.getAttribute("loginUser");
		//name属性がPHOTOのファイルをPartオブジェクトとして取得
		Part part=request.getPart("PHOTO");
		//ファイル名を取得
		//String filename=part.getSubmittedFileName();//ie対応が不要な場合
		String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
		String extension=FilenameUtils.getExtension(filename);
		//アップロードするフォルダ
		String path=getServletContext().getRealPath("/userpic");
		String fn =path+File.separator+ self_id +"."+ extension;
		String pn = self_id +"."+ extension;
		//実際にファイルが保存されるパス確認
		System.out.println(path);
		//C:\Users\class\Desktop\matchingApp\.metadata\.plugins
		//\org.eclipse.wst.server.core\tmp1\wtpwebapps\matchingApp/userpic
		System.out.println(fn);
		//書き込み
		part.write(fn);
		
		// 写真のアップロードに関するDAO
		AccountDAO dao = new AccountDAO();
		int result = dao.myPhoto(self_id , pn);
		
		if(result == 1) {
			String a = self_id +"."+ extension;
			request.setAttribute("a", a);
		}
		else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg","その画像は使えません");
		}
		RequestDispatcher rd=request.getRequestDispatcher
				("/WEB-INF/jsp/filst.jsp");
		rd.forward(request, response);
		}catch(Exception e) {
			request.setAttribute("errorMsg","画像の容量が大きすぎます");
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/filst.jsp");
			dispatcher.forward(request, response);
	}
	}
}
	
	
	
	