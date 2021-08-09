package com.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.admin.AdminApplication;
import com.admin.entities.Discount;
import com.admin.repositories.DiscountRepository;
import com.admin.services.DiscountService;

@SpringBootTest(classes = {Discount.class,AdminApplication.class})
public class DiscountTests {

	@Autowired
	DiscountService discountService;
	
	@MockBean
	DiscountRepository discountRepository;
	
	@Test
	public void savingDiscount() 
	{
		Discount disc = new Discount(1,"WINTER",25,"ACTIVE");
		
		Mockito
			.when(discountRepository.save(disc))
				.thenReturn(
						disc
				);
		Discount discount = discountService.saveDiscount(disc);
		Assertions.assertSame(discount, disc);
	}
	
	@Test
	public void getAllTheDiscounts() {
		Mockito
			.when(discountRepository.findAll())
				.thenReturn( Arrays.asList(
						new Discount(1, "NEW",22,"ACTIVE")
						,new Discount(2,"FLY100",12,"INACTIVE") )
					);
		List<Discount> discountList = discountService.getAllDiscounts();
		Assertions.assertSame(2, discountList.size());
 	}
	
	@Test
	public void DeleteDiscountTest()
	{
		int id = 1;
		discountService.deleteDiscount(id);
		Mockito.verify(discountRepository, Mockito.times(1)).deleteById(id);
	}
	


}
