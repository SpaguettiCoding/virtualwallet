package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.UserCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserUpdateDTO;
import com.alkemy.cysjava.virtualwallet.mappers.UserMapper;
import com.alkemy.cysjava.virtualwallet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserCreationDTO userCreationDTO) {
        UserDTO newUserDTO = userService.addUser(userCreationDTO);
        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO){
        UserDTO newUserDTO = userService.updateUser(id, userUpdateDTO);
        return new ResponseEntity<>(newUserDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUserDTO () {
        List<UserDTO> userDTO = userService.findAllUserDTO();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

   @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO userDTO = userService.findUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
   }

}

