package com.dawg6.web.sentry.client;

import java.util.Map;
import java.util.TreeMap;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class SkillDamagePanel extends Composite {
	
	private final Map<ActiveSkill, NumberSpinner> fields = new TreeMap<ActiveSkill, NumberSpinner>();

	public SkillDamagePanel() {

		CaptionPanel captionPanel = new CaptionPanel("Skill Damage Modifiers");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		captionPanel.setContentWidget(flexTable);		

		int n = 0;
		
		for (ActiveSkill skill: ActiveSkill.values()) {

			if (skill.doesDamage() && (skill != ActiveSkill.BOLT)) {
				
				HorizontalPanel row = new HorizontalPanel();
				row.setSpacing(5);
				flexTable.setWidget(n, 0, row);
				
				Anchor anchor = new Anchor(skill.getLongName());
				anchor.setWordWrap(false);
				anchor.setTarget("_blank");
				anchor.setHref(skill.getUrl());
				row.add(anchor);
				
				Label label = new Label("+%:");
				label.setWordWrap(false);
				row.add(label);
				
				NumberSpinner spinner = new NumberSpinner();
				spinner.setVisibleLength(3);
				fields.put(skill, spinner);
				spinner.setTitle("Additional damage done by " + skill.getLongName() + " skills, as a percent, as shown in Character details screen under Offense.");
				flexTable.setWidget(n, 1, spinner);
				
				n++;
			}
		}
	}

	public void setValues(Map<ActiveSkill, Double> map) {
		
		for (ActiveSkill type : ActiveSkill.values()) {
			Double d = map.get(type);
			
			if (d == null)
				d = 0.0;
			
			NumberSpinner field = fields.get(type);
			
			if (field != null)
				field.setValue((int)Math.round(d * 100.0));
		}
	}
	
	public Map<ActiveSkill, Double> getValues() {
		Map<ActiveSkill, Double> map = new TreeMap<ActiveSkill, Double>();

		for (ActiveSkill type : ActiveSkill.values()) {
			NumberSpinner field = fields.get(type);

			if (field != null) {
				int d = fields.get(type).getValue();
				
				if (d > 0) {
					map.put(type, d / 100.0);
				}
			}
		}
		
		return map;
	}
}
