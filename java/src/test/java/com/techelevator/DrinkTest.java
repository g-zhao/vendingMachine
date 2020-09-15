package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class DrinkTest {

	VendingFoodItems newDrink = new Drink("C1", "Cola", new BigDecimal(1.25));
	
	@Test
	public void Drink() {
		
		assertEquals("Cola", newDrink.getItemName());
		assertEquals("C1", newDrink.getItemNumber());
		assertEquals(new BigDecimal(1.25), newDrink.getItemPrice());
	}

	@Test
	public void DrinkPurchase() {
		newDrink.purchase();
		
		assertEquals(4, newDrink.getQuantity());
	}
	
	@Test
	public void DrinkToString() {
		
		assertEquals("C1 1.25 Cola", newDrink.toString());
	}

}
