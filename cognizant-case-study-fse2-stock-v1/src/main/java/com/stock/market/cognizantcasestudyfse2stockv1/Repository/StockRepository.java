package com.stock.market.cognizantcasestudyfse2stockv1.Repository;

import com.stock.market.cognizantcasestudyfse2stockv1.Model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, Integer> {
}
