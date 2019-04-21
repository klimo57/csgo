package com.klimo.csgo.exception;

public class HLTVUrlBuildingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HLTVUrlBuildingException() {
		super();
	}

	public HLTVUrlBuildingException(String message) {
		super(message);
	}

	public HLTVUrlBuildingException(Throwable cause) {
		super(cause);
	}

	public HLTVUrlBuildingException(String message, Throwable cause) {
		super(message, cause);
	}

}
