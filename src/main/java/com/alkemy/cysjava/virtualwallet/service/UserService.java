package com.alkemy.cysjava.virtualwallet.service;

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

    @Autowired
    @NotEmpty
    @NotNull
    @NotBlank
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }

}
