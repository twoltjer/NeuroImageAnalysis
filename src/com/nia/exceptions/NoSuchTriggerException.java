package com.nia.exceptions;

public class NoSuchTriggerException extends Exception {
	/**
	 * Serial version unique identifier.
	 */
	private static final long serialVersionUID = 7919690165137537556L;

	public NoSuchTriggerException() {
		super();
	}

	@Override
	public String getMessage() {
		return "There is no such trigger!";
	}
}
