package com.alkemy.cysjava.virtualwallet.models;

import com.alkemy.cysjava.virtualwallet.models.Accounts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="creditCard")
public class FixedTermDeposits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    @Column(nullable = false)
    private double interest;

    private Timestamp creationDate;

    private Timestamp closingDate;
}
