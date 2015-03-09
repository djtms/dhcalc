package com.dawg6.web.sentry.client;

import java.beans.Beans;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.common.util.AsyncTaskHandler;
import com.dawg6.gwt.common.util.DefaultCallback;
import com.dawg6.web.sentry.client.SavePanel.FormListener;
import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.BreakPoint;
import com.dawg6.web.sentry.shared.calculator.Build;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.Damage;
import com.dawg6.web.sentry.shared.calculator.DamageHolder;
import com.dawg6.web.sentry.shared.calculator.DamageMultiplier;
import com.dawg6.web.sentry.shared.calculator.DamageSource;
import com.dawg6.web.sentry.shared.calculator.DamageType;
import com.dawg6.web.sentry.shared.calculator.ExportData;
import com.dawg6.web.sentry.shared.calculator.FiringData;
import com.dawg6.web.sentry.shared.calculator.FormData;
import com.dawg6.web.sentry.shared.calculator.GemLevel;
import com.dawg6.web.sentry.shared.calculator.GemSkill;
import com.dawg6.web.sentry.shared.calculator.MultipleSummary;
import com.dawg6.web.sentry.shared.calculator.ProfileHelper;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.SkillAndRune;
import com.dawg6.web.sentry.shared.calculator.SkillSet;
import com.dawg6.web.sentry.shared.calculator.Target;
import com.dawg6.web.sentry.shared.calculator.TargetSize;
import com.dawg6.web.sentry.shared.calculator.Util;
import com.dawg6.web.sentry.shared.calculator.Version;
import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.Const;
import com.dawg6.web.sentry.shared.calculator.d3api.Hero;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.dawg6.web.sentry.shared.calculator.stats.DpsTableEntry;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPanel extends BasePanel {
	private final Label sheetDps;
	private final Label aps;
	private final ListBox skill1;
	private final ListBox rune1;
//	private final ListBox skill3;
//	private final ListBox rune3;
	private final ListBox sentryRunes;
	private final Label sentryAps;
	private final Label breakPoint;
	private final Label lblNewLabel_5;
	private final Label aps30;
	private final Label totalDamage;
	private final Label dps;
	private final FlexTable damageLog;
	private final VerticalPanel outputPanel;
	private final FlexTable summary;
	private final TextBox battleTag;
	private final TextBox tagNumber;
	private final ListBox heroList;
	private String profile;
	private String server;
	private int tag;
	protected HeroProfile hero;
	private final Label weaponDamage;
	private final Label actualAps;
	private final DPSCalculator calculator;
	private final FlexTable skillSummary;
	private final Anchor skill1Label;
//	private final Anchor skill2Label;
//	private final Anchor skill3Label;
	private final Anchor rune1Label;
//	private final Anchor rune3Label;
	private final Anchor sentryRuneLabel;
	private final ListBox realms;
	private final Anchor profileLink;
	protected CareerProfile career;
	protected Realm realm;
	private final Label dexterity;
	private final Label critChance;
	private final Label critDamage;
	private final Label avgWeaponDamage;
	private final PassivesPanel passives;
	private final SituationalPanel situational;
	private final SkillDamagePanel skillDamage;
	private final DamageTypePanel typeDamage;
	private final GemsPanel gemPanel;
	private final ItemPanel itemPanel;
	private final SkillsPanel skills;
	private Integer heroId;
	private final HorizontalPanel profileLinks;
	private FormData formData;
	private ExportData exportData;
	private final ParagonPanel paragonPanel;
	private final CDRPanel cdrPanel;
	private final RCRPanel rcrPanel;
	private final Label rawCDRLabel;
	private final Label effectiveCDRLabel;
	private final Label sentryCDLabel;
	private double rawCdr;
	private double effCdr;
	private double sentryCD;
	private final Label sentryApsLabel;
	private final Label bpLabel;
	private final Label petApsLabel;
	private final BuffPanel buffPanel;
	private final SavePanel savePanel;
	private Label sentryDps;
	private PlayerBuffPanel playerBuffPanel;
	private FlexTable compareTable;
	private final CompareData[] compareData = { null, null, null };
	private Label totalEliteDamage;
	private Label eliteDps;
	private Label eliteDamage;
	private Label smokeCDLabel;
	private double smokeCD;
	private CaptionPanel captionPanelTypeSummary;
	private CaptionPanel captionPanelSkillSummary;
	private CaptionPanel captionPanelDamageLog;
	private ListBox targetType;
	private Damage[] damage;
	private TreeMap<DamageType, DamageHolder> types;
	private TreeMap<DamageSource, DamageHolder> skillDamages;
	private double total;
	private double nonStacking;
	private CharacterData data = new CharacterData();
	private boolean firstTimeStats;
	protected DialogBox statsDialog;
	private ListBox[] skillBoxes;
	private ListBox[] runeBoxes;
	private Anchor[] skillLabels;
	private Anchor[] runeLabels;
	private FlexTable outputHeader;
	private HatredPanel hatredPanel;
	private Anchor skill2Label;
	private Anchor rune2Label;
	private ListBox skill2;
	private ListBox rune2;
	private Label rawRCRLabel;
	private Label effectiveRCRLabel;
	private double rawRcr;
	private double effRcr;

	public MainPanel() {
		VerticalPanel panel = new VerticalPanel();
		initWidget(panel);
		panel.setWidth("");

		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		panel.add(horizontalPanel_4);

		VerticalPanel verticalPanel_2 = new VerticalPanel();
		horizontalPanel_4.add(verticalPanel_2);

		CaptionPanel cptnpnlNewPanel_7 = new CaptionPanel("Battle.Net Import");
		verticalPanel_2.add(cptnpnlNewPanel_7);

		VerticalPanel verticalPanel_6 = new VerticalPanel();
		verticalPanel_6.setSpacing(5);
		verticalPanel_6.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		cptnpnlNewPanel_7.setContentWidget(verticalPanel_6);

		HorizontalPanel horizontalPanel_5 = new HorizontalPanel();
		horizontalPanel_5
				.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_5.setSpacing(5);
		verticalPanel_6.add(horizontalPanel_5);

		Label lblNewLabel_18 = new Label("Realm:");
		horizontalPanel_5.add(lblNewLabel_18);

		realms = new ListBox();
		horizontalPanel_5.add(realms);

		HorizontalPanel horizontalPanel_7 = new HorizontalPanel();
		horizontalPanel_7
				.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_7.setSpacing(5);
		verticalPanel_6.add(horizontalPanel_7);

		Label lblNewLabel_19 = new Label("Battle Tag:");
		lblNewLabel_19.setWordWrap(false);
		horizontalPanel_7.add(lblNewLabel_19);

		battleTag = new TextBox();
		battleTag.setText("BnetName");
		battleTag.setVisibleLength(15);
		horizontalPanel_7.add(battleTag);

		Label lblNewLabel_20 = new Label("#");
		horizontalPanel_7.add(lblNewLabel_20);

		tagNumber = new TextBox();
		tagNumber.setText("1234");
		tagNumber.setVisibleLength(8);
		horizontalPanel_7.add(tagNumber);

		Button fetchButton = new Button("New button");
		fetchButton.setText("Fetch");
		horizontalPanel_7.add(fetchButton);
		fetchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				fetchHeros(null);

			}
		});

		HorizontalPanel horizontalPanel_8 = new HorizontalPanel();
		horizontalPanel_8.setSpacing(5);
		horizontalPanel_8
				.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel_6.add(horizontalPanel_8);

		Label lblNewLabel_21 = new Label("Hero:");
		horizontalPanel_8.add(lblNewLabel_21);

		heroList = new ListBox();
		horizontalPanel_8.add(heroList);
		heroList.addItem("Enter BattleTag and Fetch", "");
		heroList.setSelectedIndex(0);

		Button importButton = new Button("New button");
		importButton.setText("Import");
		horizontalPanel_8.add(importButton);

		profileLinks = new HorizontalPanel();
		profileLinks.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		profileLinks.setSpacing(5);
		verticalPanel_6.add(profileLinks);

		profileLink = new Anchor("battle.net profile");
		profileLinks.add(profileLink);
		profileLink.setText("battle.net profile");
		profileLink.setHref("javascript: return false;");
		profileLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showProfile();
			}
		});

		Anchor anchor = new Anchor("paperdoll");
		anchor.setText("paperdoll");
		anchor.setHref("javascript: return false;");
		profileLinks.add(anchor);

		anchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showPaperdoll();
			}
		});

		paragonPanel = new ParagonPanel();
		verticalPanel_2.add(paragonPanel);

		this.paragonPanel.getParagonIAS().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners)
					updateDps();
			}
		});

		this.paragonPanel.getParagonHatred().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners) {
					updateHatred();
				}
			}
		});
		
		this.paragonPanel.getParagonCDR().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners)
					updateDps();
			}
		});

		this.paragonPanel.getParagonRCR().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners)
					updateDps();
			}
		});

		savePanel = new SavePanel();
		verticalPanel_2.add(savePanel);

		savePanel.setFormListener(new FormListener() {

			@Override
			public FormData getFormData() {
				return MainPanel.this.getFormData();
			}

			@Override
			public void setFormData(FormData data) {
				MainPanel.this.restoreData(data);
			}
		});

		CaptionPanel cptnpnlNewPanel_4 = new CaptionPanel("Character Data");
		verticalPanel_2.add(cptnpnlNewPanel_4);

		FlexTable grid_1 = new FlexTable();
		grid_1.setCellPadding(5);
		cptnpnlNewPanel_4.setContentWidget(grid_1);
		Label label = new Label("Sheet DPS: ");
		label.setWordWrap(false);
		grid_1.setWidget(0, 0, label);
		label.setWidth("");

		sheetDps = new Label("0.0", false);
		sheetDps.addStyleName("boldText");
		grid_1.setWidget(0, 1, sheetDps);

		Label lblWeaponDamage = new Label("Weapon Damage:");
		lblWeaponDamage.setWordWrap(false);
		grid_1.setWidget(0, 2, lblWeaponDamage);
		lblWeaponDamage.setWidth("");

		avgWeaponDamage = new Label("0.0", false);
		avgWeaponDamage.setStyleName("boldText");
		grid_1.setWidget(0, 3, avgWeaponDamage);

		Label lblNewLabel = new Label("Attacks Per Second:");
		lblNewLabel.setWordWrap(false);
		grid_1.setWidget(1, 0, lblNewLabel);

		aps = new Label("0.0", false);
		aps.addStyleName("boldText");
		grid_1.setWidget(1, 1, aps);

		Label lblCritChance = new Label("Crit Chance:");
		lblCritChance.setWordWrap(false);
		grid_1.setWidget(1, 2, lblCritChance);

		critChance = new Label("0.0", false);
		critChance.setStyleName("boldText");
		grid_1.setWidget(1, 3, critChance);

		Label lblDexterity = new Label("Dexterity:");
		lblDexterity.setWordWrap(false);
		grid_1.setWidget(2, 0, lblDexterity);

		dexterity = new Label("0.0", false);
		dexterity.setStyleName("boldText");
		grid_1.setWidget(2, 1, dexterity);

		Label lblCritHitDamage = new Label("Crit Hit Damage:");
		lblCritHitDamage.setWordWrap(false);
		grid_1.setWidget(2, 2, lblCritHitDamage);

		critDamage = new Label("0.0", false);
		critDamage.setStyleName("boldText");
		grid_1.setWidget(2, 3, critDamage);

		Label lblRawCdr = new Label("Raw CDR:");
		lblRawCdr.setWordWrap(false);
		grid_1.setWidget(3, 0, lblRawCdr);

		rawCDRLabel = new Label("0%", false);
		rawCDRLabel.setStyleName("boldText");
		grid_1.setWidget(3, 1, rawCDRLabel);

		Label lblEfgfectiveCdr = new Label("Effective CDR:");
		lblEfgfectiveCdr.setWordWrap(false);
		grid_1.setWidget(3, 2, lblEfgfectiveCdr);

		effectiveCDRLabel = new Label("0%", false);
		effectiveCDRLabel.setStyleName("boldText");
		grid_1.setWidget(3, 3, effectiveCDRLabel);

		Label lblRawRcr = new Label("Raw RCR:");
		lblRawRcr.setWordWrap(false);
		grid_1.setWidget(4, 0, lblRawRcr);

		rawRCRLabel = new Label("0%", false);
		rawRCRLabel.setStyleName("boldText");
		grid_1.setWidget(4, 1, rawRCRLabel);

		Label lblEfgfectiveRcr = new Label("Effective RCR:");
		lblEfgfectiveRcr.setWordWrap(false);
		grid_1.setWidget(4, 2, lblEfgfectiveRcr);

		effectiveRCRLabel = new Label("0%", false);
		effectiveRCRLabel.setStyleName("boldText");
		grid_1.setWidget(4, 3, effectiveRCRLabel);

		Label lblPetAps = new Label("Pet APS:");
		grid_1.setWidget(5, 0, lblPetAps);

		petApsLabel = new Label("0.0", false);
		petApsLabel.setStyleName("boldText");
		grid_1.setWidget(5, 1, petApsLabel);

		Label lblSentryCooldown = new Label("Sentry Cooldown:");
		lblSentryCooldown.setWordWrap(false);
		grid_1.setWidget(5, 2, lblSentryCooldown);

		Label lblSmokeCooldown = new Label("Smoke Screen Cooldown:");
		lblSmokeCooldown.setWordWrap(false);
		grid_1.setWidget(6, 2, lblSmokeCooldown);

		sentryCDLabel = new Label("0.0 sec", false);
		sentryCDLabel.setStyleName("boldText");
		grid_1.setWidget(5, 3, sentryCDLabel);

		smokeCDLabel = new Label("0.0 sec", false);
		smokeCDLabel.setStyleName("boldText");
		grid_1.setWidget(6, 3, smokeCDLabel);

		Label label_3 = new Label("Sentry APS:");
		grid_1.setWidget(6, 0, label_3);

		sentryApsLabel = new Label("0.0", false);
		sentryApsLabel.setStyleName("boldText");
		grid_1.setWidget(6, 1, sentryApsLabel);

		Label label_4 = new Label("Break Point:");
		label_4.setWordWrap(false);
		grid_1.setWidget(7, 2, label_4);

		bpLabel = new Label("1", false);
		bpLabel.setStyleName("boldText");
		grid_1.setWidget(7, 3, bpLabel);

		Label label_7 = new Label("Sentry Base DPS:");
		label_7.setWordWrap(false);
		grid_1.setWidget(7, 0, label_7);

		sentryDps = new Label("0");
		sentryDps.setTitle("Base Sentry DPS (includes Crit, Dex)");
		sentryDps.setStyleName("boldText");
		grid_1.setWidget(7, 1, sentryDps);

		Button calcDps = new Button("Calculator...");
		grid_1.setWidget(8, 2, calcDps);
		grid_1.getFlexCellFormatter().setColSpan(8, 2, 2);
		grid_1.getCellFormatter().setHorizontalAlignment(8, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		grid_1.getCellFormatter().setVerticalAlignment(8, 2,
				HasVerticalAlignment.ALIGN_MIDDLE);

		CaptionPanel captionPanel = new CaptionPanel("Compare Builds");
		verticalPanel_2.add(captionPanel);

		compareTable = new FlexTable();
		captionPanel.setContentWidget(compareTable);
		compareTable.setCellPadding(2);

		for (int i = 0; i < 3; i++) {
			Anchor label_1 = new Anchor("Clear");
			label_1.setHref("javascript: return false;");
			label_1.setTitle("Click to clear this build");
			compareTable.setWidget(6, (i * 2) + 1, label_1);
			compareTable.getFlexCellFormatter().setWidth(6, (i * 2) + 2, "5px");
			compareTable.getFlexCellFormatter().setHorizontalAlignment(6,
					(i * 2) + 1, HasHorizontalAlignment.ALIGN_CENTER);
			final int which = i;

			label_1.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					clearBuild(which);
				}
			});
		}

		Button button_6 = new Button("Compare...");
		compareTable.setWidget(0, 0, button_6);
		button_6.setTitle("Click to see differences between each build");
		compareTable.getFlexCellFormatter().setRowSpan(0, 0, 2);

		button_6.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				compareBuilds();
			}
		});

		Anchor button_3 = new Anchor("Set");
		button_3.setHref("javascript: return false;");
		button_3.setTitle("Click to store the current build for comparison");
		compareTable.setWidget(0, 1, button_3);
		compareTable.getFlexCellFormatter().setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

		button_3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				storeBuild(0);
			}
		});

		Anchor button_4 = new Anchor("Restore");
		button_4.setHref("javascript: return false;");
		button_4.setTitle("Click to retrieve this build");
		compareTable.setWidget(1, 0, button_4);
		compareTable.getFlexCellFormatter().setHorizontalAlignment(1, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		button_4.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadBuild(0);
			}
		});

		Anchor button_5 = new Anchor("Set");
		button_5.setHref("javascript: return false;");
		button_5.setTitle("Click to store the current build for comparison");
		compareTable.setWidget(0, 3, button_5);
		compareTable.getFlexCellFormatter().setHorizontalAlignment(0, 3,
				HasHorizontalAlignment.ALIGN_CENTER);

		button_5.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				storeBuild(1);
			}
		});

		Anchor button = new Anchor("Restore");
		button.setHref("javascript: return false;");
		button.setTitle("Click to retrieve this build");
		compareTable.setWidget(1, 2, button);
		compareTable.getFlexCellFormatter().setHorizontalAlignment(1, 2,
				HasHorizontalAlignment.ALIGN_CENTER);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadBuild(1);
			}
		});

		Anchor button_1 = new Anchor("Set");
		button_1.setHref("javascript: return false;");
		button_1.setTitle("Click to store the current build for comparison");
		compareTable.setWidget(0, 5, button_1);
		compareTable.getFlexCellFormatter().setHorizontalAlignment(0, 5,
				HasHorizontalAlignment.ALIGN_CENTER);

		button_1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				storeBuild(2);
			}
		});

		Anchor button_2 = new Anchor("Restore");
		button_2.setHref("javascript: return false;");
		button_2.setTitle("Click to retrieve this build");
		compareTable.setWidget(1, 4, button_2);
		compareTable.getFlexCellFormatter().setHorizontalAlignment(1, 4,
				HasHorizontalAlignment.ALIGN_CENTER);

		button_2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadBuild(2);
			}
		});

		Label label_8 = new Label("Base Sentry DPS:");
		label_8.setWordWrap(false);
		label_8.setStyleName("boldText");
		compareTable.setWidget(2, 0, label_8);

		Label lblNoData = new Label("No Data");
		lblNoData.setWordWrap(false);
		lblNoData.setStyleName("dpsCol");
		compareTable.setWidget(2, 1, lblNoData);

		Label lblNoData_1 = new Label("No Data");
		lblNoData_1.setWordWrap(false);
		lblNoData_1.setStyleName("dpsCol");
		compareTable.setWidget(2, 3, lblNoData_1);

		Label label_9 = new Label("No Data");
		label_9.setWordWrap(false);
		label_9.setStyleName("dpsCol");
		compareTable.setWidget(2, 5, label_9);

		Label label_11 = new Label("Break Point:");
		label_11.setWordWrap(false);
		label_11.setStyleName("boldText");
		compareTable.setWidget(3, 0, label_11);

		Label lblNoData_2 = new Label("No Data");
		lblNoData_2.setWordWrap(false);
		lblNoData_2.setStyleName("dpsCol");
		compareTable.setWidget(3, 1, lblNoData_2);

		Label lblNoData_3 = new Label("No Data");
		lblNoData_3.setWordWrap(false);
		lblNoData_3.setStyleName("dpsCol");
		compareTable.setWidget(3, 3, lblNoData_3);

		Label label_10 = new Label("No Data");
		label_10.setWordWrap(false);
		label_10.setStyleName("dpsCol");
		compareTable.setWidget(3, 5, label_10);

		Label label_14 = new Label("Total (Non-Elite) DPS:");
		label_14.setWordWrap(false);
		label_14.setStyleName("boldText");
		compareTable.setWidget(4, 0, label_14);

		Label lblNoData_4 = new Label("No Data");
		lblNoData_4.setWordWrap(false);
		lblNoData_4.setStyleName("dpsCol");
		compareTable.setWidget(4, 1, lblNoData_4);

		Label lblNoData_5 = new Label("No Data");
		lblNoData_5.setWordWrap(false);
		lblNoData_5.setStyleName("dpsCol");
		compareTable.setWidget(4, 3, lblNoData_5);

		Label label_12 = new Label("No Data");
		label_12.setWordWrap(false);
		label_12.setStyleName("dpsCol");
		compareTable.setWidget(4, 5, label_12);

		Label label_14a = new Label("Total (Elite) DPS:");
		label_14a.setWordWrap(false);
		label_14a.setStyleName("boldText");
		compareTable.setWidget(5, 0, label_14a);

		Label lblNoData_4a = new Label("No Data");
		lblNoData_4a.setWordWrap(false);
		lblNoData_4a.setStyleName("dpsCol");
		compareTable.setWidget(5, 1, lblNoData_4a);

		Label lblNoData_5a = new Label("No Data");
		lblNoData_5a.setWordWrap(false);
		lblNoData_5a.setStyleName("dpsCol");
		compareTable.setWidget(5, 3, lblNoData_5a);

		Label label_12a = new Label("No Data");
		label_12a.setWordWrap(false);
		label_12a.setStyleName("dpsCol");
		compareTable.setWidget(5, 5, label_12a);

		calcDps.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showDpsCalculator();
			}
		});

		importButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				importHero();
			}
		});

		this.paragonPanel.getParagonCC().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners)
					updateDps();
			}
		});

		this.paragonPanel.getParagonCHD().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners)
					updateDps();
			}
		});

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		horizontalPanel_4.add(verticalPanel_1);

		CaptionPanel cptnpnlNewPanel_1 = new CaptionPanel("Sentry Skills");
		verticalPanel_1.add(cptnpnlNewPanel_1);

		FlexTable grid = new FlexTable();
		grid.setCellPadding(2);
		cptnpnlNewPanel_1.setContentWidget(grid);

		sentryRuneLabel = new Anchor("Sentry Rune:");
		sentryRuneLabel.setWordWrap(false);
		sentryRuneLabel.setTarget("_blank");
		grid.setWidget(0, 0, sentryRuneLabel);

		sentryRunes = new ListBox();
		sentryRunes.setSelectedIndex(0);
		grid.setWidget(0, 1, sentryRunes);
		sentryRunes.setTitle("The selected Rune for the Sentry skill.");

		skill1Label = new Anchor("Skill 1:");
		skill1Label.setWordWrap(false);
		skill1Label.setTarget("_blank");
		grid.setWidget(1, 0, skill1Label);

		skill1 = new ListBox();
		grid.setWidget(1, 1, skill1);
		skill1.setTitle("Hatred Spender/Generator.");

		rune1Label = new Anchor("Rune:");
		rune1Label.setWordWrap(false);
		rune1Label.setTarget("_blank");
		grid.setWidget(1, 2, rune1Label);

		rune1 = new ListBox();
		grid.setWidget(1, 3, rune1);
		rune1.setTitle("Selected rune for this Hatred Spender.");

		skill2Label = new Anchor("Skill 2:");
		skill2Label.setWordWrap(false);
		skill2Label.setTarget("_blank");
		grid.setWidget(2, 0, skill2Label);

		skill2 = new ListBox();
		grid.setWidget(2, 1, skill2);
		skill2.setTitle("Hatred Spender/Generator.");

		rune2Label = new Anchor("Rune:");
		rune2Label.setWordWrap(false);
		rune2Label.setTarget("_blank");
		grid.setWidget(2, 2, rune2Label);

		rune2 = new ListBox();
		grid.setWidget(2, 3, rune2);
		rune2.setTitle("Selected rune for this Hatred Spender.");
