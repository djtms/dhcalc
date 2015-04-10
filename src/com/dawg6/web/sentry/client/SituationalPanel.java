package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.TargetSize;
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
	private final NumberSpinner percentAbove75;
	private final ListBox targetSize;
	private final NumberSpinner numHealthGlobes;
	private final NumberSpinner firingDelay;

	public SituationalPanel() {

		CaptionPanel captionPanel = new CaptionPanel("Situational");
		initWidget(captionPanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);

		Label label_7 = new Label("Max # of \"additional\" targets:");
		label_7.setWordWrap(false);
		flexTable.setWidget(0, 0, label_7);

		additional = new NumberSpinner();
		additional.setVisibleLength(4);
		additional
				.setTitle("Maximum number of additional targets to apply damage to, for skills that are able to.");
		additional.setText("0");
		flexTable.setWidget(0, 1, additional);

		this.additional.setMax(100);

		Label lblPercentOfTargets = new Label(
				"Percent of targets slowed/chilled?");
		lblPercentOfTargets.setWordWrap(false);
		flexTable.setWidget(1, 0, lblPercentOfTargets);

		percentSlowedChilled = new NumberSpinner();
		percentSlowedChilled.setVisibleLength(4);
		percentSlowedChilled
				.setTitle("Percent of targets slowed or chilled (apply Cull the Weak).");
		percentSlowedChilled.setText("0");
		flexTable.setWidget(1, 1, percentSlowedChilled);

		Label lblPercentOfTargets_1 = new Label(
				"Percent of targets control impaired?");
		lblPercentOfTargets_1.setWordWrap(false);
		flexTable.setWidget(2, 0, lblPercentOfTargets_1);

		percentControlled = new NumberSpinner();
		percentControlled.setVisibleLength(4);
		percentControlled
				.setTitle("Percent of targets control impaired (apply Bane of the Trapped).");
		percentControlled.setText("0");
		flexTable.setWidget(2, 1, percentControlled);

		Label lblPercentOfTargets_2 = new Label(
				"Percent of targets at least 10 yards away?");
		lblPercentOfTargets_2.setWordWrap(false);
		flexTable.setWidget(3, 0, lblPercentOfTargets_2);

		percentAtLeast10Yards = new NumberSpinner();
		percentAtLeast10Yards.setVisibleLength(4);
		percentAtLeast10Yards
				.setTitle("Percent of targets at least 10 yards away (apply Steady Aim/UE4).");
		percentAtLeast10Yards.setText("0");
		flexTable.setWidget(3, 1, percentAtLeast10Yards);

		Label lblPercentOfEnemies = new Label(
				"Percent of enemies above 75% health (apply Ambush).");
		lblPercentOfEnemies.setWordWrap(false);
		flexTable.setWidget(4, 0, lblPercentOfEnemies);

		percentAbove75 = new NumberSpinner();
		percentAbove75.setVisibleLength(4);
		percentAbove75
				.setTitle("Maximum number of additional targets to apply damage to, for skills that are able to.");
		percentAbove75.setText("0");
		flexTable.setWidget(4, 1, percentAbove75);

		Label label_8 = new Label("Distance to target(s) (yards)");
		label_8.setWordWrap(false);
		flexTable.setWidget(5, 0, label_8);

		distance = new NumberSpinner();
		distance.setVisibleLength(4);
		distance.setTitle("Average distance (in yards) to target(s) (for Zei's Stone of Vengeance).");
		distance.setText("0");
		flexTable.setWidget(5, 1, distance);

		Label lblSpacingBetweenTargets = new Label(
				"Spacing between targets (yards):");
		lblSpacingBetweenTargets.setWordWrap(false);
		flexTable.setWidget(6, 0, lblSpacingBetweenTargets);

		targetSpacing = new NumberSpinner();
		targetSpacing.setVisibleLength(4);
		targetSpacing
				.setTitle("Average distance (in yards) between target(s) (for Grenades, certain Marked for Death runes and Single Out).");
		targetSpacing.setText("0");
		flexTable.setWidget(6, 1, targetSpacing);

		Label lblTargetSizefor = new Label("Target Size (for Ball Lightning):");
		lblTargetSizefor.setWordWrap(false);
		flexTable.setWidget(7, 0, lblTargetSizefor);

		targetSize = new ListBox();
		targetSize.setTitle("Target Size");
		flexTable.setWidget(7, 1, targetSize);
		targetSize.setWidth("100%");
		
		Label label = new Label("# Health Globes:");
		flexTable.setWidget(8, 0, label);
		
		numHealthGlobes = new NumberSpinner();
		numHealthGlobes.setVisibleLength(4);
		numHealthGlobes.setTitle("# of Health Globes picked up during fight");
		flexTable.setWidget(8, 1, numHealthGlobes);
		
		Label label_1 = new Label("Average Firing Delay (ms):");
		label_1.setWordWrap(false);
		flexTable.setWidget(9, 0, label_1);
		
		firingDelay = new NumberSpinner();
		firingDelay.setVisibleLength(4);
		firingDelay.setTitle("Average delay (in milliseconds) of player actions.");
		flexTable.setWidget(9, 1, firingDelay);
		
		this.distance.setMax(100);
		this.targetSpacing.setMax(100);
		this.percentAbove75.setMax(100);
		this.percentAtLeast10Yards.setMax(100);
		this.percentControlled.setMax(100);
		this.percentSlowedChilled.setMax(100);
		this.numHealthGlobes.setMin(0);
		this.firingDelay.setMin(0);
		this.firingDelay.setMax(1000);
		
		
		for (TargetSize t : TargetSize.values()) {
			targetSize.addItem(t.getDisplayName(), t.name());
		}
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

	public NumberSpinner getPercentAbove75() {
		return percentAbove75;
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
}
