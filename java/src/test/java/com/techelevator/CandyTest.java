package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class CandyTest {

	VendingFoodItems newCandy = new Candy("B2", "Cowtales", new BigDecimal(1.50));
	
	@Test
	public void Candy() {
		
		assertEquals("Cowtales", newCandy.getItemName());
		assertEquals("B2", newCandy.getItemNumber());
		assertEquals(new BigDecimal(1.50), newCandy.getItemPrice());
	}

	@Test
	public void CandyPurchase() {
		newCandy.purchase();
		
		assertEquals(4, newCandy.getQuantity());
	}
	
	@Test
	public void CandyToString() {
		
		assertEquals("B2 1.5 Cowtales", newCandy.toString());
	}

}
