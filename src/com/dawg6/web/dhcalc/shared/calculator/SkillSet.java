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
