package com.booking.constants;

public interface BookingServiceConstants {

	public String BOOKING_NOT_SAVED_ERROR_MESSAGE="something went wrong while saving the booking. please try again later";
	
	public String RESTAURANT_DETAILS_NOT_UPDATED_ERROR_MESSAGE="something went wrong while updating the corresponding restaurant "
			+ "details, please try again later";
	
	public String FAILED_TO_GET_BOOKINGS_FOR_RESTAURANT = "something went wrong while fetching the bookings for a restaurant";
	
	public String NO_BOOKING_FOUND_FOR_RESTAURANT_MESSAGE = "No booking was found for this restaurant";
	
	public String NO_RESTAURANT_FOR_GIVEN_NAME_MESSAGE="No restaurants were found for the given restaurant name and table count";
	
	public String NO_RESTAURANT_FOR_GIVEN_ADDRESS="No restaurants were found for the given address and table count";
	
	public String ERROR_WHILE_GETTING_RESTAURANTS_BY_ADDRESS="Something went wrong while fetching the restaurants by address";
	
	public String ERROR_WHILE_GETTING_RESTAURANTS_BY_NAME="Something went wrong while fetching the restaurants by name";
	
	public String NO_RESTAURANT_FOR_GIVEN_ID = "No restaurant was found for the given restaurant ID";
	
	public String ERROR_WHILE_GETTING_RESTAURANT_BY_ID= "something went wrong while getting the restuarant by ID";
	
	public String ERROR_WHILE_SENDING_MAIL_TO_THE_USER = "something went wrong while sending mail to the user";
	
	public String BOOKING_MAIL_SUBJECT = "Your booking confirmation";
	
	public String THANKYOU_MESSAGE = "Thank you for booking with us";
	
	public String DOUBLE_NEW_LINE = "\n\n";
	
	public String SINGLE_NEW_LINE = "\n";
	
	public String RESTAURANT_SERVICE_DOWN_MESSAGE = "Restaurant service is temporarily down, please try again";
	
	public String PROBLEM_WITH_RESTAURANT_SERVICE = "Something is wrong with the restaurant service, please try again";
	
	public String CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS = "restaurantBookingCache";
	
	public String CACHE_REFRESHED_FOR_RESTAURANT_MESSAGE = "RESTAURANT cache refreshed";
	
	public String CACHE_REFRESHED_FOR_BOOKINGS_MESSAGE = "BOOKING cache refreshed";
	
	public String NO_RESTAURANT_FOUND_IN_THE_DB = "there are no restaurants currently registered in the system";

	public String ERROR_WHILE_GETTING_ALL_RESTAURANTS = "something went wrong while getting all the restaurants for caching";

	public String ERROR_WHILE_GETTING_BOOKINGS_FOR_USER = "something went wrong while getting the bookings for the user";

	
}
