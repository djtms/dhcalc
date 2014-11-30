package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class FiringData {

	private final SkillAction[] actions;
	private final SkillSet skills;
	private final BreakPoint bp;
	public static final int DURATION = 30;
	
	private FiringData(BreakPoint bp, SkillAction[] actions) {
		this.actions = actions;
		this.bp = bp;
		this.skills = new SkillSet();

		for (SkillAction a : actions) {
			if (a.getSkill() != ActiveSkill.BOLT)
				skills.add(a.getSkill());
		}

		FiringData.add(bp, skills, this);
	}

	public SkillAction[] getActions() {
		return actions;
	}

	public SkillSet getSkills() {
		return skills;
	}

	public BreakPoint getBreakPoint() {
		return bp;
	}

	@Override
	public String toString() {
		return actions.toString();
	}

	private static void add(BreakPoint bp, SkillSet skills,
			FiringData data) {

		synchronized (map) {
			Map<BreakPoint, FiringData> e = map.get(skills);

			if (e == null) {
				e = new TreeMap<BreakPoint, FiringData>();
				map.put(skills, e);
			}

			e.put(bp, data);
		}
	}

	private static Map<SkillSet, Map<BreakPoint, FiringData>> map = new TreeMap<SkillSet, Map<BreakPoint, FiringData>>();

	public static FiringData find(SkillSet skills, BreakPoint bp) {
		synchronized (map) {
			Map<BreakPoint, FiringData> e = map.get(skills);

			if (e != null) {
				return e.get(bp);
			}
		}

		return null;
	}

	public static final FiringData[] ALL = {

			// 0 Spenders (bolts only)

			new FiringData(BreakPoint.ALL[0],
					new SkillAction[] { new SkillAction(ActiveSkill.BOLT, 37) }),
			new FiringData(BreakPoint.ALL[1],
					new SkillAction[] { new SkillAction(ActiveSkill.BOLT, 42) }),
			new FiringData(BreakPoint.ALL[2],
					new SkillAction[] { new SkillAction(ActiveSkill.BOLT, 49) }),
			new FiringData(BreakPoint.ALL[3],
					new SkillAction[] { new SkillAction(ActiveSkill.BOLT, 60) }),
			new FiringData(BreakPoint.ALL[4],
					new SkillAction[] { new SkillAction(ActiveSkill.BOLT, 74) }),
			new FiringData(BreakPoint.ALL[5],
					new SkillAction[] { new SkillAction(ActiveSkill.BOLT, 97) }),
			new FiringData(
					BreakPoint.ALL[6],
					new SkillAction[] { new SkillAction(ActiveSkill.BOLT, 150) }),

			// 1 Spender

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.BOLT, 24) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.BOLT, 31) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.BOLT, 36) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.BOLT, 48) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.BOLT, 61) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.BOLT, 83) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.BOLT, 136) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 19),
					new SkillAction(ActiveSkill.BOLT, 18) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.BOLT, 21) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.BOLT, 24) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 30),
					new SkillAction(ActiveSkill.BOLT, 30) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.BOLT, 49) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.BOLT, 64) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 38),
					new SkillAction(ActiveSkill.BOLT, 112) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 19),
					new SkillAction(ActiveSkill.BOLT, 18) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.BOLT, 21) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.BOLT, 24) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 30),
					new SkillAction(ActiveSkill.BOLT, 30) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.BOLT, 49) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 33),
					new SkillAction(ActiveSkill.BOLT, 64) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 38),
					new SkillAction(ActiveSkill.BOLT, 112) }),

			new FiringData(BreakPoint.ALL[0],
					new SkillAction[] { new SkillAction(ActiveSkill.CHAK, 37) }),
			new FiringData(BreakPoint.ALL[1],
					new SkillAction[] { new SkillAction(ActiveSkill.CHAK, 42) }),
			new FiringData(BreakPoint.ALL[2],
					new SkillAction[] { new SkillAction(ActiveSkill.CHAK, 49) }),
			new FiringData(BreakPoint.ALL[3],
					new SkillAction[] { new SkillAction(ActiveSkill.CHAK, 60) }),
			new FiringData(BreakPoint.ALL[4],
					new SkillAction[] { new SkillAction(ActiveSkill.CHAK, 74) }),
			new FiringData(BreakPoint.ALL[5],
					new SkillAction[] { new SkillAction(ActiveSkill.CHAK, 97) }),
			new FiringData(
					BreakPoint.ALL[6],
					new SkillAction[] { new SkillAction(ActiveSkill.CHAK, 150) }),

			new FiringData(BreakPoint.ALL[0],
					new SkillAction[] { new SkillAction(ActiveSkill.EA, 37) }),
			new FiringData(BreakPoint.ALL[1],
					new SkillAction[] { new SkillAction(ActiveSkill.EA, 42) }),
			new FiringData(BreakPoint.ALL[2],
					new SkillAction[] { new SkillAction(ActiveSkill.EA, 49) }),
			new FiringData(BreakPoint.ALL[3],
					new SkillAction[] { new SkillAction(ActiveSkill.EA, 60) }),
			new FiringData(BreakPoint.ALL[4],
					new SkillAction[] { new SkillAction(ActiveSkill.EA, 74) }),
			new FiringData(BreakPoint.ALL[5],
					new SkillAction[] { new SkillAction(ActiveSkill.EA, 97) }),
			new FiringData(BreakPoint.ALL[6],
					new SkillAction[] { new SkillAction(ActiveSkill.EA, 150) }),

			// 2 Spenders

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 12),
					new SkillAction(ActiveSkill.BOLT, 12) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.BOLT, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.BOLT, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.BOLT, 24) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.BOLT, 37) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.MS, 29),
					new SkillAction(ActiveSkill.BOLT, 54) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.MS, 36),
					new SkillAction(ActiveSkill.BOLT, 100) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 12),
					new SkillAction(ActiveSkill.BOLT, 12) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.BOLT, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.BOLT, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.BOLT, 24) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.BOLT, 37) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.IMP, 29),
					new SkillAction(ActiveSkill.BOLT, 54) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.IMP, 36),
					new SkillAction(ActiveSkill.BOLT, 100) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.CHAK, 24) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.CHAK, 31) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.CHAK, 36) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.CHAK, 48) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.CHAK, 61) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.CHAK, 83) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.CHAK, 136) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.EA, 31) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.EA, 36) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.EA, 48) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.EA, 61) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.EA, 83) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.EA, 136) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 19),
					new SkillAction(ActiveSkill.IMP, 18),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 30),
					new SkillAction(ActiveSkill.IMP, 30),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.BOLT, 24) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.IMP, 32),
					new SkillAction(ActiveSkill.BOLT, 32) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 38),
					new SkillAction(ActiveSkill.IMP, 38),
					new SkillAction(ActiveSkill.BOLT, 74) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 19),
					new SkillAction(ActiveSkill.CHAK, 18) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.CHAK, 21) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.CHAK, 24) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 30),
					new SkillAction(ActiveSkill.CHAK, 30) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.CHAK, 49) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.CHAK, 64) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 38),
					new SkillAction(ActiveSkill.CHAK, 112) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 19),
					new SkillAction(ActiveSkill.EA, 18) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.EA, 21) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 30),
					new SkillAction(ActiveSkill.EA, 30) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.EA, 49) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.EA, 64) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 38),
					new SkillAction(ActiveSkill.EA, 112) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 19),
					new SkillAction(ActiveSkill.CHAK, 18) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.CHAK, 21) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.CHAK, 24) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 30),
					new SkillAction(ActiveSkill.CHAK, 30) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.CHAK, 49) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 33),
					new SkillAction(ActiveSkill.CHAK, 64) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 38),
					new SkillAction(ActiveSkill.CHAK, 112) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 19),
					new SkillAction(ActiveSkill.EA, 18) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.EA, 21) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 30),
					new SkillAction(ActiveSkill.EA, 30) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.EA, 49) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 33),
					new SkillAction(ActiveSkill.EA, 64) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 38),
					new SkillAction(ActiveSkill.EA, 112) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CHAK, 19),
					new SkillAction(ActiveSkill.EA, 18) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CHAK, 21),
					new SkillAction(ActiveSkill.EA, 21) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CHAK, 25),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CHAK, 30),
					new SkillAction(ActiveSkill.EA, 30) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CHAK, 25),
					new SkillAction(ActiveSkill.EA, 49) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CHAK, 49),
					new SkillAction(ActiveSkill.EA, 48) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CHAK, 75),
					new SkillAction(ActiveSkill.EA, 75) }),

			// 3 Spenders

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 12),
					new SkillAction(ActiveSkill.IMP, 12),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.IMP, 10),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.IMP, 12),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.BOLT, 0) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.BOLT, 13) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 31),
					new SkillAction(ActiveSkill.IMP, 31),
					new SkillAction(ActiveSkill.BOLT, 22) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.IMP, 33),
					new SkillAction(ActiveSkill.BOLT, 70) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 12),
					new SkillAction(ActiveSkill.CHAK, 12) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.CHAK, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.CHAK, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.CHAK, 24) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.CHAK, 37) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.MS, 29),
					new SkillAction(ActiveSkill.CHAK, 54) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.MS, 36),
					new SkillAction(ActiveSkill.CHAK, 100) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 12),
					new SkillAction(ActiveSkill.EA, 12) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.EA, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.EA, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.MS, 24),
					new SkillAction(ActiveSkill.EA, 37) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.MS, 29),
					new SkillAction(ActiveSkill.EA, 54) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.MS, 36),
					new SkillAction(ActiveSkill.EA, 100) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 12),
					new SkillAction(ActiveSkill.CHAK, 12) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.CHAK, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.CHAK, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.CHAK, 24) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.CHAK, 37) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.IMP, 29),
					new SkillAction(ActiveSkill.CHAK, 54) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.IMP, 36),
					new SkillAction(ActiveSkill.CHAK, 100) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 12),
					new SkillAction(ActiveSkill.EA, 12) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.EA, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.EA, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.EA, 37) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.IMP, 29),
					new SkillAction(ActiveSkill.EA, 54) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.IMP, 36),
					new SkillAction(ActiveSkill.EA, 100) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.CHAK, 12),
					new SkillAction(ActiveSkill.EA, 12) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 11),
					new SkillAction(ActiveSkill.CHAK, 16),
					new SkillAction(ActiveSkill.EA, 15) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.CHAK, 18),
					new SkillAction(ActiveSkill.EA, 18) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 12),
					new SkillAction(ActiveSkill.CHAK, 24),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.CHAK, 31),
					new SkillAction(ActiveSkill.EA, 30) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 13),
					new SkillAction(ActiveSkill.CHAK, 42),
					new SkillAction(ActiveSkill.EA, 42) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.CA, 14),
					new SkillAction(ActiveSkill.CHAK, 68),
					new SkillAction(ActiveSkill.EA, 68) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 19),
					new SkillAction(ActiveSkill.IMP, 18),
					new SkillAction(ActiveSkill.CHAK, 0) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.CHAK, 0) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.CHAK, 0) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 30),
					new SkillAction(ActiveSkill.IMP, 30),
					new SkillAction(ActiveSkill.CHAK, 0) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.CHAK, 24) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.IMP, 32),
					new SkillAction(ActiveSkill.CHAK, 32) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 38),
					new SkillAction(ActiveSkill.IMP, 38),
					new SkillAction(ActiveSkill.CHAK, 74) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 19),
					new SkillAction(ActiveSkill.IMP, 18),
					new SkillAction(ActiveSkill.EA, 0) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.EA, 0) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.IMP, 24),
					new SkillAction(ActiveSkill.EA, 0) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 30),
					new SkillAction(ActiveSkill.IMP, 30),
					new SkillAction(ActiveSkill.EA, 0) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.IMP, 32),
					new SkillAction(ActiveSkill.EA, 32) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 38),
					new SkillAction(ActiveSkill.IMP, 38),
					new SkillAction(ActiveSkill.EA, 74) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 19),
					new SkillAction(ActiveSkill.CHAK, 9),
					new SkillAction(ActiveSkill.EA, 9) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 21),
					new SkillAction(ActiveSkill.CHAK, 11),
					new SkillAction(ActiveSkill.EA, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.CHAK, 12),
					new SkillAction(ActiveSkill.EA, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 30),
					new SkillAction(ActiveSkill.CHAK, 15),
					new SkillAction(ActiveSkill.EA, 15) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 25),
					new SkillAction(ActiveSkill.CHAK, 25),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 33),
					new SkillAction(ActiveSkill.CHAK, 32),
					new SkillAction(ActiveSkill.EA, 32) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.MS, 38),
					new SkillAction(ActiveSkill.CHAK, 56),
					new SkillAction(ActiveSkill.EA, 56) }),

			new FiringData(BreakPoint.ALL[0], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 19),
					new SkillAction(ActiveSkill.CHAK, 9),
					new SkillAction(ActiveSkill.EA, 9) }),
			new FiringData(BreakPoint.ALL[1], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 21),
					new SkillAction(ActiveSkill.CHAK, 11),
					new SkillAction(ActiveSkill.EA, 10) }),
			new FiringData(BreakPoint.ALL[2], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.CHAK, 12),
					new SkillAction(ActiveSkill.EA, 12) }),
			new FiringData(BreakPoint.ALL[3], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 30),
					new SkillAction(ActiveSkill.CHAK, 15),
					new SkillAction(ActiveSkill.EA, 15) }),
			new FiringData(BreakPoint.ALL[4], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 25),
					new SkillAction(ActiveSkill.CHAK, 25),
					new SkillAction(ActiveSkill.EA, 24) }),
			new FiringData(BreakPoint.ALL[5], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 33),
					new SkillAction(ActiveSkill.CHAK, 32),
					new SkillAction(ActiveSkill.EA, 32) }),
			new FiringData(BreakPoint.ALL[6], new SkillAction[] {
					new SkillAction(ActiveSkill.IMP, 38),
					new SkillAction(ActiveSkill.CHAK, 56),
					new SkillAction(ActiveSkill.EA, 56) }), };

	public Damage[] calculateDamages(Map<ActiveSkill, Rune> runes, CharacterData data) {
		List<Damage> list = new Vector<Damage>();
		
		Rune sentryRune = runes.get(ActiveSkill.SENTRY);

		if (sentryRune != Rune.Chain_of_Torment) {
			for (SkillAction a : actions) {
	
				Rune rune= runes.get(a.getSkill());
				
				if (rune == null)
					rune = Rune.None;
				
				list.addAll(DamageFunction.getDamages(new DamageSource(a.getSkill(), rune), a.getQty(), data));
					
			}
		}
		
		list.addAll(DamageFunction.getDamages(new DamageSource(ActiveSkill.SENTRY, sentryRune), this.bp.getQty(), data));
		
		// gem procs
		list.addAll(DamageFunction.getDamages(null, FiringData.DURATION, data));

		return list.toArray(new Damage[0]);
	}

}
