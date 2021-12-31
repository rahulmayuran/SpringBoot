package com.stock.market.Stock.Repository;

import com.stock.market.Stock.Model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, Integer> {
}
