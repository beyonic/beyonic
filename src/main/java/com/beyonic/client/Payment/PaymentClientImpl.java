package com.beyonic.client.Payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beyonic.client.constants.Constants;
import com.beyonic.client.exception.APIConnectionException;
import com.beyonic.client.exception.AuthenticationException;
import com.beyonic.client.exception.InvalidRequestException;
import com.beyonic.client.model.Payment;
import com.beyonic.client.util.ConnectionUtil;
import com.beyonic.client.util.RequestOptions;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PaymentClientImpl implements PaymentClient {

	private static final String CLIENT_API_VERSION = "Client.apiVersion";
	private static final String CLIENT_API_KEY = "Client.apiKey";
	private static final String PAYMENT_API_ENDPOINT = "https://staging.beyonic.com/api/payments";
	private static String version = null;
	private static String appKey;
	public static final Gson GSON = new GsonBuilder().create();

	public PaymentClientImpl() {
		
		String key = System.getProperty(CLIENT_API_KEY);
		String versionKey = System.getProperty(CLIENT_API_VERSION);
		if(key != null && !"".equals(key)){
			this.appKey = key;
		}
		if(versionKey != null && !"".equals(versionKey)){
			this.version = versionKey;
		}
		
	}	
	public PaymentClientImpl(String appKey) {
				this.appKey = appKey;
	}
	
	public PaymentClientImpl(String appKey, String version) {
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
	public Payment create(Map<String, String> values) throws AuthenticationException, InvalidRequestException, APIConnectionException {
		Map<String, String> headers= createHeaders(values);
		RequestOptions options = new RequestOptions();
		options.setHeaders(headers);
		options.setParams(values);
		String url = PAYMENT_API_ENDPOINT+(version != null && !"".equals(version.trim())? "/"+version : "");
		
		String response = ConnectionUtil.request(ConnectionUtil.RequestMethod.POST, url, options);
		Payment payment = GSON.fromJson(response, Payment.class);
		return payment;
	}
	private Map<String, String> createHeaders(Map<String, String> values) {
		String appkey = values.get(Constants.CLIENT_API_KEY);
		if(appkey == null){
			appkey = PaymentClientImpl.appKey;
		}
		Map<String, String> headers = new HashMap<String, String>();
		if (appkey != null) {
			headers.put("Authorization", "Token " + appkey);
		}
		return headers;
	}
	@Override
	public Payment read(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Payment> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
