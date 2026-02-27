package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Table
@Entity
@Data
public class CarteAbonne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Abonne;

    private String nomAbonne;
}
