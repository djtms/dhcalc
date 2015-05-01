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
import com.dawg6.web.sentry.shared.calculator.DamageResult;
import com.dawg6.web.sentry.shared.calculator.DamageSource;
import com.dawg6.web.sentry.shared.calculator.DamageType;
import com.dawg6.web.sentry.shared.calculator.ExportData;
import com.dawg6.web.sentry.shared.calculator.FiringData;
import com.dawg6.web.sentry.shared.calculator.FormData;
import com.dawg6.web.sentry.shared.calculator.GemLevel;
import com.dawg6.web.sentry.shared.calculator.GemSkill;
import com.dawg6.web.sentry.shared.calculator.MonsterHealth;
import com.dawg6.web.sentry.shared.calculator.MonsterType;
import com.dawg6.web.sentry.shared.calculator.MultipleSummary;
import com.dawg6.web.sentry.shared.calculator.Passive;
import com.dawg6.web.sentry.shared.calculator.ProfileHelper;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.Slot;
import com.dawg6.web.sentry.shared.calculator.TargetSize;
import com.dawg6.web.sentry.shared.calculator.Util;
import com.dawg6.web.sentry.shared.calculator.Version;
import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.Const;
import com.dawg6.web.sentry.shared.calculator.d3api.Hero;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.dawg6.web.sentry.shared.calculator.stats.DpsTableEntry;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.shared.GWT;
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
	private final Label punishmentCDLabel;
	private double rawCdr;
	private double effCdr;
	private double punishmentCD;
	private final Label rovCDLabel;
	private final Label wolfCDLabel;
	private final BuffPanel buffPanel;
	private final SavePanel savePanel;
	private PlayerBuffPanel playerBuffPanel;
	private FlexTable compareTable;
	private final CompareData[] compareData = { null, null, null };
	private Label eliteDamage;
	private Label sentryCDLabel;
	private double sentryCD;
	private CaptionPanel captionPanelTypeSummary;
	private CaptionPanel captionPanelSkillSummary;
	private CaptionPanel captionPanelDamageLog;
	private DamageResult damage;
	private TreeMap<DamageType, DamageHolder> types;
	private TreeMap<DamageSource, DamageHolder> skillDamages;
	private double total;
	private double nonStacking;
	private CharacterData data = new CharacterData();
	private boolean firstTimeStats;
	protected DialogBox statsDialog;
	private FlexTable outputHeader;
	private HatredPanel hatredPanel;
	private Label rawRCRLabel;
	private Label effectiveRCRLabel;
	private double rawRcr;
	private double effRcr;
	private AboutDialog about;
	private StatsPanel stats;
	private Legend legend;
	private BreakpointData bpData;
	private SkillData skillData;
	private GearPanel gearPanel;
	private FlexTable shooterSummary;
	private Label offHand_weaponDamage;
	private Label dw_weaponDamage;
	private FlexTable statTable;
	private CaptionPanel statTableCaption;
	private Label timeElapsed;
	private NumberSpinner timeLimit;

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

		Button fetchButton = new Button("Get Hero List");
		fetchButton.setWidth("100px");
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
		profileLink.setHref("javascript:void(0)");
		profileLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showProfile();
			}
		});

		Anchor anchor = new Anchor("paperdoll");
		anchor.setText("paperdoll");
		anchor.setHref("javascript:void(0)");
		profileLinks.add(anchor);

		anchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showPaperdoll();
			}
		});

		Button itemsButton = new Button("Items...");
		profileLinks.add(itemsButton);

		itemsButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showGearPanel();
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

		this.paragonPanel.getParagonDexterity().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {

						if (!disableListeners)
							updateDps();
					}
				});

		this.paragonPanel.getParagonHatred().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {

						if (!disableListeners) {
							calculator.setHatred(paragonPanel
									.getParagonHatred().getValue());
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

		Label lblPetAps = new Label("Companion Cooldown:");
		grid_1.setWidget(5, 0, lblPetAps);

		wolfCDLabel = new Label("0.0", false);
		wolfCDLabel.setStyleName("boldText");
		grid_1.setWidget(5, 1, wolfCDLabel);

		Label lblPunishmentCooldown = new Label("Punishment Cooldown:");
		lblPunishmentCooldown.setWordWrap(false);
		grid_1.setWidget(5, 2, lblPunishmentCooldown);

		Label sentryCooldown = new Label("Sentry Cooldown:");
		sentryCooldown.setWordWrap(false);
		grid_1.setWidget(6, 2, sentryCooldown);

		punishmentCDLabel = new Label("0.0 sec", false);
		punishmentCDLabel.setStyleName("boldText");
		grid_1.setWidget(5, 3, punishmentCDLabel);

		sentryCDLabel = new Label("0.0 sec", false);
		sentryCDLabel.setStyleName("boldText");
		grid_1.setWidget(6, 3, sentryCDLabel);

		Label label_3 = new Label("(approx) RoV Cooldown:");
		grid_1.setWidget(6, 0, label_3);

		rovCDLabel = new Label("0.0", false);
		rovCDLabel.setStyleName("boldText");
		grid_1.setWidget(6, 1, rovCDLabel);

		Button calcDps = new Button("Calculator...");
		grid_1.setWidget(7, 2, calcDps);
		grid_1.getFlexCellFormatter().setColSpan(7, 2, 2);
		grid_1.getCellFormatter().setHorizontalAlignment(7, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		grid_1.getCellFormatter().setVerticalAlignment(7, 2,
				HasVerticalAlignment.ALIGN_MIDDLE);

		CaptionPanel captionPanel = new CaptionPanel("Compare Builds");
		verticalPanel_2.add(captionPanel);

		compareTable = new FlexTable();
		captionPanel.setContentWidget(compareTable);
		compareTable.setCellPadding(2);

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

		Label label_11 = new Label("Break Point:");
		label_11.setWordWrap(false);
		label_11.setStyleName("boldText");
		compareTable.setWidget(2, 0, label_11);

		Label label_8 = new Label("Weapon Damage:");
		label_8.setWordWrap(false);
		label_8.setStyleName("boldText");
		compareTable.setWidget(3, 0, label_8);

		Label label_14a = new Label("Time:");
		label_14a.setWordWrap(false);
		label_14a.setStyleName("boldText");
		compareTable.setWidget(5, 0, label_14a);

		Label label_14 = new Label("DPS:");
		label_14.setWordWrap(false);
		label_14.setStyleName("boldText");
		compareTable.setWidget(7, 0, label_14);

		for (int j = 0; j < 3; j++) {
			final int which = j;
			int col = (j * 2) + 1;

			Anchor button_3 = new Anchor("Set");
			button_3.setHref("javascript:void(0)");
			button_3.setTitle("Click to store the current build for comparison");
			compareTable.setWidget(0, col, button_3);
			compareTable.getFlexCellFormatter().setHorizontalAlignment(0, col,
					HasHorizontalAlignment.ALIGN_CENTER);

			button_3.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					storeBuild(which);
				}
			});

			Anchor button_4 = new Anchor("Restore");
			button_4.setHref("javascript:void(0)");
			button_4.setTitle("Click to retrieve this build");
			compareTable.setWidget(1, col - 1, button_4);
			compareTable.getFlexCellFormatter().setHorizontalAlignment(1,
					col - 1, HasHorizontalAlignment.ALIGN_CENTER);

			button_4.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					loadBuild(which);
				}
			});

			Anchor label_1 = new Anchor("Clear");
			label_1.setHref("javascript:void(0)");
			label_1.setTitle("Click to clear this build");
			compareTable.setWidget(9, col, label_1);
			compareTable.getFlexCellFormatter().setWidth(9, col + 1, "5px");
			compareTable.getFlexCellFormatter().setHorizontalAlignment(9, col,
					HasHorizontalAlignment.ALIGN_CENTER);

			label_1.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					clearBuild(which);
				}
			});

			for (int i = 0; i < 4; i++) {
				int row = 2 + ((i > 0) ? 1 + ((i - 1) * 2) : 0);

				Label l = new Label("No Data");
				l.setWordWrap(false);
				compareTable.setWidget(row, col, l);
				compareTable.getFlexCellFormatter().setHorizontalAlignment(row,
						col, HasHorizontalAlignment.ALIGN_CENTER);

				if (j > 0) {
					Label pct = new Label("No Data");
					pct.setWordWrap(false);
					compareTable.setWidget(row + 1, col, pct);
					compareTable.getFlexCellFormatter().setHorizontalAlignment(
							row + 1, col, HasHorizontalAlignment.ALIGN_CENTER);
				}
			}
		}

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

		skills = new SkillsPanel();
		verticalPanel_1.add(skills);

		FlexTable row = new FlexTable();
		verticalPanel_1.add(row);
		row.setWidth("100%");

		passives = new PassivesPanel();
		row.setWidget(0, 0, passives);

		typeDamage = new DamageTypePanel();
		row.setWidget(0, 1, typeDamage);

		situational = new SituationalPanel();
		verticalPanel_1.add(situational);

		gemPanel = new GemsPanel();
		verticalPanel_1.add(gemPanel);

		VerticalPanel verticalPanel_3 = new VerticalPanel();
		horizontalPanel_4.add(verticalPanel_3);

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
		calcButton.setText("Simulate");
		calcButton.setTitle("Press to run damage simulation");

		Button legendButton = new Button("New button");
		legendButton.setText("Legend...");
		horizontalPanel_19.add(legendButton);

		outputHeader = new FlexTable();
		outputHeader.setCellPadding(2);
		verticalPanel_5.add(outputHeader);

		Label lblNewLabel_27 = new Label("Average Weapon Damage (Main Hand):");
		outputHeader.setWidget(0, 0, lblNewLabel_27);
		lblNewLabel_27.setWordWrap(false);

		weaponDamage = new Label("00000");
		outputHeader.setWidget(0, 1, weaponDamage);
		weaponDamage.setStyleName("boldText");

		Label lblNewLabel_27a = new Label("(Off Hand):");
		outputHeader.setWidget(0, 2, lblNewLabel_27a);
		lblNewLabel_27a.setWordWrap(false);

		offHand_weaponDamage = new Label("00000");
		outputHeader.setWidget(0, 3, offHand_weaponDamage);
		offHand_weaponDamage.setStyleName("boldText");

		Label lblNewLabel_27b = new Label("(Dual Wield):");
		outputHeader.setWidget(0, 4, lblNewLabel_27b);
		lblNewLabel_27b.setWordWrap(false);

		dw_weaponDamage = new Label("00000");
		outputHeader.setWidget(0, 5, dw_weaponDamage);
		dw_weaponDamage.setStyleName("boldText");

		Label lblNewLabel_3 = new Label("Pet APS:");
		outputHeader.setWidget(1, 0, lblNewLabel_3);
		lblNewLabel_3.setWordWrap(false);

		sentryAps = new Label("00000");
		outputHeader.setWidget(1, 1, sentryAps);
		sentryAps.setStyleName("boldText");
		sentryAps.setWordWrap(false);

		Label lblNewLabel_4 = new Label("Break Point:");
		outputHeader.setWidget(1, 2, lblNewLabel_4);
		lblNewLabel_4.setWordWrap(false);

		breakPoint = new Label("000000");
		outputHeader.setWidget(1, 3, breakPoint);
		breakPoint.setStyleName("boldText");
		breakPoint.setWordWrap(false);

		Label lblNewLabel_29 = new Label("Sentry APS:");
		outputHeader.setWidget(1, 4, lblNewLabel_29);

		actualAps = new Label("00000");
		outputHeader.setWidget(1, 5, actualAps);
		actualAps.setStyleName("boldText");

		lblNewLabel_5 = new Label("Attacks Per " + BreakPoint.DURATION
				+ " Seconds:");
		outputHeader.setWidget(1, 6, lblNewLabel_5);
		lblNewLabel_5.setWordWrap(false);

		aps30 = new Label("00000");
		outputHeader.setWidget(1, 7, aps30);
		aps30.setStyleName("boldText");
		aps30.setWordWrap(false);

		Label lblNewLabel_6 = new Label("Total Damage:");
		outputHeader.setWidget(2, 0, lblNewLabel_6);
		lblNewLabel_6.setWordWrap(false);

		Label lblNewLabel_6a = new Label("Time Elapsed:");
		outputHeader.setWidget(3, 0, lblNewLabel_6a);
		lblNewLabel_6a.setWordWrap(false);

		totalDamage = new Label("00000");
		outputHeader.setWidget(2, 1, totalDamage);
		totalDamage.setStyleName("boldText");

		timeElapsed = new Label("00000");
		outputHeader.setWidget(3, 1, timeElapsed);
		timeElapsed.setStyleName("boldText");

		Label lblNewLabel_7 = new Label("DPS:");
		outputHeader.setWidget(2, 2, lblNewLabel_7);
		lblNewLabel_7.setWordWrap(false);

		dps = new Label("00000");
		outputHeader.setWidget(2, 3, dps);
		dps.setStyleName("boldText");

		Label lblNewLabel_29a = new Label("# Sentries:");
		outputHeader.setWidget(2, 6, lblNewLabel_29a);

		Label lblNewLabel_7b = new Label("+% Elite Damage:");
		outputHeader.setWidget(3, 4, lblNewLabel_7b);
		lblNewLabel_7b.setWordWrap(false);

		eliteDamage = new Label("00000");
		outputHeader.setWidget(3, 5, eliteDamage);
		eliteDamage.setStyleName("boldText");

		Label l1 = new Label("Simulation Time Limit (seconds):", false);
		outputHeader.setWidget(4, 0, l1);
		
		timeLimit = new NumberSpinner();
		timeLimit.setMin(1);
		timeLimit.setMax(FiringData.MAX_DURATION);
		timeLimit.setVisibleLength(5);
		outputHeader.setWidget(4,1,timeLimit);
		
		outputHeader.setWidget(4, 2, calcButton);

		Button bpButton = new Button("New button");
		bpButton.setText("Break Points...");
		horizontalPanel_19.add(bpButton);

		bpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (bpData == null)
					bpData = new BreakpointData();

				ApplicationPanel.showDialogBox("Break Points", bpData,
						ApplicationPanel.OK, null);
			}
		});

		Button skillButton = new Button("New button");
		skillButton.setText("Skills...");
		horizontalPanel_19.add(skillButton);

		skillButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (skillData == null)
					skillData = new SkillData();

				ApplicationPanel.showDialogBox("Skills", skillData,
						ApplicationPanel.OK, null);
			}
		});

		outputPanel = new VerticalPanel();
		verticalPanel_5.add(outputPanel);

		HorizontalPanel horizontalPanel_9 = new HorizontalPanel();
		outputPanel.add(horizontalPanel_9);

		statTableCaption = new CaptionPanel("Stat Calculator");
		horizontalPanel_9.add(statTableCaption);

		VerticalPanel panel_1 = new VerticalPanel();
		horizontalPanel_9.add(panel_1);

		captionPanelTypeSummary = new CaptionPanel("Damage Type Summary");
		panel_1.add(captionPanelTypeSummary);

		summary = new FlexTable();
		summary.setCellPadding(5);
		summary.setBorderWidth(1);
		summary.setStyleName("outputTable");
		summary.setWidth("100%");
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

		captionPanelSkillSummary = new CaptionPanel("Skill Damage Summary");
		panel_1.add(captionPanelSkillSummary);

		skillSummary = new FlexTable();
		skillSummary.setStyleName("outputTable");
		skillSummary.setCellPadding(5);
		skillSummary.setBorderWidth(1);
		skillSummary.setWidth("100%");

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

		captionPanelShooterSummary = new CaptionPanel("Shooter Summary");
		panel_1.add(captionPanelShooterSummary);

		shooterSummary = new FlexTable();
		captionPanelShooterSummary.setContentWidget(shooterSummary);
		shooterSummary.setStyleName("outputTable");
		shooterSummary.setCellPadding(5);
		shooterSummary.setBorderWidth(1);
		shooterSummary.setWidth("100%");

		Label lblSkill_2a = new Label("Shooter");
		lblSkill_2a.setWordWrap(false);
		shooterSummary.setWidget(0, 0, lblSkill_2a);

		Label lblAttacksa = new Label("# Attacks");
		lblAttacksa.setWordWrap(false);
		lblAttacksa.setStyleName("dpsHeader");
		shooterSummary.setWidget(0, 1, lblAttacksa);

		Label lblPerAttacka = new Label("Per Attack");
		lblPerAttacka.setWordWrap(false);
		lblPerAttacka.setStyleName("dpsHeader");
		shooterSummary.setWidget(0, 2, lblPerAttacka);

		Label lblTotala = new Label("Total");
		lblTotala.setStyleName("dpsHeader");
		lblTotala.setWordWrap(false);
		shooterSummary.setWidget(0, 3, lblTotala);
		shooterSummary.getColumnFormatter().addStyleName(1, "dpsCol");

		Label label_5a = new Label("DPS");
		label_5a.setStyleName("dpsHeader");
		label_5a.setWordWrap(false);
		shooterSummary.setWidget(0, 4, label_5a);
		shooterSummary.getColumnFormatter().addStyleName(2, "dpsCol");

		Label label_6b = new Label("% of Total");
		label_6b.setStyleName("dpsHeader");
		label_6b.setWordWrap(false);
		shooterSummary.setWidget(0, 5, label_6b);

		skillSummary.getColumnFormatter().addStyleName(3, "dpsCol");
		skillSummary.getRowFormatter().addStyleName(0, "headerRow");
		summary.getColumnFormatter().addStyleName(3, "dpsCol");
		summary.getRowFormatter().addStyleName(0, "headerRow");
		shooterSummary.getColumnFormatter().addStyleName(3, "dpsCol");
		shooterSummary.getRowFormatter().addStyleName(0, "headerRow");

		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		outputPanel.add(horizontalPanel_2);

		captionPanelDamageLog = new CaptionPanel("Damage Log");
		horizontalPanel_2.add(captionPanelDamageLog);

		damageLog = new FlexTable();
		damageLog.setCellPadding(5);
		damageLog.setBorderWidth(1);
		captionPanelDamageLog.setContentWidget(damageLog);

		int col = 0;

		Label lblNewLabel_11 = new Label("Time", false);
		lblNewLabel_11.setWordWrap(false);
		lblNewLabel_11.setStyleName("dpsHeader");
		damageLog.setWidget(0, col, lblNewLabel_11);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_8a = new Label("Shooter", false);
		lblNewLabel_8a.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_8a);
		col++;

		Label lblNewLabel_8 = new Label("Skill", false);
		lblNewLabel_8.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_8);
		col++;

		Label lblNewLabel_9 = new Label("Rune", false);
		lblNewLabel_9.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_9);
		col++;

		Label lblNewLabel_10 = new Label("Type", false);
		lblNewLabel_10.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_10);
		col++;

		Label lblNewLabel_12a = new Label("+/- Hatred", false);
		lblNewLabel_12a.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_12a);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_12b = new Label("Hatred", false);
		lblNewLabel_12b.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_12b);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_12c = new Label("+/- Disc", false);
		damageLog.setWidget(0, col, lblNewLabel_12c);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_12d = new Label("Disc", false);
		damageLog.setWidget(0, col, lblNewLabel_12d);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_13 = new Label("Damage", false);
		lblNewLabel_13.setStyleName("dpsHeader");
		lblNewLabel_13.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_13);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_14 = new Label("Target HP", false);
		lblNewLabel_14.setStyleName("dpsHeader");
		lblNewLabel_14.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_14);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_15 = new Label("% HP", false);
		lblNewLabel_15.setStyleName("dpsHeader");
		lblNewLabel_15.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_15);
		damageLog.getColumnFormatter().addStyleName(col, "dpsCol");
		col++;

		Label lblNewLabel_15b = new Label("Target", false);
		lblNewLabel_15b.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_15b);
		col++;

		Label lblNewLabel_16 = new Label("Notes", false);
		lblNewLabel_16.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_16);
		col++;

		Label lblNewLabel_28 = new Label("Calculations", false);
		lblNewLabel_28.setWordWrap(false);
		damageLog.setWidget(0, col, lblNewLabel_28);
		col++;

		damageLog.addStyleName("outputTable");
		damageLog.getRowFormatter().addStyleName(0, "headerRow");

		calculator = new DPSCalculator();

		passives.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					calculator.getPassives()
							.setPassives(passives.getPassives());
					calculator.saveForm();
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});

		calculator.getPassives().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!disableListeners) {
					disableListeners = true;
					passives.setPassives(calculator.getPassives().getPassives());
					updateDpsLabels();
					disableListeners = false;
				}
			}
		});

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

		statTable = new FlexTable();
		statTableCaption.setContentWidget(statTable);
		statTable.setCellPadding(5);
		statTable.setBorderWidth(1);
		statTable.setStyleName("outputTable");
		statTable.getRowFormatter().addStyleName(0, "headerRow");

		col = 0;

		Label l1a = new Label("Stat");
		l1a.setStyleName("dpsHeader");
		l1a.setWordWrap(false);
		statTable.setWidget(0, col++, l1a);

		Label l2b = new Label("Time");
		l2b.setStyleName("dpsHeader");
		l2b.setWordWrap(false);
		statTable.setWidget(0, col++, l2b);

		Label l2c = new Label("%");
		l2c.setStyleName("dpsHeader");
		l2c.setWordWrap(false);
		statTable.setWidget(0, col++, l2c);

		Label l4 = new Label("Damage");
		l4.setStyleName("dpsHeader");
		l4.setWordWrap(false);
		statTable.setWidget(0, col++, l4);

		Label l2 = new Label("DPS");
		l2.setStyleName("dpsHeader");
		l2.setWordWrap(false);
		statTable.setWidget(0, col++, l2);

		Label l2a = new Label("%");
		l2a.setStyleName("dpsHeader");
		l2a.setWordWrap(false);
		statTable.setWidget(0, col++, l2a);

		captionPanelDamageLog.setContentWidget(damageLog);

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
		itemPanel.getNumNats().addChangeHandler(handler);

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

		firstTimeStats = true;

		statsButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (stats == null) {
					stats = new StatsPanel();

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

				}

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

				if (about == null)
					about = new AboutDialog();

				ApplicationPanel.showDialogBox("About", about,
						ApplicationPanel.OK, null);
			}
		});

		legendButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (legend == null)
					legend = new Legend();

				ApplicationPanel.showDialogBox("Legend", legend,
						ApplicationPanel.OK, null);
			}
		});

		for (Realm r : Realm.values()) {
			realms.addItem(r.getDisplayName(), r.name());
		}

	}

	protected void setBuild(Build build) {

		this.disableListeners = true;
		situational.setDisableListeners(true);

		situational.setDisableListeners(false);
		this.disableListeners = false;
	}

	protected Build getBuild() {

		Build build = new Build();

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
		this.paragonPanel.getParagonDexterity()
				.setValue(entry.getParagon_dex());
		this.paragonPanel.getParagonHatred()
				.setValue(entry.getParagon_hatred());
		this.paragonPanel.getParagonRCR().setValue(entry.getParagon_rcr());
		this.paragonPanel.getParagonAD().setValue(entry.getParagon_ad());

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

	private static final int NUM_COMPARE_ROWS = 7;
	private CaptionPanel captionPanelShooterSummary;
	private Map<String, DamageHolder> shooterDamages;

	protected void clearBuild(int which) {
		compareData[which] = null;

		showBuildData(which);
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

			Util.putAll(map, "passives.", list.get(i).formData.passives);
			Util.putAll(map, "skills.", list.get(i).formData.skills);
			Util.putAll(map, "elementalDamages.",
					list.get(i).formData.elementalDamage);
			Util.putAll(map, "skillDamages.", list.get(i).formData.skillDamage);

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

		Set<String> keys = new TreeSet<String>();

		for (Map<String, String> map : formData) {
			keys.addAll(map.keySet());
		}

		for (String key : keys) {
			List<String> values = new Vector<String>();
			String first = formData.get(0).get(key);
			values.add(first);
			boolean diff = false;

			for (int i = 1; i < count; i++) {
				String value = formData.get(i).get(key);
				values.add(value);
				if (!diff && ((value == null) || !value.equals(first)))
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

						showBuildData(which);

						dialog.taskCompleted();
					}
				});
			}
		});

	}

	protected void showBuildData(int which) {
		CompareData data = compareData[which];

		int col = which * 2 + 1;

		if (data == null) {

			for (int row = 0; row < NUM_COMPARE_ROWS; row++) {
				Label label = (Label) compareTable.getWidget(row + 2, col);

				if (label != null)
					label.setText("No Data");
			}

		} else {
			CompareData baseline = compareData[0];

			int row = 2;

			Label bp = (Label) compareTable.getWidget(row, col);
			Label wd = (Label) compareTable.getWidget(row + 1, col);
			Label elapsed = (Label) compareTable.getWidget(row + 3, col);
			Label dps = (Label) compareTable.getWidget(row + 5, col);

			bp.setText(String.valueOf(data.exportData.bp));
			wd.setText(Util.format(Math.round(data.exportData.data
					.getWeaponDamage())));
			dps.setText(Util.format(Math.round(data.exportData.sentryDps)));
			elapsed.setText(Util.format(Math
					.round(data.exportData.output.duration * 100.0) / 100.0));

			if ((which > 0) && (baseline != null)) {

				Label wdPctL = (Label) compareTable.getWidget(row + 2, col);
				Label dpsPctL = (Label) compareTable.getWidget(row + 6, col);
				Label elapsedPctL = (Label) compareTable
						.getWidget(row + 4, col);

				double wdPct = (baseline.exportData.data.getWeaponDamage() > 0) ? ((data.exportData.data.getWeaponDamage() - baseline.exportData.data
						.getWeaponDamage())
						/ baseline.exportData.data.getWeaponDamage()) : 0.0;
				double dpsPct = (baseline.exportData.sentryDps > 0) ? ((data.exportData.sentryDps - baseline.exportData.sentryDps)
						/ baseline.exportData.sentryDps) : 0.0;
				double elapsedPct = (baseline.exportData.output.duration > 0) ? ((data.exportData.output.duration - baseline.exportData.output.duration)
						/ baseline.exportData.output.duration) : 0.0;

				wdPctL.setText("(" + ((wdPct >= 0) ? "+" : "")
						+ Util.format(Math.round(wdPct * 1000.0) / 10.0) + "%)");
				dpsPctL.setText("(" + ((dpsPct >= 0) ? "+" : "")
						+ Util.format(Math.round(dpsPct * 1000.0) / 10.0)
						+ "%)");
				elapsedPctL.setText("(" + ((elapsedPct >= 0) ? "+" : "")
						+ Util.format(Math.round(elapsedPct * 1000.0) / 10.0)
						+ "%)");

			}

		}

		if (which == 0) {
			for (int i = 1; i < compareData.length; i++) {
				showBuildData(i);
			}
		}
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
								result, "true");
					}
				});

	}

	protected void restoreData(FormData data) {

		if (data == null)
			data = new FormData();

		disableListeners = true;
		situational.setDisableListeners(true);

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

		situational.setDisableListeners(false);
		disableListeners = false;

		this.heroList.setSelectedIndex(0);

		if ((data.items != null) && (data.items.size() > 0)) {
			if (gearPanel == null) {
				gearPanel = new GearPanel();
			}

			gearPanel.restoreData(data.items);
		} else {

			if (gearPanel != null)
				gearPanel.clearData();
		}

		this.typeDamage.setValues(Util.createMap(DamageType.class,
				data.elementalDamage));
		this.skillDamage.setValues(Util.createMap(ActiveSkill.class,
				data.skillDamage));
		this.passives.setPassives(Util.createSet(Passive.class, data.passives));
		this.calculator.getPassives().setPassives(
				Util.createSet(Passive.class, data.passives));
		this.skills.setSkills(Util.createEnumMap(ActiveSkill.class, Rune.class,
				data.skills));

		calculator.saveForm();
		this.saveForm();

		updateDps();
		calculate();

	}

	private static final String SLOT_PREFIX = "gear.";

	@Override
	protected void saveFields(Field... fields) {
		super.saveFields(fields);

		if (gearPanel != null) {
			Map<Slot, GearPanel.ItemHolder> items = gearPanel.getItems();

			for (Slot s : Slot.values()) {
				GearPanel.ItemHolder item = items.get(s);

				if ((item != null) && (item.getTooltip() != null)) {
					this.saveField(SLOT_PREFIX + s.getSlot(), item.getTooltip());
				}
			}
		}
	}

	private FormData getFormData() {
		FormData data = new FormData();

		super.populateFormData(data.main);
		calculator.populateFormData(data.calculator);

		if (gearPanel != null) {
			gearPanel.populateFormData(data.items);
		}

		data.passives = Util.createMap(passives.getPassives());
		data.skillDamage = Util.createMap(this.skillDamage.getValues());
		data.elementalDamage = Util.createMap(this.typeDamage.getValues());
		data.skills = Util.createEnumMap(skills.getSkills());

		data.version = Version.getVersion();

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
			label.setHref("javascript:void(0)");
			label.setTarget("_self");
		}
	}

	protected void updateSkillLabel(Anchor label, ListBox skills) {
		ActiveSkill skill = this.getSkill(skills);

		if (skill != null) {
			label.setHref(skill.getUrl());
			label.setTarget("_blank");
		} else {
			label.setHref("javascript:void(0)");
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
							MainPanel.this.paragonPanel.getParagonDexterity()
									.setValue(calculator.getParagonDexterity());
							MainPanel.this.paragonPanel.getParagonCDR()
									.setValue(calculator.getParagonCDR());
							MainPanel.this.paragonPanel.getParagonCC()
									.setValue(calculator.getParagonCC());
							MainPanel.this.paragonPanel.getParagonCHD()
									.setValue(calculator.getParagonCHD());
							MainPanel.this.paragonPanel.getParagonHatred()
									.setValue(calculator.getParagonHatred());
							MainPanel.this.paragonPanel.getParagonRCR()
									.setValue(calculator.getParagonRCR());
							MainPanel.this.paragonPanel.getParagonAD()
									.setValue(calculator.getParagonAD());
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
							updateCDRLabels();

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
						situational.setDisableListeners(true);

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

						situational.setDisableListeners(false);
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

								if (gearPanel == null) {
									gearPanel = new GearPanel();
								}

								gearPanel.setHero(hero);

								importHeroData(null, new AsyncTaskHandler() {

									@Override
									public void taskCompleted() {

									}
								});
							}
						});

			}

		}
	}

	protected void importHeroData(Integer paragonDexterity,
			AsyncTaskHandler handler) {
		data = ProfileHelper.importHero(hero, paragonDexterity);

		if (paragonDexterity == null) {
			paragonPanel.getParagonDexterity().setValue(
					data.getParagonDexterity() / 5);
		}

		data.setRealm(realm);
		data.setProfile(profile);
		data.setTag(tag);
		data.setHero(heroId);
		data.setParagonIAS(paragonPanel.getParagonIAS().getValue());
		data.setParagonCDR(paragonPanel.getParagonCDR().getValue());
		data.setParagonCC(paragonPanel.getParagonCC().getValue());
		data.setParagonCHD(paragonPanel.getParagonCHD().getValue());
		data.setParagonHatred(paragonPanel.getParagonHatred().getValue());
		data.setParagonRCR(paragonPanel.getParagonRCR().getValue());
		data.setParagonAD(paragonPanel.getParagonAD().getValue());

		ProfileHelper.updateCdr(data);
		ProfileHelper.updateWeaponDamage(data);

		setHeroSkills();

		setElementalDamage();

		setSkillDamage();

		setGemDamage();

		setSkills();

		calculator.importHero(server, profile, tag, heroId, data);

		calculator.saveForm();

		updateDps();

		doCalculate(handler);
	}

	private void setSkills() {

		skills.setSkills(data.getSkills());
		hatredPanel.getHatredPerSecond().setValue(data.getHatredPerSecond());
		hatredPanel.getEquipmentDiscipline().setValue(
				data.getEquipmentDiscipline());
	}

	private void updateParagonPoints() {
		this.calculator.setParagonPoints(
				getValue(MainPanel.this.paragonPanel.getParagonIAS()),
				getValue(MainPanel.this.paragonPanel.getParagonDexterity()),
				getValue(MainPanel.this.paragonPanel.getParagonCDR()),
				getValue(MainPanel.this.paragonPanel.getParagonCC()),
				getValue(MainPanel.this.paragonPanel.getParagonCHD()),
				getValue(MainPanel.this.paragonPanel.getParagonHatred()),
				getValue(MainPanel.this.paragonPanel.getParagonRCR()),
				getValue(MainPanel.this.paragonPanel.getParagonAD()));
		this.calculator.saveForm();
	}

	protected void setGemDamage() {

		this.gemPanel.getBot().setValue(data.isUseBaneOfTheTrapped());
		this.gemPanel.getEnforcer().setValue(data.isUseEnforcer());
		this.gemPanel.getIceblink().setValue(data.isIceblink());
		this.gemPanel.getBotp().setValue(data.isBotp());
		this.gemPanel.getBotLevel().setValue(data.getBaneOfTheTrappedLevel());
		this.gemPanel.getEnforcerLevel().setValue(data.getEnforcerLevel());
		this.gemPanel.getIceblinkLevel().setValue(data.getIceblinkLevel());
		this.gemPanel.getBotpLevel().setValue(data.getBotpLevel());
		this.gemPanel.getZeis().setValue(data.isZeis());
		this.gemPanel.getZeisLevel().setValue(data.getZeisLevel());
		this.gemPanel.getTaeguk().setValue(data.isTaeguk());
		this.gemPanel.getTaegukLevel().setValue(data.getTaegukLevel());
		this.gemPanel.getGogok().setValue(data.isGogok());
		this.gemPanel.getGogokLevel().setValue(data.getGogokLevel());
		this.gemPanel.getToxin().setValue(data.isToxin());
		this.gemPanel.getToxinLevel().setValue(data.getToxinLevel());
		this.gemPanel.getPainEnhancer().setValue(data.isPainEnhancer());
		this.calculator.setPainEnhancer(data.isPainEnhancer());
		this.gemPanel.getPainEnhancerLevel().setValue(
				data.getPainEnhancerLevel());
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
		this.punishmentCD = 20.0 * (1 - effCdr);
		this.sentryCD = 8.0 * (1 - effCdr);
		double wolfCD = 30.0 * (1 - effCdr);
		double rovCD = 30.0 * (1 - effCdr);

		// if (itemPanel.getNumNats().getValue() >= 4)
		// rovCD = Math.max(0.0, rovCD - (skills.getRovKilled().getValue() *
		// 2.0));

		if (itemPanel.getNumNats().getValue() >= 2) {
			double interval = (1.0 / calculator.getSheetAps())
					+ (situational.getFiringDelay().getValue() / 1000.0);
			double numAttacks = rovCD / (interval + 2.0);
			rovCD = numAttacks * interval;
		}

		this.rawCDRLabel
				.setText(Util.format(Math.round(rawCdr * 10000.0) / 100.0)
						+ "%");
		this.effectiveCDRLabel
				.setText(Util.format(Math.round(effCdr * 10000.0) / 100.0)
						+ "%");
		this.punishmentCDLabel.setText(Util.format(Math
				.round(punishmentCD * 100.0) / 100.0) + " sec");
		this.sentryCDLabel
				.setText(Util.format(Math.round(sentryCD * 100.0) / 100.0)
						+ " sec");
		this.wolfCDLabel
				.setText(Util.format(Math.round(wolfCD * 100.0) / 100.0)
						+ " sec");
		this.rovCDLabel.setText(Util.format(Math.round(rovCD * 100.0) / 100.0)
				+ " sec");
	}

	protected void updateDpsLabels() {
		this.sheetDps.setText(Util.format(calculator.getSheetDps()));
		this.aps.setText(Util.format(calculator.getSheetAps()));
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
		this.dexterity.setText(String.valueOf(calculator.getTotalDexterity()));
		this.critChance.setText(Util.format(Math.round(calculator
				.getCritChance() * 1000.0) / 10.0) + "%");
		this.critDamage.setText(Util.format(Math.round(calculator
				.getCritDamage() * 100.0)) + "%");
		this.avgWeaponDamage.setText(Util.format(calculator
				.getTotalAverageWeaponDamage()));
		double aps = this.calculator.getSheetAps();
		double petIas = (this.itemPanel.getTnt().getValue() ? (this.itemPanel
				.getTntPercent().getValue() / 100.0) : 0.0);

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

		getSetSetCDR(cdrPanel.getBorn(), data.getSetCounts(),
				data.isRoyalRing(), Const.BORNS, 2);
		getSetSetCDR(cdrPanel.getCrimson(), data.getSetCounts(),
				data.isRoyalRing(), Const.CAPTAIN_CRIMSON, 2);

		this.cdrPanel.getLeorics().setValue(data.isLeorics());
		this.cdrPanel.getLeoricsLevel().setValue(
				(int) (Math.round(data.getLeoricsPercent() * 100.0)));
		this.cdrPanel.setDiamond(data.getDiamond());

		getSetRCR(rcrPanel.getShoulders(), Const.SHOULDERS);
		getSetRCR(rcrPanel.getGloves(), Const.GLOVES);
		getSetRCR(rcrPanel.getRing1(), Const.RING1);
		getSetRCR(rcrPanel.getRing2(), Const.RING2);
		getSetRCR(rcrPanel.getBelt(), Const.BELT);
		getSetRCR(rcrPanel.getWeapon(), Const.WEAPON);
		getSetRCR(rcrPanel.getQuiver(), Const.QUIVER);
		getSetRCR(rcrPanel.getAmulet(), Const.AMULET);

		getSetSetRCR(rcrPanel.getCrimson(), data.getSetCounts(),
				data.isRoyalRing(), Const.CAPTAIN_CRIMSON, 3);

		this.rcrPanel.getPridesFall().setValue(data.isPridesFall());

		this.itemPanel.getTnt().setValue(data.isTnt());
		this.itemPanel.getTntPercent().setValue(
				(int) (Math.round(data.getTntPercent() * 100.0)));
		this.itemPanel.getCalamity().setValue(data.isCalamityMdf());
		this.itemPanel.getBombadiers().setValue(data.isHasBombardiers());
		this.itemPanel.getDml().setValue(data.isDml());
		this.itemPanel.getDmlPercent().setValue(
				(int) Math.round(data.getDmlPercent() * 100.0));
		this.itemPanel.getBastions().setValue(data.isBastions());
		this.itemPanel.getCrashingRain().setValue(data.isCrashingRain());
		this.itemPanel.getCrashingRainPercent().setValue(
				(int) Math.round(data.getCrashingRainPercent() * 100.0));
		this.itemPanel.getVaxo().setValue(data.isVaxo());
		this.itemPanel.getVaxoUptime().setValue(
				(int) Math.round(data.getVaxoUptime() * 100.0));
		this.itemPanel.getCoe().setValue(data.isCoe());
		this.itemPanel.getCoePercent().setValue(
				(int) Math.round(data.getCoePercent() * 100.0));
		this.itemPanel.getHelltrapper().setValue(data.isHelltrapper());
		this.itemPanel.getHelltrapperPercent().setValue(
				(int) Math.round(data.getHelltrapperPercent() * 100));
		this.itemPanel.getOdysseysEnd().setValue(data.isOdysseysEnd());
		this.itemPanel.getOdysseysEndPercent().setValue(
				(int) Math.round(data.getOdysseysEndPercent() * 100.0));
		this.itemPanel.getReapersWraps().setValue(data.isReapersWraps());
		this.itemPanel.getReapersWrapsPercent().setValue(
				(int) Math.round(data.getReapersWrapsPercent() * 100.0));
		this.itemPanel.getCindercoat().setValue(data.isCindercoat());
		this.itemPanel.getCindercoatPercent().setValue(
				(int) Math.round(data.getCindercoatRCR() * 100.0));
		this.itemPanel.getSpines().setValue(data.isSpines());
		this.itemPanel.getKridershot().setValue(data.isKridershot());
		this.itemPanel.getSpinesHatred().setValue(data.getSpinesHatred());
		this.itemPanel.getKridershotHatred().setValue(
				data.getKridershotHatred());
		this.itemPanel.getMarauders().setValue(data.getNumMarauders());
		this.itemPanel.getNumUe().setValue(data.getNumUe());
		this.itemPanel.getNumNats().setValue(data.getNumNats());
		this.itemPanel.getEliteDamagePercent().setValue(
				(int) Math.round(data.getEliteDamage() * 100.0));
		this.itemPanel.getAreaDamageEquipment().setValue(
				(int) Math.round(data.getAreaDamageEquipment() * 100.0));
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

		this.skillDamage.setValues(data.getSkillDamage());
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

		typeDamage.setValues(data.getElementalDamage());

	}

	protected void setHeroSkills() {
		this.passives.setPassives(data.getPassives());
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
				new Field(this.timeLimit, "TimeLimit", "120"),
				new Field(this.paragonPanel.getParagonIAS(), "ParagonIas", "0"),
				new Field(this.paragonPanel.getParagonDexterity(),
						"ParagonDex", "0"),
				new Field(this.paragonPanel.getParagonCDR(), "ParagonCDR", "0"),
				new Field(this.paragonPanel.getParagonCC(), "ParagonCC", "0"),
				new Field(this.paragonPanel.getParagonCHD(), "ParagonCD", "0"),
				new Field(this.paragonPanel.getParagonHatred(),
						"ParagonHatred", "0"),
				new Field(this.paragonPanel.getParagonRCR(), "ParagonRCR", "0"),
				new Field(this.paragonPanel.getParagonAD(), "ParagonAD", "0"),
				new Field(this.itemPanel.getTnt(), "TnT",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getCalamity(), "Calamity",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getBombadiers(), "Bombadiers",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getDml(), "DML",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getDmlPercent(), "DMLPercent", "50"),
				new Field(this.itemPanel.getBastions(), "BastionsOfWill",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getCrashingRain(), "CrashingRain",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getCrashingRainPercent(),
						"CrashingRainPercent", "3000"),
				new Field(this.itemPanel.getVaxo(), "HauntOfVaxo",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getVaxoUptime(), "VaxoUptime", "50"),
				new Field(this.itemPanel.getCoe(), "COE",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getCoePercent(), "CoePercent", "150"),
				new Field(this.itemPanel.getHelltrapper(), "Helltrapper",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getHelltrapperPercent(),
						"HelltrapperPercent", "7"),
				new Field(this.itemPanel.getSpines(), "Spines",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getKridershot(), "Kridershot",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getSpinesHatred(), "SpinesHatred",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getKridershotHatred(),
						"KridershotHatred", Boolean.FALSE.toString()),
				new Field(this.itemPanel.getMarauders(), "Marauders", "6"),
				new Field(this.itemPanel.getNumUe(), "UE", "0"),
				new Field(this.itemPanel.getNumNats(), "Nats", "0"),
				new Field(this.itemPanel.getReapersWraps(), "ReapersWraps",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getReapersWrapsPercent(),
						"ReapersWrapsPercent", "25"),
				new Field(this.itemPanel.getCindercoat(), "Cindercoat",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getCindercoatPercent(),
						"CindercoatPercent", "23"),
				new Field(this.situational.getNumHealthGlobes(),
						"HealthGlobes", "1"),
				new Field(this.situational.getFiringDelay(), "FiringDelay",
						"50"),
				new Field(this.itemPanel.getCalamityUptime(), "CalamityUptime",
						"100"),
				new Field(this.skills.getMfdUptime(), "MarkedForDeathUptime",
						"100"),
				new Field(this.skills.getCaltropsUptime(), "CaltropsUptime",
						"100"),
				new Field(this.skills.getNumSpikeTraps(), "NumSpikeTraps", "3"),
				new Field(this.hatredPanel.getHatredPerSecond(),
						"EquipHatredPerSecond", "0.0"),
				new Field(this.hatredPanel.getEquipmentDiscipline(),
						"EquipDiscipline", "0.0"),
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

				new Field(this.itemPanel.getOdysseysEnd(), "OdysseysEnd",
						Boolean.FALSE.toString()),
				new Field(this.itemPanel.getOdysseysEndPercent(),
						"OdysseysEndPercent", "20"),
				new Field(this.itemPanel.getOdysseysEndUptime(),
						"OdysseysEndUptime", "100"),

				new Field(this.itemPanel.getMeticulousBoltsPercent(),
						"MeticulousBoltsPercent", "30"),
				new Field(this.situational.getPercentSlowedChilled(),
						"PercentSlowedChilled", "100"),
				new Field(this.situational.getPercentControlled(),
						"PercentControlled", "100"),
				new Field(this.situational.getAdditional(), "AdditionalTargets", "0"),
				new Field(this.situational.getTargetSize(), "TargetSize",
						TargetSize.Small.name()),
				new Field(this.situational.getPercentAtLeast10Yards(),
						"PercentAtleast10Yards", "100"),
				new Field(this.gemPanel.getBot(), "BoT",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getGogok(), "Gogok",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getTaeguk(), "Taeguk",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getEnforcer(), "Enforcer",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getIceblink(), "Iceblinnk",
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
				new Field(this.gemPanel.getIceblinkLevel(), "IceblinkLevel",
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
				new Field(this.itemPanel.getAreaDamageEquipment(),
						"AreaDamageEquipment", "0"),
				new Field(this.gemPanel.getBotp(), "BotP",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getBotpLevel(), "BotPLevel", "0"),
				new Field(this.gemPanel.getZeis(), "Zei's",
						Boolean.FALSE.toString()),
				new Field(this.gemPanel.getZeisLevel(), "Zei'sLevel", "0"),
				new Field(this.situational.getDistance(), "TargetDistance",
						"50"),
				new Field(this.situational.getTargetSpacing(), "TargetSpacing",
						"10"),
				new Field(this.situational.getRiftLevel(), "GRiftlevel", "25"),
				new Field(this.situational.getNumPlayers(), "NumPlayers", "1"),
				new Field(this.situational.getPrimaryTargetType(),
						"PrimaryTargetType", MonsterType.RiftGuardian.name()),
				new Field(this.situational.getAdditionalTargetType(),
						"AdditionalTargetType", MonsterType.NonElite.name()),
				new Field(this.situational.getPrimaryTargetHealth(),
						"PrimaryTargetHealth", String.valueOf(MonsterHealth
								.getHealth(25, 1, MonsterType.RiftGuardian))),
				new Field(this.situational.getAdditionalTargetsHealth(),
						"AddTargetHealth", String.valueOf(MonsterHealth
								.getHealth(25, 1, MonsterType.NonElite))),

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
				new Field(this.buffPanel.getInspire(), "Inspire",
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
				new Field(this.playerBuffPanel.getWolf(), "OtherWolf",
						Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getWolfUptime(), "WolfUptime",
						"33.33"),
				new Field(this.playerBuffPanel.getMassConfusion(),
						"MassConfusion", Boolean.FALSE.toString()),
				new Field(this.playerBuffPanel.getMassConfusionUptime(),
						"MassConfusionUptime", "20.0"),
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

			// data.setDuration(duration.getValue());
			data.setTimeLimit(timeLimit.getValue());
			data.setSkills(skills.getSkills());
			data.setEquipmentDexterity(calculator.getEquipmentDexterity());
			data.setParagonCC(paragonPanel.getParagonCC().getValue());
			data.setParagonIAS(paragonPanel.getParagonIAS().getValue());
			data.setParagonCHD(paragonPanel.getParagonCHD().getValue());
			data.setParagonCDR(paragonPanel.getParagonCDR().getValue());
			data.setParagonDexterity(paragonPanel.getParagonDexterity()
					.getValue() * 5);
			data.setHeroLevel(calculator.getHeroLevel());
			data.setParagonHatred(paragonPanel.getParagonHatred().getValue());
			data.setParagonRCR(paragonPanel.getParagonRCR().getValue());
			data.setParagonAD(paragonPanel.getParagonAD().getValue());
			data.setSheetDps(calculator.getSheetDps());
			data.setAps(calculator.getSheetAps());
			data.setElementalDamage(this.typeDamage.getValues());
			data.setSkillDamage(skillDamage.getValues());
			data.setPassives(passives.getPassives());
			data.setCritHitDamage(calculator.getCritDamage());
			data.setPercentSlowedChilled((double) this.situational
					.getPercentSlowedChilled().getValue() / 100.0);
			data.setPercentControlled((double) this.situational
					.getPercentSlowedChilled().getValue() / 100.0);
			data.setNumAdditional(getValue(this.situational.getAdditional()));
			data.setUseBaneOfTheTrapped(this.gemPanel.getBot().getValue());
			data.setUseEnforcer(this.gemPanel.getEnforcer().getValue());
			data.setIceblink(this.gemPanel.getIceblink().getValue());
			data.setBaneOfTheTrappedLevel(getValue(this.gemPanel.getBotLevel()));
			data.setEnforcerLevel(getValue(this.gemPanel.getEnforcerLevel()));
			data.setIceblinkLevel(getValue(this.gemPanel.getIceblinkLevel()));
			data.setEliteDamage(getValue(this.itemPanel.getEliteDamagePercent()) / 100.0);
			data.setAreaDamageEquipment(getValue(this.itemPanel
					.getAreaDamageEquipment()) / 100.0);
			data.setBotp(this.gemPanel.getBotp().getValue());
			data.setBotpLevel(getValue(this.gemPanel.getBotpLevel()));
			data.setCalamityMdf(itemPanel.getCalamity().getValue());
			data.setHasBombardiers(itemPanel.getBombadiers().getValue());
			data.setDml(itemPanel.getDml().getValue());
			data.setDmlPercent(itemPanel.getDmlPercent().getValue() / 100.0);
			data.setBastions(itemPanel.getBastions().getValue());
			data.setCrashingRain(itemPanel.getCrashingRain().getValue());
			data.setCrashingRainPercent(itemPanel.getCrashingRainPercent()
					.getValue() / 100.0);
			data.setVaxo(itemPanel.getVaxo().getValue());
			data.setVaxoUptime(itemPanel.getVaxoUptime().getValue() / 100.0);
			data.setCoe(itemPanel.getCoe().getValue());
			data.setCoePercent(itemPanel.getCoePercent().getValue() / 100.0);
			data.setHelltrapper(itemPanel.getHelltrapper().getValue());
			data.setHelltrapperPercent(itemPanel.getHelltrapperPercent()
					.getValue() / 100.0);
			data.setNumMarauders(itemPanel.getMarauders().getValue());
			data.setNumUe(itemPanel.getNumUe().getValue());
			data.setNumNats(itemPanel.getNumNats().getValue());
			data.setPercentAtLeast10Yards((double) this.situational
					.getPercentAtLeast10Yards().getValue() / 100.0);
			data.setZeis(this.gemPanel.getZeis().getValue());
			data.setZeisLevel(this.getValue(this.gemPanel.getZeisLevel()));
			data.setDistanceToTarget(this.getValue(this.situational
					.getDistance()));
			data.setRiftLevel(this.situational.getRiftLevel().getValue());
			data.setNumPlayers(this.situational.getNumPlayers().getValue());
			data.setPrimaryTargetHealth(this.situational
					.getPrimaryTargetHealth().getValue());
			data.setAdditionalTargetHealth(this.situational
					.getAdditionalTargetsHealth().getValue());
			data.setPrimaryTargetType(SituationalPanel
					.getMonsterType(this.situational.getPrimaryTargetType()));
			data.setAdditionalTargetType(SituationalPanel
					.getMonsterType(this.situational.getAdditionalTargetType()));
			data.setTargetSpacing(this.situational.getTargetSpacing()
					.getValue());
			data.setEquipIas(calculator.getEquipIAS());
			data.setEquipCritDamage(calculator.getEquipmentCritDamage());
			data.setEquipCritChance(calculator.getEquipmentCritChance());
			data.setJewelMin(calculator.getJewelMin());
			data.setJewelMax(calculator.getJewelMax());
			data.setWeaponDamage(calculator.getMainHandAverageWeaponDamage());
			data.setWeaponDamagePercent(calculator.getMainHand()
					.getWeaponDamage().getValue() / 100.0);
			data.setBaseMin(calculator.getMainHand().getBaseMin().getValue());
			data.setBaseMax(calculator.getMainHand().getBaseMax().getValue());
			data.setAddMin(calculator.getMainHand().getAddMin().getValue());
			data.setAddMax(calculator.getMainHand().getAddMax().getValue());
			data.setWeaponIas(calculator.getWeaponIAS());
			data.setWeaponType(calculator.getMainHandWeaponType());

			data.setOffHand_weaponDamagePercent(calculator.getOffHand()
					.getWeaponDamage().getValue() / 100.0);
			data.setOffHand_weaponDamage(calculator
					.getOffHandAverageWeaponDamage());
			data.setOffHand_weaponType(calculator.getOffHandWeaponType());
			data.setOffHand_weaponIas(calculator.getOffHandWeaponIAS());
			data.setOffHand_baseMin(calculator.getOffHand().getBaseMin()
					.getValue());
			data.setOffHand_baseMax(calculator.getOffHand().getBaseMax()
					.getValue());
			data.setOffHand_addMin(calculator.getOffHand().getAddMin()
					.getValue());
			data.setOffHand_addMax(calculator.getOffHand().getAddMax()
					.getValue());

			data.setGogok(gemPanel.getGogok().getValue());
			data.setGogokLevel(gemPanel.getGogokLevel().getValue());
			data.setGogokStacks(gemPanel.getGogokStacks().getValue());
			data.setCdr(this.effCdr);
			data.setRcr(this.effRcr);
			data.setFocusedMind(buffPanel.getFocusedMind().getValue());
			data.setInspire(buffPanel.getInspire().getValue());
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
			data.setCaltropsUptime(skills.getCaltropsUptime().getValue() / 100.0);
			data.setNumSpikeTraps(skills.getNumSpikeTraps().getValue());
			data.setHatredPerSecond(hatredPanel.getHatredPerSecond().getValue());
			data.setEquipmentDiscipline(hatredPanel.getEquipmentDiscipline()
					.getValue());
			data.setSpines(itemPanel.getSpines().getValue());
			data.setKridershot(itemPanel.getKridershot().getValue());
			data.setSpinesHatred(itemPanel.getSpinesHatred().getValue());
			data.setKridershotHatred(itemPanel.getKridershotHatred().getValue());
			data.setReapersWraps(itemPanel.getReapersWraps().getValue());
			data.setReapersWrapsPercent(itemPanel.getReapersWrapsPercent()
					.getValue() / 100.0);
			data.setNumHealthGlobes(situational.getNumHealthGlobes().getValue());
			data.setDelay(situational.getFiringDelay().getValue());
			data.setCindercoat(itemPanel.getCindercoat().getValue());
			data.setCindercoatRCR(itemPanel.getCindercoatPercent().getValue() / 100.0);
			data.setOdysseysEnd(itemPanel.getOdysseysEnd().getValue());
			data.setOdysseysEndPercent(itemPanel.getOdysseysEndPercent()
					.getValue() / 100.0);
			data.setOdysseysEndUptime(itemPanel.getOdysseysEndUptime()
					.getValue() / 100.0);

			ProfileHelper.updateWeaponDamage(data);

			try {
				this.damage = FiringData.calculateDamages(data);

				types = new TreeMap<DamageType, DamageHolder>();
				skillDamages = new TreeMap<DamageSource, DamageHolder>();
				shooterDamages = new TreeMap<String, DamageHolder>();

				this.exportData = new ExportData();
				this.exportData.data = data;
				this.exportData.output = damage;
				this.exportData.skills = new TreeMap<ActiveSkill, Rune>(
						data.getSkills());
				this.exportData.types = types;
				this.exportData.skillDamages = skillDamages;
				this.exportData.shooterDamages = shooterDamages;
				this.exportData.multiple = new Vector<MultipleSummary>();
				this.exportData.sentryBaseDps = calculator.getSentryDps();
				this.exportData.bp = data.getBp();

				calculateData();

				updateOutput();

			} catch (Exception e) {
				GWT.log(e.getMessage(), e);
			}

		} finally {
			if (dialog != null)
				dialog.taskCompleted();
		}
	}

	private void calculateData() {
		total = 0.0;

		Damage prev = null;

		for (Damage d : damage.damages) {

			try {
				total += d.actualDamage;

				if (d.type != null) {
					DamageType type = d.type;

					DamageHolder h = types.get(type);

					DamageSource source = d.source;

					if ((source.skill != null) && (source.rune != null)) {
						source = new DamageSource(source.skill, null);
					}

					DamageHolder th = skillDamages.get(source);
					DamageHolder sh = shooterDamages.get(d.shooter);

					if (h == null) {
						h = new DamageHolder();
						h.damage = d.actualDamage;
						h.attacks = 1;
						types.put(type, h);
					} else {
						h.damage += d.actualDamage;

						if ((prev.time != d.time)
								|| !prev.source.equals(d.source)) {
							h.attacks++;
						}
					}

					if (th == null) {
						th = new DamageHolder();
						th.damage = d.actualDamage;
						th.attacks = 1;
						skillDamages.put(source, th);
					} else {
						th.damage += d.actualDamage;

						if ((prev.time != d.time)
								|| !prev.source.equals(d.source)) {
							th.attacks++;
						}
					}

					if (sh == null) {
						sh = new DamageHolder();
						sh.damage = d.actualDamage;
						sh.attacks = 1;
						shooterDamages.put(d.shooter, sh);
					} else {
						sh.damage += d.actualDamage;

						if ((prev.time != d.time)
								|| !prev.source.equals(d.source)) {
							sh.attacks++;
						}
					}

					prev = d;
				}
			} catch (Exception e) {
				GWT.log("Exception", e);
				return;
			}

		}

		double dps = (damage.duration > 0) ? Math.round(total / damage.duration) : total;

		this.exportData.sentryDps = dps;
		this.exportData.totalDamage = total;
	}

	private void updateOutput() {

		Label ns = new Label("" + data.getNumSentries());
		ns.addStyleName("boldText");
		outputHeader.setWidget(2, 7, ns);

		while (damageLog.getRowCount() > 1) {
			damageLog.removeRow(1);
		}

		for (int i = damageLog.getRowCount(); i > 1; --i) {
			damageLog.removeRow(i - 1);
		}

		for (int i = statTable.getRowCount(); i > 1; --i) {
			statTable.removeRow(i - 1);
		}

		for (int i = shooterSummary.getRowCount(); i > 1; --i) {
			shooterSummary.removeRow(i - 1);
		}

		this.captionPanelDamageLog.setCaptionHTML("Damage Log ("
				+ damage.duration + " seconds)");

		for (int row = 0; row < damage.damages.length; row++) {
			if ((row % 2) == 0)
				damageLog.getRowFormatter().addStyleName(row + 1, "oddRow");
			else
				damageLog.getRowFormatter().addStyleName(row + 1, "evenRow");

			Damage d = damage.damages[row];

			int col = 0;

			Label timeLabel = new Label(
					Util.format(Math.round(d.time * 100.0) / 100.0), false);
			timeLabel.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, col++, timeLabel);

			Label sLabel = new Label(d.shooter);
			sLabel.setWordWrap(false);
			damageLog.setWidget(row + 1, col++, sLabel);

			if (d.source != null) {
				ActiveSkill skill = d.source.skill;
				GemSkill gem = d.source.gem;
				Anchor a = new Anchor((skill != null) ? skill.getLongName()
						: gem.getDisplayName());
				a.setTarget("_blank");
				a.setWordWrap(false);
				String url = (skill != null) ? skill.getUrl() : gem.getUrl();
				a.setHref(url);

				damageLog.setWidget(row + 1, col++, a);

				if (skill != null) {
					Anchor b = new Anchor(d.source.rune.getLongName());
					b.setTarget("_blank");
					b.setWordWrap(false);

					if (d.source.skill == ActiveSkill.CR)
						url = ActiveSkill.RoV.getUrl();

					if (d.source.rune != Rune.None)
						url += ("#" + d.source.rune.getSlug() + "+");

					b.setHref(url);

					damageLog.setWidget(row + 1, col++, b);
				} else {
					Label b = new Label("N/A");
					damageLog.setWidget(row + 1, col++, b);
				}
			} else if (d.shooter.equals("Preparation")) {
				Anchor b = new Anchor("Preparation");
				b.setTarget("_blank");
				b.setWordWrap(false);
				b.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/preparation");
				damageLog.setWidget(row + 1, col++, b);

				Anchor b2 = new Anchor("Punishment");
				b2.setTarget("_blank");
				b2.setWordWrap(false);
				b2.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/preparation#a+");
				damageLog.setWidget(row + 1, col++, b2);
			} else if (d.shooter.equals("Companion") && d.hatred != 0) {
				Anchor b = new Anchor("Companion");
				b.setTarget("_blank");
				b.setWordWrap(false);
				b.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/companion");
				damageLog.setWidget(row + 1, col++, b);

				Anchor b2 = new Anchor("Bat");
				b2.setTarget("_blank");
				b2.setWordWrap(false);
				b2.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/companion#d+");
				damageLog.setWidget(row + 1, col++, b2);
			} else if (d.shooter.equals("MFD") && d.hatred != 0) {
				Anchor b = new Anchor("MfD");
				b.setTarget("_blank");
				b.setWordWrap(false);
				b.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/marked-for-death");
				damageLog.setWidget(row + 1, col++, b);

				Anchor b2 = new Anchor("Mortal Enemy");
				b2.setTarget("_blank");
				b2.setWordWrap(false);
				b2.setHref("http://us.battle.net/d3/en/class/demon-hunter/active/marked-for-death#d+");
				damageLog.setWidget(row + 1, col++, b2);
			} else {
				col += 2;
			}

			if (d.type != null)
				damageLog.setWidget(row + 1, col++, new Label(d.type.name(),
						false));
			else
				col++;

			if (d.hatred != 0) {
				Label hatredLabel = new Label(Util.format(Math
						.round(d.hatred * 10.0) / 10.0), false);
				hatredLabel.addStyleName("dpsCol");
				damageLog.setWidget(row + 1, col++, hatredLabel);
			} else {
				col++;
			}

			Label hatredLabel2 = new Label(Util.format(Math
					.round(d.currentHatred * 10.0) / 10.0), false);
			hatredLabel2.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, col++, hatredLabel2);

			if (d.disc != 0) {
				Label discLabel = new Label(Util.format(Math
						.round(d.disc * 10.0) / 10.0), false);
				discLabel.addStyleName("dpsCol");
				damageLog.setWidget(row + 1, col++, discLabel);
			} else {
				col++;
			}

			Label discLabel2 = new Label(Util.format(Math
					.round(d.currentDisc * 10.0) / 10.0), false);
			discLabel2.addStyleName("dpsCol");
			damageLog.setWidget(row + 1, col++, discLabel2);

			if (d.damage > 0) {
				Label totalLabel = new Label(Util.format(Math.round(d.damage)),
						false);
				totalLabel.addStyleName("dpsCol");
				damageLog.setWidget(row + 1, col++, totalLabel);

				Label dpsLabel = new Label(
						Util.format(Math.round((d.targetHp))), false);
				dpsLabel.addStyleName("dpsCol");
				damageLog.setWidget(row + 1, col++, dpsLabel);
				double pct = Math.round(d.targetHpPercent * 1000.0) / 10.0;
				Label pctLabel = new Label(Util.format(pct) + "%", false);
				pctLabel.addStyleName("dpsCol");
				damageLog.setWidget(row + 1, col++, pctLabel);
			} else {
				col += 3;
			}

			if (d.target != null) {
				damageLog.setWidget(row + 1, col++, new Label(d.target.toString(), false));
			} else {
				damageLog.setWidget(row + 1, col++, new Label("", false));
			}

			if (d.note != null) {
				damageLog.setWidget(row + 1, col++, new Label(d.note, false));
			} else {
				damageLog.setWidget(row + 1, col++, new Label("", false));
			}

			if (d.log != null) {
				Label log = new Label(d.log);
				log.setWordWrap(false);
				damageLog.setWidget(row + 1, col++, log);
			} else {
				damageLog.setWidget(row + 1, col++, new Label("", false));
			}

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

			Label label2 = new Label(Util.format(da), false);
			label2.addStyleName("dpsCol");
			summary.setWidget(row, 2, label2);

			Label damageLabel = new Label(Util.format(Math.round(d)), false);
			damageLabel.addStyleName("dpsCol");
			summary.setWidget(row, 3, damageLabel);

			Label dpsLabel = new Label(Util.format(Math.round((d)
					/ damage.duration)), false);
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

			Label label2 = new Label(Util.format(da), false);
			label2.addStyleName("dpsCol");
			skillSummary.setWidget(row, 2, label2);

			Label damageLabel = new Label(Util.format(Math.round(d)), false);
			damageLabel.addStyleName("dpsCol");
			skillSummary.setWidget(row, 3, damageLabel);

			Label dpsLabel = new Label(Util.format(Math.round((d)
					/ damage.duration)), false);
			dpsLabel.addStyleName("dpsCol");
			skillSummary.setWidget(row, 4, dpsLabel);

			double pct = Math.round((d / total) * 10000.0) / 100.0;
			Label pctLabel = new Label(String.valueOf(pct) + "%", false);
			pctLabel.addStyleName("dpsCol");
			skillSummary.setWidget(row, 5, pctLabel);
			row++;
		}

		row = 1;
		for (Map.Entry<String, DamageHolder> e : shooterDamages.entrySet()) {
			if ((row % 2) == 0)
				shooterSummary.getRowFormatter().addStyleName(row, "evenRow");
			else
				shooterSummary.getRowFormatter().addStyleName(row, "oddRow");

			Label a = new Label(e.getKey());
			a.setWordWrap(false);
			shooterSummary.setWidget(row, 0, a);

			int attacks = e.getValue().attacks;
			double d = e.getValue().damage;
			double da = Math.round((d / attacks));

			Label label1 = new Label(String.valueOf(attacks), false);
			label1.addStyleName("dpsCol");
			shooterSummary.setWidget(row, 1, label1);

			Label label2 = new Label(Util.format(da), false);
			label2.addStyleName("dpsCol");
			shooterSummary.setWidget(row, 2, label2);

			Label damageLabel = new Label(Util.format(Math.round(d)), false);
			damageLabel.addStyleName("dpsCol");
			shooterSummary.setWidget(row, 3, damageLabel);

			Label dpsLabel = new Label(Util.format(Math.round((d)
					/ damage.duration)), false);
			dpsLabel.addStyleName("dpsCol");
			shooterSummary.setWidget(row, 4, dpsLabel);

			double pct = Math.round((d / total) * 10000.0) / 100.0;
			Label pctLabel = new Label(String.valueOf(pct) + "%", false);
			pctLabel.addStyleName("dpsCol");
			shooterSummary.setWidget(row, 5, pctLabel);
			row++;
		}

		double dps = (damage.duration > 0) ? Math.round(total / damage.duration) : total;
		double aps = data.getSentryAps();
		BreakPoint bp = BreakPoint.ALL[data.getBp() - 1];

		sentryAps.setText(Util.format(aps));
		breakPoint.setText(String.valueOf(bp.getBp()));
		double sentryAps = bp.getQty() / damage.duration;
		actualAps.setText(String.valueOf(Util.format(sentryAps)));
		aps30.setText(Util.format(bp.getQty()));

		weaponDamage
				.setText(Util.format(Math.round(data.getWeaponDamage() * 100.0) / 100.0));

		if (data.getOffHand_weaponType() != null) {
			offHand_weaponDamage.setText(Util.format(Math.round(data
					.getOffHand_weaponDamage() * 100.0) / 100.0));
			double dwDamage = (data.getWeaponDamage() + data
					.getOffHand_weaponDamage()) / 2.0;
			dw_weaponDamage
					.setText(Util.format(Math.round(dwDamage * 100.0) / 100.0)
							+ " sec");
		} else {
			offHand_weaponDamage.setText("N/A");
			dw_weaponDamage.setText("N/A");

		}

		row = 1;

		this.dps.setText(Util.format(Math.round(dps)));
		this.totalDamage.setText(Util.format(Math.round(total)));
		this.eliteDamage.setText(Math.round(data.getTotalEliteDamage() * 100.0)
				+ "%");
		this.timeElapsed.setText(Util.format(Math
				.round(damage.duration * 100.0) / 100.0) + " secs");

		row = 1;

		final CharacterData savedData = data.copy();

		final double baseline = total;
		final double duration = damage.duration;

		for (Stat stat : Stat.values()) {

			final StatAdapter adapter = stat.getAdapter();

			if (adapter.test(data, types.keySet())) {

				if ((row % 2) == 0)
					statTable.getRowFormatter().addStyleName(row, "evenRow");
				else
					statTable.getRowFormatter().addStyleName(row, "oddRow");

				HorizontalPanel panel = new HorizontalPanel();
				panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
				panel.setSpacing(0);

				panel.add(new Label("+", false));
				final DoubleSpinner spinner = new DoubleSpinner();
				// double value = stat.getAdapter().getDefaultValue();

				final String field = "Stat." + stat.getLabel();
				double value = Double.parseDouble(this.getFieldValue(field,
						String.valueOf(adapter.getDefaultValue())));

				spinner.setValue(value);
				spinner.setMax(value * 100.0);
				spinner.setMin(value * -100.0);
				spinner.setVisibleLength(4);

				panel.add(spinner);

				String label = stat.getLabel();

				int col = 0;
				Label l1 = new Label(label);
				l1.setWordWrap(false);
				panel.add(l1);

				statTable.setWidget(row, col++, panel);

				final Label l2 = new Label("");
				l2.addStyleName("dpsCol");
				statTable.setWidget(row, col++, l2);

				final Label l2a = new Label("");
				l2a.addStyleName("dpsCol");
				statTable.setWidget(row, col++, l2a);

				final Label l2b = new Label("");
				l2b.addStyleName("dpsCol");
				statTable.setWidget(row, col++, l2b);

				final Label l3 = new Label("");
				l3.addStyleName("dpsCol");
				statTable.setWidget(row, col++, l3);

				final Label l4 = new Label("");
				l4.addStyleName("dpsCol");
				statTable.setWidget(row, col++, l4);

				updateStatTable(adapter, spinner, savedData, row, baseline,
						duration);

				final int r = row;

				spinner.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						saveField(field, String.valueOf(spinner.getValue()));
						updateStatTable(adapter, spinner, savedData, r,
								baseline, duration);
					}
				});

				row++;
			}
		}
	}

	private void updateStatTable(StatAdapter adapter, DoubleSpinner spinner,
			CharacterData savedData, int row, double baseline,
			double baselineDuration) {
		double value = spinner.getValue();

		CharacterData data = savedData.copy();
		adapter.apply(value, data);

		DamageResult d = FiringData.calculateDamages(data);

		double totalRow = 0;

		for (Damage r : d.damages) {
			totalRow += r.actualDamage;
		}

		double dpsRow = (d.duration > 0) ? (totalRow / d.duration) : totalRow;
		double baselineDps = (baselineDuration > 0) ? (baseline / baselineDuration) : baseline;
		double duration = d.duration;
		double pct = (baselineDps > 0) ? ((dpsRow - baselineDps) / baselineDps) : 0.0;
		double pctDuration = (baselineDuration > 0) ? ((d.duration - baselineDuration) / baselineDuration) : 0.0;

		int col = 1;
		((Label) statTable.getWidget(row, col++)).setText(Util.format(Math
				.round(duration * 100.0) / 100.0) + "s");
		((Label) statTable.getWidget(row, col++))
				.setText(((pctDuration >= 0.0) ? "+" : "")
						+ Util.format(Math.round(pctDuration * 10000.0) / 100.0)
						+ "%");
		((Label) statTable.getWidget(row, col++)).setText(Util.format(Math
				.round(totalRow)));
		((Label) statTable.getWidget(row, col++)).setText(Util.format(Math
				.round(dpsRow)));
		((Label) statTable.getWidget(row, col++)).setText(((pct >= 0.0) ? "+"
				: "") + Util.format(Math.round(pct * 10000.0) / 100.0) + "%");
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
	protected void saveForm() {
		super.saveForm();

		super.saveField("passives",
				super.getFieldValue(passives.getPassives(), null));
		super.saveField("elemental.Damage",
				super.getFieldValue(this.typeDamage.getValues(), null));
		super.saveField("skill.Damage",
				super.getFieldValue(this.skillDamage.getValues(), null));
		super.saveField("skills",
				super.getFieldValue(this.skills.getSkills(), null));
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

		super.setFieldValue(skills, super.getFieldValue("skills", null));
		super.setFieldValue(passives, super.getFieldValue("passives", null));
		super.setFieldValue(calculator.getPassives(),
				super.getFieldValue("passives", null));
		super.setFieldValue(typeDamage,
				super.getFieldValue("elemental.Damage", null));
		super.setFieldValue(skillDamage,
				super.getFieldValue("skill.Damage", null));
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		Map<String, String> items = new TreeMap<String, String>();

		for (Slot s : Slot.values()) {
			String value = this.getFieldValue(SLOT_PREFIX + s.getSlot(), null);

			if (value != null)
				items.put(s.getSlot(), value);
		}

		disableListeners = true;
		this.situational.setDisableListeners(true);

		if (!Beans.isDesignTime()) {

			calculator.loadForm();
			loadForm();

			calculator.setDisableListeners(true);

			updateDps();

			calculator.setDisableListeners(false);

			calculate();

			if (items.size() > 0) {

				if (gearPanel == null) {
					gearPanel = new GearPanel();
				}

				gearPanel.restoreData(items);
			} else {
				if (gearPanel != null)
					gearPanel.clearData();

			}
		}

		this.situational.setDisableListeners(false);
		disableListeners = false;
	}

	public static native void saveFormData(String filename, String key,
			String isFile)
	/*-{
		var popupWindow = window.open('', '_self', '');
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
		var hiddenField3 = document.createElement("input");
		hiddenField3.setAttribute("type", "hidden");
		hiddenField3.setAttribute("name", "isFile");
		hiddenField3.setAttribute("value", isFile);
		form.appendChild(hiddenField);
		form.appendChild(hiddenField2);
		form.appendChild(hiddenField3);
		document.getElementsByTagName('body')[0].appendChild(form);
		form.submit();

		while (!popupWindow) {
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

	protected void showGearPanel() {

		if (gearPanel == null) {
			gearPanel = new GearPanel();
		}

		gearPanel.setVisible(true);
		gearPanel.updateLabels();

		ApplicationPanel.showDialogBox("Items", gearPanel,
				ApplicationPanel.APPLY_CHANGES | ApplicationPanel.CANCEL,
				new DialogBoxResultHandler() {

					@Override
					public void dialogBoxResult(int result) {

						gearPanel.setVisible(false);

						if (result == ApplicationPanel.APPLY_CHANGES) {

							AsyncTaskHandler dialog = ApplicationPanel
									.showWaitDialogBox("Please Wait",
											"Calculating...");

							Map<Slot, GearPanel.ItemHolder> items = gearPanel
									.getItems();

							if ((hero != null) && (hero.items != null)) {
								for (Slot s : Slot.values()) {
									final Slot slot = s;
									GearPanel.ItemHolder item = items.get(s);

									if (item == null) {
										hero.items.remove(s.getSlot());
									} else {
										item.getInfo(new DefaultCallback<ItemInformation>() {

											@Override
											protected void doOnSuccess(
													ItemInformation result) {
												hero.items.put(slot.getSlot(),
														result);
											}

										});
									}
								}

								importHeroData(data.getParagonDexterity(),
										dialog);
							} else {
								dialog.taskCompleted();
							}
						}
					}
				});
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
				ApplicationPanel.showErrorDialog("Fetch Hero List First");
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
