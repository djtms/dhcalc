package com.dawg6.web.dhcalc.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
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
	private final DoubleSpinner valorUptime;
	private final SimpleCheckBox slamDance;

	public PlayerBuffPanel() {
		
		disableListeners = true;

		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Other Player Buffs");
		initWidget(cptnpnlNewPanel);

		FlexTable flexTable = new FlexTable();
		cptnpnlNewPanel.setContentWidget(flexTable);

		Anchor anchor = new Anchor("Companion/Wolf (from other DH):");
		anchor.setWordWrap(false);
		flexTable.setWidget(0, 0, anchor);
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/companion#c");

		wolf = new SimpleCheckBox();
		flexTable.setWidget(0, 1, wolf);

		Label lblNewLabel = new Label("% Uptime:");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(0, 2, lblNewLabel);

		wolfUptime = new DoubleSpinner();
		wolfUptime.box.setVisibleLength(5);
		wolfUptime.setValue(33.33);
		flexTable.setWidget(0, 3, wolfUptime);
		wolfUptime.setTitle("% of time that another player's Wolf will be active");

		calcWolfButton = new Button("Calculate");
		calcWolfButton.setTitle("Calculate Wolf uptime based on your effective Cooldown Reduction");
		flexTable.setWidget(0, 4, calcWolfButton);

		Anchor anchor_1 = new Anchor("Big Bad Voodoo:");
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/big-bad-voodoo");
		flexTable.setWidget(1, 0, anchor_1);

		bbv = new SimpleCheckBox();
		flexTable.setWidget(1, 1, bbv);

		Label label = new Label("% Uptime:");
		label.setWordWrap(false);
		flexTable.setWidget(1, 2, label);

		bbvUptime = new DoubleSpinner();
		bbvUptime.setValue(17.67);
		bbvUptime.box.setVisibleLength(5);
		flexTable.setWidget(1, 3, bbvUptime);
		
		Anchor anchor_10 = new Anchor("Slam Dance:");
		anchor_10.setWordWrap(false);
		anchor_10.setTarget("_blank");
		anchor_10.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/big-bad-voodoo#a+");
		flexTable.setWidget(1, 4, anchor_10);
		
		slamDance = new SimpleCheckBox();
		flexTable.setWidget(1, 5, slamDance);
		slamDance.setTitle("Check this box if the Witch Doctor is using the Slam Dance rune");

		Anchor anchor_2 = new Anchor("Mass Confusion/Paranoia:");
		anchor_2.setWordWrap(false);
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/mass-confusion#a+");
		flexTable.setWidget(2, 0, anchor_2);

		massConfusion = new SimpleCheckBox();
		flexTable.setWidget(2, 1, massConfusion);

		Label label_1 = new Label("% Uptime:");
		label_1.setWordWrap(false);
		flexTable.setWidget(2, 2, label_1);

		massConfusionUptime = new DoubleSpinner();
		massConfusionUptime.box.setVisibleLength(5);
		flexTable.setWidget(2, 3, massConfusionUptime);
		
		Anchor anchor_6 = new Anchor("Piranhas:");
		anchor_6.setWordWrap(false);
		anchor_6.setTarget("_blank");
		anchor_6.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/piranhas");
		flexTable.setWidget(3, 0, anchor_6);
		
		piranhas = new SimpleCheckBox();
		flexTable.setWidget(3, 1, piranhas);
		
		Label label_5 = new Label("% Uptime:");
		label_5.setWordWrap(false);
		flexTable.setWidget(3, 2, label_5);
		
		piranhasUptime = new DoubleSpinner();
		piranhasUptime.box.setVisibleLength(5);
		flexTable.setWidget(3, 3, piranhasUptime);
		
		Anchor anchor_8 = new Anchor("Laws of Valor:");
		anchor_8.setWordWrap(false);
		anchor_8.setTarget("_blank");
		anchor_8.setHref("http://us.battle.net/d3/en/class/crusader/active/laws-of-valor");
		flexTable.setWidget(4, 0, anchor_8);
		
		valor = new SimpleCheckBox();
		flexTable.setWidget(4, 1, valor);
		
		Label label_4 = new Label("% Uptime:");
		label_4.setWordWrap(false);
		flexTable.setWidget(4, 2, label_4);
		
		valorUptime = new DoubleSpinner();
		valorUptime.box.setVisibleLength(5);
		flexTable.setWidget(4, 3, valorUptime);
		
		Anchor anchor_3 = new Anchor("Inner Sanctuary/Forbidden Palace:");
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/class/monk/active/inner-sanctuary#e+");
		flexTable.setWidget(5, 0, anchor_3);
		
		innerSanctuary = new SimpleCheckBox();
		flexTable.setWidget(5, 1, innerSanctuary);
		
		Label label_2 = new Label("% Uptime:");
		label_2.setWordWrap(false);
		flexTable.setWidget(5, 2, label_2);
		
		innerSanctuaryUptime = new DoubleSpinner();
		innerSanctuaryUptime.box.setVisibleLength(5);
		flexTable.setWidget(5, 3, innerSanctuaryUptime);
		
		Anchor anchor_4 = new Anchor("Crippling Wave/Breaking Wave:");
		anchor_4.setWordWrap(false);
		anchor_4.setTarget("_blank");
		anchor_4.setHref("http://us.battle.net/d3/en/class/monk/active/crippling-wave#e+");
		flexTable.setWidget(6, 0, anchor_4);
		
		cripplingWave = new SimpleCheckBox();
		flexTable.setWidget(6, 1, cripplingWave);
		
		Label label_3 = new Label("% Uptime:");
		label_3.setWordWrap(false);
		flexTable.setWidget(6, 2, label_3);
		
		cripplingWaveUptime = new DoubleSpinner();
		cripplingWaveUptime.box.setVisibleLength(5);
		flexTable.setWidget(6, 3, cripplingWaveUptime);
		
		Anchor anchor_9 = new Anchor("Mantra of Retribution/Transgression:");
		anchor_9.setWordWrap(false);
		anchor_9.setTarget("_blank");
		anchor_9.setHref("http://us.battle.net/d3/en/class/monk/active/mantra-of-retribution#b+");
		flexTable.setWidget(7, 0, anchor_9);
		
		retribution = new SimpleCheckBox();
		flexTable.setWidget(7, 1, retribution);
		
		Label label_6 = new Label("% Uptime:");
		label_6.setWordWrap(false);
		flexTable.setWidget(7, 2, label_6);
		
		retributionUptime = new DoubleSpinner();
		retributionUptime.box.setVisibleLength(5);
		flexTable.setWidget(7, 3, retributionUptime);
		
		Anchor anchor_5 = new Anchor("Mantra of Conviction:");
		anchor_5.setWordWrap(false);
		anchor_5.setTarget("_blank");
		anchor_5.setHref("http://us.battle.net/d3/en/class/monk/active/mantra-of-conviction");
		flexTable.setWidget(8, 0, anchor_5);
		
		conviction = new SimpleCheckBox();
		flexTable.setWidget(8, 1, conviction);
		
		Label lblPassiveUptime = new Label("% Passive:");
		lblPassiveUptime.setWordWrap(false);
		flexTable.setWidget(8, 2, lblPassiveUptime);
		
		convictionPassiveUptime = new DoubleSpinner();
		convictionPassiveUptime.box.setVisibleLength(5);
		convictionPassiveUptime.setTitle("Percent of time that Mantra of Conviction's passive bonus [only] applies");
		flexTable.setWidget(8, 3, convictionPassiveUptime);
		
		Anchor anchor_7 = new Anchor("Overawe:");
		anchor_7.setWordWrap(false);
		anchor_7.setTarget("_blank");
		anchor_7.setHref("http://us.battle.net/d3/en/class/monk/active/mantra-of-conviction#a");
		flexTable.setWidget(8, 4, anchor_7);
		
		overawe = new SimpleCheckBox();
		overawe.setTitle("Check this box if the monk is using the Overawe rune.");
		flexTable.setWidget(8, 5, overawe);
		
		Label lblActiveUptime = new Label("% Active:");
		lblActiveUptime.setWordWrap(false);
		flexTable.setWidget(9, 2, lblActiveUptime);
		
		convictionActiveUptime = new DoubleSpinner();
		convictionActiveUptime.setTitle("Percent of time that Matra of Conviction's active bonus [only] applies");
		convictionActiveUptime.box.setVisibleLength(5);
		flexTable.setWidget(9, 3, convictionActiveUptime);
		
		wolfUptime.setMax(100.0);
		bbvUptime.setMax(100.0);
		massConfusionUptime.setMax(100.0);
		piranhasUptime.setMax(100.0);
		cripplingWaveUptime.setMax(100.0);
		innerSanctuaryUptime.setMax(100.0);
		convictionPassiveUptime.setMax(100.0);
		convictionActiveUptime.setMax(100.0);
		valorUptime.setMax(100.0);
		retributionUptime.setMax(100.0);

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

	public DoubleSpinner getValorUptime() {
		return valorUptime;
	}

	public SimpleCheckBox getSlamDance() {
		return slamDance;
	}
}
