package com.booking.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.booking.constants.BookingServiceConstants;
import com.booking.service.BookingService;
import com.booking.service.BookingServiceImpl;

@Configuration
@EnableJpaRepositories(basePackages="com.booking.repo")
@EnableTransactionManagement
@EntityScan(basePackages="com.booking.model")
@ComponentScan(basePackages="com.booking.*")
@EnableAsync
@EnableScheduling
@EnableCaching
public class BookingConfig {

	public BookingService bookingService() {
		return new BookingServiceImpl();
	}
	
	/*we are using cache in this service because we can retrieve results faster without hitting the db*/
	@Bean
	public CacheManager cacheManager() {
		return new GuavaCacheManager(BookingServiceConstants.CACHE_NAME_FOR_RESTAURANT_AND_BOOKINGS);
	}
	
	
}
