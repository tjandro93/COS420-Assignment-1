package edu.usm.cos420.assignment1.view.impl;

import java.util.Scanner;

import edu.usm.cos420.assignment1.service.CustomerRepository;
import edu.usm.cos420.assignment1.service.impl.CustomerRepositoryImpl;
import edu.usm.cos420.assignment1.view.MenuView;

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

	private Scanner in;
	private CustomerRepository repository;

	/**
	 * Default constructor
	 * <p>
	 * Makes this instance use System.in for its input and create a new instance of {@code service.CustomerRepository} 
	 */
	public CustomerMenuView(){
		this.in = new Scanner(System.in);
		this.repository = new CustomerRepositoryImpl();
	}

	/**
	 * Constructor which takes a Scanner passed in for its input. 
	 * @param in the Scanner to be used for input
	 * @param repository the CustomerRepository to use 
	 */
	public CustomerMenuView(Scanner in, CustomerRepository repository){
		this.in = in;
		this.repository = repository;
	}

	/**
	 * Display menu options to user
	 */
	@Override
	public void displayMenu() {
		System.out.println();
		System.out.println("Enter the number denoting to action desired");
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
			String choiceStr = in.nextLine();
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
		if (choice != ADD_CUSTOMER && choice != FIND_CUSTOMER && choice != LIST_CUSTOMERS && choice != EXIT && choice != NO_CHOICE){
			System.out.println(input + " is not a valid action");

		}
		return choice;
	}

	/**
	 * Getter for internal Scanner
	 * @return the Scanner attached to this view
	 */
	public Scanner getScanner() {
		return this.in;
	}

}
