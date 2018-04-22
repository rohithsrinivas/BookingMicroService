package com.booking.exception;

public class NoRestaurantFoundBusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRestaurantFoundBusinessException() {
		super();
	}

	public NoRestaurantFoundBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoRestaurantFoundBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRestaurantFoundBusinessException(String message) {
		super(message);
	}

	public NoRestaurantFoundBusinessException(Throwable cause) {
		super(cause);
	}

}
