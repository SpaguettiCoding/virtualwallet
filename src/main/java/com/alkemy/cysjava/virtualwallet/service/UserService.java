package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.UserCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.EntityAlreadyExistsException;
import com.alkemy.cysjava.virtualwallet.mappers.UserMapper;
import com.alkemy.cysjava.virtualwallet.repositories.UserRepository;
import com.alkemy.cysjava.virtualwallet.models.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    public UserDTO addUser(UserCreationDTO userCreationDTO) {
        //Valido que no exista un usuario con ese email en la base
        Optional<User> optionalUser = userRepository.findByEmail(userCreationDTO.getEmail());

        if(optionalUser.isPresent()){
            throw new EntityAlreadyExistsException("There is already a user with that email");
        }

        //Si firstName es igual a "   Nombre   " este metodo lo transforma en "Nombre"
        userCreationDTO.setFirstname(userCreationDTO.getFirstname().trim());
        userCreationDTO.setLastname(userCreationDTO.getLastname().trim());
        userCreationDTO.setEmail(userCreationDTO.getEmail().trim());
        userCreationDTO.setPassword(userCreationDTO.getPassword().trim());

        User user = userMapper.toUser(userCreationDTO);
        user.setCreationDate(new Timestamp(System.currentTimeMillis()));
        User createdUser = userRepository.save(user);
        return userMapper.toUserDTO(createdUser);
    }

//    public List<User> findAllUser() { return userRepository.findAll(); }

    public List<UserDTO> findAllUserDTO() {
        List<User> user = userRepository.findAll();
        List<UserDTO> userDTO = new ArrayList<>();
        for (User user1 : user) {
            if (user1 != null) {
                UserDTO dto = userMapper.toUserDTO(user1);
                userDTO.add(dto);
            }
        }
        return userDTO;
    }

    public boolean deleteUserById(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}
