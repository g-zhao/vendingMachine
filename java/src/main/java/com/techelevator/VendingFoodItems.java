package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public interface VendingFoodItems {
	
	public String getItemNumber(); 
	public String getItemName();
	public BigDecimal getItemPrice(); 
	public int getQuantity();
	public void purchase();
	
}
