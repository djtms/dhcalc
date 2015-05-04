package com.dawg6.web.dhcalc.shared.calculator.d3api;

import java.io.Serializable;

public class Value<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public T min;
	public T max;
}