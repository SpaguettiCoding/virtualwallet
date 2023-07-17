package com.alkemy.cysjava.virtualwallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "[a-zA-Z]")
    @NotBlank
    private String firstName;

    @Pattern(regexp = "[a-zA-Z]")
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @OneToOne
    private Role role;

    private Timestamp creationDate;

    private Timestamp updateDate;

    private boolean softDelete;

//    @OneToMany(mappedBy = "user")
//    private List<Account> account;

    public User(int id, String firstName, String lastName, String email, String password, Role role, Timestamp creationDate, Timestamp updateDate, boolean softDelete) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.softDelete = softDelete;
    }
}
