package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class BuffState implements Serializable {

	private static final long serialVersionUID = 6150784391766289996L;

	private Buff buff;
	private boolean active;
	private double expires;

	public BuffState() {
	}

	public BuffState(Buff buff) {
		this.buff = buff;
		this.active = false;
		this.expires = 0.0;
	}

	public Buff getBuff() {
		return buff;
	}

	public void setBuff(Buff buff) {
		this.buff = buff;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getExpires() {
		return expires;
	}

	public void setExpires(double expires) {
		this.expires = expires;
	}

	public void expire(double time) {
		if (active && (time > expires))
			active = false;
	}
}
