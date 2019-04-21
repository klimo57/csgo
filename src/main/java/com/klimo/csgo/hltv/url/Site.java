package com.klimo.csgo.hltv.url;

public enum Site {

	MATCHES("matches");

	private String path;

	private Site(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
