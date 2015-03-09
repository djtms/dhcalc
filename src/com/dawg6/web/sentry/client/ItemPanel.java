package com.dawg6.web.sentry.client;

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

		Anchor anchor_1 = new Anchor("Calamity (Marked for Death)");
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/item/calamity");
		flexTable.setWidget(8, 0, anchor_1);

		calamity = new SimpleCheckBox();
		flexTable.setWidget(8, 1, calamity);
		
		Label lblUptime = new Label("Uptime:");
		flexTable.setWidget(8, 2, lblUptime);
		
		calamityUptime = new NumberSpinner();
		calamityUptime.setVisibleLength(2);
		calamityUptime.setText("100");
		calamityUptime.setTitle("Percent of the time Calamity's Marked for Death will be applied to targets.");
		flexTable.setWidget(8, 3, calamityUptime);
		
		Anchor labelM = new Anchor("Calamity (Marked for Death)");
		labelM.setHTML("Marauder's Set");
		labelM.setText("Marauder's Set");
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
		
		Anchor anchor_6 = new Anchor("Bombadier's Rucksack");
		anchor_6.setWordWrap(false);
		anchor_6.setTarget("_blank");
		anchor_6.setHref("http://us.battle.net/d3/en/item/bombardiers-rucksack");
		flexTable.setWidget(10, 0, anchor_6);
		
		bombadiers = new SimpleCheckBox();
		flexTable.setWidget(10, 1, bombadiers);
		
		Anchor anchor_7 = new Anchor("Spines of Seething Hatred");
		anchor_7.setWordWrap(false);
		anchor_7.setTarget("_blank");
		anchor_7.setHref("http://us.battle.net/d3/en/item/spines-of-seething-hatred");
		flexTable.setWidget(11, 0, anchor_7);
		
		spines = new SimpleCheckBox();
		flexTable.setWidget(11, 1, spines);
		
		Label label_9 = new Label("Hatred:");
		flexTable.setWidget(11, 2, label_9);
		
		spinesHatred = new NumberSpinner();
		spinesHatred.setVisibleLength(2);
		spinesHatred.setText("6");
		flexTable.setWidget(11, 3, spinesHatred);
		
		Anchor anchor_8 = new Anchor("Kridershot");
		anchor_8.setWordWrap(false);
		anchor_8.setTarget("_blank");
		anchor_8.setHref("http://us.battle.net/d3/en/item/kridershot");
		flexTable.setWidget(12, 0, anchor_8);
		
		kridershot = new SimpleCheckBox();
		flexTable.setWidget(12, 1, kridershot);
		
		Label label_10 = new Label("Hatred:");
		flexTable.setWidget(12, 2, label_10);
		
		kridershotHatred = new NumberSpinner();
		kridershotHatred.setVisibleLength(2);
		kridershotHatred.setText("6");
		flexTable.setWidget(12, 3, kridershotHatred);

		Label label_1 = new Label("Elite Damage +%:");
		label_1.setWordWrap(false);
		label_1.setTitle("The Elite Damage percentage, as shown in-in game in the character details screen under offense. Subtract 15% if Bane of the Powerful is level 25 or more (it will be added automatically if selected).");
		flexTable.setWidget(13, 0, label_1);
		flexTable.getFlexCellFormatter().setColSpan(13, 0, 3);

		eliteDamagePercent = new NumberSpinner();
		eliteDamagePercent.setVisibleLength(2);
		eliteDamagePercent
				.setTitle("The Elite Damage percentage, as shown in-in game in the character details screen under offense. Subtract 15% if Bane of the Powerful is level 25 or more (it will be added automatically if selected).");
		flexTable.setWidget(13, 1, eliteDamagePercent);
		
		Label lblNoteSubtract = new Label("Note: subtract 15% from the value displayed in-game if Bane of the Powerful is rank 25 or more.");
		lblNoteSubtract.setStyleName("boldText");
		lblNoteSubtract.setWidth("300px");
		flexTable.setWidget(14, 0, lblNoteSubtract);
		flexTable.getFlexCellFormatter().setColSpan(14, 0, 4);
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
		marauders.setMin(0);
		marauders.setMax(7);
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

}
