package com.dawg6.web.sentry.client;

import java.util.Set;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.DamageType;

public abstract class StatAdapter {

	public abstract Object apply(CharacterData data);
	
	public abstract void unapply(CharacterData data, Object token);
	
	public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
		return true;
	}
}
