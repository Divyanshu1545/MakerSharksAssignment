package com.divyanshu.assignment.services;

import com.divyanshu.assignment.dtos.UserDto;
import com.divyanshu.assignment.entities.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface UserService {
    List<UserDto> fetchAllUsers();
    UserDto fetchById(Long id);
    UserDto createUser(UserDto user);
}
