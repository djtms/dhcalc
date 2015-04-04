package com.dawg6.web.sentry.shared.calculator.stats;

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
