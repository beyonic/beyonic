package com.beyonic.client.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MetadataUtil {
	public Map<String,String> getMetadataAsMap(String metadata){
		Map<String,String> values= new HashMap<String, String>();
		
		Type collectionType = new TypeToken<HashMap<String, String>>(){}.getType();
		Gson gson = new Gson();
		values = gson.fromJson(metadata, collectionType);
		
		return values;
	}
	public String getMetadataAsMap(Map<String,String> metadata){
		
		Type collectionType = new TypeToken<HashMap<String, String>>(){}.getType();
		Gson gson = new Gson();
		String metaString = gson.toJson(metadata, collectionType);
		return metaString;
	}
}
