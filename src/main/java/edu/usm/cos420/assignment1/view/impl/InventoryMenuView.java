package edu.usm.cos420.assignment1.view.impl;

import java.util.List;

import edu.usm.cos420.assignment1.domain.InventoryItem;
import edu.usm.cos420.assignment1.service.InventoryRepository;
import edu.usm.cos420.assignment1.service.impl.InventoryRepositoryImpl;
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

	private InventoryRepository repository;

	public InventoryMenuView(){
		this.repository = new InventoryRepositoryImpl();
	}

	public InventoryMenuView(InventoryRepository repository){
		this.repository = repository;
	}

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

	/**
	 * Display abort message for adding InventoryItems
	 */
	public void abortAdd() {
		System.out.println("Add item aborted");
	}

	/**
	 * Prompt user to enter a Customer ID
	 */
	public void displayIdPrompt(){
		System.out.println("Enter 6-digit Customer ID or 0 to abort");
	}

	/**
	 * Get user input for an InventoryItem ID
	 * @return either a valid InventoryItem ID 
	 * or {@code #EXIT} if the user wishes to abort the operation
	 */
	public int getNewId(){
		int idInt;
		String idStr = Input.readLine();
		while((idInt = validateNewId(idStr)) == NO_CHOICE){
			idStr = Input.readLine();
		}
		return idInt;
	}

	/**
	 * Validate the input InventoryItem ID to ensure it is 6 Digits
	 * and is not already being used
	 * @param idStr the input from the user
	 * @return a valid ID number
	 *  or {@code #EXIT} if the user wishes to abort the operation
	 */
	private int validateNewId(String idStr){
		int idNum = NO_CHOICE;
		if(idStr.equals("0")){
			return EXIT;
		}
		if (idStr.length() == 6){
			try{
				idNum = Integer.parseInt(idStr);
			}
			catch (NumberFormatException e){
				System.out.println(idStr + " is not a valid ID");
			}
		}
		else{
			System.out.println(idStr + " is not a valid ID");
			return NO_CHOICE;
		}
		if(repository.findItemById(idNum) != null){
			System.out.println("An item with ID " + idNum + " is already in the system");
			return NO_CHOICE;
		}
		else{

			return idNum;
		}
	}

	/**
	 * Prompt user to enter an InventoryItem name
	 */
	public void displayNamePrompt() {
		System.out.println("Enter a name for the item or 0 to abort");
	}

	/**
	 * Get user input for an InventoryItem name. Will not return until a nonempty string is input
	 * @return a valid InventoryItem name
	 */
	public String getNewName(){
		String itemName = null;
		while(itemName == null){
			itemName = Input.readLine();
			if(itemName.length() == 0){
				System.out.println("You must provide a name");
				itemName = null;
			}
		}
		return itemName;
	}

	/**
	 * Prompt user to enter a description for the InventoryItem
	 */
	public void displayInfoPrompt() {
		System.out.println("Enter a short description of the item or 0 to abort");
	}

	/**
	 * Get user input for an InventoryItem description. Will not return until a nonempty string is input
	 * @return a valid InventoryItem descritption
	 */
	public String getNewInfo(){
		String itemInfo = null;
		while(itemInfo == null){
			itemInfo = Input.readLine();
			if(itemInfo.length() == 0){
				System.out.println("You must provide a name");
				itemInfo = null;
			}
		}
		return itemInfo;
	}

	/**
	 * Prompt user to input a quantity for the InventoryItem
	 */
	public void displayQuantityPrompt() {
		System.out.println("Enter the initial quantity for the item or 0 to abort");
	}
	
	/**
	 * Get user input for the initial stock quantity for the InventoryItem
	 * @return the quantity for the InventoryItem
	 */
	public int getNewQuantity(){
		int quantityInt;
		String quantityStr = Input.readLine();
		while((quantityInt = validateQuantity(quantityStr)) == NO_CHOICE){
			quantityStr = Input.readLine();
		}
		return quantityInt;
		
	}

	/**
	 * Validate the input InventoryItem quantity to ensure it is positive
	 * @param quantityStr the input from the user
	 * @return a valid quantity, 
	 * 	{@code #EXIT} if the user wishes to abort the operation,
	 * 	or {@code #NO_CHOICE} if the input is invalid
	 */
	private int validateQuantity(String quantityStr) {
		try{
			int quantityInt = Integer.parseInt(quantityStr);
			if(quantityInt < 0){
				return NO_CHOICE;
			}
			else{
				return quantityInt;
			}
		}
		catch (NumberFormatException e){
			return NO_CHOICE;
		}
		
	}
	
	/**
	 * 
	 * @param items
	 */
	public void displayAllItems(List<InventoryItem> items){
		System.out.println(items.size() + " items:");
		for(InventoryItem i : items){
			System.out.println(i.toString());
		}
	}
}
