package com.beyonic.client.Webhook;

import java.util.List;
import java.util.Map;

import com.beyonic.client.model.Webhook;

public class WebhookClientImpl implements WebhookClient {

	private static final String CLIENT_API_VERSION = "Client.apiVersion";

	private static final String CLIENT_API_KEY = "Client.apiKey";

	private static final String WEBHOOK_API_ENDPOINT = "staging.beyonic.com/api";
	
	private static String version = null;
	private static String appKey;

	public WebhookClientImpl() {
		
		String key = System.getProperty(CLIENT_API_KEY);
		String versionKey = System.getProperty(CLIENT_API_VERSION);
		if(key != null && !"".equals(key)){
			this.appKey = key;
		}
		if(versionKey != null && !"".equals(versionKey)){
			this.version = versionKey;
		}
		
	}	
	public WebhookClientImpl(String appKey) {
				this.appKey = appKey;
	}
	
	public WebhookClientImpl(String appKey, String version) {
				this.appKey = appKey;
				this.version = version;
	}

	@Override
	public void setKey(String expectedKey) {
		this.appKey = expectedKey;
		
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
		
	}

	@Override
	public Webhook craete(Map<String, Object> values) {
		

		return null;
	}

	@Override
	public Webhook read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Webhook delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Webhook update(Map<String, Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Webhook> list() {
		// TODO Auto-generated method stub
		return null;
	}


}
