package com.zubentsov.crmsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zubentsov.crmsystem.entity.Customer;
import com.zubentsov.crmsystem.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// to work with the database requires inject customer Service
	@Autowired
	private CustomerService customerServise;

	@GetMapping("/list")
	public String listCustomer(Model model) {

		// get customerList and set to model
		model.addAttribute("customers", customerServise.getCustomers());

		return "list-customers";
	}

	@GetMapping("/addCustomer")
	public String showCustomerAddForm(Model model) {

		Customer customer = new Customer();
		// create model attribute to bind form data
		model.addAttribute("customer", customer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {

		// add customer to DB
		customerServise.saveOrUpdateCustomer(customer);

		return "redirect:/customer/list";
	}

	@GetMapping("/updateCustomer")
	public String showCustomerUpdateForm(@ModelAttribute("customerId") int customerId, Model model) {

		// get customer from DB
		Customer customer = customerServise.getCustomer(customerId);

		// set customer as a model attribute to pre-populate the form
		model.addAttribute("customer", customer);

		// send over to form
		return "customer-form";
	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@ModelAttribute("customerId") int customerId) {

		// delete customer from DB
		customerServise.deleteCustomer(customerId);

		// send over to form
		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String searchCustomers(@ModelAttribute("searchName") String searchName, Model model) {

		// search customer and add search result to model
		model.addAttribute("customers", customerServise.searchCustomers(searchName));

		return "list-customers";
	}

}
