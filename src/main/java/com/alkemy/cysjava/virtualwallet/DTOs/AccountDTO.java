package com.alkemy.cysjava.virtualwallet.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String currency;
    private Double transactionLimit;
    private Double balance;
    private Long user;
}
