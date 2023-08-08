package com.alkemy.cysjava.virtualwallet.DTOs;

import com.alkemy.cysjava.virtualwallet.models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    private int id;
    private String name;
    private double amountAvailable;
    private double amount;
    private Account account;
    private LocalDateTime creationDate;
    private LocalDateTime closingDate;
}
