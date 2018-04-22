package com.booking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.booking.base.BookingMicroServiceApplicationTests;
import com.booking.constants.BookingServiceConstants;
import com.booking.model.Booking;
import com.booking.model.BookingInputDto;
import com.booking.model.Item;
import com.booking.model.Restaurant;
import com.booking.service.BookingService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Transactional
public class BookingControllerTest extends BookingMicroServiceApplicationTests{
	
	private Gson gson;
	
	@Autowired
	BookingService bookingService;
	
	@Before
	public void setUp() {
		this.gson=new Gson();
		super.setUpWithWebAppContext();
	}
	
	@Test
	public void createBookingTestNegative() throws Exception {
		List<Item> items=new ArrayList<>();
		Item item1=new Item();
		item1.setItemName("itemname");
		item1.setItemCategory("starters");
		item1.setPrice(200D);
		Item item2=new Item();
		item2.setItemName("itemname2");
		item2.setItemCategory("starters2");
		item2.setPrice(300D);
		items.add(item1);
		items.add(item2);
		BookingInputDto inputdto=new BookingInputDto();
		inputdto.setRestaurantId(4);
		inputdto.setOrderedItems(items);
		inputdto.setTableCategory("random");
		inputdto.setUserEmail("randomemail");
		inputdto.setNoOfTablesBooked(2);
		String jsonInput = this.gson.toJson(inputdto);
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/createBooking")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonInput)).andReturn();
		Assert.assertEquals("the response for createBookingTest was supposed to be 200",200, result.getResponse().getStatus());
		System.out.println(result.getResponse().getContentAsString());
		Map<String, String> response = gson.fromJson(result.getResponse().getContentAsString(),
				new TypeToken<Map<String, String>>(){}.getType());
		Assert.assertEquals("The response from the server for a non existing restaurant ID was faulty"
				,BookingServiceConstants.NO_RESTAURANT_FOR_GIVEN_ID
				, response.get("message"));
	}
	
	@Test
	public void createBookingTest() throws Exception {
		List<Item> items=new ArrayList<>();
		Item item1=new Item();
		item1.setItemName("itemname");
		item1.setItemCategory("starters");
		item1.setPrice(200D);
		Item item2=new Item();
		item2.setItemName("itemname2");
		item2.setItemCategory("starters2");
		item2.setPrice(300D);
		items.add(item1);
		items.add(item2);
		BookingInputDto inputdto=new BookingInputDto();
		inputdto.setRestaurantId(2);
		inputdto.setOrderedItems(items);
		inputdto.setTableCategory("random");
		inputdto.setUserEmail("randomemail");
		inputdto.setNoOfTablesBooked(2);
		String jsonInput = this.gson.toJson(inputdto);
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/createBooking")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonInput)).andReturn();
		Assert.assertEquals("the response for createBookingTest was supposed to be 200",200, result.getResponse().getStatus());
		System.out.println(result.getResponse().getContentAsString());
		Booking response = gson.fromJson(result.getResponse().getContentAsString(),
				Booking.class);
		Assert.assertNotNull("The expected booking entity on succesful booking was not returned from the server",response);
	}
	
	@Test
	public void getBookingsForRestaurantTest() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/bookingsForRestaurant/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("the response from the server was supposed to be 200",200, status);
		List<Booking> response 
		= gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<ArrayList<Booking>>(){}.getType());
		Assert.assertNotNull("the response was not supposed to be null in getBookingsForRestaurantTest",response);
	}
	
	@Test
	public void getBookingsForRestaurantTestNegative() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/bookingsForRestaurant/67")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("the response from the server was supposed to be 200",200, status);
		Map<String,String> response 
		= gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<Map<String,String>>(){}.getType());
		Assert.assertEquals(BookingServiceConstants.NO_BOOKING_FOUND_FOR_RESTAURANT_MESSAGE, response.get("message"));
	}
	
	@Test
	public void getRestaurantsByNameTest() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders
				.get("/restaurants/name/mc/1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("the response from the server was supposed to be 200",200, status);
		List<Booking> response 
		= gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<ArrayList<Restaurant>>(){}.getType());
		Assert.assertTrue("The size of the response was supposed to be more than zero", 
				response.size()>0);
	}
	
	@Test
	public void getRestaurantsByNameTestNegative() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders
				.get("/restaurants/name/xyz/1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("the response from the server was supposed to be 200",200, status);
		Map<String,String> response 
		= gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<Map<String,String>>(){}.getType());
		Assert.assertEquals(BookingServiceConstants.NO_RESTAURANT_FOR_GIVEN_NAME_MESSAGE, response.get("message"));
	}
	
	@Test
	public void getRestaurantsByAddressTest() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders
				.get("/restaurants/address/ec/1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("the response from the server was supposed to be 200",200, status);
		List<Booking> response 
		= gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<ArrayList<Restaurant>>(){}.getType());
		Assert.assertTrue("The size of the response was supposed to be more than zero", 
				response.size()>0);
	}
	
	@Test
	public void getRestaurantsByAddressTestNegative() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders
				.get("/restaurants/address/xyz/1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("the response from the server was supposed to be 200",200, status);
		Map<String,String> response 
		= gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<Map<String,String>>(){}.getType());
		Assert.assertEquals(BookingServiceConstants.NO_RESTAURANT_FOR_GIVEN_ADDRESS, response.get("message"));
	}
	
	
	

}
