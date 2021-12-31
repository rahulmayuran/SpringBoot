package com.stock.market.Company.Repository;

import com.stock.market.Company.Model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, Integer> {
}
