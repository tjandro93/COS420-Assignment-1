package edu.usm.cos420.assignment1.controller;

import java.util.Scanner;

import edu.usm.cos420.assignment1.service.CustomerRepository;
import edu.usm.cos420.assignment1.service.impl.CustomerRepositoryImpl;
import edu.usm.cos420.assignment1.view.impl.MainMenuView;

/**
 * A Controller class to execute user's menu choice on the Main Menu
 * <p>
 * Based on choice, delegates to sub-controllers
 */
public class MainMenuController implements MenuController {

	private Scanner in;
	private CustomerRepository customerRepository;
	
	private MainMenuView mainMenuView;
	private CustomerMenuController customerMenuController;
	
	/**
	 * Default Constructor, initializes all required fields appropriately 
	 */
	public MainMenuController(){
		this.in = new Scanner(System.in);
		this.mainMenuView = new MainMenuView(in);
		this.customerRepository = new CustomerRepositoryImpl();
		this.customerMenuController = new CustomerMenuController(customerRepository, in);
	}
	
	/**
	 * Constructor: takes a {@code view.impl.MainMenuView} to provide menu UI
	 * @param mainMenuView view for menu UI
	 */
	public MainMenuController(MainMenuView mainMenuView){
		this.mainMenuView = mainMenuView;
		this.in = mainMenuView.getScanner();
		this.customerRepository = new CustomerRepositoryImpl();
		this.customerMenuController = new CustomerMenuController(customerRepository, in);
	}


	public void provideMenuAccess(){
		int choice = MainMenuView.NO_CHOICE;
		while(choice != MainMenuView.EXIT){
			mainMenuView.displayMenu();
			choice = mainMenuView.getMenuChoice();
			executeChoice(choice);
		}
	}


	public void executeChoice(int choice){
		switch(choice){
		case MainMenuView.CUSTOMER_MENU:
			customerMenuController.provideMenuAccess();
			break;
		case MainMenuView.INVENTORY_MENU:
			System.out.println("Inventory Menu Placeholder");
			break;
		case MainMenuView.ORDER_MENU:
			System.out.println("Order Menu PLaceholder");
			break;
		default:
			break;

		}
	}
}
