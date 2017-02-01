package salestax.domain;

import salestax.util.Money;
import junit.framework.TestCase;

import static salestax.domain.Good.TAX_BASIC;
import static salestax.domain.Good.TAX_FREE;
import static salestax.domain.Good.TAX_IMPORT;

public class GoodTest extends TestCase {

	final String GOOD_DESCRIPTION = "Bottle of parfume";

	public void testDescription() {
		assertEquals(GOOD_DESCRIPTION, newGood(10.00).description());
	}

	public void testPrice() {
		assertEquals(new Money(10.0), newGood(10.0).price());
		assertEquals(new Money(10.0), newTaxedGood(10.0).price());
	}

	public void testFinalPrice() {
		assertCalculatedPricesCorrect(newGood(10.0), 10.0, 0.0);
	}

	public void testFinalPriceOnImportedItem() {
		assertCalculatedPricesCorrect(newImportedGood(10.0), 10.50, 0.50);
	}

	public void testFinalPriceOnImportedItemUsingOtherPrice() {
		assertCalculatedPricesCorrect(newImportedGood(20.00), 21.00, 1.00);
	}

	public void testFinalPriceOnTaxedItem() {
		assertCalculatedPricesCorrect(newTaxedGood(10.0), 11.00, 1.00);
	}

	public void testFinalPriceOnTaxedItemUsingAnotherPrice() {
		assertCalculatedPricesCorrect(newTaxedGood(20.0), 22.00, 2.00);
	}

	public void testFinalPriceOnTaxedImportedItem() {
		assertCalculatedPricesCorrect(newTaxedImportedGood(10.0), 11.50, 1.50);
	}

	public void testFinalPriceOnTaxedImportedGoodWithUpperRounding() {
		assertCalculatedPricesCorrect(newTaxedImportedGood(9.77), 11.27, 1.50);
	}

	public void testFinalPriceOnTaxedImportedGoodWithLowerRounding() {
		assertCalculatedPricesCorrect(newTaxedImportedGood(9.62), 11.07, 1.45);
	}

	public void testInvalidDescription() {
		try {
			new Good(null, new Money(10), TAX_FREE);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException thisWasExpected) {
		}
	}

	public void testInvalidPrice() {
		try {
			new Good(GOOD_DESCRIPTION, Money.ZERO, TAX_FREE);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException thisWasExpected) {
		}

		try {
			new Good(GOOD_DESCRIPTION, new Money(-1), TAX_FREE);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException thisWasExpected) {
		}
	}

	public void testInvalidTAxings() {
		try {
			new Good(GOOD_DESCRIPTION, new Money(10), (Taxing[])null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException thisWasExpected) {
		}
	}

	private void assertCalculatedPricesCorrect(Good good, double finalPrice, double taxes) {
		assertEquals("Invalid final price", finalPrice, good.finalPrice().amount());
		assertEquals("Invalid taxes amount", taxes, good.taxes().amount());
	}

	private Good newGood(double price) {
		return new Good(GOOD_DESCRIPTION, new Money(price), TAX_FREE);
	}

	private Good newTaxedGood(double price) {
		return new Good(GOOD_DESCRIPTION, new Money(price), TAX_BASIC);
	}

	private Good newImportedGood(double price) {
		return new Good(GOOD_DESCRIPTION, new Money(price), TAX_IMPORT);
	}

	private Good newTaxedImportedGood(double price) {
		return new Good(GOOD_DESCRIPTION, new Money(price), TAX_IMPORT, TAX_BASIC);
	}
}
