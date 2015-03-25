package com.dawg6.web.sentry.client;

import java.util.Collections;
import java.util.Map;
import java.util.Vector;

import com.dawg6.gwt.common.util.DefaultCallback;
import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.Build;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.SkillAndRune;
import com.dawg6.web.sentry.shared.calculator.Util;
import com.dawg6.web.sentry.shared.calculator.stats.DBStatistics;
import com.dawg6.web.sentry.shared.calculator.stats.DpsTableEntry;
import com.dawg6.web.sentry.shared.calculator.stats.StatCategory;
import com.dawg6.web.sentry.shared.calculator.stats.StatHolder;
import com.dawg6.web.sentry.shared.calculator.stats.StatSorter;
import com.dawg6.web.sentry.shared.calculator.stats.Statistics;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StatsPanel extends Composite {
	private final Label totalLabel;
	private final FlexTable mainTable;
	private final FlexTable filterTable;
	private final SimpleCheckBox sentry;
	private final ListBox sentryRune;
	private final ListBox skill1;
//	private final ListBox rune3;
//	private final ListBox skill3;
	private final ListBox rune2;
	private final ListBox skill2;
	private final ListBox rune1;
	private final FlexTable buildTable;
	private final ListBox[] skills;
	private final ListBox[] runes;
	private boolean disableListeners = false;
	private DBStatistics stats;
	private Vector<StatHolder> statList;
	private ActionListener actionListener;

	public StatsPanel() {

		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Statistics");
		initWidget(cptnpnlNewPanel);
		cptnpnlNewPanel.setSize("1005px", "619px");

		FlexTable flexTable = new FlexTable();
		SimplePanel panel = new SimplePanel();

		panel.setWidget(flexTable);
		flexTable.setHeight("554px");
		cptnpnlNewPanel.setContentWidget(panel);
		panel.setSize("100%", "100%");

		Label lblNewLabel = new Label("Total # of Profiles Analyzed:");
		lblNewLabel.setStyleName("boldText");
		lblNewLabel.setWordWrap(false);
		flexTable.setWidget(0, 0, lblNewLabel);

		totalLabel = new Label("Loading...");
		flexTable.setWidget(0, 1, totalLabel);

		CaptionPanel cptnpnlNewPanel_1 = new CaptionPanel("Global Statistics");
		flexTable.setWidget(1, 0, cptnpnlNewPanel_1);

		mainTable = new FlexTable();
		mainTable.setStyleName("statsTable");

		SimplePanel panel2 = new SimplePanel();
		panel2.setWidget(mainTable);
		cptnpnlNewPanel_1.setContentWidget(panel2);

		Label lblStatistic = new Label("Statistic");
		lblStatistic.setStyleName("boldText");
		lblStatistic.setWordWrap(false);
		mainTable.setWidget(0, 0, lblStatistic);

		Label lblAverage = new Label("Average DPS");
		lblAverage.setWordWrap(false);
		lblAverage.setStyleName("boldText");
		mainTable.setWidget(0, 1, lblAverage);

		Label lblNewLabel_1 = new Label("Max DPS");
		lblNewLabel_1.setStyleName("boldText");
		lblNewLabel_1.setWordWrap(false);
		mainTable.setWidget(0, 2, lblNewLabel_1);

		Label lblProfile = new Label("Link to Profile");
		lblProfile.setWordWrap(false);
		lblProfile.setStyleName("boldText");
		mainTable.setWidget(0, 3, lblProfile);
		mainTable.getCellFormatter().setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_RIGHT);
		mainTable.getCellFormatter().setHorizontalAlignment(0, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		mainTable.getCellFormatter().setHorizontalAlignment(0, 3,
				HasHorizontalAlignment.ALIGN_RIGHT);

		Label lblImport = new Label("Import");
		lblImport.setWordWrap(false);
		lblImport.setStyleName("boldText");
		mainTable.setWidget(0, 4, lblImport);
		mainTable.getCellFormatter().setHorizontalAlignment(0, 4,
				HasHorizontalAlignment.ALIGN_CENTER);

		flexTable.getFlexCellFormatter().setColSpan(1, 0, 2);

		CaptionPanel cptnpnlNewPanel_2 = new CaptionPanel("Build Statistics");
		flexTable.setWidget(2, 0, cptnpnlNewPanel_2);
		cptnpnlNewPanel_2.setHeight("367px");

		VerticalPanel verticalPanel = new VerticalPanel();
		cptnpnlNewPanel_2.setContentWidget(verticalPanel);

		filterTable = new FlexTable();

		SimplePanel panel3 = new SimplePanel();
		panel3.setWidget(filterTable);
		verticalPanel.add(panel3);
		panel3.setHeight("80px");

		Label sentryLabel = new Label("Sentry:");
		sentryLabel.setWordWrap(false);
		sentryLabel.setStyleName("boldText");
		filterTable.setWidget(0, 0, sentryLabel);
		
		sentry = new SimpleCheckBox();
		filterTable.setWidget(0, 1, sentry);
		
		Label lblNewLabel_2 = new Label("Rune:");
		lblNewLabel_2.setWordWrap(false);
		lblNewLabel_2.setStyleName("boldText");
		filterTable.setWidget(0, 2, lblNewLabel_2);

		sentryRune = new ListBox();
		filterTable.setWidget(0, 3, sentryRune);

		Label lblSkill = new Label("Skill 1:");
		lblSkill.setWordWrap(false);
		lblSkill.setStyleName("boldText");
		filterTable.setWidget(0, 2, lblSkill);

		skill1 = new ListBox();
		filterTable.setWidget(0, 3, skill1);

		Label lblSkill_1 = new Label("Skill 2:");
		lblSkill_1.setWordWrap(false);
		lblSkill_1.setStyleName("boldText");
		filterTable.setWidget(0, 4, lblSkill_1);

		skill2 = new ListBox();
		filterTable.setWidget(0, 5, skill2);

//		Label lblSkill_2 = new Label("Skill 3:");
//		lblSkill_2.setWordWrap(false);
//		lblSkill_2.setStyleName("boldText");
//		filterTable.setWidget(0, 6, lblSkill_2);
//
//		skill3 = new ListBox();
//		filterTable.setWidget(0, 7, skill3);
//
		Button button = new Button("Copy My Build");
		filterTable.setWidget(0, 6, button);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				copyBuild();
			}
		});

		Button btnNewButton = new Button("Update");
		btnNewButton.setText("Filter/Refresh");
		filterTable.setWidget(1, 0, btnNewButton);
		btnNewButton.setWidth("100%");

		btnNewButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateStats();
			}
		});

		Label lblRune = new Label("Rune 1:");
		lblRune.setWordWrap(false);
		lblRune.setStyleName("boldText");
		filterTable.setWidget(1, 2, lblRune);

		rune1 = new ListBox();
		filterTable.setWidget(1, 3, rune1);

		Label lblRune_1 = new Label("Rune 2:");
		lblRune_1.setWordWrap(false);
		lblRune_1.setStyleName("boldText");
		filterTable.setWidget(1, 4, lblRune_1);

		rune2 = new ListBox();
		filterTable.setWidget(1, 5, rune2);

