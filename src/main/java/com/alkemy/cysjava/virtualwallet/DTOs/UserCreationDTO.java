package com.alkemy.cysjava.virtualwallet.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {

    @NotNull(message = "firstName can't be null")  //Estos mensajes se interceptan en GlobalHandlerException
    @NotBlank(message = "firstName can't be empty")
    private String firstname;

    @NotNull(message = "lastName can't be null")
    @NotBlank(message = "lastName can't be empty")
    private String lastname;

    @Email(message = "Invalid format for email")
    @NotNull(message = "firstName can't be null")
    @NotBlank(message = "firstName can't be empty")
    private String email;

    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be empty")
    private String password;

    @NotNull(message = "role can't be null")
    private String role;
}
