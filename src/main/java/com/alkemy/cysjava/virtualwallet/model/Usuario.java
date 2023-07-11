package com.alkemy.cysjava.virtualwallet.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id; /*id is primary key. Can't be null or updated once in the table.*/

    @Column(nullable = false)
    private String name; /*name is the name of the User.*/

    @Column(nullable = false)
    private String surname; /*surname is the surname of the User.*/

    @Column(nullable = false)
    private String email; /*email address of the user.*/

    @Column(nullable = false)
    private String password; /*password of the user.*/

}
