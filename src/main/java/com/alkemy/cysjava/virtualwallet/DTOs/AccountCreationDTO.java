package com.alkemy.cysjava.virtualwallet.DTOs;

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
    private String currency;

    @NotNull(message = "currency can't be null")
    private Long user;
}