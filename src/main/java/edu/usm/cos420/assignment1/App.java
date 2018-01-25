package edu.usm.cos420.assignment1;

import edu.usm.cos420.assignment1.controller.MainMenuController;
import edu.usm.cos420.assignment1.view.impl.MainMenuView;

/**
 * Top level application class that coordinates the calls to view and Controller
 *
 */
public class App
{
	//TODO alter App to use MainMenuController
    /**
     * Entry point for application : calls {@link #provideCItemAccess()}
     * @param args  main program arguments, currently not used
     */
	public static void main( String[] args )
    {
		MainMenuView mainMenuView = new MainMenuView();
		MainMenuController mainMenuController = new MainMenuController(mainMenuView);		
		mainMenuController.provideMenuAccess();
    }
}
