package com.full.customer;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.full.Product;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@Controller
public class CustomerController {
	CustomerHelperClass customerHelper = new CustomerHelperClass();

	@RequestMapping(value = "/customerMenu", method = RequestMethod.GET)
	public String customerMenu() {

		return "Customer";
	}

	@ResponseBody
	@RequestMapping(value = "/addProductsCart", method = RequestMethod.POST, consumes = { "application/json" })
	public ModelAndView addproductsToCart(@RequestBody Product product, HttpSession session) {

		String mailId = (String) session.getAttribute("email");
		boolean status = customerHelper.addProductsToWishList(mailId, product.getProductName(),
				product.getProductQuantity());
	
		if (!status)

			return new ModelAndView("error", "error", "Product of requested quantity is not available");
		else
			return new ModelAndView("sucess");
		
	

	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST, consumes = { "application/json" })
	public ModelAndView buy(@RequestBody Product product, HttpSession session) {

		String userMailId = (String) session.getAttribute("email");

		int quantityRequired = Integer.parseInt(product.getProductQuantity());
		customerHelper.checkInCart(userMailId, product.getProductName());
		customerHelper.orderedProducts(userMailId, product.getProductName(), product.getProductQuantity());

		boolean update = customerHelper.updateProductList(product.getProductName(), quantityRequired);

		if (update)
			return new ModelAndView("sucess");
		else
			return new ModelAndView("error", "error", "sorry!products of requested quantity is not available");

	}

	@ResponseBody
	@RequestMapping(value = "/showCart", method = RequestMethod.GET)
	public HashMap<String, String> showCart(HttpSession session) {

		String userMailId = (String) session.getAttribute("email");
		return new CustomerHelperClass().showProductsInCart(userMailId);
	}

	@RequestMapping(value = "/ShowProduct", method = RequestMethod.GET)
	public String buyProducts() {

		return "ShowProduct";

	}
}
