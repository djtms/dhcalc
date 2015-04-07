package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
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
	
	public ItemPanel() {

		CaptionPanel captionPanel = new CaptionPanel("Item Data");
		initWidget(captionPanel);

		FlexTable flexTable = new FlexTable();
		captionPanel.setContentWidget(flexTable);

		Anchor anchor = new Anchor("Tasker and Theo");
		anchor.setWordWrap(false);
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/item/tasker-and-theo");
		anchor.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setWidget(0, 0, anchor);

		tnt = new SimpleCheckBox();
		flexTable.setWidget(0, 1, tnt);

		Label label = new Label("Percent:");
		flexTable.setWidget(0, 2, label);

		tntPercent = new NumberSpinner();
		tntPercent.setVisibleLength(2);
		tntPercent.setText("35");
		flexTable.setWidget(0, 3, tntPercent);
		
		Anchor anchor_2 = new Anchor("Meticulous Bolts");
		anchor_2.setWordWrap(false);
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/item/meticulous-bolts");
		anchor_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setWidget(1, 0, anchor_2);
		
		meticulousBolts = new SimpleCheckBox();
		flexTable.setWidget(1, 1, meticulousBolts);
		
		Label label_2 = new Label("Percent:");
		flexTable.setWidget(1, 2, label_2);
		
		meticulousBoltsPercent = new NumberSpinner();
		meticulousBoltsPercent.setVisibleLength(2);
		meticulousBoltsPercent.setText("30");
		flexTable.setWidget(1, 3, meticulousBoltsPercent);
		
		Anchor anchor_5 = new Anchor("Harrington Waistguard");
		anchor_5.setWordWrap(false);
		anchor_5.setTarget("_blank");
		anchor_5.setHref("http://us.battle.net/d3/en/item/harrington-waistguard");
		flexTable.setWidget(2, 0, anchor_5);
		
		harrington = new SimpleCheckBox();
		flexTable.setWidget(2, 1, harrington);
		
		Label label_7 = new Label("Percent:");
		flexTable.setWidget(2, 2, label_7);
		
		harringtonPercent = new NumberSpinner();
		harringtonPercent.setVisibleLength(2);
		harringtonPercent.setText("30");
		flexTable.setWidget(2, 3, harringtonPercent);
		
		Label label_8 = new Label("Uptime:");
		flexTable.setWidget(3, 2, label_8);
		
		harringtonUptime = new NumberSpinner();
		harringtonUptime.setVisibleLength(2);
		harringtonUptime.setText("30");
		flexTable.setWidget(3, 3, harringtonUptime);
		
		Anchor anchor_4 = new Anchor("Strongarm Bracers");
		anchor_4.setWordWrap(false);
		anchor_4.setTarget("_blank");
		anchor_4.setHref("http://us.battle.net/d3/en/item/strongarm-bracers");
		flexTable.setWidget(4, 0, anchor_4);
		
		strongarm = new SimpleCheckBox();
		flexTable.setWidget(4, 1, strongarm);
		
		Label label_5 = new Label("Percent:");
		flexTable.setWidget(4, 2, label_5);
		
		strongarmPercent = new NumberSpinner();
		strongarmPercent.setVisibleLength(2);
		strongarmPercent.setText("30");
		flexTable.setWidget(4, 3, strongarmPercent);
		
		Label label_6 = new Label("Uptime:");
		flexTable.setWidget(5, 2, label_6);
		
		strongarmUptime = new NumberSpinner();
		strongarmUptime.setVisibleLength(2);
		strongarmUptime.setText("30");
		flexTable.setWidget(5, 3, strongarmUptime);
		
		Anchor anchor_3 = new Anchor("Hexing Pants");
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/item/hexing-pants-of-mr-yan");
		flexTable.setWidget(6, 0, anchor_3);
		
		hexingPants = new SimpleCheckBox();
		flexTable.setWidget(6, 1, hexingPants);
		
		Label label_3 = new Label("Percent:");
		flexTable.setWidget(6, 2, label_3);
		
		hexingPantsPercent = new NumberSpinner();
		hexingPantsPercent.setVisibleLength(2);
		hexingPantsPercent.setText("20");
		flexTable.setWidget(6, 3, hexingPantsPercent);
		
		Label label_4 = new Label("Percent of time Moving:");
		flexTable.setWidget(7, 0, label_4);
		
		hexingPantsUptime = new NumberSpinner();
		hexingPantsUptime.setVisibleLength(2);
		hexingPantsUptime.setText("20");
		flexTable.setWidget(7, 1, hexingPantsUptime);

		Anchor anchor_1 = new Anchor("Calamity");
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/item/calamity");
		flexTable.setWidget(8, 0, anchor_1);

		calamity = new SimpleCheckBox();
		flexTable.setWidget(8, 1, calamity);
		
		Label lblUptime = new Label("MfD Uptime:");
		flexTable.setWidget(8, 2, lblUptime);
		
		calamityUptime = new NumberSpinner();
		calamityUptime.setVisibleLength(2);
		calamityUptime.setText("100");
		calamityUptime.setTitle("Percent of the time Calamity's Marked for Death will be applied to targets.");
		flexTable.setWidget(8, 3, calamityUptime);
		
		Anchor labelM = new Anchor();
		labelM.setText("Marauder's Set:");
		labelM.setWordWrap(false);
		labelM.setTarget("_blank");
		labelM.setHref("http://us.battle.net/d3/en/item/marauders-visage");
		flexTable.setWidget(9, 0, labelM);
		
		Label lblPieces = new Label("# Pieces:");
		flexTable.setWidget(9, 1, lblPieces);
		flexTable.getFlexCellFormatter().setColSpan(9, 1, 2);
		
		marauders = new NumberSpinner();
		marauders.setVisibleLength(2);
		marauders.setText("6");
		flexTable.setWidget(9, 2, marauders);
		
		Anchor anchor_17 = new Anchor();
		anchor_17.setWordWrap(false);
		anchor_17.setText("Unhallowed Essence Set");
		anchor_17.setTarget("_blank");
		anchor_17.setHref("http://us.battle.net/d3/en/item/accursed-visage");
		flexTable.setWidget(10, 0, anchor_17);
		
		Label label_17 = new Label("# Pieces:");
		flexTable.setWidget(10, 1, label_17);
		
		numUe = new NumberSpinner();
		numUe.setVisibleLength(2);
		numUe.setMax(6);
		numUe.setText("0");
		flexTable.setWidget(10, 2, numUe);
		
		Anchor anchor_14 = new Anchor();
		anchor_14.setWordWrap(false);
		anchor_14.setText("Natalya's Set");
		anchor_14.setTarget("_blank");
		anchor_14.setHref("http://us.battle.net/d3/en/item/natalyas-slayer");
		flexTable.setWidget(11, 0, anchor_14);
		
		Label label_16 = new Label("# Pieces:");
		flexTable.setWidget(11, 1, label_16);
		
		numNats = new NumberSpinner();
		numNats.setVisibleLength(2);
		numNats.setText("0");
		flexTable.setWidget(11, 2, numNats);
		
		Anchor anchor_15 = new Anchor();
		anchor_15.setWordWrap(false);
		anchor_15.setText("Bastions of Will");
		anchor_15.setTarget("_blank");
		anchor_15.setHref("http://us.battle.net/d3/en/item/focus");
		flexTable.setWidget(12, 0, anchor_15);
		
		bastions = new SimpleCheckBox();
		flexTable.setWidget(12, 1, bastions);
		
		Anchor anchor_16 = new Anchor();
		anchor_16.setWordWrap(false);
		anchor_16.setText("Crashing Rain");
		anchor_16.setTarget("_blank");
		anchor_16.setHref(ActiveSkill.CR.getUrl());
		flexTable.setWidget(13, 0, anchor_16);
		
		crashingRain = new SimpleCheckBox();
		flexTable.setWidget(13, 1, crashingRain);
		
		Anchor anchor_6 = new Anchor("Bombadier's Rucksack");
		anchor_6.setWordWrap(false);
		anchor_6.setTarget("_blank");
		anchor_6.setHref("http://us.battle.net/d3/en/item/bombardiers-rucksack");
		flexTable.setWidget(14, 0, anchor_6);
		
		bombadiers = new SimpleCheckBox();
		flexTable.setWidget(14, 1, bombadiers);
		
		Anchor anchor_13 = new Anchor("Haunt of Vaxo");
		anchor_13.setWordWrap(false);
		anchor_13.setTarget("_blank");
		anchor_13.setHref("http://us.battle.net/d3/en/item/haunt-of-vaxo");
		flexTable.setWidget(15, 0, anchor_13);
		
		vaxo = new SimpleCheckBox();
		flexTable.setWidget(15, 1, vaxo);
		
		Anchor anchor_7 = new Anchor("Spines of Seething Hatred");
		anchor_7.setWordWrap(false);
		anchor_7.setTarget("_blank");
		anchor_7.setHref("http://us.battle.net/d3/en/item/spines-of-seething-hatred");
		flexTable.setWidget(16, 0, anchor_7);
		
		spines = new SimpleCheckBox();
		flexTable.setWidget(16, 1, spines);
		
		Label label_9 = new Label("Hatred:");
		flexTable.setWidget(16, 2, label_9);
		
		spinesHatred = new NumberSpinner();
		spinesHatred.setVisibleLength(2);
		spinesHatred.setText("6");
		flexTable.setWidget(16, 3, spinesHatred);
		
		Anchor anchor_8 = new Anchor("Kridershot");
		anchor_8.setWordWrap(false);
		anchor_8.setTarget("_blank");
		anchor_8.setHref("http://us.battle.net/d3/en/item/kridershot");
		flexTable.setWidget(17, 0, anchor_8);
		
		kridershot = new SimpleCheckBox();
		flexTable.setWidget(17, 1, kridershot);
		
		Label label_10 = new Label("Hatred:");
		flexTable.setWidget(17, 2, label_10);
		
		kridershotHatred = new NumberSpinner();
		kridershotHatred.setVisibleLength(2);
		kridershotHatred.setText("6");
		flexTable.setWidget(17, 3, kridershotHatred);
		
		Anchor anchor_11 = new Anchor("Kridershot");
		anchor_11.setText("Odyssey's End");
		anchor_11.setHTML("Odyssey's End");
		anchor_11.setWordWrap(false);
		anchor_11.setTarget("_blank");
		anchor_11.setHref("http://us.battle.net/d3/en/item/odysseys-end");
		flexTable.setWidget(18, 0, anchor_11);
		
		odysseysEnd = new SimpleCheckBox();
		flexTable.setWidget(18, 1, odysseysEnd);
		
		Label label_13 = new Label("Percent:");
		flexTable.setWidget(18, 2, label_13);
		
		odysseysEndPercent = new NumberSpinner();
		odysseysEndPercent.setVisibleLength(2);
		odysseysEndPercent.setText("30");
		flexTable.setWidget(18, 3, odysseysEndPercent);
		
		Label label_14 = new Label("Uptime:");
		flexTable.setWidget(19, 2, label_14);
		
		odysseysEndUptime = new NumberSpinner();
		odysseysEndUptime.setVisibleLength(2);
		odysseysEndUptime.setTitle("Percent of the time Odyssey's End debuff will be applied to targets.");
		odysseysEndUptime.setText("100");
		flexTable.setWidget(19, 3, odysseysEndUptime);
		
		Anchor anchor_9 = new Anchor("Bombadier's Rucksack");
		anchor_9.setText("Reaper's Wraps");
		anchor_9.setHTML("Reaper's Wraps");
		anchor_9.setWordWrap(false);
		anchor_9.setTarget("_blank");
		anchor_9.setHref("http://us.battle.net/d3/en/artisan/blacksmith/recipe/reapers-wraps");
		flexTable.setWidget(20, 0, anchor_9);
		
		reapersWraps = new SimpleCheckBox();
		flexTable.setWidget(20, 1, reapersWraps);
		
		Label label_11 = new Label("Percent:");
		flexTable.setWidget(20, 2, label_11);
		
		reapersWrapsPercent = new NumberSpinner();
		reapersWrapsPercent.setVisibleLength(2);
		reapersWrapsPercent.setText("25");
		flexTable.setWidget(20, 3, reapersWrapsPercent);
		
		Anchor anchor_10 = new Anchor("Bombadier's Rucksack");
		anchor_10.setWordWrap(false);
		anchor_10.setText("Cindercoat");
		anchor_10.setTarget("_blank");
		anchor_10.setHTML("Cindercoat");
		anchor_10.setHref("http://us.battle.net/d3/en/item/cindercoat");
		flexTable.setWidget(21, 0, anchor_10);
		
		cindercoat = new SimpleCheckBox();
		flexTable.setWidget(21, 1, cindercoat);
		
		Label lblRcrPercent = new Label("RCR Percent:");
		flexTable.setWidget(21, 2, lblRcrPercent);
		
		cindercoatPercent = new NumberSpinner();
		cindercoatPercent.setVisibleLength(2);
		cindercoatPercent.setText("25");
		flexTable.setWidget(21, 3, cindercoatPercent);
		
		Anchor anchor_12 = new Anchor("Helltrapper");
		anchor_12.setWordWrap(false);
		anchor_12.setText("Helltrapper");
		anchor_12.setTarget("_blank");
		anchor_12.setHTML("Helltrapper");
		anchor_12.setHref("http://us.battle.net/d3/en/item/helltrapper-3tfdaj");
		flexTable.setWidget(22, 0, anchor_12);
		
		helltrapper = new SimpleCheckBox();
		flexTable.setWidget(22, 1, helltrapper);
		
		Label label_12 = new Label("Percent:");
		flexTable.setWidget(22, 2, label_12);
		
		helltrapperPercent = new NumberSpinner();
		helltrapperPercent.setVisibleLength(2);
		helltrapperPercent.setText("25");
		flexTable.setWidget(22, 3, helltrapperPercent);
		
		Label label_15 = new Label("Area Damage +%:");
		label_15.setWordWrap(false);
		label_15.setTitle("The Area Damage percentage from Equipment only.");
		flexTable.setWidget(23, 0, label_15);
		
		areaDamageEquipment = new NumberSpinner();
		areaDamageEquipment.setVisibleLength(2);
		areaDamageEquipment.setText("0");
		areaDamageEquipment.setTitle("The Area Damage percentage from Equipment only.");
		flexTable.setWidget(23, 1, areaDamageEquipment);

		Label label_1 = new Label("Elite Damage +%:");
		label_1.setWordWrap(false);
		label_1.setTitle("The Elite Damage percentage, as shown in-in game in the character details screen under offense. Subtract 15% if Bane of the Powerful is level 25 or more (it will be added automatically if selected).");
		flexTable.setWidget(24, 0, label_1);
		flexTable.getFlexCellFormatter().setColSpan(24, 0, 3);

		eliteDamagePercent = new NumberSpinner();
		eliteDamagePercent.setVisibleLength(2);
		eliteDamagePercent
				.setTitle("The Elite Damage percentage, as shown in-in game in the character details screen under offense. Subtract 15% if Bane of the Powerful is level 25 or more (it will be added automatically if selected).");
		flexTable.setWidget(24, 1, eliteDamagePercent);
		
		Label lblNoteSubtract = new Label("Note: subtract 15% from the value displayed in-game if Bane of the Powerful is rank 25 or more.");
		lblNoteSubtract.setStyleName("boldText");
		lblNoteSubtract.setWidth("300px");
		flexTable.setWidget(25, 0, lblNoteSubtract);
		flexTable.getFlexCellFormatter().setColSpan(25, 0, 4);
		flexTable.getFlexCellFormatter().setColSpan(7, 0, 3);
		flexTable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(6, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(8, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(16, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(17, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(18, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(19, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(20, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(21, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(22, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getFlexCellFormatter().setColSpan(23, 0, 3);
		flexTable.getFlexCellFormatter().setColSpan(11, 1, 2);
		flexTable.getCellFormatter().setHorizontalAlignment(11, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(10, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getFlexCellFormatter().setColSpan(10, 1, 2);
		
		this.tntPercent.setMin(40);
		this.tntPercent.setMax(50);
		meticulousBoltsPercent.setMin(30);
		meticulousBoltsPercent.setMax(40);
		calamityUptime.setMax(100);
		hexingPantsUptime.setMax(100);
		harringtonUptime.setMax(100);
		strongarmUptime.setMax(100);
		hexingPantsPercent.setMin(20);
		hexingPantsPercent.setMax(25);
		strongarmPercent.setMin(20);
		strongarmPercent.setMax(30);
		harringtonPercent.setMin(100);
		harringtonPercent.setMax(135);
		reapersWrapsPercent.setMin(25);
		reapersWrapsPercent.setMax(30);
		cindercoatPercent.setMin(23);
		cindercoatPercent.setMax(30);
		kridershotHatred.setMin(3);
		kridershotHatred.setMax(4);
		spinesHatred.setMin(3);
		spinesHatred.setMax(4);
		marauders.setMin(0);
		marauders.setMax(6);
		numNats.setMax(6);
		odysseysEndPercent.setMin(20);
		odysseysEndPercent.setMax(25);
		odysseysEndUptime.setMin(0);
		odysseysEndUptime.setMax(100);
		helltrapperPercent.setMin(7);
		helltrapperPercent.setMax(10);
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

}
