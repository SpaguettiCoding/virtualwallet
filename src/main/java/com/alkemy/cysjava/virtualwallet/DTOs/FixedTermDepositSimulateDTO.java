package com.alkemy.cysjava.virtualwallet.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixedTermDepositSimulateDTO {

    private Double amount;
    private Timestamp creationDate;
    private Timestamp closingDate;
    private Double interestEarned;
    private Double totalAmount;
}
