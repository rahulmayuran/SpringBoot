package com.admin.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int discountId;
	private String discountName;
	private int discountValue;
	
	private String status;
	
	public Discount() {
		
	}
	
	public Discount(int discountId, String discountName, int discountValue,
			String status) {
		this.discountId = discountId;
		this.discountName = discountName;
		this.discountValue = discountValue;
		this.status = status;
	}



	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public int getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(int discountValue) {
		this.discountValue = discountValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
