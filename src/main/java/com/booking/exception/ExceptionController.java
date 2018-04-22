package com.booking.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BookingServiceBusinessException.class)
	public ResponseEntity<Map<String, String>> bookingServiceBusinessExceptionHandler(BookingServiceBusinessException exception){
		return this.getErrorResponse(exception,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> genericExceptionHandler(Exception exception){
		return this.getErrorResponse(exception,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(NoBookingFoundBusinessException.class)
	public ResponseEntity<Map<String, String>> noBookingFoundBusinessExceptionHandler(NoBookingFoundBusinessException exception){
		return this.getErrorResponse(exception, HttpStatus.OK);
	}
	
	@ExceptionHandler(NoRestaurantFoundBusinessException.class)
	public ResponseEntity<Map<String, String>> noRestaurantFoundBusinessExceptionHandler(NoRestaurantFoundBusinessException exception){
		return this.getErrorResponse(exception, HttpStatus.OK);
	}
	
	private ResponseEntity<Map<String,String>> getErrorResponse(Exception exception,HttpStatus status){
		Map<String , String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		return new ResponseEntity<Map<String,String>>(response, status);
	}
}
