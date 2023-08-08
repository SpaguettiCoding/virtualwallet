package com.alkemy.cysjava.virtualwallet.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class UserUpdateDTO {

    @NotBlank(message = "firstname can't be empty")
    @Schema(description = "Primer Nombre", example = "Juan")
    private String firstname;

    @NotBlank(message = "lastname can't be empty")
    @Schema(description = "Apellido", example = "Rodriguez")
    private String lastname;

    @NotBlank(message = "password can't be empty")
    @Schema(description = "Contraseña", example = "password123")
    private String password;
}
