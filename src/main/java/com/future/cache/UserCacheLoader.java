package com.future.cache;

import com.future.dto.UserDto;
import com.google.common.cache.CacheLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCacheLoader extends CacheLoader<String, UserDto> {


    @Override
    public UserDto load(String key) throws Exception {
        return null;
    }
}
