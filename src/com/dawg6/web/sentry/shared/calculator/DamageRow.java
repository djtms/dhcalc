package com.dawg6.web.sentry.shared.calculator;

public class DamageRow {

	public final DamageMultiplierList multipliers = new DamageMultiplierList();
	public final double scalar;
	public final boolean primary;
	public final int maxAdditional;
	public int numProjectiles;
	public DamageSource source;
	public String note;
	public DamageType type;
	public int radius;
	
	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, numProjectiles, 0, "", type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles,  String note,
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, numProjectiles, 0, note, type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles, int radius,
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, numProjectiles, radius, "", type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, 1, 0, "", type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional,  String note, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, 1, 0, note, type, multipliers);
	}

	public DamageRow(DamageSource source, double scalar,
			boolean primary, int maxAdditional,  String note, 
			DamageType type, DamageMultiplier... multipliers) {
		this(source, scalar, primary, maxAdditional, 1, 0, note, type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles, int radius, String note, DamageType type,
			DamageMultiplier... multipliers) {
		this(new DamageSource(skill, rune), scalar, primary, maxAdditional, numProjectiles, radius, note, type, multipliers);
	}

	public DamageRow(DamageSource source, double scalar,
				boolean primary, int maxAdditional, int numProjectiles, int radius, String note, DamageType type,
				DamageMultiplier... multipliers) {
		this.source = source;
		this.numProjectiles = numProjectiles;
		this.radius = radius;

		

		// Sentry damage and enforcer won't effect gem procs
		if (source.skill != null) {

			if (source.skill != ActiveSkill.Companion) {
				this.multipliers.add(DamageMultiplier.Sentry);
			}
			
			// Spitfire Turret's rockets don't get Enforce bonus?
			if ((source.skill != ActiveSkill.SENTRY) || (source.rune != Rune.Spitfire_Turret)
					|| !new DamageMultiplierList(multipliers).contains(DamageMultiplier.Rockets)) {
				this.multipliers.add(DamageMultiplier.Enforcer);
			}
		}
		
		this.multipliers.add(DamageMultiplier.CtW);
		this.multipliers.add(DamageMultiplier.BoT);
		this.multipliers.add(DamageMultiplier.BotP);
		this.multipliers.add(DamageMultiplier.OdysseysEnd);
		this.multipliers.add(DamageMultiplier.Wolf);
		this.multipliers.add(DamageMultiplier.Bbv);
		this.multipliers.add(DamageMultiplier.Paranoia);
		this.multipliers.add(DamageMultiplier.Piranhas);
		this.multipliers.add(DamageMultiplier.InnerSanctuary);
		this.multipliers.add(DamageMultiplier.CripplingWave);
		this.multipliers.add(DamageMultiplier.Conviction);
		this.multipliers.add(type.getMultiplier());
		this.multipliers.add(DamageMultiplier.Dexterity);
		this.multipliers.add(DamageMultiplier.MfD);
		this.multipliers.add(DamageMultiplier.Calamity);
		this.multipliers.add(DamageMultiplier.Toxicity);

		if (source.skill != ActiveSkill.Companion) {
			this.multipliers.add(DamageMultiplier.Taeguk);
			this.multipliers.add(DamageMultiplier.Harrington);
			this.multipliers.add(DamageMultiplier.Strongarm);
			this.multipliers.add(DamageMultiplier.Ambush);
			this.multipliers.add(DamageMultiplier.ArcheryDamage);
			this.multipliers.add(DamageMultiplier.Hysteria);
			this.multipliers.add(DamageMultiplier.Zeis);
			this.multipliers.add(DamageMultiplier.HexingPants);
			this.multipliers.add(DamageMultiplier.SteadyAim);
		} else {
			this.multipliers.add(DamageMultiplier.Enforcer);
			this.multipliers.add(DamageMultiplier.IAS);
			this.multipliers.add(DamageMultiplier.Companion);
		}
		
		if (source.skill != null) {
			if (source.skill.getDamageMultiplier() != null)
				this.multipliers.add(source.skill.getDamageMultiplier());
		}

		this.multipliers.addAll(multipliers);

		this.type = type;
		this.scalar = scalar;
		this.primary = primary;
		this.maxAdditional = maxAdditional;
		this.note = note;
	}
}