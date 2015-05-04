package com.dawg6.web.dhcalc.client;

import com.dawg6.gwt.common.util.AsyncTask;
import com.dawg6.gwt.common.util.AsyncTaskHandler;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.google.gwt.core.client.EntryPoint;
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
