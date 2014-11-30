package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class MultipleSummary implements Serializable {

	private static final long serialVersionUID = -4032965562400564710L;

	public int numSentries;
	public double duration;
	public String durationString;
	public double total;
	public double dps;
	public double maxDps;
}
