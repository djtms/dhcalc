package com.dawg6.web.dhcalc.shared.calculator;

import java.io.Serializable;

public class BuffHolder implements Serializable {

	private static final long serialVersionUID = -2853766306122780821L;

	private final Buff buff;
	private final double uptime;
	private double avail;
	private final double cd;
	private final double duration;

	public BuffHolder(Buff buff, double duration, double uptime) {
		this.buff = buff;
		this.duration = duration;
		this.uptime = uptime;

		if (uptime < 0) {
			this.cd = Double.MAX_VALUE;
			this.avail = 0;
		} else if (uptime > 0) {
			this.cd = duration / uptime;
			this.avail = 0;
		} else {
			this.cd = Double.MAX_VALUE;
			this.avail = Double.MAX_VALUE;
		}

	}

	public double getAvail() {
		return avail;
	}

	public void setAvail(double avail) {
		this.avail = avail;
	}

	public double getCd() {
		return cd;
	}

	public Buff getBuff() {
		return buff;
	}

	public double getUptime() {
		return uptime;
	}

	public double getDuration() {
		return duration;
	}

}
