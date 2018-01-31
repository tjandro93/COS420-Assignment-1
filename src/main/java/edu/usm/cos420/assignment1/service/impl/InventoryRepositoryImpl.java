/**
 * 
 */
package edu.usm.cos420.assignment1.service.impl;

import java.util.List;

import edu.usm.cos420.assignment1.dao.domain.InventoryItemDao;
import edu.usm.cos420.assignment1.domain.InventoryItem;
import edu.usm.cos420.assignment1.service.InventoryRepository;

/**
 * Implementation of {@link InventoryRepository}
 * <p>
 * Serves as an repository service layer between dao/domain and the controller/view
 */
public class InventoryRepositoryImpl implements InventoryRepository {

	private InventoryItemDao dao;
	
	/**
	 * Default constructor creates new {@link InventoryItemDao}
	 */
	public InventoryRepositoryImpl(){
		this.dao = new InventoryItemDao();
	}
	
	/**
	 * Constructor which takes existing {@code InventoryItemDao}
	 * @param dao the {@code InventoryItemDao} to use
	 */
	public InventoryRepositoryImpl(InventoryItemDao dao){
		this.dao = dao;
	}
	/**
	 * Add an {@code InventoryItem} to the system
	 * @param item the {@code InventoryItem} to be added
	 */
	@Override
	public void addItem(InventoryItem item) {
		dao.add(item);
	}

	/**
	 * Find a specific {@code InventoryItem} by ID 
	 * @param idNum the ID of the desired {@code InventoryItem}
	 * @return the {@code InventoryItem} with the given ID, or null if none is found
	 */
	@Override
	public InventoryItem findItemById(int idNum) {
		return dao.find((long) idNum);
	}

	/**
	 * Get all the {@code InventoryItem} entities in the system
	 * @return list of all {@code InventoryItem} in the system
	 */
	@Override
	public List<InventoryItem> getAll() {
		return dao.list();
	}

	/**
	 * Delete the InventoryItem with the supplied ID
	 * @param id the ID of the InventoryItem to be deleted
	 */
	@Override
	public void deleteById(int id) {
		dao.remove((long) id);
	}


	/**
	 * Update the InventoryItem whose ID matches that of the supplied InventoryItem
	 * @param newItem the InventoryItem to be updated
	 */
	@Override
	public void updateItem(InventoryItem newItem) {
		dao.update(newItem);
	}

}
