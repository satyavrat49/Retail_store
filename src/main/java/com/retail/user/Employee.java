package com.retail.user;

import java.math.BigDecimal;

public class Employee implements User {

	private String name;

	@Override
	public BigDecimal getDiscount() {
		return new BigDecimal("30");
	}

	public Employee(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + "]";
	}

}
