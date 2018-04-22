package com.booking.service;

import java.util.List;

import com.booking.model.Booking;
import com.booking.model.BookingInputDto;
import com.booking.model.Restaurant;

public interface BookingService {

	Booking placeBooking(Booking booking);
	
	Restaurant updateRestaurantEntity(BookingInputDto bookingInputDto);
	
	List<Booking> getBookingsForRestaurant(Integer restaurantId);
	
	List<Restaurant> getRestaurantsByName(String restaurantName,Integer numberOfTablesRequired);

	List<Restaurant> getRestaurantsByAddress(String address, Integer numberOfTablesRequired);

	List<Booking> getBookingsForUser(String bookedByUserId);
	
	void clearCache();
		
}
