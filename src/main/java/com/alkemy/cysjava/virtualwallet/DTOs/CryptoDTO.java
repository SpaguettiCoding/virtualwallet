package com.alkemy.cysjava.virtualwallet.DTOs;

import com.alkemy.cysjava.virtualwallet.models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDTO {
    private Long id;
    private String name;
    private Double amount;
    private Long account;
}
