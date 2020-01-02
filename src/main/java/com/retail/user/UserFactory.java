package com.retail.user;

import java.util.Date;

public class UserFactory {
	public User getUser(String name, String userType, Date date) {
		User user = null;
		switch (userType.toLowerCase()) {
		case "employee":
			user = new Employee(name);
			break;
		case "affiliate":
			user = new Affiliate(name);
			break;
		default:
			user = new Customer(name, date);
		}
		return user;
	}

}
