package com.dawg6.web.sentry.client;
import com.google.gwt.user.client.ui.DoubleBox;


public class DoubleSpinner extends BaseSpinner<Double> {

	/**
	 * @wbp.parser.constructor
	 */
	public DoubleSpinner() {
		this("0");
	}		

	public DoubleSpinner(String text) {
		super(new DoubleBox(), text, 0.0, Double.MAX_VALUE);
	}
	
	@Override
	protected void increment(int step) {
		setValue(getValue() + step);
	}

	@Override
	protected Double bound(Double value) {
		
		if (value == null)
			value = 0.0;
		
		return Math.min(max, Math.max(min, value));
	}

	@Override
	protected Double parse(String text) {
		try {
			return Double.parseDouble(text);
		} catch (Exception e) {
			return 0.0;
		}
	}

	@Override
	protected boolean isEqual(Double a, Double b) {
		
		if (a == null)
			a = 0.0;
		
		if (b == null)
			b = 0.0;
		
		return Math.abs(a - b) < 0.0000001;
	}
}
