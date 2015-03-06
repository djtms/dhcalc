package com.dawg6.web.sentry.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class PassivesPanel extends Composite {
	private final SimpleCheckBox ballistics;
	private final SimpleCheckBox ctw;
	private final SimpleCheckBox grenadier;
	private final SimpleCheckBox singleOut;
	private final SimpleCheckBox ambush;
	private final SimpleCheckBox steadyAim;
	private final SimpleCheckBox archery;
	private final SimpleCheckBox customEngineering;
	
	public PassivesPanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Passives");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		Anchor anchor = new Anchor("Ballistics:");
		anchor.setWordWrap(false);
		anchor.setTarget("_blank");
		anchor.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/ballistics");
		flexTable.setWidget(0, 0, anchor);
		
		ballistics = new SimpleCheckBox();
		flexTable.setWidget(0, 1, ballistics);
		
		Anchor anchor_1 = new Anchor("Archery:");
		anchor_1.setWordWrap(false);
		anchor_1.setText("Steady Aim:");
		anchor_1.setTarget("_blank");
		anchor_1.setHTML("Steady Aim:");
		anchor_1.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/steady-aim");
		flexTable.setWidget(0, 2, anchor_1);
		
		steadyAim = new SimpleCheckBox();
		flexTable.setWidget(0, 3, steadyAim);
		
		Anchor anchor_3 = new Anchor("Cull the Weak:");
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/cull-the-weak");
		flexTable.setWidget(1, 0, anchor_3);
		
		ctw = new SimpleCheckBox();
		flexTable.setWidget(1, 1, ctw);
		
		Anchor anchor_4 = new Anchor("Ambush:");
		anchor_4.setWordWrap(false);
		anchor_4.setText("Ambush:");
		anchor_4.setTarget("_blank");
		anchor_4.setHTML("Ambush:");
		anchor_4.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/ambush");
		flexTable.setWidget(1, 2, anchor_4);
		
		ambush = new SimpleCheckBox();
		flexTable.setWidget(1, 3, ambush);
		
		Anchor anchor_5 = new Anchor("Grenadier:");
		anchor_5.setWordWrap(false);
		anchor_5.setTarget("_blank");
		anchor_5.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/grenadier");
		flexTable.setWidget(2, 0, anchor_5);
		
		grenadier = new SimpleCheckBox();
		flexTable.setWidget(2, 1, grenadier);
		
		Anchor anchor_6 = new Anchor("Single Out:");
		anchor_6.setWordWrap(false);
		anchor_6.setText("Single Out:");
		anchor_6.setTarget("_blank");
		anchor_6.setHTML("Single Out:");
		anchor_6.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/single-out");
		flexTable.setWidget(2, 2, anchor_6);
		
		singleOut = new SimpleCheckBox();
		flexTable.setWidget(2, 3, singleOut);
		
		Anchor anchor_2 = new Anchor("Archery:");
		anchor_2.setWordWrap(false);
		anchor_2.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/archery");
		flexTable.setWidget(3, 0, anchor_2);
		
		archery = new SimpleCheckBox();
		flexTable.setWidget(3, 1, archery);
		
		Anchor anchor_7 = new Anchor("Custom Engineering:");
		anchor_7.setWordWrap(false);
		anchor_7.setText("Custom Engineering:");
		anchor_7.setTarget("_blank");
		anchor_7.setHTML("Single Out:");
		anchor_7.setHref("http://us.battle.net/d3/en/class/demon-hunter/passive/custom-engineering");
		flexTable.setWidget(3, 2, anchor_7);
		
		customEngineering = new SimpleCheckBox();
		flexTable.setWidget(3, 3, customEngineering);
	}

	public SimpleCheckBox getBallistics() {
		return ballistics;
	}

	public SimpleCheckBox getCtw() {
		return ctw;
	}

	public SimpleCheckBox getGrenadier() {
		return grenadier;
	}

	public SimpleCheckBox getSingleOut() {
		return singleOut;
	}

	public SimpleCheckBox getAmbush() {
		return ambush;
	}

	public SimpleCheckBox getSteadyAim() {
		return steadyAim;
	}

	public SimpleCheckBox getArchery() {
		return archery;
	}

	public SimpleCheckBox getCustomEngineering() {
		return customEngineering;
	}

}
