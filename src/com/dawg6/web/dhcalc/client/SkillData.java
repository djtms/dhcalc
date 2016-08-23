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
import java.util.Vector;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.common.util.Pair;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.DamageFunction;
import com.dawg6.web.dhcalc.shared.calculator.DamageMultiplier;
import com.dawg6.web.dhcalc.shared.calculator.DamageProc;
import com.dawg6.web.dhcalc.shared.calculator.DamageRow;
import com.dawg6.web.dhcalc.shared.calculator.GemSkill;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SkillData extends ApplicationPanel {
	private final FlexTable table;
	private final ListBox skills;
	private final CharacterData data = new CharacterData();

	public SkillData() {

		data.setDefaults();
		
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
		
		table = new FlexTable();
		panel.add(table);
		table.setSize("5cm", "3cm");
		table.setStyleName("breakpointTable");
		table.setCellPadding(5);
		table.setBorderWidth(1);
		
		table.getRowFormatter().addStyleName(0, "headerRow");
		
		int col = 0;
		
		Label lblSkill = new Label("Skill");
		lblSkill.setWordWrap(false);
		table.setWidget(0, col++, lblSkill);
		
		Label lblRune = new Label("Rune");
		lblRune.setWordWrap(false);
		table.setWidget(0, col++, lblRune);
		
		Label lblType = new Label("Damage Type");
		lblType.setWordWrap(false);
		table.setWidget(0, col++, lblType);
		
		Label lblMultiplier = new Label("Multiplier");
		lblMultiplier.setWordWrap(false);
		table.setWidget(0, col++, lblMultiplier);
		
		Label frames = new Label("Frames");
		frames.setWordWrap(false);
		table.setWidget(0, col++, frames);

		Label lblPrimary = new Label("Primary");
		lblPrimary.setWordWrap(false);
		table.setWidget(0, col++, lblPrimary);
		
		Label lblAdditional = new Label("# Additional");
		lblAdditional.setWordWrap(false);
		table.setWidget(0, col++, lblAdditional);
		
		Label lblProjectiles = new Label("# Projectiles");
		lblProjectiles.setWordWrap(false);
		table.setWidget(0, col++, lblProjectiles);
		
		Label lblAoeRadius = new Label("AoE Radius");
		lblAoeRadius.setWordWrap(false);
		table.setWidget(0, col++, lblAoeRadius);
		
		Label lblGrenades = new Label("Grenades");
		lblGrenades.setWordWrap(false);
		table.setWidget(0, col++, lblGrenades);
		
		Label lblRockets = new Label("Rockets");
		lblRockets.setWordWrap(false);
		table.setWidget(0, col++, lblRockets);
		
		Label lblDot = new Label(" DoT ");
		lblDot.setWordWrap(false);
		table.setWidget(0, col++, lblDot);
		
		Label lblNotes = new Label("Notes");
		lblNotes.setWordWrap(false);
		lblNotes.addStyleName("notes");
		table.setWidget(0, col++, lblNotes);
		
		List<Pair<String, String>> list = new Vector<Pair<String, String>>();
		
		for (ActiveSkill s : ActiveSkill.values()) {
			
			if (DamageFunction.hasDamage(s))
				list.add(new Pair<String, String>(s.getLongName(), s.name()));
		}
		
		for (GemSkill g : GemSkill.values()) {
			
			if (DamageFunction.hasDamage(g))
				list.add(new Pair<String, String>(g.getDisplayName(), g.name()));
		}
		
		for (DamageProc p : DamageProc.values()) {
			list.add(new Pair<String, String>(p.getLongName(), p.name()));
		}

		Collections.sort(list, new Comparator<Pair<String, String>>(){

			@Override
			public int compare(Pair<String, String> o1, Pair<String, String> o2) {
				return o1.getA().toLowerCase().compareTo(o2.getA().toLowerCase());
			}});

		for (Pair<String, String> p : list) {
			skills.addItem(p.getA(), p.getB());
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
		
		DamageProc proc = null;
				
		try { proc = DamageProc.valueOf(value); } catch (Exception i3) { }

		if ((skill == null) && (gem == null) && (proc == null))
			return;
		
		int row = 1;
		for (DamageRow dr : DamageFunction.ALL) {

			boolean match = false;
			
			if (dr.source.skill != null)
				match = (dr.source.skill == skill);
			else if (dr.source.gem != null)
				match = (dr.source.gem == gem);
			else if (dr.source.proc != null)
				match = (dr.source.proc == proc);
				
			if (match) {
				if ((row % 2) == 0)
					table.getRowFormatter().addStyleName(row, "evenRow");
				else
					table.getRowFormatter().addStyleName(row, "oddRow");
				
				String url = dr.source.getUrl();
				
				int col = 0;
				
				addAnchor(row, col++, dr.source.getName(), url);

				Rune rune = Rune.None;
				
				if (dr.source.rune != null)
					rune = dr.source.rune;
				
				if (rune != Rune.None)
					url += ("#" + rune.getSlug() + "+");
				
				if (skill != null)
					addAnchor(row, col++, rune.getLongName(), url);
				else
					addLabel(row, col++, "N/A");

				addLabel(row, col++, dr.type.name());
				addLabel(row, col++, String.valueOf(Math.round(dr.getScalar(data) * 100.0)) + "%");
				
				if ((skill != null)  && (skill.getFrames() > 0)) {
					addLabel(row, col++, String.valueOf(skill.getFrames()));
				} else {
					addLabel(row, col++, "N/A");
				}
				
				addLabel(row, col++, dr.primary?"Yes":"");
				addLabel(row, col++, dr.maxAdditional > 20 ? "UNLIMITED" : dr.maxAdditional > 0 ? String.valueOf(dr.maxAdditional) : "");
				addLabel(row, col++, ((dr.numProjectiles > 20) || (dr.numProjectiles < 1)) ? "N/A" : String.valueOf(dr.numProjectiles));
				addLabel(row, col++, (dr.radius > 0) ? String.valueOf(dr.radius) : "");
				addLabel(row, col++, dr.multipliers.contains(DamageMultiplier.Grenades)?"Yes":"");
				addLabel(row, col++, dr.multipliers.contains(DamageMultiplier.Rockets)?"Yes":"");
				addLabel(row, col++, dr.multipliers.contains(DamageMultiplier.DoT)?"Yes - " + dr.dotDuration + " seconds":"");
				addLabel(row, col++, dr.note, "notes");
				
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
