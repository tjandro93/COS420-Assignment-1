package edu.usm.cos420.assignment1.view.impl;

import java.util.List;

import edu.usm.cos420.assignment1.domain.InventoryItem;
import edu.usm.cos420.assignment1.domain.Order;
import edu.usm.cos420.assignment1.service.OrderRepository;
import edu.usm.cos420.assignment1.service.impl.OrderRepositoryImpl;
import edu.usm.cos420.assignment1.util.Input;
import edu.usm.cos420.assignment1.view.MenuView;
import edu.usm.cos420.assignment1.domain.Customer;

/**
 * View class to display and receive order menu choices
 */
public class OrderMenuView implements MenuView {

	/** {@value #NO_CHOICE } : no choice selected by user*/
	public static final int NO_CHOICE = -1;
	/** {@value #ADD_ORDER } : create a new order for the customer*/
	public static final int ADD_ORDER = 1;
	/** {@value #LIST_ORDERS } : list all orders for the customer*/
	public static final int LIST_ORDERS = 2;
	/** {@value #ORDERS_IN_RANGE } : list all orders in a date range for the customer*/
	public static final int ORDERS_IN_RANGE = 3;
	/** {@value #EXIT } : exit the customer menu (returning to the main menu)*/
	public static final int EXIT = 0;
	
	private OrderRepository repository;
	
	/**
	 * Default constructor: creates a new OrderRepository
	 */
	public OrderMenuView(){
		this.repository = new OrderRepositoryImpl();
	}
	
	/**
	 * Constructor: uses the supplied OrderRepository
	 * @param repository the repository to use
	 */
	public OrderMenuView(OrderRepository repository){
		this.repository = repository;
	}
	
	/**
	 * Display menu options to user
	 */
	@Override
	public void displayMainMenu() {
		System.out.println("Add order....................." + ADD_ORDER);
		System.out.println("List all orders..............." + LIST_ORDERS);
		System.out.println("Find orders by date..........." + ORDERS_IN_RANGE);
		System.out.println("Exit.........................." + EXIT);
	}

	/**
	 * Read the menu choice from the user
	 * <p>
	 * Method will not return until a valid choice has been made
	 * @return
	 * <ul>
	 * <li> {@value #ADD_ORDER} : add an order to the system for the customer
	 * <li> {@value #LIST_ORDERS} : list all orders for the customer
	 * <li> {@value #ORDERS_IN_RANGE} : list orders in date range
	 * <li> {@value #EXIT} : exit the order menu
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
	 * <li> {@value #ADD_ORDER} : add an order to the system for the customer
	 * <li> {@value #LIST_ORDERS} : list all orders for the customer
	 * <li> {@value #ORDERS_IN_RANGE} : list orders in date range
	 * <li> {@value #NO_CHOICE} : returned if input is an invalid action
	 * <li> {@value #EXIT} : exit the order menu
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
		if (choice != ADD_ORDER && choice != LIST_ORDERS &&choice != ORDERS_IN_RANGE && choice != EXIT){
			System.out.println(input + " is not a valid action");

		}
		return choice;
	}
	
	/**
	 * Get user input for an Order ID
	 * @param checkId set to true to check the ID is not yet used
	 * @return either a valid Order ID or 
	 * {@value #EXIT} if the user wishes to abort the operation
	 */
	public int getIdInput(boolean checkId){
		System.out.println("Enter 6-digit Order ID or 0 to abort");
		int idInt;
		String idStr = Input.readLine();
		while((idInt = validateId(idStr, checkId)) == NO_CHOICE){
			idStr = Input.readLine();
		}
		return idInt;
	}

	/**
	 * Validate the input Order ID to ensure it is 6 Digits
	 * and is not already being used
	 * @param idStr the input from the user
	 * @param checkId set to true to check the ID is not yet used
	 * @return a valid ID number,
	 *  {@code #EXIT} if the user wishes to abort the operation,
	 *  or {@code #NO_CHOICE} if the input is invalid
	 */
	private int validateId(String idStr, boolean checkId) {
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
		if(checkId && repository.findOrdersById(idNum) != null){
			System.out.println("An Order with ID " + idNum + " is already in the system");
			return NO_CHOICE;
		}
		else{

			return idNum;
		}
	}

	/**
	 * Inform user they are aborting an Order add
	 */
	public void abortAdd(){
		System.out.println("Add order aborted");
	}
	
	/**
	 * Get user input for an InventoryItem to add to the Order
	 * @return a validly formatted InventoryItem ID. 
	 * Will not have been checked for existence in the InventoryRepository.
	 * May return {@value #EXIT} if the user wishes to abort the Order
	 */
	public int getItemIdInput(){
		System.out.println("Enter an item ID to order or 0 to abort");
		int itemId;
		String idStr = Input.readLine();
		while((itemId = validateId(idStr, false)) == NO_CHOICE){
			idStr = Input.readLine();
		}
		return itemId;
	}
	
	/**
	 * Report to user that the InventoryItem could not be found
	 * @param itemId the ID of the InventoryItem
	 */
	public void itemNotFound(int itemId){
		System.out.println("No item with ID " + itemId + " could be found");
	}

	/**
	 * Prompt user for quantity of an item to order
	 * @return the quantity to order or {@value #NO_CHOICE} if they wish to abort
	 */
	public int getItemQuantityInput() {
		System.out.println("Enter the quantity to order or 0 to abort");
		int itemQuantity;
		String idStr = Input.readLine();
		while((itemQuantity = validateQuantity(idStr)) == NO_CHOICE) {
			idStr = Input.readLine();
		}
		return itemQuantity;
	}

	/**
	 * Check to make sure that the quantity requested is valid
	 * @param idStr the user input
	 * @return a valid quantity or {@value #NO_CHOICE} if it is invalid
	 */
	private int validateQuantity(String idStr) {
		int quantity = NO_CHOICE;
		try{
			quantity = Integer.parseInt(idStr);
		}
		catch(NumberFormatException e){
			System.out.println(idStr + " is not a valid quantity");
		}
		if(quantity < 0){
			System.out.println(quantity + " is not a valid quantity");
			quantity = NO_CHOICE;
		}
		return quantity;
	}

	/**
	 * Report to user that the quantity requested is invalid
	 * @param quantityToOrder the quantity requested
	 * @param quantityInStock the quantity actually in stock
	 * @return calls {@code getItemQuantityInput()} to attempt to get valid input
	 */
	public int invalidQuantity(int quantityToOrder, int quantityInStock) {
		System.out.println("Cannot order " + quantityToOrder + ", only " + quantityInStock + " in stock");
		return getItemQuantityInput();
	}

	public boolean orderConfirm(Order newOrder) {
		System.out.println("Really place order?\n" + newOrder.toString());
		return Input.getConfirmation();
	}

	public void listOrders(Customer customer, List<Order> allOrders) {
		System.out.println();
		System.out.println("Orders for  " + customer.toString());
		for(Order order : allOrders) {
			System.out.println(order.toString());
		}
	}
}
