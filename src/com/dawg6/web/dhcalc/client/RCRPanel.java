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

import java.util.Map;
import java.util.TreeMap;

import com.dawg6.web.dhcalc.shared.calculator.GemLevel;
import com.dawg6.web.dhcalc.shared.calculator.Slot;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class RCRPanel extends Composite {
	private final NumberSpinner shoulders;
	private final NumberSpinner gloves;
	private final NumberSpinner amulet;
	private final NumberSpinner ring1;
	private final NumberSpinner ring2;
	private final NumberSpinner belt;
	private final NumberSpinner weapon;
	private final NumberSpinner quiver;
	private final Label effRCR;
	private final ListBox topaz;
	
	public RCRPanel() {

		CaptionPanel cptnpnlResourceReduction = new CaptionPanel(
				"Resource Cost Reduction");
		initWidget(cptnpnlResourceReduction);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		cptnpnlResourceReduction.setContentWidget(flexTable);

		int row = 0;
		
		Label lblNewLabel = new Label("Helm (Topaz):");
		lblNewLabel.setStyleName("boldText");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(row, 0, lblNewLabel);

		topaz = new ListBox();
		flexTable.setWidget(row, 1, topaz);

		row++;
		
		Label lblShoulders = new Label("Shoulders (%):");
		lblShoulders.setWordWrap(false);
		lblShoulders.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblShoulders);

		shoulders = new NumberSpinner();
		shoulders.setVisibleLength(4);
		flexTable.setWidget(row, 1, shoulders);

		row++;
		
		Label lblGloves = new Label("Gloves (%):");
		lblGloves.setWordWrap(false);
		lblGloves.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblGloves);

		gloves = new NumberSpinner();
		gloves.setVisibleLength(4);
		flexTable.setWidget(row, 1, gloves);

		row++;
		
		Label lblAmulet = new Label("Amulet (%):");
		lblAmulet.setWordWrap(false);
		lblAmulet.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblAmulet);

		amulet = new NumberSpinner();
		amulet.setVisibleLength(4);
		flexTable.setWidget(row, 1, amulet);

		row++;
		
		Label lblRing = new Label("Ring 1 (%):");
		lblRing.setWordWrap(false);
		lblRing.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblRing);

		ring1 = new NumberSpinner();
		ring1.setVisibleLength(4);
		flexTable.setWidget(row, 1, ring1);

		row++;
		
		Label lblRing_1 = new Label("Ring 2 (%):");
		lblRing_1.setWordWrap(false);
		lblRing_1.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblRing_1);

		ring2 = new NumberSpinner();
		ring2.setVisibleLength(4);
		flexTable.setWidget(row, 1, ring2);

		row++;
		
		Label lblBelt = new Label("Belt (%):");
		lblBelt.setWordWrap(false);
		lblBelt.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblBelt);

		belt = new NumberSpinner();
		belt.setVisibleLength(4);
		flexTable.setWidget(row, 1, belt);

		row++;
		
		Label lblWeapon = new Label("Main Hand (%):");
		lblWeapon.setWordWrap(false);
		lblWeapon.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblWeapon);

		weapon = new NumberSpinner();
		weapon.setVisibleLength(4);
		flexTable.setWidget(row, 1, weapon);

		row++;
		
		Label lblQuiver = new Label("Off Hand(%):");
		lblQuiver.setWordWrap(false);
		lblQuiver.setStyleName("boldText");
		flexTable.setWidget(row, 0, lblQuiver);

		quiver = new NumberSpinner();
		quiver.setVisibleLength(4);
		flexTable.setWidget(row, 1, quiver);

		row++;
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(5);
		flexTable.setWidget(row, 0, horizontalPanel);

		row++;
		
		for (GemLevel l : GemLevel.values()) {
			this.topaz.addItem(l.getDisplayName(), l.name());
		}

		topaz.setSelectedIndex(0);

		this.shoulders.setMax(10);
		this.gloves.setMax(10);
		this.ring1.setMax(10);
		this.ring2.setMax(10);
		this.belt.setMax(10);
		this.weapon.setMax(60); // yang's 50% + 10%
		this.quiver.setMax(10);
		this.amulet.setMax(10);

		Label l10 = new Label("Effective RCR:");
		l10.addStyleName("boldText");
		l10.setWordWrap(false);
		flexTable.setWidget(10, 0, l10);
		this.effRCR = new Label("0.0%");
		this.effRCR.addStyleName("boldText");
		flexTable.setWidget(10, 1, effRCR);
		
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
	
	public void setEffectiveRcr(double rcr) {
		this.effRCR.setText(Util.format(Math.round(rcr * 10000.0) / 100.0) + "%");
	}

	public ListBox getTopaz() {
		return topaz;
	}

	public GemLevel getSelectedTopaz() {
		int i = this.topaz.getSelectedIndex();
		String value = this.topaz.getValue(i);

		return GemLevel.valueOf(value);
	}

	public void setTopaz(GemLevel topaz) {
		for (int i = 0; i < this.topaz.getItemCount(); i++) {
			String value = this.topaz.getValue(i);

			if (value.equals(topaz.name())) {
				this.topaz.setSelectedIndex(i);
				return;
			}
		}

		this.topaz.setSelectedIndex(0);
	}

}
