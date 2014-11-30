package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.MarkedForDeath;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;

public class SkillsPanel extends Composite {
	private final SimpleCheckBox mfd;
	private final ListBox mfdRune;
	private final NumberSpinner mfdUptime;
	private final Anchor mfdRuneAnchor;
	private final NumberSpinner mfdAddUptime;
	private final SimpleCheckBox caltrops;
	private final NumberSpinner caltropsUptime;

	public SkillsPanel() {

		CaptionPanel captionPanel = new CaptionPanel("Active Skills");
		initWidget(captionPanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);
		
		Anchor anchor_1 = new Anchor("Caltrops/Bait the Trap", false, "http://us.battle.net/d3/en/class/demon-hunter/active/caltrops#e+");
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		flexTable.setWidget(0, 0, anchor_1);
		
		caltrops = new SimpleCheckBox();
		flexTable.setWidget(0, 1, caltrops);
		
		Label label = new Label("Uptime:");
		label.setWordWrap(false);
		flexTable.setWidget(0, 2, label);
		
		caltropsUptime = new NumberSpinner();
		caltropsUptime.setVisibleLength(3);
		caltropsUptime.setTitle("Percent of the time primary target will have active MfD applied.");
		flexTable.setWidget(0, 3, caltropsUptime);

		Anchor anchor = new Anchor("Marked for Death", false, MarkedForDeath.url);
		anchor.setWordWrap(false);
		anchor.setTarget("_blank");
		flexTable.setWidget(1, 0, anchor);

		mfd = new SimpleCheckBox();
		flexTable.setWidget(1, 1, mfd);

		mfdRuneAnchor = new Anchor("Rune:");
		mfdRuneAnchor.setHref(MarkedForDeath.url);
		mfdRuneAnchor.setTarget("_blank");
		mfdRuneAnchor.setWordWrap(false);
		flexTable.setWidget(1, 2, mfdRuneAnchor);

		mfdRune = new ListBox();
		flexTable.setWidget(1, 3, mfdRune);
		mfdRune.setWidth("100%");

		Label lblNoteThisStacks = new Label(
				"Note: This stacks with Calamity's MfD");
		lblNoteThisStacks.setStyleName("boldText");
		flexTable.setWidget(2, 0, lblNoteThisStacks);
		flexTable.getFlexCellFormatter().setColSpan(2, 0, 2);
		lblNoteThisStacks.setWidth("168px");

		Label lblUptime = new Label("Primary Target Uptime:");
		lblUptime.setWordWrap(false);
		flexTable.setWidget(2, 1, lblUptime);

		mfdUptime = new NumberSpinner();
		mfdUptime
				.setTitle("Percent of the time primary target will have active MfD applied.");
		mfdUptime.setVisibleLength(3);
		flexTable.setWidget(2, 2, mfdUptime);
		
		for (Rune rune : MarkedForDeath.RUNES) {
			mfdRune.addItem(rune.getLongName(), rune.name());
		}
		
		mfdRune.setSelectedIndex(0);
		
		Label lblAdditionalTargetsUptime = new Label("Additional Targets Uptime:");
		lblAdditionalTargetsUptime.setWordWrap(false);
		flexTable.setWidget(3, 2, lblAdditionalTargetsUptime);
		flexTable.getFlexCellFormatter().setRowSpan(2, 0, 2);
		flexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		mfdAddUptime = new NumberSpinner();
		mfdAddUptime.setVisibleLength(3);
		mfdAddUptime.setTitle("Percent of the time additional targets will have active MfD applied.");
		flexTable.setWidget(3, 3, mfdAddUptime);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		FlexTableHelper.fixRowSpan(flexTable);
		
		mfdRune.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				updateMfdRuneAnchor();
			}});
		
		mfdUptime.setMax(100);
		mfdAddUptime.setMax(100);
		caltropsUptime.setMax(100);
	}

	protected void updateMfdRuneAnchor() {
		Rune rune = getMarkedForDeathRune();
		String url = MarkedForDeath.url;
		
		if (rune != Rune.None)
			url += ("#" + rune.getSlug() + "+");
		
		mfdRuneAnchor.setHref(url);
	}

	public SimpleCheckBox getMfd() {
		return mfd;
	}

	public ListBox getMfdRune() {
		return mfdRune;
	}

	public NumberSpinner getMfdUptime() {
		return mfdUptime;
	}

	public Rune getMarkedForDeathRune() {
		int index = mfdRune.getSelectedIndex();
		String value = mfdRune.getValue(index);
		return Rune.valueOf(value);
	}
	
	public void setMarkedForDeathRune(Rune rune) {
		try {
			for (int i = 0; i < mfdRune.getItemCount(); i++) {
				String value = mfdRune.getValue(i);
				
				if (value.equals(rune.name())) {
					mfdRune.setSelectedIndex(i);
					return;
				}
			}
			
			mfdRune.setSelectedIndex(0);
		} finally {
			updateMfdRuneAnchor();
		}
	}

	public NumberSpinner getMfdAddUptime() {
		return mfdAddUptime;
	}

	public SimpleCheckBox getCaltrops() {
		return caltrops;
	}

	public NumberSpinner getCaltropsUptime() {
		return caltropsUptime;
	}
}
