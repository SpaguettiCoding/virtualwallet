package com.alkemy.cysjava.virtualwallet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creditCards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double amountAvailable;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    private Timestamp creationDate;

    private Timestamp closingDate;

    private boolean softDelete;

    @ManyToMany(mappedBy = "creditCards")
    private List<Transactions> transactions;
}
