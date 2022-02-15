//Mainでリストがなくなった時の対応

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EndList")
public class EndList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setAttribute("re", "nextlist");
	    RequestDispatcher dispatch = request.getRequestDispatcher("/Main");
	    dispatch.forward(request, response);
		return;
	}
}
