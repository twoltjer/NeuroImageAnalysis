package com.nia.exceptions;

public class FullscreenNotSupportedException extends Exception {
	/**
	 * Serial version unique identifier.
	 */
	private static final long serialVersionUID = 7919690165137537556L;

	public FullscreenNotSupportedException() {
		super();
	}

	@Override
	public String getMessage() {
		return "Full screen is not supported. Exiting now.";
	}
}
