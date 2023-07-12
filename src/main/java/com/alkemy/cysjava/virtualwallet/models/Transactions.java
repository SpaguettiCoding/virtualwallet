package com.alkemy.cysjava.virtualwallet.models;

import com.alkemy.cysjava.virtualwallet.models.Accounts;
import com.alkemy.cysjava.virtualwallet.enums.TransactionType;
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
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    private Timestamp transactionDate;
}