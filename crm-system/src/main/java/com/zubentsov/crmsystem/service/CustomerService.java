package com.zubentsov.crmsystem.service;

import java.util.List;

import com.zubentsov.crmsystem.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveOrUpdateCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String searchName);

}
