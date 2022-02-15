
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProfDao;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメーターの取得 仮の値
		String id = request.getParameter("ID");
		String password = request.getParameter("PASSWORD");
		
		//ProfDaoでパスワード照合
		ProfDao pd = new ProfDao();
		String dbpw = pd.loginCheck(id);
			//System.out.println(dbpw);
			//System.out.println(password);
		if(password.equals(dbpw)) {
			//登録できた時の処理
			
			//timestapの登録…新規登録時も追加するかな？
			Timestamp tstmp = new Timestamp(System.currentTimeMillis());
			//Profdaoはnewせず使いまわし
			boolean result = pd.timestampUd(id,tstmp); //ProfDaoのtimestampに反映
			if (result == false) {
				System.out.println("timestampの登録に失敗");
			}
			// ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",id);
			// メイン画面にリダイレクト
			response.sendRedirect("/matchingApp/Main");

		}else {
		//System.out.println(dbpw); 該当なしはnull
		//登録されていないときの処理
		String msg = "ログインに失敗しました。正しいIDとパスワードを入力してください。";
		request.setAttribute("error", msg);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request,response);
		}
	}
}