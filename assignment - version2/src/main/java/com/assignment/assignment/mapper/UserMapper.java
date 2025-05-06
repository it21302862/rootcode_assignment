package com.assignment.assignment.mapper;

import com.assignment.assignment.DTO.ResponseDTO.UserResponseDTO;
import com.assignment.assignment.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public static UserResponseDTO userToUserResponseDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static List<UserResponseDTO> userToUserResponseDtos(List<User> users) {
        List<UserResponseDTO> UserResponseDTOs = new ArrayList<>();
        for(User user:users){
            UserResponseDTOs.add(userToUserResponseDto(user));
        }
        return UserResponseDTOs;
    }
}
