package model;

import dao.AccountDAO;

public class GetFindAll {
	public void update(Test test){
		AccountDAO dao = new AccountDAO();
		boolean kekka = dao.update(test);
	}
}
