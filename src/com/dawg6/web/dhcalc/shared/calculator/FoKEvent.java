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
	private final CoEBuffEvent coe;
	private DamageType type;
	private final boolean syncWithCoe;

	public FoKEvent(CharacterData data, CoEBuffEvent coe) {
		this.rune = data.getSkills().get(ActiveSkill.FoK);
		this.cooldown = ((rune == Rune.Pinpoint_Accuracy) ? 15.0 : 10.0)
				* (1.0 - data.getCdr());
		this.time = 0.0;
		this.coe = coe;

		switch (rune) {
		case Pinpoint_Accuracy:
			type = DamageType.Lightning;
			break;

		case Bladed_Armor:
			type = DamageType.Cold;
			break;

		case Knives_Expert:
		case Fan_of_Daggers:
			type = DamageType.Fire;
			break;

		default:
			type = DamageType.Physical;
			break;

		}
		
		this.syncWithCoe = data.isSyncWithCoe();
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		if (syncWithCoe && (coe != null)) {
			DamageType t = coe.getDamageType();
			
			if (t != type) {
				this.time = coe.time;
				queue.push(this);
				return;
			}
		}

		List<Damage> dList = DamageFunction.getDamages(true, false, "Player",
				new DamageSource(ActiveSkill.FoK, rune), state);

		applyDamages(state, log, dList);

		Set<TargetType> targetsHit = new TreeSet<TargetType>();

		for (Damage d : dList) {
			if ((d.target != null) && (d.damage > 0)
					&& state.getTargets().getTarget(d.target).isAlive())
				targetsHit.add(d.target);
		}

		if (!targetsHit.isEmpty())
			applyDamages(state, log, DamageFunction.getDamages(false, false,
					"Player", null, state, targetsHit));

		state.setLastFoK(this.time);

		if (state.getData().isLGF()) // && (rune != Rune.Pinpoint_Accuracy))
			this.time += Math.max(this.cooldown, 30.0);
		else
			this.time += this.cooldown;

		queue.push(this);
	}

}
