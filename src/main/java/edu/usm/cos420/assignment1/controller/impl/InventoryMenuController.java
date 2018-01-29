package edu.usm.cos420.assignment1.controller.impl;

import java.util.List;

import edu.usm.cos420.assignment1.controller.MenuController;
import edu.usm.cos420.assignment1.domain.InventoryItem;
import edu.usm.cos420.assignment1.service.InventoryRepository;
import edu.usm.cos420.assignment1.service.impl.InventoryRepositoryImpl;
import edu.usm.cos420.assignment1.util.Input;
import edu.usm.cos420.assignment1.view.impl.InventoryMenuView;

/**
 * Controller class to execute user's choice for the Inventory menu
 */
public class InventoryMenuController implements MenuController{

	private InventoryMenuView view;
	private InventoryRepository repository;

	/**
	 * Constructor: initializes a new InventoryMenuView and {@link InventoryRepository}
	 */
	public InventoryMenuController(){
		this.view = new InventoryMenuView();
		this.repository = new InventoryRepositoryImpl();
	}

	/**
	 * Constructor: uses the supplied InventoryMenuView
	 * @param view the view to use 
	 * @param repository the repository to use
	 */
	public InventoryMenuController(InventoryMenuView view, InventoryRepository repository){
		this.view = view;
		this.repository = repository;
	}

	/**
	 * Constructor: uses provided InventoryRepository, creates new InventoryMenuView
	 * @param repository the repository to use
	 */
	public InventoryMenuController(InventoryRepository repository){
		this.repository = repository;
		this.view = new InventoryMenuView();
	}

	/**
	 * Display main menu, get user input, and branch on input
	 */
	@Override
	public void provideMenuAccess() {
		int choice = InventoryMenuView.NO_CHOICE;
		while(choice != InventoryMenuView.EXIT){
			view.displayMenu();
			choice = view.getMenuChoice();
			executeChoice(choice);
		}

	}

	/**
	 * Based on {@code choice}, do the desired Inventory action
	 * @param choice the constant from {@code InventoryMenuView} for the desired action
	 */
	@Override
	public void executeChoice(int choice) {
		switch(choice){
		case InventoryMenuView.ADD_ITEM:
			addItem();
			break;
		case InventoryMenuView.LIST_ITEMS:
			listItems();
			break;
		default:
			break;
		}
	}

	/**
	 * Add an InventoryItem to the system
	 */
	private void addItem() {
		System.out.println();
		view.displayIdPrompt();
		int itemId = view.getNewId();
		if(itemId != InventoryMenuView.EXIT){
			view.displayNamePrompt();
			String itemName = view.getNewName();
			if(!itemName.equals(String.valueOf(InventoryMenuView.EXIT))){
				view.displayInfoPrompt();
				String itemInfo = view.getNewInfo();
				if(!itemInfo.equals(String.valueOf(InventoryMenuView.EXIT))){
					view.displayQuantityPrompt();
					int itemQuantity = view.getNewQuantity();
					if(itemQuantity != InventoryMenuView.EXIT){
						InventoryItem newItem = new InventoryItem(itemId, itemName, itemInfo, itemQuantity);
						System.out.println("You entered\t " + newItem);
						if(Input.getConfirmation()){
							repository.addItem(newItem);
							System.out.println("Item saved");
						}
						else{
							view.abortAdd();
						}
					}
					else{
						view.abortAdd();
					}
				}
				else{
					view.abortAdd();
				}
			}
			else{
				view.abortAdd();
			}
		}
		else{
			view.abortAdd();
		}
	}

	/**
	 * List all InventoryItems in the system
	 */
	private void listItems() {
		List<InventoryItem> allItems = repository.getAll();
		System.out.println();
		view.displayAllItems(allItems);
	}

}
