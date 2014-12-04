package com.beyonic.client.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Webhook {

	@SerializedName("id")
	private int id;
	@SerializedName("event")
    private String event;
	@SerializedName("target")
    private long target;
	@SerializedName("created")
    private Date created;
	@SerializedName("updated")
    private Date updated;
	@SerializedName("user")
    private int user;
}
