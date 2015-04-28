package com.dawg6.web.sentry.client;
import com.google.gwt.user.client.ui.LongBox;


public class LongSpinner extends BaseSpinner<Long> {

	/**
	 * @wbp.parser.constructor
	 */
	public LongSpinner() {
		this("0");
	}		

	public LongSpinner(String text) {
		super(new LongBox(), text, 0L, Long.MAX_VALUE);
	}
	
	@Override
	protected void increment(int step) {
		setValue(getValue() + step);
	}
	
	@Override
	protected Long bound(Long value) {
		
		if (value == null)
			value = 0L;
		
		return Math.min(max, Math.max(min, value));
	}

	@Override
	protected Long parse(String text) {
		try {
			return Long.parseLong(text);
		} catch (Exception e) {
			return 0L;
		}
	}

	@Override
	protected boolean isEqual(Long a, Long b) {
		
		if (a == null)
			a = 0L;
		
		if (b == null)
			b = 0L;
		
		return a.equals(b);
	}
}
