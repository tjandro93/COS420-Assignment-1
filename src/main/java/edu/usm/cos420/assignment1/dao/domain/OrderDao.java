package edu.usm.cos420.assignment1.dao.domain;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import edu.usm.cos420.assignment1.dao.GenericDao;
import edu.usm.cos420.assignment1.dao.JsonDao;
import edu.usm.cos420.assignment1.domain.Order;

public class OrderDao {
	private GenericDao <Long, Order> orderDao;
	
	/**
	 * Default constructor creates an JSON file called order.json
	 */
	public OrderDao(){
		Type t = new TypeToken<Map<Long, Order>>(){}.getType();
		orderDao = new JsonDao<>("order.json", t);
	}
	
	/**
	 * Constructor allowing for the JSON file to be specified
	 * @param fileName the filename to use for the JSON file
	 */
	public OrderDao(String fileName){
		Type t = new TypeToken<Map<Long, Order>>(){}.getType();
		orderDao = new JsonDao<>(fileName, t);
	}
	
	/**
	 * Allows for a GenericDao of the correct type to be specified
	 * @param dao a Data Access Object that implements GenericDao<Long, Order>
	 */
	public OrderDao(GenericDao<Long, Order> dao){
		orderDao = dao;
	}
	

	/**
	 * Add a Order to the DAO repository
	 * @param entity any Order object
	 */
	public void add(Order entity)
	{
		orderDao.add(entity.getId(), entity);
	}
	
	/**
	 * Update a Order in the DAO repository
	 * @param entity any Order object
	 */
	public void update(Order entity) 
	{
		orderDao.update(entity.getId(), entity);
	}
	
	/**
	 * Remove a Order in the DAO repository
	 * @param id of the Order object to remove
	 */

	public void remove(Long id)
	{
		orderDao.remove(id);
	}
	
	/**
	 * Find a Order in the DAO repository
	 * @param id of the Order object to locate
	 * @return the Order with id field equal to key
	 */
	public Order find(Long key)
	{
		return orderDao.find(key);
	}
    
	/**
	 * Generate a list of Orders in the DAO repository
	 * @return List of Order 
	 */

	public List<Order> list() {
		return orderDao.list();
	}
}
