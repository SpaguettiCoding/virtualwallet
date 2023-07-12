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
@Table(name="crypto")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    private Timestamp creationDate;

    private Timestamp closingDate;

    private boolean softDelete;
}