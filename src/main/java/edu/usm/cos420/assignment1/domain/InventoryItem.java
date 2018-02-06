package edu.usm.cos420.assignment1.domain;

import java.io.Serializable;

/**
 * A domain class to represent an item in the shop's inventory
 * <p>
 * Contains four fields
 * <ul>
 * <li>ID, a 6 digit integer to uniquely identify the item</li>
 * <li>Name, a {@code String} of the item's name</li>
 * <li>Description, a {@code String} describing the item</li>
 * <li>Quantity, an integer counting the number of the item in stock</li>
 * </ul>
 */
public class InventoryItem implements Serializable{

	private static final long serialVersionUID = 3218115236366936L;

	private int id;
	private String name;
	private String description;
	private int quantity;
	
	/**
	 * Parameterized constructor
	 * @param id the item's ID number
	 * @param name the item's name
	 * @param description a description of the item
	 * @param quantity the count of the item in stock
	 */
	public InventoryItem(int id, String name, String description, int quantity){
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}
	
	public void decrementQuantity(int numSold){
		this.quantity = this.quantity - numSold;
	}
	
	public String toString(){
		return String.format("ID: %6d, Name: %s, Quantity: %d, Info: %s", 
				this.id, this.name, this.quantity, this.description);
	}

	/**
	 * Get the item's ID
	 * @return the ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the item's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the item's description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the quantity of the item in stock
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set the item's ID
	 * @param id the ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set the item's name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the item's description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the item's quantity
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
