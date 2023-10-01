package com.future.service;

import com.future.entity.User;
import com.future.error.CustomException;
import com.future.repository.UserRepository;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final WebClient webClient;

    @Async
    public CompletableFuture<List<User>> saveUser(MultipartFile file)
            throws CustomException {

        long start = System.currentTimeMillis();
        log.info("Logging the start time :{} for parse Operation.", start/1000/60/60);

        List<User> parsedUsers = parseCsv(file);
        log.info("Parsed the list of users with size:{} using Thread:{}",
                parsedUsers.size(), Thread.currentThread().getName());

        userRepository.saveAll(parsedUsers);

        long end = System.currentTimeMillis();
        log.info("Logging the end time:{} after parsing. Total time taken:{} sec .", end/1000/60/60, (end - start)/1000);

        return CompletableFuture.completedFuture(parsedUsers);
    }

    @Async
    public CompletableFuture<List<User>> findAllUsers(){

        List<User> allUsers = userRepository.findAll();
        log.info("findAllUsers() :: Obtained {} users from the CSV file for Thread:{}.",
                allUsers.size(), Thread.currentThread().getName());

        return CompletableFuture.completedFuture(allUsers);
    }

    @Async
    public CompletableFuture<User> findUserById(int id){

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User singleUser = user.get();
            log.info("findUserById() :: Obtained {} user from the CSV file for Thread:{}.",
                    singleUser, Thread.currentThread().getName());

            return CompletableFuture.completedFuture(singleUser);
        }
        log.error("No user found for provided Id");
        return null;
    }

    private List<User> parseCsv(MultipartFile file)
            throws CustomException {

        List<User> users = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(
                            new InputStreamReader(file.getInputStream()));
            String line;
            int lineCounter = 0;
            while( null != (line = br.readLine())){

                String[] data = line.split(",");
                User user = new User();
                user.setName(data[0]);
                user.setEmail(data[1]);
                user.setGender(data[2]);
                if(lineCounter >= 1){
                    users.add(user);
                }
                lineCounter++;

            }
            log.info("Parsing {} users from CSV file to List of Users.", lineCounter);
            return users;

        } catch (IOException e) {
            log.error("Error occurred while parsing the CSV file :{} from {}", e.getMessage(),
                    Thread.currentThread().getName());
            throw new CustomException(e);
        }
    }

    public CompletableFuture<List<User>> getUsersUsingWebClient() {

        CompletableFuture<List<User>> resultingFuture = new CompletableFuture<>();

        //Async call for first response
        /*Flux<User> responseAll = webClient.get()
                .uri("http://localhost:9000/users")
                .retrieve()
                .bodyToFlux(User.class);*/

        Mono<User> response = webClient.get()
                .uri("http://localhost:9000/users/28")
                .retrieve()
                .bodyToMono(User.class);

        //Async call for secondResponse
        Mono<User> responseOne = webClient.get()
                .uri("http://localhost:9000/users/22")
                .retrieve()
                .bodyToMono(User.class);

        response.subscribe(
                o1 -> responseOne.subscribe(
                        o2 -> {
                            log.info("Combining two responses {} and {}", o1, o2);
                            resultingFuture.complete(Arrays.asList(o2, o1));
                        },
                        resultingFuture::completeExceptionally
                ),
                resultingFuture::completeExceptionally);

        return resultingFuture;
    }
}
