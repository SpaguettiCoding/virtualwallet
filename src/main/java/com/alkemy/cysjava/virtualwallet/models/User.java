package com.alkemy.cysjava.virtualwallet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @NotNull
    @NotBlank
    private String firstName;

    //@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @NotNull
    @NotBlank
    private String lastName;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @ManyToOne
    private Role role;

    private Timestamp creationDate;

    private Timestamp updateDate;

    private boolean softDelete;

//    @OneToMany(mappedBy = "user")
//    private List<Account> account;

}