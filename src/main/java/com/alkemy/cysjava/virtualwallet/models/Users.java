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
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne
    private Roles roles;

    private Timestamp creationDate;

    private Timestamp updateDate;

    private boolean softDelete;


    @OneToMany(mappedBy = "user")
    private List<Accounts> accounts;

}
