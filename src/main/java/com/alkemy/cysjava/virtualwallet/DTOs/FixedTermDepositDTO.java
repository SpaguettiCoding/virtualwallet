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
public class FixedTermDepositDTO {

    private Long id;
    private double amount;
    private double interest;
    private Timestamp creationDate;
    private Timestamp closingDate;
    private Long accountId;
    private Double accountBalance;

}
