package com.retail.user;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Customer implements User {
	private Date userCreationDate;
	private String name;

	@Override
	public BigDecimal getDiscount() {
		LocalDate joiningDay=	Instant.ofEpochMilli(userCreationDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		return new BigDecimal(Period.between(joiningDay, LocalDate.now()).getYears() > 2 ? "5" : "0");
	}

	public Customer(String name, Date userCreationDate) {
		super();
		this.name = name;
		this.userCreationDate = userCreationDate;
	}

	@Override
	public String toString() {
		return "Customer [userCreationDate=" + userCreationDate + ", name=" + name + "]";
	}

}
