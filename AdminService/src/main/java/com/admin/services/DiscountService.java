package com.admin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entities.Discount;
import com.admin.repositories.DiscountRepository;

@Service
public class DiscountService {

	@Autowired
	DiscountRepository discountRepository;
	
	 public Optional<Discount> getDiscountById(int id) 
	 {
	        return discountRepository.findById(id);
	 }
	
	public Discount saveDiscount(Discount discount) {
		return discountRepository.save(discount);
	}
	
	public List<Discount> getAllDiscounts(){
		return discountRepository.findAll();
	}
	
	public String deleteDiscount(int id) {
		discountRepository.deleteById(id);
		return "Flight Deleted";
	}

}
