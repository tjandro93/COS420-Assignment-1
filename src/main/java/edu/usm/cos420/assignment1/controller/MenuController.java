package edu.usm.cos420.assignment1.controller;

/**
 * Interface for all Menu Controllers. Provided methods are entry points into each menu
 *
 */
public interface MenuController {

	/**
	 * Invoke View output and input prompt then execute based on that input
	 */
	public void provideMenuAccess();
	
	/**
	 * Execute the inputed choice. See the associated view for appropriate values for choice
	 * @param choice  the value associated with the desired action
	 */
	public void executeChoice(int choice);
}
