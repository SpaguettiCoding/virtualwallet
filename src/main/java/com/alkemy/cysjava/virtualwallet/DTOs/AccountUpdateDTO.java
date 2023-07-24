package com.alkemy.cysjava.virtualwallet.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateDTO {

    @NotNull(message = "Transaction limit can't be null")
    private Double transactionLimit;
}
