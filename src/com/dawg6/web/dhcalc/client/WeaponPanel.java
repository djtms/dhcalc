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

import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.dawg6.web.dhcalc.shared.calculator.WeaponType;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class WeaponPanel extends Composite {
	private final CaptionPanel captionPanel;
	private final ListBox weaponType;
	private final DoubleSpinner baseMin;
	private final DoubleSpinner baseMax;
	private final DoubleSpinner addMin;
	private final DoubleSpinner addMax;
	private final NumberSpinner weaponIAS;
	private final NumberSpinner weaponDamage;
	private final Label tooltipMin;
	private final Label tooltipMax;
	private final Label averageWeaponDamage;
	private final Label weaponDpsLabel;
	private final Label weaponApsLabel;
	private double weaponMin;
	private double weaponMax;
	private double weaponAps;
	private double weaponDps;
	private boolean disableUpdates = false;
	private double averageDamage;
	private final boolean offHand;

	public WeaponPanel() {
		this("Weapon", false);
	}

	public WeaponPanel(String title, boolean offHand) {
		this.offHand = offHand;
		captionPanel = new CaptionPanel(title);
		initWidget(captionPanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);

		Label label = new Label("Weapon Type:");
		label.setWordWrap(false);
		flexTable.setWidget(0, 0, label);

		weaponType = new ListBox();
		weaponType.setSelectedIndex(0);
		flexTable.setWidget(0, 1, weaponType);

		Label label_1 = new Label("Base (Physical) Damage:");
		label_1.setWordWrap(false);
		flexTable.setWidget(1, 0, label_1);

		baseMin = new DoubleSpinner();
		baseMin.box
				.setTitle("This number is not displayed in-game. It is imported from hero import.");
		baseMin.setVisibleLength(8);
		baseMin.setText("0");
		flexTable.setWidget(1, 1, baseMin);

		Label label_2 = new Label(" to ");
		label_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(1, 2, label_2);

		baseMax = new DoubleSpinner();
		baseMax.box
				.setTitle("This number is not displayed in-game. It is imported from hero import.");
		baseMax.setVisibleLength(8);
		baseMax.setText("0");
		flexTable.setWidget(1, 3, baseMax);

		Label label_3 = new Label("Tooltip Damage:");
		label_3.setWordWrap(false);
		label_3.setStyleName("boldText");
		flexTable.setWidget(1, 4, label_3);

		tooltipMin = new Label();
		tooltipMin.setTitle("Shown as \"white\" damage on weapon tooltip.");
		tooltipMin.setText("0");
		tooltipMin.setStyleName("boldText");
		flexTable.setWidget(1, 5, tooltipMin);

		Label label_5 = new Label(" to ");
		label_5.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(1, 6, label_5);

		tooltipMax = new Label();
		tooltipMax.setTitle("Shown as \"white\" damage on weapon tooltip.");
		tooltipMax.setText("0");
		tooltipMax.setStyleName("boldText");
		flexTable.setWidget(1, 7, tooltipMax);

		Label label_7 = new Label("Added (Elemental) Damage:");
		label_7.setWordWrap(false);
		flexTable.setWidget(2, 0, label_7);

		addMin = new DoubleSpinner();
		addMin.box.setTitle("Shown as primary stat on weapon.");
		addMin.setVisibleLength(8);
		addMin.setText("0");
		flexTable.setWidget(2, 1, addMin);

		Label label_8 = new Label(" to ");
		label_8.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(2, 2, label_8);

		addMax = new DoubleSpinner();
		addMax.box.setTitle("Shown as primary stat on weapon.");
		addMax.setVisibleLength(8);
		addMax.setText("0");
		flexTable.setWidget(2, 3, addMax);

		Label label_9 = new Label("Average Weapon Damage:");
		label_9.setWordWrap(false);
		label_9.setStyleName("boldText");
		flexTable.setWidget(2, 4, label_9);

		averageWeaponDamage = new Label("0.0");
		averageWeaponDamage
				.setTitle("Average weapon damage used for Skill calculations");
		averageWeaponDamage.setStyleName("boldText");
		flexTable.setWidget(2, 5, averageWeaponDamage);
		flexTable.getFlexCellFormatter().setColSpan(2, 5, 3);

		Label label_11 = new Label("Weapon IAS (%):");
		label_11.setWordWrap(false);
		flexTable.setWidget(3, 0, label_11);

		weaponIAS = new NumberSpinner();
		weaponIAS.box
				.setTitle("Increased attack speed as a primary attribute on the weapon.");
		weaponIAS.setVisibleLength(6);
		flexTable.setWidget(3, 1, weaponIAS);

		Label label_12 = new Label("Weapon DPS:");
		label_12.setWordWrap(false);
		label_12.setStyleName("boldText");
		flexTable.setWidget(3, 4, label_12);

		weaponDpsLabel = new Label("0.0");
		weaponDpsLabel.setTitle("Shown as dps on weapon tooltip.");
		weaponDpsLabel.setStyleName("boldText");
		flexTable.setWidget(3, 5, weaponDpsLabel);
		flexTable.getFlexCellFormatter().setColSpan(3, 5, 3);

		Label label_14 = new Label("Weapon Increased Damage (%):");
		label_14.setWordWrap(false);
		flexTable.setWidget(4, 0, label_14);

		weaponDamage = new NumberSpinner();
		weaponDamage.box
				.setTitle("Increased damage as a primary attribute on the weapon.");
		weaponDamage.setVisibleLength(6);
		flexTable.setWidget(4, 1, weaponDamage);

		Label label_15 = new Label("Weapon APS:");
		label_15.setWordWrap(false);
		label_15.setStyleName("boldText");
		flexTable.setWidget(4, 4, label_15);

		weaponApsLabel = new Label("0.0");
		weaponApsLabel.setTitle("As shown on weapon tooltip");
		weaponApsLabel.setStyleName("boldText");
		flexTable.setWidget(4, 5, weaponApsLabel);
		flexTable.getFlexCellFormatter().setColSpan(4, 5, 3);

		this.weaponIAS.setMax(7);
		this.weaponDamage.setMax(10);

		weaponType.addItem("None", "");

		for (WeaponType wt : WeaponType.values()) {
			
			if (!offHand || (wt == WeaponType.HandCrossbow))
				weaponType.addItem(wt.getName(), wt.name());
		}

		weaponType.setSelectedIndex(0);

		ChangeHandler handler = new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableUpdates) {
					calculate();
				}
			}
		};

		weaponType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				disableUpdates = true;

				WeaponType type = getWeaponTypeEnum();

				if (type == null) {
					baseMin.setValue(0.0);
					baseMax.setMax(0.0);
				} else {
					baseMin.setValue((double) type.getMin());
					baseMax.setValue((double) type.getMax());
				}

				disableUpdates = false;

				calculate();
			}
		});

		baseMin.addChangeHandler(handler);
		baseMax.addChangeHandler(handler);
		addMin.addChangeHandler(handler);
		addMax.addChangeHandler(handler);
		weaponIAS.addChangeHandler(handler);
		weaponDamage.addChangeHandler(handler);
	}

	@Override
	public void setTitle(String title) {
		captionPanel.setCaptionText(title);
	}

	public ListBox getWeaponType() {
		return weaponType;
	}

	public DoubleSpinner getBaseMin() {
		return baseMin;
	}

	public DoubleSpinner getBaseMax() {
		return baseMax;
	}

	public DoubleSpinner getAddMin() {
		return addMin;
	}

	public DoubleSpinner getAddMax() {
		return addMax;
	}

	public NumberSpinner getWeaponIAS() {
		return weaponIAS;
	}

	public NumberSpinner getWeaponDamage() {
		return weaponDamage;
	}

	public WeaponType getWeaponTypeEnum() {
		int i = this.weaponType.getSelectedIndex();
		String value = this.weaponType.getValue(i);

		if (value.length() == 0)
			return null;
		else
			return WeaponType.valueOf(value);
	}

	public void setWeaponTypeEnum(WeaponType type) {

		if (type == null) {
			weaponType.setSelectedIndex(0);
		} else {
			for (int i = 0; i < this.weaponType.getItemCount(); i++) {
				String value = this.weaponType.getValue(i);

				if (value.equals(type.name())) {
					weaponType.setSelectedIndex(i);
					return;
				}
			}
		}
	}

	public void calculate() {
		double wpnDamage = (double) this.weaponDamage.getValue() / 100.0;

		weaponMin = (baseMin.getValue() + addMin.getValue())
				* (1.0 + wpnDamage);
		weaponMax = (baseMax.getValue() + addMax.getValue())
				* (1.0 + wpnDamage);

		tooltipMin.setText(Util.format(weaponMin));
		tooltipMax.setText(Util.format(weaponMax));

		WeaponType type = getWeaponTypeEnum();

		if (type != null) {
			weaponAps = type.getAps()
					* (1.0 + (getValue(this.weaponIAS) / 100.0));

			weaponDps = Math.round(((weaponMin + weaponMax) / 2.0) * weaponAps
					* 10.0) / 10.0;

			weaponDpsLabel
					.setText(Util.format((Math.round(weaponDps * 10.0) / 10.0)));
			weaponApsLabel
					.setText(Util.format((Math.round(weaponAps * 100.0) / 100.0)));

			averageDamage = ((weaponMin + weaponMax) / 2.0);

			averageWeaponDamage.setText(Util.format(Math
					.round(100.0 * averageDamage) / 100.0));
		} else {
			weaponDpsLabel.setText("0");
			weaponApsLabel.setText("0");
			averageWeaponDamage.setText("0");
		}
	}

	protected int getValue(NumberSpinner spinner) {
		Integer value = spinner.getValue();

		if (value == null) {
			spinner.setValue(0);
			value = 0;
		}

		return value;
	}

	public double getWeaponMin() {
		return weaponMin;
	}

	public double getWeaponMax() {
		return weaponMax;
	}

	public double getWeaponAps() {
		return weaponAps;
	}

	public double getWeaponDps() {
		return weaponDps;
	}

	public double getAverageWeaponDamage() {
		return averageDamage;
	}

	public void setWeaponTypeString(String value) {

		if ((value == null) || (value.trim().length() == 0))
			setWeaponTypeEnum(null);
		else
			setWeaponTypeEnum(WeaponType.valueOf(value));
	}
}
