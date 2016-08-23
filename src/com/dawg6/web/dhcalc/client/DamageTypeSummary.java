package com.dawg6.web.dhcalc.client;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.dawg6.gwt.client.widgets.SortableTable;
import com.dawg6.gwt.client.widgets.SortableTable.Column;
import com.dawg6.web.dhcalc.shared.calculator.DamageHolder;
import com.dawg6.web.dhcalc.shared.calculator.DamageType;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class DamageTypeSummary extends Composite {

	private SortableTable<Holder> summary;

	private class Holder {
		public DamageType type;
		public int attacks;
		public double perAttack;
		public double total;
		public double dps;
		public double pctTotal;
	}

	public DamageTypeSummary() {
		CaptionPanel captionPanelTypeSummary = new CaptionPanel("Damage Type Summary");
		initWidget(captionPanelTypeSummary);

		List<Column<Holder>> list = new Vector<Column<Holder>>();

		list.add(new Column<Holder>("Type", "dpsCol", new Comparator<Holder>(){

			@Override
			public int compare(Holder o1, Holder o2) {
				return o1.type.toString().compareToIgnoreCase(o2.type.toString());
			}}));
		list.add(new Column<Holder>("# Attacks", "dpsCol", new Comparator<Holder>(){

			@Override
			public int compare(Holder o1, Holder o2) {
				return Integer.compare(o1.attacks, o2.attacks);
			}}));
		list.add(new Column<Holder>("Per Attack", "dpsCol", new Comparator<Holder>(){

			@Override
			public int compare(Holder o1, Holder o2) {
				return Double.compare(o1.perAttack, o2.perAttack);
			}}));
		list.add(new Column<Holder>("Total", "dpsCol", new Comparator<Holder>(){

			@Override
			public int compare(Holder o1, Holder o2) {
				return Double.compare(o1.total, o2.total);
			}}));
		list.add(new Column<Holder>("DPS", "dpsCol", new Comparator<Holder>(){

			@Override
			public int compare(Holder o1, Holder o2) {
				return Double.compare(o1.dps, o2.dps);
			}}));
		list.add(new Column<Holder>("% of Total", "dpsCol", new Comparator<Holder>(){

			@Override
			public int compare(Holder o1, Holder o2) {
				return Double.compare(o1.pctTotal, o2.pctTotal);
			}}));

		summary = new SortableTable<Holder>(new SortableTable.DefaultRenderer<Holder>(list) {

			@Override
			public void renderRow(SortableTable<Holder> table, int row,
					Holder item) {

				if ((row % 2) == 0)
					table.getRowFormatter().addStyleName(row, "evenRow");
				else
					table.getRowFormatter().addStyleName(row, "oddRow");

				table.setWidget(row, 0, new Label(item.type.toString(), false));

				Label label1 = new Label(String.valueOf(item.attacks), false);
				label1.addStyleName("dpsCol");
				table.setWidget(row, 1, label1);

				Label label2 = new Label(Util.format(item.perAttack), false);
				label2.addStyleName("dpsCol");
				table.setWidget(row, 2, label2);

				Label damageLabel = new Label(Util.format(item.total), false);
				damageLabel.addStyleName("dpsCol");
				table.setWidget(row, 3, damageLabel);

				Label dpsLabel = new Label(Util.format(item.dps), false);
				dpsLabel.addStyleName("dpsCol");
				table.setWidget(row, 4, dpsLabel);

				Label pctLabel = new Label(String.valueOf(item.pctTotal) + "%", false);
				pctLabel.addStyleName("dpsCol");
				table.setWidget(row, 5, pctLabel);
				row++;			
			}

			@Override
			public void renderHeader(SortableTable<Holder> table) {
				super.renderHeader(table);

				table.getRowFormatter().addStyleName(0, "headerRow");
			}

		});
		
		summary.setCellPadding(5);
		summary.setBorderWidth(1);
		summary.setStyleName("outputTable");

		captionPanelTypeSummary.setContentWidget(summary);
	}

	public void setData(Map<DamageType, DamageHolder> values, double total,
			double duration) {

		List<Holder> list = new Vector<Holder>(values.size());

		for (Map.Entry<DamageType, DamageHolder> e : values.entrySet()) {
			Holder h = new Holder();
			DamageHolder dh = e.getValue();
			h.type = e.getKey();
			h.attacks = dh.attacks;
			h.total = Math.round(dh.damage);

			if (h.attacks > 0) {
				h.perAttack = Math.round(h.total / h.attacks);
			} else {
				h.perAttack = 0.0;
			}
			
			if (total > 0) {
				h.pctTotal = Math.round((h.total / total) * 10000.0) / 100.0;
				h.dps = Math.round(h.total / duration);
			} else {
				h.pctTotal = 0.0;
				h.dps = 0.0;
			}
			
			list.add(h);
		}

		summary.setData(list);
		summary.fillTable();
	}

}
