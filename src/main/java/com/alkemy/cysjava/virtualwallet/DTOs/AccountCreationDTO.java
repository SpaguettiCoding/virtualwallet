package com.alkemy.cysjava.virtualwallet.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class AccountCreationDTO {

    @NotNull(message = "currency can't be null")
    @NotBlank(message = "currency can't be empty")
    @Schema(description = "Moneda de la cuenta. ars = pesos, usd = dolares.", example = "ars")
    private String currency;

    @NotNull(message = "user can't be null")
    @Schema(description = "ID de usuario", example = "1")
    private Long user;
}