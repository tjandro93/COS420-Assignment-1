package edu.usm.cos420.assignment1.controller.impl;

import edu.usm.cos420.assignment1.view.impl.CustomerMenuView;

import java.util.List;

import edu.usm.cos420.assignment1.controller.MenuController;
import edu.usm.cos420.assignment1.domain.Customer;
import edu.usm.cos420.assignment1.service.CustomerRepository;
import edu.usm.cos420.assignment1.service.InventoryRepository;
import edu.usm.cos420.assignment1.util.Input;

/**
 * A Controller class to execute user's choice for the Customer menu
 */
public class CustomerMenuController implements MenuController{

	private CustomerMenuView view;
	private CustomerRepository customerRepository;
	private OrderMenuController orderMenuController;
	private InventoryRepository inventoryRepository;

	/**
	 * Constructor: parameters provided to link View and Repository references
	 * @param view CustomerMenuView for menu UI
	 * @param customerRepository CustomerRepository for data access abstraction
	 * @param inventoryRepository the repository to use for InventoryItems
	 */
	public CustomerMenuController(CustomerMenuView view, CustomerRepository customerRepository, InventoryRepository inventoryRepository){
		this.view = view;
		this.customerRepository = customerRepository;
		this.inventoryRepository = inventoryRepository;
		this.orderMenuController = new OrderMenuController(this.inventoryRepository, this.customerRepository);
	}

	/**
	 * Constructor: uses provided CustomerRepository, creates a new CustomerMenuView
	 * @param customerRepository to use for Customers
	 * @param inventoryRepository the repository to use for InventoryItems
	 */
	public CustomerMenuController(CustomerRepository customerRepository, InventoryRepository inventoryRepository){
		this.customerRepository = customerRepository;
		this.view = new CustomerMenuView(customerRepository);
		this.inventoryRepository = inventoryRepository;
		this.orderMenuController = new OrderMenuController(this.inventoryRepository, this.customerRepository);
	}

	/**
	 * Display main menu, get user input, and branch on input
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
	 * Based on choice, do the desired Customer action
	 * @param choice the constant from CustomerMenuView for the desired action
	 */
	@Override
	public void executeChoice(int choice) {
		switch(choice){
		case CustomerMenuView.ADD_CUSTOMER:
			addCustomer();
			break;
		case CustomerMenuView.FIND_CUSTOMER_NAME:
			findCustomerByName();
			break;
		case CustomerMenuView.FIND_CUSTOMER_ID:
			findCustomerById();
			break;
		case CustomerMenuView.LIST_CUSTOMERS:
			listCustomers();
			break;
		default:
			break;
		}
	}

	/**
	 * Add a customer to the system 
	 */
	public void addCustomer(){
		System.out.println();
		int custId = view.getIdInput(true);
		if(custId != CustomerMenuView.EXIT){
			String custName = view.getNameInput();
			if(!custName.equals(String.valueOf(CustomerMenuView.EXIT))){
				String custAddr = view.getAddressInput();
				if(!custAddr.equals(String.valueOf(CustomerMenuView.EXIT))){
					Customer newCust = new Customer(custId, custName, custAddr);
					System.out.println("You entered\t" + newCust);
					if(Input.getConfirmation()){
						customerRepository.addCustomer(newCust);
						System.out.println("Customer saved");
					}
					else{
						view.abortAdd();
					}
				}
				else{
					view.abortAdd();
				}
			}
		}
		else{
			view.abortAdd();
		}

	}

	/**
	 * List all Customers in the System
	 */
	private void listCustomers(){
		System.out.println();
		List<Customer> allCustomers = customerRepository.getAll();
		view.displayAllCustomers(allCustomers);
	}

