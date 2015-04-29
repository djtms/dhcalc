package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class BuffList implements Serializable {

	private static final long serialVersionUID = 6176187451717518503L;

	private Map<Buff, BuffState> buffs = new TreeMap<Buff, BuffState>();
	
	public BuffList() { 
		for (Buff b : Buff.values()) {
			buffs.put(b, new BuffState(b));
		}
	}

	public Map<Buff, BuffState> getBuffs() {
		return buffs;
	}

	public void setBuffs(Map<Buff, BuffState> buffs) {
		this.buffs = buffs;
	}

	public void set(Buff buff, double expires) {
		BuffState state = buffs.get(buff);
		state.setActive(true);
		state.setExpires(expires);
	}

	public void clear(Buff buff) {
		BuffState state = buffs.get(buff);
		state.setActive(false);
		state.setExpires(0.0);
	}

	public void expire(double time) {
		
		for (BuffState state : buffs.values()) {
			state.expire(time);
		}
	}

	public boolean isActive(Buff buff) {
		return buffs.get(buff).isActive();
	}
}
