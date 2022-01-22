package com.stock.market.User.Controller;

import com.stock.market.User.Model.User;
import com.stock.market.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) {
        System.out.println("Save the user from Spring Boot - "+user);
        return userService.saveUser(user);
    }

    @GetMapping("/getUsers")
    public List<User> names()
    {
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
        return users;
    }
}
