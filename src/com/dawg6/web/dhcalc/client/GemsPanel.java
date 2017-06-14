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
import java.util.TreeMap;
import java.util.Vector;

import com.dawg6.gwt.client.widgets.SimpleCaptionPanel;
import com.dawg6.web.dhcalc.shared.calculator.GemAttributeData;
import com.dawg6.web.dhcalc.shared.calculator.GemSkill;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class GemsPanel extends Composite {

	private boolean disableListeners;
	private final List<ChangeHandler> handlers = new Vector<ChangeHandler>();
	private final List<ListBox> listBoxes = new Vector<ListBox>(NUM_GEMS);
	private final List<Anchor> anchors = new Vector<Anchor>(NUM_GEMS);
	private final List<NumberSpinner> attributes = new Vector<NumberSpinner>(
			NUM_GEMS);
	private final List<NumberSpinner> levels = new Vector<NumberSpinner>(
			NUM_GEMS);
	private final List<GemSkill> selected = new Vector<GemSkill>(NUM_GEMS);

	private FlexTable table;

	public static final int NUM_GEMS = 3;

	public GemsPanel() {

		SimpleCaptionPanel SimpleCaptionPanel = new SimpleCaptionPanel("Legendary Gems");
		initWidget(SimpleCaptionPanel);

		table = new FlexTable();
		SimpleCaptionPanel.setContentWidget(table);

		List<GemSkill> gems = new Vector<GemSkill>();

		for (GemSkill gem : GemSkill.values())
			gems.add(gem);

		Collections.sort(gems, new Comparator<GemSkill>() {

			@Override
			public int compare(GemSkill o1, GemSkill o2) {
				return o1.getDisplayName().toLowerCase()
						.compareTo(o2.getDisplayName().toLowerCase());
			}
		});

		for (int i = 0; i < NUM_GEMS; i++) {
			final int row = i;
			Anchor anchor = new Anchor("Gem " + (i + 1));
			anchor.setWordWrap(false);
			anchor.setTarget("_blank");
			anchor.setHref("javascript:void(0)");
			anchors.add(anchor);
			table.setWidget(row, 0, anchor);

			ListBox list = new ListBox();
			list.addItem("None", "");
			list.setWidth("100%");
			table.setWidget(row, 1, list);
			listBoxes.add(list);
			list.setSelectedIndex(0);
			selected.add(null);

			for (GemSkill gem : gems) {
				list.addItem(gem.getDisplayName(), gem.name());
			}

			list.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					gemChanged(row);
				}
			});

			Label label = new Label("Level:", false);
			table.setWidget(row, 2, label);

			NumberSpinner level = new NumberSpinner();
			level.setMax(1000);
			level.setVisibleLength(4);
			table.setWidget(row, 3, level);
			levels.add(level);
			level.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					levelChanged(row);
				}
			});

			attributes.add(null);
		}

	}

	protected void levelChanged(int row) {

		GemSkill gem = getSelectedGem(row);

		// TODO Handle more than 1 Attribute per Gem
		if ((gem != null) && (gem.getAttributes() != null)
				&& (gem.getAttributes().length > 0)) {
			int level = levels.get(row).getValue();

			NumberSpinner a = attributes.get(row);

			if (a != null) {
				a.setMax(gem.getAttributes()[0].getMaxValue(level));
			}
		}

		gemsChanged(null);
	}

	protected void gemChanged(int row) {
		GemSkill gem = getSelectedGem(row);

		if (setGem(row, gem, null)) {

			if (!disableListeners && (gem != null)) {
				for (int i = 0; i < NUM_GEMS; i++) {
					if (i != row) {
						GemSkill other = getSelectedGem(i);
						
						if (other == gem) {
							setGem(i, null, null);
						}
					}
				}
			}

			gemsChanged(null);
		}
	}

	public Map<GemSkill, GemAttributeData> getGems() {

		Map<GemSkill, GemAttributeData> gems = new TreeMap<GemSkill, GemAttributeData>();

		for (int i = 0; i < NUM_GEMS; i++) {
			GemSkill gem = getSelectedGem(i);

			if (gem != null) {
				GemAttributeData gd = new GemAttributeData();
				gd.level = getGemLevel(i);

				// TODO Handle more than 1 Attribute per Gem
				if ((gem.getAttributes() != null)
						&& (gem.getAttributes().length > 0)) {
					gd.put(gem.getAttributes()[0].getLabel(),
							getGemAttribute(i));
				}

				gems.put(gem, gd);
			}
		}

		return gems;
	}

	public void setGems(Map<GemSkill, GemAttributeData> gems) {

		disableListeners = true;

		int i = 0;
		boolean changed = false;

		for (Map.Entry<GemSkill, GemAttributeData> e : gems.entrySet()) {
			GemSkill gem = e.getKey();
			GemAttributeData gd = e.getValue();

			changed |= setGem(i, gem, gd);

			i++;

			if (i >= NUM_GEMS)
				break;
		}

		while (i < NUM_GEMS) {
			changed |= setGem(i++, null, null);
		}

		disableListeners = false;

		if (changed)
			gemsChanged(null);
	}

	private boolean setGem(final int i, GemSkill gem, GemAttributeData gd) {

		if ((gem != null) && (gd == null)) {
			gd = new GemAttributeData();
			gd.level = 0;

			for (GemSkill.Attribute a : gem.getAttributes()) {
				gd.put(a.getLabel(), 0);
			}
		}

		boolean gemChanged = false;
		boolean valueChanged = false;

		ListBox list = listBoxes.get(i);
		GemSkill prev = selected.get(i);
		int next = 0;

		if (gem == null) {
			next = 0;
		} else {
			int num = list.getItemCount();
			boolean found = false;

			for (int n = 1; n < num; n++) {
				String value = list.getValue(n);

				if (value.equals(gem.name())) {
					next = n;
					found = true;
					break;
				}
			}

			if (!found) {
				next = 0;
				gd = null;
			}
		}

		if (gem != prev) {
			list.setSelectedIndex(next);
			selected.set(i, gem);
			gemChanged = true;
		}

		NumberSpinner level = levels.get(i);
		int prevLevel = level.getValue();
		int nextLevel = prevLevel;

		if (gem == null) {
			nextLevel = 0;
		} else {
			nextLevel = gd.level;
		}

		if (nextLevel != prevLevel) {
			level.setValue(nextLevel);
			valueChanged = true;
		}

		if (gemChanged) {
			
			Anchor anchor = anchors.get(i);
			
			if (gem == null) {
				anchor.setHref("javascript:void(0)");
			} else {
				anchor.setHref(gem.getUrl());
			}

			if (attributes.get(i) != null) {
				table.removeCell(i, 5);
				table.removeCell(i, 4);
				attributes.set(i, null);
			}

			if ((gem != null) && (gem.getAttributes() != null)
					&& (gem.getAttributes().length > 0)) {

				// TODO Handle more than 1 Attribute per Gem
				Label label = new Label("# "
						+ gem.getAttributes()[0].getLabel() + ":", false);
				NumberSpinner spinner = new NumberSpinner();
				spinner.setMax(gem.getAttributes()[0].getMaxValue(gd.level));
				spinner.setVisibleLength(4);
				table.setWidget(i, 4, label);
				table.setWidget(i, 5, spinner);
				attributes.set(i, spinner);
				spinner.setValue(gd.get(gem.getAttributes()[0]
						.getLabel()));

				spinner.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						attributeChanged(i);
					}
				});
			}
		} else {
			if ((gem == null) || (gem.getAttributes() == null)
					|| (gem.getAttributes().length == 0)) {
				// nothing changes
			} else {
				NumberSpinner a = attributes.get(i);

				int prevValue = a.getValue();
				Integer nextValue = gd.get(gem.getAttributes()[0]
						.getLabel());

				if (nextValue != prevValue) {
					a.setValue(nextValue);
					valueChanged = true;
				}
			}
		}

		return gemChanged | valueChanged;
	}

	protected void attributeChanged(int i) {
		gemsChanged(null);
	}

	protected void gemsChanged(ChangeEvent event) {

		if (!disableListeners) {
			for (ChangeHandler h : handlers)
				h.onChange(event);
		}

	}

	public void addChangeHandler(ChangeHandler handler) {
		this.handlers.add(handler);
	}

	public boolean isGem(GemSkill gem) {

		for (int i = 0; i < NUM_GEMS; i++) {
			GemSkill skill = getSelectedGem(i);

			if (skill == gem)
				return true;
		}

		return false;
	}

	private GemSkill getSelectedGem(int i) {

		ListBox list = listBoxes.get(i);

		int n = list.getSelectedIndex();

		if (n <= 0)
			return null;

		return GemSkill.valueOf(list.getValue(n));
	}

	private int getGemLevel(int i) {
		return levels.get(i).getValue();
	}

	private int getGemAttribute(int i) {
		return attributes.get(i).getValue();
	}

	public int getGemLevel(GemSkill gem) {
		for (int i = 0; i < NUM_GEMS; i++) {
			GemSkill skill = getSelectedGem(i);

			if (skill == gem)
				return getGemLevel(i);
		}

		return 0;
	}

	public int getGemAttribute(GemSkill gem, String attribute) {
		for (int i = 0; i < NUM_GEMS; i++) {
			GemSkill skill = getSelectedGem(i);

			if (skill == gem)
				return getGemAttribute(i);
		}

		return 0;
	}
}
