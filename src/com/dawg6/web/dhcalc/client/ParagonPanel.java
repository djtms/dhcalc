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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import com.dawg6.web.dhcalc.shared.calculator.Util;
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
	private final Label lblParagonDexteritypoints;
	private final NumberSpinner paragonDexterity;
	private final Label dexLabel;
	private final Label lblOffensePoints;
	private final Label label_4;
	private final NumberSpinner paragonAD;
	private final Label adLabel;
	
	public ParagonPanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Paragon");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		lblNewLabel = new Label("Note: Paragon points other than Dexterity must be entered manually (they are not availble via the battle.net API)");
		lblNewLabel.setWordWrap(true);
		lblNewLabel.addStyleName("boldText");
		flexTable.setWidget(0, 0, lblNewLabel);
		lblNewLabel.setWidth("300px");
		
		lblParagonDexteritypoints = new Label("Paragon Dexterity (points):");
		lblParagonDexteritypoints.setWordWrap(false);
		flexTable.setWidget(1, 0, lblParagonDexteritypoints);
		
		paragonDexterity = new NumberSpinner();
		paragonDexterity.setVisibleLength(6);
		paragonDexterity.setTitle("Each paragon Dexterity point adds 5 Dexterity");
		flexTable.setWidget(1, 1, paragonDexterity);
		paragonDexterity.setMax(10000);
		
		paragonDexterity.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateDexterityLabel();
			}});

		dexLabel = new Label("(0)");
		dexLabel.setStyleName("boldText");
		flexTable.setWidget(1, 2, dexLabel);
		
		lblParagonHatredpoints = new Label("Paragon Hatred (points):");
		lblParagonHatredpoints.setWordWrap(false);
		flexTable.setWidget(2, 0, lblParagonHatredpoints);
		
		paragonHatred = new NumberSpinner();
		paragonHatred.setVisibleLength(6);
		paragonHatred.setTitle("Each paragon Hatred point adds 0.5 Max Hatred");
		paragonHatred.setText("0");
		flexTable.setWidget(2, 1, paragonHatred);
		paragonHatred.setMax(50);
		
		paragonHatred.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateHatredLabel();
			}});
		
		hatredLabel = new Label("(0)");
		hatredLabel.setStyleName("boldText");
		flexTable.setWidget(2, 2, hatredLabel);
		
		lblParagonRcrpoints = new Label("Paragon RCR (points):");
		lblParagonRcrpoints.setWordWrap(false);
		flexTable.setWidget(3, 0, lblParagonRcrpoints);
		
		paragonRCR = new NumberSpinner();
		paragonRCR.setVisibleLength(6);
		paragonRCR.setTitle("Each paragon RCR point adds 0.1% Resource Cost Reduction");
		paragonRCR.setText("0");
		flexTable.setWidget(3, 1, paragonRCR);
		paragonRCR.setMax(50);
		
		paragonRCR.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateRCRLabel();
			}});
		
		rcrLabel = new Label("(0%)");
		rcrLabel.setStyleName("boldText");
		flexTable.setWidget(3, 2, rcrLabel);
		
		label_4 = new Label("Paragon Area Damage (points):");
		label_4.setWordWrap(false);
		flexTable.setWidget(4, 0, label_4);
		
		paragonAD = new NumberSpinner();
		paragonAD.setVisibleLength(6);
		paragonAD.setTitle("Each paragon AD point adds 1% Area Damage");
		paragonAD.setText("0");
		flexTable.setWidget(4, 1, paragonAD);
		
		paragonAD.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateADLabel();
			}});

		adLabel = new Label("(0%)");
		adLabel.setStyleName("boldText");
		flexTable.setWidget(4, 2, adLabel);
		
		lblOffensePoints = new Label("Offense Points:");
		lblOffensePoints.setWordWrap(false);
		lblOffensePoints.addStyleName("boldText");
		flexTable.setWidget(5, 0, lblOffensePoints);
		
		Label label = new Label("Paragon IAS (points):");
		label.setWordWrap(false);
		flexTable.setWidget(6, 0, label);
		
		paragonIAS = new NumberSpinner();
		paragonIAS.setTitle("Each paragon IAS point adds .2% IAS");
		paragonIAS.setVisibleLength(6);
		flexTable.setWidget(6, 1, paragonIAS);
		
		paragonIAS.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateIASLabel();
				updateTotal();
			}});
		
		iasLabel = new Label("(0%)");
		iasLabel.setStyleName("boldText");
		flexTable.setWidget(6, 2, iasLabel);
		
		Label paragonCDRlabel = new Label("Paragon CDR (points):");
		paragonCDRlabel.setWordWrap(false);
		flexTable.setWidget(7, 0, paragonCDRlabel);
		
		paragonCDR = new NumberSpinner();
		paragonCDR.setTitle("Each paragon CDR point adds .2% Cooldown Reduction");
		paragonCDR.setVisibleLength(6);
		flexTable.setWidget(7, 1, paragonCDR);
		
		paragonCDR.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCDRLabel();
				updateTotal();
			}});

		cdrLabel = new Label("(0%)");
		cdrLabel.setStyleName("boldText");
		flexTable.setWidget(7, 2, cdrLabel);
		
		Label label_1 = new Label("Paragon CC  (points):");
		label_1.setWordWrap(false);
		flexTable.setWidget(8, 0, label_1);
		
		paragonCC = new NumberSpinner();
		paragonCC.setTitle("Each paragon CC point adds .1 % Crit Chance");
		paragonCC.setVisibleLength(6);
		paragonCC.setText("0");
		flexTable.setWidget(8, 1, paragonCC);
		
		paragonCC.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCCLabel();
				updateTotal();
			}});
		
		ccLabel = new Label("(0%)");
		ccLabel.setStyleName("boldText");
		flexTable.setWidget(8, 2, ccLabel);
		
		Label label_2 = new Label("Paragon CHD (points):");
		label_2.setWordWrap(false);
		flexTable.setWidget(9, 0, label_2);
		
		paragonCHD = new NumberSpinner();
		paragonCHD.setTitle("Each paragon CHD point add 1% Crit Hit Damage");
		paragonCHD.setVisibleLength(6);
		paragonCHD.setText("0");
		flexTable.setWidget(9, 1, paragonCHD);
		
		paragonCHD.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCHDLabel();
				updateTotal();
			}});
		
		
		chdLabel = new Label("(0%)");
		chdLabel.setStyleName("boldText");
		flexTable.setWidget(9, 2, chdLabel);
		
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(2);
		flexTable.setWidget(10, 0, horizontalPanel);
		
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
		flexTable.getCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getFlexCellFormatter().setColSpan(10, 0, 3);
		flexTable.getFlexCellFormatter().setColSpan(0, 0, 3);
		flexTable.getFlexCellFormatter().setColSpan(5, 0, 3);
		
		paragonIAS.setMax(50);
		paragonCC.setMax(50);
		paragonCHD.setMax(50);
		paragonCDR.setMax(50);
		paragonAD.setMax(50);
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

	protected void updateADLabel() {
		adLabel.setText("(" + Util.format(paragonAD.getValue()) + "%)");
	}

	protected void updateHatredLabel() {
		hatredLabel.setText("(" + Util.format((double)paragonRCR.getValue() * .5) + ")");
	}

	protected void updateDexterityLabel() {
		dexLabel.setText("(" + Util.format(paragonDexterity.getValue() * 5) + ")");
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

	public NumberSpinner getParagonAD() {
		return paragonAD;
	}

	public NumberSpinner getParagonDexterity() {
		return paragonDexterity;
	}

}
