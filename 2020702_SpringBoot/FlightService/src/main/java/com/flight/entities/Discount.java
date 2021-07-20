package com.flight.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Discount {

	@Column(name="DID")
	private int discountId;
	
	@Column(name="DNAME")
	private String discountName;
	
	@Column(name="DVALUE")
	private String discountValue;
	
	public Discount() {
		
	}
	
	public Discount(int discountId, String discountName, String discountValue) {
		super();
		this.discountId = discountId;
		this.discountName = discountName;
		this.discountValue = discountValue;
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

	public String getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}
	
	
	
}
