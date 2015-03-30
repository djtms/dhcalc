package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.GemLevel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class CDRPanel extends Composite {
	private final ListBox diamond;
	private final NumberSpinner leoricsLevel;
	private final NumberSpinner shoulders;
	private final NumberSpinner gloves;
	private final NumberSpinner amulet;
	private final NumberSpinner ring1;
	private final NumberSpinner ring2;
	private final SimpleCheckBox crimson;
	private final SimpleCheckBox born;
	private final SimpleCheckBox leorics;
	private final NumberSpinner belt;
	private final NumberSpinner weapon;
	private final NumberSpinner quiver;
	
	public CDRPanel() {
		
		CaptionPanel cptnpnlCooldownReduction = new CaptionPanel("Cooldown Reduction");
		initWidget(cptnpnlCooldownReduction);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		cptnpnlCooldownReduction.setContentWidget(flexTable);
		
		Label lblNewLabel = new Label("Helm (Diamond):");
		lblNewLabel.setStyleName("boldText");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(0, 0, lblNewLabel);
		
		diamond = new ListBox();
		flexTable.setWidget(0, 2, diamond);
		
		HorizontalPanel row = new HorizontalPanel();
		row.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		row.setSpacing(5);
		flexTable.setWidget(1, 0, row);

		Anchor label = new Anchor("Leroic's Crown (Gem %):");
		label.setHTML("Leroic's Crown");
		label.setWordWrap(false);
		label.setTarget("_blank");
		label.setHref("http://us.battle.net/d3/en/item/leorics-crown");
		row.add(label);
		
		Label lblgem = new Label("(Gem %):");
		lblgem.setWordWrap(false);
		lblgem.setStyleName("boldText");
		row.add(lblgem);
		
		leorics = new SimpleCheckBox();
		flexTable.setWidget(1, 1, leorics);
		
		leoricsLevel = new NumberSpinner();
		leoricsLevel.setVisibleLength(4);
		flexTable.setWidget(1, 2, leoricsLevel);
		
		Label lblShoulders = new Label("Shoulders (%):");
		lblShoulders.setWordWrap(false);
		lblShoulders.setStyleName("boldText");
		flexTable.setWidget(2, 0, lblShoulders);
		
		shoulders = new NumberSpinner();
		shoulders.setVisibleLength(4);
		flexTable.setWidget(2, 2, shoulders);
		
		Label lblGloves = new Label("Gloves (%):");
		lblGloves.setWordWrap(false);
		lblGloves.setStyleName("boldText");
		flexTable.setWidget(3, 0, lblGloves);
		
		gloves = new NumberSpinner();
		gloves.setVisibleLength(4);
		flexTable.setWidget(3, 2, gloves);
		
		Label lblAmulet = new Label("Amulet (%):");
		lblAmulet.setWordWrap(false);
		lblAmulet.setStyleName("boldText");
		flexTable.setWidget(4, 0, lblAmulet);
		
		amulet = new NumberSpinner();
		amulet.setVisibleLength(4);
		flexTable.setWidget(4, 2, amulet);
		
		Label lblRing = new Label("Ring 1 (%):");
		lblRing.setWordWrap(false);
		lblRing.setStyleName("boldText");
		flexTable.setWidget(5, 0, lblRing);
		
		ring1 = new NumberSpinner();
		ring1.setVisibleLength(4);
		flexTable.setWidget(5, 2, ring1);
		
		Label lblRing_1 = new Label("Ring 2 (%):");
		lblRing_1.setWordWrap(false);
		lblRing_1.setStyleName("boldText");
		flexTable.setWidget(6, 0, lblRing_1);
		
		ring2 = new NumberSpinner();
		ring2.setVisibleLength(4);
		flexTable.setWidget(6, 2, ring2);
		
		Label lblBelt = new Label("Belt (%):");
		lblBelt.setWordWrap(false);
		lblBelt.setStyleName("boldText");
		flexTable.setWidget(7, 0, lblBelt);
		
		belt = new NumberSpinner();
		belt.setVisibleLength(4);
		flexTable.setWidget(7, 2, belt);
		
		Label lblWeapon = new Label("Weapon (%):");
		lblWeapon.setWordWrap(false);
		lblWeapon.setStyleName("boldText");
		flexTable.setWidget(8, 0, lblWeapon);
		
		weapon = new NumberSpinner();
		weapon.setVisibleLength(4);
		flexTable.setWidget(8, 2, weapon);
		
		Label lblQuiver = new Label("Quiver (%):");
		lblQuiver.setWordWrap(false);
		lblQuiver.setStyleName("boldText");
		flexTable.setWidget(9, 0, lblQuiver);
		
		quiver = new NumberSpinner();
		quiver.setVisibleLength(4);
		flexTable.setWidget(9, 2, quiver);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(5);
		flexTable.setWidget(10, 0, horizontalPanel);
		
		Anchor lblCaptainCrimsonsSet = new Anchor();
		lblCaptainCrimsonsSet.setText("Captain Crimson's Set");
		horizontalPanel.add(lblCaptainCrimsonsSet);
		lblCaptainCrimsonsSet.setWordWrap(false);
		lblCaptainCrimsonsSet.setTarget("_blank");
		lblCaptainCrimsonsSet.setHref("http://us.battle.net/d3/en/artisan/blacksmith/recipe/captain-crimsons-silk-girdle");
		
		Label label_1 = new Label("(2pc = 10%):");
		label_1.setWordWrap(false);
		label_1.setStyleName("boldText");
		horizontalPanel.add(label_1);
		
		crimson = new SimpleCheckBox();
		flexTable.setWidget(10, 1, crimson);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_1.setSpacing(5);
		flexTable.setWidget(11, 0, horizontalPanel_1);
		
		Anchor lblBornsSet = new Anchor("Born's Set (10%):");
		lblBornsSet.setText("Born's Set");
		horizontalPanel_1.add(lblBornsSet);
		lblBornsSet.setWordWrap(false);
		lblBornsSet.setTarget("_blank");
		lblBornsSet.setHref("http://us.battle.net/d3/en/artisan/blacksmith/recipe/borns-frozen-soul");
		
		Label label_2 = new Label("(10%):");
		label_2.setWordWrap(false);
		label_2.setStyleName("boldText");
		horizontalPanel_1.add(label_2);
		
		for (GemLevel l : GemLevel.values()) {
			this.diamond.addItem(l.getDisplayName(), l.name());
		}
		
		diamond.setSelectedIndex(0);
		
		born = new SimpleCheckBox();
		flexTable.setWidget(11, 1, born);
		
		this.leoricsLevel.setMin(75);
		this.leoricsLevel.setMax(100);
		this.shoulders.setMax(8);
		this.gloves.setMax(8);
		this.ring1.setMax(8);
		this.ring2.setMax(8);
		this.belt.setMax(8);
		this.weapon.setMax(10);
		this.quiver.setMax(10);
		this.amulet.setMax(8);
	}
	public ListBox getDiamond() {
		return diamond;
	}
	public NumberSpinner getLeoricsLevel() {
		return leoricsLevel;
	}
	public NumberSpinner getShoulders() {
		return shoulders;
	}
	public NumberSpinner getGloves() {
		return gloves;
	}
	public NumberSpinner getAmulet() {
		return amulet;
	}
	public NumberSpinner getRing1() {
		return ring1;
	}
	public NumberSpinner getRing2() {
		return ring2;
	}
	public SimpleCheckBox getCrimson() {
		return crimson;
	}
	public SimpleCheckBox getBorn() {
		return born;
	}
	public SimpleCheckBox getLeorics() {
		return leorics;
	}
	public NumberSpinner getBelt() {
		return belt;
	}
	public NumberSpinner getWeapon() {
		return weapon;
	}
	public NumberSpinner getQuiver() {
		return quiver;
	}
	public GemLevel getSelectedDiamond() {
		int i = this.diamond.getSelectedIndex();
		String value = this.diamond.getValue(i);
		
		return GemLevel.valueOf(value);
	}
	public void setDiamond(GemLevel diamond) {
		for (int i = 0; i < this.diamond.getItemCount(); i++) {
			String value = this.diamond.getValue(i);
			
			if (value.equals(diamond.name())) {
				this.diamond.setSelectedIndex(i);
				return;
			}
		}
		
		this.diamond.setSelectedIndex(0);
	}

}
