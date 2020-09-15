package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.Assert;

public class gumTest {
	VendingFoodItems newGum = new Gum("D3", "Chiclets", new BigDecimal(0.75));
	
	@Test
	public void Gum() {
		
		assertEquals("Chiclets", newGum.getItemName());
		assertEquals("D3", newGum.getItemNumber());
		assertEquals(new BigDecimal(0.75), newGum.getItemPrice());
	}

	@Test
	public void GumPurchase() {
		newGum.purchase();
		
		assertEquals(4, newGum.getQuantity());
	}
	
	@Test
	public void GumToString() {
		
		assertEquals("D3 0.75 Chiclets", newGum.toString());
	}
}
