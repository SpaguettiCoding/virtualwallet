package com.alkemy.cysjava.virtualwallet.models;

import com.alkemy.cysjava.virtualwallet.enums.TransactionType;
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
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String description;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    private Timestamp transactionDate;

    @ManyToMany()
    @JoinTable(
            name = "transactions_creditCard",
            joinColumns = @JoinColumn(name = "transactions_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "creditCard_id", referencedColumnName = "id")
    )
    private List<CreditCard> creditCards;


    @ManyToMany()
    @JoinTable(
            name = "transactions_fixedTermDeposits",
            joinColumns = @JoinColumn(name = "transactions_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fixedTermDeposits_id", referencedColumnName = "id")
    )
    private List<FixedTermDeposits> fixedTermDeposits;

    @ManyToMany()
    @JoinTable(
            name = "transactions_cryptos",
            joinColumns = @JoinColumn(name = "transactions_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cryptos_id", referencedColumnName = "id")
    )
    private List<Crypto> cryptos;
}
