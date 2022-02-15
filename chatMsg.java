//======K
//ここでChat1よりデータを受け取る

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChatDAO;
import model.Chat;
import model.Match;
import model.TimeGet;

/**
 * Servlet implementation class chatMsg
 */
@WebServlet("/chatMsg")
public class chatMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		//フォームから値を取得jsp
		
		int element = Integer.parseInt(request.getParameter("elem"));
		HttpSession session = request.getSession();
		ArrayList<Match> matchList = (ArrayList<Match>) session.getAttribute("matchlist");
		Match m = matchList.get(element);
		session.setAttribute("match",m);
		
		String id_a = m.getFrom_id();		//from_id
		String id_b = m.getTo_id();		//to_id
		String name_a = m.getFrom_name();		//from_name
		String name_b = m.getTo_name();		//to_name
		String msg = request.getParameter("msg");
		String time = new TimeGet().getNow();
		//String picture = request.getParameter("path");
		String picture = request.getContextPath()+"/userpic/"+m.getTo_photo();		//to_photo
		
		//		if (msg == null || msg.length() == 0 ) {
		//			//もとの画面にフォワード
		//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/chat.jsp");
		//			dispatcher.forward(request, response);
		//		}

		Chat chat = new Chat(id_a, id_b, name_a, name_b, "msg", new TimeGet().getNow());
		ChatDAO cd = new ChatDAO();
		List<Chat> chatList = new ArrayList<>();

		chatList = cd.findMsg(chat);
		
		
		
		//リクエストスコープに保存
		request.setAttribute("chat", chatList);
		request.setAttribute("pict", picture);
		request.setAttribute("match", m);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/chatMain.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		//フォームから値を取得jsp
		HttpSession session = request.getSession();
		Match m =(Match) session.getAttribute("match");
		
		String id_a = m.getFrom_id();		//from_id
		String id_b = m.getTo_id();		//to_id
		String name_a = m.getFrom_name();		//from_name
		String name_b = m.getTo_name();		//to_name
		String msg = request.getParameter("msg");
		String time = new TimeGet().getNow();
		String picture = request.getContextPath()+"/userpic/"+m.getTo_photo();		
		
		if (msg == null || msg.length() == 0) {
			//もとの画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/chatMain.jsp");
			dispatcher.forward(request, response);
		} else {

			Chat chat = new Chat(id_a, id_b, name_a, name_b, msg, time);
			ChatDAO cd = new ChatDAO();
			List<Chat> chatList = new ArrayList<>();

			chatList = cd.sendMsg(chat);

			//リクエストスコープに保存
			request.setAttribute("chat", chatList);
			request.setAttribute("pict", picture);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/chatMain.jsp");
			dispatcher.forward(request, response);

			//		 Msg内容 
			//		 自分account
			//		 相手account
			//		 時間

		}

	}
}