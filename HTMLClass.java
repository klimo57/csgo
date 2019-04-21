package com.klimo.csgo.hltv.html;

public enum HTMLClass {

	MATCHDAY("match-day"),
	UPCOMING_MATCH("upcoming-match"),
	DATEFIELD("standard-headline"),
	TIMEFIELD("time"),
	TEAMFIELD("team"),
	FORMATFIELD("map-text");

	private String name;

	private HTMLClass(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
