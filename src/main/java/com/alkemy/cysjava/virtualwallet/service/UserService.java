package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.UserCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserUpdateDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.UserMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.repositories.UserRepository;
import com.alkemy.cysjava.virtualwallet.models.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            throw new BadRequestException("There is already a user with that email");
        }

        userCreationDTO.setFirstname(userCreationDTO.getFirstname().trim());
        userCreationDTO.setLastname(userCreationDTO.getLastname().trim());
        userCreationDTO.setEmail(userCreationDTO.getEmail().trim());
        userCreationDTO.setPassword(userCreationDTO.getPassword().trim());

        User user = userMapper.toUser(userCreationDTO);
        user.setCreationDate(new Timestamp(System.currentTimeMillis()));
        User createdUser = userRepository.save(user);
        return userMapper.toUserDTO(createdUser);
    }

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

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        return optionalUser.get();
    }

    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException("User not found");
        }else{
            User user = optionalUser.get();
            user.setSoftDelete(true);
            userRepository.save(user);
        }
    }

    public UserDTO findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        User user = optionalUser.get();
        return userMapper.toUserDTO(user);
    }

    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        //Valido que userUpdateDTO no sea un objeto vacío
        if(userUpdateDTO.getFirstname() == null && userUpdateDTO.getLastname() == null && userUpdateDTO.getPassword() == null){
            throw new BadRequestException("There is nothing to update");
        }

        //Valido que exista el usuario en la base de datos para actualizar
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException("User not found");
        }
        User user = optionalUser.get();

        if(userUpdateDTO.getFirstname() != null){
            if(userUpdateDTO.getFirstname().equals("")){
                throw new BadRequestException("firstname can't be empty");
            }
            userUpdateDTO.setFirstname(userUpdateDTO.getFirstname().trim());
            user.setFirstName(userUpdateDTO.getFirstname());
        }

        if(userUpdateDTO.getLastname() != null){
            if(userUpdateDTO.getLastname().equals("")){
                throw new BadRequestException("lastname can't be empty");
            }
            userUpdateDTO.setLastname(userUpdateDTO.getLastname().trim());
            user.setLastName(userUpdateDTO.getLastname());
        }

        if(userUpdateDTO.getPassword() != null){
            if(userUpdateDTO.getPassword().equals("")){
                throw new BadRequestException("password can't be empty");
            }
            userUpdateDTO.setPassword(userUpdateDTO.getPassword().trim());
            user.setPassword(userUpdateDTO.getPassword());
        }

        userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

}
