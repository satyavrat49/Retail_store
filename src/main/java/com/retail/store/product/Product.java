package com.retail.store.product;

import java.math.BigDecimal;

public interface Product {
	boolean isGrocery();

	void setProductDetails(String name, String price);

	BigDecimal getProductPrice();

	String getProductName();

}
