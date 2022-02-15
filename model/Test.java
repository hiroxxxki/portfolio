package model;

import java.io.Serializable;

	// ログインに必要なレコードを表すEnity
public class Test implements Serializable{
	private String id ;
	private String password ;
	private String name ;
	private int gender ;
	private String area ;
	private int age ;
	private String photo ;
	private String hobby1 ;
	private String hobby2 ;
	private String hobby3 ;
	private String comment ;
	private String login_time ;
	private String menbership ;
	
	public Test() {
		this.id = id ;
		this.password = password ;
		this.name = name ;
		this.gender = gender ;
		this.area = area ;
		this.age = age ;
		this.photo = photo ;
		this.hobby1 = hobby1 ;
		this.hobby2 = hobby2 ;
		this.hobby3 = hobby3 ;
		this.comment = comment ;
		this.login_time = login_time ;	
		this.menbership = menbership ;
	}
	
	public Test(String id , String password , String name , int gender , String area ,
			int age , String photo , String hobby1 , String hobby2 , String hobby3 , 
			String comment , String login_time , String menbership) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.area = area;
		this.age = age;
		this.photo = photo;
		this.hobby1 = hobby1;
		this.hobby2 = hobby2;
		this.hobby3 = hobby3;
		this.comment = comment;
		this.login_time = login_time;
		this.menbership = menbership ;
	}
	public Test(String id , String password , String name , int gender) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setHobby1(String hobby1) {
		this.hobby1 = hobby1;
	}
	public void setHobby2(String hobby2) {
		this.hobby2 = hobby2;
	}
	public void setHobby3(String hobby3) {
		this.hobby3 = hobby3;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public void setMenbership(String menbership) {
		this.menbership = menbership ;
	}
	public String getId() { return id ; }
	public String getPassword () { return password ; }
	public String getName () { return name ; }
	public int getGender () { return gender ; }
	public String getArea() { return area ; }
	public int getAge() { return age ; }
	public String getPhoto() { return photo ; }
	public String getHobby1() { return hobby1 ; }
	public String getHobby2() { return hobby2 ; }
	public String getHobby3() { return hobby3 ; }
	public String getComment() { return comment ; }
	public String getLogin_time() { return login_time ; }
	public String getMenbership() { return menbership ; }
}


    
    
    
    
    
    