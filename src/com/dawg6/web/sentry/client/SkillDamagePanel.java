package com.dawg6.web.sentry.client;

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

}
