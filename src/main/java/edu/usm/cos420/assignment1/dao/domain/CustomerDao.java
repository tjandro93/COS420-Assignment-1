package edu.usm.cos420.assignment1.dao.domain;

import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import edu.usm.cos420.assignment1.dao.GenericDao;
import edu.usm.cos420.assignment1.dao.JsonDao;
import edu.usm.cos420.assignment1.domain.Customer;

/**
 * 
 * A Data Access Object for {@link Customer} entities
 * <p>
 * Uses {@link JsonDao} to persist {@code Customer} entities in JSON format
 *
 */
public class CustomerDao {

	private GenericDao<Long, Customer> customerDao;
	
	/**
	 * Default constructor creates an JSON file called customer.json
	 */
	public CustomerDao(){
		Type t = new TypeToken<Map<Long, Customer>>(){}.getType();
		customerDao = new JsonDao<>("customer.json", t);
	}
	
	/**
	 * Constructor allowing for the JSON file to be specified
	 * @param fileName the filename to use for the JSON file
	 */
	public CustomerDao(String fileName){
		Type t = new TypeToken<Map<Long, Customer>>(){}.getType();
		customerDao = new JsonDao<>(fileName, t);
	}
	
	/**
	 * Allows for a GenericDao of the correct type to be specified
	 * @param dao a Data Access Object that implements GenericDao<Long, Customer>
	 */
	public CustomerDao(GenericDao<Long, Customer> dao){
		customerDao = dao;
	}
	

	/**
	 * Add a Customer to the DAO repository
	 * @param entity any Customer object
	 */
	public void add(Customer entity)
	{
		customerDao.add(entity.getId(), entity);
	}
	
	/**
	 * Update a Customer in the DAO repository
	 * @param entity any Customer object
	 */
	public void update(Customer entity) 
	{
		customerDao.update(entity.getId(), entity);
	}
	
	/**
	 * Remove a Customer in the DAO repository
	 * @param id of the Customer object to remove
	 */

	public void remove(Long id)
	{
		customerDao.remove(id);
	}
	
	/**
	 * Find a Customer in the DAO repository
	 * @param id of the Customer object to locate
	 * @return the Customer with id field equal to key
	 */
	public Customer find(Long key)
	{
		return customerDao.find(key);
	}
    
	/**
	 * Generate a list of Customers in the DAO repository
	 * @return List of Customer 
	 */

	public List<Customer> list() {
		return customerDao.list();
	}
}
