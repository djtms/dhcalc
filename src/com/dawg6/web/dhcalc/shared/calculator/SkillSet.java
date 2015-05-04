package com.dawg6.web.dhcalc.shared.calculator;

import java.util.Collection;
import java.util.TreeSet;

public class SkillSet extends TreeSet<ActiveSkill>  implements Comparable<SkillSet> {

	private static final long serialVersionUID = 1L;

	
	public SkillSet() { }

	public SkillSet(ActiveSkill s)  {
		add(s);
	}
	
	public SkillSet(ActiveSkill s1, ActiveSkill s2)  {
		add(s1);
		add(s2);
	}

	public SkillSet(ActiveSkill s1, ActiveSkill s2, ActiveSkill s3)  {
		add(s1);
		add(s2);
		add(s3);
	}

	public SkillSet(ActiveSkill[] skills) {
		for (ActiveSkill s : skills)
			add(s);
	}

	public SkillSet(Collection<ActiveSkill> skills) {
		super(skills);
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("{");

		boolean first = true;
		for (ActiveSkill s : this) {
			
			if (first)
				first = false;
			else
				b.append(",");
			
			b.append(s.getLongName());
		}
		
		b.append("}");
		
		return b.toString();
	}

	public String toShortString() {
		StringBuffer b = new StringBuffer();

		boolean first = true;
		for (ActiveSkill s : this) {
			
			if (first)
				first = false;
			else
				b.append("+");
			
			b.append(s.getShortName());
		}
		
		return b.toString();
	}

	@Override
	public int compareTo(SkillSet o) {
		return toString().compareTo(o.toString());
	}
}
