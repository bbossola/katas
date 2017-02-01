package salestax.util;

import java.math.BigDecimal;

public class RoundingFiveCentUpTest extends RoundingAbstractTest {

	public void testRoundingWithCents() {
		assertResultEquals(
			new double[] { 10.00, 10.02, 10.05, 10.07 }, 
			new double[] { 10.00, 10.05, 10.05, 10.10 });
	}

	// TODO: we should ask to the customer about this values!
	public void testRoundingWithMillicents() {
		assertResultEquals(
				new double[] { 10.001, 10.005, 10.007 },
				new double[] { 10.05,  10.05,  10.05 });
	}

	protected double round(String amountAsString) {
		return Money.ROUNDING_FIVE_CENT_UP.round(new BigDecimal(amountAsString)).doubleValue();
	}

}
