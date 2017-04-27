package com.full.customer;

import java.util.HashMap;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class CustomerHelperClass {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public void checkInCart(String userMailId, String product) {
		boolean flag = false;

		Query query = new Query("CustomerCart");
		PreparedQuery prepQuery = datastore.prepare(query);
		// String userMailId=(String)session.getAttribute("email");
		for (Entity productInCarts : prepQuery.asIterable()) {
			String customerMailId = (String) productInCarts.getProperty("CustomerId");
			String productInCart = (String) productInCarts.getProperty("ProductName");

			int quantityAvailableInCart = Integer.parseInt(productInCarts.getProperty("Quantity").toString());
			if ((userMailId.equals(customerMailId)) && (product.equals(productInCart))) {
				flag = true;
				Key key = productInCarts.getKey();
				datastore.delete(key);

			}

		}
	}

	public boolean updateProductList(String product, int quantity) {
		boolean flag = false;

		Query query = new Query("Products");
		PreparedQuery productsInStore = datastore.prepare(query);

		for (Entity productDetails : productsInStore.asIterable()) {

			String productsAvailable = (String) productDetails.getProperty("ProductName");
			if (product.equalsIgnoreCase(productsAvailable)) {
				String shopkeeperId = (String) productDetails.getProperty("ShopkeeperId");

				int quantityAvailable = Integer.parseInt(productDetails.getProperty("Quantity").toString());
				if (quantityAvailable >= quantity) {
					Key key = productDetails.getKey();
					datastore.delete(key);
					quantityAvailable -= quantity;
					flag = true;
					Entity entity = new Entity("Products");
					entity.setProperty("ShopkeeperId", shopkeeperId);
					entity.setProperty("ProductName", product);
					entity.setProperty("Quantity", quantityAvailable);
					datastore.put(entity);

				}

			}

		}
		return flag;
	}

	public void orderedProducts(String userMailId, String product, String quantity) {

		Entity productsBought = new Entity("ProductBought");
		productsBought.setProperty("CustomerId", userMailId);
		productsBought.setProperty("ProductName", product);
		productsBought.setProperty("Quantity", quantity);
		datastore.put(productsBought);

	}

	public boolean addProductsToWishList(String mailId, String product, String quantity)

	{

		boolean flag = false;
		Query query = new Query("Products");
		PreparedQuery pq = datastore.prepare(query);

		for (Entity productDetails : pq.asIterable()) {
			String productName = (String) productDetails.getProperty("ProductName");

			int quantityAvailable = Integer.parseInt(productDetails.getProperty("Quantity").toString());
			if ((product.equals(productName))) {

				if (quantityAvailable >= Integer.parseInt(quantity)) {
					flag = true;

					Entity productInfo = new Entity("CustomerCart");
					productInfo.setProperty("CustomerId", mailId);
					productInfo.setProperty("ProductName", product);
					productInfo.setProperty("Quantity", quantity);
					datastore.put(productInfo);
					break;

				}

			}
		}
		return flag;
	}

	public HashMap<String, String> showProductsInCart(String userMailId) {
		HashMap<String, String> productsList = new HashMap<String, String>();
		Query query = new Query("CustomerCart");
		PreparedQuery pq = datastore.prepare(query);
		for (Entity productDetails : pq.asIterable()) {
			if (userMailId.equals((String) productDetails.getProperty("CustomerId"))) {
				productsList.put((String) productDetails.getProperty("ProductName"),
						productDetails.getProperty("Quantity").toString());
			}
		}
		return productsList;
	}
}