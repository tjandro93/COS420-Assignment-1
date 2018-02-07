package edu.usm.cos420.assignment1.domain;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;


/**
 * A model class to represent an order made by a customer.
 * <p>
 * Contains an id field and a list of Integers of InventoryItem IDs
 */
public class Order implements Serializable{

	private static final long serialVersionUID = -4229245226118800982L;
	
	private long id;
	private List<InventoryItem> orderItems;
	private LocalDate orderDate;
	
	/**
	 * Constructor
	 * @param id  the ID of the order
	 * @param orderItems  all InventoryItems to be ordered
	 */
	public Order(int id, List<InventoryItem> orderItems){
		this.id = id;
		this.orderItems = orderItems;
		this.orderDate = LocalDate.now();
	}
	
	/**
	 * Constructor: initializes orderItems to an empty list and orderDate to the current date
	 * @param id  the ID of the order
	 */
	public Order(int id){
		this.id = id;
		this.orderItems = new ArrayList<>();
		this.orderDate = LocalDate.now();
	}

	/**
	 * Change the orderDate to be today
	 * @return the new LocalDate for the instant
	 */
	public LocalDate resetOrderDate() {
		this.orderDate = LocalDate.now();
		return this.orderDate;
	}
	
	/**
	 * Get the orderDate
	 * @return the date the Order was made
	 */
	public LocalDate getOrderDate() {
		return this.orderDate;
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
	 * @param id  the id to set
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
	 * @param orderItems  the orderItems to set
	 */
	public void setOrderItems(List<InventoryItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	/**
	 * Simple toString to return the Order as a string
	 * Formated as follows.<p>
	 * Order ID: &lt;ID&gt; Date: &lt;date&gt;<p>
	 * Items:<p>
	 * &lt;item1.toString()&gt;<p>
	 * &lt;item2.toString()&gt;<p>
	 * ...
	 * @return the formatted String
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("Order ID: ");
		sb.append(id);
		sb.append(" Date: ");
		sb.append(orderDate);
		sb.append("\nItems:\n");
		for(InventoryItem item : orderItems){
			sb.append("\t");
			sb.append(item.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
