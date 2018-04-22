package com.booking.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.Booking;
import com.booking.model.BookingInputDto;
import com.booking.model.Restaurant;
import com.booking.service.BookingService;
import com.rest.util.PaymentUtils;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;

	/*Using this endpoint we can get the list of all bookings that are currently available in the booking repository*/
	@PostMapping(value="/createBooking",
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> createBooking(@RequestBody BookingInputDto bookingInputDto){
		this.executeUpdatesOnRestaurant(bookingInputDto);
		Booking bookingEntity = this.prepareBookingDetails(bookingInputDto);
		this.bookingService.placeBooking(bookingEntity);
		return new ResponseEntity<Booking>(bookingEntity, HttpStatus.OK);
	}
	
	/*Using this endpoint we can get the list of bookings made for a particular restaurant based on the restaurant ID*/
	@GetMapping(value="/bookingsForRestaurant/{restaurantId}",
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Booking>> getBookingsForRestaurant(
			@PathVariable("restaurantId") Integer restaurantId){
		return new ResponseEntity<List<Booking>>
		(this.bookingService.getBookingsForRestaurant(restaurantId), HttpStatus.OK);
	}
	
	@GetMapping(value="/bookingsForUser/{bookedByUserId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Booking>> getBookingsForUser(@PathVariable("bookedByUserId") String bookedByUserId){
		return new ResponseEntity<List<Booking>>
		(this.bookingService.getBookingsForUser(bookedByUserId),HttpStatus.OK);
	}
	
	/*Using this endpoint we can get the list of restaurants for a given name and required table count i.e by using
	 * the Restaurant micro service, we fetch the filtered list of restaurants*/
	@GetMapping(value="/restaurants/name/{restaurantName}/{numberOfTablesRequired}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getRestaurantsByName(
			@PathVariable(name="restaurantName",required=true) String restaurantName,
			@PathVariable(name="numberOfTablesRequired",required=true) Integer numberOfTablesRequired){
			return new ResponseEntity<List<Restaurant>>
			(this.bookingService.getRestaurantsByName(restaurantName, numberOfTablesRequired), HttpStatus.OK);
	}
	
	/*Using this endpoint we can get the list of restaurants for a given address and required table count i.e by using
	 * the Restaurant micro service, we fetch the filtered list of restaurants*/
	@GetMapping(value="/restaurants/address/{address}/{numberOfTablesRequired}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getRestaurantsByAddress(
			@PathVariable(name="address",required=true) String address,
			@PathVariable(name="numberOfTablesRequired",required=true) Integer numberOfTablesRequired) throws IOException{
			return new ResponseEntity<List<Restaurant>>
			(this.bookingService.getRestaurantsByAddress(address, numberOfTablesRequired), HttpStatus.OK);
	}
	
	
	
	private Booking prepareBookingDetails(BookingInputDto bookingInputDto) {
		Booking booking=new Booking();
		booking.setNumberOfTablesBooked(bookingInputDto.getNoOfTablesBooked());
		booking.setOrderedItems(bookingInputDto.getOrderedItems());
		booking.setTableCategory(bookingInputDto.getTableCategory());
		booking.setUserEmail(bookingInputDto.getUserEmail());
		booking.setRestaurant(bookingInputDto.getRestaurant());
		booking.setBookingDate(new Date().toString());
		booking.setBookedByUserId(bookingInputDto.getBookedByUserId());
		if(bookingInputDto.getOrderedItems()!=null && bookingInputDto.getOrderedItems().size()>0)
		booking.setBillingAmount(PaymentUtils.calculateBill(bookingInputDto.getOrderedItems()));
		else
			booking.setBillingAmount(0D);
		return booking;
	}
	
	

	/*this functionality is to update the number of tables in the restaurant entity when a booking is placed for a particular
	 * number of tables i.e to reduce those many number of available tables in restaurant entity*/
	private void executeUpdatesOnRestaurant(BookingInputDto bookingInputDto) {
		this.bookingService.updateRestaurantEntity(bookingInputDto);
	}
	
	

}
