package edu.usm.cos420.assignment1.controller;

import edu.usm.cos420.assignment1.service.CustomerService;
import edu.usm.cos420.assignment1.view.impl.MainMenuView;

/**
 * A Controller class to execute user's menu choice on the Main Menu
 * <p>
 * Based on choice, delegates to subcontrollers
 */
public class MainMenuController {

	//TODO finish MainMenuController
	private MainMenuView mainMenuView;

	/**
	 * Constructor: takes a {@code view.impl.MainMenuView} to provide menu UI
	 * @param mainMenuView view for menu UI
	 */
	public MainMenuController(MainMenuView mainMenuView){
		this.mainMenuView = mainMenuView;
	}

	public void provideMainMenuAccess(){
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
			System.out.println("Customer Menu placeholder");
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
