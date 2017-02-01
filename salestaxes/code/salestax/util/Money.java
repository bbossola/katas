package salestax.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * A value object representing a Money quantity, useful because incapsulates
 * value and rounding
 * 
 * Only the operations relevant for the exercise are implemented
 * 
 * @author bossola
 */
public class Money {

	public static interface Rounding {
		public BigDecimal round(BigDecimal amount);
	};

	public static final Rounding ROUNDING_ONE_CENT_UP = new Rounding() {
		public BigDecimal round(BigDecimal amount) {
			return amount.setScale(2, RoundingMode.UP);
		}
	};

	public static final Rounding ROUNDING_FIVE_CENT_UP = new Rounding() {
		public BigDecimal round(BigDecimal amount) {
			BigDecimal result = new BigDecimal(amount.doubleValue() * 20.0).setScale(0, RoundingMode.UP);
			return result.setScale(2, RoundingMode.UP).divide(BigDecimal.valueOf(20.0));
		}
	};

	public static final Money ZERO = new Money(0.0);

	private BigDecimal amount;
	private Rounding rounding;

	public Money(double amount) {
		this(amount, ROUNDING_ONE_CENT_UP);
	}

	public Money(double amount, Rounding rounding) {
		assert (rounding != null);
		this.amount = BigDecimal.valueOf(amount);
		this.rounding = rounding;
	}

	public double amount() {
		return rounding.round(amount).doubleValue();
	}

	public Money add(Money bucks) {
		return new Money(amount.add(bucks.amount).doubleValue(), this.rounding);
	}

	public Money negate() {
		return new Money(amount.negate().doubleValue(), this.rounding);
	}

	public Money multiply(double multiplier) {
		return new Money(amount.multiply(new BigDecimal(multiplier)).doubleValue(), this.rounding);
	}

	public Money roundTo(Rounding rounding) {
		return new Money(this.amount.doubleValue(), rounding);
	}

	public int hashCode() {
		return amount.toString().hashCode();
	}

	public boolean equals(Object other) {
		boolean res = false;

		try {
			Money m = (Money) other;
			res = (m.amount() == this.amount());
		} catch (Exception ignore) {
		}

		return res;
	}

	public String toString() {
		return new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.ENGLISH)).format(amount);
	}
}
