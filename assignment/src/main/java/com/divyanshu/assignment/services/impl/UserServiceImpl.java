package com.divyanshu.assignment.services.impl;

import com.divyanshu.assignment.dtos.UserDto;
import com.divyanshu.assignment.entities.User;
import com.divyanshu.assignment.exceptions.EmailAlreadyExistsException;
import com.divyanshu.assignment.exceptions.UserNotFoundException;
import com.divyanshu.assignment.repositories.UserRepository;
import com.divyanshu.assignment.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDto> fetchAllUsers() {
        List<UserDto> users = userRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserDto fetchById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found with id:"+id));
        return mapToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new EmailAlreadyExistsException("User with email "+userDto.getEmail()+" already exists");
        }
        if(userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            throw new EmailAlreadyExistsException("User with email "+userDto.getPhoneNumber()+" already exists");
        }
        User user= mapFromDto(userDto);

        return mapToDto(userRepository.save(user));
    }
    private UserDto mapToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
    private User mapFromDto(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
}
