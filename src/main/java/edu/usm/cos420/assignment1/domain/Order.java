package edu.usm.cos420.assignment1.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;


/**
 * A model class to represent an order made by a customer
 * <p>
 * Contains an id field to identify each instance and 
 * a map of InventoryItem ID's to the quantity desired
 */
public class Order implements Serializable{

	private static final long serialVersionUID = -4229245226118800982L;
	public static int nextId;
	
	private long id;
	private Map<Long, Long> orderItems;
	
	public Order() {
		id  = nextId;
		orderItems = new HashMap<Long, Long>();
	}

	/**
	 * Get the order ID
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set the order ID
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get the orderItems map
	 * @return the orderItems
	 */
	public Map<Long, Long> getOrderItems() {
		return orderItems;
	}

	/**
	 * Set the orderItems map
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(Map<Long, Long> orderItems) {
		this.orderItems = orderItems;
	}
}
