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
import com.dawg6.web.dhcalc.shared.calculator.SpecialItemType;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class PlayerBuffPanel extends Composite {
	private final SimpleCheckBox wolf;
	private final DoubleSpinner wolfUptime;
	private final Button calcWolfButton;
	private final SimpleCheckBox bbv;
	private final DoubleSpinner bbvUptime;
	private final SimpleCheckBox massConfusion;
	private final DoubleSpinner massConfusionUptime;
	private final SimpleCheckBox innerSanctuary;
	private final DoubleSpinner innerSanctuaryUptime;
	private final SimpleCheckBox conviction;
	private final SimpleCheckBox cripplingWave;
	private final DoubleSpinner cripplingWaveUptime;
	private final DoubleSpinner convictionPassiveUptime;
	private final SimpleCheckBox piranhas;
	private final DoubleSpinner piranhasUptime;
	private final SimpleCheckBox overawe;
	private final DoubleSpinner convictionActiveUptime;
	protected boolean disableListeners = false;
	private final SimpleCheckBox valor;
	private final SimpleCheckBox retribution;
	private final DoubleSpinner retributionUptime;
	private final DoubleSpinner valorActiveUptime;
	private final DoubleSpinner valorPassiveUptime;
	private final SimpleCheckBox slamDance;
	private final SimpleCheckBox timeWarp;
	private final SimpleCheckBox stretchTime;
	private final NumberSpinner timeWarpUptime;
	private final NumberSpinner stretchTimeUptime;
	private final SimpleCheckBox oculus;
	private final NumberSpinner oculusPercent;
	private final NumberSpinner oculusUptime;
	private final SimpleCheckBox falter;
	private final DoubleSpinner falterUptime;
	private final SimpleCheckBox toxin;

	public PlayerBuffPanel() {
		
		disableListeners = true;

		SimpleCaptionPanel cptnpnlNewPanel = new SimpleCaptionPanel("Other Player Buffs");
		initWidget(cptnpnlNewPanel);

		FlexTable flexTable = new FlexTable();
		cptnpnlNewPanel.setContentWidget(flexTable);

		int row = 0;
		
		Anchor anchor = new Anchor("Companion/Wolf (from other DH):");
		anchor.setWordWrap(false);
		flexTable.setWidget(row, 0, anchor);
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/companion#c");

		wolf = new SimpleCheckBox();
		flexTable.setWidget(row, 1, wolf);

		Label lblNewLabel = new Label("% Uptime:");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(row, 2, lblNewLabel);

		wolfUptime = new DoubleSpinner();
		wolfUptime.setVisibleLength(5);
		wolfUptime.setValue(33.33);
		flexTable.setWidget(row, 3, wolfUptime);
		wolfUptime.setTitle("% of time that another player's Wolf will be active");

		calcWolfButton = new Button("Calculate");
		calcWolfButton.setTitle("Calculate Wolf uptime based on your effective Cooldown Reduction");
		flexTable.setWidget(row, 4, calcWolfButton);

		row++;
		
		Anchor anchor_6b = new Anchor("Gem of Efficacious Toxin (rank 25+):");
		anchor_6b.setWordWrap(false);
		anchor_6b.setTarget("_blank");
		anchor_6b.setHref("http://us.battle.net/d3/en/item/gem-of-efficacious-toxin");
		flexTable.setWidget(row, 0, anchor_6b);
		
		toxin = new SimpleCheckBox();
		flexTable.setWidget(row, 1, toxin);
		
		row++;
		
		Anchor anchor_1 = new Anchor("Big Bad Voodoo:");
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/big-bad-voodoo");
		flexTable.setWidget(row, 0, anchor_1);

		bbv = new SimpleCheckBox();
		flexTable.setWidget(row, 1, bbv);

		Label label = new Label("% Uptime:");
		label.setWordWrap(false);
		flexTable.setWidget(row, 2, label);

		bbvUptime = new DoubleSpinner();
		bbvUptime.setValue(17.67);
		bbvUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, bbvUptime);
		
		Anchor anchor_10 = new Anchor("Slam Dance:");
		anchor_10.setWordWrap(false);
		anchor_10.setTarget("_blank");
		anchor_10.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/big-bad-voodoo#a+");
		flexTable.setWidget(row, 4, anchor_10);
		
		slamDance = new SimpleCheckBox();
		flexTable.setWidget(row, 5, slamDance);
		slamDance.setTitle("Check this box if the Witch Doctor is using the Slam Dance rune");

		row++;
		
		Anchor anchor_1a = new Anchor("Slow Time/Stretch Time:");
		anchor_1a.setWordWrap(false);
		anchor_1a.setTarget("_blank");
		anchor_1a.setHref("http://us.battle.net/d3/en/class/wizard/active/slow-time#e+");
		flexTable.setWidget(row, 0, anchor_1a);

		stretchTime = new SimpleCheckBox();
		flexTable.setWidget(row, 1, stretchTime);

		Label labela = new Label("% Uptime:");
		labela.setWordWrap(false);
		flexTable.setWidget(row, 2, labela);

		stretchTimeUptime = new NumberSpinner();
		stretchTimeUptime.setValue(0);
		stretchTimeUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, stretchTimeUptime);

		row++;
		
		Anchor anchor_1b = new Anchor("Slow Time/Time Warp:");
		anchor_1b.setWordWrap(false);
		anchor_1b.setTarget("_blank");
		anchor_1b.setHref("http://us.battle.net/d3/en/class/wizard/active/slow-time#a+");
		flexTable.setWidget(row, 0, anchor_1b);

		timeWarp = new SimpleCheckBox();
		flexTable.setWidget(row, 1, timeWarp);

		Label labelb = new Label("% Uptime:");
		labelb.setWordWrap(false);
		flexTable.setWidget(row, 2, labelb);

		timeWarpUptime = new NumberSpinner();
		timeWarpUptime.setValue(0);
		timeWarpUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, timeWarpUptime);

		row++;

		Anchor anchor_2 = new Anchor("Mass Confusion/Paranoia:");
		anchor_2.setWordWrap(false);
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/mass-confusion#a+");
		flexTable.setWidget(row, 0, anchor_2);

		massConfusion = new SimpleCheckBox();
		flexTable.setWidget(row, 1, massConfusion);

		Label label_1 = new Label("% Uptime:");
		label_1.setWordWrap(false);
		flexTable.setWidget(row, 2, label_1);

		massConfusionUptime = new DoubleSpinner();
		massConfusionUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, massConfusionUptime);
		
		row++;
		
		Anchor anchor_6 = new Anchor("Piranhas:");
		anchor_6.setWordWrap(false);
		anchor_6.setTarget("_blank");
		anchor_6.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/piranhas");
		flexTable.setWidget(row, 0, anchor_6);
		
		piranhas = new SimpleCheckBox();
		flexTable.setWidget(row, 1, piranhas);
		
		Label label_5 = new Label("% Uptime:");
		label_5.setWordWrap(false);
		flexTable.setWidget(row, 2, label_5);
		
		piranhasUptime = new DoubleSpinner();
		piranhasUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, piranhasUptime);
		
		row++;
		
		Anchor anchor_6a = new Anchor("Threatening Shout/Falter:");
		anchor_6a.setWordWrap(false);
		anchor_6a.setTarget("_blank");
		anchor_6a.setHref("http://us.battle.net/d3/en/class/barbarian/active/threatening-shout#d+");
		flexTable.setWidget(row, 0, anchor_6a);
		
		falter = new SimpleCheckBox();
		flexTable.setWidget(row, 1, falter);
		
		Label label_5a = new Label("% Uptime:");
		label_5a.setWordWrap(false);
		flexTable.setWidget(row, 2, label_5a);
		
		falterUptime = new DoubleSpinner();
		falterUptime.setVisibleLength(5);
		falterUptime.setValue(100.0);
		flexTable.setWidget(row, 3, falterUptime);
		
		row++;
		
		Anchor anchor_8 = new Anchor("Laws of Valor:");
		anchor_8.setWordWrap(false);
		anchor_8.setTarget("_blank");
		anchor_8.setHref("http://us.battle.net/d3/en/class/crusader/active/laws-of-valor");
		flexTable.setWidget(row, 0, anchor_8);
		
		valor = new SimpleCheckBox();
		flexTable.setWidget(row, 1, valor);
		
		Label label_4 = new Label("% Active:");
		label_4.setWordWrap(false);
		flexTable.setWidget(row, 2, label_4);
		
		valorActiveUptime = new DoubleSpinner();
		valorActiveUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, valorActiveUptime);
		valorActiveUptime.setTitle("Percent of the time that Laws of Valor's Active buff (only) applies");
		
		row++;
		
		Label label_4a = new Label("% Passive:");
		label_4a.setWordWrap(false);
		flexTable.setWidget(row, 2, label_4a);
		
		valorPassiveUptime = new DoubleSpinner();
		valorPassiveUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, valorPassiveUptime);
		valorPassiveUptime.setTitle("Percent of the time that Laws of Valor's Passive buff (only) applies");
		
		row++;
		
		Anchor anchor_3 = new Anchor("Inner Sanctuary/Forbidden Palace:");
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/class/monk/active/inner-sanctuary#e+");
		flexTable.setWidget(row, 0, anchor_3);
		
		innerSanctuary = new SimpleCheckBox();
		flexTable.setWidget(row, 1, innerSanctuary);
		
		Label label_2 = new Label("% Uptime:");
		label_2.setWordWrap(false);
		flexTable.setWidget(row, 2, label_2);
		
		innerSanctuaryUptime = new DoubleSpinner();
		innerSanctuaryUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, innerSanctuaryUptime);
		
		row++;
		
		Anchor anchor_4 = new Anchor("Crippling Wave/Breaking Wave:");
		anchor_4.setWordWrap(false);
		anchor_4.setTarget("_blank");
		anchor_4.setHref("http://us.battle.net/d3/en/class/monk/active/crippling-wave#e+");
		flexTable.setWidget(row, 0, anchor_4);
		
		cripplingWave = new SimpleCheckBox();
		flexTable.setWidget(row, 1, cripplingWave);
		
		Label label_3 = new Label("% Uptime:");
		label_3.setWordWrap(false);
		flexTable.setWidget(row, 2, label_3);
		
		cripplingWaveUptime = new DoubleSpinner();
		cripplingWaveUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, cripplingWaveUptime);
		
		row++;
		
		Anchor anchor_9 = new Anchor("Mantra of Retribution/Transgression:");
		anchor_9.setWordWrap(false);
		anchor_9.setTarget("_blank");
		anchor_9.setHref("http://us.battle.net/d3/en/class/monk/active/mantra-of-retribution#b+");
		flexTable.setWidget(row, 0, anchor_9);
		
		retribution = new SimpleCheckBox();
		flexTable.setWidget(row, 1, retribution);
		
		Label label_6 = new Label("% Uptime:");
		label_6.setWordWrap(false);
		flexTable.setWidget(row, 2, label_6);
		
		retributionUptime = new DoubleSpinner();
		retributionUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, retributionUptime);
		
		row++;
		
		Anchor anchor_5 = new Anchor("Mantra of Conviction:");
		anchor_5.setWordWrap(false);
		anchor_5.setTarget("_blank");
		anchor_5.setHref("http://us.battle.net/d3/en/class/monk/active/mantra-of-conviction");
		flexTable.setWidget(row, 0, anchor_5);
		
		conviction = new SimpleCheckBox();
		flexTable.setWidget(row, 1, conviction);
		
		Label lblPassiveUptime = new Label("% Passive:");
		lblPassiveUptime.setWordWrap(false);
		flexTable.setWidget(row, 2, lblPassiveUptime);
		
		convictionPassiveUptime = new DoubleSpinner();
		convictionPassiveUptime.setVisibleLength(5);
		convictionPassiveUptime.setTitle("Percent of time that Mantra of Conviction's passive bonus [only] applies");
		flexTable.setWidget(row, 3, convictionPassiveUptime);
		
		Anchor anchor_7 = new Anchor("Overawe:");
		anchor_7.setWordWrap(false);
		anchor_7.setTarget("_blank");
		anchor_7.setHref("http://us.battle.net/d3/en/class/monk/active/mantra-of-conviction#a");
		flexTable.setWidget(row, 4, anchor_7);
		
		overawe = new SimpleCheckBox();
		overawe.setTitle("Check this box if the monk is using the Overawe rune.");
		flexTable.setWidget(row, 5, overawe);
		
		row++;
		
		Label lblActiveUptime = new Label("% Active:");
		lblActiveUptime.setWordWrap(false);
		flexTable.setWidget(row, 2, lblActiveUptime);
		
		convictionActiveUptime = new DoubleSpinner();
		convictionActiveUptime.setTitle("Percent of time that Matra of Conviction's active bonus [only] applies");
		convictionActiveUptime.setVisibleLength(5);
		flexTable.setWidget(row, 3, convictionActiveUptime);
		
		row++;
		
		Anchor anchor_11 = new Anchor(SpecialItemType.OCULUS.getName() + ":");
		anchor_11.setHref(SpecialItemType.OCULUS.getUrl());
		anchor_11.setTarget("_blank");
		flexTable.setWidget(row, 0, anchor_11);
		
		oculus = new SimpleCheckBox();
		flexTable.setWidget(row, 1, oculus);

		Label label1 = new Label("% Damage:", false);
		flexTable.setWidget(row, 2, label1);
		
		oculusPercent = new NumberSpinner();
		oculusPercent.setMin(70);
		oculusPercent.setMax(85);
		oculusPercent.setVisibleLength(4);
		oculusPercent.setTitle("Damage Increased by Percent");
		flexTable.setWidget(row, 3, oculusPercent);

		row++;
		
		Label label2 = new Label("% Uptime:", false);
		flexTable.setWidget(row, 2, label2);
		
		oculusUptime = new NumberSpinner();
		oculusUptime.setMin(0);
		oculusUptime.setMax(100);
		oculusUptime.setVisibleLength(4);
		oculusUptime.setTitle("Percent of time you are receiving other Player's Oculus buff");
		flexTable.setWidget(row, 3, oculusUptime);
		
		row++;
		
		wolfUptime.setMax(100.0);
		bbvUptime.setMax(100.0);
		massConfusionUptime.setMax(100.0);
		piranhasUptime.setMax(100.0);
		falterUptime.setMax(100.0);
		cripplingWaveUptime.setMax(100.0);
		innerSanctuaryUptime.setMax(100.0);
		convictionPassiveUptime.setMax(100.0);
		convictionActiveUptime.setMax(100.0);
		valorActiveUptime.setMax(100.0);
		valorPassiveUptime.setMax(100.0);
		retributionUptime.setMax(100.0);
		stretchTimeUptime.setMax(100);
		timeWarpUptime.setMax(100);

		convictionPassiveUptime.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				
				if (!disableListeners) {
					disableListeners = true;
					
					double a = convictionPassiveUptime.getValue();
					double b = convictionActiveUptime.getValue();
					
					convictionActiveUptime.setValue(Math.min(100.0 - a, b));

					disableListeners = false;
				}
				
			}});

		convictionActiveUptime.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				
				if (!disableListeners) {
					disableListeners = true;
					
					double a = convictionPassiveUptime.getValue();
					double b = convictionActiveUptime.getValue();
					
					convictionPassiveUptime.setValue(Math.min(100.0 - b, a));

					disableListeners = false;
				}
				
			}});
		
		valorPassiveUptime.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				
				if (!disableListeners) {
					disableListeners = true;
					
					double a = valorPassiveUptime.getValue();
					double b = valorActiveUptime.getValue();
					
					valorActiveUptime.setValue(Math.min(100.0 - a, b));

					disableListeners = false;
				}
				
			}});

		valorActiveUptime.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				
				if (!disableListeners) {
					disableListeners = true;
					
					double a = valorPassiveUptime.getValue();
					double b = valorActiveUptime.getValue();
					
					valorPassiveUptime.setValue(Math.min(100.0 - b, a));

					disableListeners = false;
				}
				
			}});
		
		disableListeners = false;

	}

	public SimpleCheckBox getWolf() {
		return wolf;
	}

	public DoubleSpinner getWolfUptime() {
		return wolfUptime;
	}

	public Button getCalcWolfButton() {
		return calcWolfButton;
	}

	public SimpleCheckBox getBbv() {
		return bbv;
	}

	public DoubleSpinner getBbvUptime() {
		return bbvUptime;
	}

	public SimpleCheckBox getMassConfusion() {
		return massConfusion;
	}

	public DoubleSpinner getMassConfusionUptime() {
		return massConfusionUptime;
	}

	public SimpleCheckBox getInnerSanctuary() {
		return innerSanctuary;
	}

	public DoubleSpinner getInnerSanctuaryUptime() {
		return innerSanctuaryUptime;
	}

	public SimpleCheckBox getConviction() {
		return conviction;
	}

	public SimpleCheckBox getCripplingWave() {
		return cripplingWave;
	}

	public DoubleSpinner getCripplingWaveUptime() {
		return cripplingWaveUptime;
	}

	public DoubleSpinner getConvictionPassiveUptime() {
		return convictionPassiveUptime;
	}

	public SimpleCheckBox getPiranhas() {
		return piranhas;
	}

	public DoubleSpinner getPiranhasUptime() {
		return piranhasUptime;
	}

	public SimpleCheckBox getFalter() {
		return falter;
	}

	public DoubleSpinner getFalterUptime() {
		return falterUptime;
	}

	public SimpleCheckBox getToxin() {
		return toxin;
	}

	public SimpleCheckBox getOverawe() {
		return overawe;
	}

	public DoubleSpinner getConvictionActiveUptime() {
		return convictionActiveUptime;
	}

	public SimpleCheckBox getValor() {
		return valor;
	}

	public SimpleCheckBox getRetribution() {
		return retribution;
	}

	public DoubleSpinner getRetributionUptime() {
		return retributionUptime;
	}

	public SimpleCheckBox getSlamDance() {
		return slamDance;
	}

	public SimpleCheckBox getTimeWarp() {
		return timeWarp;
	}

	public SimpleCheckBox getStretchTime() {
		return stretchTime;
	}

	public NumberSpinner getTimeWarpUptime() {
		return timeWarpUptime;
	}

	public NumberSpinner getStretchTimeUptime() {
		return stretchTimeUptime;
	}

	public DoubleSpinner getValorActiveUptime() {
		return valorActiveUptime;
	}

	public DoubleSpinner getValorPassiveUptime() {
		return valorPassiveUptime;
	}
	public SimpleCheckBox getOculus() {
		return oculus;
	}
	public NumberSpinner getOculusPercent() {
		return oculusPercent;
	}
	public NumberSpinner getOculusUptime() {
		return oculusUptime;
	}
}
