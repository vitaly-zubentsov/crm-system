package com.zubentsov.crmsystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
		
		if (customer == null) {
			throw new CustomerNotFoundException("Customer id not found: " + customerId);
		}
		return customer;
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
		
		CustomerErrorResponse error = new CustomerErrorResponse(
				HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
		
		CustomerErrorResponse error = new CustomerErrorResponse(
				HttpStatus.BAD_REQUEST .value(), exc.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
}
