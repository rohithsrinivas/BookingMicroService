package com.booking.exception;

public class NoBookingFoundBusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBookingFoundBusinessException() {
	}

	public NoBookingFoundBusinessException(String message) {
		super(message);
	}

	public NoBookingFoundBusinessException(Throwable cause) {
		super(cause);
	}

	public NoBookingFoundBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoBookingFoundBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
