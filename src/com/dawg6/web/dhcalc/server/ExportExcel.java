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
package com.dawg6.web.dhcalc.server;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;

import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.AttributeData;
import com.dawg6.web.dhcalc.shared.calculator.Damage;
import com.dawg6.web.dhcalc.shared.calculator.DamageHolder;
import com.dawg6.web.dhcalc.shared.calculator.DamageSource;
import com.dawg6.web.dhcalc.shared.calculator.DamageType;
import com.dawg6.web.dhcalc.shared.calculator.ExportData;
import com.dawg6.web.dhcalc.shared.calculator.GemAttributeData;
import com.dawg6.web.dhcalc.shared.calculator.GemSkill;
import com.dawg6.web.dhcalc.shared.calculator.ItemHolder;
import com.dawg6.web.dhcalc.shared.calculator.ItemSet;
import com.dawg6.web.dhcalc.shared.calculator.Passive;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.Slot;
import com.dawg6.web.dhcalc.shared.calculator.SpecialItemType;

public class ExportExcel {

	private final ExportData data;

	private final Map<String, Cell> inputCells = new Hashtable<String, Cell>();

	private Font boldFont;

	private CellStyle boldStyle;

	private HSSFWorkbook wb;

	private HSSFSheet inputs;

	private HSSFCellStyle doubleStyle;

	private HSSFDataFormat doubleFormat;

	private HSSFCellStyle intStyle;

	private HSSFDataFormat intFormat;

	private HSSFCellStyle pctStyle;

	private HSSFDataFormat pctFormat;

	private HSSFCellStyle largeDoubleStyle;

	private HSSFDataFormat largeDoubleFormat;

	private HSSFSheet summary;

	private HSSFCellStyle timeStyle;

	private HSSFDataFormat timeFormat;

	public ExportExcel(ExportData data) {
		this.data = data;
	}

	public byte[] export() throws Exception {
		wb = new HSSFWorkbook();

		boldFont = wb.createFont();
		boldFont.setFontHeightInPoints((short) 10);
		boldFont.setFontName("Arial");
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldStyle = wb.createCellStyle();
		boldStyle.setFont(boldFont);

		doubleStyle = wb.createCellStyle();
		doubleFormat = wb.createDataFormat();
		doubleStyle.setDataFormat(doubleFormat.getFormat("#,##0.0###"));

		timeStyle = wb.createCellStyle();
		timeFormat = wb.createDataFormat();
		timeStyle.setDataFormat(timeFormat.getFormat("0.00"));

		largeDoubleStyle = wb.createCellStyle();
		largeDoubleFormat = wb.createDataFormat();
		largeDoubleStyle.setDataFormat(largeDoubleFormat.getFormat("#,###"));

		intStyle = wb.createCellStyle();
		intFormat = wb.createDataFormat();
		intStyle.setDataFormat(intFormat.getFormat("#,##0"));

		pctStyle = wb.createCellStyle();
		pctFormat = wb.createDataFormat();
		pctStyle.setDataFormat(pctFormat.getFormat("0.00%"));

		inputs = wb.createSheet("Inputs");
		summary = wb.createSheet("Summary");
		HSSFSheet damageLog = wb.createSheet("DamageLog");
		HSSFSheet typeSummary = wb.createSheet("DamageTypeSummary");
		HSSFSheet skillSummary = wb.createSheet("SkillSummary");
		HSSFSheet shooterSummary = wb.createSheet("ShooterSummary");

		addInputs();
		addSummary();
		addDamageLog(damageLog);
		addTypeSummary(typeSummary);
		addSkillSummary(skillSummary);
		addShooterSummary(shooterSummary);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		wb.write(stream);

		return stream.toByteArray();
	}

	private void addSummary() {
		createInputHeader(summary, "Summary of Damage Log");

		createInput(summary, data.totalDamage, "Total Damage over "
				+ data.output.duration + " seconds", largeDoubleStyle);

		summary.autoSizeColumn(0, true);
		summary.autoSizeColumn(1, true);
	}

