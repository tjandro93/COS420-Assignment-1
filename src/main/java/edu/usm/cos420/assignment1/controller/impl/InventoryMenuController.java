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
	 * Constructor: initializes a new InventoryMenuView and InventoryRepository
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
			view.displayMainMenu();
			choice = view.getMainMenuChoice();
			executeChoice(choice);
		}
		System.out.println();
	}

	/**
	 * Based on choice, do the desired Inventory action
	 * @param choice the constant from InventoryMenuView for the desired action
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
		case InventoryMenuView.EDIT_ITEM:
			editItem();
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
		int itemId = view.getIdInput(true);
		if(itemId != InventoryMenuView.EXIT){
			String itemName = view.getNameInput();
			if(!itemName.equals(String.valueOf(InventoryMenuView.EXIT))){
				String itemInfo = view.getInfoInput();
				if(!itemInfo.equals(String.valueOf(InventoryMenuView.EXIT))){
					int itemQuantity = view.getQuantityInput();
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

	/**
	 * Edit an existing InventoryItem in the system
	 */
	private void editItem() {
		System.out.println();
		InventoryItem item = null;
		int itemId = view.getIdInput(false);
		if(itemId != InventoryMenuView.EXIT){
			while(itemId != InventoryMenuView.EXIT && 
					(item = repository.findItemById(itemId)) == null){
				view.itemNotFound(itemId);
				itemId = view.getIdInput(false);
			}
			if(itemId == InventoryMenuView.EXIT){
				view.abortEdit(null);
			}
			else{
				int choice = InventoryMenuView.NO_CHOICE;
				while(choice != InventoryMenuView.EXIT){
					System.out.println();
					view.displayItem(item);
					view.displayEditMenu();
					choice = view.getEditMenuChoice();
					switch(choice){
					case InventoryMenuView.EDIT_ID:
						item = editId(item);
						break;
					case InventoryMenuView.EDIT_NAME:
						item = editName(item);
						break;
					case InventoryMenuView.EDIT_QUANTITY:
						item = editQuantity(item);
						break;
					case InventoryMenuView.EDIT_INFO:
						item = editInfo(item);
						break;
					default:
						break;
					}
				}
			}
		}
		else{
			view.abortEdit(null);
		}
	}

	/**
	 * Handle InventoryMenuView I/O to change an InventoryItem's ID field
	 * @param item the original version of the item to be changed
	 * @return the InventoryItem whether or not it has changed
	 */
	private InventoryItem editId(InventoryItem item) {
		int itemId = view.getIdInput(true);
		if(itemId != InventoryMenuView.EXIT){
			InventoryItem updatedItem = new InventoryItem(itemId, item.getName(), 
					item.getDescription(), item.getQuantity());
			if(view.confirmEdit(item, updatedItem)){
				repository.deleteById(item.getId());
				item.setId(itemId);
				repository.addItem(item);
			}
			else{
				view.abortEdit("ID field");
			}
		}
		else{
			view.abortEdit("ID field");
		}
		return item;
	}

	/**
	 * Handle InventoryMenuView I/O to change an InventoryItem's name field
	 * @param item the original version of the item to be changed
	 * @return the InventoryItem whether or not it has changed
	 */
	private InventoryItem editName(InventoryItem item) {
		String newName = view.getNameInput();
		if(!newName.equals(String.valueOf(InventoryMenuView.EXIT))){
			InventoryItem newItem = 
					new InventoryItem(item.getId(), newName, item.getDescription(), item.getQuantity());
			if(view.confirmEdit(item, newItem)){
				repository.updateItem(newItem);
				return newItem;
			}
			else{
				view.abortEdit("name field");
			}
		}
		else{
			view.abortEdit("name field");
		}
		return item;
	}

	/**
	 * Handle InventoryMenuView I/O to change an InventoryItem's quantity field
	 * @param item the original version of the item to be changed
	 * @return the InventoryItem whether or not it has changed
	 */
	private InventoryItem editQuantity(InventoryItem item) {
		int newQuantity = view.getQuantityInput();
		if(newQuantity != InventoryMenuView.EXIT){
			InventoryItem newItem = new InventoryItem(item.getId(), item.getName(), item.getDescription(), newQuantity);
			if(view.confirmEdit(item, newItem)){
				repository.updateItem(newItem);
				return newItem;
			}
			else{
				view.abortEdit("quantity field");
			}
		}
		else{
			view.abortEdit("quantity field");
		}
		return item;
	}

	/**
	 * Handle InventoryMenuView I/O to change an InventoryItem's info field
	 * @param item the original version of the item to be changed
	 * @return the InventoryItem whether or not it has changed
	 */
	private InventoryItem editInfo(InventoryItem item) {
		String newInfo = view.getInfoInput();
		if(!newInfo.equals(String.valueOf(InventoryMenuView.EXIT))){
			InventoryItem newItem = 
					new InventoryItem(item.getId(), item.getName(), newInfo, item.getQuantity());
			if(view.confirmEdit(item, newItem)){
				repository.updateItem(newItem);
				return newItem;
			}
			else{
				view.abortEdit("info field");
			}
		}
		else{
			view.abortEdit("info field");
		}
		return item;
	}

}
