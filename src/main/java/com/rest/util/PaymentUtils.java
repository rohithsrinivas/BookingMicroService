package com.rest.util;

import java.util.List;

import com.booking.model.Item;

public class PaymentUtils {

	/*this method is to calculate the total amount payable by the customer based on the items that he has ordered 
	 * during the booking i.e if any*/
	public static Double calculateBill(List<Item> orderedItems) {
		Double totalAmountPayable = 0D;
		for(Item item:orderedItems) {
			totalAmountPayable+=(item.getPrice()*item.getQuantity());
		}
		return totalAmountPayable;
	}

}
