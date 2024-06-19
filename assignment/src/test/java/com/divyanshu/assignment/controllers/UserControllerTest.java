package com.divyanshu.assignment.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.divyanshu.assignment.dtos.UserDto;
import com.divyanshu.assignment.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPhoneNumber("1234567890");
        userDto.setName("John Doe");
    }

    @Test
    public void testCreateUser_Success() throws Exception {
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreateUser_InvalidEmail() throws Exception {
        userDto.setEmail("invalid-email");

        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateUser_InvalidPhoneNumber() throws Exception {
        userDto.setPhoneNumber("invalid-phone");

        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFetchAllUsers_Success() throws Exception {
        List<UserDto> userDtos = Arrays.asList(userDto, new UserDto(1l, "jane@example.com","Jane Doe","asdfasdfasdf", "0987654321"));
        when(userService.fetchAllUsers()).thenReturn(userDtos);

        mockMvc.perform(get("/api/user/fetch"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@example.com"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"));
    }

    @Test
    public void testFetchUserById_Success() throws Exception {
        when(userService.fetchById(1L)).thenReturn(userDto);

        mockMvc.perform(get("/api/user/fetch/1")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }
}
