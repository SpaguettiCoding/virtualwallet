package com.alkemy.cysjava.virtualwallet.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
}
