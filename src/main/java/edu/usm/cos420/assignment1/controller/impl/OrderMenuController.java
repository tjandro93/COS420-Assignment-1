package edu.usm.cos420.assignment1.controller.impl;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.usm.cos420.assignment1.controller.MenuController;
import edu.usm.cos420.assignment1.domain.Customer;
import edu.usm.cos420.assignment1.domain.InventoryItem;
import edu.usm.cos420.assignment1.domain.Order;
import edu.usm.cos420.assignment1.service.CustomerRepository;
import edu.usm.cos420.assignment1.service.InventoryRepository;
import edu.usm.cos420.assignment1.service.OrderRepository;
import edu.usm.cos420.assignment1.service.impl.CustomerRepositoryImpl;
import edu.usm.cos420.assignment1.service.impl.InventoryRepositoryImpl;
import edu.usm.cos420.assignment1.view.impl.CustomerMenuView;
import edu.usm.cos420.assignment1.view.impl.OrderMenuView;

/**
 * A Controller class to execute user's choice for the order menu
 */
public class OrderMenuController implements MenuController {

	private Customer customer;
	private InventoryRepository inventoryRepository;
	private CustomerRepository customerRepository;
	private OrderMenuView view;

	/**
	 * Default constructor. Sets Customer field to null, initializes CustomerRepository and InventoryRepository by default constructors
	 * and OrderMenuView
	 */
	public OrderMenuController() {
		
		this.customer = null;
		this.view = new OrderMenuView();
		this.inventoryRepository = new InventoryRepositoryImpl();
		this.customerRepository = new CustomerRepositoryImpl();
	}

	/**
	 * Constructor: uses provided Customer and OrderRepository
	 * @param customer the Customer the menu is working on
	 * @param inventoryRepository the repository for InventoryItems
	 * @param customerRepository the repository for Customers
	 */
	public OrderMenuController(Customer customer, InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
		this.customer = customer;
		this.inventoryRepository = inventoryRepository;
		this.view = new OrderMenuView();
		this.customerRepository = customerRepository;
	}

	/**
	 * Constructor: uses provided OrderRepository
	 * @param inventoryRepository the repository to use for InventoryItems
	 * @param customerRepository  the repository to use for Customers
	 */
	public OrderMenuController(InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
		this.customer = null;
		this.inventoryRepository = inventoryRepository;
		this.view = new OrderMenuView();
		this.customerRepository = customerRepository;
	}

	/**
	 * Display order menu, get user input, and branch on input
	 */
	@Override
	public void provideMenuAccess() {
		int choice = CustomerMenuView.NO_CHOICE;
		while(choice != CustomerMenuView.EXIT){
			view.setCustomer(customer);
			view.displayMainMenu();
			choice = view.getMainMenuChoice();
			executeChoice(choice);
		}
	}

	/**
	 * Based on {@code choice}, do the desired Order action
	 * @param choice the constant from {@code OrderMenuView} for the desired action
	 */
	@Override
	public void executeChoice(int choice) {
		switch(choice){
		case OrderMenuView.ADD_ORDER:
			addOrder();
			break;
		case OrderMenuView.LIST_ORDERS:
			listOrders();
			break;
		case OrderMenuView.ORDERS_IN_RANGE:
			ordersInRange();
			break;
		default:
			break;
		}

	}

	/**
	 * Add an Order to the current Customer
	 */
	private void addOrder() {
		int orderId = view.getIdInput();
		if(orderId == OrderMenuView.EXIT){
			view.abortAdd();
			return;
		}
		List<InventoryItem> orderItems = new ArrayList<>();
		InventoryItem orderItem = getOrderItem();
		while(orderItem != null){
			orderItems.add(orderItem);
			orderItem = getOrderItem();
		}
		if(orderItems.isEmpty()){
			view.abortAdd();
			return;
		}
		Order newOrder = new Order(orderId, orderItems);
		if(view.orderConfirm(newOrder)){
			customer.addToOrders(newOrder);
			customerRepository.updateCustomer(customer);
			for(InventoryItem item : orderItems){
				InventoryItem currItem = inventoryRepository.findItemById(item.getId());
				currItem.decrementQuantity(item.getQuantity());
				inventoryRepository.updateItem(currItem);
			}
		}
		else{
			view.abortAdd();
		}
	}

