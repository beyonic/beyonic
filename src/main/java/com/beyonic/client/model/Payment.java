package com.beyonic.client.model;

import java.util.Date;
import java.util.List;

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
}
