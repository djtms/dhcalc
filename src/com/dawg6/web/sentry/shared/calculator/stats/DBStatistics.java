package com.dawg6.web.sentry.shared.calculator.stats;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.dawg6.web.sentry.shared.calculator.Build;

public class DBStatistics implements Serializable {

	private static final long serialVersionUID = 7235257712144457043L;

	public Statistics stats = new Statistics();
	public Map<Build, Statistics> builds = new TreeMap<Build, Statistics>();
}
