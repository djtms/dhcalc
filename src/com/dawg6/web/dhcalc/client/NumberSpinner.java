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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;
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

	@Override
	protected void keyPressed() {
		
		if (getValue() > max)
			normalize();
	}
}
