package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cartes_abonnes")
@Data
public class CarteAbonne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomAbonne;
}