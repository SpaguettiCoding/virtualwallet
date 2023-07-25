package com.alkemy.cysjava.virtualwallet.DTOs;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixedTermDepositCreationDTO {

    @NotNull(message = "Investment amount can't be null.")
    private double amount;

    @NotNull(message = "The deposit must be from an account.")
    private Long accountId;

    @NotNull(message = "The deposit must have a closing date.")
    private Timestamp closingDate;

}
