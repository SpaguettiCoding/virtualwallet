package com.alkemy.cysjava.virtualwallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @OneToOne
    private Roles roles;

    private Timestamp creationDate;

    private Timestamp updateDate;

    private boolean softDelete;

    @OneToMany(mappedBy = "user")
    private List<Accounts> accounts;

    public Users(int id, String firstName, String lastName, String email, String password, Roles roles, Timestamp creationDate, Timestamp updateDate, boolean softDelete) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.softDelete = softDelete;
    }
}
