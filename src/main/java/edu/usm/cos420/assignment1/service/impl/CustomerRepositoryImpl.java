package edu.usm.cos420.assignment1.service.impl;

import edu.usm.cos420.assignment1.dao.domain.CustomerDao;
import edu.usm.cos420.assignment1.domain.Customer;
import edu.usm.cos420.assignment1.service.CustomerRepository;
/**
 * Implementation of {@link CustomerRepository}
 * <p>
 * Serves as an interface level between dao/domain and the controller/view
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
	 * Add the Customer to 
	 * @param customer
	 */
	@Override
	public void addCustomer(Customer customer) {
		dao.add(customer);
	}

	@Override
	public Customer findCustomerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
