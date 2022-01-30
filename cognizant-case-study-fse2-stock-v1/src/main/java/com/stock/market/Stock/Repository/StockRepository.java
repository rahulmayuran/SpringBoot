package com.stock.market.Stock.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stock.market.Stock.Model.Stock;

public interface StockRepository extends MongoRepository<Stock, String> {
	
	@Query("{'stockDate' : { $gte: ?0, $lte: ?1 } }") 
	public List<Stock> getStocksBasedOnDates(Date startDate, Date endDate);

}
