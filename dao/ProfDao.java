
//USERACCOUNTテーブルからArrayListを作成するDAO
//h2-2.0.204.jarをtomcatに追加する必要があります
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Prof;
import model.Search;

public class ProfDao{

	//接続設定
	private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER ="sa";
	private final String DB_PASS = "";

//ログインパスワード照合用
	public String loginCheck(String sid){
		String dbpw = null; 
		//SQL句
		String sql = "SELECT * FROM  USERACCOUNT WHERE ID = ? AND MEMBERSHIP = 'Y'";
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,sid);
			//セレクトを実行 primarykeyなので１データのみ
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()){	
				//セレクト文の結果を格納
				dbpw = rs.getString("PASSWORD");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return dbpw;
	}
	
//timestamp登録用
	public boolean timestampUd(String sid,Timestamp tstmp){
		boolean result = false;
		//SQL句
		String sql = "UPDATE USERACCOUNT SET LOGIN_TIME = ? WHERE ID = ?";
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用する値を設定しSQL完成
			pStmt.setTimestamp(1,tstmp);
			pStmt.setString(2,sid);
			//セレクトを実行 primarykeyなので１データのみ
			int rs =pStmt.executeUpdate(); //処理された行数が返る
			if (rs == 1) {
				result = true;
				return result;
			}else {
				result = false;
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
	
//idから自分のgenderを取得するメソッド
	public Prof selfValues(String sid){
		Prof selfprof =new Prof();
		//SQL句
		String sql = "SELECT * FROM  USERACCOUNT WHERE ID = ?";
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,sid);
			//セレクトを実行
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()){	
				//セレクト文の結果を格納
				selfprof.setGender(rs.getInt("GENDER"));
				selfprof.setAge(rs.getInt("AGE"));
				selfprof.setId(rs.getString("ID"));
				selfprof.setName(rs.getString("NAME"));
				selfprof.setPhoto(rs.getString("PHOTO"));
				selfprof.setHobby1(rs.getString("HOBBY1"));
				selfprof.setHobby2(rs.getString("HOBBY2"));
				selfprof.setHobby3(rs.getString("HOBBY3"));
			}
		}catch (SQLException e) {	
			e.printStackTrace();
			return null;
		}
		return selfprof;
	}	
	
//idからmypageデータを取得するメソッド
	public Prof myProf(String sid){
		Prof selfprof =new Prof();
		//SQL句
		String sql = "SELECT * FROM  USERACCOUNT WHERE ID = ?";
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,sid);
			//セレクトを実行
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()){	
				//セレクト文の結果を格納
				selfprof.setId(rs.getString("ID"));
				selfprof.setName(rs.getString("NAME"));
				selfprof.setGender(rs.getInt("GENDER"));
				selfprof.setArea(rs.getString("AREA"));
				selfprof.setAge(rs.getInt("AGE"));
				selfprof.setPhoto(rs.getString("PHOTO"));
				selfprof.setHobby1(rs.getString("HOBBY1"));
				selfprof.setHobby2(rs.getString("HOBBY2"));
				selfprof.setHobby3(rs.getString("HOBBY3"));
				selfprof.setComment(rs.getString("COMMENT"));
			}
			
		}catch (SQLException e) {	
			e.printStackTrace();
			return null;
		}
		return selfprof;
	}	
	
	//main用proflist作成用メソッド
	public ArrayList<Prof> searchProf(Search search, String areanull){
		Search s = (Search) search;
		if (s == null) {
			throw new IllegalArgumentException("引数serchがnullです");
		}
		ArrayList<Prof> profList = new ArrayList<>();
		String kakko = "";
		if (areanull.length()>0) {
			kakko="(";
		}
		//SQL文フォーマット 年齢、エリア、趣味にnullを含むかどうか（空欄登録はnullがはいるはず？）
		//引数searchの扱いも再確認
		String sql = "SELECT * FROM USERACCOUNT"
			+ " WHERE GENDER = ? "		//gender　notnull 0,1,3
			+ "AND AGE BETWEEN ? AND ? AND "	 //ge 18-99 null登録はできない
			+ kakko
			+ "AREA LIKE ?"	//searchに項目あり　通常はnullも表示する
			+  areanull
			+ "	AND (HOBBY1,HOBBY2,HOBBY3) LIKE ?"	//searchに項目あり　通常はnullも表示する
			+" AND MEMBERSHIP = 'Y' ORDER BY LOGIN_TIME DESC";	//notnul
	
		//データベース接続
		try{Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			//SELECT文中の?に使用する値を設定しSQL完成
			//性別
			pStmt.setInt(1, s.getGender());		//not null
			pStmt.setInt(2, s.getMinage());		//not null
            pStmt.setInt(3, s.getMaxage());		//not null
            pStmt.setString(4, "%" + s.getArea() + "%");
            pStmt.setString(5, "%" + s.getHobby() + "%");
			//セレクトを実行
			ResultSet rs =pStmt.executeQuery();
			
			//セレクト文の結果をArrayListに格納
			while (rs.next()){
				String id = rs.getString("ID");	//char(8)not null
				String name = rs.getString("NAME");		//verchar(10)not null	
				int gender = rs.getInt("GENDER");		//int not null	0:男性　1：女性
				String area = rs.getString("AREA");		//verchar(10)
				int age = rs.getInt("AGE");		//verchar(3)	
				String photo = rs.getString("PHOTO");		//verchar(30)
				String hobby1 = rs.getString("HOBBY1");		//verchar(10)	
				String hobby2 = rs.getString("HOBBY2");		//verchar(10)
				String hobby3 = rs.getString("HOBBY3");		//verchar(10)
				String comment = rs.getString("COMMENT");		//verchar(50)
				Prof prof= new Prof(id,name,gender,area,age,photo,hobby1,hobby2,hobby3,comment);	
				profList.add(prof);
			}
			return profList;
		}catch (SQLException e) {	
			e.printStackTrace();
			return null;
		}
	}
	
	//画像名更新用
		public void myPhoto(String id,String photo){
			//SQL句
			String sql = "UPDATE USERACCOUNT SET PHOTO = ? WHERE ID = ?";
			//データベース接続
			try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
				PreparedStatement pStmt = conn.prepareStatement(sql);
				//SELECT文中の?に使用するIDを設定しSQL完成
				pStmt.setString(1,photo);
				pStmt.setString(2,id);
				//セレクトを実行 primarykeyなので１データのみ
				int rs = pStmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	//Srting更新用
	public void updateString(String id,String btn,String value){
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			PreparedStatement pStmt = conn.prepareStatement("UPDATE USERACCOUNT SET "
					+ btn +" = ? WHERE ID = ?");
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,value);
			pStmt.setString(2,id);
			//セレクトを実行 primarykeyなので１データのみ
			pStmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//int更新用
	public void updateInt(String id,String btn,int value){
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			PreparedStatement pStmt =  conn.prepareStatement("UPDATE USERACCOUNT SET "
					+ btn +" = ? WHERE ID = ?");
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setInt(1,value);
			pStmt.setString(2,id);
			//セレクトを実行 primarykeyなので１データのみ
			pStmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
}

	//退会処理用
	public int membership(String id){
		//SQL句
		String sql = "UPDATE USERACCOUNT SET MEMBERSHIP = 'N' WHERE ID = ?";
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			PreparedStatement pStmt =  conn.prepareStatement(sql);
			//SELECT文中の?に使用するIDを設定しSQL完成
			pStmt.setString(1,id);
			//セレクトを実行 primarykeyなので１データのみ
			int re = pStmt.executeUpdate();
			return re;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
}
}
	