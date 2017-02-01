package salestax.domain;

import junit.framework.TestCase;
import salestax.domain.Receipt.Line;
import salestax.util.Money;

public class ReceiptTest extends TestCase {

	private static final Good GOOD_ONE = newGood("Good one", 10, 1);
	private static final Good GOOD_TWO = newGood("Good two", 20, 2);
	private static final Good GOOD_THREE = newGood("Good three", 30, 3);

	private Receipt receipt;

	public void setUp() {
		receipt = new Receipt();
	}

	public void testReceiptTotalsWithNoEntries() {
		assertEquals(Money.ZERO, receipt.taxesAmount());
		assertEquals(Money.ZERO, receipt.totalAmount());
	}

	public void testReceiptTotalsWithOneEntry() {
		receipt.addItem(GOOD_ONE, 1);

		assertEquals(GOOD_ONE.finalPrice(), receipt.totalAmount());
		assertEquals(GOOD_ONE.taxes(), receipt.taxesAmount());
	}

	public void testReceiptTotalsWithSomeEntries() {
		receipt.addItem(GOOD_ONE, 1);
		receipt.addItem(GOOD_TWO, 1);
		receipt.addItem(GOOD_THREE, 1);

		assertEquals(totalAmount(GOOD_ONE, GOOD_TWO, GOOD_THREE), receipt.totalAmount());

		assertEquals(totalTaxes(GOOD_ONE, GOOD_TWO, GOOD_THREE), receipt.taxesAmount());
	}

	public void testReceiptTotalsWithSomeEntriesDifferentQuantities() {
		receipt.addItem(GOOD_ONE, 1);
		receipt.addItem(GOOD_TWO, 2);
		receipt.addItem(GOOD_THREE, 2);

		assertEquals(totalAmount(GOOD_ONE, GOOD_TWO, GOOD_TWO, GOOD_THREE, GOOD_THREE), receipt.totalAmount());
		assertEquals(totalTaxes(GOOD_ONE, GOOD_TWO, GOOD_TWO, GOOD_THREE, GOOD_THREE), receipt.taxesAmount());
	}

	public void testReceiptTaxesAmountWhenRoundingRequired() {
		receipt.addItem(newGood("Good one", 10, 1.01), 1);
		receipt.addItem(newGood("Good one", 10, 1.02), 1);
		assertEquals(new Money(2.10), receipt.taxesAmount());
	}

	public void testReceiptTaxesAmountWhenRoundingRequiredAndDifferentQuantities() {
		receipt.addItem(newGood("Good one", 10, 0.89), 5);
		assertEquals(new Money(4.50), receipt.taxesAmount());
	}

	public void testReceiptLinesWihNoEntries() {
		assertEquals(0, new Receipt().lines().size());
	}

	public void testReceiptLinesWihTwoEntries() {
		receipt.addItem(GOOD_ONE, 1);
		receipt.addItem(GOOD_TWO, 1);

		assertEquals(2, receipt.lines().size());
		assertReceiptLineCorrect(receipt.lines().get(0), GOOD_ONE, 1);
		assertReceiptLineCorrect(receipt.lines().get(1), GOOD_TWO, 1);
	}

	public void testReceiptLinesWihMultipleQuantity() {
		receipt.addItem(GOOD_ONE, 3);
		assertReceiptLineCorrect(receipt.lines().get(0), GOOD_ONE, 3);
	}

	public void testAddInvalidGood() {
		try {
			receipt.addItem(null, 1);
			fail("An exception was expected!");
		} catch (IllegalArgumentException thisWasExpected) {
		}
	}

	public void testAddZeroQuantity() {
		try {
			receipt.addItem(GOOD_ONE, 0);
			fail("An exception was expected!");
		} catch (IllegalArgumentException thisWasExpected) {
		}
	}

	public void testNegativeQuantity() {
		try {
			receipt.addItem(GOOD_ONE, -1);
			fail("An exception was expected!");
		} catch (IllegalArgumentException thisWasExpected) {
		}
	}

	private void assertReceiptLineCorrect(Line line, Good good, int quantity) {
		assertEquals(good.description(), line.description());
		assertEquals(good.finalPrice().multiply(quantity), line.price());
		assertEquals(quantity, line.quantity());
	}

	private Money totalAmount(Good... goods) {

		Money amount = Money.ZERO;
		for (Good good : goods) {
			amount = amount.add(good.finalPrice());
		}

		return amount;
	}

	private Money totalTaxes(Good... goods) {

		Money amount = Money.ZERO;
		for (Good good : goods) {
			Money roundedTaxes = new Money(good.taxes().amount());
			amount = amount.add(roundedTaxes);
		}

		return amount;
	}

	// fake taxing method in order to create our precisely taxed good
	private static Good newGood(final String description, final double price, final double taxes) {
		return new Good(description, new Money(price), new Taxing() {
			public Money amount(Money price) {
				return new Money(taxes);
			}
		});

	}
}
