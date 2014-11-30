package com.dawg6.web.sentry.client;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.client.ApplicationPanel.DialogBoxResultHandler;
import com.dawg6.gwt.common.util.AsyncTask;
import com.dawg6.gwt.common.util.AsyncTaskHandler;
import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.ExportData;
import com.dawg6.web.sentry.shared.calculator.FormData;
import com.dawg6.web.sentry.shared.calculator.JsonObject;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.Version;
import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.dawg6.web.sentry.shared.calculator.stats.DBStatistics;
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

public class Service implements SentryServiceAsync {

	private static final Service instance = new Service();

	private final SentryServiceAsync SERVICE = GWT.create(SentryService.class);

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

				// String url = UrlHelper.careerProfileUrl(realm.getHost(),
				// profile, tag);
				//
				// httpRequest(url, new AsyncCallback<String>(){
				//
				// @Override
				// public void onSuccess(String result) {
				//
				// SERVICE.fromJson(result, CareerProfile.class.getName(), new
				// DefaultCallback<JsonObject>(handler){
				//
				// @Override
				// protected void doOnSuccess(JsonObject result) {
				// CareerProfile profile = (CareerProfile)result;
				// callback.onSuccess(profile);
				// }});
				// }
				//
				// @Override
				// public void onFailure(Throwable caught) {
				// callback.onFailure(caught);
				// handler.taskCompleted();
				// }});

				SERVICE.getProfile(realm, profile, tag,
						new DelegateCallback<CareerProfile>(handler, callback));
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

	@Override
	public void serializeFormData(final FormData data,
			final AsyncCallback<String> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.serializeFormData(data, new DelegateCallback<String>(
						handler, callback));
			}
		});

	}

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

	@Override
	public void toJson(final JsonObject object,
			final AsyncCallback<String> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.toJson(object, new DelegateCallback<String>(handler,
						callback));
			}
		});
	}

	@Override
	public void fromJson(final String json, final String type,
			final AsyncCallback<JsonObject> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.fromJson(json, type, new DelegateCallback<JsonObject>(
						handler, callback));
			}
		});
	}

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
						GWT.getHostPageBaseURL() + "version");

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

	public static native String makeRequest(String url) /*-{
														
														}-*/;

	@Override
	public void logData(final CharacterData data, final AsyncCallback<Void> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.logData(data,
						new DelegateCallback<Void>(handler, callback));
			}
		});
	}

	@Override
	public void getStats(final Rune sentryRune, final ActiveSkill[] skills, final Rune[] runes,
			final AsyncCallback<DBStatistics> callback) {
		execute(new AsyncTask() {

			@Override
			public void run(AsyncTaskHandler handler) {
				SERVICE.getStats(sentryRune, skills, runes,
						new DelegateCallback<DBStatistics>(handler, callback));
			}
		});
	}
}
