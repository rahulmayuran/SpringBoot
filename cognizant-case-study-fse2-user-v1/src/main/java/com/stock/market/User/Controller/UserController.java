package com.stock.market.User.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.market.User.Config.Constants;
import com.stock.market.User.Exception.UserException;
import com.stock.market.User.Model.User;
import com.stock.market.User.Service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/user")
@CrossOrigin()
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) {

        try {
            user.setUserId(returnMaxId() + 1);
            user.setRole(Constants.USER);
            log.info("Save the user from Spring Boot - " + user);
            kafkaTemplate.send("fse_user", mapper.writeValueAsString("Adding this user -> "+ user));
        } catch (Exception e) {
            log.error("Exception while saving user " + e.getMessage());
            kafkaTemplate.send("fse_user", e.getMessage());
        }
        return userService.saveUser(user);

    }

    @GetMapping("/getUsers")
    public List<User> names() throws JsonProcessingException {
        List<User> users = userService.getAllUsers();
        log.info("Total list of users, " + users);
        kafkaTemplate.send("fse_user",
                mapper.writeValueAsString("List of users -> "+users.stream()
                        .map(u->u.getUsername())
                        .collect(Collectors.toList())
                ));
        return users;
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUserById(@PathVariable int id) throws UserException,JsonProcessingException {

        Optional<User> singleUser = userService.getUserById(id);
        kafkaTemplate.send("fse_user", mapper.writeValueAsString("Fetched single user with id, " + id + " ,value = " + singleUser));
        log.info("Fetched single user with id, " + id + " ,value = " + singleUser);
        return singleUser;
    }

    @GetMapping("/getUsername/{name}")
    public Optional<User> getUserByName(@PathVariable String name) throws UserException,JsonProcessingException {

        Optional<User> singleUser = userService.getUserByName(name);
        kafkaTemplate.send("fse_user", mapper.writeValueAsString("Fetched single user with name, " + name + ", value = " + singleUser));
        log.info("Fetched single user with name, " + name + ", value = " + singleUser);
        return singleUser;
    }

    @PutMapping("/getUsername/{name}")
    public Optional<User> changeRoleToAdmin(@PathVariable String name) throws UserException {

        Optional<User> singleUser = userService.getUserByName(name);
        log.info("Fetched single user with name, " + name + ", value = " + singleUser);
        singleUser.ifPresent(u->u.setRole(Constants.ADMIN));
        return singleUser;
    }

    @DeleteMapping("/getUser/{id}")
    public Optional<User> deleteSingleUser(@PathVariable int id) throws UserException,JsonProcessingException {

        log.info("About to Delete the user");
        kafkaTemplate.send("fse_user", "About to Delete the user");
        Optional<User> deleteSingleUser = userService.getUserById(id);
        kafkaTemplate.send("fse_user", mapper.writeValueAsString("Deleted user with id, " + id));
        log.info("Deleted user with id, " + id);
        userService.deleteuser(id);
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
