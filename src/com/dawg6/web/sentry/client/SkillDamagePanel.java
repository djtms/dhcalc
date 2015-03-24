package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class SkillDamagePanel extends Composite {
	private final NumberSpinner sentryDamage;
	private final NumberSpinner eaDamage;
	private final NumberSpinner msDamage;
	private final NumberSpinner caDamage;
	private final NumberSpinner chakDamage;
	private final NumberSpinner impDamage;
	private final NumberSpinner haDamage;
	private final NumberSpinner esDamage;
	private final NumberSpinner bolasDamage;
	private final NumberSpinner efDamage;
	private final NumberSpinner grenadeDamage;
	private final NumberSpinner companionDamage;
	private final NumberSpinner stDamage;
	private final NumberSpinner rovDamage;
	
	public SkillDamagePanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Skill Damage Modifiers");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		captionPanel.setContentWidget(flexTable);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(5);
		flexTable.setWidget(0, 0, horizontalPanel);
		
		Anchor anchor = new Anchor("Ballistics:");
		anchor.setWordWrap(false);
		anchor.setText("Sentry");
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/sentry");
		horizontalPanel.add(anchor);
		
		Label label = new Label("Damage +%:");
		label.setWordWrap(false);
		horizontalPanel.add(label);
		
		sentryDamage = new NumberSpinner();
		sentryDamage.setVisibleLength(3);
		sentryDamage.setTitle("Additional damage done by Sentries (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(0, 1, sentryDamage);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_1.setSpacing(5);
		flexTable.setWidget(1, 0, horizontalPanel_1);
		
		Anchor anchor_1 = new Anchor("Ballistics:");
		anchor_1.setWordWrap(false);
		anchor_1.setText("EA");
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/elemental-arrow");
		horizontalPanel_1.add(anchor_1);
		
		Label label_1 = new Label("Damage +%:");
		label_1.setWordWrap(false);
		horizontalPanel_1.add(label_1);
		
		eaDamage = new NumberSpinner();
		eaDamage.setVisibleLength(3);
		eaDamage.setTitle("Additional damage done by the Elemental Arrow skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(1, 1, eaDamage);
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel_2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_2.setSpacing(5);
		flexTable.setWidget(2, 0, horizontalPanel_2);
		
		Anchor anchor_2 = new Anchor("Ballistics:");
		anchor_2.setWordWrap(false);
		anchor_2.setText("MS");
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/multishot");
		horizontalPanel_2.add(anchor_2);
		
		Label label_2 = new Label("Damage +%:");
		label_2.setWordWrap(false);
		horizontalPanel_2.add(label_2);
		
		msDamage = new NumberSpinner();
		msDamage.setVisibleLength(3);
		msDamage.setTitle("Additional damage done by the Multishot skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(2, 1, msDamage);
		
		HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
		horizontalPanel_3.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_3.setSpacing(5);
		flexTable.setWidget(3, 0, horizontalPanel_3);
		
		Anchor anchor_3 = new Anchor("Ballistics:");
		anchor_3.setWordWrap(false);
		anchor_3.setText("CA");
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/cluster-arrow");
		horizontalPanel_3.add(anchor_3);
		
		Label label_3 = new Label("Damage +%:");
		label_3.setWordWrap(false);
		horizontalPanel_3.add(label_3);
		
		caDamage = new NumberSpinner();
		caDamage.setVisibleLength(3);
		caDamage.setTitle("Additional damage done by the Cluster Arrow skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(3, 1, caDamage);
		
		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		horizontalPanel_4.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_4.setSpacing(5);
		flexTable.setWidget(4, 0, horizontalPanel_4);
		
		Anchor anchor_4 = new Anchor("Ballistics:");
		anchor_4.setWordWrap(false);
		anchor_4.setText("Chak");
		anchor_4.setTarget("_blank");
		anchor_4.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/chakram");
		horizontalPanel_4.add(anchor_4);
		
		Label label_4 = new Label("Damage +%:");
		label_4.setWordWrap(false);
		horizontalPanel_4.add(label_4);
		
		chakDamage = new NumberSpinner();
		chakDamage.setVisibleLength(3);
		chakDamage.setTitle("Additional damage done by the Chakram skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(4, 1, chakDamage);
		
		HorizontalPanel horizontalPanel_5 = new HorizontalPanel();
		horizontalPanel_5.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_5.setSpacing(5);
		flexTable.setWidget(5, 0, horizontalPanel_5);
		
		Anchor anchor_5 = new Anchor("Ballistics:");
		anchor_5.setWordWrap(false);
		anchor_5.setText("Imp");
		anchor_5.setTarget("_blank");
		anchor_5.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/impale");
		horizontalPanel_5.add(anchor_5);
		
		Label label_5 = new Label("Damage +%:");
		label_5.setWordWrap(false);
		horizontalPanel_5.add(label_5);
		
		impDamage = new NumberSpinner();
		impDamage.setVisibleLength(3);
		impDamage.setTitle("Additional damage done by the Impale skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(5, 1, impDamage);
		
		HorizontalPanel horizontalPanel_6 = new HorizontalPanel();
		horizontalPanel_6.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_6.setSpacing(5);
		flexTable.setWidget(6, 0, horizontalPanel_6);
		
		Anchor anchor_6 = new Anchor("Ballistics:");
		anchor_6.setHTML("HA");
		anchor_6.setWordWrap(false);
		anchor_6.setText("HA");
		anchor_6.setTarget("_blank");
		anchor_6.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/hungering-arrow");
		horizontalPanel_6.add(anchor_6);
		
		Label label_6 = new Label("Damage +%:");
		label_6.setWordWrap(false);
		horizontalPanel_6.add(label_6);
		
		haDamage = new NumberSpinner();
		haDamage.setVisibleLength(3);
		haDamage.setTitle("Additional damage done by the Hungering Arrow skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(6, 1, haDamage);
		
		HorizontalPanel horizontalPanel_7 = new HorizontalPanel();
		horizontalPanel_7.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_7.setSpacing(5);
		flexTable.setWidget(7, 0, horizontalPanel_7);
		
		Anchor anchor_7 = new Anchor("Ballistics:");
		anchor_7.setHTML("ES");
		anchor_7.setWordWrap(false);
		anchor_7.setText("ES");
		anchor_7.setTarget("_blank");
		anchor_7.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/entangling-shot");
		horizontalPanel_7.add(anchor_7);
		
		Label label_7 = new Label("Damage +%:");
		label_7.setWordWrap(false);
		horizontalPanel_7.add(label_7);
		
		esDamage = new NumberSpinner();
		esDamage.setVisibleLength(3);
		esDamage.setTitle("Additional damage done by the Entangling Shot skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(7, 1, esDamage);
		
		HorizontalPanel horizontalPanel_8 = new HorizontalPanel();
		horizontalPanel_8.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_8.setSpacing(5);
		flexTable.setWidget(8, 0, horizontalPanel_8);
		
		Anchor anchor_8 = new Anchor("Ballistics:");
		anchor_8.setHTML("Bolas");
		anchor_8.setWordWrap(false);
		anchor_8.setText("Bolas");
		anchor_8.setTarget("_blank");
		anchor_8.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/bolas");
		horizontalPanel_8.add(anchor_8);
		
		Label label_8 = new Label("Damage +%:");
		label_8.setWordWrap(false);
		horizontalPanel_8.add(label_8);
		
		bolasDamage = new NumberSpinner();
		bolasDamage.setVisibleLength(3);
		bolasDamage.setTitle("Additional damage done by the Bolas skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(8, 1, bolasDamage);
		
		HorizontalPanel horizontalPanel_9 = new HorizontalPanel();
		horizontalPanel_9.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_9.setSpacing(5);
		flexTable.setWidget(9, 0, horizontalPanel_9);
		
		Anchor anchor_9 = new Anchor("Ballistics:");
		anchor_9.setHTML("EF");
		anchor_9.setWordWrap(false);
		anchor_9.setText("EF");
		anchor_9.setTarget("_blank");
		anchor_9.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/evasive-fire");
		horizontalPanel_9.add(anchor_9);
		
		Label label_9 = new Label("Damage +%:");
		label_9.setWordWrap(false);
		horizontalPanel_9.add(label_9);
		
		efDamage = new NumberSpinner();
		efDamage.setVisibleLength(3);
		efDamage.setTitle("Additional damage done by the Evasive Fire skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(9, 1, efDamage);
		
		HorizontalPanel horizontalPanel_10 = new HorizontalPanel();
		horizontalPanel_10.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_10.setSpacing(5);
		flexTable.setWidget(10, 0, horizontalPanel_10);
		
		Anchor anchor_10 = new Anchor("Ballistics:");
		anchor_10.setHTML("Grenade");
		anchor_10.setWordWrap(false);
		anchor_10.setText("Grenade");
		anchor_10.setTarget("_blank");
		anchor_10.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/grenade");
		horizontalPanel_10.add(anchor_10);
		
		Label label_10 = new Label("Damage +%:");
		label_10.setWordWrap(false);
		horizontalPanel_10.add(label_10);
		
		grenadeDamage = new NumberSpinner();
		grenadeDamage.setVisibleLength(3);
		grenadeDamage.setTitle("Additional damage done by the Grenade skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(10, 1, grenadeDamage);
		
		HorizontalPanel horizontalPanel_11 = new HorizontalPanel();
		horizontalPanel_11.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_11.setSpacing(5);
		flexTable.setWidget(11, 0, horizontalPanel_11);
		
		Anchor anchor_11 = new Anchor("Ballistics:");
		anchor_11.setWordWrap(false);
		anchor_11.setText("Companion");
		anchor_11.setTarget("_blank");
		anchor_11.setHTML("Companion");
		anchor_11.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/companion");
		horizontalPanel_11.add(anchor_11);
		
		Label label_11 = new Label("Damage +%:");
		label_11.setWordWrap(false);
		horizontalPanel_11.add(label_11);
		
		companionDamage = new NumberSpinner();
		companionDamage.setVisibleLength(3);
		companionDamage.setTitle("Additional damage done by the Companion skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(11, 1, companionDamage);
		
		HorizontalPanel horizontalPanel_12 = new HorizontalPanel();
		horizontalPanel_12.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_12.setSpacing(5);
		flexTable.setWidget(12, 0, horizontalPanel_12);
		
		Anchor anchor_12 = new Anchor("Spike Trap:");
		anchor_12.setWordWrap(false);
		anchor_12.setText("Spike Trap");
		anchor_12.setTarget("_blank");
		anchor_12.setHTML("Spike Trap");
		anchor_12.setHref(ActiveSkill.ST.getUrl());
		horizontalPanel_12.add(anchor_12);
		
		Label label_12 = new Label("Damage +%:");
		label_12.setWordWrap(false);
		horizontalPanel_12.add(label_12);
		
		stDamage = new NumberSpinner();
		stDamage.setVisibleLength(3);
		stDamage.setTitle("Additional damage done by the Spike Trap skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(12, 1, stDamage);
		
		HorizontalPanel horizontalPanel_13 = new HorizontalPanel();
		horizontalPanel_13.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_13.setSpacing(5);
		flexTable.setWidget(13, 0, horizontalPanel_13);
		
		Anchor anchor_13 = new Anchor();
		anchor_13.setWordWrap(false);
		anchor_13.setText("Rain of Vengeance");
		anchor_13.setTarget("_blank");
		anchor_13.setHref(ActiveSkill.RoV.getUrl());
		horizontalPanel_13.add(anchor_13);
		
		Label label_13 = new Label("Damage +%:");
		label_13.setWordWrap(false);
		horizontalPanel_13.add(label_13);
		
		rovDamage = new NumberSpinner();
		rovDamage.setVisibleLength(3);
		rovDamage.setTitle("Additional damage done by the Rain of Vengeance skill (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(13, 1, rovDamage);
	}
	public NumberSpinner getSentryDamage() {
		return sentryDamage;
	}
	public NumberSpinner getEaDamage() {
		return eaDamage;
	}
	public NumberSpinner getMsDamage() {
		return msDamage;
	}
	public NumberSpinner getCaDamage() {
		return caDamage;
	}
	public NumberSpinner getChakDamage() {
		return chakDamage;
	}
	public NumberSpinner getImpDamage() {
		return impDamage;
	}
	public NumberSpinner getHaDamage() {
		return haDamage;
	}
	public NumberSpinner getEsDamage() {
		return esDamage;
	}
	public NumberSpinner getBolasDamage() {
		return bolasDamage;
	}
	public NumberSpinner getEfDamage() {
		return efDamage;
	}
	public NumberSpinner getGrenadeDamage() {
		return grenadeDamage;
	}
	public NumberSpinner getCompanionDamage() {
		return companionDamage;
	}
	public NumberSpinner getStDamage() {
		return stDamage;
	}
	public NumberSpinner getRovDamage() {
		return rovDamage;
	}

}
