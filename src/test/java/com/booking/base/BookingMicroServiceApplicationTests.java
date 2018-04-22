package com.booking.base;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.booking.controller.BookingController;
import com.booking.microservice.BookingMicroServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=BookingMicroServiceApplication.class)
public class BookingMicroServiceApplicationTests {

	protected MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext applicationContext;
	
	protected void setUpWithWebAppContext() {
		mockMvc=MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	protected void setUpWithStandAlone(BookingController controller) {
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}
	
}
