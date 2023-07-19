package com.alkemy.cysjava.virtualwallet.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    @NotBlank(message = "firstname can't be empty")
    private String firstname;

    @NotBlank(message = "lastname can't be empty")
    private String lastname;

    @NotBlank(message = "password can't be empty")
    private String password;
}
