package com.alkemy.cysjava.virtualwallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fixed_term_deposits")
public class FixedTermDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double amount;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    private double interest;

    private Timestamp creationDate;

    private Timestamp closingDate;

    public void setClosingDate(Timestamp closingDate) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp minimumClosingDate = Timestamp.from(currentTimestamp.toInstant().plus(30, ChronoUnit.DAYS));

        if (closingDate == null  || closingDate.before(minimumClosingDate)) {
            this.closingDate = minimumClosingDate;
        } else {
            this.closingDate = closingDate;
        }
    }
}
