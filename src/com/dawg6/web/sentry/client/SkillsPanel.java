package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.FiringData;
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
	private final SimpleCheckBox companion;
	private final ListBox companionRunes;
	private final Anchor crLabel;
	private final Anchor caltropsRuneLabel;
	private final ListBox caltropsRunes;
	private final Anchor spikeTrapRuneLabel;
	private final Label label_1;
	private final NumberSpinner numSpikeTraps;
	private final ListBox spikeTrapRunes;
	private final SimpleCheckBox spikeTrap;
	private final Anchor anchor_2;
	private final SimpleCheckBox rov;
	private final Anchor rovRuneLabel;
	private final ListBox rovRunes;
//	private final Label label_2;
//	private final NumberSpinner rovKilled;
	private final Anchor anchor_4;
	private final SimpleCheckBox preparation;
	private final Anchor preparationRuneLabel;
	private final ListBox preparationRunes;

	public SkillsPanel() {

		CaptionPanel captionPanel = new CaptionPanel("Active Skills");
		initWidget(captionPanel);

		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(2);
		captionPanel.setContentWidget(flexTable);

		anchor_2 = new Anchor();
		anchor_2.setWordWrap(false);
		anchor_2.setText("Rain of Vengeance");
		anchor_2.setTarget("_blank");
		anchor_2.setHref(ActiveSkill.RoV.getUrl());
		flexTable.setWidget(0, 0, anchor_2);

		rov = new SimpleCheckBox();
		flexTable.setWidget(0, 1, rov);

		rovRuneLabel = new Anchor("Rune:");
		rovRuneLabel.setTarget("_blank");
		rovRuneLabel.setHref(ActiveSkill.RoV.getUrl());
		flexTable.setWidget(0, 2, rovRuneLabel);

		rovRunes = new ListBox();
		rovRunes.setSelectedIndex(0);
		flexTable.setWidget(0, 3, rovRunes);
		rovRunes.setWidth("100%");
		
//		label_2 = new Label("# Killed:");
//		label_2.setWordWrap(false);
//		flexTable.setWidget(1, 2, label_2);
//		
//		rovKilled = new NumberSpinner();
//		rovKilled.setVisibleLength(3);
//		rovKilled.setTitle("# of enemies killed during RoV Cooldown");
//		flexTable.setWidget(1, 3, rovKilled);
		
		anchor_4 = new Anchor();
		anchor_4.setWordWrap(false);
		anchor_4.setText("Preparation");
		anchor_4.setTarget("_blank");
		anchor_4.setHref(ActiveSkill.Preparation.getUrl());
		flexTable.setWidget(1, 0, anchor_4);
		
		preparation = new SimpleCheckBox();
		flexTable.setWidget(1, 1, preparation);
		
		preparationRuneLabel = new Anchor("Rune:");
		preparationRuneLabel.setTarget("_blank");
		preparationRuneLabel.setHref(ActiveSkill.Preparation.getUrl());
		flexTable.setWidget(1, 2, preparationRuneLabel);
		
		preparationRunes = new ListBox();
		preparationRunes.setSelectedIndex(0);
		flexTable.setWidget(1, 3, preparationRunes);
		preparationRunes.setWidth("100%");

		Anchor anchor_1 = new Anchor();
		anchor_1.setText("Caltrops");
		anchor_1.setHref(ActiveSkill.Caltrops.getUrl());
		anchor_1.setWordWrap(false);
		anchor_1.setTarget("_blank");
		flexTable.setWidget(2, 0, anchor_1);

		caltrops = new SimpleCheckBox();
		flexTable.setWidget(2, 1, caltrops);

		caltropsRuneLabel = new Anchor("Rune:");
		caltropsRuneLabel.setTarget("_blank");
		caltropsRuneLabel.setHref(ActiveSkill.Caltrops.getUrl());
		flexTable.setWidget(2, 2, caltropsRuneLabel);

		caltropsRunes = new ListBox();
		caltropsRunes.setSelectedIndex(0);
		flexTable.setWidget(2, 3, caltropsRunes);
		caltropsRunes.setWidth("100%");

		Label label = new Label("Uptime:");
		label.setWordWrap(false);
		flexTable.setWidget(3, 2, label);

		caltropsUptime = new NumberSpinner();
		caltropsUptime.setVisibleLength(3);
		caltropsUptime
				.setTitle("Percent of the time primary target will have active Caltrops applied.");
		flexTable.setWidget(3, 3, caltropsUptime);
		caltropsUptime.setMax(100);

		Anchor anchor_3 = new Anchor("Caltrops/Bait the Trap", false,
				"http://us.battle.net/d3/en/class/demon-hunter/active/caltrops#e+");
		anchor_3.setText("Spike Trap");
		anchor_3.setWordWrap(false);
		anchor_3.setTarget("_blank");
		anchor_3.setHTML("Spike Trap");
		anchor_3.setHref(ActiveSkill.ST.getUrl());
		flexTable.setWidget(4, 0, anchor_3);

		spikeTrap = new SimpleCheckBox();
		flexTable.setWidget(4, 1, spikeTrap);

		spikeTrapRuneLabel = new Anchor("Rune:");
		spikeTrapRuneLabel.setTarget("_blank");
		spikeTrapRuneLabel.setHref(ActiveSkill.ST.getUrl());
		flexTable.setWidget(4, 2, spikeTrapRuneLabel);

		spikeTrapRunes = new ListBox();
		spikeTrapRunes.setSelectedIndex(0);
		flexTable.setWidget(4, 3, spikeTrapRunes);
		spikeTrapRunes.setWidth("100%");

		label_1 = new Label("# Spike Traps:");
		label_1.setWordWrap(false);
		flexTable.setWidget(5, 2, label_1);

		numSpikeTraps = new NumberSpinner();
		numSpikeTraps.setVisibleLength(3);
		numSpikeTraps.setTitle("# of Spike Traps deployed during "
				+ FiringData.DURATION + " second fight");
		flexTable.setWidget(5, 3, numSpikeTraps);

		Anchor cLabel = new Anchor("Companion:");
		cLabel.setHref(ActiveSkill.Companion.getUrl());
		cLabel.setTarget("_blank");
		flexTable.setWidget(6, 0, cLabel);

		companion = new SimpleCheckBox();
		flexTable.setWidget(6, 1, companion);

		crLabel = new Anchor("Rune:");
		crLabel.setHref(ActiveSkill.Companion.getUrl());
		crLabel.setTarget("_blank");
		flexTable.setWidget(6, 2, crLabel);

		companionRunes = new ListBox();
		flexTable.setWidget(6, 3, companionRunes);
		companionRunes.setWidth("100%");
		companionRunes.setSelectedIndex(0);

		companionRunes.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				setCompanionRuneLabel();
			}
		});

		Anchor anchor = new Anchor("Marked for Death", false,
				MarkedForDeath.url);
		anchor.setWordWrap(false);
		anchor.setTarget("_blank");
		flexTable.setWidget(7, 0, anchor);

		mfd = new SimpleCheckBox();
		flexTable.setWidget(7, 1, mfd);

		mfdRuneAnchor = new Anchor("Rune:");
		mfdRuneAnchor.setHref(MarkedForDeath.url);
		mfdRuneAnchor.setTarget("_blank");
		mfdRuneAnchor.setWordWrap(false);
		flexTable.setWidget(7, 2, mfdRuneAnchor);

		mfdRune = new ListBox();
		flexTable.setWidget(7, 3, mfdRune);
		mfdRune.setWidth("100%");

		Label lblUptime = new Label("Primary Target Uptime:");
		lblUptime.setWordWrap(false);
		flexTable.setWidget(8, 0, lblUptime);

		mfdUptime = new NumberSpinner();
		mfdUptime
				.setTitle("Percent of the time primary target will have active MfD applied.");
		mfdUptime.setVisibleLength(3);
		flexTable.setWidget(8, 1, mfdUptime);

		for (Rune rune : MarkedForDeath.RUNES) {
			mfdRune.addItem(rune.getLongName(), rune.name());
		}

		mfdRune.setSelectedIndex(0);
		flexTable.getCellFormatter().setVerticalAlignment(10, 0,
				HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(10, 0,
				HasHorizontalAlignment.ALIGN_LEFT);

		Label lblAdditionalTargetsUptime = new Label(
				"Additional Targets Uptime:");
		lblAdditionalTargetsUptime.setWordWrap(false);
		flexTable.setWidget(9, 0, lblAdditionalTargetsUptime);

		mfdAddUptime = new NumberSpinner();
		mfdAddUptime.setVisibleLength(3);
		mfdAddUptime
				.setTitle("Percent of the time additional targets will have active MfD applied.");
		flexTable.setWidget(9, 1, mfdAddUptime);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(8, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(9, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(7, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(6, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);

		Label lblNoteThisStacks = new Label(
				"Note: This stacks with Calamity's MfD");
		lblNoteThisStacks.setWordWrap(false);
		lblNoteThisStacks.setStyleName("boldText");
		flexTable.setWidget(10, 0, lblNoteThisStacks);
		lblNoteThisStacks.setWidth("168px");
		flexTable.getFlexCellFormatter().setColSpan(8, 0, 3);
		flexTable.getFlexCellFormatter().setColSpan(9, 0, 3);
		flexTable.getFlexCellFormatter().setColSpan(10, 0, 4);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		FlexTableHelper.fixRowSpan(flexTable);

		mfdRune.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateMfdRuneAnchor();
			}
		});

		mfdUptime.setMax(100);
		mfdAddUptime.setMax(100);
		numSpikeTraps.setMax(10);

		for (Rune r : ActiveSkill.Companion.getRunes()) {
			companionRunes.addItem(r.getLongName(), r.name());
		}

		for (Rune r : ActiveSkill.Caltrops.getRunes()) {
			caltropsRunes.addItem(r.getLongName(), r.name());
		}

		for (Rune r : ActiveSkill.Preparation.getRunes()) {
			preparationRunes.addItem(r.getLongName(), r.name());
		}

		for (Rune r : ActiveSkill.ST.getRunes()) {
			spikeTrapRunes.addItem(r.getLongName(), r.name());
		}

		for (Rune r : ActiveSkill.RoV.getRunes()) {
			rovRunes.addItem(r.getLongName(), r.name());
		}

		caltropsRunes.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				setCaltropsRuneLabel();
			}
		});

		preparationRunes.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				setPreparationRuneLabel();
			}
		});

		spikeTrapRunes.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				setSpikeTrapRuneLabel();
			}
		});

		rovRunes.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				setRoVRuneLabel();
			}
		});

	}

	public void setCompanionRuneLabel() {
		this.setRuneLabel(companionRunes, ActiveSkill.Companion, crLabel);
	}

	public void setSpikeTrapRuneLabel() {
		this.setRuneLabel(spikeTrapRunes, ActiveSkill.ST, spikeTrapRuneLabel);
	}

	public void setRoVRuneLabel() {
		this.setRuneLabel(rovRunes, ActiveSkill.RoV, rovRuneLabel);
	}

	public void setCaltropsRuneLabel() {
		this.setRuneLabel(caltropsRunes, ActiveSkill.Caltrops,
				caltropsRuneLabel);
	}

	public void setPreparationRuneLabel() {
		this.setRuneLabel(preparationRunes, ActiveSkill.Preparation,
				preparationRuneLabel);
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

	public SimpleCheckBox getCompanion() {
		return companion;
	}

	public ListBox getCompanionRunes() {
		return companionRunes;
	}

	private Rune getRune(ListBox runes) {
		int index = runes.getSelectedIndex();

		if (index < 0)
			return null;

		String value = runes.getValue(index);
		Rune rune = Rune.valueOf(value);

		return rune;
	}

	private void setRuneLabel(ListBox runes, ActiveSkill skill, Anchor anchor) {
		Rune rune = getRune(runes);

		if ((rune != null) && (rune != Rune.None))
			anchor.setHref(skill.getUrl() + "#" + rune.getSlug() + "+");
		else
			anchor.setHref(skill.getUrl());
	}

	public ListBox getCaltropsRunes() {
		return caltropsRunes;
	}

	public NumberSpinner getNumSpikeTraps() {
		return numSpikeTraps;
	}

	public ListBox getSpikeTrapRunes() {
		return spikeTrapRunes;
	}

	public SimpleCheckBox getSpikeTrap() {
		return spikeTrap;
	}

	public SimpleCheckBox getRov() {
		return rov;
	}

	public ListBox getRovRunes() {
		return rovRunes;
	}

//	public NumberSpinner getRovKilled() {
//		return rovKilled;
//	}
//
	public SimpleCheckBox getPreparation() {
		return preparation;
	}

	public ListBox getPreparationRunes() {
		return preparationRunes;
	}

}
