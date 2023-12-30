package com.future.cache;

import com.future.dto.UserDto;
import com.future.repository.UserRepository;
import com.google.common.cache.CacheLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCacheLoader extends CacheLoader<String, UserDto> {

    private final UserRepository repository;

    @Override
    public UserDto load(String userId) {

        log.info("UserCacheLoader :: load() method call");
        if(null != userId){
            //Cache the userDto object using the id
            UserDto userDto = repository.getById(Integer.valueOf(userId)).copytoUserDto();
            if(null != userDto){
                return userDto;
            }
        }
        return UserDto.builder().build();
    }

/*
    @Override
    public Map<String, UserDto> loadAll(Iterable<? extends String> keys) throws Exception {

        Map<String, List<User>> loadedEntries = new HashMap<>();
        List<User> userList = repository.findAll();
        for (String key : keys) {
            loadedEntries.put(key, userList);
        }
        log.info("UserCacheLoader :: load() method call, Cached the List of users using");
        return loadedEntries;
    }*/
}