	private void addSkillSummary(HSSFSheet skillSummary) {
		createTableHeader(skillSummary, 0, "Skill");
		createTableHeader(skillSummary, 1, "# Attacks");
		createTableHeader(skillSummary, 2, "Per Attack");
		createTableHeader(skillSummary, 3, "Total");
		createTableHeader(skillSummary, 4, "DPS");
		createTableHeader(skillSummary, 5, "% of Total");

		double total = 0;
		for (Damage d : data.output.damages) {
			total += d.actualDamage;
		}

		int i = 1;
		for (Map.Entry<DamageSource, DamageHolder> e : data.skillDamages
				.entrySet()) {
			Row row = skillSummary.createRow(i++);
			ActiveSkill skill = e.getKey().skill;
			GemSkill gem = e.getKey().gem;
			DamageHolder d = e.getValue();
			addTableCell(
					row,
					0,
					(skill != null) ? skill.getLongName() : gem
							.getDisplayName());
			addTableCell(row, 1, d.attacks);
			addTableCell(row, 2, Math.round((d.damage) / d.attacks));
			addTableCell(row, 3, d.damage);
			addTableCell(row, 4, Math.round((d.damage) / data.output.duration));
			addTableCell(row, 5,
					Math.round(10000.0 * d.damage / total) / 10000.0, pctStyle);

		}

		for (i = 0; i < 6; i++) {
			skillSummary.autoSizeColumn(i, true);
		}
	}

	private void addShooterSummary(HSSFSheet shooterSummary) {
		createTableHeader(shooterSummary, 0, "Shooter");
		createTableHeader(shooterSummary, 1, "# Attacks");
		createTableHeader(shooterSummary, 2, "Per Attack");
		createTableHeader(shooterSummary, 3, "Total");
		createTableHeader(shooterSummary, 4, "DPS");
		createTableHeader(shooterSummary, 5, "% of Total");

		double total = 0;
		for (Damage d : data.output.damages) {
			total += d.actualDamage;
		}

		int i = 1;
		for (Map.Entry<String, DamageHolder> e : data.shooterDamages.entrySet()) {
			Row row = shooterSummary.createRow(i++);
			DamageHolder d = e.getValue();
			addTableCell(row, 0, e.getKey());
			addTableCell(row, 1, d.attacks);
			addTableCell(row, 2, Math.round((d.damage) / d.attacks));
			addTableCell(row, 3, d.damage);
			addTableCell(row, 4, Math.round((d.damage) / data.output.duration));
			addTableCell(row, 5,
					Math.round(10000.0 * d.damage / total) / 10000.0, pctStyle);

		}

		for (i = 0; i < 6; i++) {
			shooterSummary.autoSizeColumn(i, true);
		}
	}

	private void addTypeSummary(HSSFSheet typeSummary) {
		createTableHeader(typeSummary, 0, "Type");
		createTableHeader(typeSummary, 1, "# Attacks");
		createTableHeader(typeSummary, 2, "Per Attack");
		createTableHeader(typeSummary, 3, "Total");
		createTableHeader(typeSummary, 4, "DPS");
		createTableHeader(typeSummary, 5, "% of Total");

		double total = 0;
		for (Damage d : data.output.damages) {
			total += d.actualDamage;
		}

		int i = 1;
		for (Map.Entry<DamageType, DamageHolder> e : data.types.entrySet()) {
			Row row = typeSummary.createRow(i++);
			DamageHolder d = e.getValue();
			addTableCell(row, 0, e.getKey().name());
			addTableCell(row, 1, d.attacks);
			addTableCell(row, 2, Math.round((d.damage) / d.attacks));
			addTableCell(row, 3, d.damage);
			addTableCell(row, 4, Math.round((d.damage) / data.output.duration));
			addTableCell(row, 5,
					Math.round(10000.0 * d.damage / total) / 10000.0, pctStyle);

		}

		for (i = 0; i < 6; i++) {
			typeSummary.autoSizeColumn(i, true);
		}
	}

