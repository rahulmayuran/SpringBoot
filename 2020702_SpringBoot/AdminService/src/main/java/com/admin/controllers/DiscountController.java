package com.admin.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.Exceptions.DiscountException;
import com.admin.entities.Admin;
import com.admin.entities.Discount;
import com.admin.services.AdminService;
import com.admin.services.DiscountService;

@RestController
@RequestMapping("/api/v1.0/admin")
@CrossOrigin()
public class DiscountController 
{
	
	@Autowired
	DiscountService discountService;
	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/discount/getDiscounts")
	public List<Discount> discounts() {
		System.out.println("Get all discounts from spring boot");
		List<Discount> allDiscounts = discountService.getAllDiscounts();
		return allDiscounts;
	}
	
	/**
	 * 
	 * @param saveDiscount
	 * @return
	 * @throws DiscountException
	 * This method will save and also updates the values, if in case the discount Name is same.
	 */
	@PostMapping("/discount/register")
	public Discount saveDiscountToDB(@RequestBody Discount saveDiscount) throws DiscountException
	{
			Optional<Discount> getDiscountById =  discountService.getDiscountById(saveDiscount.getDiscountId());
			System.out.println("Optional to fetch discount by id "+ getDiscountById);
			System.out.println("Name "+ saveDiscount.getDiscountName());
			
			List<Discount> alreadyExistingDiscounts = discountService.getAllDiscounts();
			System.out.println(alreadyExistingDiscounts);
			
			int counter = 0;
			
			for (int i=0; i < alreadyExistingDiscounts.size() ; i++) 
			{
				System.out.println("From Already present list "+ alreadyExistingDiscounts.get(i) );
				System.out.println("Discount to be saved " + saveDiscount);
				
				try 
				{
					if(saveDiscount.getDiscountName().equals( alreadyExistingDiscounts.get(i).getDiscountName() ) ) 
					{
						if(saveDiscount.getDiscountValue() != alreadyExistingDiscounts.get(i).getDiscountValue())
						{
							alreadyExistingDiscounts.get(i).setDiscountValue(saveDiscount.getDiscountValue());
							discountService.saveDiscount(alreadyExistingDiscounts.get(i));
							counter += 1;
							System.out.println("New value set to "+ saveDiscount.getDiscountValue());
						}
						else 
						{
							counter += 1;
							System.out.println("Conflicting Discount " + saveDiscount.getDiscountName());	
							throw new DiscountException("From exception, It is conflicting man, Try adding some other");
						}
					}
					
					} catch (DiscountException e) {
						System.out.println("Exception handled "+ e);
					}
				
			}
			
			if(counter == 0) 
			{
				System.out.println("New Discount " + saveDiscount.getDiscountName());
				System.out.println("Save the Discount from Spring boot - "+saveDiscount);
				return discountService.saveDiscount(saveDiscount);

			}
			return saveDiscount;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/discount/deleteDiscount/{id}")
	public String deleteFlightById(@PathVariable int id){
		try {
			System.out.println("Deleting Flight.." + id);
			discountService.deleteDiscount(id);
			return "Deleted Discount(Spring Boot) with id "+ id;
		} catch (Exception e) {
			return "Some Error Occured while deleting";
		}
	}
}
