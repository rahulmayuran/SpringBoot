package com.admin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
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

	@Override
	public String toString() {
		return "Discount [discountId=" + discountId + ", discountName=" + discountName + ", discountValue="
				+ discountValue + ", status=" + status + "]";
	}
	
	
	
}
