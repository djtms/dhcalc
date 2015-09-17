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
import com.dawg6.web.dhcalc.shared.calculator.DamageMultiplier;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Legend extends ApplicationPanel {

	private final FlexTable flexTable;

	public Legend() {
		
		SimplePanel panel = new SimplePanel();
		initWidget(panel);
		
		flexTable = new FlexTable();
		flexTable.setStyleName("outputTable");
		flexTable.setBorderWidth(1);
		flexTable.setCellPadding(5);
		flexTable.getRowFormatter().addStyleName(0, "headerRow");
		
		ScrollPanel scroll = new ScrollPanel();
		scroll.setWidth("800px");
		scroll.setHeight("400px");
		scroll.add(flexTable);
		panel.add(scroll);
		
		Label lblNewLabel = new Label("Key");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(0, 0, lblNewLabel);
		
		Label lblAccumulator = new Label("How Calculated");
		lblAccumulator.setWordWrap(false);
		flexTable.setWidget(0, 1, lblAccumulator);
		
		Label lblDescription = new Label("Description");
		lblDescription.setWordWrap(false);
		flexTable.setWidget(0, 2, lblDescription);

		int row = 1;
		
		List<DamageMultiplier> sortedList = new Vector<DamageMultiplier>();
		
		for (DamageMultiplier d : DamageMultiplier.values()) {
			sortedList.add(d);
		}

		Collections.sort(sortedList, new Comparator<DamageMultiplier>(){

			@Override
			public int compare(DamageMultiplier o1, DamageMultiplier o2) {
				return o1.getAbbreviation().toLowerCase().compareTo(o2.getAbbreviation().toLowerCase());
			}});
		
		for (DamageMultiplier d : sortedList) {
			if ((row % 2) == 0)
				flexTable.getRowFormatter().addStyleName(row, "evenRow");
			else
				flexTable.getRowFormatter().addStyleName(row, "oddRow");
			
			addLabel(row, 0, d.getAbbreviation());
			addLabel(row, 1, d.getAccumulator().getDescription());
			addLabel(row, 2, d.getDescription());
			row++;
		}
		
	}

	private void addLabel(int row, int column, String text) {
		Label label = new Label(text);
		label.setWordWrap(false);
		
		flexTable.setWidget(row, column, label);
	}
}
