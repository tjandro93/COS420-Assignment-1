package edu.usm.cos420.assignment1.controller;

import edu.usm.cos420.assignment1.view.impl.CustomerMenuView;
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
			System.out.println("List customer placeholder");
			break;
		default:
			break;
		}
	}
	
	public void addCustomer(){
		System.out.println();
		System.out.println("Add customer placeholder wip");
		view.displayIdPrompt();
		int custId = view.getNewId();
		if(custId != CustomerMenuView.EXIT){
			view.displayNamePrompt();
			String custName = view.getNewName();
			if(!custName.equals(String.valueOf(CustomerMenuView.EXIT))){
				view.displayAddressPrompt();
				String custAddr = view.getNewAddress();
				if(!custAddr.equals(String.valueOf(CustomerMenuView.EXIT))){
					Customer newCust = new Customer(custId, custAddr, custName);
					System.out.println("You entered\t" + newCust);
					if(Input.getConfirmation()){
						repository.addCustomer(newCust);
						System.out.println("Customer saved");
					}
					else{
						System.out.println("Add customer aborted");
					}
				}
				else{
					System.out.println("Add customer aborted");
				}
			}
		}
		else{
			System.out.println("Add customer aborted");
		}
		
	}


}
