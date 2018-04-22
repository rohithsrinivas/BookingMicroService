package com.booking.model;

public class Table {

	private String category;
	private Boolean booked;
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the booked
	 */
	public Boolean getBooked() {
		return booked;
	}
	/**
	 * @param booked the booked to set
	 */
	public void setBooked(Boolean booked) {
		this.booked = booked;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booked == null) ? 0 : booked.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		Table other = (Table) obj;
		if (booked == null) {
			if (other.booked != null)
				return false;
		} else if (!booked.equals(other.booked))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}
	
	
}
