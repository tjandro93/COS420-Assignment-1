package edu.usm.cos420.assignment1;

import edu.usm.cos420.assignment1.controller.impl.AppMenuController;
import edu.usm.cos420.assignment1.view.impl.AppMenuView;


/**
 * Top level application class that coordinates the calls to view and Controller
 *
 */
public class App
{
    /**
     * Entry point for application
     * @param args  main program arguments, currently not used
     */
	public static void main( String[] args )
    {
		AppMenuView appMenuView = new AppMenuView();
		AppMenuController appMenuController = new AppMenuController(appMenuView);		
		appMenuController.provideMenuAccess();
    }
}
