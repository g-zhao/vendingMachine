import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.Chip;
import com.techelevator.Restocker;
import com.techelevator.VendingFoodItems;
import com.techelevator.VendingMachineCLI;

public class RestockerTest {
	
	@Test
	public void getVendingFoodItemsTest() {
		assertEquals(Restocker.getVendingFoodItems(), Restocker.getVendingFoodItems());
	}
	
}