//		Label lblRune_2 = new Label("Rune 3:");
//		lblRune_2.setWordWrap(false);
//		lblRune_2.setStyleName("boldText");
//		filterTable.setWidget(1, 6, lblRune_2);
//
//		rune3 = new ListBox();
//		filterTable.setWidget(1, 7, rune3);
//		filterTable.getFlexCellFormatter().setColSpan(1, 0, 2);

		buildTable = new FlexTable();
		buildTable.setStyleName("statsTable");

		ScrollPanel scroll = new ScrollPanel();
		scroll.setWidget(buildTable);
		verticalPanel.add(scroll);
		scroll.setSize("975px", "269px");

		Anchor lblSentryRune = new Anchor("Sentry Rune");
		lblSentryRune.setWordWrap(false);
		lblSentryRune.setStyleName("boldText");
		lblSentryRune.setHref("javascript: return false;");
		buildTable.setWidget(0, 0, lblSentryRune);

		lblSentryRune.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sortStats(StatSorter.SENTRY_RUNE);
			}
		});

		Anchor lblSkill_3 = new Anchor("Skills/Runes");
		lblSkill_3.setHref("javascript: return false;");
		lblSkill_3.setWordWrap(false);
		lblSkill_3.setStyleName("boldText");
		buildTable.setWidget(0, 1, lblSkill_3);

		lblSkill_3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sortStats(StatSorter.SKILLS);
			}
		});
		buildTable.getCellFormatter().setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_CENTER);
		
				Anchor lblCount = new Anchor("Count");
				lblCount.setHref("javascript: return false;");
				lblCount.setWordWrap(false);
				lblCount.setStyleName("boldText");
				buildTable.setWidget(0, 2, lblCount);

		lblCount.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sortStats(StatSorter.COUNT);
			}
		});

		flexTable.getFlexCellFormatter().setColSpan(2, 0, 2);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0,
				HasVerticalAlignment.ALIGN_MIDDLE);

		disableListeners = true;

		int row = 1;
		int col = 3;

		for (StatCategory c : StatCategory.values()) {
			Label label = new Label(c.getDescription());
			label.setWordWrap(false);

			if ((row % 2) == 0)
				mainTable.getRowFormatter().addStyleName(row, "even");
			else
				mainTable.getRowFormatter().addStyleName(row, "odd");

			for (int i = 0; i < 4; i++) {
				Label label3 = new Label("Loading...");
				label3.addStyleName("right");
				mainTable.setWidget(row, i + 1, label3);
			}

			mainTable.setWidget(row++, 0, label);

			FlexTable table = new FlexTable();
			buildTable.setWidget(0, col, table);

			
			Anchor avg = new Anchor("Avg");
			avg.setWordWrap(false);
			avg.setHref("javascript: return false;");
			avg.setStyleName("boldText");
			
			Anchor max = new Anchor("Max");
			max.setWordWrap(false);
			max.setHref("javascript: return false;");
			max.setStyleName("boldText");

			Label split = new Label("/");
			split.setWordWrap(false);
			split.setStyleName("boldText");

			table.setWidget(0, 0, avg);
			table.setWidget(0, 1, split);
			table.setWidget(0, 2, max);
			table.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
			table.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
			table.getFlexCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
			
			Label label2 = new Label(c.getDescription());
			label2.setWordWrap(true);
			label2.setStyleName("boldText");

			table.setWidget(1, 0, label2);
			table.getFlexCellFormatter().setColSpan(1, 0, 3);
			table.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
			
			buildTable.getFlexCellFormatter().setHorizontalAlignment(0, col,
					HasHorizontalAlignment.ALIGN_CENTER);

			final StatCategory cat = c;

			avg.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					sortStats(new StatSorter.AverageCategorySorter(cat));
				}
			});

			max.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					sortStats(new StatSorter.MaxCategorySorter(cat));
				}
			});

			col++;
		}

		skills = new ListBox[] { skill1, skill2 }; //, skill3 };
		runes = new ListBox[] { rune1, rune2 }; //, rune3 };

		for (int i = 0; i < skills.length; i++) {
			populateSkillsAndRunes(i);
			final int j = i;

			skills[i].addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					if (!disableListeners) {
						skillChanged(j);
					}

				}
			});

		}

		disableListeners = false;
	}

	protected void copyBuild() {

		if (this.actionListener != null) {
			Build build = this.actionListener.getBuild();
			setBuild(build);
		}
	}

	private void setBuild(Build build) {
		this.selectRune(this.sentryRune, build.getSentryRune());
		this.sentry.setValue(build.isSentry());

		this.disableListeners = true;

		for (int i = 0; i < skills.length; i++) {
			this.selectSkill(this.skills[i], null);
			this.populateRunes(this.runes[i], null);
		}

		int n = 0;
		for (SkillAndRune sk : build.getSkills()) {
			this.selectSkill(skills[n], sk.getSkill());
			this.populateRunes(runes[n], sk.getSkill());
			this.selectRune(runes[n], sk.getRune());
			n++;
		}

		this.disableListeners = false;
	}

	private void selectRune(ListBox list, Rune rune) {

		for (int i = 0; i < list.getItemCount(); i++) {
			String value = list.getValue(i);

			if (value.equals(rune.name())) {
				list.setSelectedIndex(i);
				return;
			}

		}

		list.setSelectedIndex(0);
	}

	private void selectSkill(ListBox list, ActiveSkill skill) {

		for (int i = 0; i < list.getItemCount(); i++) {
			String value = list.getValue(i);

			if (skill != null) {
				if (value.equals(skill.name())) {
					list.setSelectedIndex(i);
					return;
				}
			} else {
				if ((value == null) || (value.trim().length() == 0)) {
					list.setSelectedIndex(i);
					return;
				}
			}

		}

		list.setSelectedIndex(0);
	}

	public void updateStats() {

		Rune sentryRune = getSelectedRune(this.sentryRune);
		ActiveSkill[] skills = new ActiveSkill[2];
		Rune[] runes = new Rune[2];

		for (int i = 0; i < skills.length; i++) {
			skills[i] = getSelectedSkill(i);
			runes[i] = getSelectedRune(i);
		}

		Service.getInstance().getStats(sentryRune, skills, runes,
				new DefaultCallback<DBStatistics>() {

					@Override
					protected void doOnSuccess(DBStatistics result) {
						showStats(result);
					}
				});

	}

	protected void sortStats(StatSorter sorter) {
		Collections.sort(statList, sorter);

		while (buildTable.getRowCount() > 1)
			buildTable.removeRow(1);

		int row = 1;
		for (StatHolder h : statList) {

			for (int n = 0; n < 4; n++) {
				if ((row % 2) == 0) {
					buildTable.getRowFormatter()
							.addStyleName((row * 4) - n, "even");
				} else {
					buildTable.getRowFormatter().addStyleName((row * 4) - n, "odd");
				}
				
				buildTable.getRowFormatter().setVerticalAlign((row * 4) - n,
						HasVerticalAlignment.ALIGN_TOP);
			}

			final Build build = h.build;
			Statistics s = h.stats;

			Rune r = build.getSentryRune();
			Anchor runeLabel = new Anchor(r.getLongName());
			runeLabel.setWordWrap(false);
			runeLabel.addStyleName("center");
			runeLabel.setTarget("_blank");

			if (r != Rune.None)
				runeLabel.setHref(ActiveSkill.SENTRY.getUrl() + "#"
						+ r.getSlug() + "+");
			else
				runeLabel.setHref(ActiveSkill.SENTRY.getUrl());

			buildTable.setWidget((row * 4) - 3, 0, runeLabel);
			buildTable.getFlexCellFormatter().setRowSpan((row * 4) - 3, 0, 4);
			buildTable.getFlexCellFormatter().setHorizontalAlignment((row * 4) - 3, 0, HasHorizontalAlignment.ALIGN_CENTER);

			SkillAndRune[] slist = build.getSkillsAsArray();

			int col = 1;
			int subRow = 3;
			
			for (int n = 0; n < 3; n++) {

				if (n < slist.length) {
					ActiveSkill skill = slist[n].getSkill();
					Rune rune = slist[n].getRune();

					String url = skill.getUrl();

					if ((rune != null) && (rune != Rune.None)) {
						url += ("#" + rune.getSlug() + "+");
					}

					Anchor slabel = new Anchor(slist[n].getSkill()
							.getShortName() + "/" + rune.getLongName());
					slabel.setWordWrap(false);
					slabel.addStyleDependentName("center");
					slabel.setTarget("_blank");
					slabel.setHref(url);
					buildTable.setWidget((row * 4) - subRow, (n == 0) ? 1 : 0, slabel);

				} else if (n == 0) {
					Label slabel = new Label("None");
					slabel.setWordWrap(false);
					slabel.addStyleDependentName("center");
					buildTable.setWidget((row * 4) - subRow, (n == 0) ? 1 : 0, slabel);
				}

				buildTable.getFlexCellFormatter().setHorizontalAlignment((row * 4) - subRow, (n == 0) ? 1 : 0, HasHorizontalAlignment.ALIGN_CENTER);

				subRow--;
			}

			Anchor copy = new Anchor("copy this build");
			copy.setHref("javascript: return false;");
			copy.setWordWrap(false);
			copy.setTitle("Copy this build");
			copy.addStyleName("center");
			buildTable.setWidget(row * 4, 0, copy);
			buildTable.getFlexCellFormatter().setHorizontalAlignment(
					row * 4, 0, HasHorizontalAlignment.ALIGN_CENTER);

			copy.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (actionListener != null)
						actionListener.setBuild(build);

				}
			});

			col++;
			Label label = new Label(Util.format(s.total));
			label.addStyleName("right");
			buildTable.setWidget((row * 4) - 3, col, label);
			buildTable.getFlexCellFormatter().setRowSpan((row * 4) - 3, col, 4);

			col++;
			for (StatCategory c : StatCategory.values()) {

				Double avg = s.average.get(c);
				Label valueLabel1 = new Label(Util.format(Math.round(avg)) + "(avg)");
				valueLabel1.addStyleName("right");
				buildTable.setWidget((row * 4) - 3, col, valueLabel1);

				final DpsTableEntry entry = s.max.get(c);
				double value = c.getValue(entry);
				Label valueLabel = new Label(Util.format(Math.round(value)) + "(max)");
				valueLabel.addStyleName("right");
				buildTable.setWidget((row * 4) - 2, col - 2, valueLabel);

				String name = entry.getRealm() + "/" + entry.getProfile() + "-" + entry.getTag();
				Anchor anchor = new Anchor(name);
				anchor.setWordWrap(false);
				anchor.setTitle("View this profile on battle.net");
				anchor.setTarget("_blank");
				anchor.addStyleName("center");
				anchor.setHref(ClientUtils.getProfileUrl(entry));
				buildTable.setWidget((row * 4) - 1, col - 2, anchor);
				buildTable.getFlexCellFormatter().setHorizontalAlignment(
						(row * 4) - 1, col - 2,
						HasHorizontalAlignment.ALIGN_CENTER);

				Anchor imp = new Anchor("import");
				imp.setWordWrap(false);
				imp.setTitle("Import this profile");
				imp.setHref("javascript: return false;");
				imp.addStyleName("center");
				buildTable.setWidget(row * 4, col - 2, imp);
				buildTable.getFlexCellFormatter().setHorizontalAlignment(
						row * 4, col - 2, HasHorizontalAlignment.ALIGN_CENTER);

				imp.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						importEntry(entry);

					}
				});
				col++;
			}

			row++;
		}
	}

	protected void showStats(DBStatistics stats) {

		this.statList = new Vector<StatHolder>(stats.builds.size());

		for (Map.Entry<Build, Statistics> e : stats.builds.entrySet()) {
			StatHolder h = new StatHolder();
			h.build = e.getKey();
			h.stats = e.getValue();
			statList.add(h);
		}

		totalLabel.setText(Util.format(stats.stats.total));

		int row = 1;
		for (StatCategory c : StatCategory.values()) {
			final DpsTableEntry entry = stats.stats.max.get(c);
			Double average = stats.stats.average.get(c);
			double max = c.getValue(entry);

			Label label1 = new Label(Util.format(Math.round(average)));
			label1.addStyleName("right");
			Label label2 = new Label(Util.format(Math.round(max)));
			label2.addStyleName("right");
			mainTable.setWidget(row, 1, label1);
			mainTable.setWidget(row, 2, label2);
			Anchor anchor = new Anchor(entry.getRealm().name() + "/" + entry.getProfile() + "-" + entry.getTag());
			anchor.setTarget("_blank");
			anchor.setTitle("View this profile on battle.net");
			anchor.setHref(ClientUtils.getProfileUrl(entry));
			mainTable.setWidget(row, 3, anchor);

			Anchor imp = new Anchor("import");
			imp.setTitle("Import this profile");
			imp.setHref("javascript: return false;");
			imp.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					importEntry(entry);

				}
			});
			mainTable.setWidget(row, 4, imp);

			row++;
		}

		sortStats(StatSorter.COUNT);
	}

	protected void importEntry(DpsTableEntry entry) {

		if (this.actionListener != null) {
			this.actionListener.closePanel();
			this.actionListener.importEntry(entry);
		}

	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	public interface ActionListener {
		void importEntry(DpsTableEntry entry);

		void setBuild(Build build);

		Build getBuild();

		void closePanel();
	}

	protected void skillChanged(int j) {
		populateRunes(j);
	}

	private void populateSkillsAndRunes(int i) {
		populateSkills(i);
		populateRunes(i);
		populateRunes(sentryRune, ActiveSkill.SENTRY);
	}

	private final ActiveSkill[] spenders = { ActiveSkill.CA, ActiveSkill.CHAK,
			ActiveSkill.EA, ActiveSkill.IMP, ActiveSkill.MS, ActiveSkill.HA, ActiveSkill.ES, ActiveSkill.BOLAS, ActiveSkill.EF, ActiveSkill.GRENADE };

	private void populateSkills(int i) {
		ListBox list = skills[i];
		list.clear();

		list.addItem(ActiveSkill.Any.getLongName(), ActiveSkill.Any.name());
		list.addItem("None", "");

		for (ActiveSkill a : spenders)
			list.addItem(a.getLongName(), a.name());

		list.setSelectedIndex(0);
	}

	ActiveSkill getSelectedSkill(int i) {
		ListBox list = skills[i];
		int index = list.getSelectedIndex();

		if (index < 0)
			return null;

		String value = list.getValue(index);

		if ((value == null) || (value.trim().length() == 0))
			return null;

		return ActiveSkill.valueOf(value);
	}

	Rune getSelectedRune(int i) {
		ListBox list = runes[i];

		return getSelectedRune(list);
	}

	Rune getSelectedRune(ListBox list) {
		int index = list.getSelectedIndex();

		if (index < 0)
			return null;

		String value = list.getValue(index);

		if ((value == null) || (value.trim().length() == 0))
			return null;

		return Rune.valueOf(value);
	}

	private void populateRunes(int i) {
		ListBox list = runes[i];
		ActiveSkill skill = getSelectedSkill(i);

		populateRunes(list, skill);
	}

	private void populateRunes(ListBox list, ActiveSkill skill) {
		list.clear();

		if (skill == null) {
			list.addItem(Rune.None.getLongName(), Rune.None.name());
		} else {
			list.addItem(Rune.All_Runes.getLongName(), Rune.All_Runes.name());

			if (skill != ActiveSkill.Any) {
				for (Rune r : skill.getRunes()) {
					list.addItem(r.getLongName(), r.name());
				}
			}
		}

		list.setSelectedIndex(0);
	}
}
