package salestax.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import salestax.util.Money;

/**
 * A value object representing a Receipt, the inner class Line holds a line of
 * this receipt
 * 
 * @author bossola
 */
public class Receipt {

	public static class Line {

		private int quantity;
		private String description;
		private Money price;

		private Line(int quantity, String description, Money price) {
			this.quantity = quantity;
			this.description = description;
			this.price = price;
		}

		public int quantity() {
			return quantity;
		}

		public String description() {
			return description;
		}

		public Money price() {
			return price;
		}
	}

	private List<Line> lines = new ArrayList<Line>();

	private Money netAmount = Money.ZERO;
	private Money totalAmount = Money.ZERO;

	public void addItem(Good good, int quantity) {

		if (good == null)
			throw new IllegalArgumentException("Invalid good");

		if (quantity <= 0)
			throw new IllegalArgumentException("Invalid quantity");

		lines.add(new Line(quantity, good.description(), good.finalPrice().multiply(quantity)));

		netAmount = netAmount.add(good.price().multiply(quantity));
		totalAmount = totalAmount.add(good.finalPrice().multiply(quantity));
	}

	public Money totalAmount() {
		return totalAmount;
	}

	public Money taxesAmount() {
		return totalAmount.add(netAmount.negate());
	}

	public List<Line> lines() {
		return Collections.unmodifiableList(lines);
	}
}
