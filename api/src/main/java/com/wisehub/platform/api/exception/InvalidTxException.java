package com.wisehub.platform.api.exception;

public class InvalidTxException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTxException() {
		super();
	}

	public InvalidTxException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidTxException(final String message) {
		super(message);
	}

	public InvalidTxException(final Throwable cause) {
		super(cause);
	}

}
