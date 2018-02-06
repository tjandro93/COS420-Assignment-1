package edu.usm.cos420.assignment1.controller.impl;

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
import edu.usm.cos420.assignment1.service.impl.OrderRepositoryImpl;
import edu.usm.cos420.assignment1.view.impl.CustomerMenuView;
import edu.usm.cos420.assignment1.view.impl.OrderMenuView;

/**
 * A Controller class to execute user's choice for the order menu
 */
public class OrderMenuController implements MenuController {

	//TODO refactor to remove InventoryRepository
	private Customer customer;
	private OrderRepository orderRepository;
	private InventoryRepository inventoryRepository;
	private CustomerRepository customerRepository;
	private OrderMenuView view;

	/**
	 * Default constructor. Sets Customer field to null, initializes OrderRepository 
	 * and OrderMenuView
	 */
	public OrderMenuController() {
		this.customer = null;
		this.orderRepository = new OrderRepositoryImpl();
		this.view = new OrderMenuView(this.orderRepository);
		this.inventoryRepository = new InventoryRepositoryImpl();
		this.customerRepository = new CustomerRepositoryImpl();
	}

	/**
	 * Constructor: uses provided Customer and OrderRepository
	 * @param customer the Customer the menu is working on
	 * @param orderRepository the repository for Orders
	 * @param inventoryRepository the repository for InventoryItems
	 * @param customerRepository the repository for Customers
	 */
	public OrderMenuController(Customer customer, OrderRepository orderRepository, InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
		this.customer = customer;
		this.orderRepository = orderRepository;
		this.inventoryRepository = inventoryRepository;
		this.view = new OrderMenuView(this.orderRepository);
		this.customerRepository = customerRepository;
	}

	/**
	 * Constructor: uses provided OrderRepository
	 * @param orderRepository the repository to use for Orders
	 * @param inventoryRepository the repository to use for InventoryItems
	 */
	public OrderMenuController(OrderRepository orderRepository, InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
		this.customer = null;
		this.inventoryRepository = inventoryRepository;
		this.orderRepository = orderRepository;
		this.view = new OrderMenuView(this.orderRepository);
		this.customerRepository = customerRepository;
	}

	/**
	 * Display order menu, get user input, and branch on input
	 */
	@Override
	public void provideMenuAccess() {
		int choice = CustomerMenuView.NO_CHOICE;
		while(choice != CustomerMenuView.EXIT){
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
		int orderId = view.getIdInput(true);
		if(orderId == OrderMenuView.EXIT){
			view.abortAdd();
			return;
		}
		List<InventoryItem> orderItems = new ArrayList<>();
		InventoryItem itemToAdd = getOrderItem();
		while(itemToAdd != null){
			orderItems.add(itemToAdd);
			itemToAdd = getOrderItem();
		}
		if(orderItems.isEmpty()){
			view.abortAdd();
			return;
		}
		Order newOrder = new Order(orderId, orderItems);
		if(view.orderConfirm(newOrder)){
			customer.addToOrders(newOrder);
			customerRepository.updateCustomer(customer);
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
		InventoryItem inventoryItem;
		while((inventoryItem = inventoryRepository.findItemById(itemId)) == null) {
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
		while(quantityToOrder > inventoryItem.getQuantity()){
			quantityToOrder = view.invalidQuantity(quantityToOrder, inventoryItem.getQuantity());
			if(quantityToOrder == OrderMenuView.EXIT){
				return null;
			}
		}
		
		InventoryItem orderItem = new InventoryItem(inventoryItem.getId(), inventoryItem.getName(), 
				inventoryItem.getDescription(), quantityToOrder);
		
		return orderItem;
	}

	private void ordersInRange() {
		System.out.println("order in range placeholder");
	}

	private void listOrders() {
		System.out.println("List orders placeholder");	
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