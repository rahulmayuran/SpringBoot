package com.future.entity;

import com.future.dto.UserDto;
import com.future.utils.CopyUtility;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "USER_TBL")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String gender;

    public UserDto copytoUserDto(){
        CopyUtility<User, UserDto> utils = new CopyUtility<>();
        return utils.copy(this, UserDto.class);
    }

    public List<UserDto> copyToUserDtoList(List<User> users){
        CopyUtility<User, UserDto> utilsList = new CopyUtility<>();
        return utilsList.copyList(users, UserDto.class);
    }
}
