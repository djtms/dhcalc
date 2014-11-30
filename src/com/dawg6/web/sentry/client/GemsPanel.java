package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.GemSkill;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class GemsPanel extends Composite {
	private final SimpleCheckBox bot;
	private final SimpleCheckBox enforcer;
	private final SimpleCheckBox botp;
	private final SimpleCheckBox zeis;
	private final NumberSpinner botLevel;
	private final NumberSpinner enforcerLevel;
	private final NumberSpinner botpLevel;
	private final NumberSpinner zeisLevel;
	private final SimpleCheckBox gogok;
	private final NumberSpinner gogokLevel;
	private final NumberSpinner gogokStacks;
	private final SimpleCheckBox toxin;
	private final NumberSpinner toxinLevel;
	private final SimpleCheckBox painEnhancer;
	private final NumberSpinner painEnhancerLevel;
	private final NumberSpinner painEnhancerStacks;
	private final SimpleCheckBox taeguk;
	private final NumberSpinner taegukLevel;
	private final NumberSpinner taegukStacks;
	private final NumberSpinner botpUptime;

	public GemsPanel() {

		CaptionPanel captionPanel = new CaptionPanel("Legendary Gems");
		initWidget(captionPanel);

		FlexTable flexTable = new FlexTable();
		captionPanel.setContentWidget(flexTable);

		Anchor anchor = new Anchor("Bane of the Trapped");
		anchor.setWordWrap(false);
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/item/bane-of-the-trapped");
		flexTable.setWidget(0, 0, anchor);

		bot = new SimpleCheckBox();
		flexTable.setWidget(0, 1, bot);

		Label label = new Label("Level:");
		flexTable.setWidget(0, 2, label);

		botLevel = new NumberSpinner();
		botLevel.setVisibleLength(2);
		flexTable.setWidget(0, 3, botLevel);

		Anchor anchor_1 = new Anchor("Enforcer");
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/item/enforcer");
		flexTable.setWidget(1, 0, anchor_1);

		enforcer = new SimpleCheckBox();
		flexTable.setWidget(1, 1, enforcer);

		Label label_1 = new Label("Level:");
		flexTable.setWidget(1, 2, label_1);

		enforcerLevel = new NumberSpinner();
		enforcerLevel.setVisibleLength(2);
		flexTable.setWidget(1, 3, enforcerLevel);

		Anchor anchor_2 = new Anchor("Bane of the Powerful");
		anchor_2.setWordWrap(false);
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/item/bane-of-the-powerful");
		flexTable.setWidget(2, 0, anchor_2);

		botp = new SimpleCheckBox();
		flexTable.setWidget(2, 1, botp);

		Label label_2 = new Label("Level:");
		flexTable.setWidget(2, 2, label_2);

		botpLevel = new NumberSpinner();
		botpLevel.setVisibleLength(2);
		flexTable.setWidget(2, 3, botpLevel);
		
		Label lblUptime = new Label("% Uptime:");
		lblUptime.setWordWrap(false);
		flexTable.setWidget(2, 4, lblUptime);
		
		botpUptime = new NumberSpinner();
		botpUptime.setVisibleLength(2);
		botpUptime.setTitle("% of time BotP's active buff is up.");
		flexTable.setWidget(2, 5, botpUptime);

		Anchor anchor_3 = new Anchor("Zei's Stone of Vengeance");
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/item/zeis-stone-of-vengeance");
		flexTable.setWidget(3, 0, anchor_3);

		zeis = new SimpleCheckBox();
		flexTable.setWidget(3, 1, zeis);

		Label label_3 = new Label("Level:");
		flexTable.setWidget(3, 2, label_3);

		zeisLevel = new NumberSpinner();
		zeisLevel.setVisibleLength(2);
		flexTable.setWidget(3, 3, zeisLevel);
		
		Anchor anchor_5 = new Anchor(GemSkill.Toxin.getDisplayName());
		anchor_5.setWordWrap(false);
		anchor_5.setTarget("_blank");
		anchor_5.setHref(GemSkill.Toxin.getUrl());
		flexTable.setWidget(4, 0, anchor_5);
		
		toxin = new SimpleCheckBox();
		flexTable.setWidget(4, 1, toxin);
		
		Label label_5 = new Label("Level:");
		flexTable.setWidget(4, 2, label_5);
		
		toxinLevel = new NumberSpinner();
		toxinLevel.setVisibleLength(2);
		flexTable.setWidget(4, 3, toxinLevel);
		
		Anchor anchor_6 = new Anchor(GemSkill.PainEnhancer.getDisplayName());
		anchor_6.setWordWrap(false);
		anchor_6.setTarget("_blank");
		anchor_6.setHref(GemSkill.PainEnhancer.getUrl());
		flexTable.setWidget(5, 0, anchor_6);
		
		painEnhancer = new SimpleCheckBox();
		flexTable.setWidget(5, 1, painEnhancer);
		
		Label label_6 = new Label("Level:");
		flexTable.setWidget(5, 2, label_6);
		
		painEnhancerLevel = new NumberSpinner();
		painEnhancerLevel.setVisibleLength(2);
		flexTable.setWidget(5, 3, painEnhancerLevel);
		
		Label lblBleeding = new Label("# Bleeding:");
		lblBleeding.setWordWrap(false);
		flexTable.setWidget(5, 4, lblBleeding);
		
		painEnhancerStacks = new NumberSpinner();
		painEnhancerStacks.setVisibleLength(2);
		painEnhancerStacks.setTitle("# of bleeding enemies within 20 yards");
		flexTable.setWidget(5, 5, painEnhancerStacks);
		
		Anchor anchor_4 = new Anchor("Gogok of Swiftness");
		anchor_4.setWordWrap(false);
		anchor_4.setTarget("_blank");
		anchor_4.setHref("http://us.battle.net/d3/en/item/gogok-of-swiftness");
		flexTable.setWidget(6, 0, anchor_4);
		
		gogok = new SimpleCheckBox();
		flexTable.setWidget(6, 1, gogok);
		
		Label label_4 = new Label("Level:");
		flexTable.setWidget(6, 2, label_4);
		
		gogokLevel = new NumberSpinner();
		gogokLevel.setVisibleLength(2);
		flexTable.setWidget(6, 3, gogokLevel);
		
		Label lblStacks = new Label("# Stacks:");
		lblStacks.setWordWrap(false);
		flexTable.setWidget(6, 4, lblStacks);
		
		gogokStacks = new NumberSpinner();
		gogokStacks.setTitle("Average # of stacks during fight");
		gogokStacks.setVisibleLength(2);
		gogokStacks.setMax(15);
		flexTable.setWidget(6, 5, gogokStacks);
		
		Anchor anchor_7 = new Anchor("Taeguk");
		anchor_7.setWordWrap(false);
		anchor_7.setTarget("_blank");
		anchor_7.setHref("http://us.battle.net/d3/en/item/taeguk");
		flexTable.setWidget(7, 0, anchor_7);
		
		taeguk = new SimpleCheckBox();
		flexTable.setWidget(7, 1, taeguk);
		
		Label label_8 = new Label("Level:");
		flexTable.setWidget(7, 2, label_8);
		
		taegukLevel = new NumberSpinner();
		taegukLevel.setVisibleLength(2);
		flexTable.setWidget(7, 3, taegukLevel);
		
		Label lblStacks_1 = new Label("# Stacks:");
		lblStacks_1.setWordWrap(false);
		flexTable.setWidget(7, 4, lblStacks_1);
		
		taegukStacks = new NumberSpinner();
		taegukStacks.setVisibleLength(2);
		taegukStacks.setTitle("Average # of stacks during fight");
		flexTable.setWidget(7, 5, taegukStacks);
		
		this.botLevel.setMax(100);
		this.enforcerLevel.setMax(100);
		this.botpLevel.setMax(100);
		this.zeisLevel.setMax(100);
		this.gogokLevel.setMax(100);
		this.gogokStacks.setMax(15);
		this.botpUptime.setMax(100);
	}

	public SimpleCheckBox getBot() {
		return bot;
	}

	public SimpleCheckBox getEnforcer() {
		return enforcer;
	}

	public SimpleCheckBox getBotp() {
		return botp;
	}

	public SimpleCheckBox getZeis() {
		return zeis;
	}

	public NumberSpinner getBotLevel() {
		return botLevel;
	}

	public NumberSpinner getEnforcerLevel() {
		return enforcerLevel;
	}

	public NumberSpinner getBotpLevel() {
		return botpLevel;
	}

	public NumberSpinner getZeisLevel() {
		return zeisLevel;
	}

	public SimpleCheckBox getGogok() {
		return gogok;
	}

	public NumberSpinner getGogokLevel() {
		return gogokLevel;
	}

	public NumberSpinner getGogokStacks() {
		return gogokStacks;
	}

	public SimpleCheckBox getToxin() {
		return toxin;
	}

	public NumberSpinner getToxinLevel() {
		return toxinLevel;
	}

	public SimpleCheckBox getPainEnhancer() {
		return painEnhancer;
	}

	public NumberSpinner getPainEnhancerLevel() {
		return painEnhancerLevel;
	}

	public NumberSpinner getPainEnhancerStacks() {
		return painEnhancerStacks;
	}

	public SimpleCheckBox getTaeguk() {
		return taeguk;
	}

	public NumberSpinner getTaegukLevel() {
		return taegukLevel;
	}

	public NumberSpinner getTaegukStacks() {
		return taegukStacks;
	}

	public NumberSpinner getBotpUptime() {
		return botpUptime;
	}

}
