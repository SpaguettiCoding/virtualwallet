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
public class TransactionDTO {

    private Double amount;
    private String transactionType;
    private String description;
    private Timestamp transactionDate;
    private Long account;
}
