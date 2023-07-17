package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.repositories.UserRepository;
import com.alkemy.cysjava.virtualwallet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }

}
