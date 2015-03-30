package com.dawg6.web.sentry.client;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class HatredPanel extends Composite {
	private final DoubleSpinner hatredPerSecond;
	private final NumberSpinner equipmentDiscipline;

	public HatredPanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Hatred/Discipline");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
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
