package edu.usm.cos420.assignment1.controller;

import edu.usm.cos420.assignment1.view.impl.CustomerMenuView;

import java.util.Scanner;

import edu.usm.cos420.assignment1.service.CustomerRepository;

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
			System.out.println("Add customer placeholder");
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


}
