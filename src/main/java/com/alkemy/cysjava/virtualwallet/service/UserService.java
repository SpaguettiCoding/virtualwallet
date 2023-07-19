package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.mappers.UserMapper;
import com.alkemy.cysjava.virtualwallet.repositories.UserRepository;
import com.alkemy.cysjava.virtualwallet.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    public UserDTO addUser(User user) {
        //Faltan todas las validaciones, encriptacion y manejo de errores
        //Validaciones podrian estar en otro metodo dentro del service -> public User verifyUser(User user)
        UserDTO userDTO = userMapper.toUserDTO(user);

        userRepository.save(user);
        return userDTO;
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
