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
package com.dawg6.web.dhcalc.shared.calculator.stats;

import java.io.Serializable;

import com.dawg6.d3api.shared.Realm;
import com.dawg6.web.dhcalc.shared.calculator.Build;

public class DpsTableEntry extends DocumentBase implements Serializable {

	private static final long serialVersionUID = -7605233453931536272L;

	public static final String DOCUMENT_TYPE = "DPS";
	
	public static final String REALM = "realm";
	public static final String BATTLETAG = "battletag";

	public static final String PROFILES = "profiles";

	public static final String BY_TIME = "profilesByTime";

	public static final String DPS_SUMMARY = "dpsSummary";
	
	protected String battletag;
	protected Realm realm;
	protected Long when;
	protected Double single;
	protected Double single_elite;
	protected Double multiple;
	protected Double multiple_elite;
	protected Integer paragon;
	protected Integer paragon_ias;
	protected Integer paragon_dex;
	protected Integer paragon_cdr;
	protected Integer paragon_cc;
	protected Integer paragon_chd;
	protected Integer paragon_hatred;
	protected Integer paragon_rcr;
	protected Integer paragon_ad;
	protected Build build;
	protected Integer bp;
	protected Double sentryDps;
	protected Double playerAps;
	protected String profile;
	protected Integer tag;
	protected Integer heroId;
	protected String heroName;
	protected Boolean seasonal;
	protected Boolean hardcore;
	protected Boolean dead;
	protected Double sheetDps;
	protected Integer level;

	public DpsTableEntry() {
		super(DOCUMENT_TYPE);
	}

	public String getBattletag() {
		return battletag;
	}

	public void setBattletag(String battletag) {
		this.battletag = battletag;
	}

	public Realm getRealm() {
		return realm;
	}

	public void setRealm(Realm realm) {
		this.realm = realm;
	}

	public Long getWhen() {
		return when;
	}

	public void setWhen(Long when) {
		this.when = when;
	}

	public Double getSingle() {
		return single;
	}

	public void setSingle(Double single) {
		this.single = single;
	}

	public Double getSingle_elite() {
		return single_elite;
	}

	public void setSingle_elite(Double single_elite) {
		this.single_elite = single_elite;
	}

	public Double getMultiple() {
		return multiple;
	}

	public void setMultiple(Double multiple) {
		this.multiple = multiple;
	}

	public Double getMultiple_elite() {
		return multiple_elite;
	}

	public void setMultiple_elite(Double multiple_elite) {
		this.multiple_elite = multiple_elite;
	}

	public Integer getParagon() {
		return paragon;
	}

	public void setParagon(Integer paragon) {
		this.paragon = paragon;
	}

	public Integer getParagon_ias() {
		return paragon_ias;
	}

	public void setParagon_ias(Integer paragon_ias) {
		this.paragon_ias = paragon_ias;
	}

	public Integer getParagon_cdr() {
		return paragon_cdr;
	}

	public void setParagon_cdr(Integer paragon_cdr) {
		this.paragon_cdr = paragon_cdr;
	}

	public Integer getParagon_cc() {
		return paragon_cc;
	}

	public void setParagon_cc(Integer paragon_cc) {
		this.paragon_cc = paragon_cc;
	}

	public Integer getParagon_chd() {
		return paragon_chd;
	}

	public void setParagon_chd(Integer paragon_chd) {
		this.paragon_chd = paragon_chd;
	}

	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}

	public Integer getBp() {
		return bp;
	}

	public void setBp(Integer bp) {
		this.bp = bp;
	}

	public Double getSentryDps() {
		return sentryDps;
	}

	public void setSentryDps(Double sentryDps) {
		this.sentryDps = sentryDps;
	}

	public Double getPlayerAps() {
		return playerAps;
	}

	public void setPlayerAps(Double playerAps) {
		this.playerAps = playerAps;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getHeroId() {
		return heroId;
	}

	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public Boolean getSeasonal() {
		return seasonal;
	}

	public void setSeasonal(Boolean seasonal) {
		this.seasonal = seasonal;
	}

	public Boolean getHardcore() {
		return hardcore;
	}

	public void setHardcore(Boolean hardcore) {
		this.hardcore = hardcore;
	}

	public Boolean getDead() {
		return dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	public Double getSheetDps() {
		return sheetDps;
	}

	public void setSheetDps(Double sheetDps) {
		this.sheetDps = sheetDps;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParagon_hatred() {
		return paragon_hatred;
	}

	public void setParagon_hatred(Integer paragon_hatred) {
		this.paragon_hatred = paragon_hatred;
	}

	public Integer getParagon_rcr() {
		return paragon_rcr;
	}

	public void setParagon_rcr(Integer paragon_rcr) {
		this.paragon_rcr = paragon_rcr;
	}

	public Integer getParagon_dex() {
		return paragon_dex;
	}

	public void setParagon_dex(Integer paragon_dex) {
		this.paragon_dex = paragon_dex;
	}

	public Integer getParagon_ad() {
		return paragon_ad;
	}

	public void setParagon_ad(Integer paragon_ad) {
		this.paragon_ad = paragon_ad;
	}
}
