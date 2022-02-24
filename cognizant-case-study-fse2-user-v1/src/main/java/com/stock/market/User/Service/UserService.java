package com.stock.market.User.Service;

import com.stock.market.User.Model.User;
import com.stock.market.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> getUserById(int id) {
    	return userRepository.findById(id);
    }
    
    public Optional<User> getUserByName(String name) {
    	return Optional.of(userRepository.getUserByUsername(name));
    }
    
    public void deleteuser(int id) {
    	userRepository.deleteById(id);
    }
    
}
