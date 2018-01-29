package edu.usm.cos420.assignment1.view.impl;

import edu.usm.cos420.assignment1.util.Input;
import edu.usm.cos420.assignment1.view.MenuView;

/**
 * View class to display and receive inventory menu choices
 */
public class InventoryMenuView implements MenuView {

	/** {@value #NO_CHOICE} : no choice selected by user */
	public static final int NO_CHOICE = -1;
	/** {@value #ADD_ITEM : add an InventoryItem to the system */
	public static final int ADD_ITEM = 1;
	/** {@value #LIST_ITEMS} : list all InventoryItems in the system */
	public static final int LIST_ITEMS = 2;
	/** {@value #EXIT} : exit the inventory menu*/
	public static final int EXIT = 0;
	
	/**
	 * Display menu options to user
	 */
	@Override
	public void displayMenu() {
		System.out.println();
		System.out.println("Enter the number denoting the action desired");
		System.out.println("Add new Inventory Item........" + ADD_ITEM);
		System.out.println("List all Inventory Items......" + LIST_ITEMS);
		System.out.println("Exit.........................." + EXIT);
	}

	/**
	 * Read the menu choice from the user
	 * <p>
	 * Method will not return until a validate choice has been made
	 * @return
	 * <ul>
	 * 	<li>{@value #ADD_ITEM} : Add an InventoryItem to the system
	 *	<li>{@value #LIST_ITEMS} : List all InventoryItems in the system
	 *	<li>{@value #EXIT} : Exit the inventory menu
	 * </ul>
	 */
	@Override
	public int getMenuChoice() {
		int choice = NO_CHOICE;	
		while( choice == NO_CHOICE){
			System.out.print("Enter choice ");
			System.out.flush();
			String choiceStr = Input.readLine();
			choice = validateChoice(choiceStr);
		}
		return choice;
	}
	
	/**
	 * Private method to validate user input of menu choice
	 * @param input the String input by the user
	 * @return
	 * <ul>
	 * 	<li>{@value #ADD_ITEM} : Add an InventoryItem to the system
	 *	<li>{@value #LIST_ITEMS} : List all InventoryItems in the system
	 *	<li>{@value #EXIT} : Exit the inventory menu
	 *	<li>{@value #NO_CHOICE} : returned if input is an invalid action
	 * </ul>
	 */
	private int validateChoice(String input) {
		int choice = NO_CHOICE;
		try{
			choice = Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			System.out.println(input + " is not a valid action");
		}
		if (choice != ADD_ITEM && choice != LIST_ITEMS && choice != EXIT){
			System.out.println(input + " is not a valid action");

		}
		return choice;
	}

}
