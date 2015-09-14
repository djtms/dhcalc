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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.dawg6.gwt.common.util.Pair;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.Breakpoint;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.DamageFunction;
import com.dawg6.web.dhcalc.shared.calculator.GemSkill;
import com.dawg6.web.dhcalc.shared.calculator.Passive;
import com.dawg6.web.dhcalc.shared.calculator.SkillType;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.dawg6.web.dhcalc.shared.calculator.WeaponType;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class DPSCalculator extends BasePanel {
	private final NumberSpinner minJewelDamage;
	private final NumberSpinner dexterity;
	private final DoubleSpinner critChance;
	private final NumberSpinner critDamage;
	private final NumberSpinner equipIAS;
	private final Label dps;
	private final Label aps;
	private final NumberSpinner maxJewelDamage;
	private final Label actualCC;
	private final Label actualCD;
	private final Label petAps;
	private final Label breakpoint;
	private final Label actualAps;
	private double sheetDps;
	private double sheetAps;
	private double totalCC;
	private double totalCD;
	private final Label nextBP;
	private final ListBox iasType;
	private Breakpoint bp;
	private Breakpoint.Data currentBp;
	private double petApsValue;
	private double eIas;
	private double wIas;
	private double pIas;
	private double petIasValue;
	private final Label prevBP;
	private boolean disableListeners;
	private final Label fpaLabel;
	private double sentryDpsValue;
	private double buffIas;
	private double focusedMind;
	private double gogokIas;
	private double painEnhancerIas;
	private final Label dexterityLabel;
	private final NumberSpinner heroLevel;
	private final WeaponPanel mainHand;
	private final WeaponPanel offHand;
	private final MainPanel main;
	private double averageDamage;
	private double offHand_averageDamage;
	private final ListBox skillBox;
	private double mainHand_Aps;
	private double offHand_aps;

	public DPSCalculator(MainPanel main) {

		this.main = main;

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

		mainHand = new WeaponPanel("Main Hand", false);
		grid.setWidget(0, 0, mainHand);
		grid.getFlexCellFormatter().setColSpan(0, 0, 2);

		offHand = new WeaponPanel("Off Hand", true);
		grid.setWidget(1, 0, offHand);
		grid.getFlexCellFormatter().setColSpan(1, 0, 2);

		CaptionPanel cptnpnlNewPanel_2 = new CaptionPanel("Equipment");
		grid.setWidget(2, 0, cptnpnlNewPanel_2);

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

		Label lblNewLabel_4 = new Label("Crit Chance (%):");
		flexTable_2.setWidget(3, 0, lblNewLabel_4);
		lblNewLabel_4.setWordWrap(false);

		critChance = new DoubleSpinner();
		critChance.box
				.setTitle("Increased Crit Chance from equipment and set bonuses");
		flexTable_2.setWidget(3, 1, critChance);
		critChance.setVisibleLength(6);
		critChance.addChangeHandler(handler);
		critChance.setMax(95.0);

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

		flexTable_7.getCellFormatter().setVerticalAlignment(0, 0,
				HasVerticalAlignment.ALIGN_TOP);
		flexTable_7.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_LEFT);

		CaptionPanel cptnpnlNewPanel_4 = new CaptionPanel("Hero");
		grid.setWidget(2, 1, cptnpnlNewPanel_4);
		grid.getCellFormatter().setVerticalAlignment(2, 1,
				HasVerticalAlignment.ALIGN_TOP);

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

		dexterity.addChangeHandler(handler);

		CaptionPanel cptnpnlNewPanel_6 = new CaptionPanel(
				"Breakpoint Calculator");
		grid.setWidget(3, 0, cptnpnlNewPanel_6);
		grid.getFlexCellFormatter().setColSpan(3, 0, 2);

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

		Label lblBreakPoint = new Label("Break Point #:");
		flexTable_6.setWidget(1, 2, lblBreakPoint);
		lblBreakPoint.setWordWrap(false);
		lblBreakPoint.setStyleName("boldText");

		breakpoint = new Label("0");
		breakpoint.setTitle("Break Point # for selected skill");
		flexTable_6.setWidget(1, 3, breakpoint);
		breakpoint.setStyleName("boldText");

		CaptionPanel cptnpnlNewPanel = new CaptionPanel(
				"Next/Previous Breakpoint");
		flexTable_6.setWidget(0, 4, cptnpnlNewPanel);
		cptnpnlNewPanel.setStyleName("boldText");

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		cptnpnlNewPanel.setContentWidget(flexTable);

		Label skill = new Label("Skill:");
		skill.setWordWrap(false);
		flexTable.setWidget(0, 0, skill);

		skillBox = new ListBox();
		skillBox.setWidth("100%");
		flexTable.setWidget(0, 1, skillBox);

		List<Pair<String, String>> list = new Vector<Pair<String, String>>();

		for (ActiveSkill s : ActiveSkill.values()) {

			if (DamageFunction.hasDamage(s) && (s.getFrames() != 0))
				list.add(new Pair<String, String>(s.getLongName(), s.name()));
		}

		Collections.sort(list, new Comparator<Pair<String, String>>() {

			@Override
			public int compare(Pair<String, String> o1, Pair<String, String> o2) {
				return o1.getA().toLowerCase()
						.compareTo(o2.getA().toLowerCase());
			}
		});

		for (Pair<String, String> p : list) {
			skillBox.addItem(p.getA(), p.getB());
		}

		skillBox.setSelectedIndex(0);

		skillBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				bpDataChanged();
			}
		});

		Label lblWithRepectTo = new Label("With Repect To:");
		lblWithRepectTo.setWordWrap(false);
		flexTable.setWidget(1, 0, lblWithRepectTo);

		iasType = new ListBox();
		flexTable.setWidget(1, 1, iasType);
		iasType.setWidth("100%");
		iasType.setSelectedIndex(0);

		Label lblApsForNext = new Label("IAS for next BP:");
		flexTable.setWidget(2, 0, lblApsForNext);
		lblApsForNext.setWordWrap(false);
		lblApsForNext.setStyleName("boldText");

		nextBP = new Label("0");
		nextBP.setTitle("IAS needed for next BP");
		flexTable.setWidget(2, 1, nextBP);
		nextBP.setWordWrap(false);
		nextBP.setStyleName("boldText");

		Label lblextraIas = new Label("\"Extra\" IAS:");
		flexTable.setWidget(3, 0, lblextraIas);
		lblextraIas.setWordWrap(false);
		lblextraIas.setStyleName("boldText");

		prevBP = new Label("0");
		prevBP.setTitle("Extra IAS that can be removed without lowering BP");
		flexTable.setWidget(3, 1, prevBP);
		prevBP.setWordWrap(false);
		prevBP.setStyleName("boldText");

		iasType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				bpDataChanged();
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
		flexTable_6.setWidget(0, 2, lblPetAps);
		lblPetAps.setWordWrap(false);
		lblPetAps.setStyleName("boldText");

		petAps = new Label("0.0");
		petAps.setTitle("Calculated Pet/Sentry APS");
		flexTable_6.setWidget(0, 3, petAps);
		petAps.setStyleName("boldText");

		Label lblTotal = new Label("Total Crit Chance:");
		flexTable_6.setWidget(2, 0, lblTotal);
		lblTotal.setStyleName("boldText");

		actualCC = new Label("0.0%");
		actualCC.setTitle("Total CC as shown on character details screen under offense");
		flexTable_6.setWidget(2, 1, actualCC);
		actualCC.setStyleName("boldText");

		Label lblAttacksPer = new Label("FPA");
		lblAttacksPer.setWordWrap(false);
		lblAttacksPer.setStyleName("boldText");
		flexTable_6.setWidget(2, 2, lblAttacksPer);

		fpaLabel = new Label("0");
		fpaLabel.setTitle("Frames per Attack");
		fpaLabel.setStyleName("boldText");
		flexTable_6.setWidget(2, 3, fpaLabel);

		Label lblTotalCritDamage = new Label("Total Crit Hit Damage:");
		flexTable_6.setWidget(3, 0, lblTotalCritDamage);
		lblTotalCritDamage.setStyleName("boldText");

		actualCD = new Label("0.0%");
		actualCD.setTitle("Total CHD as shown on character details screen under offense");
		flexTable_6.setWidget(3, 1, actualCD);
		actualCD.setStyleName("boldText");

		Label lblBpAps = new Label("Actual APS:");
		flexTable_6.setWidget(3, 2, lblBpAps);
		lblBpAps.setWordWrap(false);
		lblBpAps.setStyleName("boldText");

		actualAps = new Label("0");
		actualAps.setTitle("Actual APS (based on Breakpoint)");
		flexTable_6.setWidget(3, 3, actualAps);
		actualAps.setStyleName("boldText");

		Label lblTotalDexterity = new Label("Total Dexterity:");
		lblTotalDexterity.setStyleName("boldText");
		flexTable_6.setWidget(4, 0, lblTotalDexterity);

		dexterityLabel = new Label("0");
		dexterityLabel
				.setTitle("This is the total of all Dexterity from items, levels and paragon points");
		dexterityLabel.setStyleName("boldText");
		flexTable_6.setWidget(4, 1, dexterityLabel);

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

	protected void bpDataChanged() {
		updateBpData();
	}

	private IasType getSelectedIasType() {
		int n = this.iasType.getSelectedIndex();

		if (n < 0)
			return null;

		return IasType.valueOf(iasType.getValue(n));
	}

	private ActiveSkill getSelectedSkill() {
		int n = skillBox.getSelectedIndex();

		if (n < 0)
			return null;

		return ActiveSkill.valueOf(skillBox.getValue(n));
	}

	private void updateBpData() {

		ActiveSkill skill = getSelectedSkill();
		IasType type = getSelectedIasType();
		this.bp = getBreakpoint();
		double aps = (skill == ActiveSkill.BOLT) ? this.petApsValue
				: this.sheetAps;

		if ((skill == ActiveSkill.MS) && this.main.getItemPanel().isYangs())
			aps *= 1.5;

		if ((skill.getSkillType() == SkillType.Primary)
				&& this.main.getItemPanel().isHuntersWrath())
			aps *= 1.3;

		this.currentBp = bp.get(aps);

		this.breakpoint.setText(String.valueOf(this.currentBp.bp));
		this.actualAps.setText(Util.format(this.currentBp.actualAps));
		this.fpaLabel.setText(String.valueOf(this.currentBp.fpa));

		Breakpoint.Data next = bp.next(this.currentBp);
		Breakpoint.Data prev = bp.prev(this.currentBp);

		if (next != null) {
			double nextBp = 0.0;
			double without = aps;

			switch (type) {

			case Weapon:
				without = without / (1.0 + this.wIas);
				nextBp = (next.minAps / without) - 1.0;
				nextBp -= this.wIas;
				break;
			case Equipment:
				without = without
						/ (1.0 + this.eIas + this.pIas + focusedMind + gogokIas
								+ painEnhancerIas + buffIas);
				nextBp = (next.minAps / without) - 1.0;
				nextBp -= (this.eIas + this.pIas + focusedMind + gogokIas
						+ painEnhancerIas + buffIas);
				break;
			case Pet:
				without = without / (1.0 + this.petIasValue);
				nextBp = (next.minAps / without) - 1.0;
				nextBp -= this.petIasValue;
				break;
			default:
			}

			this.nextBP.setText(Math.round(nextBp * 10000.0) / 100.0 + "%");
		} else {
			this.nextBP.setText("N/A");
		}

		if (prev != null) {
			double without = aps;
			double prevBp = 0.0;

			switch (type) {

			case Weapon:
				without = without / (1.0 + this.wIas);

				if (without < currentBp.minAps) {
					prevBp = (currentBp.minAps / without) - 1.0;
				}
				prevBp = this.wIas - prevBp;

				break;
			case Equipment:
				without = without / (1.0 + this.eIas + this.pIas);

				if (without < currentBp.minAps) {
					prevBp = (currentBp.minAps / without) - 1.0;
				}
				prevBp = (this.eIas + this.pIas) - prevBp;

				break;
			case Pet:
				without = without / (1.0 + this.petIasValue);

				if (without < currentBp.minAps) {
					prevBp = (currentBp.minAps / without) - 1.0;
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
				new Field(this.mainHand.getWeaponDamage(), "calc.WeaponDamage",
						"0"),
				new Field(this.offHand.getWeaponType(),
						"calc.offHand.WeaponType", ""),
				new Field(this.offHand.getBaseMin(), "calc.offHand.MinDamage",
						"0"),
				new Field(this.offHand.getBaseMax(), "calc.offHand.MaxDamage",
						"0"),
				new Field(this.offHand.getAddMin(),
						"calc.offHand.AddMinDamage", "0"),
				new Field(this.offHand.getAddMax(),
						"calc.offHand.AddMaxDamage", "0"),
				new Field(this.offHand.getWeaponIAS(),
						"calc.offHand.WeaponIAS", "0"),
				new Field(this.offHand.getWeaponDamage(),
						"calc.offHand.WeaponDamage", "0"),
				new Field(this.minJewelDamage, "calc.MinJewelDamage", "0"),
				new Field(this.maxJewelDamage, "calc.MaxJewelDamage", "0"),
				new Field(this.equipIAS, "calc.EquipIAS", "0"),
				new Field(this.dexterity, "calc.EquipmentDexterity", "0"),
				new Field(this.heroLevel, "calc.HeroLevel", "70"),
				new Field(this.critChance, "calc.CritChance", "0"),
				new Field(this.critDamage, "calc.CritDamage", "0"), };
	}

	public void calculate() {

		WeaponType type = getMainHandWeaponType();

		if (type == null)
			type = WeaponType.Bow;

		WeaponType offHand_type = getOffHandWeaponType();

		double min = this.mainHand.getWeaponMin()
				+ getValue(this.minJewelDamage);
		double max = this.mainHand.getWeaponMax()
				+ getValue(this.maxJewelDamage);
		double offHand_min = this.offHand.getWeaponMin()
				+ getValue(this.minJewelDamage);
		double offHand_max = this.offHand.getWeaponMax()
				+ getValue(this.maxJewelDamage);
		double dex = getValue(this.dexterity)
				+ (main.getParagonPanel().getParagonDexterity().getValue() * 5)
				+ 7 + (this.heroLevel.getValue() * 3);
		double pCC = (getValue(main.getParagonPanel().getParagonCC()) * 0.1) / 100.0;
		double pCD = (getValue(main.getParagonPanel().getParagonCHD()) * 1.0) / 100.0;
		double aCC = 0.0;
		double aDam = 0.0;
		double aCD = 0.0;
		buffIas = 0.0;

		if (main.getPlayerBuffs().getBbv().getValue()
				&& (Math.round(main.getPlayerBuffs().getBbvUptime().getValue()) == 100)) {
			buffIas += 0.20;
		}

		if (main.getPlayerBuffs().getRetribution().getValue()
				&& (Math.round(main.getPlayerBuffs().getRetributionUptime()
						.getValue()) == 100)) {
			buffIas += 0.10;
		}

		if (main.getPlayerBuffs().getValor().getValue()) {

			int a = (int) Math.round(main.getPlayerBuffs()
					.getValorActiveUptime().getValue());
			int b = (int) Math.round(main.getPlayerBuffs()
					.getValorPassiveUptime().getValue());

			if (a >= 100) {
				buffIas += 0.15;
			} else if ((a + b) >= 100) {
				buffIas += 0.08;
			}
		}

		if (main.getPlayerBuffs().getStretchTime().getValue()
				&& (main.getPlayerBuffs().getStretchTimeUptime().getValue() == 100)) {
			buffIas += 0.1;
		}

		if (main.getPassivesPanel().getPassives().contains(Passive.Archery)) {
			if (type == WeaponType.HandCrossbow) {
				aCC += 0.05;
			} else if (type == WeaponType.Bow) {
				aDam += 0.08;
			} else if (type == WeaponType.Crossbow) {
				aCD += 0.5;
			}
		}

		if (main.getPassivesPanel().getPassives().contains(Passive.Steady_Aim)) {
			aDam += .2;
		}

		double anatomy = main.getBuffPanel().getAnatomy().getValue() ? 0.018
				: 0.0;

		double critChance = Math.min(1.0, .05
				+ (getValue(this.critChance) / 100.0) + pCC + aCC + anatomy);
		double critDamage = getValue(this.critDamage) / 100.0 + pCD + aCD;

		this.eIas = (getValue(this.equipIAS)) / 100.0;
		this.wIas = (getValue(this.mainHand.getWeaponIAS())) / 100.0;
		double offHand_wIas = (getValue(this.offHand.getWeaponIAS())) / 100.0;
		this.pIas = getValue(main.getParagonPanel().getParagonIAS()) * 0.002;
		focusedMind = (main.getBuffPanel().getFocusedMind().getValue() ? 0.03
				: 0.0);
		gogokIas = (main.getGemPanel().isGem(GemSkill.Gogok)) ? (main
				.getGemPanel().getGemAttribute(GemSkill.Gogok, GemSkill.STACKS) / 100.0)
				: 0.0;
		painEnhancerIas = (main.getGemPanel().isGem(GemSkill.PainEnhancer) && main
				.getGemPanel().getGemLevel(GemSkill.PainEnhancer) >= 25) ? (main
				.getGemPanel().getGemAttribute(GemSkill.PainEnhancer,
						GemSkill.BLEEDING) * 0.03) : 0.0;

		double dwIas = (offHand_type != null) ? 0.15 : 0.0;

		mainHand_Aps = type.getAps()
				* (1.0 + wIas)
				* (1.0 + eIas + pIas + focusedMind + gogokIas + painEnhancerIas
						+ buffIas + dwIas);

		offHand_aps = (offHand_type == null) ? 0.0 : (offHand_type.getAps()
				* (1.0 + offHand_wIas) * (1.0 + eIas + pIas + focusedMind
				+ gogokIas + painEnhancerIas + buffIas + dwIas));

		this.averageDamage = ((min + max) / 2.0);

		this.offHand_averageDamage = ((offHand_min + offHand_max) / 2.0);

		double mainHand_dps = averageDamage * mainHand_Aps
				* (1.0 + critChance * critDamage) * (1.0 + (dex / 100.0))
				* (1.0 + aDam);

		double offHand_dps = (offHand_type == null) ? 0.0
				: (offHand_averageDamage * offHand_aps
						* (1.0 + critChance * critDamage)
						* (1.0 + (dex / 100.0)) * (1.0 + aDam));

		double dw_aps = (mainHand_Aps + offHand_aps) / 2.0;
		double dw_averageDamage = (averageDamage + offHand_averageDamage) / 2.0;
		double totalDps = (offHand_type == null) ? (averageDamage
				* mainHand_Aps * (1.0 + critChance * critDamage)
				* (1.0 + (dex / 100.0)) * (1.0 + aDam)) : (dw_averageDamage
				* dw_aps * (1.0 + critChance * critDamage)
				* (1.0 + (dex / 100.0)) * (1.0 + aDam));

		this.sheetDps = Math.round(totalDps * 10.0) / 10.0;
		this.dps.setText(Util.format(sheetDps));
		this.sheetAps = (offHand_type == null) ? mainHand_Aps : dw_aps;
		this.aps.setText(Util.format((Math.round(sheetAps * 1000.0) / 1000.0)));

		this.totalCC = critChance;
		this.totalCD = critDamage;
		this.actualCC.setText(Util.format(critChance * 100.0) + "%");
		this.actualCD.setText(Util.format(critDamage * 100.0) + "%");

		this.petIasValue = main.getItemPanel().isTnt() ? main.getItemPanel()
				.getTntPercent() : 0.0;
		this.petApsValue = Math.round(mainHand_Aps * 1000.0
				* (1.0 + petIasValue)) / 1000.0;
		this.petAps.setText(Util.format(petApsValue));

		this.dexterityLabel.setText(String.valueOf(this.getTotalDexterity()));

		this.bpDataChanged();
	}

	private Breakpoint getBreakpoint() {
		ActiveSkill skill = getSelectedSkill();

		int frames = skill.getFrames();

		if (frames < 0) {
			WeaponType type = mainHand.getWeaponTypeEnum();

			if (type == null) {
				type = WeaponType.Bow;
			}

			frames = type.getFrames();
		}

		return new Breakpoint(frames);
	}

	public double getSentryDps() {
		return this.sentryDpsValue;
	}

	public WeaponType getMainHandWeaponType() {
		return mainHand.getWeaponTypeEnum();
	}

	public void importHero(String server, String profile, int tag, int id,
			CharacterData data) {

		this.heroLevel.setValue(data.getHeroLevel());
		this.mainHand.setWeaponTypeEnum(data.getWeaponType());
		this.offHand.setWeaponTypeEnum(data.getOffHand_weaponType());

		this.disableListeners = true;

		this.mainHand.getBaseMin().setValue(data.getBaseMin());
		this.mainHand.getBaseMax().setValue(data.getBaseMax());
		this.mainHand.getAddMin().setValue(data.getAddMin());
		this.mainHand.getAddMax().setValue(data.getAddMax());
		this.mainHand.getWeaponIAS().setValue(
				(int) (Math.round(data.getWeaponIas() * 100.0)));
		this.mainHand.getWeaponDamage().setValue(
				(int) Math.round(data.getWeaponDamagePercent() * 100.0));
		this.offHand.getBaseMin().setValue(data.getOffHand_baseMin());
		this.offHand.getBaseMax().setValue(data.getOffHand_baseMax());
		this.offHand.getAddMin().setValue(data.getOffHand_addMin());
		this.offHand.getAddMax().setValue(data.getOffHand_addMax());
		this.offHand.getWeaponIAS().setValue(
				(int) (Math.round(data.getOffHand_weaponIas() * 100.0)));
		this.offHand
				.getWeaponDamage()
				.setValue(
						(int) Math.round(data.getOffHand_weaponDamagePercent() * 100.0));

		this.critChance
				.setValue(Math.round(data.getEquipCritChance() * 10000.0) / 100.0);
		this.critDamage
				.setValue((int) Math.round(data.getEquipCritDamage() * 100.0));
		this.equipIAS.setValue((int) (Math.round(data.getEquipIas() * 100.0)));
		this.dexterity.setValue(data.getEquipmentDexterity());
		this.minJewelDamage.setValue((int) Math.round(data.getJewelryMin()));
		this.maxJewelDamage.setValue((int) Math.round(data.getJewelryMax()));

		this.disableListeners = false;

		calculate();

		setDefaultSkill(data.getSkills().keySet());

		saveForm();
	}

	public void setDefaultSkill(Set<ActiveSkill> skills) {
		ActiveSkill spender = null;

		for (ActiveSkill s : skills) {
			if (s.getFrames() != 0) {
				if ((spender == null)
						|| (s.getSkillType() == SkillType.Spender)
						|| (s.getSkillType() == SkillType.Channeled)) {
					spender = s;
				}
			}
		}

		if (spender != null) {
			this.setSelectedSkill(spender);
		}
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

	public double getMainHandAverageWeaponDamage() {
		return this.averageDamage;
	}

	public int getTotalDexterity() {
		return getValue(this.dexterity)
				+ (main.getParagonPanel().getParagonDexterity().getValue() * 5)
				+ 7 + (getHeroLevel() * 3);
	}

	public double getDamageBonus() {
		if (main.getPassivesPanel().getPassives().contains(Passive.Archery)) {
			WeaponType type = getMainHandWeaponType();

			if (type == WeaponType.Bow)
				return 0.08;
		}

		return 0.0;
	}

	public boolean isDisableListeners() {
		return disableListeners;
	}

	public void setDisableListeners(boolean disableListeners) {
		this.disableListeners = disableListeners;
	}

	public double getEquipIAS() {
		return this.equipIAS.getValue() / 100.0;
	}

	public double getWeaponIAS() {
		return this.mainHand.getWeaponIAS().getValue() / 100.0;
	}

	public int getEquipmentDexterity() {
		return this.dexterity.getValue();
	}

	public int getHeroLevel() {
		return heroLevel.getValue();
	}

	public double getOffHandAverageWeaponDamage() {
		return offHand_averageDamage;
	}

	public WeaponType getOffHandWeaponType() {
		return offHand.getWeaponTypeEnum();
	}

	public double getOffHandWeaponIAS() {
		return offHand.getWeaponIAS().getValue() / 100.0;
	}

	public double getTotalAverageWeaponDamage() {

		WeaponType t = offHand.getWeaponTypeEnum();
		double main = this.averageDamage;
		double oh = this.offHand_averageDamage;

		return (t == null) ? main : ((main + oh) / 2.0);
	}

	public WeaponPanel getMainHand() {
		return mainHand;
	}

	public WeaponPanel getOffHand() {
		return offHand;
	}

	public double getEquipmentCritDamage() {
		return this.critDamage.getValue() / 100.0;
	}

	public double getEquipmentCritChance() {
		return this.critChance.getValue() / 100.0;
	}

	public double getJewelryMin() {
		return this.minJewelDamage.getValue();
	}

	public double getJewelryMax() {
		return this.maxJewelDamage.getValue();
	}

	public void setSelectedSkill(ActiveSkill skill) {
		int num = skillBox.getItemCount();
		String name = skill.name();

		for (int i = 0; i < num; i++) {
			if (name.equals(skillBox.getValue(i))) {
				skillBox.setSelectedIndex(i);
				return;
			}
		}
	}
}
