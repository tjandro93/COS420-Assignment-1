package edu.usm.cos420.assignment1.service.impl;

import java.util.List;

import edu.usm.cos420.assignment1.dao.domain.OrderDao;
import edu.usm.cos420.assignment1.domain.Order;
import edu.usm.cos420.assignment1.service.OrderRepository;

public class OrderRepositoryImpl implements OrderRepository {

	private OrderDao dao;
	
	/**
	 * Default constructor create a new OrderDao with its default constructor
	 */
	public OrderRepositoryImpl() {
		this.dao = new OrderDao();
	}
	
	/**
	 * Constructor that takes an existing OrderDao
	 * @param dao the OrderDao to use
	 */
	public OrderRepositoryImpl(OrderDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Add an {@code Order} to the system
	 * @param order the {@code Order} to be added
	 */
	@Override
	public void addOrder(Order order) {
		dao.add(order);
	}
	
	/**
	 * Find a specific {@code Order} from the system
	 * @param idNum the ID of the {@code Order}
	 * @return the {@code Order} with the given ID or null if none is found
	 */
	@Override
	public Order findOrdersById(long idNum) {
		return dao.find(idNum);
	}

	/**
	 * Get all the Order's currently in the system
	 * @return list of all Order's in system
	 */
	@Override
	public List<Order> getAll() {
		return dao.list();
	}

}
