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
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import com.dawg6.gwt.client.widgets.SimpleCaptionPanel;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.SkillType;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class SkillsPanel extends Composite {
	private final NumberSpinner mfdUptime;
	private final NumberSpinner mfdAddUptime;
	private final NumberSpinner caltropsUptime;
	private final List<Anchor> skillAnchors = new Vector<Anchor>(NUM_SKILLS);
	private final List<ListBox> skillBoxes = new Vector<ListBox>(NUM_SKILLS);
	private final List<Anchor> runeAnchors = new Vector<Anchor>(NUM_SKILLS);
	private final List<ListBox> runeBoxes = new Vector<ListBox>(NUM_SKILLS);
	private final Set<SkillsChangedListener> listeners = new TreeSet<SkillsChangedListener>();
	private final SimpleCheckBox syncWithCoe;
	private final SimpleCheckBox spenderFullHatred;
	
	public interface SkillsChangedListener {
		void skillsChanged(Map<ActiveSkill, Rune> skills);
	}
	
	public static final int NUM_SKILLS = 6;
	private boolean disableListeners = false;

	public SkillsPanel() {

		SimpleCaptionPanel SimpleCaptionPanel = new SimpleCaptionPanel("Active Skills");
		initWidget(SimpleCaptionPanel);

		FlexTable flexTable = new FlexTable();
		SimpleCaptionPanel.setContentWidget(flexTable);

		int row = 0;

		List<ActiveSkill> skills = new Vector<ActiveSkill>();

		for (ActiveSkill s : ActiveSkill.values()) {
			if (s.getSkillType() != SkillType.NA)
				skills.add(s);
		}
		Collections.sort(skills, new Comparator<ActiveSkill>() {

			@Override
			public int compare(ActiveSkill o1, ActiveSkill o2) {
				return o1.getLongName().toLowerCase()
						.compareTo(o2.getLongName().toLowerCase());
			}
		});

		for (int i = 0; i < NUM_SKILLS; i++) {
			final Anchor anchor = new Anchor("Skill " + (i + 1) + ":");
			anchor.setHref("javascript:void(0)");
			anchor.setTarget("_blank");
			anchor.setWordWrap(false);
			skillAnchors.add(anchor);
			flexTable.setWidget(row, 0, anchor);

			final ListBox list = new ListBox();

			list.addItem("None", "");

			for (ActiveSkill s : skills) {
				list.addItem(s.getLongName(), s.name());
			}

			list.setSelectedIndex(0);
			skillBoxes.add(list);
			list.setWidth("100%");
			flexTable.setWidget(row, 1, list);

			final Anchor anchor2 = new Anchor("Rune:");
			anchor2.setHref("javascript:void(0)");
			anchor2.setTarget("_blank");
			anchor2.setWordWrap(false);
			runeAnchors.add(anchor2);
			flexTable.setWidget(row, 2, anchor2);

			final ListBox list2 = new ListBox();

			list2.addItem("None", Rune.None.name());
			list2.setSelectedIndex(0);
			list2.setWidth("100%");
			flexTable.setWidget(row, 3, list2);
			runeBoxes.add(list2);

			list.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {

					if (!disableListeners) {
						skillChanged(anchor, list, anchor2, list2);
					}
				}
			});

			list2.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {

					if (!disableListeners) {
						runeChanged(list, anchor2, list2);
					}
				}
			});

			row++;
		}

		Label label_1 = new Label("Sync Cooldowns with CoE:");
		label_1.setWordWrap(false);
		flexTable.setWidget(row, 0, label_1);
		flexTable.getFlexCellFormatter().setColSpan(row, 0, 2);
		flexTable.getFlexCellFormatter().setHorizontalAlignment(row, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		syncWithCoe = new SimpleCheckBox();
		syncWithCoe
				.setTitle("If set and wearing CoE, cooldowns (FoK, RoV, Companion) will only be used when proper CoE buff is up.");
		flexTable.setWidget(row, 1, syncWithCoe);
		flexTable.getFlexCellFormatter().setColSpan(row, 1, 2);

		row++;
		
		Label label_1a = new Label("Spender Requires Full Hatred:");
		label_1a.setWordWrap(false);
		flexTable.setWidget(row, 0, label_1a);
		flexTable.getFlexCellFormatter().setColSpan(row, 0, 2);
		flexTable.getFlexCellFormatter().setHorizontalAlignment(row, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		spenderFullHatred = new SimpleCheckBox();
		spenderFullHatred
				.setTitle("Only use spender when Hatred is full (or needed for a buff).");
		flexTable.setWidget(row, 1, spenderFullHatred);
		flexTable.getFlexCellFormatter().setColSpan(row, 1, 2);

		row++;
		
		Label label = new Label("Caltrops Uptime:");
		label.setWordWrap(false);
		flexTable.setWidget(row, 0, label);
		flexTable.getFlexCellFormatter().setColSpan(row, 0, 2);
		flexTable.getFlexCellFormatter().setHorizontalAlignment(row, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		caltropsUptime = new NumberSpinner();
		caltropsUptime.setVisibleLength(3);
		caltropsUptime
				.setTitle("Percent of the time primary target will have active Caltrops applied.");
		flexTable.setWidget(row, 1, caltropsUptime);
		flexTable.getFlexCellFormatter().setColSpan(row, 1, 2);
		caltropsUptime.setMax(100);

		row++;
		
		Label lblUptime = new Label("Primary Target MfD Uptime:");
		lblUptime.setWordWrap(false);
		flexTable.setWidget(row, 0, lblUptime);
		flexTable.getFlexCellFormatter().setColSpan(row, 0, 2);
		flexTable.getFlexCellFormatter().setHorizontalAlignment(row, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		mfdUptime = new NumberSpinner();
		mfdUptime
				.setTitle("Percent of the time primary target will have active MfD applied.");
		mfdUptime.setVisibleLength(3);
		flexTable.setWidget(row, 1, mfdUptime);
		flexTable.getFlexCellFormatter().setColSpan(row, 1, 2);

		row++;

		Label lblAdditionalTargetsUptime = new Label(
				"Additional Targets MfD Uptime:");
		lblAdditionalTargetsUptime.setWordWrap(false);
		flexTable.setWidget(row, 0, lblAdditionalTargetsUptime);
		flexTable.getFlexCellFormatter().setColSpan(row, 0, 2);
		flexTable.getFlexCellFormatter().setHorizontalAlignment(row, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);

		mfdAddUptime = new NumberSpinner();
		mfdAddUptime.setVisibleLength(3);
		mfdAddUptime
				.setTitle("Percent of the time additional targets will have active MfD applied.");
		flexTable.setWidget(row, 1, mfdAddUptime);
		flexTable.getFlexCellFormatter().setColSpan(row, 1, 2);

		row++;

		mfdUptime.setMax(100);
		mfdAddUptime.setMax(100);
		caltropsUptime.setMax(100);

	}

	public void addSkillsChangedListener(SkillsChangedListener l) {
		listeners.add(l);
	}
	
	public void removeSkillsChangedListener(SkillsChangedListener l) {
		listeners.remove(l);
	}
	
	protected void runeChanged(ListBox list, Anchor anchor2, ListBox list2) {

		ActiveSkill skill = getSelectedSkill(list);
		Rune rune = getSelectedRune(list2);
		setRuneAnchor(skill, anchor2, rune);
		this.skillsChanged(this.getSkills());
	}

	private void setRuneAnchor(ActiveSkill skill, Anchor anchor2, Rune rune) {

		if (skill == null) {
			anchor2.setHref("javascript:void(0)");
		} else if ((rune == null) || (rune == Rune.None)) {
			anchor2.setHref(skill.getUrl());
		} else {
			anchor2.setHref(skill.getUrl() + "#" + rune.getSlug() + "+");
		}
	}

	private Rune getSelectedRune(ListBox list) {

		int i = list.getSelectedIndex();

		if (i <= 0)
			return Rune.None;

		String value = list.getValue(i);

		return Rune.valueOf(value);
	}

	protected void skillChanged(Anchor anchor, ListBox list, Anchor anchor2,
			ListBox list2) {
		ActiveSkill skill = getSelectedSkill(list);

		setRunes(anchor2, list2, skill);
		setSkillAnchor(anchor, skill);

		checkDuplicate(list);
		
		this.skillsChanged(this.getSkills());
	}

	private void checkDuplicate(ListBox lb) {
		int selected = lb.getSelectedIndex();

		boolean prev = disableListeners;
		disableListeners = true;

		for (int i = 0; i < NUM_SKILLS; i++) {
			ListBox l = skillBoxes.get(i);

			if ((l != lb) && (l.getSelectedIndex() == selected)) {
				l.setSelectedIndex(0);
				setRunes(runeAnchors.get(i), runeBoxes.get(i), null);
				setSkillAnchor(skillAnchors.get(i), null);
			}
		}

		disableListeners = prev;
	}

	private void setSkillAnchor(Anchor anchor, ActiveSkill skill) {

		if (skill == null) {
			anchor.setHref("javascript:void(0)");
		} else {
			anchor.setHref(skill.getUrl());
		}
	}

	private void setRunes(Anchor anchor2, ListBox list2, ActiveSkill skill) {

		list2.clear();


		if (skill != null) {
			for (Rune r : skill.getRunes()) {
				list2.addItem(r.getLongName(), r.name());
			}
		} else {
			list2.addItem("None", Rune.None.name());
		}

		list2.setSelectedIndex(0);
		setRuneAnchor(skill, anchor2, Rune.None);
	}

	private ActiveSkill getSelectedSkill(ListBox list) {

		int i = list.getSelectedIndex();

		if (i <= 0)
			return null;

		String value = list.getValue(i);

		return ActiveSkill.valueOf(value);
	}

	public NumberSpinner getMfdUptime() {
		return mfdUptime;
	}

	public NumberSpinner getMfdAddUptime() {
		return mfdAddUptime;
	}

	public NumberSpinner getCaltropsUptime() {
		return caltropsUptime;
	}

	public Map<ActiveSkill, Rune> getSkills() {
		Map<ActiveSkill, Rune> map = new TreeMap<ActiveSkill, Rune>();

		for (int i = 0; i < NUM_SKILLS; i++) {
			ActiveSkill s = getSelectedSkill(skillBoxes.get(i));

			if (s != null) {
				Rune r = getSelectedRune(runeBoxes.get(i));

				map.put(s, r);
			}
		}

		return map;
	}

	public void setSkills(Map<ActiveSkill, Rune> skills) {

		disableListeners = true;
		boolean changed = false;

		int i = 0;

		for (Map.Entry<ActiveSkill, Rune> e : skills.entrySet()) {
			if (i < NUM_SKILLS) {
				ActiveSkill skill = e.getKey();
				Rune rune = e.getValue();

				ListBox list = skillBoxes.get(i);
				ListBox list2 = runeBoxes.get(i);
				Anchor anchor2 = runeAnchors.get(i);

				ActiveSkill s1 = getSelectedSkill(list);

				if (skill != s1) {
					Anchor anchor = skillAnchors.get(i);
					changed = true;

					selectSkill(anchor, list, skill, anchor2, list2);
				}

				Rune r1 = getSelectedRune(list2);

				if (rune != r1) {
					changed = true;
					selectRune(skill, anchor2, list2, rune);
				}

				i++;
			}
		}

		while (i < NUM_SKILLS) {

			ListBox list = skillBoxes.get(i);

			ActiveSkill skill = getSelectedSkill(list);

			if (skill != null) {
				ListBox list2 = runeBoxes.get(i);
				Anchor anchor = skillAnchors.get(i);
				Anchor anchor2 = runeAnchors.get(i);

				changed = true;
				selectSkill(anchor, list, null, anchor2, list2);
			}

			i++;
		}

		disableListeners = false;

		if (changed) {
			skillsChanged(skills);
		}
	}

	private void skillsChanged(Map<ActiveSkill, Rune> skills) {
		if (!disableListeners) {
			for (SkillsChangedListener l : listeners)
				l.skillsChanged(skills);
		}
	}

	private void selectRune(ActiveSkill skill, Anchor anchor2, ListBox list2,
			Rune rune) {

		if (rune == null) {
			list2.setSelectedIndex(0);
		} else {
			int n = list2.getItemCount();

			for (int i = 0; i < n; i++) {
				String value = list2.getValue(i);

				if (value.equals(rune.name())) {
					list2.setSelectedIndex(i);
					break;
				}
			}
		}

		this.setRuneAnchor(skill, anchor2, rune);
	}

	private void selectSkill(Anchor anchor, ListBox list, ActiveSkill skill,
			Anchor anchor2, ListBox list2) {

		if (skill == null) {
			list.setSelectedIndex(0);
		} else {
			int n = list.getItemCount();

			for (int i = 0; i < n; i++) {
				String value = list.getValue(i);

				if (value.equals(skill.name())) {
					list.setSelectedIndex(i);
					break;
				}
			}
		}

		this.setSkillAnchor(anchor, skill);
		this.setRunes(anchor2, list2, skill);
	}

	public SimpleCheckBox getSyncWithCoe() {
		return this.syncWithCoe;
	}

	public SimpleCheckBox getSpenderFullHatred() {
		return spenderFullHatred;
	}
}
