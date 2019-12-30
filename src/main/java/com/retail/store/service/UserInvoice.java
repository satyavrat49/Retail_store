package com.retail.store.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class UserInvoice {
	private com.retail.user.User user;
	private List<com.retail.store.product.Product> products;
	private String totalAmount;

	public UserInvoice(InvoiceBuilder invoiceBuilder) {
		super();
		this.user = invoiceBuilder.user;
		this.products = invoiceBuilder.products;
		this.totalAmount = invoiceBuilder.totalAmount + "$";
	}

	public static class InvoiceBuilder {
		private final List<com.retail.store.product.Product> products;
		private final com.retail.user.User user;
		public BigDecimal totalAmount;

		public InvoiceBuilder(List<com.retail.store.product.Product> products, com.retail.user.User user) {
			super();
			this.products = products;
			this.user = user;
		}

		public BigDecimal totalForGroceryItems(List<com.retail.store.product.Product> products) {
			return products.stream().map(product -> {
				System.out.println(
						String.format("product %s price %s ", product.getProductName(), product.getProductPrice()));
				return product.getProductPrice();
			}).reduce(new BigDecimal("0"), (x, y) -> x.add(y));
		}

		public BigDecimal totalForNonGroceryItems(List<com.retail.store.product.Product> products) {
			return products.stream().map(product -> {
				BigDecimal afterDiscountPrice = product.getProductPrice().subtract(
						(product.getProductPrice().multiply(user.getDiscount())).divide(new BigDecimal("100")));
				System.out.println(String.format("product name %s and retail price %s price after discount %s ",
						product.getProductName(), product.getProductPrice(), afterDiscountPrice + ""));
				return afterDiscountPrice;
			}).reduce(new BigDecimal("0"), (x, y) -> x.add(y));

		}

		public BigDecimal finalDiscountOnTheTotalPerchsae(BigDecimal discount, BigDecimal baseAmount,
				BigDecimal tolalAmount) {
			return tolalAmount
					.subtract(new BigDecimal(tolalAmount.divide(baseAmount).intValue() + "").multiply(discount));

		}

		public UserInvoice buildUserUserInvoice() {
			List<com.retail.store.product.Product> nonGroceryProducts = this.products.stream()
					.filter(product -> !product.isGrocery()).collect(Collectors.toList());
			List<com.retail.store.product.Product> groceryProducts = this.products.stream()
					.filter(product -> product.isGrocery()).collect(Collectors.toList());
			this.totalAmount = finalDiscountOnTheTotalPerchsae(new BigDecimal("5"), new BigDecimal("100"),
					totalForGroceryItems(groceryProducts).add(totalForNonGroceryItems(nonGroceryProducts)));
			System.out.println(String.format("total amount to be paid %s ",this.totalAmount+""));
			return new UserInvoice(this);
		}

	}

	@Override
	public String toString() {
		return "UserInvoice [user=" + user + ", products=" + products + ", totalAmount=" + totalAmount + "]";
	}

	
}
