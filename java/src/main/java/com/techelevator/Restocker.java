package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Restocker {

	private static List<VendingFoodItems> inventory = new ArrayList<>();
//	static String [] inventoryMenu = new String [17];
	
	public static void restocker() {
	
	File snacks = new File("vendingmachine.csv");
	boolean hasSnacks = snacks.exists();
	String snackLine ="";
	
//	int i = 0;

	try (Scanner restocker = new Scanner(snacks)) {
		
		while( restocker.hasNextLine() ) {
			snackLine = restocker.nextLine();
			String [] snack = snackLine.split("\\|");
			
			if (snack[3].equals("Chip")) {
//				inventoryMenu[i] = snack[1];		
				VendingFoodItems chip = new Chip(snack[0], snack[1], new BigDecimal(snack[2]));
				inventory.add(chip);
			} else if (snack[3].equals("Candy")) {
//				inventoryMenu[i] = snack[1];
				VendingFoodItems candy = new Candy(snack[0], snack[1], new BigDecimal(snack[2]));
				inventory.add(candy);
			} else if (snack[3].equals("Drink")) {
//				inventoryMenu[i] = snack[1];
				VendingFoodItems drink = new Drink(snack[0], snack[1], new BigDecimal(snack[2]));
				inventory.add(drink);
			} else if (snack[3].equals("Gum")) {
//				inventoryMenu[i] = snack[1];
				VendingFoodItems gum = new Gum(snack[0], snack[1], new BigDecimal(snack[2]));
				inventory.add(gum);
			}
//			i++;
		}

		
	} catch (FileNotFoundException e) {
		
	}
	
	}
	
	public static List<VendingFoodItems> getVendingFoodItems() {
		return inventory;
	}
	
}
