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
package com.dawg6.web.dhcalc.server.db.couchdb;

import java.util.Set;

import com.dawg6.d3api.shared.Realm;
import com.dawg6.web.dhcalc.shared.calculator.stats.DocumentBase;


public class AccountDocument extends DocumentBase {

	private static final long serialVersionUID = -6227570607442906634L;

	public static final String DOCUMENT_TYPE = "Account";

	private Set<Integer>  heroes;
	private Realm realm;
	private String profile;
	private int tag;
	
	public AccountDocument() {
		super(DOCUMENT_TYPE);
	}

	public Set<Integer> getHeroes() {
		return heroes;
	}

	public void setHeroes(Set<Integer> heroes) {
		this.heroes = heroes;
	}

	public Realm getRealm() {
		return realm;
	}

	public void setRealm(Realm realm) {
		this.realm = realm;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((realm == null) ? 0 : realm.hashCode());
		result = prime * result + tag;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		AccountDocument other = (AccountDocument) obj;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (realm != other.realm)
			return false;
		if (tag != other.tag)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return realm + "." + profile + "#" + tag;
	}
}
