package edu.usm.cos420.assignment1.domain;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


/**
 * A model class to represent an order made by a customer
 * <p>
 * Contains an id field and a list of Integers of InventoryItem IDs
 */
public class Order implements Serializable{

	private static final long serialVersionUID = -4229245226118800982L;
	
	private long id;
	private List<InventoryItem> orderItems;
	
	public Order(int id, List<InventoryItem> orderItems){
		this.id = id;
		this.orderItems = orderItems;
	}
	
	public Order(int id){
		this.id = id;
		this.orderItems = new ArrayList<>();
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
	public List<InventoryItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * Set the orderItems map
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<InventoryItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Order ID: ");
		sb.append(id);
		sb.append("\nItems:\n");
		for(InventoryItem item : orderItems){
			sb.append("\t");
			sb.append(item.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
