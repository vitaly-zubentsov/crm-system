package com.zubentsov.crmsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zubentsov.crmsystem.dao.CustomerDao;
import com.zubentsov.crmsystem.entity.Customer;

@Service
public class CustomerServiceImp  implements CustomerService {

	//inject customer DAO
	@Autowired 
	private CustomerDao customerDao;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		
		return customerDao.getCustomer(customerId);
	}
	
	

}
