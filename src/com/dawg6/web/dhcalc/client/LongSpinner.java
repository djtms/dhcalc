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
import com.google.gwt.user.client.ui.LongBox;


public class LongSpinner extends BaseSpinner<Long> {

	/**
	 * @wbp.parser.constructor
	 */
	public LongSpinner() {
		this("0");
	}		

	public LongSpinner(String text) {
		super(new LongBox(), text, 0L, Long.MAX_VALUE, 1L);
	}
	
	@Override
	protected void increment(int step) {
		setValue(getValue() + (step * this.increment));
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

	@Override
	protected void keyPressed() {
		
		if (getValue() > max)
			normalize();
	}
}
