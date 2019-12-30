package com.retail.store.product;

public class ProductFactory {
	public Product getProduct(String name, String price, boolean isGrocery) {
		Product product;
		if (isGrocery == true)
			product = new GroceryPorducts();
		else
			product = new NonGroceryPorducts();
		product.setProductDetails(name, price);
		return product;
	}
}