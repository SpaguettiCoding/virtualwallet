package com.alkemy.cysjava.virtualwallet.models;

import com.alkemy.cysjava.virtualwallet.enums.CurrencyAccount;
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
@Table(name = "account")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private CurrencyAccount currency;

    private double transactionLimit;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private Timestamp creationDate;

    private Timestamp updateDate;

    private boolean softDelete;

    @OneToMany(mappedBy = "account")
    private List<Transactions> transactions;

    @OneToMany(mappedBy = "account")
    private List<CreditCard> creditCards;

    @OneToMany(mappedBy = "account")
    private List<FixedTermDeposits> fixedTermDeposits;


    @OneToMany(mappedBy = "account")
    private List<Crypto> cryptos;
}
