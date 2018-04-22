package com.booking.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class Restaurant {
	
	@Basic
	private Integer restaurantId;
	@Basic
	private String restaurantName;
	@Basic
	private String restaurantAddress;
	@ElementCollection
	@AttributeOverrides({
	    @AttributeOverride(name="category", column=@Column(name="table_category",nullable=false)),
	    @AttributeOverride(name="booked", column=@Column(name="table_booked",nullable=false)),
	    @AttributeOverride(name="bookedByUserId", column=@Column(name="table_bookedByUserId",nullable=false))
	  })
	private List<Table> tables;
	@Basic
	private Integer tableFleet;
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="items", column=@Column(nullable=false)),
	  })
	private Menu menu;
	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}
	/**
	 * @param restaurantName the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	/**
	 * @return the restaurantAddress
	 */
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	/**
	 * @param restaurantAddress the restaurantAddress to set
	 */
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	/**
	 * @return the tables
	 */
	public List<Table> getTables() {
		return tables;
	}
	/**
	 * @param tables the tables to set
	 */
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	/**
	 * @return the tableFleet
	 */
	public Integer getTableFleet() {
		return tableFleet;
	}
	/**
	 * @param tableFleet the tableFleet to set
	 */
	public void setTableFleet(Integer tableFleet) {
		this.tableFleet = tableFleet;
	}
	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + ((restaurantAddress == null) ? 0 : restaurantAddress.hashCode());
		result = prime * result + ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((restaurantName == null) ? 0 : restaurantName.hashCode());
		result = prime * result + ((tableFleet == null) ? 0 : tableFleet.hashCode());
		result = prime * result + ((tables == null) ? 0 : tables.hashCode());
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
		Restaurant other = (Restaurant) obj;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		if (restaurantAddress == null) {
			if (other.restaurantAddress != null)
				return false;
		} else if (!restaurantAddress.equals(other.restaurantAddress))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (restaurantName == null) {
			if (other.restaurantName != null)
				return false;
		} else if (!restaurantName.equals(other.restaurantName))
			return false;
		if (tableFleet == null) {
			if (other.tableFleet != null)
				return false;
		} else if (!tableFleet.equals(other.tableFleet))
			return false;
		if (tables == null) {
			if (other.tables != null)
				return false;
		} else if (!tables.equals(other.tables))
			return false;
		return true;
	}
	
	
	
}
