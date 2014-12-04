package com.beyonic.client.Payment;

import java.util.List;
import java.util.Map;

import com.beyonic.client.model.Payment;

public class PaymentClientImpl implements PaymentClient {

	private static final String CLIENT_API_VERSION = "Client.apiVersion";

	private static final String CLIENT_API_KEY = "Client.apiKey";

	private static final String PAYMENT_API_ENDPOINT = "staging.beyonic.com/api";
	
	private static String version = null;
	private static String appKey;

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
	public Payment create(Map<String, Object> values) {
		// TODO Auto-generated method stub
		return null;
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
