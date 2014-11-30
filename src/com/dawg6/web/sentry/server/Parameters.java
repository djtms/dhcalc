package com.dawg6.web.sentry.server;

import java.util.logging.Logger;

import com.dawg6.server.common.aws.dao.AWSParameters;
import com.dawg6.web.sentry.server.db.aws.Database;
import com.dawg6.web.sentry.server.util.ServerUtils;

public class Parameters extends AWSParameters {

	private static final Logger log = Logger
			.getLogger(Parameters.class.getName());

	public static final String MAX_REQUESTS = "max_requests";
	public static final String CACHE_SIZE = "cache_size";
	public static final String SCHEMA_NAME = "schema.name";
	public static final String SCHEMA_VERSION = "schema.version";
	private static final String APP_BASE_NAME = "sentry";
	private static final String DEV_SUFFIX = ".dev";

	private static Parameters instance = null;
	
	private static String appName = null;

	private static synchronized String getAppName() {
		if (appName == null) {
			appName = APP_BASE_NAME;

			if (!ServerUtils.isProductionMode())
				appName += DEV_SUFFIX;

			log.info("APP_NAME = " + appName);
		}

		return appName;
	}

	public static synchronized Parameters getInstance() {
		if (instance == null) {
			instance = new Parameters(getAppName(), Database.getInstance());
		}
		
		return instance;
	}
	
	protected Parameters(String appName, Database database) { 
		super(appName, database, CachePolicy.CacheEnabled);
	}
}
