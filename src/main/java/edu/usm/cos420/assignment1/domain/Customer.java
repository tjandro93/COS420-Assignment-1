package edu.usm.cos420.assignment1.domain;

import java.io.Serializable;

/**
 * A domain class to represent a customer of the shop
 * <p>
 * Contains three fields
 * <ul>
 * <li>ID, a 6 digit integer to identify the customer</li>
 * <li>address, a {@code String} of the customer's address</li>
 * <li>name, a {@code String} of the customer's name</li>
 * </ul>
 */
public class Customer implements Serializable{
	
		private static final long serialVersionUID = -8605464037542649412L;
		
		private long id;
		private String name;
		private String address;
		
		/**
		 * Parameterized constructor
		 * <p>
		 * Simply sets the fields of this customer instance
		 * @param id a 6 digit integer (is not validated by this class)
		 * @param address the customer's address
		 * @param name the customer's name
		 */
		public Customer(long id, String name, String address) {
			this.id = id;
			this.address = address;
			this.name = name;
		}
		
		public String toString(){
			return String.format("ID: %6d, Name: %s, Address: %s", this.id, this.name, this.address);
		}

		/**
		 * Get the customer's ID
		 * @return the customer's ID number
		 */
		public long getId() {
			return id;
		}

		/**
		 * Get the customer's address
		 * @return the customer's address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * Get the customer's name
		 * @return the customer's name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Set the customer's ID
		 * @param the new ID for the customer
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Set the customer's address
		 * @param the new address for the customer
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/** Set the customer's name
		 * @param the new name for the customer
		 */
		public void setName(String name) {
			this.name = name;
		}
		
}
