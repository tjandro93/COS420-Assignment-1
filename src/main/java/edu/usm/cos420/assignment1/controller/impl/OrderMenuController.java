package edu.usm.cos420.assignment1.controller.impl;

import edu.usm.cos420.assignment1.controller.MenuController;
import edu.usm.cos420.assignment1.domain.Customer;
import edu.usm.cos420.assignment1.service.OrderRepository;
import edu.usm.cos420.assignment1.service.impl.OrderRepositoryImpl;
import edu.usm.cos420.assignment1.view.impl.OrderMenuView;

/**
 * A Controller class to execute user's choice for the order menu
 */
public class OrderMenuController implements MenuController {

	private Customer customer;
	private OrderRepository repository;
	private OrderMenuView view;
	
	/**
	 * Default constructor. Sets Customer field to null, initializes OrderRepository 
	 * and OrderMenuView
	 */
	public OrderMenuController() {
		this.customer = null;
		this.repository = new OrderRepositoryImpl();
		this.view = new OrderMenuView();
	}
	
	/**
	 * Constructor: uses provided Customer and OrderRepository
	 * @param customer the Customer the menu is working on
	 * @param repository the repository for data persistence
	 */
	public OrderMenuController(Customer customer, OrderRepository repository) {
		this.customer = customer;
		this.repository = repository;
		this.view = new OrderMenuView();
	}
	
	/**
	 * Constructor: uses provided OrderRepository
	 * @param repository the repository for data persistence
	 */
	public OrderMenuController(OrderRepository repository) {
		this.customer = null;
		this.repository = repository;
		this.view = new OrderMenuView();
	}
	
	/**
	 * Display order menu, get user input, and branch on input
	 */
	@Override
	public void provideMenuAccess() {
		System.out.println("Order menu placeholder");

	}

	/**
	 * Based on {@code choice}, do the desired Order action
	 * @param choice the constant from {@code OrderMenuView} for the desired action
	 */
	@Override
	public void executeChoice(int choice) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get the current Customer the menu is working on
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Set the current Customer the menu is working on
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
