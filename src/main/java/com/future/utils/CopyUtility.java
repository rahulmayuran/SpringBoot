package com.future.utils;

import com.future.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CopyUtility<S, T> {

    //Copy from One Class to Another
    public T copy(S sourceType, Class<T> target){
        T targetType = BeanUtils.instantiateClass(target);
        BeanUtils.copyProperties(sourceType, targetType);
        log.info("Copied from Source {} to Target {}", sourceType, targetType);
        return targetType;
    }

    //Copy from One List<Class> to another List<Class>
    public List<T> copyList(List<S> sourceTypeList, Class<T> target){
        List<T> targetTypeList = new ArrayList<>();
        if(null == sourceTypeList || sourceTypeList.isEmpty()){
            return targetTypeList;
        }
        for(S sourceType : sourceTypeList){
            T targetType = BeanUtils.instantiateClass(target);
            BeanUtils.copyProperties(sourceType, targetType);
            targetTypeList.add(targetType);

        }
        log.info("Copied from SourceList {} to TargetList {}", sourceTypeList.getClass().getSimpleName(), targetTypeList.getClass().getSimpleName());
        return targetTypeList;
    }
}
