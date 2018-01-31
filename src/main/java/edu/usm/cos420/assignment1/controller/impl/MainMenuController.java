package edu.usm.cos420.assignment1.controller.impl;

import edu.usm.cos420.assignment1.controller.MenuController;
import edu.usm.cos420.assignment1.service.CustomerRepository;
import edu.usm.cos420.assignment1.service.impl.CustomerRepositoryImpl;
import edu.usm.cos420.assignment1.view.impl.MainMenuView;

/**
 * A Controller class to execute user's menu choice on the Main Menu
 * <p>
 * Based on choice, delegates to sub-controllers
 */
public class MainMenuController implements MenuController {

	private CustomerRepository customerRepository;

	private MainMenuView mainMenuView;
	private CustomerMenuController customerMenuController;
	private InventoryMenuController inventoryMenuController;

	/**
	 * Default Constructor, initializes all required fields appropriately 
	 */
	public MainMenuController(){
		this.mainMenuView = new MainMenuView();
		this.customerRepository = new CustomerRepositoryImpl();
		this.customerMenuController = new CustomerMenuController(customerRepository);
		this.inventoryMenuController = new InventoryMenuController();
	}

	/**
	 * Constructor: takes a {@code view.impl.MainMenuView} to provide menu UI
	 * @param mainMenuView view for menu UI
	 */
	public MainMenuController(MainMenuView mainMenuView){
		this.mainMenuView = mainMenuView;
		this.customerRepository = new CustomerRepositoryImpl();
		this.customerMenuController = new CustomerMenuController(customerRepository);
		this.inventoryMenuController = new InventoryMenuController();
	}

	/**
	 * Display main menu, get user input, and branch on input
	 */
	@Override
	public void provideMenuAccess(){
		int choice = MainMenuView.NO_CHOICE;
		while(choice != MainMenuView.EXIT){
			mainMenuView.displayMenu();
			choice = mainMenuView.getMenuChoice();
			executeChoice(choice);
		}
	}

	/**
	 * Based on {@code choice}, go to the desired sub-menu
	 * @param choice the constant from {@code MainMenuView} for the desired sub-menu
	 */
	@Override
	public void executeChoice(int choice){
		switch(choice){
		case MainMenuView.CUSTOMER_MENU:
			customerMenuController.provideMenuAccess();
			break;
		case MainMenuView.INVENTORY_MENU:
			inventoryMenuController.provideMenuAccess();
			break;
		case MainMenuView.ORDER_MENU:
			System.out.println("Order Menu PLaceholder");
			break;
		default:
			break;

		}
	}
}
