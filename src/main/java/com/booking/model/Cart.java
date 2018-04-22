package com.booking.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;

	@ElementCollection
    private List<Item> itemsInCart;

    private Integer userId;

    public Cart() {
    }

    public List<Item> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<Item> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	/**
	 * @return the cartId
	 */
	public Integer getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
    
    
}
