package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.BreakPoint;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.FiringData;
import com.dawg6.web.sentry.shared.calculator.Util;
import com.dawg6.web.sentry.shared.calculator.WeaponType;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class DPSCalculator extends BasePanel {
	private final NumberSpinner minJewelDamage;
	private final NumberSpinner dexterity;
	private final DoubleSpinner critChance;
	private final NumberSpinner critDamage;
	private final NumberSpinner equipIAS;
	private final SimpleCheckBox archery;
	private final Label dps;
	private final Label aps;
	private final NumberSpinner maxJewelDamage;
	private final Label actualCC;
	private final Label actualCD;
	private final NumberSpinner tntPercent;
	private final Label petAps;
	private final Label breakpoint;
	private final Label sentryAps;
	private double sheetDps;
	private double sheetAps;
	private double totalCC;
	private double totalCD;
	private final Label nextBP;
	private final ListBox iasType;
	private BreakPoint bp;
	private double petApsValue;
	private double eIas;
	private double wIas;
	private double pIas;
	private double petIasValue;
	private final Label prevBP;
	private boolean disableListeners;
	private final SimpleCheckBox steadyAim;
	private final ParagonPanel paragonPanel;
	private final BuffPanel buffPanel;
	private final NumberSpinner gogokStacks;
	private final SimpleCheckBox tnt;
	private final SimpleCheckBox gogok;
	private final Label sentryAttacks;
	private final Label sentryDps;
	private double sentryDpsValue;
	private final SimpleCheckBox painEnhancer;
	private final NumberSpinner gogokLevel;
	private final NumberSpinner painEnhancerLevel;
	private final NumberSpinner painEnhancerStacks;
	private final SimpleCheckBox bbv;
	private final DoubleSpinner bbvUptime;
	private final SimpleCheckBox retribution;
	private final SimpleCheckBox valor;
	private final DoubleSpinner valorUptime;
	private final DoubleSpinner retributionUptime;
	private double buffIas;
	private double focusedMind;
	private double gogokIas;
	private double painEnhancerIas;
	private final Label dexterityLabel;
	private final NumberSpinner heroLevel;
	private final WeaponPanel mainHand;
	private final WeaponPanel offHand;

	public DPSCalculator() {

		ChangeHandler handler = new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners)
					calculate();
			}
			
		};
		
		ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		};
		
		FlexTable grid = new FlexTable();
		grid.setBorderWidth(0);
		grid.setCellPadding(5);
		initWidget(grid);
		
		mainHand = new WeaponPanel("Main Hand");
		grid.setWidget(0, 0, mainHand);
		mainHand.setWidth("100%");
		
		offHand = new WeaponPanel("Off Hand");
		grid.setWidget(1, 0, offHand);
		offHand.setWidth("100%");

		FlexTable flexTable_3 = new FlexTable();
		grid.setWidget(2, 0, flexTable_3);

		CaptionPanel cptnpnlNewPanel_2 = new CaptionPanel("Equipment");
		flexTable_3.setWidget(0, 0, cptnpnlNewPanel_2);

		FlexTable flexTable_2 = new FlexTable();
		flexTable_2.setCellPadding(2);
		cptnpnlNewPanel_2.setContentWidget(flexTable_2);

		Label lblJewelryDamage = new Label("Jewelry Damage:");
		flexTable_2.setWidget(0, 0, lblJewelryDamage);
		lblJewelryDamage.setWordWrap(false);

		minJewelDamage = new NumberSpinner();
		minJewelDamage.box.setTitle("Total of all jewelry damage");
		flexTable_2.setWidget(0, 1, minJewelDamage);
		minJewelDamage.setVisibleLength(6);
		minJewelDamage.addChangeHandler(handler);

		Label label_1 = new Label(" to ");
		flexTable_2.setWidget(0, 2, label_1);
		label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		maxJewelDamage = new NumberSpinner();
		maxJewelDamage.box.setTitle("Total of all jewelry damage");
		flexTable_2.setWidget(0, 3, maxJewelDamage);
		maxJewelDamage.setVisibleLength(6);
		maxJewelDamage.addChangeHandler(handler);

		Label lblNewLabel_2b = new Label("Equipment IAS (%):");
		flexTable_2.setWidget(1, 0, lblNewLabel_2b);
		lblNewLabel_2b.setWordWrap(false);

		equipIAS = new NumberSpinner();
		equipIAS.box
				.setTitle("Increased attack speed from equipment, except Weapon");
		flexTable_2.setWidget(1, 1, equipIAS);
		equipIAS.setVisibleLength(6);
		equipIAS.addChangeHandler(handler);

		Anchor lblPetAttackSpeed = new Anchor("Tasker and Theo (%):");
		lblPetAttackSpeed.setTarget("_blank");
		lblPetAttackSpeed
				.setHref("http://us.battle.net/d3/en/item/tasker-and-theo");
		lblPetAttackSpeed.setText("Tasker and Theo:");
		flexTable_2.setWidget(2, 0, lblPetAttackSpeed);
		lblPetAttackSpeed.setWordWrap(false);

		tnt = new SimpleCheckBox();
		flexTable_2.setWidget(2, 1, tnt);

		Label label_3 = new Label(" % ");
		label_3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_2.setWidget(2, 2, label_3);

		tntPercent = new NumberSpinner();
		tntPercent.box
				.setTitle("Increased Pet Attack speed from Tasker and Theo");
		flexTable_2.setWidget(2, 3, tntPercent);
		tntPercent.setVisibleLength(6);
		tntPercent.setText("0");
		tntPercent.addChangeHandler(handler);
		this.tntPercent.setMax(40);
		this.tntPercent.setMax(50);

		Label lblNewLabel_4 = new Label("Crit Chance (%):");
		flexTable_2.setWidget(3, 0, lblNewLabel_4);
		lblNewLabel_4.setWordWrap(false);

		critChance = new DoubleSpinner();
		critChance.box
				.setTitle("Increased Crit Chance from equipment and set bonuses");
		flexTable_2.setWidget(3, 1, critChance);
		critChance.setVisibleLength(6);
		critChance.addChangeHandler(handler);

		Label lblNewLabel_5 = new Label("Crit Hit Damage (%):");
		flexTable_2.setWidget(4, 0, lblNewLabel_5);
		lblNewLabel_5.setWordWrap(false);

		critDamage = new NumberSpinner();
		critDamage.box
				.setTitle("Increased Crit Hit Damage from equipment and set bonuses");
		flexTable_2.setWidget(4, 1, critDamage);
		critDamage.setVisibleLength(6);
		critDamage.addChangeHandler(handler);

		FlexTable flexTable_7 = new FlexTable();
		grid.setWidget(2, 1, flexTable_7);

		paragonPanel = new ParagonPanel();
		flexTable_7.setWidget(0, 0, paragonPanel);

		paragonPanel.getParagonIAS().addChangeHandler(handler);
		paragonPanel.getParagonDexterity().addChangeHandler(handler);
		paragonPanel.getParagonCC().addChangeHandler(handler);
		paragonPanel.getParagonCHD().addChangeHandler(handler);

		buffPanel = new BuffPanel();
		flexTable_7.setWidget(2, 0, buffPanel);
		flexTable_7.getCellFormatter().setVerticalAlignment(2, 0,
				HasVerticalAlignment.ALIGN_TOP);
		flexTable_7.getCellFormatter().setHorizontalAlignment(2, 0,
				HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_7.getCellFormatter().setVerticalAlignment(0, 0,
				HasVerticalAlignment.ALIGN_TOP);
		flexTable_7.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_LEFT);

		CaptionPanel cptnpnlNewPanel_5 = new CaptionPanel("Passives");
		flexTable_7.setWidget(3, 0, cptnpnlNewPanel_5);

		FlexTable flexTable_5 = new FlexTable();
		flexTable_5.setCellPadding(2);
		cptnpnlNewPanel_5.setContentWidget(flexTable_5);

		Anchor lblNewLabel_6 = new Anchor("Archery:");
		lblNewLabel_6.setTarget("_blank");
		flexTable_5.setWidget(0, 0, lblNewLabel_6);
		lblNewLabel_6
				.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/archery");
		lblNewLabel_6.setWordWrap(false);

		archery = new SimpleCheckBox();
		flexTable_5.setWidget(0, 1, archery);

		archery.addClickHandler(clickHandler);

		Anchor anchor = new Anchor("Steady Aim:");
		anchor.setText("Steady Aim:");
		anchor.setTarget("_blank");
		anchor.setWordWrap(false);
		anchor.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/steady-aim");
		flexTable_5.setWidget(0, 2, anchor);

		steadyAim = new SimpleCheckBox();
		flexTable_5.setWidget(0, 3, steadyAim);
		flexTable_7.getCellFormatter().setHorizontalAlignment(3, 0,
				HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_7.getCellFormatter().setVerticalAlignment(3, 0,
				HasVerticalAlignment.ALIGN_TOP);

		steadyAim.addClickHandler(clickHandler);
		buffPanel.getAnatomy().addClickHandler(clickHandler);
		buffPanel.getFocusedMind().addClickHandler(clickHandler);
		buffPanel.getHysteria().addClickHandler(clickHandler);

		CaptionPanel cptnpnlNewPanel_4 = new CaptionPanel("Character");
		flexTable_3.setWidget(1, 0, cptnpnlNewPanel_4);

		FlexTable flexTable_4 = new FlexTable();
		flexTable_4.setCellPadding(2);
		cptnpnlNewPanel_4.setContentWidget(flexTable_4);
		
		Label lblHeroLevel = new Label("Hero Level:");
		lblHeroLevel.setWordWrap(false);
		flexTable_4.setWidget(0, 0, lblHeroLevel);
		
		heroLevel = new NumberSpinner();
		heroLevel.box.setTitle("Hero's level");
		heroLevel.setVisibleLength(6);
		heroLevel.setMin(1);
		heroLevel.setMax(70);
		flexTable_4.setWidget(0, 1, heroLevel);
		
		heroLevel.addChangeHandler(handler);

		Label lblNewLabel_3 = new Label("Dexterity from Items:");
		flexTable_4.setWidget(1, 0, lblNewLabel_3);
		lblNewLabel_3.setWordWrap(false);

		dexterity = new NumberSpinner();
		dexterity.box
				.setTitle("Total of all dexterity from items and set bonuses only");
		flexTable_4.setWidget(1, 1, dexterity);
		dexterity.setVisibleLength(6);

		CaptionPanel captionPanel = new CaptionPanel("Other");
		flexTable_3.setWidget(3, 0, captionPanel);

		FlexTable flexTable_8 = new FlexTable();
		flexTable_8.setCellPadding(2);
		captionPanel.setContentWidget(flexTable_8);

		Anchor anchor_1 = new Anchor("Gogok of Swiftness");
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/item/gogok-of-swiftness");
		flexTable_8.setWidget(0, 0, anchor_1);

		gogok = new SimpleCheckBox();
		flexTable_8.setWidget(0, 1, gogok);

		Label label_5 = new Label("Level:");
		label_5.setWordWrap(false);
		flexTable_8.setWidget(0, 2, label_5);

		gogokLevel = new NumberSpinner();
		gogokLevel.setVisibleLength(3);
		flexTable_8.setWidget(0, 3, gogokLevel);

		Label lblStacks = new Label("# stacks:");
		lblStacks.setWordWrap(false);
		flexTable_8.setWidget(0, 4, lblStacks);

		gogokStacks = new NumberSpinner();
		gogokStacks.box
				.setTitle("Average # of stacks of Gogok of Swiftness");
		gogokStacks.setVisibleLength(3);
		flexTable_8.setWidget(0, 5, gogokStacks);

		Anchor anchor_2 = new Anchor("Pain Enhancer");
		anchor_2.setWordWrap(false);
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/item/pain-enhancer");
		flexTable_8.setWidget(1, 0, anchor_2);

		painEnhancer = new SimpleCheckBox();
		flexTable_8.setWidget(1, 1, painEnhancer);

		Label label_6 = new Label("Level:");
		label_6.setWordWrap(false);
		flexTable_8.setWidget(1, 2, label_6);

		painEnhancerLevel = new NumberSpinner();
		painEnhancerLevel.setVisibleLength(3);
		flexTable_8.setWidget(1, 3, painEnhancerLevel);

		Label label_7 = new Label("# bleeding:");
		label_7.setWordWrap(false);
		flexTable_8.setWidget(1, 4, label_7);

		painEnhancerStacks = new NumberSpinner();
		painEnhancerStacks.box
				.setTitle("Average # of bleeding enemies");
		painEnhancerStacks.setVisibleLength(3);
		painEnhancerStacks.setTitle("# of bleeding enemies within 20 yards");
		flexTable_8.setWidget(1, 5, painEnhancerStacks);

		Anchor anchor_3 = new Anchor("Big Bad Voodoo:");
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/class/witch-doctor/active/big-bad-voodoo");
		flexTable_8.setWidget(2, 0, anchor_3);

		bbv = new SimpleCheckBox();
		flexTable_8.setWidget(2, 1, bbv);

		Label label_8 = new Label("% Uptime:");
		label_8.setWordWrap(false);
		flexTable_8.setWidget(2, 2, label_8);

		bbvUptime = new DoubleSpinner();
		bbvUptime.setVisibleLength(3);
		flexTable_8.setWidget(2, 3, bbvUptime);

		Label lblNewLabel_1 = new Label(
				"Note: Attack speed buffs from other players are only included in breakpoint calculations when uptime is \"100%\"");
		lblNewLabel_1.setStyleName("boldText");
		flexTable_8.setWidget(2, 4, lblNewLabel_1);
		lblNewLabel_1.setWidth("168px");
		flexTable_8.getCellFormatter().setWidth(2, 4, "");
		flexTable_8.getFlexCellFormatter().setColSpan(2, 4, 2);
		flexTable_8.getCellFormatter().setVerticalAlignment(2, 4,
				HasVerticalAlignment.ALIGN_TOP);
		flexTable_8.getCellFormatter().setHorizontalAlignment(2, 4,
				HasHorizontalAlignment.ALIGN_LEFT);

		Anchor anchor_4 = new Anchor("Mantra of Retribution/Transgression:");
		anchor_4.setWordWrap(false);
		anchor_4.setTarget("_blank");
		anchor_4.setHref("http://us.battle.net/d3/en/class/monk/active/mantra-of-retribution#b+");
		flexTable_8.setWidget(3, 0, anchor_4);

		retribution = new SimpleCheckBox();
		flexTable_8.setWidget(3, 1, retribution);

		Label label_9 = new Label("% Uptime:");
		label_9.setWordWrap(false);
		flexTable_8.setWidget(3, 2, label_9);

		retributionUptime = new DoubleSpinner();
		retributionUptime.setVisibleLength(3);
		flexTable_8.setWidget(3, 3, retributionUptime);

		Anchor anchor_5 = new Anchor("Laws of Valor:");
		anchor_5.setWordWrap(false);
		anchor_5.setTarget("_blank");
		anchor_5.setHref("http://us.battle.net/d3/en/class/crusader/active/laws-of-valor");
		flexTable_8.setWidget(4, 0, anchor_5);

		valor = new SimpleCheckBox();
		flexTable_8.setWidget(4, 1, valor);

		Label label_10 = new Label("% Uptime:");
		label_10.setWordWrap(false);
		flexTable_8.setWidget(4, 2, label_10);

		valorUptime = new DoubleSpinner();
		valorUptime.setVisibleLength(3);
		flexTable_8.setWidget(4, 3, valorUptime);
		flexTable_8.getFlexCellFormatter().setRowSpan(2, 4, 3);
		flexTable_3.getCellFormatter().setHorizontalAlignment(3, 0,
				HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(3, 0,
				HasVerticalAlignment.ALIGN_TOP);

		gogok.addClickHandler(clickHandler);
		bbv.addClickHandler(clickHandler);
		valor.addClickHandler(clickHandler);
		retribution.addClickHandler(clickHandler);
		painEnhancer.addClickHandler(clickHandler);

		gogokStacks.addChangeHandler(handler);
		bbvUptime.addChangeHandler(handler);
		valorUptime.addChangeHandler(handler);
		retributionUptime.addChangeHandler(handler);
		painEnhancerStacks.addChangeHandler(handler);
		painEnhancerLevel.addChangeHandler(handler);
		dexterity.addChangeHandler(handler);

		CaptionPanel cptnpnlNewPanel_6 = new CaptionPanel("Calculator");
		grid.setWidget(3, 0, cptnpnlNewPanel_6);

		FlexTable flexTable_6 = new FlexTable();
		flexTable_6.setCellPadding(5);
		cptnpnlNewPanel_6.setContentWidget(flexTable_6);

		Label lblNewLabel_7 = new Label("Sheet DPS:");
		flexTable_6.setWidget(0, 0, lblNewLabel_7);
		lblNewLabel_7.setWordWrap(false);
		lblNewLabel_7.setStyleName("boldText");

		dps = new Label("0");
		dps.setTitle("This is the shet dps shown on charcter screen as \"Damage\"");
		flexTable_6.setWidget(0, 1, dps);
		dps.setStyleName("boldText");

		Label lblBreakPoint = new Label("Sentry Break Point:");
		flexTable_6.setWidget(0, 2, lblBreakPoint);
		lblBreakPoint.setWordWrap(false);
		lblBreakPoint.setStyleName("boldText");

		breakpoint = new Label("0");
		breakpoint.setTitle("Sentry Break Point #");
		flexTable_6.setWidget(0, 3, breakpoint);
		breakpoint.setStyleName("boldText");

		CaptionPanel cptnpnlNewPanel = new CaptionPanel(
				"Next/Previous Breakpoint");
		flexTable_6.setWidget(0, 4, cptnpnlNewPanel);
		cptnpnlNewPanel.setStyleName("boldText");

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		cptnpnlNewPanel.setContentWidget(flexTable);

		Label lblWithRepectTo = new Label("With Repect To:");
		lblWithRepectTo.setWordWrap(false);
		flexTable.setWidget(0, 0, lblWithRepectTo);

		iasType = new ListBox();
		flexTable.setWidget(0, 1, iasType);
		iasType.setSelectedIndex(0);

		Label lblApsForNext = new Label("IAS for next BP:");
		flexTable.setWidget(1, 0, lblApsForNext);
		lblApsForNext.setWordWrap(false);
		lblApsForNext.setStyleName("boldText");

		nextBP = new Label("0");
		nextBP.setTitle("IAS needed for next BP");
		flexTable.setWidget(1, 1, nextBP);
		nextBP.setWordWrap(false);
		nextBP.setStyleName("boldText");

		Label lblextraIas = new Label("\"Extra\" IAS:");
		flexTable.setWidget(2, 0, lblextraIas);
		lblextraIas.setWordWrap(false);
		lblextraIas.setStyleName("boldText");

		prevBP = new Label("0");
		prevBP.setTitle("Extra IAS that can be removed without lowering BP");
		flexTable.setWidget(2, 1, prevBP);
		prevBP.setWordWrap(false);
		prevBP.setStyleName("boldText");

		iasType.addChangeHandler(handler);

		Label lblNewLabel_7b = new Label("Player APS:");
		flexTable_6.setWidget(1, 0, lblNewLabel_7b);
		lblNewLabel_7b.setWordWrap(false);
		lblNewLabel_7b.setStyleName("boldText");

		aps = new Label("0.0");
		aps.setTitle("This is the APS for the character, as shown on the details screen under offense");
		flexTable_6.setWidget(1, 1, aps);
		aps.setStyleName("boldText");

		Label lblPetAps = new Label("Pet APS:");
		flexTable_6.setWidget(1, 2, lblPetAps);
		lblPetAps.setWordWrap(false);
		lblPetAps.setStyleName("boldText");

		petAps = new Label("0.0");
		petAps.setTitle("Calculated Pet APS (determines which Sentry BP)");
		flexTable_6.setWidget(1, 3, petAps);
		petAps.setStyleName("boldText");

		Label lblTotal = new Label("Total Crit Chance:");
		flexTable_6.setWidget(2, 0, lblTotal);
		lblTotal.setStyleName("boldText");

		actualCC = new Label("0.0%");
		actualCC.setTitle("Total CC as shown on character details screen under offense");
		flexTable_6.setWidget(2, 1, actualCC);
		actualCC.setStyleName("boldText");

		Label lblAttacksPer = new Label("Attacks/" + FiringData.DURATION
				+ "sec:");
		lblAttacksPer.setWordWrap(false);
		lblAttacksPer.setStyleName("boldText");
		flexTable_6.setWidget(2, 2, lblAttacksPer);

		sentryAttacks = new Label("0");
		sentryAttacks.setTitle("# of Sentry Attacks over "
				+ FiringData.DURATION + " seconds.");
		sentryAttacks.setStyleName("boldText");
		flexTable_6.setWidget(2, 3, sentryAttacks);

		Label lblTotalCritDamage = new Label("Total Crit Hit Damage:");
		flexTable_6.setWidget(3, 0, lblTotalCritDamage);
		lblTotalCritDamage.setStyleName("boldText");

		actualCD = new Label("0.0%");
		actualCD.setTitle("Total CHD as shown on character details screen under offense");
		flexTable_6.setWidget(3, 1, actualCD);
		actualCD.setStyleName("boldText");

		Label lblBpAps = new Label("Sentry APS:");
		flexTable_6.setWidget(3, 2, lblBpAps);
		lblBpAps.setWordWrap(false);
		lblBpAps.setStyleName("boldText");

		sentryAps = new Label("0");
		sentryAps.setTitle("Actual Sentry APS (based on BP)");
		flexTable_6.setWidget(3, 3, sentryAps);
		sentryAps.setStyleName("boldText");
		
		Label lblTotalDexterity = new Label("Total Dexterity:");
		lblTotalDexterity.setStyleName("boldText");
		flexTable_6.setWidget(4, 0, lblTotalDexterity);
		
		dexterityLabel = new Label("0");
		dexterityLabel.setTitle("This is the total of all Dexterity from items, levels and paragon points");
		dexterityLabel.setStyleName("boldText");
		flexTable_6.setWidget(4, 1, dexterityLabel);

		Label lblSentryDps = new Label("Sentry Base DPS:");
		lblSentryDps.setWordWrap(false);
		lblSentryDps.setStyleName("boldText");
		flexTable_6.setWidget(4, 2, lblSentryDps);

		sentryDps = new Label("0");
		sentryDps.setTitle("Base Sentry DPS (includes Crit, Dex)");
		sentryDps.setStyleName("boldText");
		flexTable_6.setWidget(4, 3, sentryDps);
		flexTable_6.getFlexCellFormatter().setRowSpan(0, 4, 5);
		flexTable_6.getCellFormatter().setVerticalAlignment(0, 4,
				HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(0, 4,
				HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setHorizontalAlignment(2, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(2, 1,
				HasVerticalAlignment.ALIGN_TOP);
		grid.getCellFormatter().setHorizontalAlignment(2, 0,
				HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(2, 0,
				HasVerticalAlignment.ALIGN_TOP);

		for (IasType t : IasType.values()) {
			iasType.addItem(t.getDescription(), t.name());
		}
		grid.getFlexCellFormatter().setColSpan(3, 0, 2);
		grid.getFlexCellFormatter().setColSpan(0, 0, 2);
		grid.getFlexCellFormatter().setColSpan(1, 0, 2);
		this.gogokStacks.setMax(15);
		this.bbvUptime.setMax(100.0);
		this.valorUptime.setMax(100.0);
		this.retributionUptime.setMax(100.0);

		this.tnt.addClickHandler(clickHandler);
		this.gogok.addClickHandler(clickHandler);
		
		mainHand.getWeaponType().addChangeHandler(handler);
		mainHand.getBaseMin().addChangeHandler(handler);
		mainHand.getBaseMax().addChangeHandler(handler);
		mainHand.getAddMin().addChangeHandler(handler);
		mainHand.getAddMax().addChangeHandler(handler);
		mainHand.getWeaponIAS().addChangeHandler(handler);
		mainHand.getWeaponDamage().addChangeHandler(handler);

		offHand.getWeaponType().addChangeHandler(handler);
		offHand.getBaseMin().addChangeHandler(handler);
		offHand.getBaseMax().addChangeHandler(handler);
		offHand.getAddMin().addChangeHandler(handler);
		offHand.getAddMax().addChangeHandler(handler);
		offHand.getWeaponIAS().addChangeHandler(handler);
		offHand.getWeaponDamage().addChangeHandler(handler);
	}

	protected void iasTypeChanged() {
		int index = iasType.getSelectedIndex();

		if (index < 0)
			index = 0;

		String value = iasType.getValue(index);
		IasType type = IasType.valueOf(value);

		updateIasType(type);
	}

	private void updateIasType(IasType type) {

		BreakPoint next = bp.next();
		BreakPoint prev = bp.prev();

		if (next != null) {
			double nextBp = 0.0;
			double without = petApsValue;

			switch (type) {

			case Weapon:
				without = petApsValue / (1.0 + this.wIas);
				nextBp = (next.getAps() / without) - 1.0;
				nextBp -= this.wIas;
				break;
			case Equipment:
				without = petApsValue / (1.0 + this.eIas + this.pIas + focusedMind + gogokIas + painEnhancerIas + buffIas);
				nextBp = (next.getAps() / without) - 1.0;
				nextBp -= (this.eIas + this.pIas + focusedMind + gogokIas + painEnhancerIas + buffIas);
				break;
			case Pet:
				without = petApsValue / (1.0 + this.petIasValue);
				nextBp = (next.getAps() / without) - 1.0;
				nextBp -= this.petIasValue;
				break;
			default:
			}

			this.nextBP.setText(Math.round(nextBp * 10000.0) / 100.0 + "%");
		} else {
			this.nextBP.setText("N/A");
		}

		if (prev != null) {
			double without = petApsValue;
			double prevBp = 0.0;

			switch (type) {

			case Weapon:
				without = petApsValue / (1.0 + this.wIas);

				if (without < bp.getAps()) {
					prevBp = (bp.getAps() / without) - 1.0;
				}
				prevBp = this.wIas - prevBp;

				break;
			case Equipment:
				without = petApsValue / (1.0 + this.eIas + this.pIas);

				if (without < bp.getAps()) {
					prevBp = (bp.getAps() / without) - 1.0;
				}
				prevBp = (this.eIas + this.pIas) - prevBp;

				break;
			case Pet:
				without = petApsValue / (1.0 + this.petIasValue);

				if (without < bp.getAps()) {
					prevBp = (bp.getAps() / without) - 1.0;
				}
				prevBp = this.petIasValue - prevBp;

				break;
			default:
			}

			this.prevBP.setText(Math.round(prevBp * 10000.0) / 100.0 + "%");
		} else {
			this.prevBP.setText("N/A");
		}
	}

	private enum IasType {
		Equipment("Equipment/Paragon"), Weapon("Weapon"), Pet("Tasker and Theo");

		private String description;

		public String getDescription() {
			return description;
		}

		private IasType(String description) {
			this.description = description;
		}
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		loadForm();

	}

	@Override
	protected void loadForm() {
		this.disableListeners = true;

		super.loadForm();

		this.disableListeners = false;

		calculate();
	}

	@Override
	public Field[] getFields() {
		return new Field[] {
				new Field(this.mainHand.getWeaponType(), "calc.WeaponType",
						WeaponType.HandCrossbow.name()),
				new Field(this.mainHand.getBaseMin(), "calc.MinDamage", "100"),
				new Field(this.mainHand.getBaseMax(), "calc.MaxDamage", "200"),
				new Field(this.mainHand.getAddMin(), "calc.AddMinDamage", "0"),
				new Field(this.mainHand.getAddMax(), "calc.AddMaxDamage", "0"),
				new Field(this.mainHand.getWeaponIAS(), "calc.WeaponIAS", "0"),
				new Field(this.mainHand.getWeaponDamage(), "calc.WeaponDamage", "0"),
				new Field(this.offHand.getWeaponType(), "calc.offHand.WeaponType",
						""),
				new Field(this.offHand.getBaseMin(), "calc.offHand.MinDamage", "0"),
				new Field(this.offHand.getBaseMax(), "calc.offHand.MaxDamage", "0"),
				new Field(this.offHand.getAddMin(), "calc.offHand.AddMinDamage", "0"),
				new Field(this.offHand.getAddMax(), "calc.offHand.AddMaxDamage", "0"),
				new Field(this.offHand.getWeaponIAS(), "calc.offHand.WeaponIAS", "0"),
				new Field(this.offHand.getWeaponDamage(), "calc.offHand.WeaponDamage", "0"),
				new Field(this.gogokLevel, "calc.GogokLevel", "0"),
				new Field(this.painEnhancerLevel, "calc.PainEnhancerLevel", "0"),
				new Field(this.gogokStacks, "calc.GogokStacks", "0"),
				new Field(this.painEnhancerStacks, "calc.PainEnhancerStacks",
						"0"),
				new Field(this.minJewelDamage, "calc.MinJewelDamage", "0"),
				new Field(this.maxJewelDamage, "calc.MaxJewelDamage", "0"),
				new Field(this.equipIAS, "calc.EquipIAS", "0"),
				new Field(this.paragonPanel.getParagonIAS(), "calc.ParagonIAS",
						"0"),
				new Field(this.paragonPanel.getParagonDexterity(), "calc.ParagonDEX",
						"0"),
				new Field(this.paragonPanel.getParagonCDR(), "calc.ParagonCDR",
						"0"),
				new Field(this.paragonPanel.getParagonCC(), "calc.ParagonCC",
						"0"),
				new Field(this.paragonPanel.getParagonCHD(), "calc.ParagonCHD",
						"0"),
				new Field(this.paragonPanel.getParagonHatred(), "calc.ParagonHatred",
						"0"),
				new Field(this.paragonPanel.getParagonRCR(), "calc.ParagonRCR",
						"0"),
				new Field(this.paragonPanel.getParagonAD(), "calc.ParagonAD",
						"0"),
				new Field(this.tntPercent, "calc.PetIAS", "0"),
				new Field(this.dexterity, "calc.EquipmentDexterity", "0"),
				new Field(this.heroLevel, "calc.HeroLevel", "70"),
				new Field(this.critChance, "calc.CritChance", "0"),
				new Field(this.critDamage, "calc.CritDamage", "0"),
				new Field(this.archery, "calc.Archery", "0"),
				new Field(this.steadyAim, "calc.SteadyAim", "0"),

				new Field(this.buffPanel.getFocusedMind(), "calc.FocusedMind",
						Boolean.FALSE.toString()),
				new Field(this.buffPanel.getAnatomy(), "calc.Anatomy", Boolean.FALSE.toString()),
				new Field(this.buffPanel.getHysteria(), "calc.Hysteria",
						Boolean.FALSE.toString()), new Field(this.gogok, "calc.Gogok", Boolean.FALSE.toString()),
				new Field(this.painEnhancer, "calc.PainEnhancer", Boolean.FALSE.toString()),
				new Field(this.tnt, "calc.TnT", Boolean.FALSE.toString()), 
		
				new Field(this.bbv, "calc.BBV", Boolean.FALSE.toString()),
				new Field(this.valor, "calc.Valor", Boolean.FALSE.toString()),
				new Field(this.retribution, "calc.Retribution", Boolean.FALSE.toString()),
				new Field(this.bbvUptime, "calc.BBVUptime", "0"),
				new Field(this.valorUptime, "calc.ValorUptime", "0"),
				new Field(this.retributionUptime, "calc.RetributionUptime", "0"),
		};
	}

	public void calculate() {

		WeaponType type = getMainHandWeaponType();
		WeaponType offHand_type = getOffHandWeaponType();
		
		double min = this.mainHand.getWeaponMin() + getValue(this.minJewelDamage);
		double max = this.mainHand.getWeaponMax() + getValue(this.maxJewelDamage);
		double offHand_min = this.offHand.getWeaponMin() + getValue(this.minJewelDamage);
		double offHand_max = this.offHand.getWeaponMax() + getValue(this.maxJewelDamage);
		double dex = getValue(this.dexterity) + (paragonPanel.getParagonDexterity().getValue() * 5) + 217;
		double pCC = (getValue(this.paragonPanel.getParagonCC()) * 0.1) / 100.0;
		double pCD = (getValue(this.paragonPanel.getParagonCHD()) * 1.0) / 100.0;
		double aCC = 0.0;
		double aDam = 0.0;
		double aCD = 0.0;
		buffIas = 0.0;
		
		if (this.bbv.getValue() && (Math.round(this.bbvUptime.getValue()) == 100)) {
			buffIas += 0.20;
		}
		
		if (this.retribution.getValue() && (Math.round(this.retributionUptime.getValue()) == 100)) {
			buffIas += 0.10;
		}
		
		if (this.valor.getValue() && (Math.round(this.valorUptime.getValue()) == 100)) {
			buffIas += 0.08;
		}

		if (this.archery.getValue()) {
			if (type == WeaponType.HandCrossbow) {
				aCC += 0.05;
			} else if (type == WeaponType.Bow) {
				aDam += 0.08;
			} else if (type == WeaponType.Crossbow) {
				aCD += 0.5;
			}
		}

		if (this.steadyAim.getValue()) {
			aDam += .2;
		}

		double anatomy = buffPanel.getAnatomy().getValue() ? 0.018 : 0.0;

		double critChance = getValue(this.critChance) / 100.0 + pCC + aCC
				+ anatomy;
		double critDamage = getValue(this.critDamage) / 100.0 + pCD + aCD;

		this.eIas = (getValue(this.equipIAS)) / 100.0;
		this.wIas = (getValue(this.mainHand.getWeaponIAS())) / 100.0;
		double offHand_wIas = (getValue(this.offHand.getWeaponIAS())) / 100.0;
		this.pIas = getValue(this.paragonPanel.getParagonIAS()) * 0.002;
		focusedMind = (buffPanel.getFocusedMind().getValue() ? 0.03
				: 0.0);
		gogokIas = this.gogok.getValue() ? (this.gogokStacks.getValue() / 100.0)
				: 0.0;
		painEnhancerIas = (this.painEnhancer.getValue() && this.painEnhancerLevel
				.getValue() >= 25) ? (this.painEnhancerStacks.getValue() * 0.03)
				: 0.0;

		double dwIas = (offHand_type != null) ? 0.15 : 0.0;

		double aps = type.getAps()
				* (1.0 + wIas)
				* (1.0 + eIas + pIas + focusedMind + gogokIas + painEnhancerIas + buffIas + dwIas);

		double offHand_aps = (offHand_type == null) ? 0.0 : (offHand_type.getAps()
				* (1.0 + offHand_wIas)
				* (1.0 + eIas + pIas + focusedMind + gogokIas + painEnhancerIas + buffIas + dwIas));

		double averageDamage = ((min + max) / 2.0);

		double offHand_averageDamage = ((offHand_min + offHand_max) / 2.0);

		double mainHand_dps = averageDamage * aps * (1.0 + critChance * critDamage)
				* (1.0 + (dex / 100.0)) * (1.0 + aDam);

		double offHand_dps = (offHand_type == null) ? 0.0 : (offHand_averageDamage * offHand_aps * (1.0 + critChance * critDamage)
				* (1.0 + (dex / 100.0)) * (1.0 + aDam));

		
		double dw_aps = (aps + offHand_aps) / 2.0;
		double dw_averageDamage = (averageDamage + offHand_averageDamage) / 2.0;
		double totalDps = (offHand_type == null) ? (averageDamage * aps * (1.0 + critChance * critDamage)
				* (1.0 + (dex / 100.0)) * (1.0 + aDam))
				: (dw_averageDamage * dw_aps * (1.0 + critChance * critDamage)
				* (1.0 + (dex / 100.0)) * (1.0 + aDam))
				;
		
		this.sheetDps = Math.round(totalDps * 10.0) / 10.0;
		this.dps.setText(Util.format(sheetDps));
		this.sheetAps = (offHand_type == null) ? aps : dw_aps;
		this.aps.setText(Util.format((Math.round(sheetAps * 1000.0) / 1000.0)));

		this.totalCC = critChance;
		this.totalCD = critDamage;
		this.actualCC.setText(Util.format(critChance * 100.0) + "%");
		this.actualCD.setText(Util.format(critDamage * 100.0) + "%");


		this.petIasValue = tnt.getValue() ? (tntPercent.getValue() / 100.0)
				: 0.0;
		this.petApsValue = aps * (1.0 + petIasValue);
		this.petAps.setText(Util.format(petApsValue));
		this.bp = BreakPoint.get(petApsValue);
		this.breakpoint.setText(String.valueOf(bp.getBp()));

		double sentryAps = (double) bp.getQty() / (double) FiringData.DURATION;
		this.sentryAps.setText(Util.format(sentryAps));
		this.sentryAttacks.setText(String.valueOf(bp.getQty()));

		sentryDpsValue = averageDamage * sentryAps
				* (1.0 + critChance * critDamage) * (1.0 + (dex / 100.0))
				* (1.0 + aDam);
		this.sentryDps.setText(Util.format(Math.round(sentryDpsValue)));

		this.dexterityLabel.setText(String.valueOf(this.getTotalDexterity()));

		this.iasTypeChanged();
	}

	public double getSentryDps() {
		return this.sentryDpsValue;
	}

	public WeaponType getMainHandWeaponType() {
		return mainHand.getWeaponTypeEnum();
	}

	public void importHero(String server, String profile, int tag, int id,
			CharacterData data) {

		this.mainHand.setWeaponTypeEnum(data.getWeaponType());
		this.offHand.setWeaponTypeEnum(data.getOffHand_weaponType());

		this.disableListeners = true;

		this.mainHand.getBaseMin().setValue(data.getBaseMin());
		this.mainHand.getBaseMax().setValue(data.getBaseMax());
		this.mainHand.getAddMin().setValue(data.getAddMin());
		this.mainHand.getAddMax().setValue(data.getAddMax());
		this.mainHand.getWeaponIAS().setValue((int)(Math.round(data.getWeaponIas() * 100.0)));
		this.mainHand.getWeaponDamage().setValue((int) Math.round(data.getWeaponDamagePercent() * 100.0));
		this.offHand.getBaseMin().setValue(data.getOffHand_baseMin());
		this.offHand.getBaseMax().setValue(data.getOffHand_baseMax());
		this.offHand.getAddMin().setValue(data.getOffHand_addMin());
		this.offHand.getAddMax().setValue(data.getOffHand_addMax());
		this.offHand.getWeaponIAS().setValue((int)(Math.round(data.getOffHand_weaponIas() * 100.0)));
		this.offHand.getWeaponDamage().setValue((int) Math.round(data.getOffHand_weaponDamagePercent() * 100.0));

		this.critChance.setValue(Math.round(data.getEquipCritChance() * 10000.0) / 100.0);
		this.critDamage.setValue((int) Math.round(data.getEquipCritDamage()
				* 100.0));
		this.equipIAS.setValue((int)(Math.round(data.getEquipIas() * 100.0)));
		this.dexterity.setValue(data.getEquipmentDexterity());
		this.tnt.setValue(data.isTnt());
		this.tntPercent.setValue((int)(Math.round(data.getTntPercent() * 100.0)));
		this.archery.setValue(data.isArchery());
		this.steadyAim.setValue(data.isSteadyAim());
		this.minJewelDamage.setValue((int) Math.round(data.getJewelMin()));
		this.maxJewelDamage.setValue((int) Math.round(data.getJewelMax()));

		this.disableListeners = false;

		calculate();

		saveForm();
	}

	@Override
	protected void setFieldValue(ListBox field, String value) {
		if (field == mainHand.getWeaponType()) {
			mainHand.setWeaponTypeString(value);
		} else if (field == offHand.getWeaponType()) {
			offHand.setWeaponTypeString(value);
		}
	}

	private void setWeaponType(WeaponType type) {
		mainHand.setWeaponTypeEnum(type);
	}

	public void setParagonPoints(int ias, int dex, int cdr, int cc, int cd, int hatred, int rcr, int ad) {

		this.disableListeners = true;

		this.paragonPanel.getParagonIAS().setValue(ias);
		this.paragonPanel.getParagonDexterity().setValue(dex);
		this.paragonPanel.getParagonCDR().setValue(cdr);
		this.paragonPanel.getParagonCC().setValue(cc);
		this.paragonPanel.getParagonCHD().setValue(cd);
		this.paragonPanel.getParagonHatred().setValue(hatred);
		this.paragonPanel.getParagonRCR().setValue(rcr);
		this.paragonPanel.getParagonAD().setValue(ad);

		this.disableListeners = false;

		this.calculate();
	}

	public double getSheetAps() {
		return sheetAps;
	}

	public double getSheetDps() {
		return sheetDps;
	}

	public double getCritChance() {
		return totalCC;
	}

	public double getCritDamage() {
		return totalCD;
	}

	public Boolean isArchery() {
		return archery.getValue();
	}

	public Integer getTntPercent() {
		return getValue(this.tntPercent);
	}

	public int getParagonIas() {
		return this.getValue(this.paragonPanel.getParagonIAS());
	}

	public int getParagonCC() {
		return this.getValue(this.paragonPanel.getParagonCC());
	}

	public int getParagonCHD() {
		return this.getValue(this.paragonPanel.getParagonCHD());
	}

	public Integer getParagonHatred() {
		return this.getValue(this.paragonPanel.getParagonHatred());
	}

	public Integer getParagonRCR() {
		return this.getValue(this.paragonPanel.getParagonRCR());
	}

	public Integer getParagonAD() {
		return this.getValue(this.paragonPanel.getParagonAD());
	}

	public double getMainHandAverageWeaponDamage() {
		return mainHand.getAverageWeaponDamage();
	}

	public void setArchery(boolean archery) {
		this.archery.setValue(archery);
		this.calculate();
	}

	public int getTotalDexterity() {
		return getValue(this.dexterity) + (paragonPanel.getParagonDexterity().getValue() * 5) + 7 + (getHeroLevel() * 3);
	}

	public double getDamageBonus() {
		if (this.archery.getValue()) {
			WeaponType type = getMainHandWeaponType();

			if (type == WeaponType.Bow)
				return 0.08;
		}

		return 0.0;
	}

	public boolean getArchery() {
		return this.archery.getValue();
	}

	public boolean isDisableListeners() {
		return disableListeners;
	}

	public void setDisableListeners(boolean disableListeners) {
		this.disableListeners = disableListeners;
	}

	public boolean getSteadyAim() {
		return this.steadyAim.getValue();
	}

	public void setSteadyAim(boolean steadyAim) {
		this.steadyAim.setValue(steadyAim);
		this.calculate();
	}

	public Integer getParagonCDR() {
		return paragonPanel.getParagonCDR().getValue();
	}

	public boolean getFocusedMind() {
		return buffPanel.getFocusedMind().getValue();
	}

	public void setFocusedMind(boolean value) {
		buffPanel.getFocusedMind().setValue(value);
		this.calculate();
	}

	public boolean getAnatomy() {
		return buffPanel.getAnatomy().getValue();
	}

	public void setAnatomy(boolean value) {
		buffPanel.getAnatomy().setValue(value);
		this.calculate();
	}

	public boolean getHysteria() {
		return buffPanel.getHysteria().getValue();
	}

	public void setHysteria(boolean value) {
		buffPanel.getHysteria().setValue(value);
		this.calculate();
	}

	public void setTntPercent(int pct) {
		this.tntPercent.setValue(pct);
		this.calculate();
	}

	public void setGogokStacks(Integer value) {
		this.gogokStacks.setValue(value);
		this.calculate();
	}

	public int getGogokStacks() {
		return this.gogokStacks.getValue();
	}

	public void setPainEnhancerStacks(Integer value) {
		this.painEnhancerStacks.setValue(value);
		this.calculate();
	}

	public int getPainEnhancerStacks() {
		return this.painEnhancerStacks.getValue();
	}

	public boolean getGogok() {
		return gogok.getValue();
	}

	public void setGogok(boolean value) {
		gogok.setValue(value);
		this.calculate();
	}

	public boolean getPainEnhancer() {
		return painEnhancer.getValue();
	}

	public void setPainEnhancer(boolean value) {
		painEnhancer.setValue(value);
		this.calculate();
	}

	public boolean getTnt() {
		return tnt.getValue();
	}

	public void setTnt(boolean value) {
		tnt.setValue(value);
		this.calculate();
	}

	public int getGogokLevel() {
		return gogokLevel.getValue();
	}

	public void setGogokLevel(int value) {
		gogokLevel.setValue(value);
		this.calculate();
	}

	public int getPainEnhancerLevel() {
		return painEnhancerLevel.getValue();
	}

	public void setPainEnhancerLevel(int value) {
		painEnhancerLevel.setValue(value);
		this.calculate();
	}

	public SimpleCheckBox getBbv() {
		return bbv;
	}

	public DoubleSpinner getBbvUptime() {
		return bbvUptime;
	}

	public SimpleCheckBox getRetribution() {
		return retribution;
	}

	public SimpleCheckBox getValor() {
		return valor;
	}

	public DoubleSpinner getValorUptime() {
		return valorUptime;
	}

	public DoubleSpinner getRetributionUptime() {
		return retributionUptime;
	}

	public double getEquipIAS() {
		return this.equipIAS.getValue() / 100.0;
	}
	
	public double getWeaponIAS() {
		return this.mainHand.getWeaponIAS().getValue() / 100.0;
	}

	public Integer getParagonDexterity() {
		return this.getValue(this.paragonPanel.getParagonDexterity());
	}

	public int getEquipmentDexterity() {
		return this.dexterity.getValue();
	}
	
	public int getHeroLevel() {
		return heroLevel.getValue();
	}

	public void setHatred(int value) {
		this.paragonPanel.getParagonHatred().setValue(value);
	}

	public double getOffHandAverageWeaponDamage() {
		return offHand.getAverageWeaponDamage();
	}

	public WeaponType getOffHandWeaponType() {
		return offHand.getWeaponTypeEnum();
	}

	public double getOffHandWeaponIAS() {
		return offHand.getWeaponIAS().getValue() / 100.0;
	}

	public double getTotalAverageWeaponDamage() {

		WeaponType t = offHand.getWeaponTypeEnum();
		double main = mainHand.getAverageWeaponDamage();
		double oh = offHand.getAverageWeaponDamage();
		
		return (t == null) ? main : ((main + oh) / 2.0);
	}
}
