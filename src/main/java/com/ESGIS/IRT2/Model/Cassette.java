package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Cassette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Cassette;

    private String titre;

    private Categorie categorie;

}
