package edu.usm.cos420.assignment1.service;

import edu.usm.cos420.assignment1.domain.Customer;

/**
 * A service interface to define actions to be performed on the list of {@link Customer} entities.
 */
public interface CustomerService {

	/**
	 * Add a {@code Customer} to the system
	 * @param customer the {@code Customer} to be added
	 */
	public void addCustomer(Customer customer);
	
	/**
	 * Find a specific {@code Customer} from the system
	 * @param name the name of the {@code Customer}
	 * @return the {@code Customer} with the given name or null if none is found
	 */
	public Customer findCustomerByName(String name);
	
	
}
