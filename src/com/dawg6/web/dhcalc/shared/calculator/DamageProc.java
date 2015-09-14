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

public enum DamageProc {

	Thunderfury(0.6, 1.0, SpecialItemType.Thunderfury.getName(), SpecialItemType.Thunderfury.getUrl(), new Adapter(){

		@Override
		public double getValue(SimulationState state) {
			return state.getData().isThunderfury() ? state.getData().getThunderfuryPercent() : 0.0;
		}}),
		
	Fulminator(1.0, 0, SpecialItemType.Fulminator.getName(), SpecialItemType.Fulminator.getUrl(), new Adapter(){

		@Override
		public double getValue(SimulationState state) {
			return (state.getData().isFulminator() && (state.getTargets().getNumAlive() > 1) && (state.getData().getTargetSpacing() <= 10)) ? state.getData().getFulminatorPercent() : 0.0;
		}}),
		
	;
	
	private final double proc;
	private final double icd;
	private final String url;
	private final String name;
	
	public interface Adapter { 
		double getValue(SimulationState state);
	}
	
	private Adapter adapter;
	
	private DamageProc(double proc, double icd, String name, String url, Adapter adapter) {
		this.adapter = adapter;
		this.proc = proc;
		this.icd = icd;
		this.url = url;
		this.name = name;
	}
	
	public double getScalar(SimulationState state) {
		return adapter.getValue(state);
	}

	public double getProc() {
		return proc;
	}

	public double getIcd() {
		return icd;
	}

	public String getUrl() {
		return url;
	}

	public String getLongName() {
		return name;
	}
}
