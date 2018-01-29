package edu.usm.cos420.assignment1.controller.impl;

import edu.usm.cos420.assignment1.controller.MenuController;
import edu.usm.cos420.assignment1.view.impl.InventoryMenuView;

/**
 * Controller class to execute user's choice for the Inventory menu
 */
public class InventoryMenuController implements MenuController{

	private InventoryMenuView view;
	
	/**
	 * Constructor: initializes a new InventoryMenuView
	 */
	public InventoryMenuController(){
		this.view = new InventoryMenuView();
	}
	
	/**
	 * Constructor: uses the supplied InventoryMenuView
	 * @param view the view to use 
	 */
	public InventoryMenuController(InventoryMenuView view){
		this.view = view;
	}
	
	@Override
	public void provideMenuAccess() {
		int choice = InventoryMenuView.NO_CHOICE;
		while(choice != InventoryMenuView.EXIT){
			view.displayMenu();
			choice = view.getMenuChoice();
			executeChoice(choice);
		}
		
	}

	@Override
	public void executeChoice(int choice) {
		switch(choice){
		case InventoryMenuView.ADD_ITEM:
			System.out.println("Add item placeholder");
			break;
		case InventoryMenuView.LIST_ITEMS:
			System.out.println("List items placeholder");
			break;
			default:
				break;
		}
	}

}
