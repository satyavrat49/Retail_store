package com.retail.store.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.store.product.Product;
import com.retail.store.product.ProductFactory;
import com.retail.user.User;
import com.retail.user.UserFactory;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
class InvoiceServiceTest {
	@Autowired
	private InvoiceService invoiceService = new InvoiceService();
	private List<Product> list;

	@BeforeEach
	public void init() {
		list = new ArrayList<Product>();
		ProductFactory pf = new ProductFactory();
		list.add(pf.getProduct("Milk", "100", true));
		list.add(pf.getProduct("rice", "100", true));
		list.add(pf.getProduct("tv", "100", false));
		list.add(pf.getProduct("radio", "100", false));
	}

	@Test
	void createInvoiceForEmploye() {
		UserFactory userFactory = new UserFactory();
		User user = userFactory.getUser("bob", "employee", new Date());
		Assert.assertTrue("325$".equalsIgnoreCase(invoiceService.createInvoice(user, list).getTotalAmount()));
	}

	@Test
	void createInvoiceForaffiliate() {
		UserFactory userFactory = new UserFactory();
		User user = userFactory.getUser("sam", "affiliate", new Date());
		invoiceService.createInvoice(user, list);
		Assert.assertTrue("365$".equalsIgnoreCase(invoiceService.createInvoice(user, list).getTotalAmount()));
	}

	@Test
	void createInvoiceForcustomer() {
		UserFactory userFactory = new UserFactory();
		User user = userFactory.getUser("om", "customer", new Date());
		invoiceService.createInvoice(user, list);
		Assert.assertTrue("380$".equalsIgnoreCase(invoiceService.createInvoice(user, list).getTotalAmount()));
	}

	@Test
	void createInvoiceForOldcustomer() throws ParseException {
		UserFactory userFactory = new UserFactory();
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/12/2016");
		User user = userFactory.getUser("test", "customer", date1);
		Assert.assertTrue("375$".equalsIgnoreCase(invoiceService.createInvoice(user, list).getTotalAmount()));
	}

}
