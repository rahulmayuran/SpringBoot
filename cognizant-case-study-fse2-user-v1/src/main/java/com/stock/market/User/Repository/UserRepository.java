package com.stock.market.User.Repository;

import com.stock.market.User.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

    User getUserByUsername(String name) ;
}
