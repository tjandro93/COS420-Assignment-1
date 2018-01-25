package edu.usm.cos420.assignment1.view;

public interface MenuView {
	
	/**
	 * Display menu options to user
	 */
	public void displayMenu();
	
	
	/**
	 * Read the menu choice from the user
	 * @return an int matching the user's choice. 
	 * See implementation for valid choices
	 */
	public int getMenuChoice();
	
}

