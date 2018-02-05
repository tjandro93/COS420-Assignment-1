package edu.usm.cos420.assignment1.service;

import java.util.List;

import edu.usm.cos420.assignment1.domain.Order;

/**
 * A repository service interface to define actions to performed on the Order database
 */
public interface OrderRepository {
	/**
	 * Add an {@code Order} to the system
	 * @param order the {@code Order} to be added
	 */
	public void addOrder(Order order);
	
	/**
	 * Find a specific {@code Order} from the system
	 * @param idNum the ID of the {@code Order}
	 * @return the {@code Order} with the given ID or null if none is found
	 */
	public Order findOrdersById(long idNum);
	
	/**
	 * Get all the Order's currently in the system
	 * @return list of all Order's in system
	 */
	public List<Order> getAll();
}
