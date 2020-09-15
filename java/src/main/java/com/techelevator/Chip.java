package com.techelevator;

import java.math.BigDecimal;

public class Chip implements VendingFoodItems {

	String itemNumber;
	String itemName;
	BigDecimal itemPrice;
	int quantity = 0;
	
	public Chip(String itemNumber, String itemName, BigDecimal itemPrice) {
		this.itemNumber = itemNumber;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = 5;
	}
		
	@Override
	public String toString() {
		return this.itemNumber + " " + this.itemPrice + " " + this.itemName;
	}

	public void purchase() {
		quantity--;
		System.out.println("Crunch Crunch, Yum!");
	}

	@Override
	public String getItemNumber() {
		// TODO Auto-generated method stub
		return this.itemNumber;
	}

	@Override
	public String getItemName() {
		// TODO Auto-generated method stub
		return this.itemName;
	}

	@Override
	public BigDecimal getItemPrice() {
		// TODO Auto-generated method stub
		return this.itemPrice;
	}

	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return this.quantity;
	}

}
