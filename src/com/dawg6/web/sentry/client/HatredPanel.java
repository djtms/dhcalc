package com.dawg6.web.sentry.client;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class HatredPanel extends Composite {
	private final NumberSpinner maxHatred;
	private final DoubleSpinner hatredPerSecond;
	
	public HatredPanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Passives");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		Label lblNewLabel = new Label("Maximum Hatred:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		maxHatred = new NumberSpinner();
		maxHatred.setVisibleLength(5);
		flexTable.setWidget(0, 1, maxHatred);
		
		Label lblHatredPerSecond = new Label("Hatred per Second:");
		flexTable.setWidget(1, 0, lblHatredPerSecond);
		
		hatredPerSecond = new DoubleSpinner();
		hatredPerSecond.setVisibleLength(5);
		flexTable.setWidget(1, 1, hatredPerSecond);
	}


	public NumberSpinner getMaxHatred() {
		return maxHatred;
	}


	public DoubleSpinner getHatredPerSecond() {
		return hatredPerSecond;
	}


}