	private void addDamageLog(HSSFSheet damageLog) {

		int col = 0;

		createTableHeader(damageLog, col++, "Time");
		createTableHeader(damageLog, col++, "Shooter");
		createTableHeader(damageLog, col++, "Skill");
		createTableHeader(damageLog, col++, "Rune");
		createTableHeader(damageLog, col++, "Type");
		createTableHeader(damageLog, col++, "+/- Hatred");
		createTableHeader(damageLog, col++, "Hatred");
		createTableHeader(damageLog, col++, "+/- Disc");
		createTableHeader(damageLog, col++, "Disc");
		createTableHeader(damageLog, col++, "Damage");
		createTableHeader(damageLog, col++, "Target HP");
		createTableHeader(damageLog, col++, "% HP");
		createTableHeader(damageLog, col++, "Target");
		createTableHeader(damageLog, col++, "Notes");
		createTableHeader(damageLog, col++, "Calculations");

		double total = 0;
		for (Damage d : data.output.damages) {
			total += d.actualDamage;
		}

		for (int i = 0; i < data.output.damages.length; i++) {
			Damage d = data.output.damages[i];
			Row row = damageLog.createRow(i + 1);
			col = 0;

			addTableCellD(row, col++, Math.round(d.time * 100.0) / 100.0);
			addTableCell(row, col++, d.shooter);

			if (d.source != null) {
				ActiveSkill skill = d.source.skill;
				GemSkill gem = d.source.gem;
				Rune rune = d.source.rune;

				addTableCell(row, col++, (skill != null) ? skill.getLongName()
						: gem.getDisplayName());
				addTableCell(row, col++, (rune != null) ? rune.getLongName()
						: "N/A");
			} else {
				col += 2;
			}

			if (d.type != null) {
				addTableCell(row, col++, d.type.name());
			} else {
				col++;
			}

			if (d.hatred != 0) {
				addTableCellD(row, col++, Math.round(d.hatred * 10.0) / 10.0);
			} else {
				col++;
			}
			addTableCellD(row, col++, Math.round(d.currentHatred * 10.0) / 10.0);
			
			if (d.disc != 0) {
				addTableCellD(row, col++, Math.round(d.disc * 10.0) / 10.0);
			} else {
				col++;
			}
			addTableCellD(row, col++, Math.round(d.currentDisc * 10.0) / 10.0);

			if (d.damage > 0) {
				addTableCell(row, col++, Math.round(d.damage));
				addTableCell(row, col++, (double)Math.round(d.targetHp));
				addTableCell(row, col++, Math.round(d.targetHpPercent * 1000.0) / 10.0 + "%");
			} else {
				col += 3;
			}

			if (d.target != null) {
				addTableCell(row, col++, d.target.toString());
			} else {
				col += 2;
			}

			if (d.note != null) {
				addTableCell(row, col++, d.note);
			} else {
				col++;
			}

			if (d.log != null) {
				addTableCell(row, col++, d.log);
			} else {
				col++;
			}
		}

		for (int i = 0; i < 12; i++) {
			damageLog.autoSizeColumn(i, true);
		}

	}

	private Cell addTableCell(Row row, int col, String label) {
		Cell cell = row.createCell(col);
		cell.setCellValue(label);
		CellUtil.setAlignment(cell, row.getSheet().getWorkbook(),
				CellStyle.ALIGN_LEFT);

		return cell;
	}

	@SuppressWarnings("unused")
	private Cell addTableCell(Row row, int col, Boolean value) {
		Cell cell = row.createCell(col);
		cell.setCellValue(String.valueOf(value));
		CellUtil.setAlignment(cell, row.getSheet().getWorkbook(),
				CellStyle.ALIGN_CENTER);

		return cell;
	}

	private Cell addTableCell(Row row, int col, int value) {
		Cell cell = row.createCell(col);
		cell.setCellValue(String.valueOf(value));
		cell.setCellStyle(intStyle);
		CellUtil.setAlignment(cell, row.getSheet().getWorkbook(),
				CellStyle.ALIGN_RIGHT);

		return cell;
	}

	private Cell addTableCellD(Row row, int col, double value) {
		return addTableCell(row, col, value, doubleStyle);
	}
	
	private Cell addTableCell(Row row, int col, double value) {
		return addTableCell(row, col, value, largeDoubleStyle);
	}

