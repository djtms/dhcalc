package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;

public class DotEvent extends Event {

	public DotEvent() { 
		this.time = 1.0;
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		this.time = state.getDots().tick(state, log);

		queue.push(this);
	}

}
