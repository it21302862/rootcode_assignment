package com.assignment.assignment.service;

import com.assignment.assignment.DTO.ResponseDTO.UserResponseDTO;
import com.assignment.assignment.entity.Book;
import com.assignment.assignment.entity.User;
import com.assignment.assignment.exception.BookNotFoundException;
import com.assignment.assignment.exception.UserNotFoundException;
import com.assignment.assignment.mapper.BookMapper;
import com.assignment.assignment.mapper.UserMapper;
import com.assignment.assignment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO getUserById(Integer userId) {
        return UserMapper.userToUserResponseDto(getUser(userId));
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            logger.warn("User with ID {} not found", userId);
            return new UserNotFoundException(
                    "user with id: " + userId + " could not be found");
        });
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = StreamSupport
                .stream(userRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return UserMapper.userToUserResponseDtos(users);
    }
}
