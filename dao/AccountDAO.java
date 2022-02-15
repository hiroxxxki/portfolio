/* 新規に作成する。Testテーブルを担当するDAO。
	全レコードを取得するメソッドとレコードの追加を行うメソッドを持つ */

package dao ;

import java.sql.Connection ;		// DBMSへの接続や切断を行う
import java.sql.DriverManager ;		// DBMSへの接続準備を行う
import java.sql.PreparedStatement ;	// SQLの送信を行う
import java.sql.ResultSet ;			// DBMSから検索結果を受け取る
import java.sql.SQLException ;

import model.Test;


public class AccountDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa" ;
	private final String DB_PASS = "" ;
	
	// プロフィール作成SQL
	public boolean update(Test test) {
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(
			JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文を準備
			String sql = "SELECT * FROM USERACCOUNT WHERE ID = ?"; 
			// SQL文の内容を送信する
			PreparedStatement pStmt = conn.prepareStatement(sql) ;
			pStmt.setString(1,test.getId()) ;
			// SELECTを実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery() ;
			// idの確認して存在していたらif文実行
			if(rs.next()) {
			// UPDATE文を準備
			String sql2 = "UPDATE USERACCOUNT SET NAME = ?, "
					+ "GENDER = ? ,"
					+ "AREA = ? ,"
					+ "AGE = ? ,"
					+ "PHOTO = ? ,"
					+ "HOBBY1 = ? ,"
					+ "HOBBY2 = ? ,"
					+ "HOBBY3 = ? ,"
					+ "COMMENT = ? WHERE ID = ?" ;
			
			PreparedStatement pStt = conn.prepareStatement(sql2) ;
			pStt.setString(1, test.getName()) ;
			pStt.setInt(2,test.getGender()) ;
			pStt.setString(3, test.getArea()) ;
			pStt.setInt(4,test.getAge()) ;
			pStt.setString(5,test.getPhoto()) ;
			pStt.setString(6,test.getHobby1()) ;
			pStt.setString(7,test.getHobby2()) ;
			pStt.setString(8,test.getHobby3()) ;
			pStt.setString(9,test.getComment()) ;  
			pStt.setString(10,test.getId()) ;
			
			// UPDATEを実行し、結果票を取得-
			pStt.executeUpdate() ;
				return true;
			}else {
				return false;
			}
			}catch (SQLException e) {
			e.printStackTrace() ;
			return false ;
		}
	}

		// DBテーブルの最後に取得データをを追加
	public boolean create(Test test) {
		// データベースに接続
		try (Connection conn = DriverManager.getConnection(
			JDBC_URL, DB_USER, DB_PASS)) {
				
			// INSERT文の準備   (「？」には入力された内容が入る)
			// NAMEとGENDERはNOT NULLしてるから、あらかじめデフォルトの文字を入力しておく。
			String sql = "INSERT INTO USERACCOUNT(ID , PASSWORD , NAME , GENDER , MEMBERSHIP) "
					+ "VALUES(? , ? , 'user' , 3 , 'Y')" ;
			
			PreparedStatement pStmt = conn.prepareStatement(sql) ;
			// INSERT文中の[?]に使用する値を設定しSQLを完成
			pStmt.setString(1, test.getId()) ;
			pStmt.setString(2, test.getPassword()) ;
				
			// INSERT文を実行(resultには追加された行数が代入される)
				int result = pStmt.executeUpdate() ;
				
				// 入力文字が0でなければ完了!
				return true;
			} catch (SQLException e) {
				e.printStackTrace() ;
				return false ;
		}
	}

		// ログインアカウントを探すインスタンス
		public boolean check(String id) {
			String msg = ("そのIDは既に使われています。");
			// データベースへ接続
			try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)) {
				
				// SELECT文を準備
				String sql = "SELECT * FROM USERACCOUNT WHERE ID = ?" ; 
				// SQL文の内容を送信する
				PreparedStatement pStmt = conn.prepareStatement(sql) ;
				pStmt.setString(1, id) ;
				// SELECTを実行し、結果票を取得
				ResultSet rs = pStmt.executeQuery() ;
				// 取得したSELECT文の結果を取得した値をTestインスタンスに格納する。
				if (rs.next()) {  
					msg="重複";
					return true;
				}else {
					msg="ID OK";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return  false;
		}	
		
			// 写真のアップロードするSQL
		public int myPhoto(String id,String photo){
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
				return rs;
			}catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
	}








