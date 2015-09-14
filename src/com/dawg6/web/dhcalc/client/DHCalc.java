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

import com.dawg6.gwt.common.util.AsyncTask;
import com.dawg6.gwt.common.util.AsyncTaskHandler;
import com.dawg6.web.dhcalc.shared.calculator.ILogger;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DHCalc implements EntryPoint {

	@Override
	public void onModuleLoad() {

		Util.getInstance().setFormatter(new Util.Formatter() {

			@Override
			public String format(double d) {
				return NumberFormat.getFormat("#,###.####").format(d);
			}
		});

		Util.getInstance().setLogger(new ILogger() {

			@Override
			public void log(String message) {
				GWT.log(message);
			}

			@Override
			public void log(String message, Throwable t) {
				GWT.log(message, t);
			}});

		Window.setTitle("DH Damage Calculator ("
				+ Version.getShortVersionString() + ")");

		Service.getInstance().execute(new AsyncTask() {

			@Override
			public void run(final AsyncTaskHandler handler) {

				Scheduler.get().scheduleDeferred(new Command() {

					@Override
					public void execute() {
						RootPanel.get("main").add(new MainPanel());
						handler.taskCompleted();
					}
				});

			}
		});

	}
}
