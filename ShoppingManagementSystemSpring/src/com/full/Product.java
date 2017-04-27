package com.full;

public class Product {
	private String productName;
	private String productQuantity;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String toString() {

		return productName + "     -     " + productQuantity;
	}

}
