package com.dawg6.web.dhcalc.client;

import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class ItemPanel extends Composite {
	private final SimpleCheckBox tnt;
	private final SimpleCheckBox calamity;
	private final NumberSpinner tntPercent;
	private final NumberSpinner eliteDamagePercent;
	private final SimpleCheckBox meticulousBolts;
	private final NumberSpinner meticulousBoltsPercent;
	private final NumberSpinner calamityUptime;
	private final SimpleCheckBox hexingPants;
	private final NumberSpinner hexingPantsUptime;
	private final SimpleCheckBox strongarm;
	private final SimpleCheckBox harrington;
	private final NumberSpinner harringtonPercent;
	private final NumberSpinner harringtonUptime;
	private final NumberSpinner strongarmPercent;
	private final NumberSpinner strongarmUptime;
	private final NumberSpinner hexingPantsPercent;
	private final SimpleCheckBox bombadiers;
	private final NumberSpinner marauders;
	private final SimpleCheckBox spines;
	private final SimpleCheckBox kridershot;
	private final NumberSpinner spinesHatred;
	private final NumberSpinner kridershotHatred;
	private final SimpleCheckBox reapersWraps;
	private final NumberSpinner reapersWrapsPercent;
	private final SimpleCheckBox cindercoat;
	private final NumberSpinner cindercoatPercent;
	private final SimpleCheckBox odysseysEnd;
	private final NumberSpinner odysseysEndPercent;
	private final NumberSpinner odysseysEndUptime;
	private final SimpleCheckBox helltrapper;
	private final NumberSpinner helltrapperPercent;
	private final SimpleCheckBox vaxo;
	private final NumberSpinner areaDamageEquipment;
	private final NumberSpinner numNats;
	private final SimpleCheckBox bastions;
	private final SimpleCheckBox crashingRain;
	private final NumberSpinner numUe;
	private final NumberSpinner crashingRainPercent;
	private final SimpleCheckBox dml;
	private final NumberSpinner dmlPercent;
	private final NumberSpinner vaxoUptime;
	private final NumberSpinner coePercent;
	private final SimpleCheckBox coe;

	public ItemPanel() {

		CaptionPanel cptnpnlSpecialItems = new CaptionPanel("Items");
		initWidget(cptnpnlSpecialItems);

		FlexTable flexTable_1 = new FlexTable();
		cptnpnlSpecialItems.setContentWidget(flexTable_1);

		Label lblNewLabel = new Label("Belts");
		flexTable_1.setWidget(0, 0, lblNewLabel);
		lblNewLabel.setWordWrap(false);
		lblNewLabel.addStyleName("boldText");

		Anchor anchor_5 = new Anchor("Harrington Waistguard");
		flexTable_1.setWidget(1, 0, anchor_5);
		anchor_5.setWordWrap(false);
		anchor_5.setTarget("_blank");
		anchor_5.setHref("http://us.battle.net/d3/en/item/harrington-waistguard");

		harrington = new SimpleCheckBox();
		flexTable_1.setWidget(1, 1, harrington);

		Label label_7 = new Label("Percent:");
		flexTable_1.setWidget(1, 2, label_7);

		harringtonPercent = new NumberSpinner();
		flexTable_1.setWidget(1, 3, harringtonPercent);
		harringtonPercent.setVisibleLength(4);
		harringtonPercent.setText("30");

		Label label_8 = new Label("Uptime:");
		flexTable_1.setWidget(2, 2, label_8);

		harringtonUptime = new NumberSpinner();
		flexTable_1.setWidget(2, 3, harringtonUptime);
		harringtonUptime.setVisibleLength(4);
		harringtonUptime.setText("30");

		Anchor anchor_16 = new Anchor();
		flexTable_1.setWidget(3, 0, anchor_16);
		anchor_16.setWordWrap(false);
		anchor_16.setText("Crashing Rain");
		anchor_16.setTarget("_blank");
		anchor_16.setHref(ActiveSkill.CR.getUrl());

		crashingRain = new SimpleCheckBox();
		flexTable_1.setWidget(3, 1, crashingRain);

		Label label_18 = new Label("Percent:");
		flexTable_1.setWidget(3, 2, label_18);

		crashingRainPercent = new NumberSpinner();
		flexTable_1.setWidget(3, 3, crashingRainPercent);
		crashingRainPercent.setVisibleLength(4);

		Label lblWeapons = new Label("Weapons");
		lblWeapons.setWordWrap(false);
		flexTable_1.setWidget(4, 0, lblWeapons);
		lblWeapons.addStyleName("boldText");

		Anchor anchor_1 = new Anchor("Calamity");
		flexTable_1.setWidget(5, 0, anchor_1);
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/item/calamity");

		calamity = new SimpleCheckBox();
		flexTable_1.setWidget(5, 1, calamity);

		Label lblUptime = new Label("MfD Uptime:");
		lblUptime.setWordWrap(false);
		flexTable_1.setWidget(5, 2, lblUptime);

		calamityUptime = new NumberSpinner();
		flexTable_1.setWidget(5, 3, calamityUptime);
		calamityUptime.setVisibleLength(4);
		calamityUptime.setText("100");
		calamityUptime
				.setTitle("Percent of the time Calamity's Marked for Death will be applied to targets.");

		Anchor anchor_8 = new Anchor("Kridershot");
		flexTable_1.setWidget(6, 0, anchor_8);
		anchor_8.setWordWrap(false);
		anchor_8.setTarget("_blank");
		anchor_8.setHref("http://us.battle.net/d3/en/item/kridershot");

		kridershot = new SimpleCheckBox();
		flexTable_1.setWidget(6, 1, kridershot);

		Label label_10 = new Label("Hatred:");
		flexTable_1.setWidget(6, 2, label_10);

		kridershotHatred = new NumberSpinner();
		flexTable_1.setWidget(6, 3, kridershotHatred);
		kridershotHatred.setVisibleLength(4);
		kridershotHatred.setText("6");
		kridershotHatred.setMin(3);
		kridershotHatred.setMax(4);

		Anchor anchor_11 = new Anchor("Kridershot");
		flexTable_1.setWidget(7, 0, anchor_11);
		anchor_11.setText("Odyssey's End");
		anchor_11.setHTML("Odyssey's End");
		anchor_11.setWordWrap(false);
		anchor_11.setTarget("_blank");
		anchor_11.setHref("http://us.battle.net/d3/en/item/odysseys-end");

		odysseysEnd = new SimpleCheckBox();
		flexTable_1.setWidget(7, 1, odysseysEnd);

		Label label_13 = new Label("Percent:");
		flexTable_1.setWidget(7, 2, label_13);

		odysseysEndPercent = new NumberSpinner();
		flexTable_1.setWidget(7, 3, odysseysEndPercent);
		odysseysEndPercent.setVisibleLength(4);
		odysseysEndPercent.setText("30");
		odysseysEndPercent.setMin(20);
		odysseysEndPercent.setMax(25);

		Label label_14 = new Label("Uptime:");
		flexTable_1.setWidget(8, 2, label_14);

		odysseysEndUptime = new NumberSpinner();
		flexTable_1.setWidget(8, 3, odysseysEndUptime);
		odysseysEndUptime.setVisibleLength(4);
		odysseysEndUptime
				.setTitle("Percent of the time Odyssey's End debuff will be applied to targets.");
		odysseysEndUptime.setText("100");
		odysseysEndUptime.setMin(0);
		odysseysEndUptime.setMax(100);

		Anchor anchor_12 = new Anchor("Helltrapper");
		flexTable_1.setWidget(9, 0, anchor_12);
		anchor_12.setWordWrap(false);
		anchor_12.setText("Helltrapper");
		anchor_12.setTarget("_blank");
		anchor_12.setHTML("Helltrapper");
		anchor_12.setHref("http://us.battle.net/d3/en/item/helltrapper-3tfdaj");

		helltrapper = new SimpleCheckBox();
		flexTable_1.setWidget(9, 1, helltrapper);

		Label label_12 = new Label("Percent:");
		flexTable_1.setWidget(9, 2, label_12);

		helltrapperPercent = new NumberSpinner();
		flexTable_1.setWidget(9, 3, helltrapperPercent);
		helltrapperPercent.setVisibleLength(4);
		helltrapperPercent.setText("25");
		helltrapperPercent.setMin(7);
		helltrapperPercent.setMax(10);

		Label lblGloves = new Label("Gloves");
		lblGloves.setWordWrap(false);
		flexTable_1.setWidget(10, 0, lblGloves);
		lblGloves.addStyleName("boldText");

		Anchor anchor = new Anchor("Tasker and Theo");
		flexTable_1.setWidget(11, 0, anchor);
		anchor.setWordWrap(false);
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/item/tasker-and-theo");
		anchor.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		tnt = new SimpleCheckBox();
		flexTable_1.setWidget(11, 1, tnt);

		Label label = new Label("Percent:");
		flexTable_1.setWidget(11, 2, label);

		tntPercent = new NumberSpinner();
		flexTable_1.setWidget(11, 3, tntPercent);
		tntPercent.setVisibleLength(4);
		tntPercent.setText("35");

		this.tntPercent.setMin(40);
		this.tntPercent.setMax(50);

		Label lblQuivers = new Label("Quivers");
		lblQuivers.setWordWrap(false);
		flexTable_1.setWidget(12, 0, lblQuivers);
		lblQuivers.addStyleName("boldText");

		Anchor anchor_2 = new Anchor("Meticulous Bolts");
		flexTable_1.setWidget(13, 0, anchor_2);
		anchor_2.setWordWrap(false);
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/item/meticulous-bolts");
		anchor_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		meticulousBolts = new SimpleCheckBox();
		flexTable_1.setWidget(13, 1, meticulousBolts);

		Label label_2 = new Label("Percent:");
		flexTable_1.setWidget(13, 2, label_2);

		meticulousBoltsPercent = new NumberSpinner();
		flexTable_1.setWidget(13, 3, meticulousBoltsPercent);
		meticulousBoltsPercent.setVisibleLength(4);
		meticulousBoltsPercent.setText("30");
		meticulousBoltsPercent.setMin(30);
		meticulousBoltsPercent.setMax(40);

		Anchor anchor_6 = new Anchor("Bombadier's Rucksack");
		flexTable_1.setWidget(14, 0, anchor_6);
		anchor_6.setWordWrap(false);
		anchor_6.setTarget("_blank");
		anchor_6.setHref("http://us.battle.net/d3/en/item/bombardiers-rucksack");

		bombadiers = new SimpleCheckBox();
		flexTable_1.setWidget(14, 1, bombadiers);

		Anchor anchor_7 = new Anchor("Spines of Seething Hatred");
		flexTable_1.setWidget(15, 0, anchor_7);
		anchor_7.setWordWrap(false);
		anchor_7.setTarget("_blank");
		anchor_7.setHref("http://us.battle.net/d3/en/item/spines-of-seething-hatred");

		spines = new SimpleCheckBox();
		flexTable_1.setWidget(15, 1, spines);

		Label label_9 = new Label("Hatred:");
		flexTable_1.setWidget(15, 2, label_9);

		spinesHatred = new NumberSpinner();
		flexTable_1.setWidget(15, 3, spinesHatred);
		spinesHatred.setVisibleLength(4);
		spinesHatred.setText("6");
		spinesHatred.setMin(3);
		spinesHatred.setMax(4);

		Anchor anchor_18 = new Anchor("Dead Man's Legacy");
		flexTable_1.setWidget(16, 0, anchor_18);
		anchor_18.setWordWrap(false);
		anchor_18.setTarget("_blank");
		anchor_18.setHref("http://us.battle.net/d3/en/item/dead-mans-legacy");

		dml = new SimpleCheckBox();
		flexTable_1.setWidget(16, 1, dml);

		Label label_19 = new Label("Percent:");
		flexTable_1.setWidget(16, 2, label_19);

		dmlPercent = new NumberSpinner();
		flexTable_1.setWidget(16, 3, dmlPercent);
		dmlPercent.setVisibleLength(4);
		dmlPercent.setText("30");
		dmlPercent.setMin(50);
		dmlPercent.setMax(60);

		Label lblBracers = new Label("Bracers");
		lblBracers.setWordWrap(false);
		flexTable_1.setWidget(17, 0, lblBracers);
		lblBracers.addStyleName("boldText");

		Anchor anchor_4 = new Anchor("Strongarm Bracers");
		flexTable_1.setWidget(18, 0, anchor_4);
		anchor_4.setWordWrap(false);
		anchor_4.setTarget("_blank");
		anchor_4.setHref("http://us.battle.net/d3/en/item/strongarm-bracers");

		strongarm = new SimpleCheckBox();
		flexTable_1.setWidget(18, 1, strongarm);

		Label label_5 = new Label("Percent:");
		flexTable_1.setWidget(18, 2, label_5);

		strongarmPercent = new NumberSpinner();
		flexTable_1.setWidget(18, 3, strongarmPercent);
		strongarmPercent.setVisibleLength(4);
		strongarmPercent.setText("30");
		strongarmPercent.setMin(20);
		strongarmPercent.setMax(30);

		Label label_6 = new Label("Uptime:");
		flexTable_1.setWidget(19, 2, label_6);

		strongarmUptime = new NumberSpinner();
		flexTable_1.setWidget(19, 3, strongarmUptime);
		strongarmUptime.setVisibleLength(4);
		strongarmUptime.setText("30");
		strongarmUptime.setMax(100);

		Anchor anchor_9 = new Anchor("Bombadier's Rucksack");
		flexTable_1.setWidget(20, 0, anchor_9);
		anchor_9.setText("Reaper's Wraps");
		anchor_9.setHTML("Reaper's Wraps");
		anchor_9.setWordWrap(false);
		anchor_9.setTarget("_blank");
		anchor_9.setHref("http://us.battle.net/d3/en/artisan/blacksmith/recipe/reapers-wraps");

		reapersWraps = new SimpleCheckBox();
		flexTable_1.setWidget(20, 1, reapersWraps);

		Label label_11 = new Label("Percent:");
		flexTable_1.setWidget(20, 2, label_11);

		reapersWrapsPercent = new NumberSpinner();
		flexTable_1.setWidget(20, 3, reapersWrapsPercent);
		reapersWrapsPercent.setVisibleLength(4);
		reapersWrapsPercent.setText("25");
		reapersWrapsPercent.setMin(25);
		reapersWrapsPercent.setMax(30);

		Label lblSets = new Label("Sets");
		lblSets.setWordWrap(false);
		flexTable_1.setWidget(21, 0, lblSets);
		lblSets.addStyleName("boldText");

		Anchor labelM = new Anchor();
		flexTable_1.setWidget(22, 0, labelM);
		labelM.setText("Marauder's Set:");
		labelM.setWordWrap(false);
		labelM.setTarget("_blank");
		labelM.setHref("http://us.battle.net/d3/en/item/marauders-visage");

		Label lblPieces = new Label("# Pieces:");
		lblPieces.setWordWrap(false);
		flexTable_1.setWidget(22, 2, lblPieces);

		marauders = new NumberSpinner();
		flexTable_1.setWidget(22, 3, marauders);
		marauders.setVisibleLength(4);
		marauders.setText("6");
		marauders.setMin(0);
		marauders.setMax(6);

		Anchor anchor_17 = new Anchor();
		flexTable_1.setWidget(23, 0, anchor_17);
		anchor_17.setWordWrap(false);
		anchor_17.setText("Unhallowed Essence Set");
		anchor_17.setTarget("_blank");
		anchor_17.setHref("http://us.battle.net/d3/en/item/accursed-visage");

		Label label_17 = new Label("# Pieces:");
		label_17.setWordWrap(false);
		flexTable_1.setWidget(23, 2, label_17);

		numUe = new NumberSpinner();
		flexTable_1.setWidget(23, 3, numUe);
		numUe.setVisibleLength(4);
		numUe.setMax(6);
		numUe.setText("0");

		Anchor anchor_14 = new Anchor();
		flexTable_1.setWidget(24, 0, anchor_14);
		anchor_14.setWordWrap(false);
		anchor_14.setText("Natalya's Set");
		anchor_14.setTarget("_blank");
		anchor_14.setHref("http://us.battle.net/d3/en/item/natalyas-slayer");

		Label label_16 = new Label("# Pieces:");
		label_16.setWordWrap(false);
		flexTable_1.setWidget(24, 2, label_16);

		numNats = new NumberSpinner();
		flexTable_1.setWidget(24, 3, numNats);
		numNats.setVisibleLength(4);
		numNats.setText("0");
		numNats.setMax(6);

		Anchor anchor_15 = new Anchor();
		flexTable_1.setWidget(25, 0, anchor_15);
		anchor_15.setWordWrap(false);
		anchor_15.setText("Bastions of Will");
		anchor_15.setTarget("_blank");
		anchor_15.setHref("http://us.battle.net/d3/en/item/focus");

		bastions = new SimpleCheckBox();
		flexTable_1.setWidget(25, 1, bastions);

		Label lblPants = new Label("Pants");
		lblPants.setWordWrap(false);
		flexTable_1.setWidget(26, 0, lblPants);
		lblPants.addStyleName("boldText");

		Anchor anchor_3 = new Anchor("Hexing Pants");
		flexTable_1.setWidget(27, 0, anchor_3);
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/item/hexing-pants-of-mr-yan");

		hexingPants = new SimpleCheckBox();
		flexTable_1.setWidget(27, 1, hexingPants);

		Label label_3 = new Label("Percent:");
		flexTable_1.setWidget(27, 2, label_3);

		hexingPantsPercent = new NumberSpinner();
		flexTable_1.setWidget(27, 3, hexingPantsPercent);
		hexingPantsPercent.setVisibleLength(4);
		hexingPantsPercent.setText("20");
		hexingPantsPercent.setMin(20);
		hexingPantsPercent.setMax(25);

		Label label_4 = new Label("Percent of time Moving:");
		flexTable_1.setWidget(28, 0, label_4);

		hexingPantsUptime = new NumberSpinner();
		flexTable_1.setWidget(28, 1, hexingPantsUptime);
		hexingPantsUptime.setVisibleLength(4);
		hexingPantsUptime.setText("20");
		hexingPantsUptime.setMax(100);

		Label lblJewelry = new Label("Jewelry");
		lblJewelry.setWordWrap(false);
		flexTable_1.setWidget(29, 0, lblJewelry);
		lblJewelry.addStyleName("boldText");

		Anchor anchor_13 = new Anchor("Haunt of Vaxo");
		flexTable_1.setWidget(30, 0, anchor_13);
		anchor_13.setWordWrap(false);
		anchor_13.setTarget("_blank");
		anchor_13.setHref("http://us.battle.net/d3/en/item/haunt-of-vaxo");

		vaxo = new SimpleCheckBox();
		flexTable_1.setWidget(30, 1, vaxo);
		
		Label label_20 = new Label("Uptime:");
		flexTable_1.setWidget(30, 2, label_20);
		
		vaxoUptime = new NumberSpinner();
		vaxoUptime.setVisibleLength(4);
		vaxoUptime.setText("30");
		flexTable_1.setWidget(30, 3, vaxoUptime);
		vaxoUptime.setMax(100);
		
		Anchor anchor_19 = new Anchor("Convention of Elements");
		anchor_19.setWordWrap(false);
		anchor_19.setTarget("_blank");
		anchor_19.setHref("http://us.battle.net/d3/en/item/convention-of-elements");
		flexTable_1.setWidget(31, 0, anchor_19);
		
		coe = new SimpleCheckBox();
		flexTable_1.setWidget(31, 1, coe);
		
		Label label_21 = new Label("Percent:");
		flexTable_1.setWidget(31, 2, label_21);
		
		coePercent = new NumberSpinner();
		coePercent.setVisibleLength(4);
		coePercent.setText("150");
		flexTable_1.setWidget(31, 3, coePercent);
		coePercent.setMin(150);
		coePercent.setMax(200);
		
		Label lblChestArmor = new Label("Chest Armor");
		lblChestArmor.setWordWrap(false);
		flexTable_1.setWidget(32, 0, lblChestArmor);
		lblChestArmor.addStyleName("boldText");

		Anchor anchor_10 = new Anchor("Bombadier's Rucksack");
		flexTable_1.setWidget(33, 0, anchor_10);
		anchor_10.setWordWrap(false);
		anchor_10.setText("Cindercoat");
		anchor_10.setTarget("_blank");
		anchor_10.setHTML("Cindercoat");
		anchor_10.setHref("http://us.battle.net/d3/en/item/cindercoat");

		cindercoat = new SimpleCheckBox();
		flexTable_1.setWidget(33, 1, cindercoat);

		Label lblRcrPercent = new Label("RCR Percent:");
		lblRcrPercent.setWordWrap(false);
		flexTable_1.setWidget(33, 2, lblRcrPercent);

		cindercoatPercent = new NumberSpinner();
		flexTable_1.setWidget(33, 3, cindercoatPercent);
		cindercoatPercent.setVisibleLength(4);
		cindercoatPercent.setText("25");
		cindercoatPercent.setMin(23);
		cindercoatPercent.setMax(30);

		Label lblGeneral = new Label("General");
		lblGeneral.setWordWrap(false);
		flexTable_1.setWidget(34, 0, lblGeneral);
		lblGeneral.addStyleName("boldText");

		Label label_15 = new Label("Area Damage +%:");
		flexTable_1.setWidget(35, 0, label_15);
		label_15.setWordWrap(false);
		label_15.setTitle("The Area Damage percentage from Equipment only.");

		areaDamageEquipment = new NumberSpinner();
		flexTable_1.setWidget(35, 1, areaDamageEquipment);
		areaDamageEquipment.setVisibleLength(4);
		areaDamageEquipment.setText("0");
		areaDamageEquipment
				.setTitle("The Area Damage percentage from Equipment only.");

		Label label_1 = new Label("Elite Damage +%:");
		flexTable_1.setWidget(36, 0, label_1);
		label_1.setWordWrap(false);
		label_1.setTitle("The Elite Damage percentage, as shown in-in game in the character details screen under offense. Subtract 15% if Bane of the Powerful is level 25 or more (it will be added automatically if selected).");

		eliteDamagePercent = new NumberSpinner();
		flexTable_1.setWidget(36, 1, eliteDamagePercent);
		eliteDamagePercent.setVisibleLength(4);
		eliteDamagePercent
				.setTitle("The Elite Damage percentage, as shown in-in game in the character details screen under offense. Subtract 15% if Bane of the Powerful is level 25 or more (it will be added automatically if selected).");

		Label lblNoteSubtract = new Label(
				"Note: subtract 15% from the value displayed in-game if Bane of the Powerful is rank 25 or more.");
		flexTable_1.setWidget(37, 0, lblNoteSubtract);
		lblNoteSubtract.setStyleName("boldText");
		lblNoteSubtract.setWidth("300px");
		flexTable_1.getFlexCellFormatter().setColSpan(28, 0, 3);
		flexTable_1.getFlexCellFormatter().setColSpan(37, 0, 4);
		flexTable_1.getFlexCellFormatter().setColSpan(36, 0, 3);
		flexTable_1.getFlexCellFormatter().setColSpan(35, 0, 3);
		calamityUptime.setMax(100);
		FlexTableHelper.fixRowSpan(flexTable_1);
		crashingRainPercent.setMin(3000);
		crashingRainPercent.setMax(4000);
		harringtonUptime.setMax(100);
		harringtonPercent.setMin(100);
		harringtonPercent.setMax(135);
	}

	public SimpleCheckBox getTnt() {
		return tnt;
	}

	public SimpleCheckBox getCalamity() {
		return calamity;
	}

	public NumberSpinner getTntPercent() {
		return tntPercent;
	}

	public NumberSpinner getEliteDamagePercent() {
		return eliteDamagePercent;
	}

	public SimpleCheckBox getMeticulousBolts() {
		return meticulousBolts;
	}

	public NumberSpinner getMeticulousBoltsPercent() {
		return meticulousBoltsPercent;
	}

	public NumberSpinner getCalamityUptime() {
		return calamityUptime;
	}

	public SimpleCheckBox getHexingPants() {
		return hexingPants;
	}

	public NumberSpinner getHexingPantsUptime() {
		return hexingPantsUptime;
	}

	public SimpleCheckBox getStrongarm() {
		return strongarm;
	}

	public SimpleCheckBox getHarrington() {
		return harrington;
	}

	public NumberSpinner getHarringtonPercent() {
		return harringtonPercent;
	}

	public NumberSpinner getHarringtonUptime() {
		return harringtonUptime;
	}

	public NumberSpinner getStrongarmPercent() {
		return strongarmPercent;
	}

	public NumberSpinner getStrongarmUptime() {
		return strongarmUptime;
	}

	public NumberSpinner getHexingPantsPercent() {
		return hexingPantsPercent;
	}

	public SimpleCheckBox getBombadiers() {
		return bombadiers;
	}

	public NumberSpinner getMarauders() {
		return marauders;
	}

	public SimpleCheckBox getSpines() {
		return spines;
	}

	public SimpleCheckBox getKridershot() {
		return kridershot;
	}

	public NumberSpinner getSpinesHatred() {
		return spinesHatred;
	}

	public NumberSpinner getKridershotHatred() {
		return kridershotHatred;
	}

	public SimpleCheckBox getReapersWraps() {
		return reapersWraps;
	}

	public NumberSpinner getReapersWrapsPercent() {
		return reapersWrapsPercent;
	}

	public SimpleCheckBox getCindercoat() {
		return cindercoat;
	}

	public NumberSpinner getCindercoatPercent() {
		return cindercoatPercent;
	}

	public SimpleCheckBox getOdysseysEnd() {
		return odysseysEnd;
	}

	public NumberSpinner getOdysseysEndPercent() {
		return odysseysEndPercent;
	}

	public NumberSpinner getOdysseysEndUptime() {
		return odysseysEndUptime;
	}

	public SimpleCheckBox getHelltrapper() {
		return helltrapper;
	}

	public NumberSpinner getHelltrapperPercent() {
		return helltrapperPercent;
	}

	public SimpleCheckBox getVaxo() {
		return vaxo;
	}

	public NumberSpinner getAreaDamageEquipment() {
		return areaDamageEquipment;
	}

	public NumberSpinner getNumNats() {
		return numNats;
	}

	public SimpleCheckBox getBastions() {
		return bastions;
	}

	public SimpleCheckBox getCrashingRain() {
		return crashingRain;
	}

	public NumberSpinner getNumUe() {
		return numUe;
	}

	public NumberSpinner getCrashingRainPercent() {
		return crashingRainPercent;
	}

	public SimpleCheckBox getDml() {
		return dml;
	}

	public NumberSpinner getDmlPercent() {
		return dmlPercent;
	}

	public NumberSpinner getVaxoUptime() {
		return vaxoUptime;
	}

	public NumberSpinner getCoePercent() {
		return coePercent;
	}

	public SimpleCheckBox getCoe() {
		return coe;
	}

}
