

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//セッション無効の処理をしてNewAccountに戻す
		HttpSession session = request.getSession(true);
		session.invalidate();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/start.jsp");
		dispatcher.forward(request, response);
	}
}
