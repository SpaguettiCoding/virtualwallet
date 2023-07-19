package com.alkemy.cysjava.virtualwallet.models;

import com.alkemy.cysjava.virtualwallet.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String currency;

    @NotNull
    private double transactionLimit;

    @NotNull
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Timestamp creationDate;
    
    private Timestamp updateDate;

    private boolean softDelete;

}