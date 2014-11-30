package com.dawg6.web.sentry.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class BuffPanel extends Composite {
	private final SimpleCheckBox focusedMind;
	private final SimpleCheckBox anatomy;
	private final SimpleCheckBox hysteria;
	public BuffPanel() {
		
		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Follower Buffs");
		initWidget(cptnpnlNewPanel);
		
		FlexTable flexTable = new FlexTable();
		cptnpnlNewPanel.setContentWidget(flexTable);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(5);
		flexTable.setWidget(0, 0, horizontalPanel);
		
		Label lblNewLabel = new Label("Enchantress");
		horizontalPanel.add(lblNewLabel);
		
		Anchor anchor = new Anchor("Focused Mind:");
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/follower/enchantress/skill/focused-mind");
		horizontalPanel.add(anchor);
		
		Label lblNewLabel_1 = new Label((String) null);
		horizontalPanel.add(lblNewLabel_1);
		
		focusedMind = new SimpleCheckBox();
		flexTable.setWidget(0, 1, focusedMind);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_1.setSpacing(5);
		flexTable.setWidget(1, 0, horizontalPanel_1);
		
		Label lblScoundrel = new Label("Scoundrel");
		horizontalPanel_1.add(lblScoundrel);
		
		Anchor anchor_1 = new Anchor("Anatomy:");
		anchor_1.setTarget("_blank");
		anchor_1.setHref("http://us.battle.net/d3/en/follower/scoundrel/skill/anatomy");
		horizontalPanel_1.add(anchor_1);
		
		anatomy = new SimpleCheckBox();
		flexTable.setWidget(1, 1, anatomy);
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel_2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_2.setSpacing(5);
		flexTable.setWidget(2, 0, horizontalPanel_2);
		
		Label label = new Label("Scoundrel");
		horizontalPanel_2.add(label);
		
		Anchor anchor_2 = new Anchor("Hysteria:");
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/follower/scoundrel/skill/hysteria");
		horizontalPanel_2.add(anchor_2);
		
		hysteria = new SimpleCheckBox();
		flexTable.setWidget(2, 1, hysteria);
	}
	public SimpleCheckBox getFocusedMind() {
		return focusedMind;
	}
	public SimpleCheckBox getAnatomy() {
		return anatomy;
	}
	public SimpleCheckBox getHysteria() {
		return hysteria;
	}
}
