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

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.client.ApplicationPanel.DialogBoxResultHandler;
import com.dawg6.gwt.common.util.AsyncTask;
import com.dawg6.gwt.common.util.AsyncTaskHandler;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.ExportData;
import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.dawg6.web.dhcalc.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Leaderboard;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
import com.dawg6.web.dhcalc.shared.calculator.d3api.SeasonIndex;
import com.dawg6.web.dhcalc.shared.calculator.stats.DBStatistics;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Service implements DHCalcServiceAsync {

	private static final Service instance = new Service();

	private final DHCalcServiceAsync SERVICE = GWT.create(DHCalcService.class);

	private Service() {
	}

	public static Service getInstance() {
		return instance;
	}

	public void execute(final AsyncTask task) {

		final AsyncTaskHandler dialog = ApplicationPanel.showWaitDialogBox(
				"Please wait...", null);

		versionCheck(new Handler<VersionCheck>() {

			@Override
			public void handleResult(VersionCheck result) {
				if (result.success) {
					task.run(dialog);
				} else {
					final DialogBoxResultHandler dialogHandler = new DialogBoxResultHandler() {

						@Override
						public void dialogBoxResult(int result) {
							if (result == ApplicationPanel.OK)
								forceReload();
						}
					};

					String message;
					String title;

					if (result.serverVersion != null) {
						title = "Server Version Mismatch";
						message = "New Version (" + result.serverVersion
								+ ") available on Server.";
					} else if (result.exception != null) {
						title = "Error";
						message = result.exception;
					} else {
						title = "Error";
						message = "Unknown error communicating with server.";
					}

					message += "\nPress OK to force browser refresh.";

					ApplicationPanel.showDialogBox(title, message,
							ApplicationPanel.OK | ApplicationPanel.CANCEL,
							dialogHandler);

					dialog.taskCompleted();
				}
			}
		});
	}

	@Override
	public void getProfile(final Realm realm, final String profile,
			final int tag, final AsyncCallback<CareerProfile> callback) {

		execute(new AsyncTask() {

			@Override
			public void run(final AsyncTaskHandler handler) {

				SERVICE.getProfile(realm, profile, tag,
						new DelegateCallback<CareerProfile>(handler, callback));
			}
		});

	}

	@Override
	public void getItem(final Realm realm, final String item,
			final AsyncCallback<ItemInformation> callback) {

		execute(new AsyncTask() {

			@Override
			public void run(final AsyncTaskHandler handler) {

				SERVICE.getItem(
						realm,
						item,
						new DelegateCallback<ItemInformation>(handler, callback));
			}
		});

	}

	@Override
	public void getHero(final Realm realm, final String profile, final int tag,
			final int id, final AsyncCallback<HeroProfile> callback) {

		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.getHero(realm, profile, tag, id,
						new DelegateCallback<HeroProfile>(handler, callback));
			}
		});

	}

	// @Override
	// public void serializeFormData(final FormData data,
	// final AsyncCallback<String> callback) {
	// execute(new AsyncTask() {
	//
	// @Override
	// public void run(AsyncTaskHandler handler) {
	// SERVICE.serializeFormData(data, new DelegateCallback<String>(
	// handler, callback));
	// }
	// });
	//
	// }

	@Override
	public void getClientData(final String client,
			final AsyncCallback<FormData> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.getClientData(client, new DelegateCallback<FormData>(
						handler, callback));
			}
		});
	}

	@Override
	public void exportData(final ExportData data,
			final AsyncCallback<String> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.exportData(data, new DelegateCallback<String>(handler,
						callback));
			}
		});
	}

	@Override
	public void getVersion(final AsyncCallback<Version> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.getVersion(new DelegateCallback<Version>(handler,
						callback));
			}
		});
	}

	// @Override
	// public void toJson(final JsonObject object,
	// final AsyncCallback<String> callback) {
	// execute(new AsyncTask() {
	//
	// @Override
	// public void run(AsyncTaskHandler handler) {
	// SERVICE.toJson(object, new DelegateCallback<String>(handler,
	// callback));
	// }
	// });
	// }
	//
	// @Override
	// public void fromJson(final String json, final String type,
	// final AsyncCallback<JsonObject> callback) {
	// execute(new AsyncTask() {
	//
	// @Override
	// public void run(AsyncTaskHandler handler) {
	// SERVICE.fromJson(json, type, new DelegateCallback<JsonObject>(
	// handler, callback));
	// }
	// });
	// }

	private static class VersionCheck {
		public boolean success;
		public String serverVersion;
		public String exception;
	}

	private interface Handler<T> {
		void handleResult(T result);
	}

	public void httpRequest(String url, final AsyncCallback<String> handler) {

		try {

			JsonpRequestBuilder builder = new JsonpRequestBuilder();

			builder.requestObject(url, new AsyncCallback<JavaScriptObject>() {

				@Override
				public void onFailure(Throwable caught) {
					handler.onFailure(caught);
				}

				@Override
				public void onSuccess(JavaScriptObject result) {
					JSONObject json = new JSONObject(result);
					handler.onSuccess(json.toString());
				}
			});

		} catch (RuntimeException e) {
			handler.onFailure(e);
		} catch (Exception e) {
			handler.onFailure(e);
		}
	}

	private void versionCheck(final Handler<VersionCheck> handler) {
		Scheduler.get().scheduleDeferred(new Command() {

			@Override
			public void execute() {
				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
						GWT.getHostPageBaseURL() + "version?v2");

				final VersionCheck result = new VersionCheck();

				try {
					builder.sendRequest(null, new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {

							String text = response.getText();

							if (text != null) {
								if (text.startsWith(Version.PREFIX)) {

									result.serverVersion = text
											.substring(Version.PREFIX.length());

									if (text.startsWith(Version.getVersion()
											.getPrefixString())) {
										result.success = true;
									}

								}
							}

							if ((!result.success)
									&& (result.serverVersion == null)) {
								result.exception = "Unable to obtain Server version.";
							}

							handler.handleResult(result);
						}

						@Override
						public void onError(Request request, Throwable exception) {
							result.success = false;
							result.exception = "Error communicating with server.";
							handler.handleResult(result);
						}
					});

				} catch (Exception e) {
					result.success = false;
					result.exception = "Error communicating with server.";
					handler.handleResult(result);
				}
			}
		});

	}

	public static native void forceReload() /*-{
		$wnd.location.reload(true);
	}-*/;

	@Override
	public void logData(final CharacterData data,
			final AsyncCallback<Void> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.logData(data, new DelegateCallback<Void>(handler,
						callback));
			}
		});
	}

	@Override
	public void getStats(final Rune sentryRune, final ActiveSkill[] skills,
			final Rune[] runes, final AsyncCallback<DBStatistics> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.getStats(sentryRune, skills, runes,
						new DelegateCallback<DBStatistics>(handler, callback));
			}
		});
	}

	@Override
	public void getSeasonEraIndex(final Realm realm, final AsyncCallback<SeasonIndex> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.getSeasonEraIndex(realm, new DelegateCallback<SeasonIndex>(
						handler, callback));
			}
		});
	}

	@Override
	public void getLeaderboard(final Realm realm, final int seasonEra, final boolean isEra,
			final String which, final AsyncCallback<Leaderboard> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.getLeaderboard(realm, seasonEra, isEra, which, new DelegateCallback<Leaderboard>(
						handler, callback));
			}
		});
	}
}
