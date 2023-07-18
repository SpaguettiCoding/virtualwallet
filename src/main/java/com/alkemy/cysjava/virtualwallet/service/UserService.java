package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.UserCreationDTO;
import com.alkemy.cysjava.virtualwallet.mappers.UserMapper;
import com.alkemy.cysjava.virtualwallet.repositories.UserRepository;
import com.alkemy.cysjava.virtualwallet.models.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    public UserCreationDTO addUser(UserCreationDTO userDTO) {
        //Faltan todas las validaciones, encriptacion y manejo de errores
        //Validaciones podrian estar en otro metodo dentro del service -> public User verifyUser(User user)
        User user = userMapper.toUser(userDTO);
        user.setCreationDate(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        return userDTO;
    }

}
