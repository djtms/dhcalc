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
import com.dawg6.web.dhcalc.shared.calculator.DamageType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class DamageTypePanel extends Composite {
	
	private final Map<DamageType, Holder> fields = new TreeMap<DamageType, Holder>();
	
	private final ListBox listBox;
	private final Button addButton;
	private final FlexTable flexTable;
	
	private class Holder {
		public NumberSpinner spinner;
		public int row;
		public Button removeButton;
		public DamageType type;
	}
	
	public DamageTypePanel() {

		SimpleCaptionPanel SimpleCaptionPanel = new SimpleCaptionPanel("Elemental Damage Modifiers");
		initWidget(SimpleCaptionPanel);
		
		flexTable = new FlexTable();
		SimpleCaptionPanel.setContentWidget(flexTable);		

		List<DamageType> list = new Vector<DamageType>(DamageType.values().length);
		listBox = new ListBox();
		
		for (DamageType type: DamageType.values()) {
				list.add(type);
		}

		Collections.sort(list, new Comparator<DamageType>(){

			@Override
			public int compare(DamageType o1, DamageType o2) {
				return o1.getLongName().compareTo(o2.getLongName());
			}});
		
		for (DamageType s : list) {
			listBox.addItem(s.getLongName(), s.name());
		}

		listBox.setSelectedIndex(0);
		addButton = new Button("Add");

		addButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				addType();
			}});
		
		flexTable.setWidget(0, 0, new Label("Type:", false));
		flexTable.setWidget(0, 1, listBox);
		flexTable.getFlexCellFormatter().setColSpan(0, 1, 2);
		flexTable.setWidget(0, 2, addButton);
		
	}

	protected void addType() {
		int i = listBox.getSelectedIndex();
		
		if (i < 0)
			return;
		
		String s = listBox.getValue(i);
		listBox.removeItem(i);
		DamageType type = DamageType.valueOf(s);
		
		addType(type, 0);
	}
	
	private void addType(DamageType type, int value) {
		int row = fields.size() + 1;
		
		final Holder holder = new Holder();
		holder.removeButton = new Button("Remove");
		holder.row = row;
		holder.spinner = new NumberSpinner();
		holder.spinner.setVisibleLength(4);
		holder.spinner.setTitle("Additional damage done by " + type.getLongName() + " skills, as shown in-game in the Character Details screen, under Offense");
		holder.spinner.setValue(value);
		holder.type = type;
		
		holder.removeButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				removeType(holder);
			}});
		
		fields.put(type, holder);
		
		flexTable.setWidget(row, 0, new Label(type.getLongName(), false));
		flexTable.setWidget(row, 1, new Label("+ %", false));
		flexTable.setWidget(row, 2, holder.spinner);
		flexTable.setWidget(row, 3, holder.removeButton);
	}

	protected void removeType(Holder holder) {
		int row = holder.row;
		DamageType type = holder.type;
		
		flexTable.removeRow(row);
		
		fields.remove(holder.type);
		
		for (Holder h : fields.values()) {
			if (h.row > row)
				h.row--;
		}
		
		int n = listBox.getItemCount();
		int index = -1;
		
		for (int i = 0; i < n; i++) {
			String s = listBox.getItemText(i);
			
			if (type.getLongName().compareTo(s) > 0) {
				index = i;
				break;
			}
		}
		
		if (index < 0) {
			listBox.addItem(type.getLongName(), type.name());
			listBox.setSelectedIndex(n);
		} else {
			listBox.insertItem(type.getLongName(), type.name(), index);
			listBox.setSelectedIndex(index);
		}
	}

	public void setValues(Map<DamageType, Double> map) {
		
		for (Holder h : fields.values()) {
			DamageType s = h.type;
			Double d = map.get(s);
			
			if ((d == null) || (d == 0.0))
				removeType(h);
			else
				h.spinner.setValue((int)Math.round(d * 100.0));
		}
		
		for (Map.Entry<DamageType, Double> e : map.entrySet()) {
			
			DamageType s = e.getKey();
			Double d = e.getValue();
			
			if ((d != null) && (d > 0.0)) {
				Holder h = fields.get(s);
				
				if (h == null) {
					addType(s, (int)Math.round(d * 100.0));
					
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
	
	public Map<DamageType, Double> getValues() {
		
		Map<DamageType, Double> map = new TreeMap<DamageType, Double>();
		
		for (Holder h : fields.values()) {
			
			int value = h.spinner.getValue();
			
			if (value > 0) {
				map.put(h.type, value / 100.0);
			}
		}

		return map;
	}
}
