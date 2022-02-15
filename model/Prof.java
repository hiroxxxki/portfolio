//main表示用アレイリストのモデル（クラス型）
package model;

import java.io.Serializable;

public class Prof implements Serializable{
	//USERUSERACCOUNT データベースのパスワード以外
	private String id;		//char(8)not null
	private String name;		//verchar(10)not null	
	private int gender;		//int not null	0:男性　1：女性
	private String area;		//verchar(10)
	private int age;		//verchar(3)	
	private String photo;		//verchar(30)
	private String hobby1;		//verchar(10)
	private String hobby2;		//verchar(10)	
	private String hobby3;		//verchar(10)	
	private String comment;		//verchar(50)
	
	//コンストラクタ
	public Prof() {;}
	public Prof(String id,String name,int gender,String area,int age, 
			String photo,String hobby1,String hobby2,String hobby3,String comment) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.area = area;
		this.age = age;
		this.photo = photo;	
		this.hobby1 = hobby1;
		this.hobby2 = hobby2;
		this.hobby3 = hobby3;
		this.comment = comment;
	}
	public void setId(String id) {this.id=id;}
	public void setName(String name) {this.name=name;}
	public void setGender(int gender) {this.gender=gender;}
	public void setArea(String area) {this.area=area;}
	public void setAge(int age) {this.age=age;}
	public void setPhoto(String photo) {this.photo=photo;}
	public void setHobby1(String hobby1) {this.hobby1=hobby1;}
	public void setHobby2(String hobby2) {this.hobby2=hobby2;}
	public void setHobby3(String hobby3) {this.hobby3=hobby3;}
	public void setComment(String comment) {this.comment=comment;}
	
	public String getId() {return this.id;}
	public String getName() {return this.name;}
	public int getGender() {return this.gender;}
	public String getArea() {return this.area;}
	public int getAge() {return this.age;}
	public String getPhoto() {return this.photo;}
	public String getHobby1() {return this.hobby1;}
	public String getHobby2() {return this.hobby2;}
	public String getHobby3() {return this.hobby3;}
	public String getComment() {return this.comment;}
}
//例えば、System.currentTimeMills()メソッドを使って、現在の時刻をTimestamp型で取得できます。
//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//https://techacademy.jp/magazine/27367
