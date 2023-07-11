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
@Table(name = "fixedTermDeposits")
public class FixedTermDeposits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    private double interest;

    @ManyToMany(mappedBy = "fixedTermDeposits")
    private List<Transactions> transactions;

    private Timestamp creationDate;

    private Timestamp closingDate;
}
