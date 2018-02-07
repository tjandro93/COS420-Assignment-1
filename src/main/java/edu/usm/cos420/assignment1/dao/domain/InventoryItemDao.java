package edu.usm.cos420.assignment1.dao.domain;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import edu.usm.cos420.assignment1.dao.GenericDao;
import edu.usm.cos420.assignment1.dao.JsonDao;
import edu.usm.cos420.assignment1.domain.InventoryItem;

/**
 * A Data Access Object for {@link InventoryItem} entities
 * <p>
 * Uses {@link JsonDao} to persist {@code InventoryItem} entities in JSON format
 */
public class InventoryItemDao {
	
	private GenericDao<Long, InventoryItem> inventoryItemDao;

	/**
	 * Default constructor creates a JSON file called inventory.json
	 */
	public InventoryItemDao(){
		Type t= new TypeToken<Map<Long, InventoryItem>>(){}.getType();
		inventoryItemDao = new JsonDao<>("inventory.json", t);
	}
	
	/**
	 * Constructor allowing the JSON filename to be specified
	 * @param fileName the filename to use for the JSON file
	 */
	public InventoryItemDao(String fileName){
		Type t= new TypeToken<Map<Long, InventoryItem>>(){}.getType();
		inventoryItemDao = new JsonDao<>(fileName, t);
	}
	
	
	/**
	 * Allows for a GenericDao of the correct type to be specified
	 * @param dao a Data Access Object that implements GenericDao&lt;Long, InventoryItem&gt;
	 */
	public InventoryItemDao(GenericDao<Long, InventoryItem> dao){
		this.inventoryItemDao = dao;
	}
	
	/**
	 * Add an InventoryItem to the DAO repository
	 * @param entity any InventoryItem object
	 */
	public void add(InventoryItem entity)
	{
		inventoryItemDao.add((long)entity.getId(), entity);
	}
	
	/**
	 * Update an InventoryItem in the DAO repository
	 * @param entity any InventoryItem object
	 */
	public void update(InventoryItem entity) 
	{
		inventoryItemDao.update((long)entity.getId(), entity);
	}
	
	/**
	 * Remove an InventoryItem in the DAO repository
	 * @param id of the InventoryItem object to remove
	 */

	public void remove(Long id)
	{
		inventoryItemDao.remove(id);
	}
	
	/**
	 * Find a InventoryItem in the DAO repository
	 * @param key  id of the InventoryItem object to locate
	 * @return the InventoryItem with id field equal to key
	 */
	public InventoryItem find(Long key)
	{
		return inventoryItemDao.find(key);
	}
    
	/**
	 * Generate a list of InventoryItem in the DAO repository
	 * @return List of InventoryItem 
	 */

	public List<InventoryItem> list() {
		return inventoryItemDao.list();
	}
}

