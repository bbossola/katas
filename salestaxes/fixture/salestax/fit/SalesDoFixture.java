package salestax.fit;

import java.util.ArrayList;
import java.util.List;

import salestax.domain.Good;
import salestax.domain.Receipt;
import salestax.domain.Taxing;
import salestax.util.Money;
import fit.ColumnFixture;
import fitlibrary.DoFixture;

import static salestax.domain.Good.TAX_BASIC;
import static salestax.domain.Good.TAX_IMPORT;

public class SalesDoFixture extends DoFixture {

	private Receipt receipt = new Receipt();

	public class SetupBasketFixture extends ColumnFixture {

		public int quantity;
		public String description;
		public double price;
		public boolean basictax;
		public boolean imported;
		public String exception;

		@Override
		public void reset() {
			exception = "";
		}

		@Override
		public void execute() {
			try {
				final Good good = new Good(description, new Money(price), taxings());
				receipt.addItem(good, quantity);
			} catch (IllegalArgumentException ex) {
				exception = ex.getMessage();
			}
		}

		private Taxing[] taxings() {
			ArrayList<Taxing> taxings = new ArrayList<Taxing>();
			if (basictax) taxings.add(TAX_BASIC);
			if (imported) taxings.add(TAX_IMPORT);
			
			return taxings.toArray(new Taxing[taxings.size()]);
		}

		public String exception() {
			return exception;
		}
	}

	public SetupBasketFixture basketContents() {
		return new SetupBasketFixture();
	}

	public List<Receipt.Line> receiptLines() {
		return receipt.lines();
	}

	public Receipt receipt() {
		return receipt;
	}

	// supports Money comparison in this fixture
	public Money findMoney(String amountAsString) 
	{ 
		return new Money(Double.valueOf(amountAsString));
	} 

	public String showMoney(Money money) 
	{ 
		return String.valueOf(money.amount());
	} 
}
