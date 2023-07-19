package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.UserCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.mappers.UserMapper;
import com.alkemy.cysjava.virtualwallet.models.User;
import com.alkemy.cysjava.virtualwallet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserCreationDTO userCreationDTO) {
        UserDTO newUserDTO = userService.addUser(userCreationDTO);
        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllUser () {
//        List<User> user = userService.findAllUser();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUserDTO () {
        List<UserDTO> userDTO = userService.findAllUserDTO();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}

