package com.dawg6.web.dhcalc.client;

import java.util.Map;
import java.util.TreeMap;

import com.dawg6.web.dhcalc.shared.calculator.DamageType;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class DamageTypePanel extends Composite {

	private final Map<DamageType, NumberSpinner> fields = new TreeMap<DamageType, NumberSpinner>();
	
	public DamageTypePanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Elemental Damage Modifiers");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		captionPanel.setContentWidget(flexTable);		

		int n = 0;
		
		for (DamageType type : DamageType.values()) {
			Label label = new Label(type.getLongName() + " +%:");
			label.setWordWrap(false);
			flexTable.setWidget(n, 0, label);
			
			NumberSpinner spinner = new NumberSpinner();
			spinner.setVisibleLength(3);
			fields.put(type, spinner);
			spinner.setTitle("Additional damage done by " + type.getLongName() + " skills, as a percent, as shown in Character details screen under Offense.");
			flexTable.setWidget(n, 1, spinner);
			
			n++;
		}
	}

	public void setValues(Map<DamageType, Double> map) {
		
		for (DamageType type : DamageType.values()) {
			Double d = map.get(type);
			
			if (d == null)
				d = 0.0;
			
			fields.get(type).setValue((int)Math.round(d * 100.0));
		}
	}
	
	public Map<DamageType, Double> getValues() {
		Map<DamageType, Double> map = new TreeMap<DamageType, Double>();

		for (DamageType type : DamageType.values()) {
			int d = fields.get(type).getValue();
			
			if (d > 0) {
				map.put(type, d / 100.0);
			}
		}
		
		return map;
	}
}
