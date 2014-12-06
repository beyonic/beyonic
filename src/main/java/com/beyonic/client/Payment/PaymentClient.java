package com.beyonic.client.Payment;

import java.util.List;
import java.util.Map;

import com.beyonic.client.exception.APIConnectionException;
import com.beyonic.client.exception.AuthenticationException;
import com.beyonic.client.exception.InvalidRequestException;
import com.beyonic.client.model.Payment;

public interface PaymentClient {
	
	public void setKey(String expectedKey);
	public void setVersion(String version);
	
	public Payment create(Map<String, String> values)throws AuthenticationException, InvalidRequestException, APIConnectionException ;
	
	public Payment read(String id);
	public List<Payment> list();

}
