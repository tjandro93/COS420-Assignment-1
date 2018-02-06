package edu.usm.cos420.assignment1.view.impl;

import java.time.LocalDate;
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
	private Customer currentCustomer;
	
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
	
	public Customer getCustomer(){
		return this.currentCustomer;
	}
	
	public void setCustomer(Customer c){
		this.currentCustomer = c;
	}
	
	/**
	 * Display menu options to user
	 */
	@Override
	public void displayMainMenu() {
		System.out.println();
		System.out.println("Customer " + currentCustomer.toString());
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
		System.out.println("Orders for Customer " + customer.toString());
		for(Order order : allOrders) {
			System.out.println(order.toString());
		}
	}

	/**
	 * Report to user abort of lookup operation
	 */
	public void abortLookup() {
		System.out.println("Lookup aborted");
	}

	/**
	 * Prompt and get a year for the order to lookup
	 * @return a valid year for an Order, or {@value #EXIT} if the user wants to exit
	 */
	public int getYearInput(String bound) {
		System.out.println("Enter the "+ bound + " bound for year 0 to abort");
		int year;
		String yearStr = Input.readLine();
		while((year = validateDate(yearStr, LocalDate.MIN.getYear(), LocalDate.MAX.getYear())) == NO_CHOICE) {
			System.out.println(yearStr + " is not a valid year");
			yearStr = Input.readLine();
		}
		return year;
	}

	/**
	 * Prompt and get a month for the order to lookup
	 * @return a valid month for an Order, or {@value #EXIT} if the user wants to exit
	 */
	public int getMonthInput(String bound) {
		System.out.println("Enter the " + bound + " bound for month 0 to abort");
		int month;
		String monthStr = Input.readLine();
		while((month = validateDate(monthStr, 0, 12)) == NO_CHOICE) {
			System.out.println(monthStr + " is not a valid month");
			monthStr = Input.readLine();
		}
		return month;
	}

	public int getDayInput(String bound) {
		System.out.println("Enter the " + bound + " bound for day or 0 to abort");
		int day;
		String dayStr = Input.readLine();
		while((day = validateDate(dayStr, 0, 31)) == NO_CHOICE) {
			System.out.println(dayStr + " is not a valid day");
			dayStr = Input.readLine();
		}
		return day;
	}
	
	/**
	 * Check if the input String is a valid number between lowValue and highValue
	 * return the value as an int if it is, otherwise return {@value #NO_CHOICE}
	 * 
	 * @param yearStr the input to parse
	 * @param lowValue the lowest valid value for the date (inclusive)
	 * @param highValue the highest valid value for the date (inclusive)
	 * @return a valid date int between lowValue and highValue, 
	 * or {@value #NO_CHOICE} if the input is invalid
	 */
	private int validateDate(String yearStr, int lowValue, int highValue) {
		int dateInt = NO_CHOICE;
		try {
			dateInt = Integer.parseInt(yearStr);
		}
		catch(NumberFormatException e){
			return NO_CHOICE;
		}
		if(dateInt < lowValue || dateInt > highValue) {
			return NO_CHOICE;
		}
		else {
			return dateInt;
		}
	}

	public void invalidDate(int year, int month, int day) {
		System.out.println(year + "-" + month + "-" + day + " is not a valid date");
	}

	/**
	 * Inform to user that the Dates entered are reversed
	 * @param toDate the upper bound for the date
	 * @param fromDate the lower bound for the date
	 */
	public void wrongDateOrder(LocalDate toDate, LocalDate fromDate) {
		System.out.println(toDate + " is before " + fromDate+ ". Please try again");
	}

	public void noOrdersFound(Customer customer, LocalDate fromDate, LocalDate toDate) {
		System.out.println("No orders for " + customer + " were found between " + fromDate + " and " + toDate);
	}

	public void listOrdersInRange(Customer customer, List<Order> ordersInRange, LocalDate fromDate, LocalDate toDate) {
		System.out.println("Between " + fromDate + " and " + toDate);
		System.out.println("For Customer " + customer);
		for(Order order : ordersInRange) {
			System.out.println(order.toString());
		}
	}
	
}