//
//		skill3Label = new Anchor("Skill 3:");
//		skill3Label.setWordWrap(false);
//		skill3Label.setTarget("_blank");
//		grid.setWidget(3, 0, skill3Label);
//
//		skill3 = new ListBox();
//		grid.setWidget(3, 1, skill3);
//		skill3.setTitle("Hatred Spender.");
//
//		rune3Label = new Anchor("Rune:");
//		rune3Label.setWordWrap(false);
//		rune3Label.setTarget("_blank");
//		grid.setWidget(3, 2, rune3Label);
//
//		rune3 = new ListBox();
//		grid.setWidget(3, 3, rune3);
//		rune3.setTitle("Selected rune for this Hatred Spender.");

		skills = new SkillsPanel();
		verticalPanel_1.add(skills);

		situational = new SituationalPanel();
		verticalPanel_1.add(situational);

		gemPanel = new GemsPanel();
		verticalPanel_1.add(gemPanel);

		VerticalPanel verticalPanel_3 = new VerticalPanel();
		horizontalPanel_4.add(verticalPanel_3);

		typeDamage = new DamageTypePanel();
		verticalPanel_3.add(typeDamage);

		skillDamage = new SkillDamagePanel();
		verticalPanel_3.add(skillDamage);

		itemPanel = new ItemPanel();
		verticalPanel_3.add(itemPanel);

		itemPanel.getTnt().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (!disableListeners) {
					disableListeners = true;
					calculator.setTnt(itemPanel.getTnt().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = true;
				}
			}
		});

		itemPanel.getTntPercent().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners) {
					disableListeners = true;
					calculator.setTntPercent(itemPanel.getTntPercent()
							.getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});

		playerBuffPanel = new PlayerBuffPanel();
		verticalPanel_1.add(playerBuffPanel);

		playerBuffPanel.getCalcWolfButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculateWolfUptime();
			}
		});

		passives = new PassivesPanel();
		verticalPanel_3.add(passives);

		this.passives.getBloodVengeance().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					updateHatred();
				}
			}});

		passives.getArchery().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (!disableListeners) {
					calculator.setArchery(passives.getArchery().getValue());
					calculator.saveForm();
					updateDpsLabels();
				}
			}
		});

		passives.getSteadyAim().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					calculator.setSteadyAim(passives.getSteadyAim().getValue());
					calculator.saveForm();
					updateDpsLabels();
				}
			}
		});

		playerBuffPanel.getBbv().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.getBbv().setValue(
							playerBuffPanel.getBbv().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		playerBuffPanel.getBbvUptime().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.getBbvUptime().setValue(
							playerBuffPanel.getBbvUptime().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		playerBuffPanel.getValor().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.getValor().setValue(
							playerBuffPanel.getValor().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		playerBuffPanel.getValorUptime().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.getValorUptime().setValue(
							playerBuffPanel.getValorUptime().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		playerBuffPanel.getRetribution().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.getRetribution().setValue(
							playerBuffPanel.getRetribution().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		playerBuffPanel.getRetributionUptime().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						if (!disableListeners) {
							disableListeners = true;
							calculator.getRetributionUptime().setValue(
									playerBuffPanel.getRetributionUptime()
											.getValue());
							calculator.saveForm();
							updateDpsLabels();
							disableListeners = false;
						}
					}
				});

		VerticalPanel vpanel = new VerticalPanel();
		horizontalPanel_4.add(vpanel);
		
		cdrPanel = new CDRPanel();
		vpanel.add(cdrPanel);

		rcrPanel = new RCRPanel();
		vpanel.add(rcrPanel);

		hatredPanel = new HatredPanel();
		vpanel.add(hatredPanel);
		
		buffPanel = new BuffPanel();
		vpanel.add(buffPanel);

		buffPanel.getAnatomy().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					calculator.setAnatomy(buffPanel.getAnatomy().getValue());
					calculator.saveForm();
					updateDpsLabels();
				}
			}
		});

		buffPanel.getFocusedMind().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					calculator.setFocusedMind(buffPanel.getFocusedMind()
							.getValue());
					calculator.saveForm();
					updateDpsLabels();
				}
			}
		});

		buffPanel.getHysteria().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					calculator.setHysteria(buffPanel.getHysteria().getValue());
					calculator.saveForm();
					updateDpsLabels();
				}
			}
		});

		gemPanel.getGogokStacks().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.setGogokStacks(gemPanel.getGogokStacks()
							.getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		gemPanel.getPainEnhancerStacks().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.setPainEnhancerStacks(gemPanel
							.getPainEnhancerStacks().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		gemPanel.getPainEnhancerLevel().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.setPainEnhancerLevel(gemPanel
							.getPainEnhancerLevel().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		gemPanel.getGogok().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.setGogok(gemPanel.getGogok().getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});
		gemPanel.getPainEnhancer().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.setPainEnhancer(gemPanel.getPainEnhancer()
							.getValue());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});

		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Output");
		panel.add(cptnpnlNewPanel);
		cptnpnlNewPanel.setWidth("");

		VerticalPanel verticalPanel_5 = new VerticalPanel();
		cptnpnlNewPanel.setContentWidget(verticalPanel_5);
		verticalPanel_5.setSize("100%", "3cm");

		HorizontalPanel horizontalPanel_19 = new HorizontalPanel();
		verticalPanel_5.add(horizontalPanel_19);
		horizontalPanel_19
				.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_19.setSpacing(5);

		Button calcButton = new Button("New button");
		horizontalPanel_19.add(calcButton);
		calcButton.setText("Calculate");
		calcButton.setTitle("Press to calculate Damage");

		Button legendButton = new Button("New button");
		legendButton.setText("Legend...");
		horizontalPanel_19.add(legendButton);

		outputHeader = new FlexTable();
		outputHeader.setCellPadding(2);
		verticalPanel_5.add(outputHeader);

		Label lblNewLabel_27 = new Label("Average Weapon Damage:");
		outputHeader.setWidget(0, 0, lblNewLabel_27);
		lblNewLabel_27.setWordWrap(false);

		weaponDamage = new Label("00000");
		outputHeader.setWidget(0, 1, weaponDamage);
		weaponDamage.setStyleName("boldText");

		Label lblNewLabel_3 = new Label("Pet APS:");
		outputHeader.setWidget(0, 2, lblNewLabel_3);
		lblNewLabel_3.setWordWrap(false);

		sentryAps = new Label("00000");
		outputHeader.setWidget(0, 3, sentryAps);
		sentryAps.setStyleName("boldText");
		sentryAps.setWordWrap(false);

		Label lblNewLabel_4 = new Label("Break Point:");
		outputHeader.setWidget(0, 4, lblNewLabel_4);
		lblNewLabel_4.setWordWrap(false);

		breakPoint = new Label("000000");
		outputHeader.setWidget(0, 5, breakPoint);
		breakPoint.setStyleName("boldText");
		breakPoint.setWordWrap(false);

		Label lblNewLabel_29 = new Label("Sentry APS:");
		outputHeader.setWidget(0, 6, lblNewLabel_29);

		Label lblNewLabel_29a = new Label("# Sentries:");
		outputHeader.setWidget(1, 6, lblNewLabel_29a);

		actualAps = new Label("00000");
		outputHeader.setWidget(0, 7, actualAps);
		actualAps.setStyleName("boldText");

		lblNewLabel_5 = new Label("Attacks Per " + FiringData.DURATION
				+ " Seconds:");
		outputHeader.setWidget(0, 8, lblNewLabel_5);
		lblNewLabel_5.setWordWrap(false);

		aps30 = new Label("00000");
		outputHeader.setWidget(0, 9, aps30);
		aps30.setStyleName("boldText");
		aps30.setWordWrap(false);

		Label lblNewLabel_6 = new Label(
				"Total (Non-Elite) Damage over "
						+ FiringData.DURATION + " seconds:");
		outputHeader.setWidget(1, 0, lblNewLabel_6);
		lblNewLabel_6.setWordWrap(false);

		totalDamage = new Label("00000");
		outputHeader.setWidget(1, 1, totalDamage);
		totalDamage.setStyleName("boldText");

		Label lblNewLabel_7 = new Label("(Non-Elite) DPS:");
		outputHeader.setWidget(1, 2, lblNewLabel_7);
		lblNewLabel_7.setWordWrap(false);

		dps = new Label("00000");
		outputHeader.setWidget(1, 3, dps);
		dps.setStyleName("boldText");

		Label lblNewLabel_6a = new Label(
				"Total (Elite) Damage over "
						+ FiringData.DURATION + " seconds:");
		outputHeader.setWidget(2, 0, lblNewLabel_6a);
		lblNewLabel_6a.setWordWrap(false);

		totalEliteDamage = new Label("00000");
		outputHeader.setWidget(2, 1, totalEliteDamage);
		totalEliteDamage.setStyleName("boldText");

		Label lblNewLabel_7a = new Label("(Elite) DPS:");
		outputHeader.setWidget(2, 2, lblNewLabel_7a);
		lblNewLabel_7a.setWordWrap(false);

		Label lblNewLabel_7b = new Label("Elite Damage:");
		outputHeader.setWidget(2, 4, lblNewLabel_7b);
		lblNewLabel_7b.setWordWrap(false);

		eliteDps = new Label("00000");
		outputHeader.setWidget(2, 3, eliteDps);
		eliteDps.setStyleName("boldText");

		eliteDamage = new Label("00000");
		outputHeader.setWidget(2, 5, eliteDamage);
		eliteDamage.setStyleName("boldText");

		final Legend legend = new Legend();
		final BreakpointData bpData = new BreakpointData();

		Label label_13 = new Label("Show Details for Damage against: ");
		label_13.setWordWrap(false);
		outputHeader.setWidget(3, 0, label_13);

		targetType = new ListBox();
		targetType.setWidth("100%");
		outputHeader.setWidget(3, 1, targetType);

		targetType.addItem("Non-Elite", Boolean.FALSE.toString());
		targetType.addItem("Elite", Boolean.TRUE.toString());
		targetType.setSelectedIndex(0);

		targetType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners) {
					saveFields(getField(targetType));
					updateTargetType();
				}
			}
		});

		Button bpButton = new Button("New button");
		bpButton.setText("Break Points...");
		horizontalPanel_19.add(bpButton);

		bpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ApplicationPanel.showDialogBox("Break Points", bpData,
						ApplicationPanel.OK, null);
			}
		});

		Button skillButton = new Button("New button");
		skillButton.setText("Skills...");
		horizontalPanel_19.add(skillButton);

		final SkillData skillData = new SkillData();

		skillButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ApplicationPanel.showDialogBox("Skills", skillData,
						ApplicationPanel.OK, null);
			}
		});

