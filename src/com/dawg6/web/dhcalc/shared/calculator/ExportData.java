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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ExportData implements Serializable {

	private static final long serialVersionUID = -5281919948781069134L;
	
	public CharacterData data;
	public Map<ActiveSkill, Rune> skills;
	public DamageResult output;
	public Map<DamageType, DamageHolder> types;
	public Map<DamageSource, DamageHolder> skillDamages;
	public Map<String, DamageHolder> shooterDamages;
	public List<MultipleSummary> multiple;
	public double sentryDps;
	public double totalDamage;

}