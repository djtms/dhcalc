package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.Util;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class ParagonPanel extends Composite {
	private final NumberSpinner paragonIAS;
	private final NumberSpinner paragonCC;
	private final NumberSpinner paragonCHD;
	private final Label chdLabel;
	private final Label ccLabel;
	private final Label iasLabel;
	private final NumberSpinner paragonCDR;
	private final Label cdrLabel;
	private final Label lblNewLabel;
	private final Label lblTotal;
	private final Label total;
	private final Label lblRequiredParagonLevel;
	private final Label level;
	private final HorizontalPanel horizontalPanel;
	private final Label label_3;
	private final Label lblParagonHatredpoints;
	private final Label lblParagonRcrpoints;
	private final Label rcrLabel;
	private final Label hatredLabel;
	private final NumberSpinner paragonHatred;
	private final NumberSpinner paragonRCR;
	
	public ParagonPanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Paragon");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		lblNewLabel = new Label("Note: Paragon points must be entered manually (they are not availble via the battle.net API)");
		lblNewLabel.setWordWrap(true);
		lblNewLabel.addStyleName("boldText");
		flexTable.setWidget(0, 0, lblNewLabel);
		lblNewLabel.setWidth("300px");
		
		Label label = new Label("Paragon IAS (points):");
		label.setWordWrap(false);
		flexTable.setWidget(1, 0, label);
		
		paragonIAS = new NumberSpinner();
		paragonIAS.setTitle("Each paragon IAS point adds .2% IAS");
		paragonIAS.setVisibleLength(6);
		flexTable.setWidget(1, 1, paragonIAS);
		
		paragonIAS.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateIASLabel();
				updateTotal();
			}});
		
		iasLabel = new Label("(0%)");
		iasLabel.setStyleName("boldText");
		flexTable.setWidget(1, 2, iasLabel);
		
		Label paragonCDRlabel = new Label("Paragon CDR (points):");
		paragonCDRlabel.setWordWrap(false);
		flexTable.setWidget(2, 0, paragonCDRlabel);
		
		paragonCDR = new NumberSpinner();
		paragonCDR.setTitle("Each paragon CDR point adds .2% Cooldown Reduction");
		paragonCDR.setVisibleLength(6);
		flexTable.setWidget(2, 1, paragonCDR);
		
		paragonCDR.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCDRLabel();
				updateTotal();
			}});

		cdrLabel = new Label("(0%)");
		cdrLabel.setStyleName("boldText");
		flexTable.setWidget(2, 2, cdrLabel);
		
		Label label_1 = new Label("Paragon CC  (points):");
		label_1.setWordWrap(false);
		flexTable.setWidget(3, 0, label_1);
		
		paragonCC = new NumberSpinner();
		paragonCC.setTitle("Each paragon CC point adds .1 % Crit Chance");
		paragonCC.setVisibleLength(6);
		paragonCC.setText("0");
		flexTable.setWidget(3, 1, paragonCC);
		
		paragonCC.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCCLabel();
				updateTotal();
			}});
		
		ccLabel = new Label("(0%)");
		ccLabel.setStyleName("boldText");
		flexTable.setWidget(3, 2, ccLabel);
		
		Label label_2 = new Label("Paragon CHD (points):");
		label_2.setWordWrap(false);
		flexTable.setWidget(4, 0, label_2);
		
		paragonCHD = new NumberSpinner();
		paragonCHD.setTitle("Each paragon CHD point add 1% Crit Hit Damage");
		paragonCHD.setVisibleLength(6);
		paragonCHD.setText("0");
		flexTable.setWidget(4, 1, paragonCHD);
		
		paragonCHD.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCHDLabel();
				updateTotal();
			}});
		
		
		chdLabel = new Label("(0%)");
		chdLabel.setStyleName("boldText");
		flexTable.setWidget(4, 2, chdLabel);
		flexTable.getFlexCellFormatter().setColSpan(0, 0, 3);
		
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(2);
		flexTable.setWidget(5, 0, horizontalPanel);
		
		lblTotal = new Label("Total Offense Points:");
		horizontalPanel.add(lblTotal);
		lblTotal.setWordWrap(false);
		
		total = new Label("0");
		horizontalPanel.add(total);
		total.setStyleName("boldText");
		
		lblRequiredParagonLevel = new Label(" (Requires paragon level");
		horizontalPanel.add(lblRequiredParagonLevel);
		lblRequiredParagonLevel.setWordWrap(false);
		
		level = new Label("0");
		horizontalPanel.add(level);
		level.setStyleName("boldText");
		
		label_3 = new Label(")");
		label_3.setWordWrap(false);
		horizontalPanel.add(label_3);
		
		lblParagonHatredpoints = new Label("Paragon Hatred (points):");
		lblParagonHatredpoints.setWordWrap(false);
		flexTable.setWidget(6, 0, lblParagonHatredpoints);
		
		paragonHatred = new NumberSpinner();
		paragonHatred.setVisibleLength(6);
		paragonHatred.setTitle("Each paragon Hatred point adds 0.5 Max Hatred");
		paragonHatred.setText("0");
		flexTable.setWidget(6, 1, paragonHatred);
		
		hatredLabel = new Label("(0)");
		hatredLabel.setStyleName("boldText");
		flexTable.setWidget(6, 2, hatredLabel);
		
		lblParagonRcrpoints = new Label("Paragon RCR (points):");
		lblParagonRcrpoints.setWordWrap(false);
		flexTable.setWidget(7, 0, lblParagonRcrpoints);
		
		paragonRCR = new NumberSpinner();
		paragonRCR.setVisibleLength(6);
		paragonRCR.setTitle("Each paragon RCR point adds 0.1% Resource Cost Reduction");
		paragonRCR.setText("0");
		flexTable.setWidget(7, 1, paragonRCR);
		
		rcrLabel = new Label("(0%)");
		rcrLabel.setStyleName("boldText");
		flexTable.setWidget(7, 2, rcrLabel);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getFlexCellFormatter().setColSpan(5, 0, 3);
		
		paragonIAS.setMax(50);
		paragonCC.setMax(50);
		paragonCHD.setMax(50);
		paragonCDR.setMax(50);
		paragonHatred.setMax(50);
		paragonRCR.setMax(50);
		
		paragonHatred.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateHatredLabel();
			}});

		paragonRCR.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateRCRLabel();
			}});
	}
	
	protected void updateTotal() {
		int total = paragonIAS.getValue() + paragonCDR.getValue() + paragonCC.getValue() + paragonCHD.getValue();
		this.total.setText(String.valueOf(total));
		this.level.setText(String.valueOf(Math.max(0, total * 4 - 2)));
	}

	protected void updateCDRLabel() {
		cdrLabel.setText("(" + Util.format((double)paragonCDR.getValue() * .2) + "%)");
	}

	protected void updateCHDLabel() {
		chdLabel.setText("(" + paragonCHD.getValue() + "%)");
	}

	protected void updateCCLabel() {
		ccLabel.setText("(" + Util.format((double)paragonCC.getValue() / 10.0) + "%)");
	}

	protected void updateIASLabel() {
		iasLabel.setText("(" + Util.format((double)paragonIAS.getValue() * .2) + "%)");
	}

	protected void updateRCRLabel() {
		rcrLabel.setText("(" + Util.format((double)paragonRCR.getValue() * .2) + "%)");
	}

	protected void updateHatredLabel() {
		hatredLabel.setText("(" + Util.format((double)paragonRCR.getValue() * .5) + ")");
	}

	public NumberSpinner getParagonIAS() {
		return paragonIAS;
	}
	public NumberSpinner getParagonCC() {
		return paragonCC;
	}
	public NumberSpinner getParagonCHD() {
		return paragonCHD;
	}

	public NumberSpinner getParagonCDR() {
		return paragonCDR;
	}

	public NumberSpinner getParagonHatred() {
		return paragonHatred;
	}

	public NumberSpinner getParagonRCR() {
		return paragonRCR;
	}

}
