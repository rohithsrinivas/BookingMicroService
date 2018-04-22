package com.booking.exception;

public class BookingServiceBusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookingServiceBusinessException() {
	}

	public BookingServiceBusinessException(String message) {
		super(message);
	}

	public BookingServiceBusinessException(Throwable cause) {
		super(cause);
	}

	public BookingServiceBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingServiceBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
