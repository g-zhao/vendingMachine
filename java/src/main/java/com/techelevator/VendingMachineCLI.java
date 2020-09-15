package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_DONE = "Done";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_DONE };

	private static final String[] PURCHASE_MENU = { "Feed Money", "Select Product", "Back" };
	private static final String[] MONEY_MENU = { "$1 Bill", "$2 Bill", "$5 Bill", "Back" };
	private static List<VendingFoodItems> products = new ArrayList<>();
	private static List<VendingFoodItems> cart = new ArrayList<>();
	BigDecimal balance = new BigDecimal(0.00);
	File log = new File("Log.txt");
	Date startingDate = new Date();
	Scanner userInput = new Scanner(System.in);

	private Menu menu;

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		Restocker.restocker();
		products = Restocker.getVendingFoodItems();
		cli.run();

	}

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println(choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (VendingFoodItems i : products) {
					System.out.println(i);
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				processPurchaseMenuOptions();

			} else if (choice.equals(MAIN_MENU_OPTION_DONE)) {
				issueChange();
			}
		}

	}

	public void processPurchaseMenuOptions() {
		String purchaseMenuOption = "";

		while (!purchaseMenuOption.equals("Back")) {
			purchaseMenuOption = (String) menu.getChoiceFromOptions(PURCHASE_MENU);

			if (purchaseMenuOption.equals("Feed Money")) {
				processMoneyFeed();
			}

			if (purchaseMenuOption.equals("Select Product")) {
				processProductSelection();
			}
		}

	}

	private void processMoneyFeed() {

		String feedOptions = "";

		while (!feedOptions.equals("Back")) {
			feedOptions = (String) menu.getChoiceFromOptions(MONEY_MENU);

			if (feedOptions.equals("$1 Bill")) {
				balance = balance.add(new BigDecimal(1.00));
				System.out.println("$" + balance + " dollar has been inserted.");
			} else if (feedOptions.equals("$2 Bill")) {
				balance = balance.add(new BigDecimal(2.00));
				System.out.println("$" + balance + " dollars has been inserted.");
			} else if (feedOptions.equals("$5 Bill")) {
				balance = balance.add(new BigDecimal(5.00));
				System.out.println("$" + balance + " dollars has been inserted.");
			}
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
				writer.append(
						startingDate + " Money Inserted: " + feedOptions + "\t" + "Current balance: " + balance + "\n");
			} catch (FileNotFoundException e) {

			}
		}
	}

	private void processProductSelection() {
		String productSelection = "";
//		String [] productDisplay = new String [products.size()+1];
//		productDisplay[productDisplay.length-1] = "Back";

//		for (int i = 0; i < products.size(); i++) {
//			String temp = products.get(i).getItemName(); //using built in menu for selection
		// using item number for selection
//			String temp = products.get(i).getItemNumber() + " " + products.get(i).getItemPrice() + " " + products.get(i).getItemName();
//			productDisplay[i] = temp;
//		}

		while (!productSelection.equals("1")) {
//			productSelection = (String) menu.getChoiceFromOptions(productDisplay); using built in menu
			System.out.println("1) Back");
			for (VendingFoodItems i : products) {
				System.out.println(i);
			}
			productSelection = userInput.nextLine();
			double price = 0.00;
			double currentBalance = 0.0;
			VendingFoodItems itemSelected = null;

			boolean itemFound = false;
			
			// ANDY********
			// Let's verify if input can be found in products
			for (VendingFoodItems i : products) {

				if (productSelection.equals(i.getItemNumber())) {
					itemFound = true;
					itemSelected = i;
					break;

				}
			}

			if (itemFound) {
				
				price = itemSelected.getItemPrice().doubleValue();
				currentBalance = balance.doubleValue();
				if (price <= currentBalance && itemSelected.getQuantity() != 0) {
					cart.add(itemSelected);
					itemSelected.purchase();
					balance = balance.subtract(itemSelected.getItemPrice());
					System.out.println("Current balance: " + balance);

					try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
						writer.append(
								startingDate + " Purchase: " + itemSelected + "\t Current Balance: " + balance + "\n");
					} catch (FileNotFoundException e) {

					}
				} else if (price > currentBalance || itemSelected.getQuantity() == 0){
					System.out.println("Insufficient funds or item is out of stock.");
				} 
			} else if (!productSelection.equals("1")){
				System.out.println("\n*** " + productSelection + " is not a valid option ***\n");
			}

			// ANDY********

//			for (VendingFoodItems vendingFoodItem : products) {
//				System.out.println(vendingFoodItem.getItemNumber());
//				System.out.println(productSelection);
////				if (productSelection.equals(vendingFoodItem.getItemName())) {
//				if (productSelection.equals(vendingFoodItem.getItemNumber())) {
//					
//					itemSelected = vendingFoodItem;
//					
//					price = itemSelected.getItemPrice().doubleValue();
//					currentBalance = balance.doubleValue();
//					if (price <= currentBalance && itemSelected.getQuantity() != 0) {
//						cart.add(itemSelected);
//						itemSelected.purchase();
//						balance = balance.subtract(itemSelected.getItemPrice());
//						System.out.println("Current balance: " + balance);
//						
//						try (PrintWriter writer = new PrintWriter( new FileOutputStream(log, true)) ) {
//							writer.append(startingDate + " Purchase: " + itemSelected + "\t Current Balance: " + balance + "\n");
//						} catch (FileNotFoundException e) {
//				
//						}
//						
//					} else if (price > currentBalance || itemSelected.getQuantity() == 0){
//						System.out.println("Insufficient funds or item is out of stock.");
//					} 
//					} else {
//						System.out.println("\n*** " + productSelection + " is not a valid option ***\n");
//				}
//				break;
//			}
		}
	}

	private void issueChange() {
		int refundBalance = ((int) (balance.doubleValue() * 100));
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		String changeIssued = "";

		while (refundBalance != 0) {
			if (refundBalance >= 25) {
				quarters += 25;
				refundBalance -= (25);
			} else if (refundBalance >= 10) {
				dimes += 10;
				refundBalance -= 10;
			} else if (refundBalance >= 5) {
				nickels += 5;
				refundBalance -= 5;
			}
		}

		int numOfQuarters = ((int) (quarters / 25));
		int numOfDimes = ((int) (dimes / 10));
		int numOfNickels = ((int) (nickels / 5));

		if (quarters != 0) {
			changeIssued += numOfQuarters + " in quarters ";
			if (dimes != 0)
				System.out.print(" ");
		}
		if (dimes != 0) {
			changeIssued += numOfDimes + " in dimes ";
			if (nickels != 0)
				System.out.print(" ");
		}
		if (nickels != 0) {
			changeIssued += numOfNickels + " in nickels";
		}

		System.out.println("Your change of " + balance + " is returned as: " + changeIssued + ".");

		try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
			writer.append(startingDate + " Returned Funds Of: " + balance + "\n");
		} catch (FileNotFoundException e) {

		}
		System.exit(0);
	}
}