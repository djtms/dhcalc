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
import java.util.Map;

public class CompanionBuffEvent extends Event {

	private final boolean hasBat;
	private final boolean hasWolf;
	private final double batAmount;
	private final double cd;
	private final CoEBuffEvent coe;
	private final DamageType type;
	private final boolean syncWithCoe;
	
	public CompanionBuffEvent(CharacterData data, CoEBuffEvent coe) {
		this.hasBat = (data.getNumMarauders() >= 2)
				|| (data.getCompanionRune() == Rune.Bat);
		this.hasWolf = (data.getNumMarauders() >= 2)
				|| (data.getCompanionRune() == Rune.Wolf);

		this.batAmount = 50.0 + (data.isHexingPants() ? ((50.0 * data
				.getPercentMoving() * .25) - (50.0 * (1.0 - data
				.getPercentMoving()) * data.getHexingPantsPercent())) : 0.0);

		cd = 30.0 * (1.0 - data.getCdr());
		
		this.coe = coe;
		this.syncWithCoe = data.isSyncWithCoe();

		Rune r = data.getSkills().get(ActiveSkill.FoK);
		DamageType t = null;
		
		if (r != null)
			t = DamageFunction.getDamageType(new SkillAndRune(ActiveSkill.FoK, r));
		else {
			r = data.getSkills().get(ActiveSkill.RoV);

			if (r != null)
				t = DamageFunction.getDamageType(new SkillAndRune(ActiveSkill.RoV, r));
			else {
				for (Map.Entry<ActiveSkill, Rune> e : data.getSkills().entrySet()) {
					ActiveSkill skill = e.getKey();
					Rune rune = e.getValue();
					
					if ((skill.getSkillType() == SkillType.Spender)) {
						t = DamageFunction.getDamageType(new SkillAndRune(skill, rune));
						break;
					}
				}

				if (t == null) {
					for (Map.Entry<ActiveSkill, Rune> e : data.getSkills().entrySet()) {
						ActiveSkill skill = e.getKey();
						Rune rune = e.getValue();
						
						if ((skill.getSkillType() == SkillType.Primary)) {
							t = DamageFunction.getDamageType(new SkillAndRune(skill, rune));
							break;
						}
					}
				}
			}
		}
		
		if (t == null)
			t = DamageType.Physical;
		
		this.type = t;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		boolean use = false;

		if (syncWithCoe && (coe != null)) {
			DamageType t = coe.getDamageType();
			
			if (t != type) {
				this.time = coe.time;
				queue.push(this);
				return;
			}
		}
		
		if (hasBat && (state.getHatred() <= 50.0) && !state.getBuffs().isActive(Buff.Seethe)) {
			use = true;
		} else if (hasWolf && !state.getBuffs().isActive(Buff.OtherWolf)) {
			use = true;
		}

		if (use) {

			if (hasBat) {
				double h = state.addHatred(batAmount);

				if (h > 0) {
					Damage d = new Damage();
					d.shooter = "Companion";
					d.source = new DamageSource(ActiveSkill.Companion, Rune.Bat);
					d.hatred = h;
					d.currentHatred = state.getHatred();
					d.note = "Bat Hatred";
					d.currentDisc = state.getDisc();
					d.time = state.getTime();
					log.add(d);
				}
			}

			if (hasWolf) {
				state.getBuffs().set(Buff.Wolf, state.getTime() + 10.0);
				state.getBuffs().set(Buff.CompanionActive, Double.MAX_VALUE);

				Damage d = new Damage();
				d.shooter = "Companion";
				d.source = new DamageSource(ActiveSkill.Companion, Rune.Wolf);
				d.note = "Wolf Howl";
				d.time = state.getTime();
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				log.add(d);
			}

			this.time += cd;
		} else {
			this.time = queue.nextTime(this.time);
		}

		queue.push(this);
	}

}
