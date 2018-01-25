package edu.usm.cos420.assignment1.controller;

public interface MenuController {

	/**
	 * Invoke View output and input prompt then execute based on that input
	 */
	public void provideMenuAccess();
	
	/**
	 * Execute the inputed choice. See the associated view for appropriate values for {@code choice}
	 * @param choice the value associated with the desired action
	 */
	public void executeChoice(int choice);
}
