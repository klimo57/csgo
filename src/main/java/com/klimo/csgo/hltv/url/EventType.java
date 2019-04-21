package com.klimo.csgo.hltv.url;

public enum EventType {

	MAJOR("MAJOR"),
	INTERNATIONAL_LAN("INTLLAN"),
	REGIONAL_LAN("REGIONALLAN");

	private String value;

	private EventType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String getKey() {
		return "eventType";
	}

}
