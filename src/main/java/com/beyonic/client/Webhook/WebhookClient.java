package com.beyonic.client.Webhook;

import java.util.List;
import java.util.Map;

import com.beyonic.client.model.Webhook;

public interface WebhookClient {
	
	public void setKey(String expectedKey);
	public void setVersion(String version);
	
	public Webhook craete(Map<String, Object> values);
	public Webhook read(String id);
	public Webhook delete(String id);
	public Webhook update(Map<String, Object> values);
	public List<Webhook> list();

}
