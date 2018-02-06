package edu.usm.cos420.assignment1.view.impl;

import java.util.List;

import edu.usm.cos420.assignment1.domain.Customer;
import edu.usm.cos420.assignment1.service.CustomerRepository;
import edu.usm.cos420.assignment1.service.impl.CustomerRepositoryImpl;
import edu.usm.cos420.assignment1.util.Input;
import edu.usm.cos420.assignment1.view.MenuView;

/**
 * View class to display and receive customer menu choices 
 */
public class CustomerMenuView implements MenuView {

	/** {@value #NO_CHOICE } : no choice selected by user*/
	public static final int NO_CHOICE = -1;
	/** {@value #ADD_CUSTOMER } : add a customer to the system*/
	public static final int ADD_CUSTOMER = 1;
	/**	{@value #FIND_CUSTOMER_ID } : find a customer by ID*/
	public static final int FIND_CUSTOMER_ID = 2;
	/** {@value #FIND_CUSTOMER_NAME } : find a customer by name*/
	public static final int FIND_CUSTOMER_NAME = 3;
	/** {@value #LIST_CUSTOMERS } : list all customers in the system*/
	public static final int LIST_CUSTOMERS = 4;
	/** {@value #ORDERS } : access the orders menu*/
	public static final int ORDERS = 5;
	/** {@value #EDIT_ID } : edit the customer ID*/
	public static final int EDIT_ID = 6;
	/** {@value #EDIT_NAME } : edit the customer name*/	
	public static final int EDIT_NAME = 7;
	/** {@value #EDIT_ADDRESS } : edit the customer address*/
	public static final int EDIT_ADDRESS = 8;
	/** {@value #EXIT } : exit the customer menu (returning to the main menu)*/
	public static final int EXIT = 0;

	private CustomerRepository repository;

	/**
	 * Default constructor
	 * <p>
	 * Makes this instance create a new instance of {@code service.CustomerRepository} 
	 */
	public CustomerMenuView(){
		this.repository = new CustomerRepositoryImpl();
	}

	/**
	 * Constructor which takes a CustomerRepository instance 
	 * @param repository the CustomerRepository to use 
	 */
	public CustomerMenuView(CustomerRepository repository){
		this.repository = repository;
	}

	/**
	 * Display menu options to user
	 */
	@Override
	public void displayMainMenu() {
		System.out.println();
		System.out.println("Enter the number denoting the action desired");
		System.out.println("Add new Customer.............." + ADD_CUSTOMER);
		System.out.println("Look up Customer by ID........" + FIND_CUSTOMER_ID);
		System.out.println("Look up Customer by name......" + FIND_CUSTOMER_NAME);
		System.out.println("List all Customers............" + LIST_CUSTOMERS);
		System.out.println("Exit.........................." + EXIT);
	}

	/**
	 * Read the menu choice from the user
	 * <p>
	 * Method will not return until a valid choice has been made
	 * @return
	 * <ul>
	 * 	<li>{@value #ADD_CUSTOMER} : Add a customer to the system
	 *	<li>{@value #FIND_CUSTOMER_NAME} : Find a customer in the system
	 *	<li>{@value #LIST_CUSTOMERS} : List all customers in the system
	 *	<li>{@value #EXIT} : Exit the customer menu
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
	 * 	<li>{@value #ADD_CUSTOMER} : Add a customer to the system
	 *	<li>{@value #FIND_CUSTOMER_NAME} : Find a customer in the system
	 *	<li>{@value #LIST_CUSTOMERS} : List all customers in the system
	 *	<li>{@value #EXIT} : Exit the customer menu
	 *	<li>{@value #NO_CHOICE} : returned if input is an invalid action
	 * </ul>
	 */
	private int validateMainMenuChoice(String input){
		int choice = NO_CHOICE;
		try{
			choice = Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			System.out.println(input + " is not a valid action");
		}
		if (choice != ADD_CUSTOMER && choice != FIND_CUSTOMER_ID &&choice != FIND_CUSTOMER_NAME && choice != LIST_CUSTOMERS && choice != EXIT){
			System.out.println(input + " is not a valid action");

		}
		return choice;
	}

	/**
	 * Get user input for a Customer ID
	 * @param checkId set to true to check the ID is not yet used
	 * @return either a valid Customer ID 
	 * or {@code #EXIT} if the user wishes to abort the operation
	 */
	public int getIdInput(boolean checkId){
		System.out.println("Enter 6-digit Customer ID or 0 to abort");
		int idInt;
		String idStr = Input.readLine();
		while((idInt = validateNewId(idStr, checkId)) == NO_CHOICE){
			idStr = Input.readLine();
		}
		return idInt;
	}

	/**
	 * Validate the input Customer ID to ensure it is 6 Digits
	 * and is not already being used
	 * @param idStr the input from the user
	 * @param checkId set to true to check the ID is not yet used
	 * @return a valid ID number,
	 *  {@code #EXIT} if the user wishes to abort the operation,
	 *  or {@code #NO_CHOICE} if the input is invalid
	 */
	private int validateNewId(String idStr, boolean checkId){
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
		if(checkId && repository.findCustomersById(idNum) != null){
			System.out.println("A Customer with ID " + idNum + " is already in the system");
			return NO_CHOICE;
		}
		else{

			return idNum;
		}
	}

