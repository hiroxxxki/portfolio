//package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PostTestLogic;
import model.Test;
@WebServlet("/AccountCheck")
public class AccountCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AccountCheck() {
        super();

    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("checkId" );
		String pw = (String)session.getAttribute("checkPass" );
		System.out.println(id);
		
		Test test = new Test();
		test.setId(id);
		test.setPassword(pw);
		
		System.out.println(id);
		PostTestLogic postTestLogic = new PostTestLogic();
		postTestLogic.execute(test);
		// loginUserにidを保存している
		session.setAttribute("loginUser" , id);
		
		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher
				("/WEB-INF/jsp/filst.jsp");
		dispatcher.forward(request, response);
	}
}
