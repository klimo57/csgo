package com.klimo.csgo.hltv.url;

import java.util.HashMap;
import java.util.Map;

import com.klimo.csgo.exception.HLTVUrlBuildingException;

public class HLTVUrlBuilder implements UrlBuilder {

	private static final String URL = "https://www.hltv.org/";

	private Map<String, String> headers = new HashMap<>();

	private StringBuilder path;
	private boolean paramsAdded;
	private String url = null;

	public HLTVUrlBuilder() {
		this.path = new StringBuilder();
		headers.put("authority", "www.hltv.org");
		headers.put("method", "");
		headers.put("path", "/");
		headers.put("scheme", "https");
		headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		headers.put("accept-encoding", "gzip, deflate, br");
		headers.put("accept-language", "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7");
		headers.put("dnt", "1");
		headers.put("upgrade-insecure-requests", "1");
		headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
	}

	@Override
	public HLTVUrlBuilder setMethod(String method) {
		headers.put("method", method.toUpperCase());
		return this;
	}

	@Override
	public HLTVUrlBuilder setSite(Site site) {
		if(paramsAdded)
			throw new HLTVUrlBuildingException("already added params, cannot add site");
		path.append(site.getPath()).append('/');
		return this;
	}

	public HLTVUrlBuilder addStatus(Status status) {
		addParam(Status.getKey(), status.getValue());
		return this;
	}

	public HLTVUrlBuilder addEventType(EventType type) {
		addParam(EventType.getKey(), type.getValue());
		return this;
	}

	@Override
	public Map<String, String> headers() {
		return headers;
	}

	@Override
	public String build() {
		if(url != null)
			return url;

		headers.put("path", "/" + path.toString());
		url = path.insert(0, URL).toString();
		return url;
	}

	private void addParam(String key, String value) {
		if(paramsAdded)
			path.append('&');
		else {
			path.append('?');
			paramsAdded = true;
		}
		path.append(key).append('=').append(value);
	}

}