	/**
	 * Get user input for a Customer name. Will not return until a nonempty string is input
	 * @return a valid Customer name
	 */
	public String getNameInput(){
		System.out.println("Enter a name for the Customer or 0 to abort");
		String custName = null;
		while(custName == null){
			custName = Input.readLine();
			if(custName.length() == 0){
				System.out.println("You must provide a name");
				custName = null;
			}
		}
		return custName;
	}

	/**
	 * Get user input for a Customer address. Will not return until a nonempty string is input
	 * @return a valid Customer address
	 */
	public String getAddressInput(){
		System.out.println("Enter the customer's address or 0 to abort");
		String custAddr = null;
		while(custAddr == null){
			custAddr = Input.readLine();
			if(custAddr.length() == 0){
				System.out.println("You must provide an address");
				custAddr = null;
			}
		}
		return custAddr;
	}
	
	/**
	 * Report that a Customer could not be found in the system
	 */
	public void customerNotFound(String message) {
		System.out.println("The customer " + message + " could not be found");
	}
	
	/**
	 * Display a list Customers
	 * @param customers the list of Customers to display
	 */
	public void displayAllCustomers(List<Customer> customers){
		System.out.println(customers.size() + " customers:");
		for(Customer c : customers){
			System.out.println(c.toString());
		}
	}
	
	/**
	 * Display that multiple customers were found on lookup
	 */
	public void multipleCustomersFound(List<Customer> customers) {
		System.out.println(customers.size() + " customers were found with the given name");
		System.out.println("Please abort lookup by name and lookup by ID instead");
		for(Customer c : customers) {
			System.out.println(c.toString());
		}
	}

	/**
	 * Display abort message for adding Customers
	 */
	public void abortAdd() {
		System.out.println("Add customer aborted");
	}

	/** 
	 * Display abort message for lookup of Customers
	 */
	public void abortLookup() {
		System.out.println("Lookup customer aborted");
		
	}
	
	/**
	 * Display options for accessing a specific customer
	 * @param customer The customer which is being accessed or changed
	 */
	public void displayLookupMenu(Customer customer) {
		System.out.println();
		System.out.println("Customer " + customer.toString());
		System.out.println("Access customer orders........" + ORDERS);;
		System.out.println("Edit customer ID ............." + EDIT_ID);
		System.out.println("Edit customer name............" + EDIT_NAME);
		System.out.println("Edit customer address........." + EDIT_ADDRESS);
		System.out.println("Exit.........................." + EXIT);
	}
	
	/**
	 * Read the menu choice from the user
	 * <p>
	 * Method will not return until a validate choice has been made
	 * @return
	 * <ul>
	 * 	<li>{@value #ORDERS} : Access customer's orders
	 *	<li>{@value #EDIT_ID} : edit the customer's ID
	 *	<li>{@value #EDIT_NAME} : edit the customer's name
	 *	<li>{@value #EDIT_ADDRESS} : edit the customer's address
	 *	<li>{@value #EXIT} : Exit the customer menu
	 * </ul>
	 */
	public int getLookupMenuChoice() {
		int choice = NO_CHOICE;	
		while( choice == NO_CHOICE){
			System.out.print("Enter choice ");
			System.out.flush();
			String choiceStr = Input.readLine();
			choice = validateLookupMenuChoice(choiceStr);
		}
		return choice;
	}


	/**
	 * Private method to validate user input of menu choice
	 * @param input the String input by the user
	 * @return
	 * <ul>
	 * 	<li>{@value #ORDERS} : Access customer's orders
	 *	<li>{@value #EDIT_ID} : edit the customer's ID
	 *	<li>{@value #EDIT_NAME} : edit the customer's name
	 *	<li>{@value #EDIT_ADDRESS} : edit the customer's address
	 *	<li>{@value #NO_CHOICE} : returned if input is an invalid action
	 *	<li>{@value #EXIT} : Exit the customer menu
	 * </ul>
	 */
	private int validateLookupMenuChoice(String choiceStr) {
		int choice = NO_CHOICE;
		try{
			choice = Integer.parseInt(choiceStr);
		}
		catch(NumberFormatException e){
			System.out.println(choiceStr + " is not a valid action");
		}
		if (choice != ORDERS && choice != EDIT_ID && choice != EDIT_NAME && choice != EDIT_ADDRESS 
				&& choice != NO_CHOICE && choice != EXIT){
			System.out.println(choiceStr + " is not a valid action");

		}
		return choice;
	}

	/**
	 * Report an aborted edit of a Customer field
	 * @param message specifies the field which was being edit. Set to null for default message 
	 */
	public void abortEdit(String message) {
		if(message == null) {
			System.out.println("Edit customer aborted");
		}
		else {
			System.out.println("Edit " + message + " aborted");
		}
		
	}

	public boolean confirmEdit(Customer customer, Customer updatedCustomer) {
		System.out.println(String.format("Really change?\nOriginal: %s\nNew: %s",
				customer.toString(), updatedCustomer.toString()));
		return Input.getConfirmation();
	}
}
