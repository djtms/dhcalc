package com.dawg6.web.sentry.shared.calculator;

import java.util.Collection;
import java.util.Vector;

public class DamageMultiplierList extends Vector<DamageMultiplier>  implements Comparable<DamageMultiplierList> {

	private static final long serialVersionUID = 1L;
	
	public DamageMultiplierList() { }

	@Override
	public boolean add(DamageMultiplier item) {
		if (!contains(item))
			return super.add(item);
		else
			return false;
	}
	
	public void addAll(DamageMultiplier[] items) {
		for (DamageMultiplier d : items) 
			add(d);
	}
	
	@Override
	public boolean addAll(Collection<? extends DamageMultiplier> items) {
		
		for (DamageMultiplier d : items) 
			add(d);
		
		return true;
	}
	
	public DamageMultiplierList(DamageMultiplier... items) {
		addAll(items);
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();

		boolean first = true;
		for (DamageMultiplier s : this) {
			
			if (first)
				first = false;
			else
				b.append(" + ");
			
			b.append(s.getAbbreviation());
		}
		
		return b.toString();
	}

	@Override
	public int compareTo(DamageMultiplierList o) {
		return toString().compareTo(o.toString());
	}
}
