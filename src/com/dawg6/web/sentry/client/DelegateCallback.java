package com.dawg6.web.sentry.client;

import com.dawg6.gwt.common.util.AsyncTaskHandler;
import com.dawg6.gwt.common.util.DefaultCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

class DelegateCallback<T> extends DefaultCallback<T> {

	
	public DelegateCallback(AsyncCallback<T> delegate) {
		super(delegate);
	}

	public DelegateCallback(AsyncTaskHandler handler,
			AsyncCallback<T> delegate) {
		super(handler, delegate);
	}

	@Override
	protected void doOnSuccess(T result) {
		// do nothing
	}
	
}