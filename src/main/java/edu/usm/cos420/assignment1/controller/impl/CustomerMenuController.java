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
			view.displayMenu();
			choice = view.getMenuChoice();
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
		case CustomerMenuView.FIND_CUSTOMER:
			System.out.println("Find customer placeholder");
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

}
