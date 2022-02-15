
//MATCHテーブルからArrayListを作成するDAO
//https://sukkiri.jp/technologies/ides/eclipse/h2jdbc_install_web.html?ssj=13
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Match;

public class MatchDao {
	//データベース接続に使用する情報
	private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER ="sa";
	private final String DB_PASS = "";
	
	//idがLIKEした人一覧を取得
	public ArrayList<Match> likeList(String sid){
		ArrayList<Match> likeList = new ArrayList<>();
		
		//セレクト文の準備
		String sql =  "SELECT * FROM MATCH WHERE FROM_ID = ?";
		
		//データベース接続
		try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,sid);
			//セレクトを実行
			ResultSet rs =pStmt.executeQuery();
			//セレクト文の結果をArrayListに格納
			while (rs.next()){
				int id = rs.getInt("ID");
				String from_id = rs.getString("FROM_ID");
				String from_name = rs.getString("FROM_NAME");
				String from_photo = rs.getString("FROM_PHOTO");
				String to_id = rs.getString("TO_ID");
				String to_name = rs.getString("TO_NAME");
				String to_photo = rs.getString("TO_PHOTO");
				String match = rs.getString("MATCH");
				Match m = new Match(id,from_id,from_name,from_photo,
						to_id,to_name,to_photo,match);
				likeList.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	return likeList;
	}
	
	//idにLIKEした人一覧を取得
	public ArrayList<Match> tolikeList(String sid){
		ArrayList<Match> tolikeList = new ArrayList<>();
		
		//セレクト文の準備
		String sql =  "SELECT * FROM MATCH WHERE TO_ID = ?";
		
		//データベース接続
		try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,sid);
			//セレクトを実行
			ResultSet rs =pStmt.executeQuery();
			//セレクト文の結果をArrayListに格納
			while (rs.next()){
				int id = rs.getInt("ID");
				String from_id = rs.getString("FROM_ID");
				String from_name = rs.getString("FROM_NAME");
				String from_photo = rs.getString("FROM_PHOTO");
				String to_id = rs.getString("TO_ID");
				String to_name = rs.getString("TO_NAME");
				String to_photo = rs.getString("TO_PHOTO");
				String match = rs.getString("MATCH");
				Match m = new Match(id,from_id,from_name,from_photo,
						to_id,to_name,to_photo,match);
				tolikeList.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	return tolikeList;
	}
	
	//idがLIKEした人かつMatchYの一覧を取得
	public ArrayList<Match> matchList(String sid){
		ArrayList<Match> matchList = new ArrayList<>();
		
		//セレクト文の準備
		String sql =  "SELECT * FROM MATCH WHERE FROM_ID = ? AND MATCH = 'Y'";
		
		//データベース接続
		try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,sid);
			//セレクトを実行
			ResultSet rs =pStmt.executeQuery();
			//セレクト文の結果をArrayListに格納
			while (rs.next()){
				int id = rs.getInt("ID");
				String from_id = rs.getString("FROM_ID");
				String from_name = rs.getString("FROM_NAME");
				String from_photo = rs.getString("FROM_PHOTO");
				String to_id = rs.getString("TO_ID");
				String to_name = rs.getString("TO_NAME");
				String to_photo = rs.getString("TO_PHOTO");
				String match = rs.getString("MATCH");
				Match m = new Match(id,from_id,from_name,from_photo,
						to_id,to_name,to_photo,match);
				matchList.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	return matchList;
	}
	
	//insert	
	public boolean addList(Match match){
		//INSERT文の準備
		String sql =  "INSERT INTO MATCH (FROM_ID,FROM_NAME,FROM_PHOTO,TO_ID,TO_NAME,TO_PHOTO)"
				+ " VALUES (?,?,?,?,?,?)";
		
		//データベース接続
		try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,(String) match.getFrom_id());
			pStmt.setString(2,(String) match.getFrom_name());
			pStmt.setString(3,(String) match.getFrom_photo());
			pStmt.setString(4,(String) match.getTo_id());
			pStmt.setString(5,(String) match.getTo_name());
			pStmt.setString(6,(String) match.getTo_photo());
			//実行
			int result =pStmt.executeUpdate();
			if (result !=1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	return true;
	}
	
	//Match(Y/N)の更新	
	public boolean updateMatch(int id,String ismatch){
		//UPDATE文の準備
		String sql =  "UPDATE MATCH SET MATCH = ? WHERE ID = ?";	
		//データベース接続
		try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,ismatch);
			pStmt.setInt(2,id);
			//実行
			int result =pStmt.executeUpdate();
			if (result !=1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
				return false;
		}
		return true;
	}

	//マイページ変更時の一括反映(col=NAME Or PHOTO)
	public boolean updateMatch(String sid,String col,String after){
	//データベース接続
	try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		String sql1 =  "UPDATE MATCH SET FROM_"+ col +" = ? WHERE FROM_ID = ?";	
		PreparedStatement pStmt1 = conn.prepareStatement(sql1);
		//SELECT文中の?に使用するIDを設定しSQL完成
		pStmt1.setString(1,after);
		pStmt1.setString(2,sid);
		//実行
		int result1 =pStmt1.executeUpdate();
		
		String sql2 =  "UPDATE MATCH SET TO_"+ col +" = ? WHERE TO_ID = ?";	
		PreparedStatement pStmt2 = conn.prepareStatement(sql2);
		pStmt2.setString(1,after);
		pStmt2.setString(2,sid);
		//実行
		int result2 =pStmt2.executeUpdate();

		
	}catch(SQLException e) {
		e.printStackTrace();
			return false;
	}
	return true;
	}
	
	//退会時のLIKEデータ削除
	public void membershipcancel(String sid){
	//データベース接続
	try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		String sql1 =  "DELETE FROM MATCH WHERE FROM_ID = ?";	
		PreparedStatement pStmt1 = conn.prepareStatement(sql1);
		//SELECT文中の?に使用するIDを設定しSQL完成
		pStmt1.setString(1,sid);
		//実行
		int result1 =pStmt1.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
	String sql2 =  "DELETE FROM MATCH WHERE TO_ID = ?";	
	PreparedStatement pStmt2 = conn.prepareStatement(sql2);
	pStmt2.setString(1,sid);
	//実行
	int result2 =pStmt2.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}
}