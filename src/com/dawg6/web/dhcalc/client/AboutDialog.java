package com.dawg6.web.dhcalc.client;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class AboutDialog extends ApplicationPanel {

	private final FlexTable flexTable;
	private final Label versionLabel;

	public AboutDialog() {
		
		flexTable = new FlexTable();
		flexTable.setBorderWidth(0);
		flexTable.setCellPadding(5);
		initWidget(flexTable);
		
		Label lblNewLabel = new Label("DH DPS Calculator");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblNewLabel.setStyleName("boldText");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(0, 0, lblNewLabel);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(5);
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(1, 0, horizontalPanel);
		
		Label lblNewLabel_1 = new Label("Version:");
		horizontalPanel.add(lblNewLabel_1);
		
		versionLabel = new Label(Version.getVersionString());
		horizontalPanel.add(versionLabel);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_1.setSpacing(5);
		horizontalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(3, 0, horizontalPanel_1);
		
		Label lblWritteByDawg = new Label("Written By:");
		horizontalPanel_1.add(lblWritteByDawg);
		
		Anchor label_1 = new Anchor("New label");
		label_1.setTarget("_blank");
		label_1.setText("dawg6");
		label_1.setHref("http://us.battle.net/d3/en/profile/Dawg6-1416/");
		horizontalPanel_1.add(label_1);
		
		Label lblNewLabel_2 = new Label("(");
		horizontalPanel_1.add(lblNewLabel_2);
		
		Anchor anchor_1 = new Anchor("New label");
		anchor_1.setText("scott@dawg6.com");
		anchor_1.setTarget("_blank");
		anchor_1.setHref("mailto:scott@dawg6.com");
		horizontalPanel_1.add(anchor_1);
		
		Label lblNewLabel_3 = new Label(")");
		horizontalPanel_1.add(lblNewLabel_3);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel_2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_2.setSpacing(5);
		horizontalPanel_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(5, 0, horizontalPanel_2);
		
		Label lblRedditThread = new Label("Reddit Thread:");
		horizontalPanel_2.add(lblRedditThread);
		
		Anchor anchor = new Anchor("New label");
		anchor.setTarget("_blank");
		anchor.setText("http://redd.it/2jiynj");
		anchor.setHref("http://redd.it/2jiynj");
		horizontalPanel_2.add(anchor);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
		horizontalPanel_3.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_3.setSpacing(5);
		horizontalPanel_3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(6, 0, horizontalPanel_3);
		
		Label lblBlizzardThread = new Label("Blizzard Thread:");
		horizontalPanel_3.add(lblBlizzardThread);
		
		Anchor anchor_2 = new Anchor("New label");
		anchor_2.setText("http://us.battle.net/d3/en/forum/topic/14926303061");
		anchor_2.setTarget("_blank");
		anchor_2.setHref("http://us.battle.net/d3/en/forum/topic/14926303061");
		horizontalPanel_3.add(anchor_2);
		flexTable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		horizontalPanel_4.setSpacing(5);
		horizontalPanel_4.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(7, 0, horizontalPanel_4);
		
		Anchor anchor_3 = new Anchor("New label");
		anchor_3.setText("Change Log");
		anchor_3.setTarget("_blank");
		anchor_3.setHref("changeLog.txt?v2");
		horizontalPanel_4.add(anchor_3);
		
		Anchor anchor_4 = new Anchor("New label");
		anchor_4.setText("To-do List");
		anchor_4.setTarget("_blank");
		anchor_4.setHref("todo.txt?v2");
		horizontalPanel_4.add(anchor_4);
		flexTable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);

		Anchor anchor_5 = new Anchor("Source Code");
		anchor_5.setTarget("_blank");
		anchor_5.setHref("https://github.com/dawg6/dhcalc");
		horizontalPanel_4.add(anchor_5);
		flexTable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
	}

}
