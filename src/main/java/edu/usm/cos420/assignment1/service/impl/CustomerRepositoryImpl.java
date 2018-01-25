package edu.usm.cos420.assignment1.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.usm.cos420.assignment1.dao.domain.CustomerDao;
import edu.usm.cos420.assignment1.domain.Customer;
import edu.usm.cos420.assignment1.service.CustomerRepository;
/**
 * Implementation of {@link CustomerRepository}
 * <p>
 * Serves as an repository service layer between dao/domain and the controller/view
 */
public class CustomerRepositoryImpl implements CustomerRepository {

	private CustomerDao dao;

	/**
	 * Default constructor creates new CustomerDao object
	 */
	public CustomerRepositoryImpl(){
		this.dao = new CustomerDao();
	}

	/**
	 * Constructor which takes a provided CustomerDao instance
	 * @param dao the CustomerDao to use
	 */
	public CustomerRepositoryImpl(CustomerDao dao){
		this.dao = dao;
	}

	/**
	 * Add the Customer to the repository
	 * @param customer the Customer to be added
	 */
	@Override
	public void addCustomer(Customer customer) {
		dao.add(customer);
	}

	/**
	 * Find a Customer in the repository by name field
	 * @param name the name of the Customer to be searched for
	 * @return a List of Customer objects whose name field matches the input name 
	 */
	@Override
	public List<Customer> findCustomersByName(String name) {
		Iterator<Customer> itr = dao.list().iterator();
		Customer currCustomer;
		List<Customer> foundCustomers = new ArrayList<>();
		while(itr.hasNext()){
			if((currCustomer = itr.next()).getName().equals(name)){
				foundCustomers.add(currCustomer);
			}
		}
		return foundCustomers;
	}

}