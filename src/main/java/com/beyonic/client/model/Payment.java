package com.beyonic.client.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

public class Payment {
	@SerializedName("id")
	private int id;
	@SerializedName("organization")
	private String organization;
	@SerializedName("amount")
	private long amount;
	@SerializedName("currency")
	private String currency;
	@SerializedName("payment_type")
	private String payment_type;
	@SerializedName("metadata")
	private String metadata;
	@SerializedName("description")
	private String description;
	@SerializedName("phone_nos")
	private List<String> phoneNumbers;
	@SerializedName("state")
	private String state;
	@SerializedName("last_error")
	private String lastError;
	@SerializedName("rejected_reason")
	private String rejectreason;
	@SerializedName("rejected_by")
	private String rejectedBy;
	@SerializedName("rejected_time")
	private Date rejectedTime;
	@SerializedName("cancelled_reason")
	private String cancelledReason;
	@SerializedName("cancelled_by")
	private String cancelledBy;
	@SerializedName("cancelled_time")
	private Date cancelledTime;
	@SerializedName("created")
	private Date createdTime;
	@SerializedName("author")
	private String author;
	@SerializedName("modified")
	private Date modifiedTime;
	@SerializedName("updated_by")
	private String updatedBy;
	@SerializedName("start_date")
	private Date startDate;

	public Map<String,String> getMetadataAsMap(){
		Map<String,String> values= new HashMap<String, String>();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
	    JsonArray array = parser.parse(metadata).getAsJsonArray();
	    for (int i=0; 1< array.size() ; i++) {
	    	JsonArray array2 = parser.parse(gson.fromJson(array.get(i), String.class)).getAsJsonArray();
	    	String key = gson.fromJson(array2.get(0), String.class);
		    String value = gson.fromJson(array2.get(1), String.class);
		    values.put(key, value);
		}
		return values;
	}
}
