package model;

import dao.AccountDAO;

public class PostTestLogic {
	public void execute(Test test) {
		AccountDAO dao = new AccountDAO();
		boolean kekka = dao.create(test);
		
	}
}
