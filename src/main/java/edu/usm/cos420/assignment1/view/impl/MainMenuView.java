package edu.usm.cos420.assignment1.view.impl;

import java.util.Scanner;

import edu.usm.cos420.assignment1.view.MenuView;

/**
 * View class to handle main menu input and output
 * <p>
 * This view is to be attached to {@link edu.usm.cos420.assignment1.controller.MainMenuController}
 */
public class MainMenuView implements MenuView{

	//TODO test MainMenuView
	/** {@value #NO_CHOICE} : no choice selected by user */
	public static final int NO_CHOICE = 0;
	/** {@value #CUSTOMER_MENU} : access customer sub-menu */
	public static final int CUSTOMER_MENU = 1;
	/** {@value #INVENTORY_MENU} : access inventory sub-menu*/
	public static final int INVENTORY_MENU = 2;
	/** {@value #ORDER_MENU} : access order sub-menu*/
	public static final int ORDER_MENU = 3;
	/** {@value #EXIT} : exit the main menu (closing the application)*/
	public static final int EXIT = 4;
	
	private Scanner in;

	/**
	 * Default constructor
	 * <p>
	 * Makes this instance use System.in for its input. 
	 */
	public MainMenuView(){
		this.in = new Scanner(System.in);
	}
	
	/**
	 * Constructor which takes a Scanner passed in for its input. 
	 * @param in the Scanner to be used for input
	 */
	public MainMenuView(Scanner in){
		this.in = in;
	}

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
	 * </ul>
	 */
	public int getMenuChoice() {
		in = new Scanner(System.in);
		int choice = NO_CHOICE;
		while(choice == NO_CHOICE){
			System.out.print("Enter choice ");
			System.out.flush();
			String choiceStr = in.next();
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
		return choice;
	}
	
	/**
	 * Getter for the internal Scanner
	 * @return the Scanner being used
	 */
	public Scanner getScanner(){
		return this.in;
	}
	
	/**
	 * Setter for the internal Scanner
	 * @param in the Scanner to be used for input
	 */
	public void setScanner(Scanner in){
		this.in = in;
	}
}
