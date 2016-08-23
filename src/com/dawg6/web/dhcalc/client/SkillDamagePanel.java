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
package com.dawg6.web.dhcalc.client;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import com.dawg6.gwt.client.widgets.SimpleCaptionPanel;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.SkillType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class SkillDamagePanel extends Composite {
	
	private final Map<ActiveSkill, Holder> fields = new TreeMap<ActiveSkill, Holder>();
	
	private final ListBox listBox;
	private final Button addButton;
	private final FlexTable flexTable;
	
	private class Holder {
		public NumberSpinner spinner;
		public int row;
		public Button removeButton;
		public Anchor anchor;
		public ActiveSkill skill;
	}
	
	public SkillDamagePanel() {

		SimpleCaptionPanel SimpleCaptionPanel = new SimpleCaptionPanel("Skill Damage Modifiers");
		initWidget(SimpleCaptionPanel);
		
		flexTable = new FlexTable();
		SimpleCaptionPanel.setContentWidget(flexTable);		

		List<ActiveSkill> list = new Vector<ActiveSkill>(ActiveSkill.values().length);
		listBox = new ListBox();
		
		for (ActiveSkill skill: ActiveSkill.values()) {
			if (skill.doesDamage() && (skill != ActiveSkill.BOLT) && (skill.getSkillType() != SkillType.NA)) {
				list.add(skill);
			}
		}

		Collections.sort(list, new Comparator<ActiveSkill>(){

			@Override
			public int compare(ActiveSkill o1, ActiveSkill o2) {
				return o1.getLongName().compareTo(o2.getLongName());
			}});
		
		for (ActiveSkill s : list) {
			listBox.addItem(s.getLongName(), s.name());
		}

		listBox.setSelectedIndex(0);
		addButton = new Button("Add");

		addButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				addSkill();
			}});
		
		flexTable.setWidget(0, 0, new Label("Skill:", false));
		flexTable.setWidget(0, 1, listBox);
		flexTable.getFlexCellFormatter().setColSpan(0, 1, 2);
		flexTable.setWidget(0, 2, addButton);
		
	}

	protected void addSkill() {
		int i = listBox.getSelectedIndex();
		
		if (i < 0)
			return;
		
		String s = listBox.getValue(i);
		listBox.removeItem(i);
		ActiveSkill skill = ActiveSkill.valueOf(s);
		
		addSkill(skill, 0);
	}
	
	private void addSkill(ActiveSkill skill, int value) {
		int row = fields.size() + 1;
		
		final Holder holder = new Holder();
		holder.anchor = new Anchor(skill.getLongName());
		holder.anchor.setHref(skill.getUrl());
		holder.anchor.setTarget("_blank");
		holder.removeButton = new Button("Remove");
		holder.row = row;
		holder.spinner = new NumberSpinner();
		holder.spinner.setVisibleLength(4);
		holder.spinner.setTitle("Additional damage done by " + skill.getLongName() + " skill, as shown in-game in the Character Details screen, under Offense");
		holder.spinner.setValue(value);
		holder.skill = skill;
		
		holder.removeButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				removeSkill(holder);
			}});
		
		fields.put(skill, holder);
		
		flexTable.setWidget(row, 0, holder.anchor);
		flexTable.setWidget(row, 1, new Label("+ %", false));
		flexTable.setWidget(row, 2, holder.spinner);
		flexTable.setWidget(row, 3, holder.removeButton);
	}

	protected void removeSkill(Holder holder) {
		int row = holder.row;
		ActiveSkill skill = holder.skill;
		
		flexTable.removeRow(row);
		
		fields.remove(holder.skill);
		
		for (Holder h : fields.values()) {
			if (h.row > row)
				h.row--;
		}
		
		int n = listBox.getItemCount();
		int index = -1;
		
		for (int i = 0; i < n; i++) {
			String s = listBox.getItemText(i);
			
			if (skill.getLongName().compareTo(s) > 0) {
				index = i;
				break;
			}
		}
		
		if (index < 0) {
			listBox.addItem(skill.getLongName(), skill.name());
			listBox.setSelectedIndex(n);
		} else {
			listBox.insertItem(skill.getLongName(), skill.name(), index);
			listBox.setSelectedIndex(index);
		}
	}

	public void setValues(Map<ActiveSkill, Double> map) {
		
		for (Holder h : fields.values()) {
			ActiveSkill s = h.skill;
			Double d = map.get(s);
			
			if ((d == null) || (d == 0.0))
				removeSkill(h);
			else
				h.spinner.setValue((int)Math.round(d * 100.0));
		}
		
		for (Map.Entry<ActiveSkill, Double> e : map.entrySet()) {
			
			ActiveSkill s = e.getKey();
			Double d = e.getValue();
			
			if ((d != null) && (d > 0.0)) {
				Holder h = fields.get(s);
				
				if (h == null) {
					addSkill(s, (int)Math.round(d * 100.0));
					
					int n = listBox.getItemCount();
					
					for (int i = 0; i < n; i++) {
						String v = listBox.getValue(i);
						
						if (v.equals(s.name())) {
							listBox.removeItem(i);
							break;
						}
					}
				}
			}
		}
	}
	
	public Map<ActiveSkill, Double> getValues() {
		
		Map<ActiveSkill, Double> map = new TreeMap<ActiveSkill, Double>();
		
		for (Holder h : fields.values()) {
			
			int value = h.spinner.getValue();
			
			if (value > 0) {
				map.put(h.skill, value / 100.0);
			}
		}

		return map;
	}
}
