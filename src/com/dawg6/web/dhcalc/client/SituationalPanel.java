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
import com.dawg6.web.dhcalc.shared.calculator.MonsterHealth;
import com.dawg6.web.dhcalc.shared.calculator.MonsterType;
import com.dawg6.web.dhcalc.shared.calculator.TargetSize;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
	private final NumberSpinner percentMoving;
	
	public SituationalPanel() {

		SimpleCaptionPanel SimpleCaptionPanel = new SimpleCaptionPanel("Situational");
		initWidget(SimpleCaptionPanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		SimpleCaptionPanel.setContentWidget(flexTable);
		
		int row = 0;
		
		Label lblGreaterRiftLevel = new Label("Greater Rift Level:");
		lblGreaterRiftLevel.setWordWrap(false);
		flexTable.setWidget(row, 0, lblGreaterRiftLevel);
		
		riftLevel = new NumberSpinner();
		riftLevel.setVisibleLength(4);
		riftLevel.setTitle("Greater Rift Level");
		riftLevel.setText("1");
		riftLevel.setMin(1);
		riftLevel.setMax(1000);
		flexTable.setWidget(row++, 1, riftLevel);
		
		Label lblNumberOfPlayers = new Label("Number of Players:");
		lblNumberOfPlayers.setWordWrap(false);
		flexTable.setWidget(row, 0, lblNumberOfPlayers);
		
		numPlayers = new NumberSpinner();
		numPlayers.setVisibleLength(4);
		numPlayers.setTitle("# of players in rift (used to calculate monster health)");
		numPlayers.setText("1");
		numPlayers.setMin(1);
		numPlayers.setMax(4);
		flexTable.setWidget(row++, 1, numPlayers);
		
		Label lblPrimaryTargetType = new Label("Primary Target Type:");
		lblPrimaryTargetType.setWordWrap(false);
		flexTable.setWidget(row, 0, lblPrimaryTargetType);
		
		primaryTargetType = new ListBox();
		flexTable.setWidget(row++, 1, primaryTargetType);
		primaryTargetType.setWidth("100%");
		
		Label lblPrimaryTargetHealth = new Label("Primary Target Health:");
		lblPrimaryTargetHealth.setWordWrap(false);
		flexTable.setWidget(row, 0, lblPrimaryTargetHealth);
		
		primaryTargetHealth = new LongSpinner();
		primaryTargetHealth.setVisibleLength(20);
		primaryTargetHealth.setTitle("Number of additional targets to account for.");
		primaryTargetHealth.setText("0");
		flexTable.setWidget(row++, 1, primaryTargetHealth);
		
		Label lblAdditionalTargetsType = new Label("Additional Targets Type:");
		lblAdditionalTargetsType.setWordWrap(false);
		flexTable.setWidget(row, 0, lblAdditionalTargetsType);
		
		additionalTargetType = new ListBox();
		flexTable.setWidget(row++, 1, additionalTargetType);
		additionalTargetType.setWidth("100%");

		Label lblOfadditional = new Label("# of Additional targets:");
		lblOfadditional.setWordWrap(false);
		flexTable.setWidget(row, 0, lblOfadditional);

		additional = new NumberSpinner();
		additional.setVisibleLength(4);
		additional
				.setTitle("Number of additional targets to account for.");
		additional.setText("0");
		flexTable.setWidget(row++, 1, additional);
		this.additional.setMax(10);
		
		Label lblAdditionalTargetsHealth = new Label("Additional Targets Health:");
		lblAdditionalTargetsHealth.setWordWrap(false);
		flexTable.setWidget(row, 0, lblAdditionalTargetsHealth);
		
		additionalTargetsHealth = new LongSpinner();
		additionalTargetsHealth.setVisibleLength(20);
		additionalTargetsHealth.setTitle("Number of additional targets to account for.");
		additionalTargetsHealth.setText("0");
		flexTable.setWidget(row++, 1, additionalTargetsHealth);

		Label lblPercentOfTargets = new Label(
				"Percent of targets slowed/chilled?");
		lblPercentOfTargets.setWordWrap(false);
		flexTable.setWidget(row, 0, lblPercentOfTargets);

		percentSlowedChilled = new NumberSpinner();
		percentSlowedChilled.setVisibleLength(4);
		percentSlowedChilled
				.setTitle("Percent of targets slowed or chilled (apply Cull the Weak).");
		percentSlowedChilled.setText("0");
		flexTable.setWidget(row++, 1, percentSlowedChilled);

		Label lblPercentOfTargets_1 = new Label(
				"Percent of targets control impaired?");
		lblPercentOfTargets_1.setWordWrap(false);
		flexTable.setWidget(row, 0, lblPercentOfTargets_1);

		percentControlled = new NumberSpinner();
		percentControlled.setVisibleLength(4);
		percentControlled
				.setTitle("Percent of targets control impaired (apply Bane of the Trapped).");
		percentControlled.setText("0");
		flexTable.setWidget(row++, 1, percentControlled);

		Label lblPercentOfTargets_2 = new Label(
				"Percent of targets at least 10 yards away?");
		lblPercentOfTargets_2.setWordWrap(false);
		flexTable.setWidget(row, 0, lblPercentOfTargets_2);

		percentAtLeast10Yards = new NumberSpinner();
		percentAtLeast10Yards.setVisibleLength(4);
		percentAtLeast10Yards
				.setTitle("Percent of targets at least 10 yards away (apply Steady Aim/UE4).");
		percentAtLeast10Yards.setText("0");
		flexTable.setWidget(row++, 1, percentAtLeast10Yards);

		Label label_8 = new Label("Distance to target(s) (yards)");
		label_8.setWordWrap(false);
		flexTable.setWidget(row, 0, label_8);

		distance = new NumberSpinner();
		distance.setVisibleLength(4);
		distance.setTitle("Average distance (in yards) to target(s) (for Zei's Stone of Vengeance).");
		distance.setText("0");
		flexTable.setWidget(row++, 1, distance);

		Label lblSpacingBetweenTargets = new Label(
				"Spacing between targets (yards):");
		lblSpacingBetweenTargets.setWordWrap(false);
		flexTable.setWidget(row, 0, lblSpacingBetweenTargets);

		targetSpacing = new NumberSpinner();
		targetSpacing.setVisibleLength(4);
		targetSpacing
				.setTitle("Average distance (in yards) between target(s) (for Grenades, certain Marked for Death runes and Single Out).");
		targetSpacing.setText("0");
		flexTable.setWidget(row++, 1, targetSpacing);

		Label moving = new Label(
				"% of time Moving:");
		moving.setWordWrap(false);
		flexTable.setWidget(row, 0, moving);

		percentMoving = new NumberSpinner();
		percentMoving.setVisibleLength(4);
		percentMoving
				.setTitle("Percent of the time that the player is moving (vs standing still)");
		percentMoving.setMin(0);
		percentMoving.setMax(100);
		percentMoving.setText("50");
		flexTable.setWidget(row++, 1, percentMoving);

		Label lblTargetSizefor = new Label("Target Size (for Ball Lightning):");
		lblTargetSizefor.setWordWrap(false);
		flexTable.setWidget(row, 0, lblTargetSizefor);

		targetSize = new ListBox();
		targetSize.setTitle("Target Size");
		flexTable.setWidget(row++, 1, targetSize);
		targetSize.setWidth("100%");
		
		Label label = new Label("# Health Globes:");
		flexTable.setWidget(row, 0, label);
		
		numHealthGlobes = new NumberSpinner();
		numHealthGlobes.setVisibleLength(4);
		numHealthGlobes.setTitle("# of Health Globes picked up during fight");
		flexTable.setWidget(row++, 1, numHealthGlobes);
		
		Label label_1 = new Label("Average Firing Delay (ms):");
		label_1.setWordWrap(false);
		flexTable.setWidget(row, 0, label_1);
		
		firingDelay = new NumberSpinner();
		firingDelay.setVisibleLength(4);
		firingDelay.setTitle("Average delay (in milliseconds) of player actions.");
		flexTable.setWidget(row++, 1, firingDelay);
		
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
	
	public NumberSpinner getPercentMoving() {
		return this.percentMoving;
	}
	
}
