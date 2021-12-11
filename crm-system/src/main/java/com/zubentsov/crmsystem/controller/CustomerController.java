package com.zubentsov.crmsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zubentsov.crmsystem.dao.CustomerDao;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// to work with the database requires inject customerDao
	@Autowired
	private CustomerDao customerDao;
	
	@RequestMapping("/list")
	public String listCustomer(Model model) {
		
		//get customerList and set to model
		model.addAttribute("customers", customerDao.getCustomers());
		
		return "list-customers";
	}

}
