package com.booking.service;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.booking.constants.BookingServiceConstants;
import com.booking.exception.BookingServiceBusinessException;
import com.booking.exception.NoBookingFoundBusinessException;
import com.booking.exception.NoRestaurantFoundBusinessException;
import com.booking.model.Booking;
import com.booking.model.BookingInputDto;
import com.booking.model.Restaurant;
import com.booking.repo.BookingRepo;
import com.rest.util.ServerLoggerUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
@SuppressWarnings({ "unchecked"})
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepo bookingRepo;
	
//	@Autowired
//	JavaMailSender mailSender;
	
	@Autowired
	CacheManager cacheManager;
	
	@Value("${restaurant.service.baseurl}")
	private String baseUrl ;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@CachePut(cacheNames=BookingServiceConstants.CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS,key="#result.bookingId")
	public Booking placeBooking(Booking booking) {
		Booking bookingResult = null;
		try {
			bookingResult = this.bookingRepo.save(booking);
			this.clearCache();
		} catch (HibernateException|PersistenceException e) {
			ServerLoggerUtil.error(this.getClass(), BookingServiceConstants.BOOKING_NOT_SAVED_ERROR_MESSAGE+" "+
					e.getMessage()+" "+e.getStackTrace());
			throw new BookingServiceBusinessException(BookingServiceConstants.BOOKING_NOT_SAVED_ERROR_MESSAGE);
		}
		return bookingResult;
	}
	
	@Override
	public List<Booking> getBookingsForUser(String bookedByUserId) {
		List<Booking> bookingsForUser = null;
		try {
			bookingsForUser = this.bookingRepo.findTop10ByBookedByUserIdOrderByBookingIdDesc(bookedByUserId);
		} catch (HibernateException|PersistenceException e) {
			ServerLoggerUtil.error(this.getClass(), BookingServiceConstants.ERROR_WHILE_GETTING_BOOKINGS_FOR_USER+" "+
					e.getMessage()+" "+e.getStackTrace());
			throw new BookingServiceBusinessException(BookingServiceConstants.ERROR_WHILE_GETTING_BOOKINGS_FOR_USER);
		}
		return bookingsForUser;
	}

	
	@Override
	public Restaurant updateRestaurantEntity(BookingInputDto bookingInputDto) {
		Restaurant restaurant = null;
		if(this.isRestaurantServerUp()) {
		try {
		RestTemplate template=new RestTemplate();
		HttpEntity<?> entity=new HttpEntity<>(this.getAuthHeaders());
		ResponseEntity<Restaurant> response = template.exchange(baseUrl+"/getRestaurantById/"+bookingInputDto.getRestaurantId()
		, HttpMethod.GET,entity,Restaurant.class);
		restaurant=response.getBody();
		if(restaurant!=null) {
		restaurant.setTableFleet(restaurant.getTableFleet()-bookingInputDto.getNoOfTablesBooked());
		HttpHeaders headers=this.getAuthHeaders();
		HttpEntity<Restaurant> restaurantEntity=new HttpEntity<Restaurant>(restaurant, headers);
		ResponseEntity<Restaurant> updateResponse = 
				template.exchange(this.baseUrl+"/update", HttpMethod.PUT,restaurantEntity, Restaurant.class);
		if(updateResponse.getBody()!=null)
			return updateResponse.getBody();
		}
		else
			throw new NoRestaurantFoundBusinessException(BookingServiceConstants.NO_RESTAURANT_FOR_GIVEN_ID);
		}catch (Exception e) {
			if(e instanceof NoRestaurantFoundBusinessException)
				throw new NoRestaurantFoundBusinessException(BookingServiceConstants.NO_RESTAURANT_FOR_GIVEN_ID);
			ServerLoggerUtil.error(this.getClass(), BookingServiceConstants.RESTAURANT_DETAILS_NOT_UPDATED_ERROR_MESSAGE+" "+
					e.getMessage()+" "+e.getStackTrace());
			throw new BookingServiceBusinessException(BookingServiceConstants.RESTAURANT_DETAILS_NOT_UPDATED_ERROR_MESSAGE);
		}
		}
		else
			throw new BookingServiceBusinessException(BookingServiceConstants.RESTAURANT_SERVICE_DOWN_MESSAGE);
		
		return null;
		
	}
	

	@Override
	@Cacheable(cacheNames=BookingServiceConstants.CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS)
	public List<Booking> getBookingsForRestaurant(Integer restaurantId) {
		List<Booking> bookingsForRestaurant = null;
		try {
			bookingsForRestaurant=this.bookingRepo.findByRestaurantRestaurantId(restaurantId);
		}catch (HibernateException|PersistenceException e) {
			ServerLoggerUtil.error(this.getClass(), BookingServiceConstants.FAILED_TO_GET_BOOKINGS_FOR_RESTAURANT+" "+
					e.getMessage()+" "+e.getStackTrace());
			throw new BookingServiceBusinessException(BookingServiceConstants.FAILED_TO_GET_BOOKINGS_FOR_RESTAURANT);
		}
		if(bookingsForRestaurant!=null && bookingsForRestaurant.size()>0)
			return bookingsForRestaurant;
		else
			throw new NoBookingFoundBusinessException(BookingServiceConstants.NO_BOOKING_FOUND_FOR_RESTAURANT_MESSAGE);
	}
	

	@Override
	@Cacheable(cacheNames=BookingServiceConstants.CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS)
	public List<Restaurant> getRestaurantsByName(String restaurantName, Integer numberOfTablesRequired) {
		RestTemplate template=new RestTemplate();
		if(this.isRestaurantServerUp()) {
		HttpEntity<?> entity=new HttpEntity<>(this.getAuthHeaders());
		ResponseEntity<?> response = template.exchange(
				this.baseUrl+"/getRestaurantsByName/"+restaurantName+"/"+numberOfTablesRequired,HttpMethod.GET,entity,
				Object.class);
		
		if(response.getBody() instanceof List<?>) {
			List<Restaurant> restaurantList=(List<Restaurant>) response.getBody();
				return restaurantList;
		}
		else
			throw new BookingServiceBusinessException(BookingServiceConstants.ERROR_WHILE_GETTING_RESTAURANTS_BY_NAME);
		}
		else
			throw new BookingServiceBusinessException(BookingServiceConstants.RESTAURANT_SERVICE_DOWN_MESSAGE);
	}
	
	
	@Override
	@Cacheable(cacheNames=BookingServiceConstants.CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS)
	public List<Restaurant> getRestaurantsByAddress(String address, Integer numberOfTablesRequired)  {
		RestTemplate template=new RestTemplate();
		if(this.isRestaurantServerUp()) {
		HttpEntity<?> entity=new HttpEntity<>(this.getAuthHeaders());
		ResponseEntity<?> response = template.exchange(
				this.baseUrl+"/getRestaurantsByAddress/"+address+"/"+numberOfTablesRequired,HttpMethod.GET,entity,
				Object.class);
		
		if(response.getBody() instanceof List<?>) {
			List<Restaurant> restaurantList=(List<Restaurant>) response.getBody();
				return restaurantList;
		}
		else
			throw new BookingServiceBusinessException(BookingServiceConstants.ERROR_WHILE_GETTING_RESTAURANTS_BY_ADDRESS);
		}
		else
			throw new BookingServiceBusinessException(BookingServiceConstants.RESTAURANT_SERVICE_DOWN_MESSAGE);
	}
	
	
	private HttpHeaders getAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString("rohith:rohith123".getBytes(Charset.forName("UTF-8"))));
	    return headers;
	}


	/*we are using async because we use a separate thread for tasks like sending a mail so that in case of 
	 * any exception or failure while sending the mail it does not interrupt 
	 * the regular flow of the application*/
