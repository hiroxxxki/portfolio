//match.jspの作成
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

import dao.MatchDao;
import model.Match;

@WebServlet("/Chat1")
public class Chat1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//String self_id = "tstid001";
		HttpSession session = request.getSession();
		String self_id = (String) session.getAttribute("loginUser");
		
		//MatchDaoでLIKEテーブルのMatchフラグ更新
			//LIKEテーブルでYになっているもの以外の２つのアレイリストを取得
		MatchDao md = new MatchDao();
		List<Match> likeList= md.likeList(self_id);
		List<Match> tolikeList= md.tolikeList(self_id);	
		//新規マッチ分(listのtoid=tolistのfromid)の
		//両リストの該当行（id)のMatch列をYに更新
		
		for (Match fromlike : likeList) {
			for (Match tolike : tolikeList) {
				if(fromlike.getTo_id().equals(tolike.getFrom_id())){
					if(fromlike.getMatch()==null || fromlike.getMatch().equals("N")){
						md.updateMatch(fromlike.getId(),"Y");
						md.updateMatch(tolike.getId(),"Y");
					}
				break;
				}
			}
		}
				
		//tolikeかつMatchYのリストを作成=matchList
		ArrayList<Match> matchList= md.matchList(self_id);
		//セッションスコープに格納
		session.setAttribute("matchlist",matchList);
		//match.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/match.jsp");
		dispatcher.forward(request, response);
	}
//newのフラグについては相談の上jsp処理できるか確認→河

}