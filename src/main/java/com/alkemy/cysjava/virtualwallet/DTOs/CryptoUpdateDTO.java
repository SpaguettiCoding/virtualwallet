package com.alkemy.cysjava.virtualwallet.DTOs;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoUpdateDTO {
    @NotNull(message = "Amount can't be null")
    private Double amount;
}
