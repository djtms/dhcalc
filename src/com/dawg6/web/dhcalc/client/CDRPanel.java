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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import java.util.Map;
import java.util.TreeMap;

import com.dawg6.web.dhcalc.shared.calculator.GemLevel;
import com.dawg6.web.dhcalc.shared.calculator.Slot;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class CDRPanel extends Composite {
	private final ListBox diamond;
	private final NumberSpinner shoulders;
	private final NumberSpinner gloves;
	private final NumberSpinner amulet;
	private final NumberSpinner ring1;
	private final NumberSpinner ring2;
	private final NumberSpinner belt;
	private final NumberSpinner weapon;
	private final NumberSpinner quiver;
	
	public CDRPanel() {
		
		CaptionPanel cptnpnlCooldownReduction = new CaptionPanel("Cooldown Reduction");
		initWidget(cptnpnlCooldownReduction);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		cptnpnlCooldownReduction.setContentWidget(flexTable);
		
		Label lblNewLabel = new Label("Helm (Diamond):");
		lblNewLabel.setStyleName("boldText");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(0, 0, lblNewLabel);
		
		diamond = new ListBox();
		flexTable.setWidget(0, 2, diamond);
		
		HorizontalPanel row = new HorizontalPanel();
		row.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		row.setSpacing(5);
		flexTable.setWidget(1, 0, row);

		Label lblShoulders = new Label("Shoulders (%):");
		lblShoulders.setWordWrap(false);
		lblShoulders.setStyleName("boldText");
		flexTable.setWidget(2, 0, lblShoulders);
		
		shoulders = new NumberSpinner();
		shoulders.setVisibleLength(4);
		flexTable.setWidget(2, 2, shoulders);
		
		Label lblGloves = new Label("Gloves (%):");
		lblGloves.setWordWrap(false);
		lblGloves.setStyleName("boldText");
		flexTable.setWidget(3, 0, lblGloves);
		
		gloves = new NumberSpinner();
		gloves.setVisibleLength(4);
		flexTable.setWidget(3, 2, gloves);
		
		Label lblAmulet = new Label("Amulet (%):");
		lblAmulet.setWordWrap(false);
		lblAmulet.setStyleName("boldText");
		flexTable.setWidget(4, 0, lblAmulet);
		
		amulet = new NumberSpinner();
		amulet.setVisibleLength(4);
		flexTable.setWidget(4, 2, amulet);
		
		Label lblRing = new Label("Ring 1 (%):");
		lblRing.setWordWrap(false);
		lblRing.setStyleName("boldText");
		flexTable.setWidget(5, 0, lblRing);
		
		ring1 = new NumberSpinner();
		ring1.setVisibleLength(4);
		flexTable.setWidget(5, 2, ring1);
		
		Label lblRing_1 = new Label("Ring 2 (%):");
		lblRing_1.setWordWrap(false);
		lblRing_1.setStyleName("boldText");
		flexTable.setWidget(6, 0, lblRing_1);
		
		ring2 = new NumberSpinner();
		ring2.setVisibleLength(4);
		flexTable.setWidget(6, 2, ring2);
		
		Label lblBelt = new Label("Belt (%):");
		lblBelt.setWordWrap(false);
		lblBelt.setStyleName("boldText");
		flexTable.setWidget(7, 0, lblBelt);
		
		belt = new NumberSpinner();
		belt.setVisibleLength(4);
		flexTable.setWidget(7, 2, belt);
		
		Label lblWeapon = new Label("Weapon (%):");
		lblWeapon.setWordWrap(false);
		lblWeapon.setStyleName("boldText");
		flexTable.setWidget(8, 0, lblWeapon);
		
		weapon = new NumberSpinner();
		weapon.setVisibleLength(4);
		flexTable.setWidget(8, 2, weapon);
		
		Label lblQuiver = new Label("Quiver (%):");
		lblQuiver.setWordWrap(false);
		lblQuiver.setStyleName("boldText");
		flexTable.setWidget(9, 0, lblQuiver);
		
		quiver = new NumberSpinner();
		quiver.setVisibleLength(4);
		flexTable.setWidget(9, 2, quiver);
		
		for (GemLevel l : GemLevel.values()) {
			this.diamond.addItem(l.getDisplayName(), l.name());
		}
		
		diamond.setSelectedIndex(0);
		
		this.shoulders.setMax(8);
		this.gloves.setMax(8);
		this.ring1.setMax(8);
		this.ring2.setMax(8);
		this.belt.setMax(8);
		this.weapon.setMax(10);
		this.quiver.setMax(10);
		this.amulet.setMax(8);
	}
	public ListBox getDiamond() {
		return diamond;
	}

	public NumberSpinner getShoulders() {
		return shoulders;
	}
	public NumberSpinner getGloves() {
		return gloves;
	}
	public NumberSpinner getAmulet() {
		return amulet;
	}
	public NumberSpinner getRing1() {
		return ring1;
	}
	public NumberSpinner getRing2() {
		return ring2;
	}
	public NumberSpinner getBelt() {
		return belt;
	}
	public NumberSpinner getWeapon() {
		return weapon;
	}
	public NumberSpinner getQuiver() {
		return quiver;
	}
	public GemLevel getSelectedDiamond() {
		int i = this.diamond.getSelectedIndex();
		String value = this.diamond.getValue(i);
		
		return GemLevel.valueOf(value);
	}
	public void setDiamond(GemLevel diamond) {
		for (int i = 0; i < this.diamond.getItemCount(); i++) {
			String value = this.diamond.getValue(i);
			
			if (value.equals(diamond.name())) {
				this.diamond.setSelectedIndex(i);
				return;
			}
		}
		
		this.diamond.setSelectedIndex(0);
	}

	public Map<String, Integer> getData() {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
		map.put(Slot.Shoulders.name(), shoulders.getValue());
		map.put(Slot.Hands.name(), gloves.getValue());
		map.put(Slot.Necklace.name(), amulet.getValue());
		map.put(Slot.Ring1.name(), ring1.getValue());
		map.put(Slot.Ring2.name(), ring2.getValue());
		map.put(Slot.Waist.name(), belt.getValue());
		map.put(Slot.MainHand.name(), weapon.getValue());
		map.put(Slot.OffHand.name(), quiver.getValue());

		return map;
	}
}
