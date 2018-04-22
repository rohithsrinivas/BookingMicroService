package com.booking.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.booking.config.BookingConfig;

@SpringBootApplication
@ImportAutoConfiguration(classes= {BookingConfig.class})
public class BookingMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingMicroServiceApplication.class, args);
//		List<Item> items=new ArrayList<>();
//		Item item1=new Item();
//		item1.setItemName("itemname");
//		item1.setItemCategory("starters");
//		item1.setPrice(200D);
//		Item item2=new Item();
//		item2.setItemName("itemname2");
//		item2.setItemCategory("starters2");
//		item2.setPrice(300D);
//		items.add(item1);
//		items.add(item2);
//		BookingInputDto inputdto=new BookingInputDto();
//		inputdto.setRestaurantId(4);
//		inputdto.setOrderedItems(items);
//		inputdto.setTableCategory("random");
//		inputdto.setUserEmail("randomemail");
//		inputdto.setNoOfTablesBooked(5);
//		Gson gson=new Gson();
//		String json = gson.toJson(inputdto);
//		System.out.println(json);
	}
}
