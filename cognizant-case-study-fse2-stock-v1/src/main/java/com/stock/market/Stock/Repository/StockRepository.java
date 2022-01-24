package com.stock.market.Stock.Repository;

import com.stock.market.Stock.Model.Stock;

import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StockRepository extends MongoRepository<Stock, Integer> {
	
	@Query("{'stockDate' : { $gte: ?0, $lte: ?1 } }") 
	public List<Stock> getStocksBasedOnDates(Date startDate,Date endDate);
}
