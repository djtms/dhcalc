package com.dawg6.web.sentry.client;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.DamageFunction;
import com.dawg6.web.sentry.shared.calculator.DamageMultiplier;
import com.dawg6.web.sentry.shared.calculator.DamageRow;
import com.dawg6.web.sentry.shared.calculator.GemSkill;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SkillData extends ApplicationPanel {
	private final FlexTable table;
	private final ListBox skills;

	public SkillData() {

		VerticalPanel panel = new VerticalPanel();
		initWidget(panel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSpacing(5);
		panel.add(horizontalPanel);
		
		Label lblNewLabel = new Label("Skill:");
		lblNewLabel.setStyleName("boldText");
		horizontalPanel.add(lblNewLabel);
		
		skills = new ListBox();
		horizontalPanel.add(skills);
		
		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Skill Damage");
		panel.add(cptnpnlNewPanel);
		
		table = new FlexTable();
		cptnpnlNewPanel.setContentWidget(table);
		table.setSize("5cm", "3cm");
		table.setStyleName("breakpointTable");
		table.setCellPadding(5);
		table.setBorderWidth(1);
		
		table.getRowFormatter().addStyleName(0, "headerRow");
		
		Label lblSkill = new Label("Skill");
		lblSkill.setWordWrap(false);
		table.setWidget(0, 0, lblSkill);
		
		Label lblRune = new Label("Rune");
		lblRune.setWordWrap(false);
		table.setWidget(0, 1, lblRune);
		
		Label lblType = new Label("Damage Type");
		lblType.setWordWrap(false);
		table.setWidget(0, 2, lblType);
		
		Label lblMultiplier = new Label("Multiplier");
		lblMultiplier.setWordWrap(false);
		table.setWidget(0, 3, lblMultiplier);
		
		Label lblPrimary = new Label("Primary");
		lblPrimary.setWordWrap(false);
		table.setWidget(0, 4, lblPrimary);
		
		Label lblAdditional = new Label("# Additional");
		lblAdditional.setWordWrap(false);
		table.setWidget(0, 5, lblAdditional);
		
		Label lblProjectiles = new Label("# Projectiles");
		lblProjectiles.setWordWrap(false);
		table.setWidget(0, 6, lblProjectiles);
		
		Label lblAoeRadius = new Label("AoE Radius");
		lblAoeRadius.setWordWrap(false);
		table.setWidget(0, 7, lblAoeRadius);
		
		Label lblGrenades = new Label("Grenades");
		lblGrenades.setWordWrap(false);
		table.setWidget(0, 8, lblGrenades);
		
		Label lblRockets = new Label("Rockets");
		lblRockets.setWordWrap(false);
		table.setWidget(0, 9, lblRockets);
		
		Label lblDot = new Label(" DoT ");
		lblDot.setWordWrap(false);
		table.setWidget(0, 10, lblDot);
		
		Label lblNotes = new Label("Notes");
		lblNotes.setWordWrap(false);
		lblNotes.addStyleName("notes");
		table.setWidget(0, 11, lblNotes);
		
		for (ActiveSkill s : ActiveSkill.values()) {
			skills.addItem(s.getLongName(), s.name());
		}
		
		for (GemSkill g : GemSkill.values()) {
			skills.addItem(g.getDisplayName(), g.name());
		}
		
		skills.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				skillChanged();
			}});
		
		skills.setSelectedIndex(0);

		skillChanged();
	}

	protected void skillChanged() {
		
		int index = skills.getSelectedIndex();
		
		while (table.getRowCount() > 1)
			table.removeRow(table.getRowCount()-1);
		
		if (index < 0)
			return;

		String value = skills.getValue(index);
		
		ActiveSkill skill = null;
		
		try { skill = ActiveSkill.valueOf(value); } catch (Exception i1) { }
		
		GemSkill gem = null;
		
		try { gem = GemSkill.valueOf(value); } catch (Exception i2) { }
		
		if ((skill == null) && (gem == null))
			return;
		
		int row = 1;
		for (DamageRow dr : DamageFunction.ALL) {

			boolean match = false;
			
			if (dr.source.skill != null)
				match = (dr.source.skill == skill);
			else
				match = (dr.source.gem == gem);
				
			if (match) {
				if ((row % 2) == 0)
					table.getRowFormatter().addStyleName(row, "evenRow");
				else
					table.getRowFormatter().addStyleName(row, "oddRow");
				
				String url = (skill != null) ? skill.getUrl() : gem.getUrl();
				
				addAnchor(row, 0, (skill != null) ? skill.getLongName() : gem.getDisplayName(), url);

				Rune rune = Rune.None;
				
				if (dr.source.rune != null)
					rune = dr.source.rune;
				
				if (rune != Rune.None)
					url += ("#" + rune.getSlug() + "+");
				
				if (skill != null)
					addAnchor(row, 1, rune.getLongName(), url);
				else
					addLabel(row, 1, "N/A");

				addLabel(row, 2, dr.type.name());
				addLabel(row, 3, String.valueOf(Math.round(dr.scalar * 100.0)) + "%");
				addLabel(row, 4, dr.primary?"Yes":"");
				addLabel(row, 5, dr.maxAdditional > 20 ? "UNLIMITED" : dr.maxAdditional > 0 ? String.valueOf(dr.maxAdditional) : "");
				addLabel(row, 6, ((dr.numProjectiles > 20) || (dr.numProjectiles < 1)) ? "N/A" : String.valueOf(dr.numProjectiles));
				addLabel(row, 7, (dr.radius > 0) ? String.valueOf(dr.radius) : "");
				addLabel(row, 8, dr.multipliers.contains(DamageMultiplier.Grenades)?"Yes":"");
				addLabel(row, 9, dr.multipliers.contains(DamageMultiplier.Rockets)?"Yes":"");
				addLabel(row, 10, dr.multipliers.contains(DamageMultiplier.DoT)?"Yes":"");
				addLabel(row, 11, dr.note, "notes");
				
				row++;
			}
		}
	}

	private void addLabel(int row, int col, String text, String... styles) {
		Label label = new Label(text);
		label.setWordWrap(false);
		
		if (styles != null)
			for (String s : styles)
				label.addStyleName(s);
		
		table.setWidget(row, col, label);
	}

	private void addAnchor(int row, int col, String text, String url) {
		Anchor anchor = new Anchor(text);
		anchor.setTarget("_blank");
		anchor.setHref(url);
		anchor.setWordWrap(false);
		table.setWidget(row, col, anchor);
	}

}