//		for (int i = 1; i <= 3; i++) {
//			Button spenderButton = new Button("New button");
//			final String title = String.valueOf(i) + " Spender"
//					+ ((i > 1) ? "s" : "");
//			spenderButton.setText(title + "...");
//			horizontalPanel_19.add(spenderButton);
////			final SpenderData spenders = new SpenderData(i);
//
//			spenderButton.addClickHandler(new ClickHandler() {
//
//				@Override
//				public void onClick(ClickEvent event) {
////					ApplicationPanel.showDialogBox(title, spenders,
////							ApplicationPanel.OK, null);
//				}
//			});
//		}

		final AboutDialog about = new AboutDialog();
		final StatsPanel stats = new StatsPanel();

		stats.setActionListener(new StatsPanel.ActionListener() {

			@Override
			public void importEntry(DpsTableEntry entry) {
				MainPanel.this.importEntry(entry);
			}

			@Override
			public void closePanel() {
				if (statsDialog != null) {
					statsDialog.hide();
					statsDialog = null;
				}
			}

			@Override
			public Build getBuild() {
				return MainPanel.this.getBuild();
			}

			@Override
			public void setBuild(Build build) {
				MainPanel.this.setBuild(build);

			}
		});

		outputPanel = new VerticalPanel();
		verticalPanel_5.add(outputPanel);

		HorizontalPanel horizontalPanel_9 = new HorizontalPanel();
		outputPanel.add(horizontalPanel_9);

		captionPanelTypeSummary = new CaptionPanel(
				"Damage Type Summary (Non-Elite, "
						+ FiringData.DURATION + " seconds)");
		horizontalPanel_9.add(captionPanelTypeSummary);

		summary = new FlexTable();
		summary.setCellPadding(5);
		summary.setBorderWidth(1);
		summary.setStyleName("outputTable");
		captionPanelTypeSummary.setContentWidget(summary);

		Label lblNewLabel_17 = new Label("Type");
		lblNewLabel_17.setWordWrap(false);
		summary.setWidget(0, 0, lblNewLabel_17);

		Label label_1 = new Label("# Attacks");
		label_1.setWordWrap(false);
		label_1.setStyleName("dpsHeader");
		summary.setWidget(0, 1, label_1);

		Label label_2 = new Label("Per Attack");
		label_2.setWordWrap(false);
		label_2.setStyleName("dpsHeader");
		summary.setWidget(0, 2, label_2);

		Label lblTotalDamage = new Label("Total");
		lblTotalDamage.setStyleName("dpsHeader");
		lblTotalDamage.setWordWrap(false);
		summary.setWidget(0, 3, lblTotalDamage);
		summary.getColumnFormatter().addStyleName(1, "dpsCol");

		Label lblDps_1 = new Label("DPS");
		lblDps_1.setStyleName("dpsHeader");
		lblDps_1.setWordWrap(false);
		summary.setWidget(0, 4, lblDps_1);
		summary.getColumnFormatter().addStyleName(2, "dpsCol");

		Label lblOfTotal = new Label("% of Total");
		lblOfTotal.setStyleName("dpsHeader");
		lblOfTotal.setWordWrap(false);
		summary.setWidget(0, 5, lblOfTotal);

		captionPanelSkillSummary = new CaptionPanel(
				"Skill Damage Summary (Non-Elite, "
						+ FiringData.DURATION + " seconds)");
		horizontalPanel_9.add(captionPanelSkillSummary);

		skillSummary = new FlexTable();
		skillSummary.setStyleName("outputTable");
		skillSummary.setCellPadding(5);
		skillSummary.setBorderWidth(1);
		captionPanelSkillSummary.setContentWidget(skillSummary);

		Label lblSkill_2 = new Label("Skill");
		lblSkill_2.setWordWrap(false);
		skillSummary.setWidget(0, 0, lblSkill_2);

		Label lblAttacks = new Label("# Attacks");
		lblAttacks.setWordWrap(false);
		lblAttacks.setStyleName("dpsHeader");
		skillSummary.setWidget(0, 1, lblAttacks);

		Label lblPerAttack = new Label("Per Attack");
		lblPerAttack.setWordWrap(false);
		lblPerAttack.setStyleName("dpsHeader");
		skillSummary.setWidget(0, 2, lblPerAttack);

		Label lblTotal = new Label("Total");
		lblTotal.setStyleName("dpsHeader");
		lblTotal.setWordWrap(false);
		skillSummary.setWidget(0, 3, lblTotal);
		skillSummary.getColumnFormatter().addStyleName(1, "dpsCol");

		Label label_5 = new Label("DPS");
		label_5.setStyleName("dpsHeader");
		label_5.setWordWrap(false);
		skillSummary.setWidget(0, 4, label_5);
		skillSummary.getColumnFormatter().addStyleName(2, "dpsCol");

		Label label_6 = new Label("% of Total");
		label_6.setStyleName("dpsHeader");
		label_6.setWordWrap(false);
		skillSummary.setWidget(0, 5, label_6);

		skillSummary.getColumnFormatter().addStyleName(3, "dpsCol");
		skillSummary.getRowFormatter().addStyleName(0, "headerRow");
		summary.getColumnFormatter().addStyleName(3, "dpsCol");
		summary.getRowFormatter().addStyleName(0, "headerRow");

		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		outputPanel.add(horizontalPanel_2);

		captionPanelDamageLog = new CaptionPanel(
				"Damage Log (Non-Elite, " + FiringData.DURATION
						+ " seconds)");
		horizontalPanel_2.add(captionPanelDamageLog);

		damageLog = new FlexTable();
		damageLog.setCellPadding(5);
		damageLog.setBorderWidth(1);
		captionPanelDamageLog.setContentWidget(damageLog);

		Label lblNewLabel_8a = new Label("Shooter", false);
		lblNewLabel_8a.setWordWrap(false);
		damageLog.setWidget(0, 0, lblNewLabel_8a);

		Label lblNewLabel_8 = new Label("Skill", false);
		lblNewLabel_8.setWordWrap(false);
		damageLog.setWidget(0, 1, lblNewLabel_8);

		Label lblNewLabel_9 = new Label("Rune", false);
		lblNewLabel_9.setWordWrap(false);
		damageLog.setWidget(0, 2, lblNewLabel_9);

		Label lblNewLabel_10 = new Label("Type", false);
		lblNewLabel_10.setWordWrap(false);
		damageLog.setWidget(0, 3, lblNewLabel_10);

		Label lblNewLabel_11 = new Label("Damage", false);
		lblNewLabel_11.setWordWrap(false);
		lblNewLabel_11.setStyleName("dpsHeader");
		damageLog.setWidget(0, 4, lblNewLabel_11);
		damageLog.getColumnFormatter().addStyleName(3, "dpsCol");

		Label lblNewLabel_12 = new Label("Qty", false);
		lblNewLabel_12.setWordWrap(false);
		damageLog.setWidget(0, 5, lblNewLabel_12);

		Label lblNewLabel_12a = new Label("Hatred", false);
		lblNewLabel_12a.setWordWrap(false);
		damageLog.setWidget(0, 6, lblNewLabel_12a);
		damageLog.getColumnFormatter().addStyleName(6, "dpsCol");

		Label lblNewLabel_13 = new Label("Total Damage", false);
		lblNewLabel_13.setStyleName("dpsHeader");
		lblNewLabel_13.setWordWrap(false);
		damageLog.setWidget(0, 7, lblNewLabel_13);
		damageLog.getColumnFormatter().addStyleName(7, "dpsCol");

		Label lblNewLabel_14 = new Label("DPS", false);
		lblNewLabel_14.setStyleName("dpsHeader");
		lblNewLabel_14.setWordWrap(false);
		damageLog.setWidget(0, 8, lblNewLabel_14);
		damageLog.getColumnFormatter().addStyleName(8, "dpsCol");

		Label lblNewLabel_15 = new Label("% of Total", false);
		lblNewLabel_15.setStyleName("dpsHeader");
		lblNewLabel_15.setWordWrap(false);
		damageLog.setWidget(0, 9, lblNewLabel_15);
		damageLog.getColumnFormatter().addStyleName(9, "dpsCol");

		Label lblNewLabel_15b = new Label("Target", false);
		lblNewLabel_15b.setWordWrap(false);
		damageLog.setWidget(0, 10, lblNewLabel_15b);

		Label lblNewLabel_16 = new Label("Notes", false);
		lblNewLabel_16.setWordWrap(false);
		damageLog.setWidget(0, 11, lblNewLabel_16);

		Label lblNewLabel_28 = new Label("Calculations", false);
		lblNewLabel_28.setWordWrap(false);
		damageLog.setWidget(0, 12, lblNewLabel_28);

		damageLog.addStyleName("outputTable");
		damageLog.getRowFormatter().addStyleName(0, "headerRow");

		calculator = new DPSCalculator();

		ChangeHandler handler = new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateCDRLabels();
			}
		};

		ChangeHandler handler2 = new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateRCRLabels();
			}
		};

		ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateCDRLabels();
			}

		};

		ClickHandler clickHandler2 = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateRCRLabels();
			}

		};

		paragonPanel.getParagonCDR().addChangeHandler(handler);
		gemPanel.getGogok().addClickHandler(clickHandler);
		gemPanel.getGogokLevel().addChangeHandler(handler);
		gemPanel.getGogokStacks().addChangeHandler(handler);
		gemPanel.getPainEnhancer().addClickHandler(clickHandler);
		gemPanel.getPainEnhancerLevel().addChangeHandler(handler);
		gemPanel.getPainEnhancerStacks().addChangeHandler(handler);
		cdrPanel.getDiamond().addChangeHandler(handler);
		cdrPanel.getLeorics().addClickHandler(clickHandler);
		cdrPanel.getLeoricsLevel().addChangeHandler(handler);
		cdrPanel.getShoulders().addChangeHandler(handler);
		cdrPanel.getAmulet().addChangeHandler(handler);
		cdrPanel.getGloves().addChangeHandler(handler);
		cdrPanel.getRing1().addChangeHandler(handler);
		cdrPanel.getRing2().addChangeHandler(handler);
		cdrPanel.getBelt().addChangeHandler(handler);
		cdrPanel.getWeapon().addChangeHandler(handler);
		cdrPanel.getQuiver().addChangeHandler(handler);
		cdrPanel.getBorn().addClickHandler(clickHandler);
		cdrPanel.getCrimson().addClickHandler(clickHandler);

		paragonPanel.getParagonRCR().addChangeHandler(handler2);
		rcrPanel.getPridesFall().addClickHandler(clickHandler2);
		rcrPanel.getShoulders().addChangeHandler(handler2);
		rcrPanel.getAmulet().addChangeHandler(handler2);
		rcrPanel.getGloves().addChangeHandler(handler2);
		rcrPanel.getRing1().addChangeHandler(handler2);
		rcrPanel.getRing2().addChangeHandler(handler2);
		rcrPanel.getBelt().addChangeHandler(handler2);
		rcrPanel.getWeapon().addChangeHandler(handler2);
		rcrPanel.getQuiver().addChangeHandler(handler2);
		rcrPanel.getCrimson().addClickHandler(clickHandler2);

		Button exportButton = new Button("New button");
		exportButton.setText("Export to Excel...");
		horizontalPanel_19.add(exportButton);

		exportButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				exportExcel();
			}

		});

		Button statsButton = new Button("Statistics...");
