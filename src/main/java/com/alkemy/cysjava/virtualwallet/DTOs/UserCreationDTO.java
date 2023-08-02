package com.alkemy.cysjava.virtualwallet.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class UserCreationDTO {

    @NotNull(message = "firstname can't be null")  //Estos mensajes se interceptan en GlobalHandlerException
    @NotBlank(message = "firstname can't be empty")
    @Schema(description = "Primer Nombre", example = "Juan")
    private String firstname;

    @NotNull(message = "lastname can't be null")
    @NotBlank(message = "lastname can't be empty")
    @Schema(description = "Apellido", example = "Rodriguez")
    private String lastname;

    @Email(message = "Invalid format for email")
    @NotNull(message = "email can't be null")
    @NotBlank(message = "email can't be empty")
    @Schema(description = "Dirección de E-mail", example = "Juanrod@mail.com")
    private String email;

    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be empty")
    @Schema(description = "Contraseña", example = "password123")
    private String password;

    @NotNull(message = "role can't be null")
    @Schema(description = "Nivel de Acceso", example = "user")
    private String role;
}
