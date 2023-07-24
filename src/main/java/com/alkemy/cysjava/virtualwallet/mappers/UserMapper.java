package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.UserCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.User;
import com.alkemy.cysjava.virtualwallet.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserMapper {
    @Autowired
    RoleService roleService;

     public UserDTO toUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstName());
        dto.setLastname(user.getLastName());
        dto.setEmail(user.getEmail());
        if(user.getRole() != null){
            dto.setRole(user.getRole().getName());
        }else{
            dto.setRole("");
        }
        return dto;
    }

    public User toUser(UserCreationDTO dto){

         User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstname());
        user.setLastName(dto.getLastname());
        user.setPassword(dto.getPassword());
        user.setRole(roleService.findRoleByName(dto.getRole()).orElseThrow(() -> new ResourceNotFoundException("Role Not Found")));

        return user;
    }
}