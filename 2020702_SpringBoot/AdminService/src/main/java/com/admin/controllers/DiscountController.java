package com.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entities.Admin;
import com.admin.entities.Discount;
import com.admin.services.AdminService;
import com.admin.services.DiscountService;

@RestController
@RequestMapping("/api/v1.0/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class DiscountController 
{
	
	@Autowired
	DiscountService discountService;
	
	
	//To get all the registered users from DB
	@GetMapping("/discount/getDiscounts")
	public List<Discount> discounts() {
		System.out.println("Get all discounts from spring boot");
		List<Discount> allDiscounts = discountService.getAllDiscounts();
		return allDiscounts;
	}
	
	@PostMapping("/discount/register")
	public Discount saveDiscountToDB(@RequestBody Discount saveDiscount)
	{
		System.out.println("Save the Discount from Spring boot - "+saveDiscount);
		return discountService.saveDiscount(saveDiscount);
	}
	
	@DeleteMapping("/discount/deleteDiscount/{id}")
	public String deleteFlightById(@PathVariable int id){
		try {
			System.out.println("Deleting Flight.." + id);
			discountService.deleteDiscount(id);
			return "Deleted Discount(Spring Boot) with id "+ id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Some Error Occured while deleting";
		}
	}
}
