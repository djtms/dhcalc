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
import java.util.Vector;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.common.util.Pair;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.Breakpoint;
import com.dawg6.web.dhcalc.shared.calculator.DamageFunction;
import com.dawg6.web.dhcalc.shared.calculator.WeaponType;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BPData extends ApplicationPanel {
	private final FlexTable table;
	private final ListBox skills;
	private final ListBox weaponType;

	public BPData() {

		VerticalPanel panel = new VerticalPanel();
		initWidget(panel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(5);
		panel.add(horizontalPanel);
		
		Label lblNewLabel = new Label("Skill:");
		lblNewLabel.setStyleName("boldText");
		horizontalPanel.add(lblNewLabel);
		
		skills = new ListBox();
		horizontalPanel.add(skills);
		
		Label lblNewLabel2 = new Label("Weapon Type:");
		lblNewLabel2.setStyleName("boldText");
		horizontalPanel.add(lblNewLabel2);
		
		weaponType = new ListBox();
		horizontalPanel.add(weaponType);
		
		for (WeaponType t : WeaponType.values()) {
			weaponType.addItem(t.getName(), t.name());
		}

		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Skill Break Point Data");
		panel.add(cptnpnlNewPanel);
		
		table = new FlexTable();
		cptnpnlNewPanel.setContentWidget(table);
		table.setSize("5cm", "3cm");
		table.setStyleName("breakpointTable");
		table.setCellPadding(5);
		table.setBorderWidth(1);
		
		table.getRowFormatter().addStyleName(0, "headerRow");
		
		int col = 0;
		
		Label bp = new Label("BP");
		bp.setWordWrap(false);
		table.setWidget(0, col++, bp);

		Label fpa = new Label("FPA");
		fpa.setWordWrap(false);
		table.setWidget(0, col++, fpa);
		
		Label minAps = new Label("Min APS");
		minAps.setWordWrap(false);
		table.setWidget(0, col++, minAps);

		Label actualAps = new Label("Actual APS");
		actualAps.setWordWrap(false);
		table.setWidget(0, col++, actualAps);
		
		List<Pair<String, String>> list = new Vector<Pair<String, String>>();
		
		for (ActiveSkill s : ActiveSkill.values()) {
			
			if (DamageFunction.hasDamage(s) && (s.getFrames() != 0))
				list.add(new Pair<String, String>(s.getLongName(), s.name()));
		}
		
		Collections.sort(list, new Comparator<Pair<String, String>>(){

			@Override
			public int compare(Pair<String, String> o1, Pair<String, String> o2) {
				return o1.getA().toLowerCase().compareTo(o2.getA().toLowerCase());
			}});

		for (Pair<String, String> p : list) {
			skills.addItem(p.getA(), p.getB());
		}
		
		skills.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				skillChanged();
			}});
		
		weaponType.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				skillChanged();
			}});

		skills.setSelectedIndex(0);
		weaponType.setSelectedIndex(0);

		skillChanged();
	}

	protected void skillChanged() {
		
		int index = skills.getSelectedIndex();
		
		while (table.getRowCount() > 1)
			table.removeRow(table.getRowCount()-1);
		
		if (index < 0)
			return;

		String value = skills.getValue(index);
		
		ActiveSkill skill = ActiveSkill.valueOf(value); 
		
		int row = 1;
		
		
		int frames = skill.getFrames();
		
		if (frames < 0) {
			frames = getSelectedWeaponType().getFrames();
		}
		
		Breakpoint bp = new Breakpoint(frames);
		
		while (bp.getData()[row-1].fpa > 15) {

			Breakpoint.Data data = bp.getData()[row-1];
			
			if ((row % 2) == 0)
				table.getRowFormatter().addStyleName(row, "evenRow");
			else
				table.getRowFormatter().addStyleName(row, "oddRow");
			
			int col = 0;

			addLabel(row, col++, String.valueOf(data.bp));
			addLabel(row, col++, String.valueOf(data.fpa));
			addLabel(row, col++, String.valueOf(data.minAps));
			addLabel(row, col++, String.valueOf(data.actualAps));
			
			row++;
			
		}
	}

	private WeaponType getSelectedWeaponType() {
		int n = weaponType.getSelectedIndex();
		
		if (n < 0)
			return null;
		
		return WeaponType.valueOf(weaponType.getValue(n));
	}

	private void addLabel(int row, int col, String text, String... styles) {
		Label label = new Label(text);
		label.setWordWrap(false);
		
		if (styles != null)
			for (String s : styles)
				label.addStyleName(s);
		
		table.setWidget(row, col, label);
	}

}
