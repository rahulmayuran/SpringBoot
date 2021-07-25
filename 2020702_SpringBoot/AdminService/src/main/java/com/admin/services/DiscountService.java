package com.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entities.Discount;
import com.admin.repositories.DiscountRepository;

@Service
public class DiscountService {

	@Autowired
	DiscountRepository discountRepository;
	
	public Discount saveDiscount(Discount discount) {
		return discountRepository.save(discount);
	}
	
	public List<Discount> getAllDiscounts(){
		return discountRepository.findAll();
	}
	
	public String deleteDiscount(String name) {
		discountRepository.deleteDiscountBydiscountName(name);
		return "Flight Deleted";
	}
}
