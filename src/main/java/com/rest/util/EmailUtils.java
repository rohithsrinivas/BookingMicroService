package com.rest.util;

import com.booking.constants.BookingServiceConstants;
import com.booking.model.Booking;
import com.booking.model.Item;

public class EmailUtils {
	
	
	
	/*this util method is to generate the mail body for the booking details which are to be sent to the user*/
	public static String generateMailBody(Booking booking) {
		StringBuilder emailContent = new StringBuilder();
		emailContent.append("Your booking has been confirmed, please find the details of your booking."
		+BookingServiceConstants.DOUBLE_NEW_LINE);
		emailContent.append("Booking ID : "+booking.getBookingId()
		+BookingServiceConstants.DOUBLE_NEW_LINE);
		emailContent.append("Table Category : "+booking.getTableCategory()
		+BookingServiceConstants.DOUBLE_NEW_LINE);
		emailContent.append("Number of tables booked : "+booking.getNumberOfTablesBooked()
		+BookingServiceConstants.DOUBLE_NEW_LINE);
		for(Item item : booking.getOrderedItems()) {
			emailContent.append("Item Name : "+item.getItemName()
			+BookingServiceConstants.SINGLE_NEW_LINE);
			emailContent.append("Item Price : "+item.getPrice()
			+BookingServiceConstants.SINGLE_NEW_LINE);
			emailContent.append("Item category : "+item.getItemCategory()
			+BookingServiceConstants.SINGLE_NEW_LINE);
			emailContent.append(BookingServiceConstants.DOUBLE_NEW_LINE);
		}
		
		emailContent.append("Total billing amount : "+booking.getBillingAmount()
		+BookingServiceConstants.DOUBLE_NEW_LINE);
		emailContent.append(BookingServiceConstants.THANKYOU_MESSAGE);
		
		return emailContent.toString();
	}
	
	

}