//		horizontalPanel_8.add(statsButton);

		firstTimeStats = true;

		statsButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				statsDialog = ApplicationPanel.showDialogBox("Statistics",
						stats, ApplicationPanel.OK, null);

				if (firstTimeStats) {
					firstTimeStats = false;
					stats.updateStats();
				}
			}
		});

		Button aboutButton = new Button("New button");
		aboutButton.setText("About...");
		horizontalPanel_19.add(aboutButton);

		calcButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				calculate();
			}
		});

		aboutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ApplicationPanel.showDialogBox("About", about,
						ApplicationPanel.OK, null);
			}
		});

		legendButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ApplicationPanel.showDialogBox("Legend", legend,
						ApplicationPanel.OK, null);
			}
		});

		for (Realm r : Realm.values()) {
			realms.addItem(r.getDisplayName(), r.name());
		}

		skillBoxes = new ListBox[] { skill1, skill2 }; //, skill2, skill3 };
		runeBoxes = new ListBox[] { rune1, rune2 }; //, rune2, rune3 };

		skillLabels = new Anchor[] { skill1Label, skill2Label }; //, skill2Label, skill3Label };
		runeLabels = new Anchor[] { rune1Label, rune2Label }; //, rune2Label, rune3Label };
	}

	protected void updateHatred() {
		hatredPanel.getMaxHatred().setValue(150.0 + (paragonPanel.getParagonHatred().getValue() * 0.5) +
				(this.passives.getBloodVengeance().getValue() ? 25:0));
	}

	protected void setBuild(Build build) {

		this.disableListeners = true;

		this.setFieldValue(this.sentryRunes, build.getSentryRune().name());

		for (int i = 0; i < 3; i++) {
			this.setFieldValue(this.skillBoxes[i], null);
			this.setFieldValue(this.runeBoxes[i], Rune.None.name());
		}

		int n = 0;

		for (SkillAndRune sk : build.getSkills()) {
			this.setFieldValue(this.skillBoxes[n], sk.getSkill().name());
			this.setFieldValue(this.runeBoxes[n], sk.getRune().name());
			n++;
		}

		this.disableListeners = false;
	}

	protected Build getBuild() {

		Build build = new Build();
		build.setSentryRune(this.getRune(this.sentryRunes));
		build.setSkills(new TreeSet<SkillAndRune>());

		for (int i = 0; i < 3; i++) {
			ActiveSkill skill = this.getSkill(skillBoxes[i]);

			if (skill != null) {
				Rune rune = this.getRune(runeBoxes[i]);
				SkillAndRune sk = new SkillAndRune();
				sk.setSkill(skill);
				sk.setRune(rune);
				build.getSkills().add(sk);
			}
		}

		return build;
	}

	protected void importEntry(DpsTableEntry entry) {
		String battletag = entry.getBattletag();
		Realm realm = entry.getRealm();
		final String[] split1 = battletag.split("/");
		String[] split2 = split1[0].split("-");
		String profile = split2[0];
		String tag = split2[1];

		this.battleTag.setText(profile);
		this.tagNumber.setValue(tag);
		this.setFieldValue(this.realms, realm.name());

		this.paragonPanel.getParagonCC().setValue(entry.getParagon_cc());
		this.paragonPanel.getParagonCHD().setValue(entry.getParagon_chd());
		this.paragonPanel.getParagonCDR().setValue(entry.getParagon_cdr());
		this.paragonPanel.getParagonIAS().setValue(entry.getParagon_ias());
		this.paragonPanel.getParagonHatred().setValue(entry.getParagon_hatred());
		this.paragonPanel.getParagonRCR().setValue(entry.getParagon_rcr());

		this.fetchHeros(new AsyncTaskHandler() {

			@Override
			public void taskCompleted() {
				importHero(split1[1]);
			}
		});
	}

	protected void importHero(String id) {
		for (int i = 0; i < heroList.getItemCount(); i++) {
			String value = heroList.getValue(i);

			if (value.equals(id)) {
				heroList.setSelectedIndex(i);
				importHero();
			}
		}
	}

	protected void updateTargetType() {
		final AsyncTaskHandler dialog = super.showWaitDialogBox("Calculating",
				"Please wait...");

		Scheduler.get().scheduleDeferred(new Command() {

			@Override
			public void execute() {

				try {
					updateOutput();
				} finally {
					dialog.taskCompleted();
				}
			}
		});

	}

	boolean isEliteSelected() {
		int i = targetType.getSelectedIndex();

		return i > 0;
	}

	private static final int NUM_COMPARE_ROWS = 4;

	protected void clearBuild(int which) {
		compareData[which] = null;

		int col = which * 2 + 1;

		for (int row = 0; row < NUM_COMPARE_ROWS; row++) {
			Label label = (Label) compareTable.getWidget(row + 2, col);
			label.setText("No Data");
		}
	}

	protected void compareBuilds() {
		int count = 0;
		List<CompareData> list = new Vector<CompareData>();

		for (int i = 0; i < compareData.length; i++) {
			CompareData data = compareData[i];

			if (data != null) {
				count++;
				list.add(data);
			}
		}

		if (count == 0) {
			ApplicationPanel
					.showErrorDialog("Please select at least 2 builds to compare");
			return;
		}

		int numDiff = 0;

		FlexTable table = new FlexTable();
		table.setCellPadding(2);
		table.setBorderWidth(1);
		table.addStyleName("breakpointTable");
		ScrollPanel panel = new ScrollPanel();
		panel.setWidget(table);

		for (int i = 0; i < count; i++) {
			Label label = new Label("Build #" + (i + 1));
			label.setWordWrap(false);
			table.setWidget(0, i + 1, label);
			table.getFlexCellFormatter().setHorizontalAlignment(0, i + 1,
					HasHorizontalAlignment.ALIGN_CENTER);
		}

		Label l1 = new Label("Input Field");
		l1.setWordWrap(false);
		table.setWidget(0, 0, l1);
		table.getFlexCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_LEFT);

		table.getRowFormatter().addStyleName(0, "headerRow");

		int row = 1;

		List<Map<String, String>> formData = new Vector<Map<String, String>>();

		for (int i = 0; i < count; i++) {
			Map<String, String> map = new TreeMap<String, String>();
			map.putAll(list.get(i).formData.main);

			for (Map.Entry<String, String> e : list.get(i).formData.calculator
					.entrySet()) {
				String key = e.getKey();
				String value = e.getValue();

				if (key.startsWith("calc.")) {
					String k = key.substring(5);
					String old = map.get(k);

					if (old == null) {
						map.put(k, value);
					} else if (!old.equals(value)) {
						map.put(key, value);
					}
				} else {
					map.put(key, value);
				}
			}

			formData.add(map);
		}

		for (Map.Entry<String, String> e : formData.get(0).entrySet()) {
			List<String> values = new Vector<String>();
			String key = e.getKey();
			values.add(e.getValue());
			boolean diff = false;

			for (int i = 1; i < count; i++) {
				String value = formData.get(i).get(key);
				values.add(value);
				if (!diff && ((value == null) || !value.equals(e.getValue())))
					diff = true;
			}

			if (diff) {
				numDiff++;

				if ((row % 2) == 0) {
					table.getRowFormatter().addStyleName(row, "evenRow");
				} else {
					table.getRowFormatter().addStyleName(row, "oddRow");
				}

				Label keyLabel = new Label(key);
				keyLabel.setWordWrap(false);
				keyLabel.addStyleName("boldText");
				table.setWidget(row, 0, keyLabel);
				table.getFlexCellFormatter().setHorizontalAlignment(row, 0,
						HasHorizontalAlignment.ALIGN_LEFT);

				for (int i = 0; i < count; i++) {
					String value = values.get(i);

					if (value == null)
						value = "N/A";

					Label label = new Label(value);
					label.setWordWrap(false);
					table.setWidget(row, i + 1, label);
					table.getFlexCellFormatter().setHorizontalAlignment(row,
							i + 1, HasHorizontalAlignment.ALIGN_CENTER);
				}

				row++;
			}
		}

		if (numDiff == 0) {
			ApplicationPanel
					.showErrorDialog("There were no differences in the builds");
			return;
		} else {
			ApplicationPanel.showDialogBox("Compare Builds", panel,
					ApplicationPanel.OK, null);
		}
	}

	protected void loadBuild(int which) {

		CompareData data = compareData[which];

		if (data == null) {
			ApplicationPanel
					.showErrorDialog("No build data saved in this spot.");
			return;
		}

		this.restoreData(data.formData);
	}

	protected void storeBuild(final int which) {
		final AsyncTaskHandler dialog = super.showWaitDialogBox("Calculating",
				"Please wait...");

		Scheduler.get().scheduleDeferred(new Command() {

			@Override
			public void execute() {
				doCalculate(new AsyncTaskHandler() {

					@Override
					public void taskCompleted() {
						CompareData data = new CompareData();
						compareData[which] = data;

						data.formData = formData;
						data.exportData = exportData;

						int col = which * 2 + 1;
						int row = 2;

						Label baseDps = (Label) compareTable.getWidget(row++,
								col);
						Label bp = (Label) compareTable.getWidget(row++, col);
						Label dps = (Label) compareTable.getWidget(row++, col);
						Label edps = (Label) compareTable.getWidget(row++, col);

						baseDps.setText(Util.format(Math
								.round(data.exportData.sentryBaseDps)));
						bp.setText(String.valueOf(data.exportData.bp));
						dps.setText(Util.format(Math
								.round(data.exportData.sentryDps)));
						edps.setText(Util.format(Math
								.round(data.exportData.sentryEliteDps)));
						dialog.taskCompleted();
					}
				});
			}
		});

	}

	protected void calculateWolfUptime() {
		double wolfCd = 30.0 * (1.0 - this.effCdr);
		double uptime = Math.round(Math.min(10.0 / wolfCd, 1.0) * 10000.0) / 100.0;
		playerBuffPanel.getWolfUptime().setValue(uptime);
	}

	protected void exportExcel() {
		Service.getInstance().exportData(exportData,
				new DefaultCallback<String>() {

					@Override
					protected void doOnSuccess(String result) {
						MainPanel.saveFormData("sentry-calculator-export.xls",
								result);
					}
				});

	}

	protected void restoreData(FormData data) {

		if (data == null)
			data = new FormData();

		disableListeners = true;

		heroList.clear();
		heroList.addItem("Enter BattleTag and Fetch", "");

		calculator.restoreFormData(data.calculator);
		super.restoreFormData(data.main);

		this.career = null;
		this.hero = null;

		calculator.setTnt(itemPanel.getTnt().getValue());
		calculator.setGogok(gemPanel.getGogok().getValue());
		calculator.setGogokLevel(gemPanel.getGogokLevel().getValue());
		calculator.setGogokStacks(gemPanel.getGogokStacks().getValue());
		calculator.setPainEnhancer(gemPanel.getPainEnhancer().getValue());
		calculator.setPainEnhancerLevel(gemPanel.getPainEnhancerLevel()
				.getValue());
		calculator.setPainEnhancerStacks(gemPanel.getPainEnhancerStacks()
				.getValue());
		calculator.saveForm();
		calculator.calculate();

		disableListeners = false;

		this.heroList.setSelectedIndex(0);

		calculator.saveForm();
		this.saveForm();

		updateDps();
		calculate();

	}

	private FormData getFormData() {
		FormData data = new FormData();

		super.populateFormData(data.main);
		calculator.populateFormData(data.calculator);

		data.version = Version.getVersion();

		// Having a problem with non UTF-8 encoding in these
		// if (this.career != null)
		// data.career = this.career;
		//
		// if (this.hero != null)
		// data.hero = this.hero;

		return data;
	}

	protected void setRuneLabel(final Anchor label, final ListBox skills,
			final ListBox runes) {
		label.setTarget("_blank");
		runes.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners)
					updateRuneLabel(label, skills, runes);

			}
		});
		updateRuneLabel(label, skills, runes);
	}

	protected void setSkillLabel(final Anchor label, final ListBox skills) {
		label.setTarget("_blank");
		skills.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners)
					updateSkillLabel(label, skills);

			}
		});
		updateSkillLabel(label, skills);
	}

	protected void updateRuneLabel(Anchor label, ListBox skills, ListBox runes) {
		// Rune rune = this.getRune(runes);

		ActiveSkill skill = ActiveSkill.SENTRY;

		if (skills != null)
			skill = this.getSkill(skills);

		if (skill != null) {
			Rune rune = getRune(runes);

			label.setTarget("_blank");

			if ((rune != null) && (rune != Rune.None))
				label.setHref(skill.getUrl() + "#" + rune.getSlug() + "+");
			else
				label.setHref(skill.getUrl());
		} else {
			label.setHref("javascript: return false;");
			label.setTarget("_self");
		}
	}

	protected void updateSkillLabel(Anchor label, ListBox skills) {
		ActiveSkill skill = this.getSkill(skills);

		if (skill != null) {
			label.setHref(skill.getUrl());
			label.setTarget("_blank");
		} else {
			label.setHref("javascript: return false;");
			label.setTarget("_self");
		}
	}

	protected void showDpsCalculator() {

		ApplicationPanel.showDialogBox("DPS/Break Point Calculator",
				calculator, ApplicationPanel.OK + ApplicationPanel.CANCEL,
				new DialogBoxResultHandler() {

					@Override
					public void dialogBoxResult(int result) {

						if (result == ApplicationPanel.OK) {

							calculator.saveForm();

							MainPanel.this.disableListeners = true;

							MainPanel.this.paragonPanel.getParagonIAS()
									.setValue(calculator.getParagonIas());
							MainPanel.this.paragonPanel.getParagonCDR()
									.setValue(calculator.getParagonCDR());
							MainPanel.this.paragonPanel.getParagonCC()
									.setValue(calculator.getParagonCC());
							MainPanel.this.paragonPanel.getParagonCHD()
									.setValue(calculator.getParagonCD());
							MainPanel.this.paragonPanel.getParagonCHD()
								.setValue(calculator.getParagonHatred());
							MainPanel.this.paragonPanel.getParagonCHD()
								.setValue(calculator.getParagonRCR());
							MainPanel.this.passives.getArchery().setValue(
									calculator.getArchery());
							MainPanel.this.passives.getSteadyAim().setValue(
									calculator.getSteadyAim());
							MainPanel.this.buffPanel.getAnatomy().setValue(
									calculator.getAnatomy());
							MainPanel.this.buffPanel.getFocusedMind().setValue(
									calculator.getFocusedMind());
							MainPanel.this.buffPanel.getHysteria().setValue(
									calculator.getHysteria());
							MainPanel.this.gemPanel.getGogok().setValue(
									calculator.getGogok());
							MainPanel.this.gemPanel.getGogokLevel().setValue(
									calculator.getGogokLevel());
							MainPanel.this.gemPanel.getGogokStacks().setValue(
									calculator.getGogokStacks());
							MainPanel.this.gemPanel.getPainEnhancer().setValue(
									calculator.getPainEnhancer());
							MainPanel.this.gemPanel
									.getPainEnhancerLevel()
									.setValue(calculator.getPainEnhancerLevel());
							MainPanel.this.gemPanel.getPainEnhancerStacks()
									.setValue(
											calculator.getPainEnhancerStacks());
							MainPanel.this.itemPanel.getTnt().setValue(
									calculator.getTnt());
							MainPanel.this.itemPanel.getTntPercent().setValue(
									calculator.getTntPercent());

							updateDpsLabels();

							MainPanel.this.disableListeners = false;

							calculate();
						}
					}
				});

	}

	private Realm getSelectedRealm() {
		int i = realms.getSelectedIndex();
		String value = realms.getValue(i);

		return Realm.valueOf(value);
	}

	protected void fetchHeros(final AsyncTaskHandler fetchHandler) {
		realm = getSelectedRealm();
		career = null;
		hero = null;

		if ((battleTag.getText() == null)
				|| (battleTag.getText().trim().length() == 0)) {
			ApplicationPanel.showErrorDialog("Enter Battle Tag");
			return;
		}

		final String profile = battleTag.getText();

		if (tagNumber.getValue() == null) {
			ApplicationPanel.showErrorDialog("Enter Battle Tag Number");
			return;
		}

		final int tag = (int) getValue(this.tagNumber);

		Service.getInstance().getProfile(realm, profile, tag,
				new DefaultCallback<CareerProfile>() {

					@Override
					protected void doOnSuccess(CareerProfile result) {

						if ((result == null) || (result.code != null)) {
							String reason = (result != null) ? result.reason
									: "Unable to find profile";

							ApplicationPanel.showErrorDialog(
									"Error loading profile", reason);
							return;
						}

						MainPanel.this.career = result;
						MainPanel.this.profile = profile;
						MainPanel.this.server = server;
						MainPanel.this.tag = tag;

						boolean first = true;
						disableListeners = true;

						for (Hero h : result.heroes) {

							if (h.clazz
									.equalsIgnoreCase(Const.CLASS_DEMONHUNTER)) {

								if (first) {
									heroList.clear();
									first = false;
								}

								addHero(h);
							}

						}
						if (first) {
							heroList.clear();
							heroList.addItem(
									"No Demon Hunters found on profile", "");
						}

						heroList.setSelectedIndex(0);

						disableListeners = false;

						saveForm();

						if (fetchHandler != null)
							fetchHandler.taskCompleted();
					}
				});

	}

	protected void addHero(Hero h) {

		StringBuffer buf = new StringBuffer();

		buf.append(h.name + " " + h.level);

		if (h.paragonLevel > 0)
			buf.append("(" + h.paragonLevel + ")");

		if (h.hardcore)
			buf.append(" Hardcore");

		if (h.seasonal)
			buf.append(" Seasonal");
		
		if (h.dead)
			buf.append(" (RIP)");

		heroList.addItem(buf.toString(), String.valueOf(h.id));
	}

	protected void importHero() {

		int index = heroList.getSelectedIndex();

		if (index >= 0) {

			final String value = heroList.getValue(index);

			if (value.length() > 0) {

				heroId = Integer.valueOf(value);

				Service.getInstance().getHero(realm, profile, tag, heroId,
						new DefaultCallback<HeroProfile>() {

							@Override
							protected void doOnSuccess(final HeroProfile hero) {

								if ((hero == null) || (hero.code != null)) {

									String reason = (hero != null) ? hero.reason
											: "Unable to find profile";

									ApplicationPanel.showErrorDialog(
											"Error loading Profile", reason);
									return;
								}

								MainPanel.this.hero = hero;

								data = ProfileHelper
										.importHero(hero);

								data.setRealm(realm);
								data.setProfile(profile);
								data.setTag(tag);
								data.setHero(heroId);
								data.setParagonIAS(paragonPanel.getParagonIAS()
										.getValue());
								data.setParagonCDR(paragonPanel.getParagonCDR()
										.getValue());
								data.setParagonCC(paragonPanel.getParagonCC()
										.getValue());
								data.setParagonCHD(paragonPanel.getParagonCHD()
										.getValue());
								data.setParagonHatred(paragonPanel.getParagonHatred()
										.getValue());
								data.setParagonRCR(paragonPanel.getParagonRCR()
										.getValue());
								
								ProfileHelper.updateCdr(data);
								ProfileHelper.updateWeaponDamage(data);

								setHeroSkills();

								setElementalDamage();

								setSkillDamage();

								setGemDamage();

								setSkills();
								
								calculator.importHero(server, profile, tag,
										heroId, data);

								calculator.saveForm();

								MainPanel.this.passives.getArchery().setValue(
										calculator.isArchery());
								MainPanel.this.passives.getSteadyAim()
										.setValue(calculator.getSteadyAim());

								updateDps();

								doCalculate(new AsyncTaskHandler() {

									@Override
									public void taskCompleted() {

										Service.getInstance().logData(data,
												new DefaultCallback<Void>() {

													@Override
													public void doOnSuccess(
															Void result) {
														// nothing to do
													}
												});
									}
								});

							}
						});

			}

		}
	}

	private void setSkills() {
		
		Rune sentryRune = data.getSentryRune();
		
		this.setSentryRune(sentryRune.getLongName());
		
		Set<SkillAndRune> skills = data.getSkills();

		int n = 0;
		
		for (SkillAndRune skr : skills) {
			this.setSkillAndRune(skillLabels[n], runeLabels[n], skillBoxes[n], runeBoxes[n], skr.getSkill(), skr.getRune().getLongName());
			n++;
			
			if (n > skillLabels.length)
				break;
		}
		
		hatredPanel.getHatredPerSecond().setValue(data.getHatredPerSecond());
	}
	
	private void updateParagonPoints() {
		this.calculator.setParagonPoints(
				getValue(MainPanel.this.paragonPanel.getParagonIAS()),
				getValue(MainPanel.this.paragonPanel.getParagonCDR()),
				getValue(MainPanel.this.paragonPanel.getParagonCC()),
				getValue(MainPanel.this.paragonPanel.getParagonCHD()),
				getValue(MainPanel.this.paragonPanel.getParagonHatred()),
				getValue(MainPanel.this.paragonPanel.getParagonRCR()));
		this.calculator.saveForm();
	}

	@Override
	protected void setFieldValue(ListBox field, String value) {
		try {
			if (field == this.sentryRunes) {
				setRune(field, Rune.valueOf(value));
			} else if (field == cdrPanel.getDiamond()) {
				cdrPanel.setDiamond(GemLevel.valueOf(value));
			} else if (field == targetType) {
				if ((value == null) || value.equals(Boolean.FALSE.toString()))
					field.setSelectedIndex(0);
				else
					field.setSelectedIndex(1);
			} else if (field == situational.getTargetSize()) {
				situational.setTargetSize(TargetSize.valueOf(value));
			} else if (field == skills.getMfdRune()) {
				skills.setMarkedForDeathRune(Rune.valueOf(value));
			} else if (field == this.realms) {
				for (int i = 0; i < realms.getItemCount(); i++) {
					String v = realms.getValue(i);

					if (v.equals(value)) {
						realms.setSelectedIndex(i);
						return;
					}
				}

				realms.setSelectedIndex(0);

			} else if (field == this.skill1 || (field == this.skill2)) // || (field == this.skill3)) 
				{

				this.disableListeners = true;

				if ((value == null) || (value.trim().length() == 0)) {
					field.setSelectedIndex(0);
				} else {
					ActiveSkill skill = ActiveSkill.valueOf(value);

					this.selectSkill(field, skill);
					ListBox runes = rune1; //rune3;

					if (field == this.skill1)
						runes = rune1;
					else if (field == this.skill2)
						runes = rune2;

					this.setRunes(runes, skill);
				}

				this.disableListeners = false;

			} else if ((field == this.rune1) || (field == this.rune2))// || (field == this.rune3)) 
				{

				Rune rune = Rune.None;

				try {
					rune = Rune.valueOf(value);
				} catch (Exception e) {
				}

				this.setRune(field, rune);
			}
		} catch (Exception e) {
			field.setSelectedIndex(0);
		}
	}

	protected void setGemDamage() {

		this.gemPanel.getBot().setValue(data.isUseBaneOfTheTrapped());
		this.gemPanel.getEnforcer().setValue(data.isUseEnforcer());
		this.gemPanel.getBotp().setValue(data.isBotp());
		this.gemPanel.getBotLevel().setValue(data.getBaneOfTheTrappedLevel());
		this.gemPanel.getEnforcerLevel().setValue(data.getEnforcerLevel());
		this.gemPanel.getBotpLevel().setValue(data.getBotpLevel());
		this.gemPanel.getZeis().setValue(data.isZeis());
		this.gemPanel.getZeisLevel().setValue(data.getZeisLevel());
		this.gemPanel.getGogok().setValue(data.isGogok());
		this.gemPanel.getGogokLevel().setValue(data.getGogokLevel());
		this.gemPanel.getToxin().setValue(data.isToxin());
		this.gemPanel.getToxinLevel().setValue(data.getToxinLevel());
		this.gemPanel.getPainEnhancer().setValue(data.isPainEnhancer());
		this.calculator.setPainEnhancer(data.isPainEnhancer());
		this.gemPanel.getPainEnhancerLevel().setValue(data.getPainEnhancerLevel());
		this.calculator.setPainEnhancerLevel(data.getPainEnhancerLevel());
		this.calculator.setGogok(data.isGogok());
		this.calculator.setGogokLevel(data.getGogokLevel());

	}

	protected void updateDps() {
		updateParagonPoints();
		updateDpsLabels();
		updateCDRLabels();
		updateRCRLabels();
	}

	private void updateRCRLabels() {
		List<Double> list = new Vector<Double>();

		list.add(paragonPanel.getParagonRCR().getValue() * 0.002);

		if (rcrPanel.getPridesFall().getValue())
			list.add(0.30);

		list.add(rcrPanel.getShoulders().getValue() / 100.0);
		list.add(rcrPanel.getGloves().getValue() / 100.0);
		list.add(rcrPanel.getAmulet().getValue() / 100.0);
		list.add(rcrPanel.getRing1().getValue() / 100.0);
		list.add(rcrPanel.getRing2().getValue() / 100.0);
		list.add(rcrPanel.getBelt().getValue() / 100.0);
		list.add(rcrPanel.getWeapon().getValue() / 100.0);
		list.add(rcrPanel.getQuiver().getValue() / 100.0);

		if (rcrPanel.getCrimson().getValue())
			list.add(0.10);

		double rawRcr = 0.0;
		double effRcr = 0.0;

		boolean first = true;

		for (Double c : list) {
			rawRcr += c;

			if (first) {
				first = false;
				effRcr = 1.0 - c;
			} else {
				effRcr *= (1.0 - c);
			}
		}

		effRcr = 1.0 - effRcr;

		this.rawRcr = rawRcr;
		this.effRcr = effRcr;

		this.rawRCRLabel
				.setText(Util.format(Math.round(rawRcr * 10000.0) / 100.0)
						+ "%");
		this.effectiveRCRLabel
				.setText(Util.format(Math.round(effRcr * 10000.0) / 100.0)
						+ "%");
	}
	
	private void updateCDRLabels() {

		List<Double> list = new Vector<Double>();

		list.add(paragonPanel.getParagonCDR().getValue() * .002);

		if (gemPanel.getGogok().getValue()
				&& gemPanel.getGogokLevel().getValue() >= 25)
			list.add(gemPanel.getGogokStacks().getValue() * .01);

		if (cdrPanel.getLeorics().getValue())
			list.add(cdrPanel.getSelectedDiamond().getCdr()
					* (1 + ((double) cdrPanel.getLeoricsLevel().getValue() / 100.0)));
		else
			list.add(cdrPanel.getSelectedDiamond().getCdr());

		list.add(cdrPanel.getShoulders().getValue() / 100.0);
		list.add(cdrPanel.getGloves().getValue() / 100.0);
		list.add(cdrPanel.getAmulet().getValue() / 100.0);
		list.add(cdrPanel.getRing1().getValue() / 100.0);
		list.add(cdrPanel.getRing2().getValue() / 100.0);
		list.add(cdrPanel.getBelt().getValue() / 100.0);
		list.add(cdrPanel.getWeapon().getValue() / 100.0);
		list.add(cdrPanel.getQuiver().getValue() / 100.0);

		if (cdrPanel.getCrimson().getValue())
			list.add(0.10);

		if (cdrPanel.getBorn().getValue())
			list.add(0.10);

		double rawCdr = 0.0;
		double effCdr = 0.0;

		boolean first = true;

		for (Double c : list) {
			rawCdr += c;

			if (first) {
				first = false;
				effCdr = 1.0 - c;
			} else {
				effCdr *= (1.0 - c);
			}
		}

		effCdr = 1.0 - effCdr;

		this.rawCdr = rawCdr;
		this.effCdr = effCdr;
		this.sentryCD = 6.0 * (1 - effCdr);
		this.smokeCD = 2.0 * (1 - effCdr);

		this.rawCDRLabel
				.setText(Util.format(Math.round(rawCdr * 10000.0) / 100.0)
						+ "%");
		this.effectiveCDRLabel
				.setText(Util.format(Math.round(effCdr * 10000.0) / 100.0)
						+ "%");
		this.sentryCDLabel
				.setText(Util.format(Math.round(sentryCD * 100.0) / 100.0)
						+ " sec");
		this.smokeCDLabel
				.setText(Util.format(Math.round(smokeCD * 100.0) / 100.0)
						+ " sec");
	}

	protected void updateDpsLabels() {
		this.sheetDps.setText(Util.format(calculator.getSheetDps()));
		this.aps.setText(Util.format(calculator.getAps()));
		this.itemPanel.getTntPercent().setValue(calculator.getTntPercent());
		this.itemPanel.getTnt().setValue(calculator.getTnt());
		this.gemPanel.getGogok().setValue(calculator.getGogok());
		this.gemPanel.getGogokLevel().setValue(calculator.getGogokLevel());
		this.gemPanel.getGogokStacks().setValue(calculator.getGogokStacks());
		this.gemPanel.getPainEnhancer().setValue(calculator.getPainEnhancer());
		this.gemPanel.getPainEnhancerLevel().setValue(
				calculator.getPainEnhancerLevel());
		this.gemPanel.getPainEnhancerStacks().setValue(
				calculator.getPainEnhancerStacks());
		this.dexterity.setText(String.valueOf(calculator.getDexterity()));
		this.critChance.setText(Util.format(Math.round(calculator
				.getCritChance() * 1000.0) / 10.0) + "%");
		this.critDamage.setText(Util.format(Math.round(calculator
				.getCritDamage() * 100.0)) + "%");
		this.avgWeaponDamage.setText(Util.format(calculator
				.getAverageWeaponDamage()));
		this.passives.getArchery().setValue(calculator.getArchery());
		this.passives.getSteadyAim().setValue(calculator.getSteadyAim());
		double aps = this.calculator.getAps();
		double petIas = (this.itemPanel.getTnt().getValue() ? (this.itemPanel
				.getTntPercent().getValue() / 100.0) : 0.0);
		double petAps = aps * (1.0 + petIas);
		this.petApsLabel.setText(Util.format(petAps));

		BreakPoint bp = BreakPoint.get(petAps);
		this.bpLabel.setText(String.valueOf(bp.getBp()));
		double sentryAps = (double) bp.getQty() / (double) FiringData.DURATION;
		this.sentryApsLabel.setText(Util.format(sentryAps));
		this.sentryDps
				.setText(Util.format(Math.round(calculator.getSentryDps())));

		this.playerBuffPanel.getBbv().setValue(calculator.getBbv().getValue());
		this.playerBuffPanel.getBbvUptime().setValue(
				calculator.getBbvUptime().getValue());
		this.playerBuffPanel.getValor().setValue(
				calculator.getValor().getValue());
		this.playerBuffPanel.getValorUptime().setValue(
				calculator.getValorUptime().getValue());
		this.playerBuffPanel.getRetribution().setValue(
				calculator.getRetribution().getValue());
		this.playerBuffPanel.getRetributionUptime().setValue(
				calculator.getRetributionUptime().getValue());
	}

	protected void setSkillDamage() {

		getSetCDR(cdrPanel.getShoulders(), Const.SHOULDERS);
		getSetCDR(cdrPanel.getGloves(), Const.GLOVES);
		getSetCDR(cdrPanel.getRing1(), Const.RING1);
		getSetCDR(cdrPanel.getRing2(), Const.RING2);
		getSetCDR(cdrPanel.getBelt(), Const.BELT);
		getSetCDR(cdrPanel.getWeapon(), Const.WEAPON);
		getSetCDR(cdrPanel.getQuiver(), Const.QUIVER);
		getSetCDR(cdrPanel.getAmulet(), Const.AMULET);
		
		getSetSetCDR(cdrPanel.getBorn(), data.getSetCounts(), data.isRoyalRing(), Const.BORNS, 2);
		getSetSetCDR(cdrPanel.getCrimson(), data.getSetCounts(), data.isRoyalRing(),
				Const.CAPTAIN_CRIMSON, 2);

		this.cdrPanel.getLeorics().setValue(data.isLeorics());
		this.cdrPanel.getLeoricsLevel().setValue((int) (Math.round(data.getLeoricsPercent() * 100.0)));
		this.cdrPanel.setDiamond(data.getDiamond());

		getSetRCR(rcrPanel.getShoulders(), Const.SHOULDERS);
		getSetRCR(rcrPanel.getGloves(), Const.GLOVES);
		getSetRCR(rcrPanel.getRing1(), Const.RING1);
		getSetRCR(rcrPanel.getRing2(), Const.RING2);
		getSetRCR(rcrPanel.getBelt(), Const.BELT);
		getSetRCR(rcrPanel.getWeapon(), Const.WEAPON);
		getSetRCR(rcrPanel.getQuiver(), Const.QUIVER);
		getSetRCR(rcrPanel.getAmulet(), Const.AMULET);
		
		getSetSetRCR(rcrPanel.getCrimson(), data.getSetCounts(), data.isRoyalRing(),
				Const.CAPTAIN_CRIMSON, 3);

		this.rcrPanel.getPridesFall().setValue(data.isPridesFall());

		this.itemPanel.getTnt().setValue(data.isTnt());
		this.itemPanel.getTntPercent().setValue((int) (Math.round(data.getTntPercent() * 100.0)));
		this.itemPanel.getCalamity().setValue(data.isCalamityMdf());
		this.itemPanel.getBombadiers().setValue(data.isHasBombardiers());
		this.itemPanel.getSpines().setValue(data.isSpines());
		this.itemPanel.getKridershot().setValue(data.isKridershot());
		this.itemPanel.getSpinesHatred().setValue(data.getSpinesHatred());
		this.itemPanel.getKridershotHatred().setValue(data.getKridershotHatred());
		this.itemPanel.getMarauders().setValue(data.getNumMarauders());
		this.itemPanel.getEliteDamagePercent()
				.setValue((int) Math.round(data.getEliteDamage()));
		this.itemPanel.getMeticulousBolts().setValue(data.isMeticulousBolts());
		this.itemPanel.getMeticulousBoltsPercent().setValue(
				(int) (Math.round(data.getMeticulousBoltsPercent() * 100.0)));
		this.itemPanel.getStrongarm().setValue(data.isStrongarm());
		this.itemPanel.getStrongarmPercent().setValue(
				(int) Math.round(data.getStrongarmPercent() * 100.0));
		this.itemPanel.getHexingPants().setValue(data.isHexingPants());
		this.itemPanel.getHexingPantsPercent().setValue(
				(int) Math.round(data.getHexingPantsPercent() * 100.0));
		this.itemPanel.getHarrington().setValue(data.isHarrington());
		this.itemPanel.getHarringtonPercent().setValue(
				(int) Math.round(data.getHarringtonPercent() * 100.0));
		
		this.skillDamage.getSentryDamage().setValue((int)Math.round(data.getSentryDamage() * 100.0));
		this.skillDamage.getEaDamage().setValue((int)Math.round(data.getEaDamage() * 100.0));
		this.skillDamage.getChakDamage().setValue((int)Math.round(data.getChakDamage() * 100.0));
		this.skillDamage.getImpDamage().setValue((int)Math.round(data.getImpDamage() * 100.0));
		this.skillDamage.getMsDamage().setValue((int)Math.round(data.getMsDamage() * 100.0));
		this.skillDamage.getCaDamage().setValue((int)Math.round(data.getCaDamage() * 100.0));

		this.skillDamage.getHaDamage().setValue((int)Math.round(data.getHaDamage() * 100.0));
		this.skillDamage.getEsDamage().setValue((int)Math.round(data.getEsDamage() * 100.0));
		this.skillDamage.getBolasDamage().setValue((int)Math.round(data.getBolasDamage() * 100.0));
		this.skillDamage.getEfDamage().setValue((int)Math.round(data.getEfDamage() * 100.0));
		this.skillDamage.getGrenadeDamage().setValue((int)Math.round(data.getGrenadeDamage() * 100.0));
	}

	private void getSetSetCDR(SimpleCheckBox field,
			Map<String, Integer> setCounts, boolean royalRing, String name,
			int count) {

		boolean hasSet = false;

		Integer i = setCounts.get(name);

		if (i != null) {

			if ((i >= 2) && (royalRing))
				i++;

			hasSet = (i >= count);
		}

		field.setValue(hasSet);
	}

	private void getSetSetRCR(SimpleCheckBox field,
			Map<String, Integer> setCounts, boolean royalRing, String name,
			int count) {

		boolean hasSet = false;

		Integer i = setCounts.get(name);

		if (i != null) {

			if ((i >= 2) && (royalRing))
				i++;

			hasSet = (i >= count);
		}

		field.setValue(hasSet);
	}

	private void getSetRCR(NumberSpinner field, String slot) {

		Integer value = data.getRcrData().get(slot);
		
		if (value == null)
			value = 0;
		
		field.setValue(value);
	}

	private void getSetCDR(NumberSpinner field, String slot) {

		Integer value = data.getCdrData().get(slot);
		
		if (value == null)
			value = 0;
		
		field.setValue(value);
	}

	protected void setElementalDamage() {

		typeDamage.getColdDamage().setValue((int) (data.getColdDamage() * 100));
		typeDamage.getFireDamage().setValue((int) (data.getFireDamage() * 100));
		typeDamage.getPoisonDamage().setValue((int) (data.getPoisonDamage() * 100));
		typeDamage.getPhysicalDamage().setValue((int) (data.getPhysDamage() * 100));
		typeDamage.getLightningDamage().setValue((int) (data.getLightDamage() * 100));

	}

	protected void setHeroSkills() {

		this.passives.getBallistics().setValue(data.isBallistics());
		this.passives.getBloodVengeance().setValue(data.isBloodVengeance());
		this.passives.getNightStalker().setValue(data.isNightStalker());
		this.passives.getCtw().setValue(data.isCullTheWeak());
		this.passives.getGrenadier().setValue(data.isGrenadier());
		this.skills.getMfd().setValue(data.isMfdSkill());
		this.skills.setMarkedForDeathRune(data.getMfdRune());
		this.passives.getSteadyAim().setValue(data.isSteadyAim());
		this.passives.getAmbush().setValue(data.isAmbush());
		this.passives.getSingleOut().setValue(data.isSingleOut());
		this.playerBuffPanel.getWolf().setValue(data.isWolf());
		this.skills.getCaltrops().setValue(data.isCaltrops());
		this.passives.getCustomEngineering().setValue(data.isCustomEngineering());
		this.passives.getArchery().setValue(data.isArchery());
	}

	private void setSkillAndRune(Anchor skillLabel, Anchor runeLabel,
			ListBox skills, ListBox runes, ActiveSkill skill, String rune) {

		this.disableListeners = true;

		this.selectSkill(skills, skill);
		this.setRunes(runes, skill);
		this.updateSkillLabel(skillLabel, skills);

		if (skill != null) {
			for (Rune r : skill.getRunes()) {
				if (rune.equalsIgnoreCase(r.getLongName())) {
					setRune(runes, r);
					this.updateRuneLabel(runeLabel, skills, runes);
					break;
				}
			}
		} else {
			runes.clear();
			addRune(runes, Rune.None);
			runes.setSelectedIndex(0);
			this.updateRuneLabel(runeLabel, skills, runes);
		}

		this.disableListeners = false;

	}

	private void setSentryRune(String name) {

		for (Rune r : ActiveSkill.SENTRY.getRunes()) {
			if (name.equalsIgnoreCase(r.getLongName())) {
				setRune(sentryRunes, r);
				break;
			}
		}

	}

	protected void setValue(TextBox textBox, int value) {
		textBox.setText(String.valueOf(value));
	}

	protected void setValue(TextBox textBox, double value) {
		textBox.setText(String.valueOf(value));
	}

	protected void setValue(TextBox textBox, long value) {
		textBox.setText(String.valueOf(value));
	}

	protected void setValue(TextBox textBox, float value) {
		textBox.setText(String.valueOf(value));
	}

	@Override
	protected Field[] getFields() {
		return new Field[] {
				new Field(this.realms, "Realm", ""),
				new Field(this.battleTag, "BattleTag", "BnetName"),
				new Field(this.tagNumber, "BattleTagNumber", "1234"),
				new Field(this.paragonPanel.getParagonIAS(), "ParagonIas", "0"),
				new Field(this.paragonPanel.getParagonCDR(), "ParagonCDR", "0"),
				new Field(this.paragonPanel.getParagonCC(), "ParagonCC", "0"),
				new Field(this.paragonPanel.getParagonCHD(), "ParagonCD", "0"),
				new Field(this.paragonPanel.getParagonHatred(), "ParagonHatred", "0"),
				new Field(this.paragonPanel.getParagonRCR(), "ParagonRCR", "0"),
				new Field(this.itemPanel.getTnt(), "TnT",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getCalamity(), "Calamity",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getBombadiers(), "Bombadiers",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getSpines(), "Spines",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getKridershot(), "Kridershot",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getSpinesHatred(), "SpinesHatred",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getKridershotHatred(), "KridershotHatred",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getMarauders(), "Marauders",
						"6"),
				new Field(this.itemPanel.getCalamityUptime(), "CalamityUptime",
						"100"),
				new Field(this.skills.getMfd(), "MarkedForDeath",
						Boolean.FALSE.toString()),
				new Field(this.skills.getCaltrops(), "Caltrops",
						Boolean.FALSE.toString()),
				new Field(this.skills.getMfdRune(), "MarkedForDeathRune",
						Rune.None.name()),
				new Field(this.skills.getMfdUptime(), "MarkedForDeathUptime",
						"100"),
				new Field(this.skills.getCaltropsUptime(), "CaltropsUptime",
						"100"),
				new Field(this.hatredPanel.getMaxHatred(),
						"MaxHatred", "150"),
				new Field(this.hatredPanel.getHatredPerSecond(),
						"HatredPerSecond", "5.0"),
				new Field(this.skills.getMfdAddUptime(),
						"MarkedForDeathAddUptime", "100"),
				new Field(this.itemPanel.getTntPercent(), "TnTPercent", "35"),
				new Field(this.itemPanel.getMeticulousBolts(),
						"MeticulousBolts", Boolean.FALSE.toString()),

				new Field(this.itemPanel.getHarrington(), "Harrington",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getHexingPants(), "HexingPants",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getStrongarm(), "Strongarm",
						Boolean.FALSE.toString()),

				new Field(this.itemPanel.getHarringtonPercent(),
						"HarringtonPercent", "100"),
				new Field(this.itemPanel.getHexingPantsPercent(),
						"HexingPantsPercent", "20"),
				new Field(this.itemPanel.getStrongarmPercent(),
						"StrongarmPercent", "20"),

				new Field(this.itemPanel.getHarringtonUptime(),
						"HarringtonUptime", "0"),
				new Field(this.itemPanel.getHexingPantsUptime(),
						"HexingPantsUptime", "0"),
				new Field(this.itemPanel.getStrongarmUptime(),
						"StrongarmUptime", "0"),

				new Field(this.itemPanel.getMeticulousBoltsPercent(),
						"MeticulousBoltsPercent", "30"),
				new Field(this.situational.getPercentSlowedChilled(),
						"PercentSlowedChilled", "100"),
				new Field(this.situational.getPercentControlled(),
						"PercentControlled", "100"),
				new Field(this.situational.getAdditional(), "Additional", "5"),
				new Field(this.skillDamage.getSentryDamage(), "Sentry", "0"),
				new Field(this.skillDamage.getEaDamage(), "EA", "0"),
				new Field(this.skillDamage.getMsDamage(), "MS", "0"),
				new Field(this.skillDamage.getCaDamage(), "CA", "0"),
				new Field(this.skillDamage.getChakDamage(), "CHAK", "0"),
				new Field(this.skillDamage.getImpDamage(), "IMP", "0"),
				new Field(this.skillDamage.getHaDamage(), "HA", "0"),
				new Field(this.skillDamage.getEsDamage(), "ES", "0"),
				new Field(this.skillDamage.getBolasDamage(), "Bolas", "0"),
				new Field(this.skillDamage.getEfDamage(), "EF", "0"),
				new Field(this.skillDamage.getGrenadeDamage(), "Grenade", "0"),
				new Field(this.situational.getTargetSize(), "TargetSize",
						TargetSize.Small.name()),
				new Field(this.situational.getPercentAtLeast10Yards(),
						"PercentAtleast10Yards", "100"),
				new Field(this.passives.getArchery(), "Archery",
						Boolean.FALSE.toString()),
				new Field(this.passives.getCustomEngineering(), "CustomEngineering",
						Boolean.FALSE.toString()),
				new Field(this.passives.getBallistics(), "Ballistics",
						Boolean.FALSE.toString()),
				new Field(this.passives.getBloodVengeance(), "BloodVengeance",
						Boolean.FALSE.toString()),
				new Field(this.passives.getNightStalker(), "NightStalker",
						Boolean.FALSE.toString()),
				new Field(this.passives.getSteadyAim(), "SteadyAim",
						Boolean.FALSE.toString()),
				new Field(this.passives.getGrenadier(), "Grenadier",
						Boolean.FALSE.toString()),
				new Field(this.passives.getCtw(), "CtW",
						Boolean.FALSE.toString()),
				new Field(this.typeDamage.getColdDamage(), "Cold", "0"),
				new Field(this.typeDamage.getFireDamage(), "Fire", "0"),
				new Field(this.typeDamage.getLightningDamage(), "Light", "0"),
				new Field(this.typeDamage.getPhysicalDamage(), "Phys", "0"),
				new Field(this.typeDamage.getPoisonDamage(), "Psn", "0"),
				new Field(this.gemPanel.getBot(), "BoT",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getGogok(), "Gogok",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getTaeguk(), "Taeguk",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getEnforcer(), "Enforcer",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getBotLevel(), "BoTLevel", "0"),
				new Field(this.gemPanel.getGogokLevel(), "GogokLevel", "0"),
				new Field(this.gemPanel.getTaegukLevel(), "TaegukLevel", "0"),
				new Field(this.gemPanel.getGogokStacks(), "GogokStacks", "0"),
				new Field(this.gemPanel.getTaegukStacks(), "TaegukStacks", "0"),
				new Field(this.gemPanel.getPainEnhancerStacks(),
						"PainEnhancerStacks", "0"),
				new Field(this.gemPanel.getEnforcerLevel(), "EnforcerLevel",
						"0"),
				new Field(this.gemPanel.getToxin(), "Toxin",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getToxinLevel(), "ToxinLevel", "0"),
				new Field(this.gemPanel.getPainEnhancer(), "PainEnhancer",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getPainEnhancerLevel(),
						"PainEnhancerLevel", "0"),

				new Field(this.itemPanel.getEliteDamagePercent(),
						"EliteDamage", "0"),
				new Field(this.gemPanel.getBotp(), "BotP",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getBotpLevel(), "BotPLevel", "0"),
				new Field(this.gemPanel.getBotpUptime(), "BotPUptime", "100"),
				new Field(this.gemPanel.getZeis(), "Zei's",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getZeisLevel(), "Zei'sLevel", "0"),
				new Field(this.situational.getDistance(), "TargetDistance",
						"50"),
				new Field(this.sentryRunes, "SentryRune", Rune.None.name()),
				new Field(this.skill1, "Skill1", ""),
				new Field(this.skill2, "Skill2", ""),
//				new Field(this.skill3, "Skill3", ""),
				new Field(this.rune1, "Rune1", Rune.None.name()),
				new Field(this.rune2, "Rune2", Rune.None.name()),
//				new Field(this.rune3, "Rune3", Rune.None.name()),
				new Field(this.passives.getAmbush(), "Ambush",
						Boolean.FALSE.toString()),
				new Field(this.passives.getSingleOut(), "SingleOut",
						Boolean.FALSE.toString()),
				new Field(this.situational.getTargetSpacing(), "TargetSpacing",
						"10"),
				new Field(this.situational.getPercentAbove75(),
						"PercentAbove75Health", "100"),

				new Field(this.cdrPanel.getAmulet(), "CDR.Amulet", "0"),
				new Field(this.cdrPanel.getBelt(), "CDR.Belt", "0"),
				new Field(this.cdrPanel.getBorn(), "CDR.BornsSet",
						Boolean.FALSE.toString()),
				new Field(this.cdrPanel.getCrimson(), "CDR.CrimsonsSet",
						Boolean.FALSE.toString()),
				new Field(this.cdrPanel.getDiamond(), "CDR.Diamond",
						GemLevel.None.name()),
				new Field(this.cdrPanel.getGloves(), "CDR.Gloves", "0"),
				new Field(this.cdrPanel.getLeorics(), "CDR.Leorics",
						Boolean.FALSE.toString()),
				new Field(this.cdrPanel.getLeoricsLevel(),
						"CDR.LeroricsPercent", "0"),
				new Field(this.cdrPanel.getQuiver(), "CDR.Quiver", "0"),
				new Field(this.cdrPanel.getRing1(), "CDR.Ring1", "0"),
				new Field(this.cdrPanel.getRing2(), "CDR.Ring2", "0"),
				new Field(this.cdrPanel.getShoulders(), "CDR.Shoulders", "0"),
				new Field(this.cdrPanel.getWeapon(), "CDR.Weapon", "0"),

				new Field(this.rcrPanel.getBelt(), "RCR.Belt", "0"),
				new Field(this.rcrPanel.getCrimson(), "RCR.CrimsonsSet",
						Boolean.FALSE.toString()),
				new Field(this.rcrPanel.getGloves(), "RCR.Gloves", "0"),
				new Field(this.rcrPanel.getPridesFall(), "RCR.PridesFall",
						Boolean.FALSE.toString()),
				new Field(this.rcrPanel.getQuiver(), "RCR.Quiver", "0"),
				new Field(this.rcrPanel.getRing1(), "RCR.Ring1", "0"),
				new Field(this.rcrPanel.getRing2(), "RCR.Ring2", "0"),
				new Field(this.rcrPanel.getShoulders(), "RCR.Shoulders", "0"),
				new Field(this.rcrPanel.getWeapon(), "RCR.Weapon", "0"),

				new Field(this.buffPanel.getHysteria(), "Hysteria",
						Boolean.FALSE.toString()),
				new Field(this.buffPanel.getAnatomy(), "Anatomy",
						Boolean.FALSE.toString()),
				new Field(this.buffPanel.getFocusedMind(), "FocusedMind",
						Boolean.FALSE.toString()),

				new Field(this.playerBuffPanel.getBbv(), "BBV",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getBbvUptime(), "BBVUptime",
						"17.67"),
				new Field(this.playerBuffPanel.getPiranhas(), "Piranhas",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getPiranhasUptime(),
						"PiranhasUptime", "100"),
				new Field(this.playerBuffPanel.getInnerSanctuary(),
						"InnerSanctuary", Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getInnerSanctuaryUptime(),
						"InnerSanctuaryUptime", "30"),
				new Field(this.playerBuffPanel.getCripplingWave(),
						"CripplingWave", Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getCripplingWaveUptime(),
						"CripplingWaveUptime", "100"),
				new Field(this.playerBuffPanel.getConviction(), "Conviction",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getOverawe(), "Overawe",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getConvictionPassiveUptime(),
						"ConvictionPassiveUptime", "100"),
				new Field(this.playerBuffPanel.getConvictionActiveUptime(),
						"ConvictionActiveUptime", "0"),
				new Field(this.playerBuffPanel.getWolf(), "Wolf",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getWolfUptime(), "WolfUptime",
						"33.33"),
				new Field(this.playerBuffPanel.getMassConfusion(),
						"MassConfusion", Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getMassConfusionUptime(),
						"MassConfusionUptime", "20.0"),
				new Field(this.targetType, "TargetType",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getValor(), "Valor",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getRetribution(), "Retribution",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getSlamDance(), "SlamDance",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getValorUptime(), "ValorUptime",
						"0"),
				new Field(this.playerBuffPanel.getRetributionUptime(),
						"RetributionUptime", "0"),

		};

	}

	protected void calculate() {

		final AsyncTaskHandler dialog = super.showWaitDialogBox("Calculating",
				"Please wait...");

		Scheduler.get().scheduleDeferred(new Command() {

			@Override
			public void execute() {
				doCalculate(dialog);
			}
		});

	}

	protected void doCalculate(AsyncTaskHandler dialog) {
		try {

			saveForm();

			this.formData = getFormData();

			data.setDexterity(calculator.getDexterity());
			data.setSheetDps(calculator.getSheetDps());
			data.setAps(calculator.getAps());
			data.setCaDamage(getValue(this.skillDamage.getCaDamage()) / 100.0);
			data.setChakDamage(getValue(this.skillDamage.getChakDamage()) / 100.0);
			data.setColdDamage(getValue(this.typeDamage.getColdDamage()) / 100.0);
			data.setEaDamage(getValue(this.skillDamage.getEaDamage()) / 100.0);
			data.setFireDamage(getValue(this.typeDamage.getFireDamage()) / 100.0);
			data.setImpDamage(getValue(this.skillDamage.getImpDamage()) / 100.0);
			data.setHaDamage(getValue(this.skillDamage.getHaDamage()) / 100.0);
			data.setEsDamage(getValue(this.skillDamage.getEsDamage()) / 100.0);
			data.setBolasDamage(getValue(this.skillDamage.getBolasDamage()) / 100.0);
			data.setEfDamage(getValue(this.skillDamage.getEfDamage()) / 100.0);
			data.setGrenadeDamage(getValue(this.skillDamage.getGrenadeDamage()) / 100.0);
			data.setLightDamage(getValue(this.typeDamage.getLightningDamage()) / 100.0);
			data.setMsDamage(getValue(this.skillDamage.getMsDamage()) / 100.0);
			data.setPhysDamage(getValue(this.typeDamage.getPhysicalDamage()) / 100.0);
			data.setBallistics(this.passives.getBallistics().getValue());
			data.setBloodVengeance(this.passives.getBloodVengeance().getValue());
			data.setNightStalker(this.passives.getNightStalker().getValue());
			data.setSentryDamage(getValue(this.skillDamage.getSentryDamage()) / 100.0);
			data.setSentryRune(this.getRune(sentryRunes));
			data.setCritChance(calculator.getCritChance());
			data.setCritHitDamage(calculator.getCritDamage());
			data.setPoisonDamage(getValue(this.typeDamage.getPoisonDamage()) / 100.0);
			data.setPercentSlowedChilled((double) this.situational
					.getPercentControlled().getValue() / 100.0);
			data.setPercentControlled((double) this.situational
					.getPercentSlowedChilled().getValue() / 100.0);
			data.setNumAdditional(getValue(this.situational.getAdditional()));
			data.setUseBaneOfTheTrapped(this.gemPanel.getBot().getValue());
			data.setUseEnforcer(this.gemPanel.getEnforcer().getValue());
			data.setBaneOfTheTrappedLevel(getValue(this.gemPanel.getBotLevel()));
			data.setEnforcerLevel(getValue(this.gemPanel.getEnforcerLevel()));
			data.setChillDamage(this.passives.getCtw().getValue() ? 0.2 : 0.0);
			data.setCullTheWeak(this.passives.getCtw().getValue());
			data.setEliteDamage(getValue(this.itemPanel.getEliteDamagePercent()) / 100.0);
			data.setBotp(this.gemPanel.getBotp().getValue());
			data.setBotpLevel(getValue(this.gemPanel.getBotpLevel()));
			data.setBotpUptime(getValue(this.gemPanel.getBotpUptime()) / 100.0);
			data.setGrenadier(this.passives.getGrenadier().getValue());
			data.setCalamityMdf(itemPanel.getCalamity().getValue());
			data.setHasBombardiers(itemPanel.getBombadiers().getValue());
			data.setNumMarauders(itemPanel.getMarauders().getValue());
			data.setMarked(skills.getMfd().getValue());
			data.setSteadyAim(this.passives.getSteadyAim().getValue());
			data.setPercentAtLeast10Yards((double) this.situational
					.getPercentAtLeast10Yards().getValue() / 100.0);
			data.setZeis(this.gemPanel.getZeis().getValue());
			data.setZeisLevel(this.getValue(this.gemPanel.getZeisLevel()));
			data.setDistanceToTarget(this.getValue(this.situational
					.getDistance()));
			data.setAmbush(this.passives.getAmbush().getValue());
			data.setSingleOut(this.passives.getSingleOut().getValue());
			data.setPercentAbove75((double) this.situational
					.getPercentAbove75().getValue() / 100.0);
			data.setTargetSpacing(this.situational.getTargetSpacing()
					.getValue());
			data.setWeaponDamage(calculator.getAverageWeaponDamage());
			data.setArchery(calculator.getArchery());
			data.setCustomEngineering(passives.getCustomEngineering().getValue());
			data.setWeaponType(calculator.getWeaponType());
			data.setGogok(gemPanel.getGogok().getValue());
			data.setGogokLevel(gemPanel.getGogokLevel().getValue());
			data.setGogokStacks(gemPanel.getGogokStacks().getValue());
			data.setCdr(this.effCdr);
			data.setRcr(this.effRcr);
			data.setFocusedMind(buffPanel.getFocusedMind().getValue());
			data.setHysteria(buffPanel.getHysteria().getValue());
			data.setAnatomy(buffPanel.getAnatomy().getValue());
			data.setToxin(gemPanel.getToxin().getValue());
			data.setToxinLevel(gemPanel.getToxinLevel().getValue());
			data.setPainEnhancer(gemPanel.getPainEnhancer().getValue());
			data.setPainEnhancerLevel(gemPanel.getPainEnhancerLevel()
					.getValue());
			data.setPainEnhancerStacks(gemPanel.getPainEnhancerStacks()
					.getValue());
			data.setTaeguk(gemPanel.getTaeguk().getValue());
			data.setTaegukLevel(gemPanel.getTaegukLevel().getValue());
			data.setTaegukStacks(gemPanel.getTaegukStacks().getValue());
			data.setWolf(playerBuffPanel.getWolf().getValue());
			data.setWolfUptime(playerBuffPanel.getWolfUptime().getValue() / 100.0);
			data.setBbv(playerBuffPanel.getBbv().getValue());
			data.setBbvUptime(playerBuffPanel.getBbvUptime().getValue() / 100.0);
			data.setMassConfusion(playerBuffPanel.getMassConfusion().getValue());
			data.setMassConfusionUptime(playerBuffPanel
					.getMassConfusionUptime().getValue() / 100.0);
			data.setPiranhas(playerBuffPanel.getPiranhas().getValue());
			data.setPiranhasUptime(playerBuffPanel.getPiranhasUptime()
					.getValue() / 100.0);
			data.setInnerSanctuary(playerBuffPanel.getInnerSanctuary()
					.getValue());
			data.setInnerSanctuaryUptime(playerBuffPanel
					.getInnerSanctuaryUptime().getValue() / 100.0);
			data.setCripplingWave(playerBuffPanel.getCripplingWave().getValue());
			data.setCripplingWaveUptime(playerBuffPanel
					.getCripplingWaveUptime().getValue() / 100.0);
			data.setConviction(playerBuffPanel.getConviction().getValue());
			data.setOverawe(playerBuffPanel.getOverawe().getValue());
			data.setConvictionPassiveUptime(playerBuffPanel
					.getConvictionPassiveUptime().getValue() / 100.0);
			data.setConvictionActiveUptime(playerBuffPanel
					.getConvictionActiveUptime().getValue() / 100.0);
			data.setTnt(itemPanel.getTnt().getValue());
			data.setTntPercent(itemPanel.getTntPercent().getValue() / 100.0);
			data.setMeticulousBolts(itemPanel.getMeticulousBolts().getValue());
			data.setMeticulousBoltsPercent(itemPanel
					.getMeticulousBoltsPercent().getValue() / 100.0);
			data.setTargetSize(situational.getSelectedTargetSize());
			data.setCalamityUptime(itemPanel.getCalamityUptime().getValue() / 100.0);
			data.setMfdRune(skills.getMarkedForDeathRune());
			data.setMfdUptime(skills.getMfdUptime().getValue() / 100.0);
			data.setMfdAddUptime(skills.getMfdAddUptime().getValue() / 100.0);
			data.setRetribution(playerBuffPanel.getRetribution().getValue());
			data.setValor(playerBuffPanel.getValor().getValue());
			data.setRetributionUptime(playerBuffPanel.getRetributionUptime()
					.getValue() / 100.0);
			data.setValorUptime(playerBuffPanel.getValorUptime().getValue() / 100.0);
			data.setSlamDance(playerBuffPanel.getSlamDance().getValue());
			data.setHarrington(itemPanel.getHarrington().getValue());
			data.setHarringtonPercent(itemPanel.getHarringtonPercent()
					.getValue() / 100.0);
			data.setHarringtonUptime(itemPanel.getHarringtonUptime().getValue() / 100.0);
			data.setStrongarm(itemPanel.getStrongarm().getValue());
			data.setStrongarmPercent(itemPanel.getStrongarmPercent().getValue() / 100.0);
			data.setStrongarmUptime(itemPanel.getStrongarmUptime().getValue() / 100.0);
			data.setHexingPants(itemPanel.getHexingPants().getValue());
			data.setHexingPantsPercent(itemPanel.getHexingPantsPercent()
					.getValue() / 100.0);
			data.setHexingPantsUptime(itemPanel.getHexingPantsUptime()
					.getValue() / 100.0);
			data.setCaltrops(skills.getCaltrops().getValue());
			data.setCaltropsUptime(skills.getCaltropsUptime().getValue() / 100.0);
			data.setMaxHatred(hatredPanel.getMaxHatred().getValue());
			data.setHatredPerSecond(hatredPanel.getHatredPerSecond().getValue());
			data.setSpines(itemPanel.getSpines().getValue());
			data.setKridershot(itemPanel.getKridershot().getValue());
			data.setSpinesHatred(itemPanel.getSpinesHatred().getValue());
			data.setKridershotHatred(itemPanel.getKridershotHatred().getValue());

			Map<ActiveSkill, Rune> skills = getSkills();
//			SkillSet skillSet = getSkillSet(skills);

			double gogokIas = data.isGogok() ? (data.getGogokStacks() / 100.0) : 0.0;
			double petIasValue = data.isTnt() ? data.getTntPercent() : 0.0;
			double petApsValue = data.getAps() * (1.0 + petIasValue) * (1.0 + gogokIas);
			
			BreakPoint bp = BreakPoint.get(petApsValue);
			data.setBp(bp.getBp());

			this.damage = FiringData.calculateDamages(skills, data);

			types = new TreeMap<DamageType, DamageHolder>();
			skillDamages = new TreeMap<DamageSource, DamageHolder>();

			this.exportData = new ExportData();
			this.exportData.data = data;
			this.exportData.output = damage;
			this.exportData.skills = skills;
			this.exportData.types = types;
			this.exportData.skillDamages = skillDamages;
			this.exportData.multiple = new Vector<MultipleSummary>();
			this.exportData.sentryBaseDps = calculator.getSentryDps();
			this.exportData.bp = bp.getBp();

			calculateData();

			updateOutput();

		} finally {
			if (dialog != null)
				dialog.taskCompleted();
		}
	}

	private SkillSet getSkillSet(Map<ActiveSkill, Rune> skills) {
		SkillSet skillSet = new SkillSet(skills.keySet());

		Rune rune = getRune(this.sentryRunes);
		skills.put(ActiveSkill.SENTRY, rune);
		skills.put(ActiveSkill.BOLT, rune);

		return skillSet;
	}

	private Map<ActiveSkill, Rune> getSkills() {
		Map<ActiveSkill, Rune> skills = new TreeMap<ActiveSkill, Rune>();

		addSkill(skills, skill1, rune1);
		addSkill(skills, skill2, rune2);
//		addSkill(skills, skill3, rune3);

		return skills;
	}

	private void calculateData() {
		total = 0.0;
		nonStacking = 0.0;

		for (Damage d : damage) {

			total += d.totalDamage;

			if (d.nonStacking)
				nonStacking += d.totalDamage;

			DamageType type = d.type;

			DamageHolder h = types.get(type);

			DamageSource source = d.source;
			DamageHolder th = skillDamages.get(source);

			if (h == null) {
				h = new DamageHolder();
				h.damage = d.totalDamage;
				h.attacks = d.qty;
				types.put(type, h);
			} else {
				h.damage += d.totalDamage;
				h.attacks = Math.max(d.qty, h.attacks);
				h.attacks += d.qty;
			}

			if (th == null) {
				th = new DamageHolder();
				th.damage = d.totalDamage;
				th.attacks = d.qty;
				skillDamages.put(source, th);
			} else {
				th.damage += d.totalDamage;
				th.attacks = Math.max(d.qty, th.attacks);
			}

		}

		double dps = Math.round(total / FiringData.DURATION);
		double elite = 1.0 + data.getTotalEliteDamage();

		this.exportData.sentryDps = dps;
		this.exportData.sentryEliteDps = dps * elite;
		this.exportData.totalDamage = total;
		this.exportData.totalEliteDamage = total;
	}

	private void updateOutput() {

		boolean isElite = this.isEliteSelected();
		double elite = 1.0 + data.getTotalEliteDamage();
		double eliteBonus = isElite ? elite : 1.0;
		String eliteLog = "";
		String eliteString = isElite ? "Elite" : "Non-Elite";
		
		Label ns = new Label("" + data.getNumSentries());
		ns.addStyleName("boldText");
		outputHeader.setWidget(1, 7, ns);
		
		if (isElite && (elite > 1.0)) {
			eliteLog = " X " + DamageMultiplier.Elite.getAbbreviation() + "("
					+ Util.format(elite) + ")";
		}

		while (damageLog.getRowCount() > 1) {
			damageLog.removeRow(1);
		}

		for (int i = damageLog.getRowCount(); i > 1; --i) {
			damageLog.removeRow(i - 1);
		}

		this.captionPanelDamageLog.setCaptionHTML("Damage Log ("
				+ eliteString + " " + FiringData.DURATION + " seconds)");
		this.captionPanelTypeSummary
				.setCaptionHTML("Damage Type Summary ("
						+ eliteString + " " + FiringData.DURATION + " seconds)");
		this.captionPanelSkillSummary
				.setCaptionHTML("Skill Damage Summary ("
						+ eliteString + " " + FiringData.DURATION + " seconds)");

		for (int row = 0; row < damage.length; row++) {
			if ((row % 2) == 0)
				damageLog.getRowFormatter().addStyleName(row + 1, "oddRow");
			else
				damageLog.getRowFormatter().addStyleName(row + 1, "evenRow");

			Damage d = damage[row];
			
			Label sLabel = new Label(d.shooter);
			sLabel.setWordWrap(false);
			damageLog.setWidget(row + 1, 0, sLabel);
			
			ActiveSkill skill = d.source.skill;
			GemSkill gem = d.source.gem;
			Anchor a = new Anchor((skill != null) ? skill.getLongName()
					: gem.getDisplayName());
			a.setTarget("_blank");
			a.setWordWrap(false);
			String url = (skill != null) ? skill.getUrl() : gem.getUrl();
			a.setHref(url);

			damageLog.setWidget(row + 1, 1, a);

			if (skill != null) {
				Anchor b = new Anchor(d.source.rune.getLongName());
				b.setTarget("_blank");
				b.setWordWrap(false);

				if (d.source.rune != Rune.None)
					url += ("#" + d.source.rune.getSlug() + "+");

				b.setHref(url);

				damageLog.setWidget(row + 1, 2, b);
			} else {
				Label b = new Label("N/A");
				damageLog.setWidget(row + 1, 2, b);
			}

			damageLog.setWidget(row + 1, 3, new Label(d.type.name(), false));
			Label damageLabel = new Label(Util.format(Math.round(d.damage
					* eliteBonus)), false);
			damageLabel.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, 4, damageLabel);
			damageLog.setWidget(row + 1, 5, new Label(String.valueOf(d.qty),
					false));

			Label hatredLabel = new Label(Util.format(Math.round(d.hatred)),
					false);
			hatredLabel.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, 6, hatredLabel);

			Label totalLabel = new Label(Util.format(Math.round(d.totalDamage
					* eliteBonus)), false);
			totalLabel.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, 7, totalLabel);

			Label dpsLabel = new Label(
					Util.format(Math.round((d.totalDamage * eliteBonus)
							/ FiringData.DURATION)), false);
			dpsLabel.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, 8, dpsLabel);
			double pct = Math.round((d.totalDamage / total) * 10000.0) / 100.0;
			Label pctLabel = new Label(String.valueOf(pct) + "%", false);
			pctLabel.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, 9, pctLabel);

			String target = d.target.name();

			if (d.target == Target.Additional)
				target += (" (" + d.numAdd + ")");

			damageLog.setWidget(row + 1, 10, new Label(target, false));
			damageLog.setWidget(row + 1, 11, new Label(d.note, false));
			Label log = new Label(d.log + eliteLog);
			log.setWordWrap(false);
			damageLog.setWidget(row + 1, 12, log);
		}

		for (int i = summary.getRowCount(); i > 1; --i) {
			summary.removeRow(i - 1);
		}

		int row = 1;
		for (Map.Entry<DamageType, DamageHolder> e : types.entrySet()) {
			if ((row % 2) == 0)
				summary.getRowFormatter().addStyleName(row, "evenRow");
			else
				summary.getRowFormatter().addStyleName(row, "oddRow");

			summary.setWidget(row, 0, new Label(e.getKey().name(), false));

			int attacks = e.getValue().attacks;
			double d = e.getValue().damage;
			double da = Math.round((d / attacks));

			Label label1 = new Label(String.valueOf(attacks), false);
			label1.addStyleName("dpsCol");
			summary.setWidget(row, 1, label1);

			Label label2 = new Label(Util.format(da * eliteBonus), false);
			label2.addStyleName("dpsCol");
			summary.setWidget(row, 2, label2);

			Label damageLabel = new Label(Util.format(Math
					.round(d * eliteBonus)), false);
			damageLabel.addStyleName("dpsCol");
			summary.setWidget(row, 3, damageLabel);

			Label dpsLabel = new Label(Util.format(Math.round((d * eliteBonus)
					/ FiringData.DURATION)), false);
			dpsLabel.addStyleName("dpsCol");
			summary.setWidget(row, 4, dpsLabel);

			double pct = Math.round((d / total) * 10000.0) / 100.0;
			Label pctLabel = new Label(String.valueOf(pct) + "%", false);
			pctLabel.addStyleName("dpsCol");
			summary.setWidget(row, 5, pctLabel);
			row++;
		}

		for (int i = skillSummary.getRowCount(); i > 1; --i) {
			skillSummary.removeRow(i - 1);
		}

		row = 1;
		for (Map.Entry<DamageSource, DamageHolder> e : skillDamages.entrySet()) {
			if ((row % 2) == 0)
				skillSummary.getRowFormatter().addStyleName(row, "evenRow");
			else
				skillSummary.getRowFormatter().addStyleName(row, "oddRow");

			ActiveSkill skill = e.getKey().skill;
			GemSkill gem = e.getKey().gem;

			Anchor a = new Anchor((skill != null) ? skill.getLongName()
					: gem.getDisplayName());
			a.setTarget("_blank");
			a.setWordWrap(false);
			String url = (skill != null) ? skill.getUrl() : gem.getUrl();
			a.setHref(url);
			skillSummary.setWidget(row, 0, a);

			int attacks = e.getValue().attacks;
			double d = e.getValue().damage;
			double da = Math.round((d / attacks));

			Label label1 = new Label(String.valueOf(attacks), false);
			label1.addStyleName("dpsCol");
			skillSummary.setWidget(row, 1, label1);

			Label label2 = new Label(Util.format(da * eliteBonus), false);
			label2.addStyleName("dpsCol");
			skillSummary.setWidget(row, 2, label2);

			Label damageLabel = new Label(Util.format(Math
					.round(d * eliteBonus)), false);
			damageLabel.addStyleName("dpsCol");
			skillSummary.setWidget(row, 3, damageLabel);

			Label dpsLabel = new Label(Util.format(Math.round((d * eliteBonus)
					/ FiringData.DURATION)), false);
			dpsLabel.addStyleName("dpsCol");
			skillSummary.setWidget(row, 4, dpsLabel);

			double pct = Math.round((d / total) * 10000.0) / 100.0;
			Label pctLabel = new Label(String.valueOf(pct) + "%", false);
			pctLabel.addStyleName("dpsCol");
			skillSummary.setWidget(row, 5, pctLabel);
			row++;
		}

		double dps = Math.round(total / FiringData.DURATION);
		double aps = data.getSentryAps();
		BreakPoint bp = BreakPoint.ALL[data.getBp()-1];

		sentryAps.setText(Util.format(aps));
		breakPoint.setText(String.valueOf(bp.getBp()));
		double sentryAps = (double) bp.getQty() / FiringData.DURATION;
		actualAps.setText(String.valueOf(Util.format(sentryAps)));
		aps30.setText(Util.format(bp.getQty()));

		weaponDamage
				.setText(Util.format(Math.round(data.getWeaponDamage() * 100.0) / 100.0));

		row = 1;

