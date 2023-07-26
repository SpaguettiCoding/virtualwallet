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
public class TransactionCreationDTO {

    @NotNull(message = "amount can't be null or less than 0")
    private Double amount;

    @NotNull(message = "account can't be null")
    private Long account;
}
