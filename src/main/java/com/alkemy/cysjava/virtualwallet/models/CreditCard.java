package com.alkemy.cysjava.virtualwallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @NotNull
    private double amountAvailable;
    @NotNull
    private double amount;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private Timestamp creationDate;
    private Timestamp closingDate;
    private boolean softDelete;

    public void setClosingDateOneMonthAfterCreation() {
        if (creationDate != null) {
            LocalDateTime currentDate = LocalDateTime.now();

            LocalDate creationDateLocal = creationDate.toLocalDateTime().toLocalDate();
            LocalDate currentDateLocal = currentDate.toLocalDate();

            LocalDate closingDateLocal = creationDateLocal.plusMonths(1);

            if (currentDateLocal.isAfter(closingDateLocal)) {
                closingDateLocal = currentDateLocal.plusMonths(1);
            }
            closingDate = Timestamp.valueOf(closingDateLocal.atStartOfDay());
        }
    }
}
