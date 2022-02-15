//退会処理

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MatchDao;
import dao.ProfDao;


@WebServlet("/Membership")
public class Membership extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//IDをセッションスコープより取得
		HttpSession session = request.getSession();
		String self_id = (String) session.getAttribute("loginUser");
		// 退会処理
		//USERACCOUNTのMEMBERSHIPをNに変更
		ProfDao pd = new ProfDao();
		int re = pd.membership(self_id);
		String msg = null;
		if(re==1) {
			//処理成功
			HttpSession session2 = request.getSession(true);
			session2.invalidate();
			msg = "退会処理が完了しました";
		}else {
			//処理失敗
		}
		//Matchtテーブルから削除
		MatchDao md = new MatchDao();
		md.membershipcancel(self_id);
				
		// DBのMEMBERSHIPをNにしてセッション無効化 NewAccountに戻す
		request.setAttribute("msg",msg);
		System.out.println(msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/membershipcancel.jsp");
		dispatcher.forward(request, response);
	}
}
