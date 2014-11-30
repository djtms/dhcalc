package com.dawg6.web.sentry.client;

import java.util.List;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.BreakPoint;
import com.dawg6.web.sentry.shared.calculator.FiringData;
import com.dawg6.web.sentry.shared.calculator.SkillAction;
import com.dawg6.web.sentry.shared.calculator.SkillSet;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;

public class SpenderData extends ApplicationPanel {
	public SpenderData() {
		this(1);
	}
	
	
	public SpenderData(int num) {
		
		FlexTable table = new FlexTable();
		table.setStyleName("breakpointTable");
		table.setCellPadding(5);
		table.setBorderWidth(1);
		initWidget(table);
		table.setSize("", "");
		
		FlexCellFormatter cf = table.getFlexCellFormatter();

		cf.setRowSpan(0, 0, 3);
		cf.setRowSpan(0, 1, 3);

		for (int i = 0; i < 3; i++)
			table.getRowFormatter().addStyleName(i, "headerRow");
		
		addHeader(table, 0, 0, "BP");
		addHeader(table, 0, 1, "# Bolts");
		addHeader(table, 0, 2, FiringData.DURATION +" seconds with " + num + " spender"
				+ (num > 1 ? "s" : ""));

		List<SkillSet> skills = ActiveSkill.getCombinations(num);

		for (BreakPoint bp : BreakPoint.ALL) {
			int row = 2 + bp.getBp();

			if ((row % 2) == 0)
				table.getRowFormatter().addStyleName(row, "evenRow");
			else
				table.getRowFormatter().addStyleName(row, "oddRow");
				
			addData(table, row, 0, bp.getBp());
			addData(table, row, 1, bp.getQty());

			int col = 2;
			int hcol = 0;
			int h2col = 0;

			for (SkillSet sk : skills) {
				FiringData data = FiringData.find(sk, bp);
				SkillAction[] actions = data.getActions();

				if (bp.getBp() == 1) {
					cf.setColSpan(1, hcol, actions.length);
					addHeader(table, 1, hcol++, sk.toShortString());

					if (actions.length > 1) {
						for (SkillAction a : actions) {
							addSkillHeader(table, 2, h2col++, a.getSkill());
						}
					} else {
						cf.setRowSpan(1, hcol - 1, 2);
					}
				}

				for (SkillAction a : actions) {
					addData(table, row, col++, a.getQty());
				}
			}

			if (bp.getBp() == 1) {
				cf.setColSpan(0, 2, col - 2);
			}
		}
	}


	private void addData(FlexTable table, int row, int column, int value) {
		addData(table, row, column, String.valueOf(value));
	}

	private void addData(FlexTable table, int row, int column, String text) {
		Label label = new Label(text);
		label.setWordWrap(false);
		table.setWidget(row, column, label);
	}

	private void addHeader(FlexTable table, int row, int column, String text) {
		Label label = new Label(text);
		label.setWordWrap(false);
		table.setWidget(row, column, label);
	}

	private void addSkillHeader(FlexTable table, int row, int column, ActiveSkill skill) {
		Anchor label = new Anchor(skill.getShortName());
		label.setTarget("_blank");
		label.setHref(skill.getUrl());
		label.setWordWrap(false);
		table.setWidget(row, column, label);
	}
}
