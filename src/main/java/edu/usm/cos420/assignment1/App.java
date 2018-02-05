package edu.usm.cos420.assignment1;

import edu.usm.cos420.assignment1.controller.impl.AppMenuController;
import edu.usm.cos420.assignment1.view.impl.AppMenuView;


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
		AppMenuView mainMenuView = new AppMenuView();
		AppMenuController mainMenuController = new AppMenuController(mainMenuView);		
		mainMenuController.provideMenuAccess();
    }
}
