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
import java.util.Vector;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.common.util.DefaultCallback;
import com.dawg6.web.dhcalc.shared.calculator.LeaderboardType;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Leaderboard;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Leaderboard.Data;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Leaderboard.Player;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Leaderboard.Row;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
import com.dawg6.web.dhcalc.shared.calculator.d3api.SeasonIndex;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class LeaderboardPanel extends ApplicationPanel {

	private final ListBox seasonList = new ListBox();
	private final ListBox leaderboard = new ListBox();
	private final MainPanel mainPanel;
	private final CheckBox hardcore = new CheckBox();
	private SeasonIndex seasons;
	private final FlexTable table;
	private final int leaderboardRow;
	private ListBox leaders;
	
	public LeaderboardPanel(MainPanel mainPanel) {

		this.mainPanel = mainPanel;

		table = new FlexTable();
		initWidget(table);

		int row = 0;
		table.setWidget(row, 0, new Label("Season/Era:", false));
		table.setWidget(row, 1, seasonList);

		table.setWidget(row, 2, new Label("Hardcore:", false));
		table.setWidget(row, 3, hardcore);
		
		table.setWidget(row, 4, new Label("Leaderboard:", false));
		table.setWidget(row, 5, leaderboard);
		row++;

		Button button = new Button("Get Leaderboard");
		button.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				getLeaderboard();
				
			}});
		
		table.setWidget(row, 0, button);

		Anchor anchor = new Anchor("battle.net");
		anchor.setHref("javascript:void(0)");
		anchor.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				leaderboardLink();
			}});

		table.setWidget(row, 1, anchor);
		
		row++;
		
		for (LeaderboardType t : LeaderboardType.values()) {
			leaderboard.addItem(t.toString(), t.name());
		}
		
		leaderboard.setSelectedIndex(0);
		leaderboardRow = row;

		
	}

	protected void leaderboardLink() {
		Realm realm = mainPanel.getSelectedRealm();
		Integer season = getSelectedSeason();
		LeaderboardType type = getSelectedLeaderboard();
		
		if ((realm != null) && (season != null) && (type != null)) {
			boolean hardcore = this.hardcore.getValue();
			String which = type.getKey(hardcore);
			String server = realm.getHost();
			String url = server + "/d3/en/rankings/" + ((season < 0)?"era":"season") + "/" + Math.abs(season) + "/" + which;
			Window.open(url, "_blank", "");
		}
		
	}

	protected void getLeaderboard() {
		
		LeaderboardType type = getSelectedLeaderboard();
		boolean hardcore = this.hardcore.getValue();
		
		if (type != null) {
			String which = type.getKey(hardcore);

			Integer season = getSelectedSeason();
			
			if (season != null) {
				Service.getInstance().getLeaderboard(mainPanel.getSelectedRealm(), Math.abs(season), season < 0, which, new DefaultCallback<Leaderboard>(){
	
					@Override
					protected void doOnSuccess(Leaderboard result) {
						populateLeaders(result);
						
					}});
			}
		}
		
	}

	protected void populateLeaders(Leaderboard result) {
		leaders = new ListBox();
		
		for (Row row : result.rows) {
			Long rift = null;
			Long time = null;
			Long rank = null;
			
			for (Data d : row.data) {
				if (d.id.equals("Rank")) {
					rank = d.number;
				} else if (d.id.equals("RiftLevel")) {
					rift = d.number;
				} else if (d.id.equals("RiftTime")) {
					time = d.timestamp;
				}
				
				if ((rift != null) && (time != null) && (rank != null)) {
					break;
				}
			}
			
			if ((rift != null) && (time != null) && (rank != null)) {
			
				for (Player pl : row.players) {
					String btag = null;
					Long heroId = null;
					boolean isDh = false;
					
					for (Data d : pl.data) {
						if (d.id.equals("HeroClass")) {
							if (d.string.equals("demon hunter")) {
								isDh = true;
							} else {
								break;
							}
						} else if (d.id.equals("HeroBattleTag")) {
							btag = d.string;
						} else if (d.id.equals("HeroId")) {
							heroId = d.number;
						}
						
						if (isDh && (btag != null) && (heroId != null))
							break;
					}
					
					if (isDh && (btag != null) && (heroId != null)) {
						long minutes = time / 60000L;
						long seconds = (time - (minutes * 60000))/1000L;
						String ts = NumberFormat.getFormat("00").format(minutes) + ":" + NumberFormat.getFormat("00").format(seconds);
						String label = rank + ") Level " + rift + ": " + ts + " - " + btag;
						leaders.addItem(label, btag + "#" + heroId);
					}
				}
			}
		}
		
		this.table.setWidget(this.leaderboardRow, 0, leaders);
		this.table.getFlexCellFormatter().setColSpan(this.leaderboardRow, 0, 5);
		
		Button button = new Button("Import Profile");
		this.table.setWidget(this.leaderboardRow, 1, button);
		
		button.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				getHeroList();
			}});
	}

	protected void getHeroList() {
		String btag = getSelectedAccount();
		
		if (btag != null) {
			String[] split = btag.split("#");
			mainPanel.importProfile(split[0], split[1], Integer.parseInt(split[2]));
		}
	}

	private String getSelectedAccount() {
		
		int i = leaders.getSelectedIndex();
		
		if (i < 0)
			return null;
		
		return leaders.getValue(i);
	}

	private Integer getSelectedSeason() {
		int i = this.seasonList.getSelectedIndex();
		
		if (i < 0)
			return null;
		
		String value = seasonList.getValue(i);
		
		return Integer.parseInt(value);
	}

	private LeaderboardType getSelectedLeaderboard() {
		
		int i = this.leaderboard.getSelectedIndex();
		
		if (i < 0)
			return null;

		String value = leaderboard.getValue(i);
		
		return LeaderboardType.valueOf(value);
	}

	@Override
	public void onLoad() {
		if (this.seasons == null) {
			Realm realm = mainPanel.getSelectedRealm();
			
			Service.getInstance().getSeasonEraIndex(realm,
					new DefaultCallback<SeasonIndex>() {
	
						@Override
						protected void doOnSuccess(SeasonIndex result) {
							populateSeasonIndex(result);
						}
	
					});
		}
	}

	protected void populateSeasonIndex(SeasonIndex seasons) {
		this.seasons = seasons;
		seasonList.clear();

		List<SeasonHolder> list = new Vector<SeasonHolder>();

		for (int i = 1; i <= seasons.current_era; i++) {
			SeasonHolder s = new SeasonHolder();
			s.era = i;
			list.add(s);
		}

		for (int i = 1; i <= seasons.current_season; i++) {
			SeasonHolder s = new SeasonHolder();
			s.season = i;
			list.add(s);
		}
		
		for (SeasonHolder s : list) {
			seasonList.addItem(s.toString(), s.value());
		}
		
		seasonList.setSelectedIndex(list.size() -1);
	}
	
	private class SeasonHolder {
		public Integer season;
		public Integer era;
		
		@Override
		public String toString() {
			return (era != null) ? ("Era " + era) : ("Season " + season);
		}

		public String value() {
			return (era != null) ? String.valueOf(-era) : String.valueOf(season);
		}
	}
}
