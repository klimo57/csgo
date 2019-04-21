package com.klimo.csgo.hltv.url;

public enum Status {

	UPCOMING("Upcoming");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String getKey() {
		return "status";
	}

}
