package com.alkemy.cysjava.virtualwallet.models;

import com.alkemy.cysjava.virtualwallet.models.User;
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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String currency;

    private double transactionLimit;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Timestamp creationDate;

    private Timestamp updateDate;

    private boolean softDelete;

}