package salestax.domain;

import salestax.util.Money;

/**
 * Incapsulate a simple taxing calculation
 * 
 * @author bossola
 */
public interface Taxing {
	public Money amount(Money price);
}