	/**
	 * Find a Customer in the System by name
	 */
	private void findCustomerByName() {
		System.out.println();
		String custName = view.getNameInput();
		boolean validCustomer = false;
		while(!validCustomer) {

			if(custName.equals(String.valueOf(CustomerMenuView.EXIT))) {
				view.abortLookup();
				return;
			}

			List<Customer> customers = customerRepository.findCustomersByName(custName);
			if(customers == null || customers.isEmpty()) {
				view.customerNotFound(custName);
			}
			else if(customers.size() > 1) {
				view.multipleCustomersFound(customers);
			}
			else {
				validCustomer = true;
				lookupMenu(customers.get(0));
			}
			custName = view.getNameInput();
		}
	}

	/**
	 * Find a Customer in the system by ID
	 */
	private void findCustomerById() {
		System.out.println();
		int custId = view.getIdInput(false);
		Customer customer = customerRepository.findCustomersById(custId);
		while(customer == null) {
			view.customerNotFound(Integer.toString(custId));
			custId = view.getIdInput(false);
			customer = customerRepository.findCustomersById(custId);
		}
		lookupMenu(customer);
	}

	/**
	 * Provide access to options once a Customer has been looked up in the system
	 * @param customer the customer found by findCustomerByName() or findCustomerByID()
	 */
	private void lookupMenu(Customer customer) {
		int choice = CustomerMenuView.NO_CHOICE;
		while(choice != CustomerMenuView.EXIT) {
			System.out.println();
			view.displayLookupMenu(customer);
			choice = view.getLookupMenuChoice();
			switch(choice) {
			case CustomerMenuView.ORDERS:
				orderMenuController.setCustomer(customer);
				orderMenuController.provideMenuAccess();
				break;
			case CustomerMenuView.EDIT_ID:
				customer = editId(customer);
				break;
			case CustomerMenuView.EDIT_NAME:
				customer = editName(customer);
				break;
			case CustomerMenuView.EDIT_ADDRESS:
				customer = editAddress(customer);
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		}
	}

	/**
	 * Edit the ID field of a customer
	 * @param customer the target to be edited
	 * @return the customer after updating
	 */
	private Customer editId(Customer customer) {
		int custId = view.getIdInput(true);
		if(custId == CustomerMenuView.EXIT) {
			view.abortEdit("ID field");
		}
		else {
			Customer updatedCustomer = new Customer(custId, customer.getName(), customer.getAddress());
			if(view.confirmEdit(customer, updatedCustomer)) {
				customerRepository.deleteById(customer.getId());
				customerRepository.addCustomer(updatedCustomer);
				return updatedCustomer;
			}
			else {
				view.abortEdit("ID field");
			}
		}
		return customer;
		
		
	}

	/**
	 * Edit the name field of a customer
	 * @param customer the target to be edited
	 * @return the customer after updating
	 */
	private Customer editName(Customer customer) {
		String custName = view.getNameInput();
		if(custName.equals(String.valueOf(CustomerMenuView.EXIT))) {
			view.abortEdit("name field");
		}
		else {
			Customer updatedCustomer = new Customer(customer.getId(), custName, customer.getAddress());
			if(view.confirmEdit(customer, updatedCustomer)) {
				customerRepository.updateCustomer(updatedCustomer);
				return updatedCustomer;
			}
			else {
				view.abortEdit("name field");
			}
			
		}
		return customer;
	}

	/**
	 * Edit the address field of a customer
	 * @param customer the target to be edited
	 * @return the customer after editing
	 */
	private Customer editAddress(Customer customer) {
		String custAddr = view.getAddressInput();
		if(custAddr.equals(String.valueOf(CustomerMenuView.EXIT))) {
			view.abortEdit("address field");
		}
		else{
			Customer updatedCustomer = new Customer(customer.getId(), customer.getName(), custAddr);
			if(view.confirmEdit(customer, updatedCustomer)){
				customerRepository.updateCustomer(updatedCustomer);
				return updatedCustomer;
			}
			else{
				view.abortEdit("address field");
			}
		}
		return customer;
	}


}
