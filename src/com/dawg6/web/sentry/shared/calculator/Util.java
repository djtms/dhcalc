package com.dawg6.web.sentry.shared.calculator;



public class Util {

	private static final Util instance = new Util();
	private Formatter formatter;
	
	private Util() { }
	
	public static Util getInstance() { return instance; }
	
	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}
	
	public static String format(double d) {
		
		if (instance.formatter == null) {
			return String.valueOf(d);
		} else {
			return instance.formatter.format(d);
		}
	}
	
	public interface Formatter {
		String format(double d);
	}
}
