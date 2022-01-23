package com.stock.market.Stock.Repository;

import com.stock.market.Stock.Model.Stock;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StockRepository extends MongoRepository<Stock, Integer> {
	
	@Query("{ startDate : ?0, endDate : ?0 }")  
	public List<Stock> getStocksBasedOnDates(LocalDate startDate,LocalDate endDate);
}
