package com.beyonic.client.Webhook;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beyonic.client.constants.Constants;
import com.beyonic.client.exception.APIConnectionException;
import com.beyonic.client.exception.AuthenticationException;
import com.beyonic.client.exception.InvalidRequestException;
import com.beyonic.client.model.Webhook;
import com.beyonic.client.util.ConnectionUtil;
import com.beyonic.client.util.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class WebhookClientImpl implements WebhookClient {

	private static final String CLIENT_API_VERSION = "Client.apiVersion";

	private static final String CLIENT_API_KEY = "Client.apiKey";

	private static final String WEBHOOK_API_ENDPOINT = "https://staging.beyonic.com/api/webhooks";
	
	private static String version = null;
	private static String appKey;
	public static final Gson GSON = new GsonBuilder().create();

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
	public Webhook craete(Map<String, String> values) throws AuthenticationException, InvalidRequestException, APIConnectionException {
		Map<String, String> headers= createHeaders(values);
		RequestOptions options = new RequestOptions();
		options.setHeaders(headers);
		options.setParams(values);
		String url = WEBHOOK_API_ENDPOINT+(version != null && !"".equals(version.trim())? "/"+version : "");
		
		String response = ConnectionUtil.request(ConnectionUtil.RequestMethod.POST, url, options);
		Webhook webhook = GSON.fromJson(response, Webhook.class);
		return webhook;
	}

	private Map<String, String> createHeaders(Map<String, String> values) {
		String appkey = values.get(Constants.CLIENT_API_KEY);
		if(appkey == null){
			appkey = WebhookClientImpl.appKey;
		}
		Map<String, String> headers = new HashMap<String, String>();
		if (appkey != null) {
			headers.put("Authorization", "Token " + appkey);
		}
		return headers;
	}
	
	@Override
	public Webhook read(String id) throws AuthenticationException, InvalidRequestException, APIConnectionException {
		String url = WEBHOOK_API_ENDPOINT+(version != null && !"".equals(version.trim())? "/"+version : "")+"/"+id;
		
		String response = ConnectionUtil.request(ConnectionUtil.RequestMethod.GET, url, RequestOptions.getDefault());
		Webhook webhook = GSON.fromJson(response, Webhook.class);
		return webhook;
	}

	@Override
	public void delete(String id) throws AuthenticationException, InvalidRequestException, APIConnectionException {
		String url = WEBHOOK_API_ENDPOINT+(version != null && !"".equals(version.trim())? "/"+version : "")+"/"+id;
		
		String response = ConnectionUtil.request(ConnectionUtil.RequestMethod.DELETE, url, RequestOptions.getDefault());
	}

	@Override
	public Webhook update(String id, Map<String, String> values) throws AuthenticationException, InvalidRequestException, APIConnectionException {

		Map<String, String> headers= createHeaders(values);
		RequestOptions options = new RequestOptions();
		options.setHeaders(headers);
		options.setParams(values);
		String url = WEBHOOK_API_ENDPOINT+(version != null && !"".equals(version.trim())? "/"+version : ""+"/"+id);
		
		String response = ConnectionUtil.request(ConnectionUtil.RequestMethod.PUT, url, options);
//		Webhook webhook = GSON.fromJson(response, Webhook.class);
//		return webhook;
		return null;
	}

	@Override
	public List<Webhook> list() throws AuthenticationException, InvalidRequestException, APIConnectionException {
		
		List<Webhook> hooks = new ArrayList<Webhook>();
		String url = WEBHOOK_API_ENDPOINT+(version != null && !"".equals(version.trim())? "/"+version : "");
		
		String response = ConnectionUtil.request(ConnectionUtil.RequestMethod.GET, url, RequestOptions.getDefault());
		Type collectionType = new TypeToken<ArrayList<Webhook>>(){}.getType();
		hooks = GSON.fromJson(response, collectionType);
		return hooks;
	}
}