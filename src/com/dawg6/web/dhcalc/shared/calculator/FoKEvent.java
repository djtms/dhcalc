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

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FoKEvent extends CooldownEvent {

	private final Rune rune;

	public FoKEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.FoK);
		this.cooldown = ((rune == Rune.Pinpoint_Accuracy) ? 15.0 : 10.0) * (1.0 - data.getCdr());
		this.time = 0.0;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		List<Damage> dList = DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.FoK, rune), state);

		applyDamages(state, log, dList);

		Set<TargetType> targetsHit = new TreeSet<TargetType>();
		
		for (Damage d : dList) {
			if ((d.target != null) && (d.damage > 0) && state.getTargets().getTarget(d.target).isAlive())
				targetsHit.add(d.target);
		}
		
		if (!targetsHit.isEmpty())
			applyDamages(state, log, DamageFunction.getDamages(false, false, "Player", null, state, targetsHit));

		this.time += this.cooldown;
		
		queue.push(this);
	}

}
