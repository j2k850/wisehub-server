package com.wisehub.platform.api.exception;

public class ConstraintUsernameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConstraintUsernameException() {
		super();
	}

	public ConstraintUsernameException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ConstraintUsernameException(final String message) {
		super(message);
	}

	public ConstraintUsernameException(final Throwable cause) {
		super(cause);
	}

}
