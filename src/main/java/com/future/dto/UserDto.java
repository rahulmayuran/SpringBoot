package com.future.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.future.entity.User;
import com.future.utils.CopyUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty(value = "id")
    @NotBlank(message = "Id should not be empty")
    private int id;

    @JsonProperty(value = "name")
    @NotBlank(message = "Name should not be empty")
    private String name;

    @Email
    @JsonProperty(value = "email")
    @NotBlank(message = "Email should not be empty")
    private String email;

    @JsonProperty(value = "gender")
    @NotBlank(message = "Gender should not be empty")
    private String gender;

    public User copytoUser(){
        CopyUtility<UserDto, User> utils = new CopyUtility<>();
        return utils.copy(this, User.class);
    }

    public List<User> copyToUserList(List<UserDto> dtoList){
        CopyUtility<UserDto, User> listUtils = new CopyUtility<>();
        return listUtils.copyList(dtoList, User.class);
    }
}
