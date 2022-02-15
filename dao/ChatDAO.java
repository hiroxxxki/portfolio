//======K
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Chat;

public class ChatDAO {

	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public List<Chat> sendMsg(Chat ch) {
		String key = ch.getId_a();
		List<Chat> chatList = new ArrayList<>();

		try (

				Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO CHAT(NAME_SEND,NAME_CATCH,ID_SEND,ID_CATCH,MESSAGE,TIME) VALUES(?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ch.getName_a());
			pStmt.setString(2, ch.getName_b());
			pStmt.setString(3, ch.getId_a());
			pStmt.setString(4, ch.getId_b());
			pStmt.setString(5, ch.getMsg());
			pStmt.setString(6, ch.getTime());

			pStmt.executeUpdate();

			//			sql = "SELECT NAME_SEND,NAME_CATCH,ID_SEND,ID_CATCH,MESSAGE,TIME FROM CHAT  WHERE ID_SEND=? OR ID_CATCH = ?";

			sql = "SELECT NAME_SEND,NAME_CATCH,ID_SEND,ID_CATCH,MESSAGE,TIME "
					+ "FROM CHAT WHERE (ID_SEND=? AND ID_CATCH=?) OR ( ID_SEND=? AND ID_CATCH=?) ORDER BY TIME";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ch.getId_a());
			pStmt.setString(2, ch.getId_b());
			pStmt.setString(3, ch.getId_b());
			pStmt.setString(4, ch.getId_a());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				String id_a = rs.getString("ID_SEND");
				String id_b = rs.getString("ID_CATCH");
				String msg = rs.getString("MESSAGE");
				String name_a = rs.getString("NAME_SEND");
				String name_b = rs.getString("NAME_CATCH");
				String datetime = rs.getString("TIME");
				boolean align = false;
				String date = "";
				String time ="";
				
				if(datetime.length() !=0) {
					time = datetime.substring(11,16);
					date = datetime.substring(5,11);

					if(date.charAt(0) == '0') {
						date = date.substring(1);
					}
					date=date.replace("-", "月");
					date=date.replace("T", "日");
					}
				
				

				if (id_a.equals(ch.getId_a())) {
					align = true;
					Chat chat = new Chat(id_a, id_b, name_a, name_b, msg, date, time, align);
					chatList.add(chat);
				} else {
					Chat chat = new Chat(id_b, id_a, name_b, name_a, msg, date, time, align);
					chatList.add(chat);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return chatList;

	}

	public List<Chat> findMsg(Chat ch) {
		List<Chat> chatList = new ArrayList<>();

		try (

				Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT NAME_SEND,NAME_CATCH,ID_SEND,ID_CATCH,MESSAGE,TIME "
					+ "FROM CHAT WHERE (ID_SEND=? AND ID_CATCH=?) OR ( ID_SEND=? AND ID_CATCH=?) ORDER BY TIME";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ch.getId_a());
			pStmt.setString(2, ch.getId_b());
			pStmt.setString(3, ch.getId_b());
			pStmt.setString(4, ch.getId_a());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				String id_a = rs.getString("ID_SEND");
				String id_b = rs.getString("ID_CATCH");
				String msg = rs.getString("MESSAGE");
				String name_a = rs.getString("NAME_SEND");
				String name_b = rs.getString("NAME_CATCH");
				String datetime = rs.getString("TIME");
				String date="";
				String time ="";
				
				if(datetime.length() !=0) {
					time = datetime.substring(11,16);
					date = datetime.substring(5,11);
					

					if(date.charAt(0) == '0') {
						date = date.substring(1);
					}
					date=date.replace("-", "月");
					date=date.replace("T", "日");
					}
				boolean align = false;
				

				if (id_a.equals(ch.getId_a())) {
					align = true;
					Chat chat = new Chat(id_a, id_b, name_a, name_b, msg, date,time, align);
					chatList.add(chat);
				} else {
					Chat chat = new Chat(id_b, id_a, name_b, name_a, msg, date,time, align);
					chatList.add(chat);
				}
			}
		} catch (SQLException e) {
			return null;
		}

		return chatList;
	}
}

//	不要品
//	public boolean create(Chat chat) {
//		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//			String sql = "INSERT INTO MUTTER(NAME,TEXT) VALUES(?,?)";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//
//			pStmt.setString(1, chat.getId_a());
//			pStmt.setString(1, chat.getId_b());
//			pStmt.setString(1, chat.getName_a());
//			pStmt.setString(1, chat.getName_b());
//			pStmt.setString(2, chat.getMsg());
//
//			int result = pStmt.executeUpdate();
//			if (result != 1) {
//				return false;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//
//	}
