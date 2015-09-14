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

import java.util.Comparator;

public interface StatSorter extends Comparator<StatHolder> {

//	public static final StatSorter SENTRY_RUNE = new StatSorter() {
//
//		@Override
//		public int compare(StatHolder o1, StatHolder o2) {
//			return o1.build.getSentryRune().compareTo(o2.build.getSentryRune());
//		}
//	};
//
//	public static final StatSorter SKILLS = new StatSorter() {
//
//		@Override
//		public int compare(StatHolder o1, StatHolder o2) {
//			
//			for (int i = 0; i < 3; i++) {
//				SkillAndRune s1 = o1.build.getSkill(i);
//				SkillAndRune s2 = o2.build.getSkill(i);
//
//				int d = SkillAndRune.compare(s1, s2);
//				
//				if (d != 0)
//					return d;
//			}
//			
//			return 0;
//		}
//		
//	};
	
	public static final StatSorter COUNT = new StatSorter(){

		@Override
		public int compare(StatHolder o1, StatHolder o2) {
			return Long.compare(o2.stats.total, o1.stats.total);
		}
		
	};

	public class AverageCategorySorter implements StatSorter {


		private final StatCategory cat;

		public AverageCategorySorter(StatCategory cat) {
			this.cat = cat;
		}
		
		@Override
		public int compare(StatHolder o1, StatHolder o2) {
			Double e1 = o1.stats.average.get(cat);
			Double e2 = o2.stats.average.get(cat);
			
			return Double.compare(e2, e1);
		}
		
	}

	public class MaxCategorySorter implements StatSorter {


		private final StatCategory cat;

		public MaxCategorySorter(StatCategory cat) {
			this.cat = cat;
		}
		
		@Override
		public int compare(StatHolder o1, StatHolder o2) {
			DpsTableEntry e1 = o1.stats.max.get(cat);
			DpsTableEntry e2 = o2.stats.max.get(cat);
			
			return Double.compare(cat.getValue(e2), cat.getValue(e1));
		}
		
	}
}
