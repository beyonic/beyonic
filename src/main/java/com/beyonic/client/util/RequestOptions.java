package com.beyonic.client.util;

import java.util.HashMap;
import java.util.Map;

public class RequestOptions {
	private Map<String,String> params;
	private Map<String,String> headers;

	public static RequestOptions getDefault() {
		return new RequestOptions();
	}

	public Map<String, String> getParams() {
		if(params == null)
			return new HashMap<String, String>();
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Map<String, String> getHeaders() {
		if(headers == null)
			return new HashMap<String, String>();
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

}
