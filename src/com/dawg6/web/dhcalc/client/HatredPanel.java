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

import com.dawg6.gwt.client.widgets.SimpleCaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class HatredPanel extends Composite {
	private final DoubleSpinner hatredPerSecond;
	private final NumberSpinner equipmentDiscipline;

	public HatredPanel() {
		
		SimpleCaptionPanel SimpleCaptionPanel = new SimpleCaptionPanel("Hatred/Discipline");
		initWidget(SimpleCaptionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		SimpleCaptionPanel.setContentWidget(flexTable);
		
		Label lblHatredPerSecond = new Label("+ Hatred per Second:");
		flexTable.setWidget(0, 0, lblHatredPerSecond);
		
		hatredPerSecond = new DoubleSpinner();
		hatredPerSecond.setVisibleLength(5);
		
		flexTable.setWidget(0, 1, hatredPerSecond);
		
		Label label = new Label("+ Maximum Discipline:");
		flexTable.setWidget(1, 0, label);
		
		equipmentDiscipline = new NumberSpinner();
		equipmentDiscipline.setVisibleLength(5);
		flexTable.setWidget(1, 1, equipmentDiscipline);
		
		Label lblNewLabel_1 = new Label("Only include increased Hatred per Second and Max Discipline from items. Do not include gains from skills (e.g. Archery) or buffs (e.g. Inspire).");
		lblNewLabel_1.setWordWrap(true);
		lblNewLabel_1.addStyleName("boldText");
		flexTable.setWidget(2, 0, lblNewLabel_1);
		flexTable.getFlexCellFormatter().setColSpan(2, 0, 2);
		lblNewLabel_1.setWidth("400px");
	}


	public DoubleSpinner getHatredPerSecond() {
		return hatredPerSecond;
	}


	public NumberSpinner getEquipmentDiscipline() {
		return equipmentDiscipline;
	}
}
