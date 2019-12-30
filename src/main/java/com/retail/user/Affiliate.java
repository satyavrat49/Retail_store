package com.retail.user;

import java.math.BigDecimal;

public class Affiliate implements User {

	private String name;

	@Override
	public BigDecimal getDiscount() {
		return new BigDecimal(10);
	}

	public  Affiliate(String name) {
		this.name = name;

	}

	@Override
	public String toString() {
		return "Affiliate [name=" + name + "]";
	}

	
}
