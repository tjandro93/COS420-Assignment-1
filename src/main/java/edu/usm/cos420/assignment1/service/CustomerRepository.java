package edu.usm.cos420.assignment1.service;

import java.util.List;

import edu.usm.cos420.assignment1.domain.Customer;

/**
 * A repository service interface to define actions to be performed on the list of {@link Customer} entities.
 */
public interface CustomerRepository {

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
	public List<Customer> findCustomersByName(String name);
	
	/**
	 * Find a specific {@code Customer} from the system
	 * @param idNum the ID of the {@code Customer}
	 * @return the {@code Customer} with the given ID or null if none is found
	 */
	public Customer findCustomersById(int idNum);
	
	/**
	 * Get all the Customer's currently in the system
	 * @return list of all Customer's in system
	 */
	public List<Customer> getAll();
}
