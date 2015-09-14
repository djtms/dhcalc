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
package com.dawg6.web.dhcalc.shared.calculator.stats;

public enum StatCategory {
	Single("Single Target Non-Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getSingle();
		}
	}),

	Multiple("Multiple Targets Non-Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getMultiple();
		}
	}), SingleElite("Single Target Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getSingle_elite();
		}
	}), MultipleElite("Multiple Targets Elite DPS", new Evaluator() {

		@Override
		public double getValue(DpsTableEntry e) {
			return e.getMultiple_elite();
		}
	});

	private StatCategory(String description, Evaluator eval) {
		this.eval = eval;
		this.description = description;
	}

	private String description;
	private Evaluator eval;

	public String getDescription() {
		return this.description;
	}

	public double getValue(DpsTableEntry e) {
		return eval.getValue(e);
	}

	public interface Evaluator {
		double getValue(DpsTableEntry e);
	}
}