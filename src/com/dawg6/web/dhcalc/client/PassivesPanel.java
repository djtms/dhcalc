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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import com.dawg6.web.dhcalc.shared.calculator.Passive;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;

public class PassivesPanel extends Composite {
	
	private final List<ChangeHandler> handlers = new Vector<ChangeHandler>();
	private final List<ListBox> listBoxes = new Vector<ListBox>();
	private final List<Anchor> anchors = new Vector<Anchor>();
	
	private boolean disableListeners = false;
	
	public static final int NUM_PASSIVES = 5;
	
	public PassivesPanel() {
		
		CaptionPanel captionPanel = new CaptionPanel("Passives");
		initWidget(captionPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		List<Passive> list = new Vector<Passive>();
		for (Passive p : Passive.values())
			list.add(p);
		Collections.sort(list, new Comparator<Passive>(){

			@Override
			public int compare(Passive a, Passive b) {
				return a.getLongName().compareTo(b.getLongName());
			}});
		
		for (int i = 0; i < NUM_PASSIVES; i++) {
			final ListBox lb = new ListBox();
			lb.setWidth("100%");
			flexTable.setWidget(i, 1, lb);
			
			final Anchor anchor = new Anchor("passive " + (i + 1));
			anchor.setWordWrap(false);
			anchor.setTarget("_blank");
			anchor.setHref("javascript:void(0)");
			flexTable.setWidget(i, 0, anchor);
			
			lb.addItem("None", "");
			
			for (Passive p : list) {
				lb.addItem(p.getLongName(), p.name());
			}
			
			lb.setSelectedIndex(0);
			
			lb.addChangeHandler(new ChangeHandler(){

				@Override
				public void onChange(ChangeEvent event) {
					setTooltip(lb, anchor);
					checkDuplicate(lb);
					passivesChanged(event);
				}});
			
			listBoxes.add(lb);
			anchors.add(anchor);
		}
	}

	protected void checkDuplicate(ListBox lb) {
		int selected = lb.getSelectedIndex();
		
		boolean prev = disableListeners;
		disableListeners = true;

		for (int i = 0; i < NUM_PASSIVES; i++) {
			ListBox l = listBoxes.get(i);
			
			if ((l != lb) && (l.getSelectedIndex() == selected)) {
				l.setSelectedIndex(0);
				setTooltip(anchors.get(i), null);
			}
		}

		disableListeners = prev;
	}

	protected void setTooltip(ListBox lb, Anchor anchor) {
		Passive p = getPassive(lb);
		setTooltip(anchor, p);
	}

	private void setTooltip(Anchor anchor, Passive p) {
		anchor.setHref((p == null)?"javascript:void(0)":p.getUrl());
	}

	protected void passivesChanged(ChangeEvent event) {
		
		if (!disableListeners) {
			for (ChangeHandler h : handlers)
				h.onChange(event);
		}
		
	}

	public void addChangeHandler(ChangeHandler handler) {
		this.handlers.add(handler);
	}
	
	public void setPassives(Set<Passive> list) {
		disableListeners = true;
	
		boolean changed = false;
		
		int n = 0;
		
		if (list != null) {
			for (Passive p : list) {
				
				if (n < NUM_PASSIVES) {
					Anchor a = anchors.get(n);
					ListBox lb = listBoxes.get(n++);
					
					int before = lb.getSelectedIndex();
					
					setPassive(lb, p);
					setTooltip(a, p);
					
					changed |= (lb.getSelectedIndex() != before);
				}
			}
		}
		
		while (n < NUM_PASSIVES) {
			Anchor a = anchors.get(n);
			ListBox lb = listBoxes.get(n++);

			int before = lb.getSelectedIndex();
			
			setPassive(lb, (Passive)null);
			setTooltip(a, null);
			
			changed |= (0 != before);
		}
		
		disableListeners = false;
		
		if (changed)
			passivesChanged(null);
	}
	
	public Set<Passive> getPassives() {
		Set<Passive> set = new TreeSet<Passive>();
		
		for (ListBox lb : listBoxes) {
			Passive p = getPassive(lb);
			
			if (p != null)
				set.add(p);
		}
		
		return set;
	}

	private Passive getPassive(ListBox lb) {
		int i = lb.getSelectedIndex();
		String value = lb.getValue(i);
		
		return (value.length() == 0) ? null : Passive.valueOf(value);
	}

	private void setPassive(ListBox lb, Passive p) {
		setPassive(lb, (p == null) ? null : p.name());
	}

	private void setPassive(ListBox lb, String name) {
		int n = lb.getItemCount();
		
		if ((name != null) && (name.length() > 0)) {
			for (int i = 0; i < n; i++) {
				String value = lb.getValue(i);
				
				if (value.equals(name)) {
					lb.setSelectedIndex(i);
					return;
				}
				
			}
		}
		
		lb.setSelectedIndex(0);
	}
}
