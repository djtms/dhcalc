package com.dawg6.web.sentry.shared.calculator.d3api;


public class UrlHelper {

	public static String careerProfileUrl(String server, String name, int code) {
		return server + "/d3/profile/" + name + "-" + code + "/?locale=" + LOCALE;
	}

	public static String heroProfileUrl(String server, String name, int code,
			int id) {
		return server + "/d3/profile/" + name + "-" + code + "/hero/" + id + "?locale=" + LOCALE;
	}

	public static String itemInformationUrl(String server, String tooltipParams) {
		return server + "/d3/data/" + tooltipParams + "?locale=" + LOCALE;
	}
	
	public static String LOCALE = "en_US";
	
}
