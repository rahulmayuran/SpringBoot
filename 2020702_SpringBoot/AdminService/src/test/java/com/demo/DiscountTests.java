package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	
	//Testing save method
	@Test
	public void savingDiscount() 
	{
		List<Discount> discList = null;
		
		//TO check whether the object is initialized and not null, This will fail obviously. Henceforth, commenting
		
		//assertNotNull(discList);
		
		discList = Arrays.asList( new Discount(1,"WINTER",25,"ACTIVE"),new Discount(1,"WINTER",25,"ACTIVE") );
		assertNotNull(discList);
		
		//A simple check of size 
		assertEquals(2, discList.size());
		
		//A simple check of other parameters
		assertEquals(1, discList.get(0).getDiscountId());
		assertEquals("WINTER", discList.get(0).getDiscountName());
		assertEquals(25, discList.get(0).getDiscountValue());
		assertEquals("ACTIVE", discList.get(0).getStatus());

		
		
		//Interaction with Service and DB layers
		Discount disc = new Discount(1,"WINTER",25,"ACTIVE");
		
		Mockito
			.when(discountRepository.save(disc))
				.thenReturn(
						disc
				);
		Discount discount = discountService.saveDiscount(disc);
		Assertions.assertSame(discount, disc);
	}
	
	//Testing the get all discount List method
	@Test
	public void getAllTheDiscounts() {
		
		List<Discount> discList = Arrays.asList( 
				new Discount(1,"WINTER",25,"ACTIVE"),
				new Discount(2,"SUMMER",25,"ACTIVE") );
		
		Discount discountSaved = new Discount(3, "RAINY", 22, "ACTIVE");
		discountService.saveDiscount(discountSaved);
		
		Mockito
			.when(discountRepository.findAll())
				.thenReturn( discList );
		
		List<Discount> discountList = discountService.getAllDiscounts();
		
		assertEquals(2, discountList.size());
		assertEquals(2, discList.size());
		Assertions.assertSame(2, discountList.size());
 	}
	
	//Deleting the discount based on id;
	@Test
	public void DeleteDiscountTest()
	{
		int id = 1;
		discountService.deleteDiscount(id);
		Mockito.verify(discountRepository, Mockito.times(1)).deleteById(id);
	}
	


}
