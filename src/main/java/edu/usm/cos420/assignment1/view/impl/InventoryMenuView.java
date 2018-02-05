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
	/** {@value #EDIT_ITEM} : edit an InventoryItem in the system */
	public static final int EDIT_ITEM = 3;
	/** {@value #EDIT_ID} : edit an InventoryItem's ID field */
	public static final int EDIT_ID = 4;
	/** {@value #EDIT_NAME} : edit an InventoryItem's name field */
	public static final int EDIT_NAME = 5;
	/** {@value #EDIT_INFO} : edit an InventoryItem's description field */
	public static final int EDIT_INFO = 7;
	/** {@value #EDIT_QUANTITY} : edit an InventoryItem's quantity field */
	public static final int EDIT_QUANTITY = 6;
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
	 * Display main menu options to user
	 */
	@Override
	public void displayMainMenu() {
		System.out.println();
		System.out.println("Enter the number denoting the action desired");
		System.out.println("Add new Inventory Item........" + ADD_ITEM);
		System.out.println("List all Inventory Items......" + LIST_ITEMS);
		System.out.println("Edit an Inventory Item........" + EDIT_ITEM);
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
	 *	<li>{@value #EDIT_ITEM} : Edit an InventoryItem in the system
	 *	<li>{@value #EXIT} : Exit the inventory menu
	 * </ul>
	 */
	@Override
	public int getMainMenuChoice() {
		int choice = NO_CHOICE;	
		while( choice == NO_CHOICE){
			System.out.print("Enter choice ");
			System.out.flush();
			String choiceStr = Input.readLine();
			choice = validateMainMenuChoice(choiceStr);
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
	 *	<li>{@value #EDIT_ITEM} : Edit an InventoryItem in the system
	 *	<li>{@value #EXIT} : Exit the inventory menu
	 *	<li>{@value #NO_CHOICE} : returned if input is an invalid action
	 * </ul>
	 */
	private int validateMainMenuChoice(String input) {
		int choice = NO_CHOICE;
		try{
			choice = Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			System.out.println(input + " is not a valid action");
		}
		if (choice != ADD_ITEM && choice != LIST_ITEMS && choice != EXIT && choice != EDIT_ITEM){
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
	 * Prompt and get user input for an InventoryItem ID
	 * @param isNewItem set to true if the ID should be unused
	 * @return either a valid InventoryItem ID 
	 * or {@code #EXIT} if the user wishes to abort the operation
	 */
	public int getIdInput(boolean isNewItem){
		System.out.println("Enter 6-digit Item ID or 0 to abort");
		int idInt;
		String idStr = Input.readLine();
		while((idInt = validateId(idStr, isNewItem)) == NO_CHOICE){
			idStr = Input.readLine();
		}
		return idInt;
	}

	/**
	 * Validate the input InventoryItem ID to ensure it is 6 Digits
	 * and is (optionally) not already being used
	 * @param idStr the input from the user
	 * @param isNewItem set to true if the ID should be unique
	 * @return a valid ID number
	 *  or {@code #EXIT} if the user wishes to abort the operation
	 */
	private int validateId(String idStr, boolean isNewItem){
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
		if(isNewItem && repository.findItemById(idNum) != null){
			System.out.println("An item with ID " + idNum + " is already in the system");
			return NO_CHOICE;
		}
		else{

			return idNum;
		}
	}

	/**
	 * Get user input for an InventoryItem name. Will not return until a nonempty string is input
	 * @return a valid InventoryItem name
	 */
	public String getNameInput(){
		System.out.println("Enter a name for the item or 0 to abort");
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
	 * Get user input for an InventoryItem description. Will not return until a nonempty string is input
	 * @return a valid InventoryItem description
	 */
	public String getInfoInput(){
		System.out.println("Enter a short description of the item or 0 to abort");
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
	 * Get user input for the initial stock quantity for the InventoryItem
	 * @return the quantity for the InventoryItem
	 */
	public int getQuantityInput(){
		System.out.println("Enter the initial quantity for the item or 0 to abort");
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
	 * Display a list of InventoryItems
	 * @param items the InventoryItems to be displayed
	 */
	public void displayAllItems(List<InventoryItem> items){
		System.out.println(items.size() + " items:");
		for(InventoryItem i : items){
			System.out.println(i.toString());
		}
	}

	/**
	 * Display abort message for editing InventoryItems
	 * @param optional detail message. Set to null for default message
	 */
	public void abortEdit(String message) {
		if(message == null){
			System.out.println("Edit item aborted");
		}
		else{
			System.out.println("Edit " + message + " aborted");
		}
	}

	/**
	 * Inform user that the desired InventoryItem could not be found
	 * @param idNum the ID of the InventoryItem being searched for
	 */
	public void itemNotFound(int idNum){
		System.out.println("No item with ID " + idNum + " could be found");
	}

	/**
	 * Display information about an InventoryItem
	 * @param item the InventoryItem being displayed
	 */
	public void displayItem(InventoryItem item){
		System.out.println(item.toString());
	}

	/**
	 * Display edit menu options to user
	 */
	public void displayEditMenu(){
		System.out.println("Edit ID......................." + EDIT_ID);
		System.out.println("Edit name....................." + EDIT_NAME);
		System.out.println("Edit quantity................." + EDIT_QUANTITY);
		System.out.println("Edit info....................." + EDIT_INFO);
		System.out.println("Exit.........................." + EXIT);
	}

	/**
	 * Read the menu choice from the user
	 * <p>
	 * Method will not return until a validate choice has been made
	 * @return
	 * <ul>
	 * 	<li>{@value #EDIT_ID} : edit the id field
	 *	<li>{@value #EDIT_NAME} : edit the name field
	 *  <li>{@value #EDIT_INFO} : edit the description field
	 *  <li>{@value #EDIT_QUANTITY} : edit the quantity field
	 *	<li>{@value #EXIT} : Exit the edit menu
	 * </ul>
	 */
	public int getEditMenuChoice() {
		int choice = NO_CHOICE;	
		while( choice == NO_CHOICE){
			System.out.print("Enter choice ");
			System.out.flush();
			String choiceStr = Input.readLine();
			choice = validateEditMenuChoice(choiceStr);
		}
		return choice;
	}

	/**
	 * Private method to validate user input of menu choice
	 * @param input the String input by the user
	 * @return
	 * <ul>
	 * 	<li>{@value #EDIT_ID} : edit the id field
	 *	<li>{@value #EDIT_NAME} : edit the name field
	 *  <li>{@value #EDIT_INFO} : edit the description field
	 *  <li>{@value #EDIT_QUANTITY} : edit the quantity field
	 *	<li>{@value #EXIT} : Exit the inventory menu
	 *	<li>{@value #NO_CHOICE} : returned if input is an invalid action
	 * </ul>
	 */
	private int validateEditMenuChoice(String input) {
		int choice = NO_CHOICE;
		try{
			choice = Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			System.out.println(input + " is not a valid action");
		}
		if (choice != EDIT_ID && choice != EDIT_NAME && choice != EDIT_INFO && choice != EDIT_QUANTITY && choice != EXIT){
			System.out.println(input + " is not a valid action");

		}
		return choice;
	}

	/**
	 * Prompt user to confirm changes to an InventoryItem
	 * @param item the original version of the InventoryItem
	 * @param updatedItem the updated version of the InventoryItem
	 * @return true if the user wishes to commit changes
	 */
	public boolean confirmEdit(InventoryItem item, InventoryItem updatedItem) {
		System.out.println(String.format("Really change?\nOriginal: %s\nNew: %s", 
				item.toString(), updatedItem.toString()));
		return Input.getConfirmation();
	}
}
