package com.divyanshu.assignment.controllers;

import com.divyanshu.assignment.dtos.UserDto;
import com.divyanshu.assignment.entities.User;
import com.divyanshu.assignment.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("api/user/")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("register")
    public UserDto createUser( @RequestBody @Valid UserDto user){
        return userService.createUser(user);
    }
    @GetMapping("fetch")
    public List<UserDto> fetchAllUsers(){
        return userService.fetchAllUsers();
    }

    @GetMapping("fetch/{id}")
    public UserDto fetchUserById(@RequestParam Long id){
        return userService.fetchById(id);
    }


}
