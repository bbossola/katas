package salestax.domain;

import salestax.util.Money;

/**
 * A good you can buy. It encapsulates taxing calculation
 * by using a strategy 
 * 
 * @author bossola
 */
public class Good {

	public static final Taxing TAX_FREE = new Taxing() {
		public Money amount(Money price) {
			return Money.ZERO;
		}
	};
	
	public static final Taxing TAX_BASIC = new Taxing() {
		public Money amount(Money price) {
			return price.multiply(0.10).roundTo(Money.ROUNDING_FIVE_CENT_UP);
		}
	};
	
	public static final Taxing TAX_IMPORT = new Taxing() {
		public Money amount(Money price) {
			return price.multiply(0.05).roundTo(Money.ROUNDING_FIVE_CENT_UP);
		}
	};
	
	private String description;
	private Money price;
	private Taxing[] taxings;
	
	public Good(String description, Money price, Taxing... taxings) {
		if (description == null)
			throw new IllegalArgumentException("Invalid good description");

		if (price.amount() <= 0.0)
			throw new IllegalArgumentException("Invalid good price");

		if (taxings == null)
			throw new IllegalArgumentException("Invalid good taxings");

		this.description = description;
		this.price = price;
		this.taxings = taxings;
	}

	public String description() {
		return description;
	}

	public Money price() {
		return price;
	}

	public Money finalPrice() {
		Money roundedTaxes = new Money(taxes().amount());
		return price.add(roundedTaxes);
	}

	public Money taxes() {
		Money taxes = new Money(0.0, Money.ROUNDING_FIVE_CENT_UP);
		for (Taxing taxing : taxings) {
			taxes = taxes.add(taxing.amount(price));
		}
		return taxes;
	}
}
