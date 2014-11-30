package com.dawg6.web.sentry.client;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class DamageTypePanel extends Composite {
	private final NumberSpinner coldDamage;
	private final NumberSpinner fireDamage;
	private final NumberSpinner lightningDamage;
	private final NumberSpinner physicalDamage;
	private final NumberSpinner poisonDamage;
	public DamageTypePanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Damage Type Modifiers");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		captionPanel.setContentWidget(flexTable);		
		Label label = new Label("Cold Damage +%:");
		label.setWordWrap(false);
		flexTable.setWidget(0, 0, label);
		
		coldDamage = new NumberSpinner();
		coldDamage.setVisibleLength(3);
		coldDamage.setTitle("Additional damage done by Cold skills (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(0, 1, coldDamage);
		
		Label label_1 = new Label("Fire Damage +%:");
		label_1.setWordWrap(false);
		flexTable.setWidget(1, 0, label_1);
		
		fireDamage = new NumberSpinner();
		fireDamage.setVisibleLength(3);
		fireDamage.setTitle("Additional damage done by Fire skills (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(1, 1, fireDamage);
		
		Label label_2 = new Label("Lightning Damage +%:");
		label_2.setWordWrap(false);
		flexTable.setWidget(2, 0, label_2);
		
		lightningDamage = new NumberSpinner();
		lightningDamage.setVisibleLength(3);
		lightningDamage.setTitle("Additional damage done by Lightning skills (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(2, 1, lightningDamage);
		
		Label label_3 = new Label("Physical Damage +%:");
		label_3.setWordWrap(false);
		flexTable.setWidget(3, 0, label_3);
		
		physicalDamage = new NumberSpinner();
		physicalDamage.setVisibleLength(3);
		physicalDamage.setTitle("Additional damage done by Physical skills (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(3, 1, physicalDamage);
		
		Label label_4 = new Label("Poison Damage +%:");
		label_4.setWordWrap(false);
		flexTable.setWidget(4, 0, label_4);
		
		poisonDamage = new NumberSpinner();
		poisonDamage.setVisibleLength(3);
		poisonDamage.setTitle("Additional damage done by Poison skills (as a percent), as shown in-game in the Character details screen under offense.");
		flexTable.setWidget(4, 1, poisonDamage);
	}
	public NumberSpinner getColdDamage() {
		return coldDamage;
	}
	public NumberSpinner getFireDamage() {
		return fireDamage;
	}
	public NumberSpinner getLightningDamage() {
		return lightningDamage;
	}
	public NumberSpinner getPhysicalDamage() {
		return physicalDamage;
	}
	public NumberSpinner getPoisonDamage() {
		return poisonDamage;
	}

}
