package com.beyonic.client.Webhook;

import java.util.List;
import java.util.Map;

import com.beyonic.client.exception.APIConnectionException;
import com.beyonic.client.exception.AuthenticationException;
import com.beyonic.client.exception.InvalidRequestException;
import com.beyonic.client.model.Webhook;

public interface WebhookClient {
	
	public void setKey(String expectedKey);
	public void setVersion(String version);
	
	public Webhook craete(Map<String, String> values) throws AuthenticationException, InvalidRequestException, APIConnectionException ;
	public Webhook read(String id) throws AuthenticationException, InvalidRequestException, APIConnectionException ;
	public void delete(String id) throws AuthenticationException, InvalidRequestException, APIConnectionException ;
	public Webhook update(String id,Map<String, String> values) throws AuthenticationException, InvalidRequestException, APIConnectionException ;
	public List<Webhook> list() throws AuthenticationException, InvalidRequestException, APIConnectionException ;

}
