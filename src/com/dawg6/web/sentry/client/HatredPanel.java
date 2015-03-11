package com.dawg6.web.sentry.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class HatredPanel extends Composite {
	private final DoubleSpinner maxHatred;
	private final DoubleSpinner hatredPerSecond;
	private final SimpleCheckBox preparationPunishment;
	
	public HatredPanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Hatred");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		Label lblNewLabel = new Label("Maximum Hatred:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		maxHatred = new DoubleSpinner();
		maxHatred.setVisibleLength(5);
		flexTable.setWidget(0, 1, maxHatred);
		
		Label lblHatredPerSecond = new Label("Hatred per Second:");
		flexTable.setWidget(1, 0, lblHatredPerSecond);
		
		hatredPerSecond = new DoubleSpinner();
		hatredPerSecond.setVisibleLength(5);
		maxHatred.setMin(125.0);
		maxHatred.setMax(200.0);
		
		flexTable.setWidget(1, 1, hatredPerSecond);
		
		Anchor label = new Anchor("Hatred per Second:");
		label.setTarget("_blank");
		label.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/preparation#a+");
		label.setText("Preparation/Punishment:");
		label.setHTML("Preparation/Punishment:");
		flexTable.setWidget(2, 0, label);
		
		preparationPunishment = new SimpleCheckBox();
		flexTable.setWidget(2, 1, preparationPunishment);
	}


	public DoubleSpinner getMaxHatred() {
		return maxHatred;
	}


	public DoubleSpinner getHatredPerSecond() {
		return hatredPerSecond;
	}

	public SimpleCheckBox getPreparationPunishment() {
		return preparationPunishment;
	}

}
