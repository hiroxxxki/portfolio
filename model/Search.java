//DAO検索条件用のモデル（クラス型）
package model;

import java.io.Serializable;

public class Search implements Serializable{
	//USERUSERACCOUNT データベースの検索条件
	private String id;
	private int gender;		//int not null	0:男性　1：女性
	private String area;		//verchar(10)
	private int min_age;		//verchar(3)	
	private int max_age;		//verchar(3)	
	private String hobby;		//verchar(10)	
	
	//コンストラクタ
	public Search() {
	}
	public Search(int gender,String area,int min_age,int max_age,String hobby) {
		this.gender = gender;
		this.area = area;
		this.min_age = min_age;
		this.max_age = max_age;
		this.hobby = hobby;				
	}
	public void setId(String id) {this.id = id;}
	public void setGender(int gender) {this.gender = gender;}
	public void setArea(String area) {this.area = area;}
	public void setMinage(int min_age) {this.min_age = min_age;}
	public void setMaxage(int max_age) {this.max_age = max_age;}
	public void setHobby(String hobby) {this.hobby = hobby;}
	
	public String getId() {return this.id;}
	public int getGender() {return this.gender;}
	public String getArea() {return this.area;}
	public int getMinage() {return this.min_age;}
	public int getMaxage() {return this.max_age;}
	public String getHobby() {return this.hobby;}
}

