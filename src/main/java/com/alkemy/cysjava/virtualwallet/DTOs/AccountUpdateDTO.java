package com.alkemy.cysjava.virtualwallet.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Nuevo limite de transaccion. Los valores estandar son 350000 para pesos, 1000 para dolares.", example = "350000")
    private Double transactionLimit;
}
