package com.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

	List<Booking>
	
	findByRestaurantRestaurantId
	
	(Integer restaurantId);
	
	List<Booking>
	
	findTop10ByBookedByUserIdOrderByBookingIdDesc
	
	(String bookedByUserId);
}
