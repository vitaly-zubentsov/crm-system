package com.zubentsov.crmsystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubentsov.crmsystem.entity.Customer;
import com.zubentsov.crmsystem.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerServise;

	@GetMapping("/customers")
	public List<Customer> getCustomers() {

		return customerServise.getCustomers();
	}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) throws Exception {
		
		Customer customer = customerServise.getCustomer(customerId);
		return customer;
	}
}
