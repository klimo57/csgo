package com.klimo.csgo.hltv.url;

import java.util.Map;

public interface UrlBuilder {

	public Map<String, String> headers();

	public String build();

	public UrlBuilder setMethod(String method);

	public UrlBuilder setSite(Site site);

}
