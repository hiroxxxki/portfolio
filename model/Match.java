//DAO検索用のモデル（クラス型）
package model;

import java.io.Serializable;

public class Match implements Serializable {
	//MATCH データベースの内容
	private int id;		//int AUTO_INCREMENT
	private String from_id;		//char(8)
	private String from_name;	//varchar(10)
	private String from_photo;		//varchar(30)
	private String to_id;		//char(8)
	private String to_name;	//varchar(10)
	private String to_photo;		//varchar(30)
	private String match;		//char(1)Y/N
	
	//コンストラクタ
	public Match() { ;}
	public Match(String from_id) {
		this.from_id = from_id;
	}
	public Match(String from_id,String from_name,String from_photo) {
		this.from_id = from_id;
		this.from_name = from_name;
		this.from_photo = from_photo;
	}
	public Match(int id, String from_id,String from_name,String from_photo,
			String to_id,String to_name,String to_photo,String match) {
		this.id = id;
		this.from_id = from_id;
		this.from_name = from_name;
		this.from_photo = from_photo;
		this.to_id = to_id;
		this.to_name = to_name;
		this.to_photo = to_photo;
		this.match = match;
	}
	public Match(int id, String from_id,String from_name,String from_photo,
			String to_id,String to_name,String to_photo) {
		this.id = id;
		this.from_id = from_id;
		this.from_name = from_name;
		this.from_photo = from_photo;
		this.to_id = to_id;
		this.to_name = to_name;
		this.to_photo = to_photo;
	}

	//setter
	public void setFrom_id(String from_id){ this.from_id = from_id;}
	public void setFrom_name(String from_name){ this.from_name = from_name;}
	public void setFrom_photo(String from_photo){ this.from_photo = from_photo;}
	public void setTo_id(String to_id){ this.to_id = to_id;}
	public void setTo_name(String to_name){ this.to_name = to_name;}
	public void setTo_photo(String to_photo){ this.to_photo = to_photo;}
	public void setMatch(String match) {this.match = match;}
	
	//getter
	public String getFrom_id() {return this.from_id;}
	public String getFrom_name() {return this.from_name;}
	public String getFrom_photo() {return this.from_photo;}
	public String getTo_id() {return this.to_id;}
	public String getTo_name() {return this.to_name;}
	public String getTo_photo() {return this.to_photo;}
	public String getMatch() {return this.match;}
	public int getId() {return this.id;}

}
