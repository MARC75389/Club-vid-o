package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Table
@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Admin;

    private String userName;

    private String password;
    @NotNull
    private String Role;
}
