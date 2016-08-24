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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;

import com.dawg6.gwt.client.widgets.SimpleCaptionPanel;
import com.dawg6.web.dhcalc.shared.calculator.Damage;
import com.dawg6.web.dhcalc.shared.calculator.DamageResult;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;

public class GraphPanel extends Composite {

	private Chart chart;
	private DamageResult log;
	private final CheckBox skills;
	private final FlexTable table;
	private final CheckBox target;
	private final CheckBox dps;
	private final CheckBox targetHp;

	public GraphPanel() {

		SimplePanel panel = new SimplePanel();
		this.initWidget(panel);

		table = new FlexTable();
		panel.setWidget(table);

		resetChart();

		SimpleCaptionPanel options = new SimpleCaptionPanel("Options");
		table.setWidget(1, 0, options);

		FlexTable table2 = new FlexTable();
		options.setContentWidget(table2);

		skills = new CheckBox("Show By Skill");
		skills.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				redraw();

			}
		});

		int row = 0;
		int column = 0;

		table2.setWidget(row, column++, skills);

		target = new CheckBox("Show By Target");
		table2.setWidget(row, column++, target);
		target.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				redraw();

			}
		});

		dps = new CheckBox("Show DPS");
		table2.setWidget(row, column++, dps);
		dps.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				redraw();

			}
		});

		targetHp = new CheckBox("Show Target HP %");
		table2.setWidget(row, column++, targetHp);
		targetHp.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				redraw();

			}
		});
	}

	private void resetChart() {
		chart = new Chart();
		chart.setType(Series.Type.LINE);
		chart.setChartTitleText("Damage Log");
		chart.setWidth("800px");
		chart.setHeight("600px");
		chart.getXAxis().setAxisTitleText("Time");
		chart.getXAxis().setMin(0);
		chart.getYAxis(0).setAxisTitleText("Damage");
		chart.getYAxis(0).setMin(0);
		chart.getYAxis(1).setAxisTitleText("HP Percent");
		chart.getYAxis(1).setMin(0);
		chart.getYAxis(1).setMax(100.0);
		chart.getYAxis(1).setOpposite(true);
		table.setWidget(0, 0, chart);

	}

	public void setLog(DamageResult log) {

		this.log = log;
		redraw();
	}

	protected void redraw() {

		if (log == null)
			return;

		resetChart();

		boolean showSkills = skills.getValue();
		boolean showTarget = target.getValue();
		boolean showDps = dps.getValue();
		boolean showTargetHp = targetHp.getValue();

		List<Series> series = new Vector<Series>();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		List<List<Point>> points = new Vector<List<Point>>();
		List<Double> prev = new Vector<Double>();
		Series dpsSeries = null;
		double totalDamage = 0.0;
		List<Point> dpsPoints = new Vector<Point>();
		double dpsPrev = -1;

		if (!showSkills && !showTarget) {
			Series s = chart.createSeries();
			s.setYAxis(0);
			s.setName("Damage Done");
			chart.addSeries(s);
			series.add(s);
			points.add(new Vector<Point>());
			prev.add(new Double(-1));
		}

		if (showDps) {
			dpsSeries = chart.createSeries();
			dpsSeries.setName("DPS");
			chart.addSeries(dpsSeries);
			dpsSeries.setYAxis(0);
		}

		for (Damage d : log.damages) {
			if (d.damage > 0) {

				if (showDps) {
					totalDamage += d.damage;
					double duration = d.time;
					
					if (duration == 0)
						duration = 1.0;
					
					if (d.time > dpsPrev) {
						dpsPoints.add(new Point(d.time, totalDamage
								/ duration));
					} else {
						int n = dpsPoints.size();
						Point p = dpsPoints.get(n);
						dpsPoints.set(n, new Point(p.getX(), totalDamage
								/ duration));
					}
				}

				if (showTargetHp) {
					String source = d.target.toString() + " HP (%)";
					Integer index = map.get(source);

					if (index == null) {
						index = series.size();
						map.put(source, index);
						Series s = chart.createSeries();
						s.setYAxis(1);
						s.setName(source);
						chart.addSeries(s);
						series.add(s);
						points.add(new Vector<Point>());
						prev.add(new Double(-1));
					}
					List<Point> pt = points.get(index);
					Double pp = prev.get(index);

					if (d.time > pp) {
						pt.add(new Point(d.time, d.targetHpPercent * 100.0));
						prev.set(index, d.time);
					} else {
						int n = pt.size() - 1;
						Point p = pt.get(n);
						pt.set(n, new Point(p.getX(), d.targetHpPercent * 100.0));
					}
				}

				Integer index = 0;

				if (showSkills || showTarget) {
					StringBuffer buf = new StringBuffer();

					if (showTarget)
						buf.append(d.target.toString());

					if (showSkills) {
						if (showTarget)
							buf.append("-");
						buf.append(d.source.getName());
					}

					String source = buf.toString();
					index = map.get(source);

					if (index == null) {
						index = series.size();
						map.put(source, index);
						Series s = chart.createSeries();
						s.setYAxis(0);
						s.setName(source);
						chart.addSeries(s);
						series.add(s);
						points.add(new Vector<Point>());
						prev.add(new Double(-1));
					}
				}

				List<Point> pt = points.get(index);
				Double pp = prev.get(index);

				if (d.time > pp) {
					pt.add(new Point(d.time, d.damage));
					prev.set(index, d.time);
				} else {
					int n = pt.size() - 1;
					Point p = pt.get(n);
					pt.set(n, new Point(p.getX(), p.getY().doubleValue()
							+ d.damage));
				}
			}
		}

		if (showDps)
			dpsSeries.setPoints(dpsPoints.toArray(new Point[0]));

		for (int i = 0; i < series.size(); i++) {
			series.get(i).setPoints(points.get(i).toArray(new Point[0]));
		}
	}

}
