package com.alkemy.cysjava.virtualwallet.models;

import com.alkemy.cysjava.virtualwallet.enums.RolName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RolName name;

    private String description;

    private Timestamp creationDate;

    private Timestamp updateDate;
}
