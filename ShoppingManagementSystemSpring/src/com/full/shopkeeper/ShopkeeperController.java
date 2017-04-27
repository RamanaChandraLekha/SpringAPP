package com.full.shopkeeper;

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
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@Controller
public class ShopkeeperController {

	ShopkeeperHelperClass shopkeeperHelper = new ShopkeeperHelperClass();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init() {

		return "display";
	}

	@RequestMapping(value = "/mainMenu", method = RequestMethod.GET)
	public String mainMenu() {

		return "index";
	}

	@RequestMapping(value = "/shopkeeper", method = RequestMethod.GET)
	public String shopkeeperMenu() {

		return "Shopkeeper";

	}

	@RequestMapping(value = "/AddProductPage", method = RequestMethod.GET)
	public String requestForPage() {

		return "AddProduct";

	}

	@ResponseBody
	@RequestMapping(value = "/addProducts", method = RequestMethod.POST, consumes = { "application/json" })
	public void addProducts(@RequestBody Product product, HttpSession session) {

		String mailId = (String) session.getAttribute("email");

		shopkeeperHelper.addProductsToStore(mailId, product.getProductName(), product.getProductQuantity());

	}

	@ResponseBody
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public HashMap<String, String> viewproducts(HttpServletRequest req, HttpSession session) {
		String mailId = (String) session.getAttribute("email");

		HashMap<String, String> productsList = shopkeeperHelper.showProductsOfShopkeeper(mailId);
		return productsList;
	}

	@RequestMapping(value = "/deleteProductPage", method = RequestMethod.GET)
	public String deleteProductPage() {
		return "DeleteProduct";
	}

	@ResponseBody
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.DELETE)
	public String deleteProduct(@RequestParam("product") String product, HttpSession session) {
		String mailIdToDelete = (String) session.getAttribute("email");

		boolean productDeleted = shopkeeperHelper.deleteProductFromStore(mailIdToDelete, product);
		if (productDeleted)
			return "status:true";
		else
			return "status:false";

	}

	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public String retrieveDetails() {
		return "RetrieveProducts";
	}
    @ResponseBody
	@RequestMapping(value = "/retrieveProduct", method = RequestMethod.POST)
	public ModelAndView RetrieveDetails(@RequestParam("productName") String productName, HttpSession session) {

		String mailIdToRetrieve = (String) session.getAttribute("email");
		ModelAndView model = new ModelAndView("RetrieveProductDetails");

		String availableQuantity = shopkeeperHelper.retrieveProductsFromStore(mailIdToRetrieve, productName);
		if (availableQuantity.equals(" "))
			model.addObject("error", "product is not available");
		else {

			model.addObject("product", "product is" + productName);
			model.addObject("quantity", "quantity is" + availableQuantity);
		}

		return model;
	}
}
