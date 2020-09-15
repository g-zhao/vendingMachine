package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ChipTest {

	VendingFoodItems newChip = new Chip("A3", "Grain Waves", new BigDecimal(2.75));
	
	@Test
	public void Chip() {
		
		assertEquals("Grain Waves", newChip.getItemName());
		assertEquals("A3", newChip.getItemNumber());
		assertEquals(new BigDecimal(2.75), newChip.getItemPrice());
	}

	@Test
	public void ChipPurchase() {
		newChip.purchase();
		
		assertEquals(4, newChip.getQuantity());
	}
	
	@Test
	public void ChipToString() {
		
		assertEquals("A3 2.75 Grain Waves", newChip.toString());
	}
}
