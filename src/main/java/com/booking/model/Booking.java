package com.booking.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bookings")
public class Booking {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bookingId;
	private String tableCategory;
	@ElementCollection
	private List<Item> orderedItems;
	private String userEmail;
	private Double billingAmount;
	private Integer numberOfTablesBooked;
	@Embedded
	private Restaurant restaurant;
	@Basic
	private String bookingDate;
	@Basic
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
	 * @return the bookingDate
	 */
	public String getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
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
	 * @return the bookingId
	 */
	public Integer getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
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
	/**
	 * @return the billingAmount
	 */
	public Double getBillingAmount() {
		return billingAmount;
	}
	/**
	 * @param billingAmount the billingAmount to set
	 */
	public void setBillingAmount(Double billingAmount) {
		this.billingAmount = billingAmount;
	}
	/**
	 * @return the numberOfTablesBooked
	 */
	public Integer getNumberOfTablesBooked() {
		return numberOfTablesBooked;
	}
	/**
	 * @param numberOfTablesBooked the numberOfTablesBooked to set
	 */
	public void setNumberOfTablesBooked(Integer numberOfTablesBooked) {
		this.numberOfTablesBooked = numberOfTablesBooked;
	}
	
	
	
}
