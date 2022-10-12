package com.example.projectteachmeskills.mapper;

import com.example.projectteachmeskills.dto.UserDTO;
import com.example.projectteachmeskills.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    List<UserDTO> toUserDTOList(List<User> users);

}

