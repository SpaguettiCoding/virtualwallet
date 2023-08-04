package com.alkemy.cysjava.virtualwallet.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSendMoneyDTO {

    @NotNull(message = "amount can't be null or less than 0")
    @Schema(description = "Valor de la transaccion", example = "1000")
    private Double amount;

    @NotNull(message = "account can't be null")
    @Schema(description = "ID de la cuenta que envia el dinero", example = "1")
    private Long account;

    @NotNull(message = "targetAccount can't be null")
    @Schema(description = "ID de la cuenta que recibe el dinero", example = "2")
    private Long targetAccount;
}
