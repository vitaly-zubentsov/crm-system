package com.zubentsov.crmsystem.service;

import java.util.List;

import com.zubentsov.crmsystem.entity.Customer;

public interface CustomerService {
	
	public  List<Customer> getCustomers(); 
	
	public void addCustomer(Customer customer);

}
