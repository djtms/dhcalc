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
	private final Label minDamage;
	private final Label maxDamage;
	private final ListBox weaponType;
	private final NumberSpinner minJewelDamage;
	private final NumberSpinner dexterity;
	private final DoubleSpinner critChance;
	private final NumberSpinner critDamage;
	private final NumberSpinner weaponIAS;
	private final NumberSpinner equipIAS;
	private final SimpleCheckBox archery;
	private final Label dps;
	private final Label aps;
	private final NumberSpinner maxJewelDamage;
	private final NumberSpinner weaponDamage;
	private final Label weaponDps;
	private final Label weaponAps;
	private final Label actualCC;
	private final Label actualCD;
	private final Label avgWeaponDamage;
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
	private double averageWeaponDamage;
	private final DoubleSpinner baseMin;
	private final DoubleSpinner baseMax;
	private final DoubleSpinner addMin;
	private final DoubleSpinner addMax;
	private boolean disableListeners;
	private double min;
	private double max;
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

	public DPSCalculator() {

		FlexTable grid = new FlexTable();
		grid.setBorderWidth(0);
		grid.setCellPadding(5);
		initWidget(grid);

		CaptionPanel cptnpnlNewPanel_1 = new CaptionPanel("Weapon");
		grid.setWidget(0, 0, cptnpnlNewPanel_1);

		FlexTable flexTable_1 = new FlexTable();
		flexTable_1.setCellPadding(2);
		cptnpnlNewPanel_1.setContentWidget(flexTable_1);

		Label lblNewLabel_2 = new Label("Weapon Type:");
		flexTable_1.setWidget(0, 0, lblNewLabel_2);
		lblNewLabel_2.setWordWrap(false);
		weaponType = new ListBox();
		flexTable_1.setWidget(0, 1, weaponType);

		weaponType.setSelectedIndex(0);

		Label lblBaseDamage = new Label("Base (Physical) Damage:");
		lblBaseDamage.setWordWrap(false);
		flexTable_1.setWidget(1, 0, lblBaseDamage);

		baseMin = new DoubleSpinner();
		baseMin.box
				.setTitle("This number is not displayed in-game. It is imported from hero import.");
		baseMin.setVisibleLength(8);
		baseMin.setText("0");
		flexTable_1.setWidget(1, 1, baseMin);

		baseMin.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateTooltipDamage();
			}
		});

		Label label = new Label(" to ");
		label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_1.setWidget(1, 2, label);

		baseMax = new DoubleSpinner();
		baseMax.box
				.setTitle("This number is not displayed in-game. It is imported from hero import.");
		baseMax.setVisibleLength(8);
		baseMax.setText("0");
		flexTable_1.setWidget(1, 3, baseMax);
		baseMax.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateTooltipDamage();
			}
		});

		Label lblNewLabel = new Label("Tooltip Damage:");
		lblNewLabel.setStyleName("boldText");
		flexTable_1.setWidget(1, 4, lblNewLabel);
		lblNewLabel.setWordWrap(false);

		minDamage = new Label();
		minDamage.setTitle("Shown as \"white\" damage on weapon tooltip.");
		flexTable_1.setWidget(1, 5, minDamage);
		minDamage.setText("0");
		minDamage.setStyleName("boldText");

		Label label_4 = new Label(" to ");
		label_4.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_1.setWidget(1, 6, label_4);

		maxDamage = new Label();
		maxDamage.setTitle("Shown as \"white\" damage on weapon tooltip.");
		flexTable_1.setWidget(1, 7, maxDamage);
		maxDamage.setText("0");
		maxDamage.setStyleName("boldText");

		Label lblAddedDamage = new Label("Added (Elemental) Damage:");
		lblAddedDamage.setWordWrap(false);
		flexTable_1.setWidget(2, 0, lblAddedDamage);

		addMin = new DoubleSpinner();
		addMin.box.setTitle("Shown as primary stat on weapon.");
		addMin.setVisibleLength(8);
		addMin.setText("0");
		flexTable_1.setWidget(2, 1, addMin);
		addMin.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateTooltipDamage();
			}
		});

		Label label_2 = new Label(" to ");
		label_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_1.setWidget(2, 2, label_2);

		addMax = new DoubleSpinner();
		addMax.box.setTitle("Shown as primary stat on weapon.");
		addMax.setVisibleLength(8);
		addMax.setText("0");
		flexTable_1.setWidget(2, 3, addMax);
		addMax.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateTooltipDamage();
			}
		});

		Label lblWeaponDamage = new Label("Average Weapon Damage:");
		flexTable_1.setWidget(2, 4, lblWeaponDamage);
		lblWeaponDamage.setWordWrap(false);
		lblWeaponDamage.setStyleName("boldText");

		avgWeaponDamage = new Label("0.0");
		flexTable_1.setWidget(2, 5, avgWeaponDamage);
		avgWeaponDamage
				.setTitle("Average weapon damage used for Sentry calculations");
		avgWeaponDamage.setStyleName("boldText");

		Label lblNewLabel_2a = new Label("Weapon IAS (%):");
		flexTable_1.setWidget(3, 0, lblNewLabel_2a);
		lblNewLabel_2a.setWordWrap(false);

		weaponIAS = new NumberSpinner();
		weaponIAS.box
				.setTitle("Increased attack speed as a primary attribute on the weapon.");
		flexTable_1.setWidget(3, 1, weaponIAS);
		weaponIAS.setVisibleLength(6);

		weaponIAS.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateTooltipDamage();
			}
		});

		Label lblNewLabel_7c = new Label("Weapon DPS:");
		flexTable_1.setWidget(3, 4, lblNewLabel_7c);
		lblNewLabel_7c.setWordWrap(false);
		lblNewLabel_7c.setStyleName("boldText");

		weaponDps = new Label("0.0");
		weaponDps.setTitle("Shown as dps on weapon tooltip.");
		flexTable_1.setWidget(3, 5, weaponDps);
		weaponDps.setStyleName("boldText");

		Label lblNewLabel_2d = new Label("Weapon Increased Damage (%):");
		flexTable_1.setWidget(4, 0, lblNewLabel_2d);
		lblNewLabel_2d.setWordWrap(false);

		weaponDamage = new NumberSpinner();
		weaponDamage.box
				.setTitle("Increased damage as a primary attribute on the weapon.");
		flexTable_1.setWidget(4, 1, weaponDamage);
		weaponDamage.setVisibleLength(6);

		weaponDamage.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateTooltipDamage();
			}
		});

		Label lblNewLabel_7d = new Label("Weapon APS:");
		flexTable_1.setWidget(4, 4, lblNewLabel_7d);
		lblNewLabel_7d.setWordWrap(false);
		lblNewLabel_7d.setStyleName("boldText");

		weaponAps = new Label("0.0");
		weaponAps.setTitle("As shown on weapon tooltip");
		flexTable_1.setWidget(4, 5, weaponAps);
		weaponAps.setStyleName("boldText");
		flexTable_1.getFlexCellFormatter().setColSpan(4, 5, 3);
		flexTable_1.getFlexCellFormatter().setColSpan(2, 5, 3);
		flexTable_1.getFlexCellFormatter().setColSpan(3, 5, 3);

		FlexTable flexTable_3 = new FlexTable();
		grid.setWidget(1, 0, flexTable_3);

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
		minJewelDamage.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

		Label label_1 = new Label(" to ");
		flexTable_2.setWidget(0, 2, label_1);
		label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		maxJewelDamage = new NumberSpinner();
		maxJewelDamage.box.setTitle("Total of all jewelry damage");
		flexTable_2.setWidget(0, 3, maxJewelDamage);
		maxJewelDamage.setVisibleLength(6);
		maxJewelDamage.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

		Label lblNewLabel_2b = new Label("Equipment IAS (%):");
		flexTable_2.setWidget(1, 0, lblNewLabel_2b);
		lblNewLabel_2b.setWordWrap(false);

		equipIAS = new NumberSpinner();
		equipIAS.box
				.setTitle("Increased attack speed from equipment, except Weapon");
		flexTable_2.setWidget(1, 1, equipIAS);
		equipIAS.setVisibleLength(6);
		equipIAS.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

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
		tntPercent.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});
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
		critChance.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

		Label lblNewLabel_5 = new Label("Crit Hit Damage (%):");
		flexTable_2.setWidget(4, 0, lblNewLabel_5);
		lblNewLabel_5.setWordWrap(false);

		critDamage = new NumberSpinner();
		critDamage.box
				.setTitle("Increased Crit Hit Damage from equipment and set bonuses");
		flexTable_2.setWidget(4, 1, critDamage);
		critDamage.setVisibleLength(6);
		critDamage.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

		for (WeaponType t : WeaponType.values()) {
			weaponType.addItem(t.getName(), t.name());
		}

		weaponType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				DPSCalculator.this.updateTooltipDamage();
			}
		});

		FlexTable flexTable_7 = new FlexTable();
		grid.setWidget(1, 1, flexTable_7);

		paragonPanel = new ParagonPanel();
		flexTable_7.setWidget(0, 0, paragonPanel);

		paragonPanel.getParagonIAS().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

		paragonPanel.getParagonCC().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

		paragonPanel.getParagonCHD().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

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

		archery.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateTooltipDamage();
			}
		});

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

		steadyAim.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});

		buffPanel.getAnatomy().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateTooltipDamage();
			}
		});

		buffPanel.getFocusedMind().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateTooltipDamage();
			}
		});

		buffPanel.getHysteria().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateTooltipDamage();
			}
		});

		CaptionPanel cptnpnlNewPanel_4 = new CaptionPanel("Character");
		flexTable_3.setWidget(1, 0, cptnpnlNewPanel_4);

		FlexTable flexTable_4 = new FlexTable();
		flexTable_4.setCellPadding(2);
		cptnpnlNewPanel_4.setContentWidget(flexTable_4);

		Label lblNewLabel_3 = new Label("Total Dexterity:");
		flexTable_4.setWidget(0, 0, lblNewLabel_3);
		lblNewLabel_3.setWordWrap(false);

		dexterity = new NumberSpinner();
		dexterity.box
				.setTitle("Total of all dexterity from levels, paragon points, equipment and set bonuses");
		flexTable_4.setWidget(0, 1, dexterity);
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
				.setTitle("Total of all dexterity from levels, paragon points, equipment and set bonuses");
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
				.setTitle("Total of all dexterity from levels, paragon points, equipment and set bonuses");
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

		gogok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});
		bbv.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});
		valor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});
		retribution.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});
		painEnhancer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});

		gogokStacks.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});
		bbvUptime.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});
		valorUptime.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});
		retributionUptime.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});
		painEnhancerStacks.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});
		painEnhancerLevel.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});
		dexterity.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculate();
			}
		});

		CaptionPanel cptnpnlNewPanel_6 = new CaptionPanel("Calculator");
		grid.setWidget(2, 0, cptnpnlNewPanel_6);

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

		iasType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				iasTypeChanged();
			}
		});

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
		grid.getCellFormatter().setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(1, 1,
				HasVerticalAlignment.ALIGN_TOP);
		grid.getCellFormatter().setHorizontalAlignment(1, 0,
				HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(1, 0,
				HasVerticalAlignment.ALIGN_TOP);

		for (IasType t : IasType.values()) {
			iasType.addItem(t.getDescription(), t.name());
		}
		grid.getFlexCellFormatter().setColSpan(0, 0, 2);
		grid.getFlexCellFormatter().setColSpan(2, 0, 2);

		this.weaponIAS.setMax(7);
		this.weaponDamage.setMax(10);
		this.gogokStacks.setMax(15);
		this.bbvUptime.setMax(100.0);
		this.valorUptime.setMax(100.0);
		this.retributionUptime.setMax(100.0);

		this.tnt.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});

		this.gogok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});

	}

	protected void updateTooltipDamage() {

		if (!disableListeners) {

			double wpnDamage = (double) this.weaponDamage.getValue() / 100.0;

			this.min = (baseMin.getValue() + addMin.getValue())
					* (1.0 + wpnDamage);
			this.max = (baseMax.getValue() + addMax.getValue())
					* (1.0 + wpnDamage);

			minDamage.setText(Util.format(min));
			maxDamage.setText(Util.format(max));

			WeaponType type = getWeaponType();

			double weaponAps = type.getAps()
					* (1.0 + (getValue(this.weaponIAS) / 100.0));

			double weaponDps = Math.round(((min + max) / 2.0) * weaponAps
					* 10.0) / 10.0;

			this.weaponDps
					.setText(Util.format((Math.round(weaponDps * 10.0) / 10.0)));
			this.weaponAps
					.setText(Util.format((Math.round(weaponAps * 100.0) / 100.0)));

			calculate();
		}

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

		this.updateTooltipDamage();
	}

	@Override
	public Field[] getFields() {
		return new Field[] {
				new Field(this.weaponType, "calc.WeaponType",
						WeaponType.HandCrossbow.name()),
				new Field(this.baseMin, "calc.MinDamage", "100"),
				new Field(this.baseMax, "calc.MaxDamage", "200"),
				new Field(this.addMin, "calc.AddMinDamage", "0"),
				new Field(this.addMax, "calc.AddMaxDamage", "0"),
				new Field(this.gogokLevel, "calc.GogokLevel", "0"),
				new Field(this.painEnhancerLevel, "calc.PainEnhancerLevel", "0"),
				new Field(this.gogokStacks, "calc.GogokStacks", "0"),
				new Field(this.painEnhancerStacks, "calc.PainEnhancerStacks",
						"0"),
				new Field(this.minJewelDamage, "calc.MinJewelDamage", "0"),
				new Field(this.maxJewelDamage, "calc.MaxJewelDamage", "0"),
				new Field(this.weaponIAS, "calc.WeaponIAS", "0"),
				new Field(this.equipIAS, "calc.EquipIAS", "0"),
				new Field(this.paragonPanel.getParagonIAS(), "calc.ParagonIAS",
						"0"),
				new Field(this.paragonPanel.getParagonCDR(), "calc.ParagonCDR",
						"0"),
				new Field(this.paragonPanel.getParagonCC(), "calc.ParagonCC",
						"0"),
				new Field(this.paragonPanel.getParagonCC(), "calc.ParagonCD",
						"0"),
				new Field(this.tntPercent, "calc.PetIAS", "0"),
				new Field(this.dexterity, "calc.Dexterity", "0"),
				new Field(this.critChance, "calc.CritChance", "0"),
				new Field(this.critDamage, "calc.CritDamage", "0"),
				new Field(this.archery, "calc.Archery", "0"),
				new Field(this.steadyAim, "calc.SteadyAim", "0"),
				new Field(this.weaponDamage, "calc.WeaponDamage", "0"),

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

		WeaponType type = getWeaponType();

		double min = this.min + getValue(this.minJewelDamage);
		double max = this.max + getValue(this.maxJewelDamage);
		double dex = getValue(this.dexterity);
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
		this.wIas = (getValue(this.weaponIAS)) / 100.0;
		this.pIas = getValue(this.paragonPanel.getParagonIAS()) * 0.002;
		focusedMind = (buffPanel.getFocusedMind().getValue() ? 0.03
				: 0.0);
		gogokIas = this.gogok.getValue() ? (this.gogokStacks.getValue() / 100.0)
				: 0.0;
		painEnhancerIas = (this.painEnhancer.getValue() && this.painEnhancerLevel
				.getValue() >= 25) ? (this.painEnhancerStacks.getValue() * 0.03)
				: 0.0;

		double aps = type.getAps()
				* (1.0 + wIas)
				* (1.0 + eIas + pIas + focusedMind + gogokIas + painEnhancerIas + buffIas);

		double averageDamage = ((min + max) / 2.0);
		this.averageWeaponDamage = averageDamage;

		double dps = averageDamage * aps * (1.0 + critChance * critDamage)
				* (1.0 + (dex / 100.0)) * (1.0 + aDam);

		this.sheetDps = Math.round(dps * 10.0) / 10.0;
		this.dps.setText(Util.format(sheetDps));
		this.sheetAps = aps;
		this.aps.setText(Util.format((Math.round(sheetAps * 1000.0) / 1000.0)));

		this.totalCC = critChance;
		this.totalCD = critDamage;
		this.actualCC.setText(Util.format(critChance * 100.0) + "%");
		this.actualCD.setText(Util.format(critDamage * 100.0) + "%");

		this.avgWeaponDamage.setText(Util.format(Math
				.round(100.0 * averageWeaponDamage) / 100.0));

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

		this.iasTypeChanged();
	}

	public double getSentryDps() {
		return this.sentryDpsValue;
	}

	public WeaponType getWeaponType() {
		int i = this.weaponType.getSelectedIndex();
		String value = this.weaponType.getValue(i);

		return WeaponType.valueOf(value);
	}

	public void importHero(String server, String profile, int tag, int id,
			CharacterData data) {

		this.setWeaponType(data.getWeaponType());
		this.disableListeners = true;

		this.baseMin.setValue(data.getBaseMin());
		this.baseMax.setValue(data.getBaseMax());
		this.addMin.setValue(data.getAddMin());
		this.addMax.setValue(data.getAddMax());

		this.disableListeners = false;

		this.critChance.setValue(Math.round(data.getCritChance() * 10000.0) / 100.0);
		this.critDamage.setValue((int) Math.round(data.getCritHitDamage()
				* 100.0));
		this.weaponIAS.setValue((int)(Math.round(data.getWeaponIas() * 100.0)));
		this.equipIAS.setValue((int)(Math.round(data.getEquipIas() * 100.0)));
		this.weaponDamage.setValue((int) Math.round(data.getWeaponDamage() * 100.0));
		this.dexterity.setValue((int)data.getDexterity());
		this.tnt.setValue(data.isTnt());
		this.tntPercent.setValue((int)(Math.round(data.getTntPercent() * 100.0)));
		this.archery.setValue(data.isArchery());
		this.steadyAim.setValue(data.isSteadyAim());
		this.minJewelDamage.setValue((int) Math.round(data.getJewelMin()));
		this.maxJewelDamage.setValue((int) Math.round(data.getJewelMax()));

		this.updateTooltipDamage();

		saveForm();
	}

	@Override
	protected void setFieldValue(ListBox field, String value) {
		if (field == weaponType) {
			WeaponType type = WeaponType.valueOf(value);
			setWeaponType(type);
		}
	}

	private void setWeaponType(WeaponType type) {
		for (int i = 0; i < this.weaponType.getItemCount(); i++) {
			String value = this.weaponType.getValue(i);

			if (value.equals(type.name())) {
				weaponType.setSelectedIndex(i);
				return;
			}
		}
	}

	public void setParagonPoints(int ias, int cdr, int cc, int cd) {

		this.disableListeners = true;

		this.paragonPanel.getParagonIAS().setValue(ias);
		this.paragonPanel.getParagonCDR().setValue(cdr);
		this.paragonPanel.getParagonCC().setValue(cc);
		this.paragonPanel.getParagonCHD().setValue(cd);

		this.disableListeners = false;

		this.updateTooltipDamage();
	}

	public double getAps() {
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

	public int getParagonCD() {
		return this.getValue(this.paragonPanel.getParagonCHD());
	}

	public double getAverageWeaponDamage() {
		return this.averageWeaponDamage;
	}

	public void setArchery(boolean archery) {
		this.archery.setValue(archery);
		this.calculate();
	}

	public int getDexterity() {
		return this.dexterity.getValue();
	}

	public double getDamageBonus() {
		if (this.archery.getValue()) {
			WeaponType type = getWeaponType();

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

}
