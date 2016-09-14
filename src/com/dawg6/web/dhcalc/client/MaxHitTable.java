package com.dawg6.web.dhcalc.client;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.dawg6.gwt.client.widgets.SimpleCaptionPanel;
import com.dawg6.gwt.client.widgets.SortableTable;
import com.dawg6.gwt.client.widgets.SortableTable.Column;
import com.dawg6.web.dhcalc.shared.calculator.MaxHit;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;

public class MaxHitTable extends SimpleCaptionPanel {

	private SortableTable<MaxHit> table;

	public MaxHitTable() {
		super("Maximum Hit Values");
		
		List<Column<MaxHit>> list = new Vector<Column<MaxHit>>();

		list.add(new Column<MaxHit>("Shooter", "dpsCol", new Comparator<MaxHit>(){

			@Override
			public int compare(MaxHit o1, MaxHit o2) {
				return o1.shooter.compareToIgnoreCase(o2.shooter);
			}}));

		list.add(new Column<MaxHit>("Skill", "dpsCol", new Comparator<MaxHit>(){

			@Override
			public int compare(MaxHit o1, MaxHit o2) {
				return o1.source.skill.getLongName().compareToIgnoreCase(o2.source.skill.getLongName());
			}}));
		list.add(new Column<MaxHit>("Rune", "dpsCol", new Comparator<MaxHit>(){

			@Override
			public int compare(MaxHit o1, MaxHit o2) {
				return o1.source.rune.getLongName().compareToIgnoreCase(o2.source.rune.getLongName());
			}}));
		list.add(new Column<MaxHit>("Type", "dpsCol", new Comparator<MaxHit>(){

			@Override
			public int compare(MaxHit o1, MaxHit o2) {
				return o1.type.getLongName().compareToIgnoreCase(o2.type.getLongName());
			}}));
		list.add(new Column<MaxHit>("No Crit", "dpsCol", new Comparator<MaxHit>(){

			@Override
			public int compare(MaxHit o1, MaxHit o2) {
				return Double.compare(o1.noCrit, o2.noCrit);
			}}));
		list.add(new Column<MaxHit>("Max Crit", "dpsCol", new Comparator<MaxHit>(){

			@Override
			public int compare(MaxHit o1, MaxHit o2) {
				return Double.compare(o1.maxCrit, o2.maxCrit);
			}}));
		list.add(new Column<MaxHit>("Notes", "dpsCol", new Comparator<MaxHit>(){

			@Override
			public int compare(MaxHit o1, MaxHit o2) {
				return o1.notes.compareToIgnoreCase(o2.notes);
			}}));
		list.add(new Column<MaxHit>("Calculations", "notes", null));

		table = new SortableTable<MaxHit>(new SortableTable.DefaultRenderer<MaxHit>(list) {

			@Override
			public void renderRow(SortableTable<MaxHit> table, int row,
					MaxHit item) {

				if ((row % 2) == 0)
					table.getRowFormatter().addStyleName(row, "evenRow");
				else
					table.getRowFormatter().addStyleName(row, "oddRow");

				int col = 0;
				
				Label label = new Label(item.shooter, false);
				label.addStyleName("notes");
				table.setWidget(row, col++, label);

				Anchor anchor = new Anchor();
				anchor.setTarget("_blank");
				anchor.addStyleName("notes");
				anchor.setHref(item.source.skill.getUrl());
				anchor.setText(item.source.skill.getLongName());
				anchor.setWordWrap(false);
				table.setWidget(row, col++, anchor);

				Anchor anchor2 = new Anchor();
				anchor2.setTarget("_blank");
				anchor2.addStyleName("notes");
				anchor2.setHref(item.source.skill.getUrl(item.source.rune));
				anchor2.setText(item.source.rune.getLongName());
				anchor2.setWordWrap(false);
				table.setWidget(row, col++, anchor2);

				Label label1 = new Label(String.valueOf(item.type.getLongName()), false);
				label1.addStyleName("notes");
				table.setWidget(row, col++, label1);

				Label label2 = new Label(Util.format(item.noCrit), false);
				label2.addStyleName("dpsCol");
				table.setWidget(row, col++, label2);

				Label label3 = new Label(Util.format(item.maxCrit), false);
				label3.addStyleName("dpsCol");
				table.setWidget(row, col++, label3);

				Label label4 = new Label(item.notes, false);
				label4.addStyleName("notes");
				table.setWidget(row, col++, label4);

				Label label5 = new Label(item.log, false);
				label5.addStyleName("notes");
				table.setWidget(row, col++, label5);
				
				row++;			
			}

			@Override
			public void renderHeader(SortableTable<MaxHit> table) {
				super.renderHeader(table);

				table.getRowFormatter().addStyleName(0, "headerRow");
			}

		});
		
		table.setCellPadding(5);
		table.setBorderWidth(1);
		table.setStyleName("outputTable");

		this.setContentWidget(table);
	}

	public void setData(List<MaxHit> list) {

		table.setData(list);
		table.fillTable();
	}

}