	private Cell addTableCell(Row row, int col, double value,
			HSSFCellStyle style) {
		Cell cell = row.createCell(col);
		cell.setCellValue(value);
		cell.setCellStyle(style);
		CellUtil.setAlignment(cell, row.getSheet().getWorkbook(),
				CellStyle.ALIGN_RIGHT);

		return cell;
	}

	private Cell createTableHeader(HSSFSheet sheet, int col, String label) {

		int n = sheet.getPhysicalNumberOfRows();
		Row row = null;

		if (n < 1)
			row = sheet.createRow(0);
		else
			row = sheet.getRow(0);

		Cell cell = row.createCell(col);
		cell.setCellValue(label);
		cell.setCellStyle(boldStyle);
		CellUtil.setAlignment(cell, sheet.getWorkbook(), CellStyle.ALIGN_CENTER);

		return cell;

	}

	private void addInputs() {

		createInputHeader(inputs, "Character Data");
		createInput(inputs, data.data.getCritChance(), "Crit Chance ", pctStyle);
		createInput(inputs, data.data.getCritHitDamage(), "Crit Hit Damage ",
				pctStyle);
		createInput(inputs, data.data.getWeaponDamage(), "Weapon Damage");
		createInput(inputs, data.data.getDexterity(), "Total Dexterity");
		createInput(inputs, data.data.getEquipmentDexterity(),
				"Equipment Dexterity");
		createInput(inputs, data.data.getLevel(), "Level");
		createInput(inputs, data.data.getCdr(), "Effective CDR", this.pctStyle);
		createInput(inputs, data.data.getRcr(), "Effective RCR", this.pctStyle);
		createInput(inputs, data.data.getWeaponType().getName(), "WeaponType");
		createInput(inputs, data.data.getAps(), "Player APS");
		createInput(inputs, data.data.getSentryAps(), "Sentry APS");
		createInput(inputs, 8.0 * (1 - data.data.getCdr()),
				"Sentry Cooldown (sec)", timeStyle);

		createInput(inputs, data.data.getTotalEliteDamage(),
				"Total Elite Damage", pctStyle);
		createInput(inputs, data.data.getMaxHatred(), "Max Hatred");
		createInput(inputs, data.data.getHatredPerSecond(), "Hatred Per Second");
		createInput(inputs, data.data.getEquipmentDiscipline(),
				"Equipment +Max Discipline");
		createInput(inputs, data.data.getParagonDexterity(),
				"Paragon Dexterity");
		createInput(inputs, data.data.getParagonIAS(), "Paragon IAS");
		createInput(inputs, data.data.getParagonCC(), "Paragon CC");
		createInput(inputs, data.data.getParagonCHD(), "Paragon CHD");
		createInput(inputs, data.data.getParagonCDR(), "Paragon CDR");
		createInput(inputs, data.data.getParagonHatred(), "Paragon Hatred");
		createInput(inputs, data.data.getParagonRCR(), "Paragon RCR");
		createInput(inputs, data.data.getParagonAD(), "Paragon Area Damage");

		createInputHeader(inputs, "Active Skills");

		for (Map.Entry<ActiveSkill, Rune> e : data.data.getSkills().entrySet()) {
			createInput(inputs, e.getValue().getLongName(), e.getKey()
					.getLongName());
		}

		createInput(inputs, data.data.getCaltropsUptime(), "Caltrops Uptime",
				pctStyle);
		createInput(inputs, data.data.getMfdUptime(),
				"Marked for Death Primary Target Uptime", pctStyle);
		createInput(inputs, data.data.getMfdAddUptime(),
				"Marked for Death Additional Targets Uptime", pctStyle);
		createInput(inputs, data.data.getNumSentries(), "# of Sentries");

		createInputHeader(inputs, "Passive Skills");

		for (Passive p : data.data.getPassives()) {
			createInput(inputs, Boolean.TRUE, p.getLongName());
		}

		createInputHeader(inputs, "Situational");
		createInput(inputs, data.data.getRiftLevel(), "Greater Rift Level");
		createInput(inputs, data.data.getNumPlayers(), "Number of Players");
		createInput(inputs, data.data.getPrimaryTargetType().toString(),
				"Primary Target Type");
		createInput(inputs, data.data.getPrimaryTargetHealth(),
				"Primary Target Health");
		createInput(inputs, data.data.getNumAdditional(),
				"# Additional Targets");
		createInput(inputs, data.data.getAdditionalTargetType().toString(),
				"Additional Target Type");
		createInput(inputs, data.data.getAdditionalTargetHealth(),
				"Additional Target Health");
		createInput(inputs, data.data.getPercentSlowedChilled(),
				"Percent Slowed/Chilled", pctStyle);
		createInput(inputs, data.data.getPercentControlled(),
				"percent Control Impaired", pctStyle);
		createInput(inputs, data.data.getPercentAtLeast10Yards(),
				"Percent of targets at least 10 Yards away", pctStyle);
		createInput(inputs, data.data.getDistanceToTarget(),
				"Distance to Target(s)(yards)");
		createInput(inputs, data.data.getTargetSpacing(),
				"Spacing between Targets(yards)");
		createInput(inputs, data.data.getTargetSize().getDisplayName(),
				"Target Size");
		createInput(inputs, data.data.getDelay(), "Player Action Delay");

		createInputHeader(inputs, "Elemental Damage Modifiers");
		for (Map.Entry<DamageType, Double> e : data.data.getElementalDamage()
				.entrySet()) {
			createInput(inputs, e.getValue(), e.getKey().getLongName(),
					pctStyle);
		}

		createInputHeader(inputs, "Skill Damage Modifiers");

		for (Map.Entry<ActiveSkill, Double> e : data.data.getSkillDamage()
				.entrySet()) {
			createInput(inputs, e.getValue(), e.getKey().getLongName(),
					pctStyle);
		}

		createInputHeader(inputs, "Item Data");
		createInput(inputs, data.data.getEliteDamage(),
				"Equipment Elite Damage (minus BotP passive)", pctStyle);
		createInput(inputs, data.data.getAreaDamageEquipment(),
				"Equipment Area Damage", pctStyle);
		
		for (Map.Entry<Slot, ItemHolder> e : data.data.getSpecialItems().entrySet()) {
			Slot slot = e.getKey();
			ItemHolder item = e.getValue();
			SpecialItemType type = item.getType();
			AttributeData ad = item.getAttributes();
			createInput(inputs, type.getName(), slot.getSlot());
			
			for (SpecialItemType.Attribute a : type.getAttributes()) {
				Integer i = ad.get(a.getLabel());
				
				if (i != null) {
					createInput(inputs, i, type.getName() + " " + a.getLabel());
				}
			}
		}

		createInputHeader(inputs, "Set Items");

		for (ItemSet set : ItemSet.values()) {
			Integer i = data.data.getSetCount(set.getSlug());
			
			if ((i != null) && (i > 0)) {
				createInput(inputs, i, set.getName());
			}
		}
		
		createInputHeader(inputs, "Legendary Gems");
		
		for (Map.Entry<GemSkill, GemAttributeData> e : data.data.getGems().entrySet()) {
			GemSkill gem = e.getKey();
			GemAttributeData gd = e.getValue();
			
			createInput(inputs, gd.level,
					gem.getDisplayName() + " Level");
			
			for (Map.Entry<String, Integer> a : gd.entrySet()) {
				createInput(inputs, a.getValue(),
						gem.getDisplayName() + " " + a.getKey());
			}
		}

		createInputHeader(inputs, "Follower Buffs");
		createInput(inputs, data.data.isFocusedMind(),
				"Enchantress Focused Mind");
		createInput(inputs, data.data.isAnatomy(), "Scoundrel Anatomy");
		createInput(inputs, data.data.isHysteria(), "Scoundrel Hysteria");
		createInput(inputs, data.data.isInspire(), "Templar Inspire");

		createInputHeader(inputs, "Player Buffs");
		createInput(inputs, data.data.isWolf(), "Wolf Companion");
		createInput(inputs, data.data.getWolfUptime(), "Wolf Companion Uptime",
				pctStyle);
		createInput(inputs, data.data.isBbv(), "Big Bad Voodoo");
		createInput(inputs, data.data.getBbvUptime(), "Big Bad Voodoo Uptime",
				pctStyle);
		createInput(inputs, data.data.isSlamDance(), "Slam Dance");
		createInput(inputs, data.data.isTimeWarp(), "Slow Time/Time Warp");
		createInput(inputs, data.data.getTimeWarpUptime(), "Slow Time/Time Warp Uptime",
				pctStyle);
		createInput(inputs, data.data.isStretchTime(), "Slow Time/Stretch Time");
		createInput(inputs, data.data.getStretchTimeUptime(), "Slow Time/Stretch Time Uptime",
				pctStyle);
		createInput(inputs, data.data.isMassConfusion(),
				"Mass Connfusion/Paranoia");
		createInput(inputs, data.data.getMassConfusionUptime(),
				"Mass Connfusion/Paranoia Uptime", pctStyle);
		createInput(inputs, data.data.isPiranhas(), "Piranhas");
		createInput(inputs, data.data.getPiranhasUptime(), "Piranhas Uptime",
				pctStyle);
		createInput(inputs, data.data.isInnerSanctuary(),
				"Inner Sanctuary/Forbidden Palace");
		createInput(inputs, data.data.getInnerSanctuaryUptime(),
				"Inner Sanctuary/Forbidden Palace Uptime", pctStyle);
		createInput(inputs, data.data.isCripplingWave(),
				"Crippling Wave/Breaking Wave");
		createInput(inputs, data.data.getCripplingWaveUptime(),
				"Crippling Wave/Breaking Wave Uptime", pctStyle);
		createInput(inputs, data.data.isValor(), "Laws of Valor");
		createInput(inputs, data.data.getValorActiveUptime(), "Laws of Valor Active Uptime",
				pctStyle);
		createInput(inputs, data.data.getValorPassiveUptime(), "Laws of Valor Passive Uptime",
				pctStyle);
		createInput(inputs, data.data.isRetribution(), "Mantra of Retribution");
		createInput(inputs, data.data.getRetributionUptime(),
				"Mantra of Retribution Uptime", pctStyle);
		createInput(inputs, data.data.isConviction(), "Conviction");
		createInput(inputs, data.data.isOverawe(), "Overawe");
		createInput(inputs, data.data.getConvictionPassiveUptime(),
				"Conviction Passive Uptime", pctStyle);
		createInput(inputs, data.data.getConvictionActiveUptime(),
				"Conviction Active Uptime", pctStyle);

		inputs.autoSizeColumn(0, true);
		inputs.autoSizeColumn(1, true);
	}

