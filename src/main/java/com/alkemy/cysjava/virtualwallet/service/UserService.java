package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.UserDTO;
import com.alkemy.cysjava.virtualwallet.exception.NotFoundException;
import com.alkemy.cysjava.virtualwallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDTO userDTO;

    @Autowired
    public UserService(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public User addUser(User user) {
        return userDTO.save(user);
    }

}
