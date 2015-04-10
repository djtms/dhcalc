package com.dawg6.web.sentry.client;
import com.google.gwt.user.client.ui.IntegerBox;


public class NumberSpinner extends BaseSpinner<Integer> {

	/**
	 * @wbp.parser.constructor
	 */
	public NumberSpinner() {
		this("0");
	}		

	public NumberSpinner(String text) {
		super(new IntegerBox(), text, 0, Integer.MAX_VALUE);
	}
	
	@Override
	protected void increment(int step) {
		setValue(getValue() + step);
	}
	
	@Override
	protected Integer bound(Integer value) {
		
		if (value == null)
			value = 0;
		
		return Math.min(max, Math.max(min, value));
	}

	@Override
	protected Integer parse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	protected boolean isEqual(Integer a, Integer b) {
		
		if (a == null)
			a = 0;
		
		if (b == null)
			b = 0;
		
		return a.equals(b);
	}
}
