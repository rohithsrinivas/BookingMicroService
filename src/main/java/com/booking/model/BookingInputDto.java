package com.booking.model;

import java.util.List;

import javax.persistence.ElementCollection;

public class BookingInputDto {

	private Integer restaurantId;
	private String tableCategory;
	private List<Item> orderedItems;
	private Integer noOfTablesBooked;
	private String userEmail;
	private Restaurant restaurant;
	private String bookedByUserId;


	/**
	 * @return the bookedByUserId
	 */
	public String getBookedByUserId() {
		return bookedByUserId;
	}
	/**
	 * @param bookedByUserId the bookedByUserId to set
	 */
	public void setBookedByUserId(String bookedByUserId) {
		this.bookedByUserId = bookedByUserId;
	}
	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}
	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	/**
	 * @return the restaurantId
	 */
	public Integer getRestaurantId() {
		return restaurantId;
	}
	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	/**
	 * @return the tableCategory
	 */
	public String getTableCategory() {
		return tableCategory;
	}
	/**
	 * @param tableCategory the tableCategory to set
	 */
	public void setTableCategory(String tableCategory) {
		this.tableCategory = tableCategory;
	}
	/**
	 * @return the orderedItems
	 */
	public List<Item> getOrderedItems() {
		return orderedItems;
	}
	/**
	 * @param orderedItems the orderedItems to set
	 */
	public void setOrderedItems(List<Item> orderedItems) {
		this.orderedItems = orderedItems;
	}
	/**
	 * @return the noOfTablesBooked
	 */
	public Integer getNoOfTablesBooked() {
		return noOfTablesBooked;
	}
	/**
	 * @param noOfTablesBooked the noOfTablesBooked to set
	 */
	public void setNoOfTablesBooked(Integer noOfTablesBooked) {
		this.noOfTablesBooked = noOfTablesBooked;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookedByUserId == null) ? 0 : bookedByUserId.hashCode());
		result = prime * result + ((noOfTablesBooked == null) ? 0 : noOfTablesBooked.hashCode());
		result = prime * result + ((orderedItems == null) ? 0 : orderedItems.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
		result = prime * result + ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((tableCategory == null) ? 0 : tableCategory.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingInputDto other = (BookingInputDto) obj;
		if (bookedByUserId == null) {
			if (other.bookedByUserId != null)
				return false;
		} else if (!bookedByUserId.equals(other.bookedByUserId))
			return false;
		if (noOfTablesBooked == null) {
			if (other.noOfTablesBooked != null)
				return false;
		} else if (!noOfTablesBooked.equals(other.noOfTablesBooked))
			return false;
		if (orderedItems == null) {
			if (other.orderedItems != null)
				return false;
		} else if (!orderedItems.equals(other.orderedItems))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (tableCategory == null) {
			if (other.tableCategory != null)
				return false;
		} else if (!tableCategory.equals(other.tableCategory))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookingInputDto [restaurantId=" + restaurantId + ", tableCategory=" + tableCategory + ", orderedItems="
				+ orderedItems + ", noOfTablesBooked=" + noOfTablesBooked + ", userEmail=" + userEmail + ", restaurant="
				+ restaurant + ", bookedByUserId=" + bookedByUserId + "]";
	}
	
	
	
	
}
