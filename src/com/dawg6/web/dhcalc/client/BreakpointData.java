package com.dawg6.web.dhcalc.client;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.web.dhcalc.shared.calculator.BreakPoint;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class BreakpointData extends ApplicationPanel {
	public BreakpointData() {
		
		FlexTable bpFlexTable = new FlexTable();
		bpFlexTable.setStyleName("breakpointTable");
		bpFlexTable.setCellPadding(5);
		bpFlexTable.setBorderWidth(1);
		initWidget(bpFlexTable);
		
		Label[] bpHeadings = new Label[] { new Label("#"), new Label("APS"),
				new Label("Sentry APS"), new Label("Attacks/" + BreakPoint.DURATION +"sec") };

		bpFlexTable.getRowFormatter().addStyleName(0, "headerRow");
		
		for (int i = 0; i < bpHeadings.length; i++) {
			bpFlexTable.setWidget(0, i, bpHeadings[i]);
		}

		for (int i = 0; i < BreakPoint.ALL.length; i++) {
			
			if ((i % 2) == 0)
				bpFlexTable.getRowFormatter().addStyleName(i+1, "oddRow");
			else
				bpFlexTable.getRowFormatter().addStyleName(i+1, "evenRow");
			
			BreakPoint bp = BreakPoint.ALL[i];
			Label[] labels = new Label[] {
					new Label(String.valueOf(bp.getBp())),
					new Label(String.valueOf(bp.getAps())),
					new Label(
							String.valueOf(Util.format(bp.getQty() / (double)BreakPoint.DURATION))),
					new Label(String.valueOf(bp.getQty())) };

			for (int j = 0; j < labels.length; j++) {
				bpFlexTable.setWidget(i + 1, j, labels[j]);
			}
		}

	}

}
