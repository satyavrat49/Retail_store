package com.retail.store.product;

import java.math.BigDecimal;

public class NonGroceryPorducts implements Product {
	private String name;
	private String price;

	@Override
	public boolean isGrocery() {
		return false;
	}

	@Override
	public void setProductDetails(String name, String price) {
		this.name = name;
		this.price = price;

	}

	@Override
	public BigDecimal getProductPrice() {
		return new BigDecimal(this.price);
	}

	@Override
	public String getProductName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "NonGroceryPorducts [name=" + name + ", price=" + price + "]";
	}
	
	
	
	

}
