package com.divyanshu.assignment.services;
import com.divyanshu.assignment.dtos.UserDto;
import com.divyanshu.assignment.entities.User;
import com.divyanshu.assignment.exceptions.EmailAlreadyExistsException;
import com.divyanshu.assignment.repositories.UserRepository;
import com.divyanshu.assignment.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPhoneNumber("1234567890");
        userDto.setName("John Doe");
    }

    @Test
    public void testCreateUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(userDto);
        });
    }
}