	/**
	 * Prompt user for an InventoryItem and check that it exists in the system
	 * @return an existing InventoryItem
	 */
	private InventoryItem getOrderItem(){
		int itemId = view.getItemIdInput();
		if(itemId == OrderMenuView.EXIT){
			return null;
		}
		InventoryItem stockItem;
		while((stockItem = inventoryRepository.findItemById(itemId)) == null) {
			view.itemNotFound(itemId);
			itemId = view.getItemIdInput();
			if(itemId == OrderMenuView.EXIT){
				return null;
			}
		}
		int quantityToOrder = view.getItemQuantityInput();
		if(quantityToOrder == OrderMenuView.EXIT){
			return null;
		}
		while(quantityToOrder > stockItem.getQuantity()){
			quantityToOrder = view.invalidQuantity(quantityToOrder, stockItem.getQuantity());
			if(quantityToOrder == OrderMenuView.EXIT){
				return null;
			}
		}

		InventoryItem orderItem = new InventoryItem(stockItem.getId(), stockItem.getName(), 
				stockItem.getDescription(), quantityToOrder);
		return orderItem;
	}

	private void ordersInRange() {
		int year, month, day;
		LocalDate fromDate = LocalDate.now(),
				toDate = LocalDate.now();
		boolean validDate = false;
		do {
			if((year = view.getYearInput("lower")) == OrderMenuView.EXIT) {
				view.abortLookup();
				return;
			}
			if((month = view.getMonthInput("lower")) == OrderMenuView.EXIT) {
				view.abortLookup();
				return;
			}
			if((day = view.getDayInput("lower")) == OrderMenuView.EXIT) {
				view.abortLookup();
				return;
			}
			try {
				fromDate = LocalDate.of(year, month, day);
				validDate = true;
			}
			catch (DateTimeException e) {
				view.invalidDate(year, month, day);
				validDate = false;
			}
		} while(!validDate);
		
		validDate = false;
		
		do {
			if((year = view.getYearInput("upper")) == OrderMenuView.EXIT) {
				view.abortLookup();
				return;
			}
			if((month = view.getMonthInput("upper")) == OrderMenuView.EXIT) {
				view.abortLookup();
				return;
			}
			if((day = view.getDayInput("upper")) == OrderMenuView.EXIT) {
				view.abortLookup();
				return;
			}
			try {
				toDate = LocalDate.of(year, month, day);
				validDate = true;
			}
			catch (DateTimeException e) {
				view.invalidDate(year, month, day);
				validDate = false;
			}
		} while(!validDate);
		
		if(toDate.isBefore(fromDate)) {
			view.wrongDateOrder(toDate, fromDate);
			return;
		}
		
		List<Order> ordersInRange = new ArrayList<>();
		for(Order order : customer.getOrders()) {
			LocalDate currOrderDate = order.getOrderDate();
			if(currOrderDate.isEqual(toDate) || currOrderDate.isEqual(fromDate)) {
				ordersInRange.add(order);
			}
			else if(currOrderDate.isAfter(fromDate) && currOrderDate.isBefore(toDate)){
				ordersInRange.add(order);				
			}
		}
		if(ordersInRange.isEmpty()) {
			view.noOrdersFound(customer, fromDate, toDate);
			return;
		}
		view.listOrdersInRange(customer, ordersInRange, fromDate, toDate);
	}

	private void listOrders() {
		List<Order> allOrders = customer.getOrders();
		view.listOrders(customer, allOrders);
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
