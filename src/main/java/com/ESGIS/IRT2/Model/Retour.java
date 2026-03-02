package com.ESGIS.IRT2.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Retour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Retour;
}
