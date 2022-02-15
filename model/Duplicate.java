package model;

import dao.AccountDAO;

public class Duplicate {
	public boolean check(String id) {
		AccountDAO dao = new AccountDAO();
		boolean a  = dao.check(id);
		return a;

	}

}
