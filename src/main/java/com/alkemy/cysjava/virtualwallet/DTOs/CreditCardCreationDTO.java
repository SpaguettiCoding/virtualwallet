package com.alkemy.cysjava.virtualwallet.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardCreationDTO {

    @NotBlank(message = "AmountAvailable can't be null ")
    private double amountAvailable;
    @NotBlank(message = "AmountAvailable can't be null ")
    private double amount;
    @NotEmpty(message = "Must have accountId")
    private Long accountId;
}
