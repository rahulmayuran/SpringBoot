package com.admin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entities.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer>{

	public Discount deleteDiscountBydiscountName(String name);
}
