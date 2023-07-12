package com.alkemy.cysjava.virtualwallet.models;

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

//    @PrePersist
//    @PreUpdate
//    //PrePersist -> Será ejecutado antes de persistir los datos automaticamente por JPA
//    // PreUpdate -> Será ejecutado antes de que una entidad existente sea actualizada
//    private void calculateAmountAvailable() {
//
//        if (account != null) {
//            double money = account.getBalance();
//            if (money < 10000) {
//                amountAvailable = money * 10;
//            } else {
//                amountAvailable = money * 5;
//            }
//        }else {
//            amountAvailable = 0;
//            System.out.println("You don't qualify for a credit card");
//        }
//    }
}
