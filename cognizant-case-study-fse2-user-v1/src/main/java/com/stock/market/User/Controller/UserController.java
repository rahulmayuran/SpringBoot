package com.stock.market.User.Controller;

import com.stock.market.User.Config.Constants;
import com.stock.market.User.Exception.UserException;
import com.stock.market.User.Model.User;
import com.stock.market.User.Service.UserService;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/user")
@CrossOrigin()
@Slf4j
public class UserController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public User saveUser(@RequestBody User user) {

        try {
            user.setUserId(returnMaxId() + 1);
            user.setRole(Constants.USER);
            log.info("Save the user from Spring Boot - " + user);
        } catch (Exception e) {
            log.error("Exception while saving user " + e.getMessage());
        }
        return userService.saveUser(user);

    }

    @GetMapping("/getUsers")
    public List<User> names() {
        List<User> users = userService.getAllUsers();
        log.info("Total list of users, " + users);
        return users;
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUserById(@PathVariable int id) throws UserException {

        Optional<User> singleUser = userService.getUserById(id);
        log.info("Fetched single user with id, " + id + " ,value = " + singleUser);
        logger.info("Fetched single user with id, " + id + " ,value = " + singleUser);
        return singleUser;
    }

    @GetMapping("/getUsername/{name}")
    public Optional<User> getUserByName(@PathVariable String name) throws UserException {

        Optional<User> singleUser = userService.getUserByName(name);
        log.info("Fetched single user with name, " + name + ", value = " + singleUser);
        return singleUser;
    }

    @DeleteMapping("/getUser/{id}")
    public Optional<User> deleteSingleUser(@PathVariable int id) throws UserException {

        log.info("About to Delete the user");
        Optional<User> deleteSingleUser = userService.getUserById(id);
        log.info("Deleted user with id, " + id);
        return deleteSingleUser;
    }

    private int returnMaxId() {
        List<User> allUsers = userService.getAllUsers();
        if (allUsers.size() == 0) {
            return 0;
        }
        int maxId = allUsers.stream()
                .max(Comparator.comparing(User::getUserId))
                .get()
                .getUserId();
        log.info("Max Id is " + maxId);
        return maxId;
    }
}
