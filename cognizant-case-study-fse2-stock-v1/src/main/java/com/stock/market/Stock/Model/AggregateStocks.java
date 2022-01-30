package com.stock.market.Stock.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AggregateStocks {
	
	private String companyName;
	private float avgStockPrice;
	private float minStockPrice;
	private float maxStockPrice;

}
