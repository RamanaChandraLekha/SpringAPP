package com.full.shopkeeper;

import java.util.HashMap;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class ShopkeeperHelperClass {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public void addProductsToStore(String mailId, String product, String quantity) {

		Entity productInfo = new Entity("Products");
		productInfo.setProperty("ShopkeeperId", mailId);
		productInfo.setProperty("ProductName", product);
		productInfo.setProperty("Quantity", quantity);
		datastore.put(productInfo);

	}

	public boolean deleteProductFromStore(String mailId, String product) {

		boolean flag = false;
		;
		Query query = new Query("Products");
		PreparedQuery productToDelete = datastore.prepare(query);

		for (Entity productDetails : productToDelete.asIterable()) {

			String shopkeeperMailId = (String) productDetails.getProperty("ShopkeeperId");

			if (mailId.equals(shopkeeperMailId)) {

				String productDeleted = (String) productDetails.getProperty("ProductName");
				if (product.equals(productDeleted)) {
					Key key = productDetails.getKey();
					datastore.delete(key);
					flag = true;
				}

			}

		}
		return flag;
	}

	public String retrieveProductsFromStore(String mailId, String product) {
		String availableQuantity = "";

		Query query = new Query("Products");
		PreparedQuery productToRetrieve = datastore.prepare(query);

		for (Entity productDetails : productToRetrieve.asIterable()) {

			String shopkeeperMailId = (String) productDetails.getProperty("ShopkeeperId");
			if (mailId.equals(shopkeeperMailId)) {

				String productInfo = (String) productDetails.getProperty("ProductName");
				if (productInfo.equals(product)) {
					availableQuantity = (String) productDetails.getProperty("Quantity");
				}
			}
		}
		return availableQuantity;
	}

	public HashMap<String, String> showProductsOfShopkeeper(String mailId) {
		HashMap<String, String> productsList = new HashMap<String, String>();

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Products");
		PreparedQuery product = datastore.prepare(query);

		for (Entity productDetails : product.asIterable()) {

			String shopkeeperMailId = (String) productDetails.getProperty("ShopkeeperId");

			if (mailId.equals(shopkeeperMailId)) {

				productsList.put((String) productDetails.getProperty("ProductName"),
						productDetails.getProperty("Quantity").toString());

			}

		}

		return productsList;
	}
}
