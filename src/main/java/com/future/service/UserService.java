package com.future.service;

import com.future.dto.UserDto;
import com.future.entity.User;
import com.future.error.CustomException;
import com.future.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final WebClient webClient;

    @Async
    public CompletableFuture<List<UserDto>> saveUser(MultipartFile file)
            throws CustomException {

        LocalTime start = LocalTime.now();
        log.info("Logging the start time :{} for parse Operation.", start);

        List<UserDto> parsedDtoUsers = parseCsv(file);
        log.info("Parsed the list of users with size:{} using Thread:{}",
                parsedDtoUsers.size(), Thread.currentThread().getName());

        //Make DTO to persistable object, save it and convert back to DTO
        List<User> parsedUsers = convertToUserList(parsedDtoUsers);
        userRepository.saveAll(parsedUsers);
        parsedDtoUsers = convertToUserDtoList(parsedUsers);

        LocalTime end = LocalTime.now();
        log.info("Logging the end time:{} after parsing. Total time taken:{} sec .", end, Duration.between(start, end));

        return CompletableFuture.completedFuture(parsedDtoUsers);
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
        return CompletableFuture.completedFuture(null);
    }

    private List<UserDto> parseCsv(MultipartFile file)
            throws CustomException {

        List<UserDto> users = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(
                            new InputStreamReader(file.getInputStream()));
            String line;
            int lineCounter = 0;
            while( null != (line = br.readLine())){

                String[] data = line.split(",");
                UserDto userDto = new UserDto();
                userDto.setName(data[0]);
                userDto.setEmail(data[1]);
                userDto.setGender(data[2]);
                if(lineCounter >= 1){
                    users.add(userDto);
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

    @Async
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
    private static List<User> convertToUserList(List<UserDto> userDtoList){
        UserDto u = new UserDto();
        log.info("Converted from {} to {}", userDtoList.getClass().getSimpleName(), u.getClass().getSimpleName());
        return u.copyToUserList(userDtoList);
    }
    private static List<UserDto> convertToUserDtoList(List<User> userList){
        User u = new User();
        log.info("Converted from {} to {}", userList.getClass().getSimpleName(), u.getClass().getSimpleName());
        return u.copyToUserDtoList(userList);
    }

    @Async
    public ResponseEntity<List<User>> clubMultipleThreads() {

        AtomicReference<List<User>> users = new AtomicReference<>(new ArrayList<>());

        //Create a CompletableFuture<List<User>> to add all threads together and extract its results to a List
        CompletableFuture<List<User>> userListCompletableFuture = CompletableFuture.allOf(
                findUserById(1), findUserById(3), findUserById(5), findUserById(7), findUserById(9),
                findUserById(11), findUserById(13), findUserById(15), findUserById(17)
        ).thenApply(c ->
                Stream.of(findUserById(1), findUserById(3), findUserById(5), findUserById(7), findUserById(9),
                        findUserById(11), findUserById(13), findUserById(15), findUserById(17))
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        );

        userListCompletableFuture.whenCompleteAsync((userList, throwable) -> {
            if(throwable == null){
                users.set(userList);
                log.info("Completable Future returned users");
            } else {
                log.error("Exception Occurred while clubbing multiple threads");
                throw new CustomException(throwable, "Unable to club multiple Threads");
            }
        });
        return ResponseEntity.ok(users.get());
    }
}