//		double dpsActual = total / FiringData.DURATION;

		this.dps.setText(Util.format(Math.round(dps)));
		this.totalDamage.setText(Util.format(Math.round(total)));
		this.eliteDps.setText(Util.format(Math.round(dps * elite)));
		this.totalEliteDamage.setText(Util.format(Math.round(total * elite)));
		this.eliteDamage.setText(Math.round(data.getTotalEliteDamage() * 100.0)
				+ "%");

	}

	private void addSkill(Map<ActiveSkill, Rune> skills, ListBox skillBox,
			ListBox runeBox) {

		ActiveSkill skill = getSkill(skillBox);

		if (skill != null) {
			Rune rune = getRune(runeBox);
			skills.put(skill, rune);
		}
	}

	private ActiveSkill getSkill(ListBox skills) {
		int index = skills.getSelectedIndex();

		if (index < 0)
			return null;

		String value = skills.getValue(index);

		if (value.length() == 0)
			return null;
		else
			return ActiveSkill.valueOf(value);
	}

	private Rune getRune(ListBox runes) {
		int index = runes.getSelectedIndex();

		if (index < 0)
			return null;

		String value = runes.getValue(index);
		Rune rune = Rune.valueOf(value);

		return rune;
	}

	@Override
	protected void loadForm() {
		super.loadForm();

		calculator.setTnt(itemPanel.getTnt().getValue());
		calculator.setTntPercent(itemPanel.getTntPercent().getValue());
		calculator.setGogok(gemPanel.getGogok().getValue());
		calculator.setGogokLevel(gemPanel.getGogokLevel().getValue());
		calculator.setGogokStacks(gemPanel.getGogokStacks().getValue());
		calculator.setPainEnhancer(gemPanel.getPainEnhancer().getValue());
		calculator.setPainEnhancerLevel(gemPanel.getPainEnhancerLevel()
				.getValue());
		calculator.setPainEnhancerStacks(gemPanel.getPainEnhancerStacks()
				.getValue());
		calculator.saveForm();
		calculator.calculate();
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		disableListeners = true;

		if (!Beans.isDesignTime()) {

			addSkills(skill1, rune1);
			addSkills(skill2, rune2);
//			addSkills(skill3, rune3);

			addSkillHandler(skill1, rune1Label, rune1, skill2); //, skill2, skill3);
			addSkillHandler(skill2, rune2Label, rune2, skill1); //, skill3);
//			addSkillHandler(skill3, rune3Label, rune3, skill1, skill2);

			calculator.loadForm();
			loadForm();

			calculator.setDisableListeners(true);

			updateDps();

			calculator.setDisableListeners(false);

			calculate();

			setRuneLabel(sentryRuneLabel, null, sentryRunes);
			setRuneLabel(this.rune1Label, skill1, rune1);
			setRuneLabel(this.rune2Label, skill2, rune2);
//			setRuneLabel(this.rune3Label, skill3, rune3);
			setSkillLabel(this.skill1Label, skill1);
			setSkillLabel(this.skill2Label, skill2);
//			setSkillLabel(this.skill3Label, skill3);
		}

		disableListeners = false;
	}

	private void addSkillHandler(final ListBox skills, final Anchor runeLabel,
			final ListBox runes, final ListBox other1) { //, final ListBox other2) {
		skills.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (!disableListeners)
					skillChanged(skills, runeLabel, runes, other1); //, other2);
			}
		});

	}

	protected void skillChanged(ListBox skills, Anchor runeLabel,
			ListBox runes, ListBox other1) { //, ListBox other2) {

		// ActiveSkill sk = getSkill(skills);
		//
		// removeSkill(other1, sk);
		// removeSkill(other2, sk);

		boolean save = this.disableListeners;
		this.disableListeners = true;

		setRunes(skills, runes);
		updateRuneLabel(runeLabel, skills, runes);

		this.disableListeners = save;
	}

	protected void removeSkill(ListBox skills, ActiveSkill s) {
		for (int i = skills.getItemCount() - 1; i >= 0; i--) {
			String value = skills.getValue(i);

			if (value.equals(s.name()))
				skills.removeItem(i);
		}

	}

	private void addSkills(ListBox skills, ListBox runes) {
		skills.clear();

		skills.addItem("None", "");
		skills.setSelectedIndex(0);
		addSkill(skills, ActiveSkill.HA);
		addSkill(skills, ActiveSkill.ES);
		addSkill(skills, ActiveSkill.BOLAS);
		addSkill(skills, ActiveSkill.EF);
		addSkill(skills, ActiveSkill.GRENADE);
		addSkill(skills, ActiveSkill.CA);
		addSkill(skills, ActiveSkill.EA);
		addSkill(skills, ActiveSkill.IMP);
		addSkill(skills, ActiveSkill.CHAK);
		addSkill(skills, ActiveSkill.MS);

		setRunes(skills, runes);
		setRunes(sentryRunes, ActiveSkill.SENTRY);
	}

	private void setRunes(ListBox skills, ListBox runes) {

		int index = skills.getSelectedIndex();
		String value = skills.getValue(index);

		if (value.length() == 0) {
			runes.clear();
			addRune(runes, Rune.None);
		} else {
			ActiveSkill skill = ActiveSkill.valueOf(value);

			setRunes(runes, skill);
		}

		runes.setSelectedIndex(0);
	}

	private void setRunes(ListBox runes, ActiveSkill skill) {
		runes.clear();

		if (skill != null) {
			for (Rune rune : skill.getRunes())
				addRune(runes, rune);

		} else {

		}
	}

	private void selectSkill(ListBox skills, ActiveSkill skill) {

		if (skill != null) {
			for (int i = 0; i < skills.getItemCount(); i++) {
				String value = skills.getValue(i);

				if (value.equals(skill.name())) {
					skills.setSelectedIndex(i);
					return;
				}
			}
		}

		skills.setSelectedIndex(0);
	}

	private void setRune(ListBox runes, Rune rune) {
		for (int i = 0; i < runes.getItemCount(); i++) {
			String value = runes.getValue(i);

			if (value.equals(rune.name())) {
				runes.setSelectedIndex(i);
				return;
			}
		}

		runes.setSelectedIndex(0);
	}

	private void addRune(ListBox runes, Rune rune) {
		runes.addItem(rune.getLongName(), rune.name());
	}

	private void addSkill(ListBox skills, ActiveSkill skill) {
		skills.addItem(skill.getLongName(), skill.name());
	}

	public static native void saveFormData(String filename, String key)
	/*-{
			var popupWindow =  window.open('', '_self', '');
			var form = document.createElement("form");
			
			form.setAttribute("method", "post");
			form.setAttribute("action", "saveData");
			form.setAttribute("target", "_self");
			form.setAttribute("enctype", "multipart/form-data");
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "key");
			hiddenField.setAttribute("value", key);
			var hiddenField2 = document.createElement("input");
			hiddenField2.setAttribute("type", "hidden");
			hiddenField2.setAttribute("name", "filename");
			hiddenField2.setAttribute("value", filename);
			form.appendChild(hiddenField);
			form.appendChild(hiddenField2);
			document.getElementsByTagName('body')[0].appendChild(form);
			form.submit();
			
			while(!popupWindow){
				//wait for popupwindow to open before submitting the form
			}
	}-*/;

	protected void showProfile() {
		Realm realm = getSelectedRealm();

		final String server = realm.getHost();

		if ((battleTag.getText() == null)
				|| (battleTag.getText().trim().length() == 0)) {
			ApplicationPanel.showErrorDialog("Enter Battle Tag");
			return;
		}

		final String profile = battleTag.getText();

		if ((tagNumber.getValue() == null)
				|| (tagNumber.getValue().trim().length() == 0)) {
			ApplicationPanel.showErrorDialog("Enter Battle Tag Number");
			return;
		}

		int tag = (int) getValue(this.tagNumber);

		String url = server + "/d3/profile/" + URL.encode(profile) + "-" + tag
				+ "/";

		int index = heroList.getSelectedIndex();

		if (index >= 0) {

			String value = heroList.getValue(index);

			if (value.length() > 0) {
				url += ("hero/" + value);
			}
		}

		Window.open(url, "_blank", "");

	}

	protected void showPaperdoll() {

		if (this.career == null) {
			ApplicationPanel.showErrorDialog("Fetch Hero List First");
			return;
		}

		int index = heroList.getSelectedIndex();

		if (index >= 0) {

			String value = heroList.getValue(index);

			if (value.length() == 0) {
				ApplicationPanel.showErrorDialog("Fetch Hero List");
				return;
			}

			openPaperdoll(realm.name(), URL.encodePathSegment(profile), tag,
					Integer.valueOf(value));

			// PaperdollPanel panel = new PaperdollPanel();
			// panel.load(realm, profile, tag, Integer.valueOf(value));
			//
			// ApplicationPanel.showDialogBox("Paperdoll", panel,
			// ApplicationPanel.OK, null);

		} else {
			ApplicationPanel.showErrorDialog("Select a Hero");
			return;
		}

	}

	public static native void openPaperdoll(String realm, String profile,
			int tag, int id)
	/*-{
	 		var name = 'paperdoll.' + profile + '-' + tag + '.' + id;
	 
	   		window.open('', name, 'width=640,height=640').focus();
			var form = document.createElement("form");
			
			form.setAttribute("method", "post");
			form.setAttribute("action", 'paperdoll.jsp');
			form.setAttribute("target", name);
			form.enctype = "application/x-www-form-urlencoded";

			var serverField = document.createElement("input");
			serverField.setAttribute("type", "hidden");
			serverField.setAttribute("name", "realm");
			serverField.setAttribute("value", realm);
			form.appendChild(serverField);
			
			var profileField = document.createElement("input");
			profileField.setAttribute("type", "hidden");
			profileField.setAttribute("name", "profile");
			profileField.setAttribute("value", profile);
			form.appendChild(profileField);
			
			var tagField = document.createElement("input");
			tagField.setAttribute("type", "hidden");
			tagField.setAttribute("name", "tag");
			tagField.setAttribute("value", tag);
			form.appendChild(tagField);
			
			var idField = document.createElement("input");
			idField.setAttribute("type", "hidden");
			idField.setAttribute("name", "id");
			idField.setAttribute("value", id);
			form.appendChild(idField);
			
			document.getElementsByTagName('body')[0].appendChild(form);
			form.submit();
	}-*/;

}
