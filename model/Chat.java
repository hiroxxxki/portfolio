package model;

import java.io.Serializable;

public class Chat implements Serializable {
	private String id_a;
	String id_b;
	String name_a;
	String name_b;
	String msg;
	String time;
	String date;
	boolean align;
	

	//コンストラクター
	public Chat() {
		super();

	}

	//コンストラクター
	public Chat(String id_a, String id_b, String name_a, String name_b, String msg,String time) {
		super();
		this.id_a = id_a;
		this.id_b = id_b;
		this.name_a = name_a;
		this.name_b = name_b;
		this.msg = msg;
		this.time = time;
	}

	//コンストラクター
	public Chat(String id_a, String id_b, String name_a, String name_b, String msg,String date, String time, boolean align) {
		super();
		this.id_a = id_a;
		this.id_b = id_b;
		this.name_a = name_a;
		this.name_b = name_b;
		this.msg = msg;
		this.time = time;
		this.date = date;
		this.align = align;
	}

	public String getId_a() {
		return id_a;
	}

	public void setId_a(String id_a) {
		this.id_a = id_a;
	}

	public String getId_b() {
		return id_b;
	}

	public void setId_b(String id_b) {
		this.id_b = id_b;
	}

	public String getName_a() {
		return name_a;
	}

	public void setName_a(String name_a) {
		this.name_a = name_a;
	}

	public String getName_b() {
		return name_b;
	}

	public void setName_b(String name_b) {
		this.name_b = name_b;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isAlign() {
		return align;
	}

	public void setAlign(boolean align) {
		this.align = align;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
