package salestax.util;

import salestax.util.Money;
import junit.framework.TestCase;

public class MoneyTest extends TestCase {

	// need to recognize assertion status (see testAssertion* tests)
	static boolean assertsEnabled = false;
	static {
		assert assertsEnabled = true; // intentional side effect!
	}

	public void testCreateWithIntegerAmount() {
		Money bucks = new Money(10);
		assertEquals(10.0, bucks.amount());
	}

	public void testCreateWithDoubleAmount() {
		Money bucks = new Money(10.47);
		assertEquals(10.47, bucks.amount());
	}

	public void testAddingMoney() {
		Money bucks = new Money(0.45).add(new Money(0.55));
		assertEquals(1.00, bucks.amount());
	}

	public void testNegatetMoney() {
		Money bucks = new Money(0.55).negate();
		assertEquals(-0.55, bucks.amount());
	}

	public void testAddingMoneyWhenRoundingSourceIsFiveCentRounded() {
		Money bucks = new Money(0.47, Money.ROUNDING_FIVE_CENT_UP).add(new Money(0.57));
		assertEquals(1.05, bucks.amount());
	}

	public void testAddingMoneyWhenRoundingSourceIsOneCentRounded() {
		Money bucks = new Money(0.47).add(new Money(0.57, Money.ROUNDING_FIVE_CENT_UP));
		assertEquals(1.04, bucks.amount());
	}

	public void testMultiplyWithIntegerMultiplier() {
		Money bucks = new Money(3.33);
		assertEquals(9.99, bucks.multiply(3).amount());
	}

	public void testMultiplyWithDoubleMultiplier() {
		Money bucks = new Money(10.0);
		assertEquals(12.5, bucks.multiply(1.25).amount());
	}

	public void testRoundToWhenRoundingUp() {
		Money bucks = new Money(10.01).roundTo(Money.ROUNDING_FIVE_CENT_UP);
		assertEquals(10.05, bucks.amount());
	}

	public void testRoundToWhenRoundingDown() {
		Money bucks = new Money(10.01, Money.ROUNDING_FIVE_CENT_UP).roundTo(Money.ROUNDING_ONE_CENT_UP);
		assertEquals(10.01, bucks.amount());
	}

	public void testMoneyZero() {
		assertEquals(0.0, Money.ZERO.amount());
	}

	public void testToString() {
		assertEquals("120.05", new Money(120.05).toString());
		assertEquals("10.00", new Money(10.0).toString());
		assertEquals("0.10", new Money(0.10).toString());
	}

	public void testHashCode() {
		final double value = 1237.36;
		assertEquals(String.valueOf(value).hashCode(), new Money(value).hashCode());
	}

	public void testEqualsNaively() {
		assertTrue(new Money(10.05).equals(new Money(10.05)));

		assertFalse(new Money(10.05).equals(new Money(99.9)));
		assertFalse(new Money(10.05).equals(new Object()));
		assertFalse(new Money(10.05).equals(null));
	}

	public void testEqualsWhenRoundingDifferent() {
		assertTrue(new Money(10.05, Money.ROUNDING_FIVE_CENT_UP).equals(new Money(10.05, Money.ROUNDING_ONE_CENT_UP)));
		assertFalse(new Money(10.03, Money.ROUNDING_FIVE_CENT_UP).equals(new Money(10.03, Money.ROUNDING_ONE_CENT_UP)));
	}

	public void testAssertionErrorWhenRounderIsNull() {

		// this test will work only with assertions enabled: unfortunately API to set
		// assertion status are not reliable because of classloading issues, that's
		// the reason of this dirty trick :) please use -ea during tests!
		if (assertsEnabled)
			try {
				new Money(10.0, null);
				fail("An assertion error was expected!");
			} catch (AssertionError itWasExpected) {
				// okay :)
			}
	}
}
