//package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Duplicate;


@WebServlet("/NewAccount")
public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String str = request.getParameter("register");

		if(str != null && str.length() != 0 && str.equals("new")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/newAccount.jsp");
					dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
	}
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// パラメーターの取得
		String id = request.getParameter("ID");
		String pw = request.getParameter("パスワード");
		// 入力値をチェック
		if( id != null && id.matches("[a-zA-Z_.0-9]{4,8}") && pw.matches("[a-zA-Z_.0-9]{6,8}")){
		}else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg","ID・パスワードに設定ができない表現が含まれています。ルールをご確認の上、再度入力ください。");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/newAccount.jsp");
			dispatcher.forward(request, response);
		}
		try {
			
			// 入力されたIDが「既に使われているかを見に行って！」と言ってる。
			Duplicate duplicate = new Duplicate() ;
			duplicate.check(id); 
			boolean a  = duplicate.check(id);
			if(a == true) {
				request.setAttribute("errorMsg", "そのIDは既に使われています。");
				RequestDispatcher dispatcher = request.getRequestDispatcher
						("/WEB-INF/jsp/newAccount.jsp");
				dispatcher.forward(request, response);
			}
			// セッションスコープにリクエストして
			// id と パスワードをセットしてる
			HttpSession session = request.getSession();
			session.setAttribute("checkId" , id );
			session.setAttribute("checkPass" , pw);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/accountCheck.jsp");
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/WEB-INF/jsp/newAccount.jsp");
			dispatcher.forward(request, response);
		}
	}
}








