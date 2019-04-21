package com.klimo.csgo.exception;

public class HLTVParsingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HLTVParsingException() {
		super();
	}

	public HLTVParsingException(String message) {
		super(message);
	}

	public HLTVParsingException(Throwable cause) {
		super(cause);
	}

	public HLTVParsingException(String message, Throwable cause) {
		super(message, cause);
	}

}