//	@Override
//	@Async
//	public Boolean sendMailToTheUser(Booking booking) {
//		MimeMessage message=this.mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//		try {
//			helper.setTo(booking.getUserEmail());
//			helper.setSubject(BookingServiceConstants.BOOKING_MAIL_SUBJECT);
//			helper.setText(EmailUtils.generateMailBody(booking));
//			this.mailSender.send(message);
//		} catch (Exception e) {
//			ServerLoggerUtil.error(this.getClass(), BookingServiceConstants.ERROR_WHILE_SENDING_MAIL_TO_THE_USER
//					+" "+e.getMessage()+ " " +e.getClass());
//			throw new BookingServiceBusinessException(BookingServiceConstants.ERROR_WHILE_SENDING_MAIL_TO_THE_USER);
//		}
//		return Boolean.TRUE;
//	}

	/*this method is to check if the restaurant service is up and running*/
	private boolean isRestaurantServerUp() {
		RestTemplate template=new RestTemplate();
		HttpEntity<?> entity=new HttpEntity<>(this.getAuthHeaders());
		try {
			template.exchange(
					this.baseUrl+"/health",HttpMethod.GET,entity,
					Object.class);
		} catch (Exception e) {
			if(e instanceof ResourceAccessException) {
				ServerLoggerUtil.error(this.getClass(), BookingServiceConstants.RESTAURANT_SERVICE_DOWN_MESSAGE
						+" "+e.getMessage()+ " " +e.getStackTrace());
				return false;
			}
			else
				throw new BookingServiceBusinessException(BookingServiceConstants.PROBLEM_WITH_RESTAURANT_SERVICE);
		}
		return true;
		
	}

	@Override
	@CacheEvict(cacheNames=BookingServiceConstants.CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS,allEntries=true)
	public void clearCache() {
		cacheManager.getCache(BookingServiceConstants.CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS).clear();
		ServerLoggerUtil.info(this.getClass(), "&&&  Cache cleared  &&&");
	}


	

	
}