	private Cell createInputHeader(Sheet sheet, String label) {
		Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
		Cell cell1 = row.createCell(0);
		cell1.setCellValue(label);
		cell1.setCellStyle(boldStyle);

		return cell1;
	}

	private Cell createInput(Sheet sheet, double value, String label) {
		return createInput(sheet, value, label, doubleStyle);
	}

	private Cell createInput(Sheet sheet, double value, String label,
			HSSFCellStyle style) {
		Cell cell = createInputCell(sheet, label);
		cell.setCellValue(value);
		cell.setCellStyle(style);

		return cell;
	}

	private Cell createInput(Sheet sheet, boolean value, String label) {
		Cell cell = createInputCell(sheet, label);
		cell.setCellValue(value);

		return cell;
	}

	private Cell createInput(Sheet sheet, int value, String label) {
		Cell cell = createInputCell(sheet, label);
		cell.setCellValue(value);
		cell.setCellStyle(intStyle);

		return cell;
	}

	private Cell createInput(Sheet sheet, String text, String label) {
		Cell cell = createInputCell(sheet, label);
		cell.setCellValue(text);

		return cell;
	}

	private Cell createInputCell(Sheet sheet, String label) {
		Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
		Cell cell1 = row.createCell(0);
		cell1.setCellValue(label + ":");
		Cell cell2 = row.createCell(1);
		CellUtil.setAlignment(cell2, sheet.getWorkbook(), CellStyle.ALIGN_RIGHT);

		inputCells.put(label, cell2);

		return cell2;
	}
}
