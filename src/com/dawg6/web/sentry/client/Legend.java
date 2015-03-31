package com.dawg6.web.sentry.client;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.web.sentry.shared.calculator.DamageMultiplier;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class Legend extends ApplicationPanel {

	private final FlexTable flexTable;

	public Legend() {
		
		flexTable = new FlexTable();
		flexTable.setStyleName("outputTable");
		flexTable.setBorderWidth(1);
		flexTable.setCellPadding(5);
		flexTable.getRowFormatter().addStyleName(0, "headerRow");
		initWidget(flexTable);
		
		Label lblNewLabel = new Label("Key");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(0, 0, lblNewLabel);
		
		Label lblAccumulator = new Label("How Calculated");
		lblAccumulator.setWordWrap(false);
		flexTable.setWidget(0, 1, lblAccumulator);
		
		Label lblDescription = new Label("Description");
		lblDescription.setWordWrap(false);
		flexTable.setWidget(0, 2, lblDescription);

		int row = 1;
		
		List<DamageMultiplier> sortedList = new Vector<DamageMultiplier>();
		
		for (DamageMultiplier d : DamageMultiplier.values()) {
			sortedList.add(d);
		}

		Collections.sort(sortedList, new Comparator<DamageMultiplier>(){

			@Override
			public int compare(DamageMultiplier o1, DamageMultiplier o2) {
				return o1.getAbbreviation().toLowerCase().compareTo(o2.getAbbreviation().toLowerCase());
			}});
		
		for (DamageMultiplier d : sortedList) {
			if ((row % 2) == 0)
				flexTable.getRowFormatter().addStyleName(row, "evenRow");
			else
				flexTable.getRowFormatter().addStyleName(row, "oddRow");
			
			addLabel(row, 0, d.getAbbreviation());
			addLabel(row, 1, d.getAccumulator().getDescription());
			addLabel(row, 2, d.getDescription());
			row++;
		}
		
	}

	private void addLabel(int row, int column, String text) {
		Label label = new Label(text);
		label.setWordWrap(false);
		
		flexTable.setWidget(row, column, label);
	}
}
