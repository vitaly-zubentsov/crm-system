package com.zubentsov.crmsystem.dao;

import java.util.List;

import com.zubentsov.crmsystem.entity.Customer;

public interface CustomerDao {
	
	public  List<Customer> getCustomers(); 
	
	public void addCustomer(Customer customer);

	public Customer getCustomer(int customerId);

}
