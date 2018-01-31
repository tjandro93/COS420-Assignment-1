package edu.usm.cos420.assignment1.view.impl;

import edu.usm.cos420.assignment1.util.Input;
import edu.usm.cos420.assignment1.view.MenuView;

/**
 * View class to handle main menu input and output
 * <p>
 * This view is to be attached to {@link edu.usm.cos420.assignment1.controller.MainMenuController}
 */
public class MainMenuView implements MenuView{

	/** {@value #NO_CHOICE} : no choice selected by user */
	public static final int NO_CHOICE = -1;
	/** {@value #CUSTOMER_MENU} : access customer sub-menu */
	public static final int CUSTOMER_MENU = 1;
	/** {@value #INVENTORY_MENU} : access inventory sub-menu*/
	public static final int INVENTORY_MENU = 2;
	/** {@value #ORDER_MENU} : access order sub-menu*/
	public static final int ORDER_MENU = 3;
	/** {@value #EXIT} : exit the main menu (closing the application)*/
	public static final int EXIT = 0;

	/**
	 * Default constructor
	 */
	public MainMenuView(){	}

	/**
	 * Display menu options to user
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("Enter the number denoting the action desired");
		System.out.println("Customer Menu................." + CUSTOMER_MENU);
		System.out.println("Inventory Menu................" + INVENTORY_MENU);
		System.out.println("Order Menu...................." + ORDER_MENU);
		System.out.println("Exit.........................." + EXIT);
	}

	/**
	 * Read the menu choice from the user
	 * <p>
	 * Method will not return until a validate choice has been made
	 * @return
	 * <ul>
	 * 	<li>{@value #CUSTOMER_MENU} : Display the customer menu
	 *	<li>{@value #INVENTORY_MENU} : Display the inventory menu
	 *	<li>{@value #ORDER_MENU} : Display the order menu
	 *	<li>{@value #EXIT} : Exit the application
	 * </ul>
	 */
	public int getMenuChoice() {
		int choice = NO_CHOICE;
		while(choice == NO_CHOICE){
			System.out.print("Enter choice ");
			System.out.flush();
			String choiceStr = Input.readLine();
			choice = validateChoice(choiceStr);
		}
		return choice;
	}

	/**
	 * Private method to validate user input of menu choice
	 * 
	 * @param userInput the String input by the user
	 * @return
	 * * <ul>
	 * 	<li>{@value #CUSTOMER_MENU} : Display the customer menu
	 *	<li>{@value #INVENTORY_MENU} : Display the inventory menu
	 *	<li>{@value #ORDER_MENU} : Display the order menu
	 *	<li>{@value #EXIT} : Exit the application
	 *  <li>{@value #NO_CHOICE} : returned if {@code userInput} is an invalid choice
	 * </ul>
	 */
	private int validateChoice(String userInput){
		int choice = NO_CHOICE;
		try {
			choice = Integer.parseInt(userInput);
		}
		catch(NumberFormatException e){
			System.out.println(userInput + " is not a valid action");
		}
		if (choice != CUSTOMER_MENU && choice != INVENTORY_MENU && choice != ORDER_MENU && choice != EXIT){
			System.out.println(userInput + " is not a valid action");

		}
		return choice;

	}
}
