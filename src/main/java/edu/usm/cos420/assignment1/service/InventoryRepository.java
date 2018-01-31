package edu.usm.cos420.assignment1.service;

import java.util.List;

import edu.usm.cos420.assignment1.domain.InventoryItem;

/**
 * A repository service interface to define actions to be performed on the collection of {@link InventoryItem} entities.
 */
public interface InventoryRepository {

	/**
	 * Add an {@code InventoryItem} to the system
	 * @param item the {@code InventoryItem} to be added
	 */
	public void addItem(InventoryItem item);
	
	/**
	 * Find a specific {@code InventoryItem} by ID 
	 * @param idNum the ID of the desired {@code InventoryItem}
	 * @return the {@code InventoryItem} with the given ID, or null if none is found
	 */
	public InventoryItem findItemById(int idNum);
	
	/**
	 * Get all the {@code InventoryItem} entities in the system
	 * @return list of all {@code InventoryItem} in the system
	 */
	public List<InventoryItem> getAll();

	/**
	 * Delete the InventoryItem with the supplied ID
	 * @param id the ID of the InventoryItem to be deleted
	 */
	public void deleteById(int id);

	/**
	 * Update the InventoryItem whose ID matches that of the supplied InventoryItem
	 * @param newItem the InventoryItem to be updated
	 */
	public void updateItem(InventoryItem newItem);
	
}
