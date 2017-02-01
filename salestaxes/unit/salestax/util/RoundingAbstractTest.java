package salestax.util;

import junit.framework.TestCase;

public abstract class RoundingAbstractTest extends TestCase {

	protected void assertResultEquals(double[] inputs, double[] outputs)
	{
		for (int i=0; i<inputs.length; i++) {
			assertEquals(outputs[i],round(String.valueOf(inputs[i])));
		} 
	}

	protected abstract double round(String amountAsString) 
	;
}
