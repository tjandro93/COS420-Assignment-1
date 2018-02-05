package edu.usm.cos420.assignment1.controller.impl;

import edu.usm.cos420.assignment1.view.impl.CustomerMenuView;

import java.util.List;

import edu.usm.cos420.assignment1.controller.MenuController;
import edu.usm.cos420.assignment1.domain.Customer;
import edu.usm.cos420.assignment1.service.CustomerRepository;
import edu.usm.cos420.assignment1.util.Input;

/**
 * A Controller class to execute user's choice for the Customer menu
 */
public class CustomerMenuController implements MenuController{

	private CustomerMenuView view;
	private CustomerRepository repository;

	/**
	 * Constructor: parameters provided to link View and Repository references
	 * @param view CustomerMenuView for menu UI
	 * @param repository CustomerRepository for data access abstraction
	 */
	public CustomerMenuController(CustomerMenuView view, CustomerRepository repository){
		this.view = view;
		this.repository = repository;
	}

	/**
	 * Constructor: uses provided CustomerRepository, creates a new CustomerMenuView
	 * @param repository to use
	 */
	public CustomerMenuController(CustomerRepository repository){
		this.repository = repository;
		this.view = new CustomerMenuView(repository);
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
	 * Based on {@code choice}, do the desired Customer action
	 * @param choice the constant from {@code CustomerMenuView} for the desired action
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
						repository.addCustomer(newCust);
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
		List<Customer> allCustomers = repository.getAll();
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

			List<Customer> customers = repository.findCustomersByName(custName);
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
		Customer customer = repository.findCustomersById(custId);
		while(customer == null) {
			view.customerNotFound(Integer.toString(custId));
			custId = view.getIdInput(false);
			customer = repository.findCustomersById(custId);
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
				System.out.println("Order menu placeholder, will call new controller");
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

	private Customer editId(Customer customer) {
		int custId = view.getIdInput(true);
		if(custId == CustomerMenuView.EXIT) {
			view.abortEdit("ID field");
		}
		else {
			Customer updatedCustomer = new Customer(custId, customer.getName(), customer.getAddress());
			if(view.confirmEdit(customer, updatedCustomer)) {
				repository.deleteById(customer.getId());
				repository.addCustomer(updatedCustomer);
				return updatedCustomer;
			}
			else {
				view.abortEdit("ID field");
			}
		}
		return customer;
		
		
	}

	private Customer editName(Customer customer) {
		String custName = view.getNameInput();
		if(custName.equals(String.valueOf(CustomerMenuView.EXIT))) {
			view.abortEdit("name field");
		}
		else {
			Customer updatedCustomer = new Customer(customer.getId(), custName, customer.getAddress());
			if(view.confirmEdit(customer, updatedCustomer)) {
				repository.updateCustomer(updatedCustomer);
				return updatedCustomer;
			}
			else {
				view.abortEdit("name field");
				return customer;
			}
			
		}
		return customer;
	}

	private Customer editAddress(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("Edit address placeholder");
		return customer;
	}


}
