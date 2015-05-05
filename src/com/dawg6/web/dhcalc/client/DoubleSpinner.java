/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;
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
