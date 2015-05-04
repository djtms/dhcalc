package com.dawg6.web.dhcalc.shared.calculator.stats;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Statistics implements Serializable {

	private static final long serialVersionUID = 3696769404960522163L;

	public long total = 0;
	public Map<StatCategory, DpsTableEntry> max = new TreeMap<StatCategory, DpsTableEntry>();
	public Map<StatCategory, Double> average = new TreeMap<StatCategory, Double>();
}
