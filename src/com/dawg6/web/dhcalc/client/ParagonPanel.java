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

import java.util.List;
import java.util.Vector;

import com.dawg6.gwt.client.widgets.ImageButton;
import com.dawg6.gwt.client.widgets.SimpleCaptionPanel;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.Widget;

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
	private final NumberSpinner paragonLevel;
	private int firstRow = 999;
	private int lastRow = -1;
	private final int[] priorities = { 0, 1, 2, 3};
	private final FlexTable offenseTable;
	private final List<Listener> listeners = new Vector<Listener>();
	private boolean disableListeners = false;
	private final SimpleCheckBox autoFill;
	
	public interface Listener {
		void paragonsChanged();
	}
	
	public ParagonPanel() {
		
		SimpleCaptionPanel caption = new SimpleCaptionPanel("Paragon");
		initWidget(caption);
		
		FlexTable table1 = new FlexTable();
		caption.setContentWidget(table1);
		
		FlexTable table2 = new FlexTable();
		table2.setCellPadding(2);
		table1.setWidget(0, 0, table2);
		table1.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		offenseTable = new FlexTable();
		offenseTable.setCellPadding(2);
		table1.setWidget(0, 1, offenseTable);
		table1.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);

		int row = 0;
		
		lblNewLabel = new Label(
			"Note: Paragon points other than Dexterity must be adjusted manually after import" 
			+ " (they are not available via the battle.net API)."
			+ " Offense points will be filled in order of priority (top to bottom)."
				);
		lblNewLabel.setWordWrap(true);
		lblNewLabel.addStyleName("boldText");
		table2.setWidget(row, 0, lblNewLabel);
		lblNewLabel.setWidth("300px");
		table2.getFlexCellFormatter().setColSpan(row, 0, 3);
		
		row++;
		
		lblParagonDexteritypoints = new Label("Paragon Dexterity (points):");
		lblParagonDexteritypoints.setWordWrap(false);
		table2.setWidget(row, 0, lblParagonDexteritypoints);
		
		paragonDexterity = new NumberSpinner();
		paragonDexterity.setVisibleLength(6);
		paragonDexterity.setTitle("Each paragon Dexterity point adds 5 Dexterity");
		table2.setWidget(row, 1, paragonDexterity);
		paragonDexterity.setMax(10000);
		
		paragonDexterity.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateDexterityLabel();
				valuesChanged();
			}});

		dexLabel = new Label("(0)");
		dexLabel.setStyleName("boldText");
		table2.setWidget(row, 2, dexLabel);
		
		row++;
		
		lblParagonHatredpoints = new Label("Paragon Hatred (points):");
		lblParagonHatredpoints.setWordWrap(false);
		table2.setWidget(row, 0, lblParagonHatredpoints);
		
		paragonHatred = new NumberSpinner();
		paragonHatred.setVisibleLength(6);
		paragonHatred.setTitle("Each paragon Hatred point adds 0.5 Max Hatred");
		paragonHatred.setText("0");
		table2.setWidget(row, 1, paragonHatred);
		paragonHatred.setMax(50);
		
		paragonHatred.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateHatredLabel();
				valuesChanged();
			}});
		
		hatredLabel = new Label("(0)");
		hatredLabel.setStyleName("boldText");
		table2.setWidget(row, 2, hatredLabel);
		
		row++;
		
		lblParagonRcrpoints = new Label("Paragon RCR (points):");
		lblParagonRcrpoints.setWordWrap(false);
		table2.setWidget(row, 0, lblParagonRcrpoints);
		
		paragonRCR = new NumberSpinner();
		paragonRCR.setVisibleLength(6);
		paragonRCR.setTitle("Each paragon RCR point adds 0.1% Resource Cost Reduction");
		paragonRCR.setText("0");
		table2.setWidget(row, 1, paragonRCR);
		paragonRCR.setMax(50);
		
		paragonRCR.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateRCRLabel();
				valuesChanged();
			}});
		
		rcrLabel = new Label("(0%)");
		rcrLabel.setStyleName("boldText");
		table2.setWidget(row, 2, rcrLabel);
		
		row++;
		
		label_4 = new Label("Paragon Area Damage (points):");
		label_4.setWordWrap(false);
		table2.setWidget(row, 0, label_4);
		
		paragonAD = new NumberSpinner();
		paragonAD.setVisibleLength(6);
		paragonAD.setTitle("Each paragon AD point adds 1% Area Damage");
		paragonAD.setText("0");
		table2.setWidget(row, 1, paragonAD);
		
		paragonAD.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateADLabel();
				valuesChanged();
			}});

		adLabel = new Label("(0%)");
		adLabel.setStyleName("boldText");
		table2.setWidget(row, 2, adLabel);
		
		row = 0;
		
		Label label1 = new Label("Paragon Level:");
		label1.setWordWrap(false);
		offenseTable.setWidget(row, 0, label1);
		
		paragonLevel = new NumberSpinner();
		paragonLevel.setTitle("Hero Paragon Level");
		paragonLevel.setMin(0);
		paragonLevel.setVisibleLength(6);
		offenseTable.setWidget(row, 1, paragonLevel);
		
		paragonLevel.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				valuesChanged();
			}});
		
		Button calcButton = new Button("Fill");
		calcButton.setTitle("Click to fill paragon offense points based on level and order of priorities");
		calcButton.setWidth("100%");
		offenseTable.setWidget(row, 3, calcButton);
		offenseTable.getFlexCellFormatter().setColSpan(row, 3, 2);
		
		calcButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fillOffensePoints();
				
			}});
		
		row++;
		
		Label label2 = new Label("Auto Fill on Import:");
		label2.setWordWrap(false);
		offenseTable.setWidget(row, 0, label2);

		autoFill = new SimpleCheckBox();
		autoFill.setTitle("Check this to fill Offense paragon points on import");
		offenseTable.setWidget(row, 1, autoFill);
		
		row++;
		
		lblOffensePoints = new Label("Offense Points:");
		lblOffensePoints.setWordWrap(false);
		lblOffensePoints.addStyleName("boldText");
		offenseTable.setWidget(row, 0, lblOffensePoints);
		offenseTable.getFlexCellFormatter().setColSpan(row, 0, 3);
		
		row++;
		
		Label label = new Label("Paragon IAS (points):");
		label.setWordWrap(false);
		offenseTable.setWidget(row, 0, label);
		
		paragonIAS = new NumberSpinner();
		paragonIAS.setTitle("Each paragon IAS point adds .2% IAS");
		paragonIAS.setVisibleLength(6);
		offenseTable.setWidget(row, 1, paragonIAS);
		
		paragonIAS.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateIASLabel();
				updateTotal();
			}});
		
		iasLabel = new Label("(0%)");
		iasLabel.setStyleName("boldText");
		offenseTable.setWidget(row, 2, iasLabel);
		
		addUpDownButtons(offenseTable, row);
		
		row++;
		
		Label paragonCDRlabel = new Label("Paragon CDR (points):");
		paragonCDRlabel.setWordWrap(false);
		offenseTable.setWidget(row, 0, paragonCDRlabel);
		
		paragonCDR = new NumberSpinner();
		paragonCDR.setTitle("Each paragon CDR point adds .2% Cooldown Reduction");
		paragonCDR.setVisibleLength(6);
		offenseTable.setWidget(row, 1, paragonCDR);
		
		paragonCDR.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCDRLabel();
				updateTotal();
			}});

		cdrLabel = new Label("(0%)");
		cdrLabel.setStyleName("boldText");
		offenseTable.setWidget(row, 2, cdrLabel);
		
		addUpDownButtons(offenseTable, row);
		
		row++;
		
		Label label_1 = new Label("Paragon CHC  (points):");
		label_1.setWordWrap(false);
		offenseTable.setWidget(row, 0, label_1);
		
		paragonCC = new NumberSpinner();
		paragonCC.setTitle("Each paragon CHC point adds .1 % Crit Chance");
		paragonCC.setVisibleLength(6);
		paragonCC.setText("0");
		offenseTable.setWidget(row, 1, paragonCC);
		
		paragonCC.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCCLabel();
				updateTotal();
			}});
		
		ccLabel = new Label("(0%)");
		ccLabel.setStyleName("boldText");
		offenseTable.setWidget(row, 2, ccLabel);
		
		addUpDownButtons(offenseTable, row);
		
		row++;
		
		Label label_2 = new Label("Paragon CHD (points):");
		label_2.setWordWrap(false);
		offenseTable.setWidget(row, 0, label_2);
		
		paragonCHD = new NumberSpinner();
		paragonCHD.setTitle("Each paragon CHD point add 1% Crit Hit Damage");
		paragonCHD.setVisibleLength(6);
		paragonCHD.setText("0");
		offenseTable.setWidget(row, 1, paragonCHD);
		
		paragonCHD.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateCHDLabel();
				updateTotal();
			}});
		
		
		chdLabel = new Label("(0%)");
		chdLabel.setStyleName("boldText");
		offenseTable.setWidget(row, 2, chdLabel);
		
		addUpDownButtons(offenseTable, row);
		
		row++;
		
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(2);
		offenseTable.setWidget(row, 0, horizontalPanel);
		
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
		offenseTable.getCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		offenseTable.getFlexCellFormatter().setColSpan(row, 0, 5);

		paragonIAS.setMax(50);
		paragonCC.setMax(50);
		paragonCHD.setMax(50);
		paragonCDR.setMax(50);
		paragonAD.setMax(50);

	}
	
	private void addUpDownButtons(final FlexTable table, final int row) {
		
		if (row < firstRow)
			firstRow = row;
		
		if (row > lastRow)
			lastRow = row;
		
		ImageButton up = new ImageButton(Images.UP, "move up in priority");
		ImageButton down = new ImageButton(Images.DOWN, "move down in priority");
		
		table.setWidget(row, 3, up);
		table.setWidget(row, 4, down);
		
		up.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				if (row > firstRow) 
					moveFields(table, row, -1);
			}});

		down.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				if (row < lastRow) 
					moveFields(table, row, 1);
			}});
	}

	protected void moveFields(FlexTable table, int row, int dir) {
		for (int i = 0; i < 3; i++) {
			Widget a = table.getWidget(row, i);
			Widget b = table.getWidget(row + dir, i);
			table.setWidget(row, i, b);
			table.setWidget(row + dir, i, a);
		}
		
		int a = priorities[row - firstRow];
		int b = priorities[row - firstRow + dir];
		priorities[row - firstRow] = b;
		priorities[row - firstRow + dir] = a;
	}

	public void fillOffensePoints() {
		calculateParagonPoints(this.offenseTable);
	}
	
	protected void calculateParagonPoints(FlexTable table) {
		int level = this.paragonLevel.getValue();
		int points = (level + 2) / 4;
		
		for (int i = 0; i < 4; i++) {
			NumberSpinner n = (NumberSpinner)table.getWidget(firstRow + i, 1);
			int value = Math.min(50, points);
			
			n.setValue(value);
			points -= value;
		}
	}

	protected void updateTotal() {
		int total = paragonIAS.getValue() + paragonCDR.getValue() + paragonCC.getValue() + paragonCHD.getValue();
		this.total.setText(String.valueOf(total));
		this.level.setText(String.valueOf(Math.max(0, total * 4 - 2)));
		
		valuesChanged();
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

	public NumberSpinner getParagonLevel() {
		return paragonLevel;
	}

	public NumberSpinner getParagonAD() {
		return paragonAD;
	}

	public NumberSpinner getParagonDexterity() {
		return paragonDexterity;
	}

	public String getParagonPriorities() {
		StringBuffer buf = new StringBuffer();
		
		for (int i = 0; i < 4; i++)
			buf.append(String.valueOf(this.priorities[i]));

		return buf.toString();
	}
	
	public void setParagonPriorities(String s) {
		if (s == null)
			s = "0123";
		
		s = s.trim();
		
		if (s.length() != 4)
			s = "0123";

		int[] def = { 0, 1, 2, 3};
		int[] p = new int[4];
		
		for (int i = 0; i < 4; i++) {
			char c = s.charAt(i);

			if ((c < '0') || (c > '3')) {
				p = def;
				break;
			} else {
				p[i] = c - '0';
			}
		}
		
		setParagonPriorities(p);
	}
	
	public void setParagonPriorities(int[] p) {
		
		Widget[][] widgets = new Widget[4][3];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				widgets[priorities[i]][j] = offenseTable.getWidget(firstRow + i, j);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				Widget w = widgets[p[i]][j];
				offenseTable.setWidget(firstRow + i, j, w);
			}

			priorities[i] = p[i];
		}
	}
	
	public void enableListeners() {
		this.disableListeners = false;
	}
	
	public void disableListeners() {
		this.disableListeners = false;
	}
	
	private void valuesChanged() {
		if (!disableListeners) {
			for (Listener l : listeners)
				l.paragonsChanged();
		}
	}
	
	public void addListener(Listener l) {
		if (!listeners.contains(l))
			listeners.add(l);
	}
	
	public void removeListener(Listener l) {
		listeners.remove(l);
	}
	
	public SimpleCheckBox getAutoFill() {
		return autoFill;
	}
}
