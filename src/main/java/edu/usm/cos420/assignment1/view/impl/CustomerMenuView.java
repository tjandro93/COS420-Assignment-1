package edu.usm.cos420.assignment1.view.impl;

import java.util.List;
import java.util.Scanner;

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
	/** {@value #FIND_CUSTOMER } : find a customer in the system*/
	public static final int FIND_CUSTOMER = 2;
	/** {@value #LIST_CUSTOMERS } : list all customers in the system*/
	public static final int LIST_CUSTOMERS = 3;
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
	public void displayMenu() {
		System.out.println();
		System.out.println("Enter the number denoting the action desired");
		System.out.println("Add new Customer.............." + ADD_CUSTOMER);
		System.out.println("Look up Customer.............." + FIND_CUSTOMER);
		System.out.println("List all Customers............" + LIST_CUSTOMERS);
		System.out.println("Exit.........................." + EXIT);
	}

	/**
	 * Read the menu choice from the user
	 * <p>
	 * Method will not return until a validate choice has been made
	 * @return
	 * <ul>
	 * 	<li>{@value #ADD_CUSTOMER} : Add a customer to the system
	 *	<li>{@value #FIND_CUSTOMER} : Find a customer in the system
	 *	<li>{@value #LIST_CUSTOMERS} : List all customers in the system
	 *	<li>{@value #EXIT} : Exit the customer menu
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
	 * 	<li>{@value #ADD_CUSTOMER} : Add a customer to the system
	 *	<li>{@value #FIND_CUSTOMER} : Find a customer in the system
	 *	<li>{@value #LIST_CUSTOMERS} : List all customers in the system
	 *	<li>{@value #EXIT} : Exit the customer menu
	 *	<li>{@value #NO_CHOICE} : returned if input is an invalid action
	 * </ul>
	 */
	private int validateChoice(String input){
		int choice = NO_CHOICE;
		try{
			choice = Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			System.out.println(input + " is not a valid action");
		}
		if (choice != ADD_CUSTOMER && choice != FIND_CUSTOMER && choice != LIST_CUSTOMERS && choice != EXIT){
			System.out.println(input + " is not a valid action");

		}
		return choice;
	}

	/**
	 * Prompt user to enter a Customer ID
	 */
	public void displayIdPrompt(){
		System.out.println("Enter 6-digit Customer ID or 0 to abort");
	}

	/**
	 * Get user input for a Customer ID
	 * @return either a valid Customer ID 
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
	 * Validate the input Customer ID to ensure it is 6 Digits
	 * and is not already being used
	 * @param idStr the input from the user
	 * @return a valid ID number,
	 *  {@code #EXIT} if the user wishes to abort the operation,
	 *  or {@code #NO_CHOICE} if the input is invalid
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
		if(repository.findCustomersById(idNum) != null){
			System.out.println("A Customer with ID " + idNum + " is already in the system");
			return NO_CHOICE;
		}
		else{

			return idNum;
		}
	}

	/**
	 * Prompt user to enter a Customer name
	 */
	public void displayNamePrompt(){
		System.out.println("Enter a name for the Customer or 0 to abort");
	}

	/**
	 * Get user input for a Customer name. Will not return until a nonempty string is input
	 * @return a valid Customer name
	 */
	public String getNewName(){
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
	 * Prompt user to enter a Customer's address
	 */
	public void displayAddressPrompt(){
		System.out.println("Enter the customer's address or 0 to abort");
	}

	/**
	 * Get user input for a Customer address. Will not return until a nonempty string is input
	 * @return a valid Customer address
	 */
	public String getNewAddress(){
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
	 * Display abort message for adding Customers
	 */
	public void abortAdd() {
		System.out.println("Add customer aborted");
	}
}
