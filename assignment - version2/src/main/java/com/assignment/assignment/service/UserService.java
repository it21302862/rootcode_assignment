package com.assignment.assignment.service;

import com.assignment.assignment.DTO.ResponseDTO.UserResponseDTO;
import com.assignment.assignment.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserResponseDTO getUserById(Integer userId);

    public User getUser(Integer userId);

    public List<UserResponseDTO> getAllUsers();
}
