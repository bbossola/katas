package salestax.util;

import java.math.BigDecimal;

public class RoundingOneCentUpTest extends RoundingAbstractTest {

	public void testRoundingWithCents() {
		assertResultEquals(
			new double[] { 10.00, 10.02, 10.05, 10.07 }, 
			new double[] { 10.00, 10.02, 10.05, 10.07});
	}

	// TODO: we should ask to the customer about this values!
	public void testRoundingWithMillicents() {
		assertResultEquals(
				new double[] { 10.001, 10.005, 10.007 },
				new double[] { 10.01,  10.01,  10.01});
	}

	protected double round(String amountAsString) {
		return Money.ROUNDING_ONE_CENT_UP.round(new BigDecimal(amountAsString)).doubleValue();
	}


}
