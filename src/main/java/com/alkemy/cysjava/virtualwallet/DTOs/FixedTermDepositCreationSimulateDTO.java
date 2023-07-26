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
public class FixedTermDepositCreationSimulateDTO {

    @NotNull(message = "Investment amount can't be null.")
    private Double amount;

    @NotNull(message = "The deposit must have a closing date.")
    private Timestamp closingDate;
}
