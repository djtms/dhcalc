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

import com.dawg6.web.dhcalc.shared.calculator.MonsterHealth;
import com.dawg6.web.dhcalc.shared.calculator.MonsterType;
import com.dawg6.web.dhcalc.shared.calculator.TargetSize;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class SituationalPanel extends Composite {
	private final NumberSpinner additional;
	private final NumberSpinner distance;
	private final NumberSpinner targetSpacing;
	private final NumberSpinner percentSlowedChilled;
	private final NumberSpinner percentControlled;
	private final NumberSpinner percentAtLeast10Yards;
	private final ListBox targetSize;
	private final NumberSpinner numHealthGlobes;
	private final NumberSpinner firingDelay;
	private final NumberSpinner riftLevel;
	private final LongSpinner primaryTargetHealth;
	private final ListBox primaryTargetType;
	private final LongSpinner additionalTargetsHealth;
	private final NumberSpinner numPlayers;
	private final ListBox additionalTargetType;
	private boolean disableListeners = false;

	public SituationalPanel() {

		CaptionPanel captionPanel = new CaptionPanel("Situational");
		initWidget(captionPanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		Label lblGreaterRiftLevel = new Label("Greater Rift Level:");
		lblGreaterRiftLevel.setWordWrap(false);
		flexTable.setWidget(0, 0, lblGreaterRiftLevel);
		
		riftLevel = new NumberSpinner();
		riftLevel.setVisibleLength(4);
		riftLevel.setTitle("Greater Rift Level");
		riftLevel.setText("1");
		riftLevel.setMin(1);
		riftLevel.setMax(100);
		flexTable.setWidget(0, 1, riftLevel);
		
		Label lblNumberOfPlayers = new Label("Number of Players:");
		lblNumberOfPlayers.setWordWrap(false);
		flexTable.setWidget(1, 0, lblNumberOfPlayers);
		
		numPlayers = new NumberSpinner();
		numPlayers.setVisibleLength(4);
		numPlayers.setTitle("Greater Rift Level");
		numPlayers.setText("1");
		numPlayers.setMin(1);
		numPlayers.setMax(4);
		flexTable.setWidget(1, 1, numPlayers);
		
		Label lblPrimaryTargetType = new Label("Primary Target Type:");
		lblPrimaryTargetType.setWordWrap(false);
		flexTable.setWidget(2, 0, lblPrimaryTargetType);
		
		primaryTargetType = new ListBox();
		flexTable.setWidget(2, 1, primaryTargetType);
		primaryTargetType.setWidth("100%");
		
		Label lblPrimaryTargetHealth = new Label("Primary Target Health:");
		lblPrimaryTargetHealth.setWordWrap(false);
		flexTable.setWidget(3, 0, lblPrimaryTargetHealth);
		
		primaryTargetHealth = new LongSpinner();
		primaryTargetHealth.setVisibleLength(20);
		primaryTargetHealth.setTitle("Number of additional targets to account for.");
		primaryTargetHealth.setText("0");
		flexTable.setWidget(3, 1, primaryTargetHealth);
		
		Label lblAdditionalTargetsType = new Label("Additional Targets Type:");
		lblAdditionalTargetsType.setWordWrap(false);
		flexTable.setWidget(4, 0, lblAdditionalTargetsType);
		
		additionalTargetType = new ListBox();
		flexTable.setWidget(4, 1, additionalTargetType);
		additionalTargetType.setWidth("100%");

		Label lblOfadditional = new Label("# of Additional targets:");
		lblOfadditional.setWordWrap(false);
		flexTable.setWidget(5, 0, lblOfadditional);

		additional = new NumberSpinner();
		additional.setVisibleLength(4);
		additional
				.setTitle("Number of additional targets to account for.");
		additional.setText("0");
		flexTable.setWidget(5, 1, additional);
		this.additional.setMax(10);
		
		Label lblAdditionalTargetsHealth = new Label("Additional Targets Health:");
		lblAdditionalTargetsHealth.setWordWrap(false);
		flexTable.setWidget(6, 0, lblAdditionalTargetsHealth);
		
		additionalTargetsHealth = new LongSpinner();
		additionalTargetsHealth.setVisibleLength(20);
		additionalTargetsHealth.setTitle("Number of additional targets to account for.");
		additionalTargetsHealth.setText("0");
		flexTable.setWidget(6, 1, additionalTargetsHealth);

		Label lblPercentOfTargets = new Label(
				"Percent of targets slowed/chilled?");
		lblPercentOfTargets.setWordWrap(false);
		flexTable.setWidget(7, 0, lblPercentOfTargets);

		percentSlowedChilled = new NumberSpinner();
		percentSlowedChilled.setVisibleLength(4);
		percentSlowedChilled
				.setTitle("Percent of targets slowed or chilled (apply Cull the Weak).");
		percentSlowedChilled.setText("0");
		flexTable.setWidget(7, 1, percentSlowedChilled);

		Label lblPercentOfTargets_1 = new Label(
				"Percent of targets control impaired?");
		lblPercentOfTargets_1.setWordWrap(false);
		flexTable.setWidget(8, 0, lblPercentOfTargets_1);

		percentControlled = new NumberSpinner();
		percentControlled.setVisibleLength(4);
		percentControlled
				.setTitle("Percent of targets control impaired (apply Bane of the Trapped).");
		percentControlled.setText("0");
		flexTable.setWidget(8, 1, percentControlled);

		Label lblPercentOfTargets_2 = new Label(
				"Percent of targets at least 10 yards away?");
		lblPercentOfTargets_2.setWordWrap(false);
		flexTable.setWidget(9, 0, lblPercentOfTargets_2);

		percentAtLeast10Yards = new NumberSpinner();
		percentAtLeast10Yards.setVisibleLength(4);
		percentAtLeast10Yards
				.setTitle("Percent of targets at least 10 yards away (apply Steady Aim/UE4).");
		percentAtLeast10Yards.setText("0");
		flexTable.setWidget(9, 1, percentAtLeast10Yards);

		Label label_8 = new Label("Distance to target(s) (yards)");
		label_8.setWordWrap(false);
		flexTable.setWidget(10, 0, label_8);

		distance = new NumberSpinner();
		distance.setVisibleLength(4);
		distance.setTitle("Average distance (in yards) to target(s) (for Zei's Stone of Vengeance).");
		distance.setText("0");
		flexTable.setWidget(10, 1, distance);

		Label lblSpacingBetweenTargets = new Label(
				"Spacing between targets (yards):");
		lblSpacingBetweenTargets.setWordWrap(false);
		flexTable.setWidget(11, 0, lblSpacingBetweenTargets);

		targetSpacing = new NumberSpinner();
		targetSpacing.setVisibleLength(4);
		targetSpacing
				.setTitle("Average distance (in yards) between target(s) (for Grenades, certain Marked for Death runes and Single Out).");
		targetSpacing.setText("0");
		flexTable.setWidget(11, 1, targetSpacing);

		Label lblTargetSizefor = new Label("Target Size (for Ball Lightning):");
		lblTargetSizefor.setWordWrap(false);
		flexTable.setWidget(12, 0, lblTargetSizefor);

		targetSize = new ListBox();
		targetSize.setTitle("Target Size");
		flexTable.setWidget(12, 1, targetSize);
		targetSize.setWidth("100%");
		
		Label label = new Label("# Health Globes:");
		flexTable.setWidget(13, 0, label);
		
		numHealthGlobes = new NumberSpinner();
		numHealthGlobes.setVisibleLength(4);
		numHealthGlobes.setTitle("# of Health Globes picked up during fight");
		flexTable.setWidget(13, 1, numHealthGlobes);
		
		Label label_1 = new Label("Average Firing Delay (ms):");
		label_1.setWordWrap(false);
		flexTable.setWidget(14, 0, label_1);
		
		firingDelay = new NumberSpinner();
		firingDelay.setVisibleLength(4);
		firingDelay.setTitle("Average delay (in milliseconds) of player actions.");
		flexTable.setWidget(14, 1, firingDelay);
		
		this.distance.setMax(100);
		this.targetSpacing.setMax(100);
		this.percentAtLeast10Yards.setMax(100);
		this.percentControlled.setMax(100);
		this.percentSlowedChilled.setMax(100);
		this.numHealthGlobes.setMin(0);
		this.firingDelay.setMin(0);
		this.firingDelay.setMax(1000);
		
		
		for (TargetSize t : TargetSize.values()) {
			targetSize.addItem(t.getDisplayName(), t.name());
		}
		
		ChangeHandler primaryHandler = new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				
				if (!disableListeners)
					updatePrimaryMonsterHealth();
			}};

		ChangeHandler additionalHandler = new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners)
					updateAdditionalMonsterHealth();
			}};
			
		for (MonsterType m : MonsterType.values()) {
			this.primaryTargetType.addItem(m.toString(), m.name());
			this.additionalTargetType.addItem(m.toString(), m.name());
		}
		
		this.primaryTargetType.setSelectedIndex(0);
		this.additionalTargetType.setSelectedIndex(0);
		
		this.riftLevel.addChangeHandler(primaryHandler);
		this.riftLevel.addChangeHandler(additionalHandler);
		this.primaryTargetType.addChangeHandler(primaryHandler);
		this.additionalTargetType.addChangeHandler(additionalHandler);
		this.numPlayers.addChangeHandler(primaryHandler);
		this.numPlayers.addChangeHandler(additionalHandler);
	}

	protected void updatePrimaryMonsterHealth() {
		long value = MonsterHealth.getHealth(this.riftLevel.getValue(), this.numPlayers.getValue(), getMonsterType(this.primaryTargetType));

		this.primaryTargetHealth.setValue(value);
	}

	public static MonsterType getMonsterType(ListBox listBox) {
		int i = listBox.getSelectedIndex();

		String value = listBox.getValue(i);
		
		return MonsterType.valueOf(value);
	}

	protected void updateAdditionalMonsterHealth() {
		long value = MonsterHealth.getHealth(this.riftLevel.getValue(), this.numPlayers.getValue(), getMonsterType(this.additionalTargetType));

		this.additionalTargetsHealth.setValue(value);
	}

	public NumberSpinner getAdditional() {
		return additional;
	}

	public NumberSpinner getDistance() {
		return distance;
	}

	public NumberSpinner getTargetSpacing() {
		return targetSpacing;
	}

	public NumberSpinner getPercentSlowedChilled() {
		return percentSlowedChilled;
	}

	public NumberSpinner getPercentControlled() {
		return percentControlled;
	}

	public NumberSpinner getPercentAtLeast10Yards() {
		return percentAtLeast10Yards;
	}

	public ListBox getTargetSize() {
		return targetSize;
	}
	
	public TargetSize getSelectedTargetSize() {
		int i = targetSize.getSelectedIndex();
		
		if (i < 0)
			return null;
		
		String value = targetSize.getValue(i);
		
		return TargetSize.valueOf(value);
	}

	public void setTargetSize(TargetSize targetSize) {
		for (int i = 0; i < this.targetSize.getItemCount(); i++) {
			String value = this.targetSize.getValue(i);
			
			if (value.equals(targetSize.name())) {
				this.targetSize.setSelectedIndex(i);
				return;
			}
		}
	}

	public NumberSpinner getNumHealthGlobes() {
		return numHealthGlobes;
	}

	public NumberSpinner getFiringDelay() {
		return firingDelay;
	}

	public NumberSpinner getRiftLevel() {
		return riftLevel;
	}

	public LongSpinner getPrimaryTargetHealth() {
		return primaryTargetHealth;
	}

	public ListBox getPrimaryTargetType() {
		return primaryTargetType;
	}

	public LongSpinner getAdditionalTargetsHealth() {
		return additionalTargetsHealth;
	}

	public NumberSpinner getNumPlayers() {
		return numPlayers;
	}

	public ListBox getAdditionalTargetType() {
		return additionalTargetType;
	}

	public boolean isDisableListeners() {
		return disableListeners;
	}

	public void setDisableListeners(boolean disableListeners) {
		this.disableListeners = disableListeners;
	}
	
}
