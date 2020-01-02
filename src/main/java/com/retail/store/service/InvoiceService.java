package com.retail.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.store.product.Product;
import com.retail.store.service.UserInvoice.InvoiceBuilder;
import com.retail.user.User;

@Service
public class InvoiceService {
	public UserInvoice createInvoice(User user, List<Product> listOfProducts) {
		InvoiceBuilder InvoiceBuilder = new InvoiceBuilder(listOfProducts, user);
		UserInvoice userInvoice = InvoiceBuilder.buildUserUserInvoice();
		System.out.println(userInvoice);
		return userInvoice;

	}

}
