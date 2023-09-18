package com.future.controller;

import com.future.error.CustomException;
import com.future.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<String> saveUsers(@RequestParam(value = "files")MultipartFile[] files )
            throws CustomException {

        for(MultipartFile file : files){
            try {
                userService.saveUser(file);
            } catch (CustomException e) {
                log.error("UserController::Error while saving the file :{}", e.getMessage());
                throw new CustomException(e);
            }
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .allow(HttpMethod.POST)
                .body("Creating a List object from a CSV file at: "
                        + LocalDateTime.now() + ", for "+ files.length + " file(s)");
    }

    @GetMapping("/users")
    public CompletableFuture<ResponseEntity> findAllUsers(){
        return userService.findAllUsers()
                .thenApply(ResponseEntity::ok);
    }
}
