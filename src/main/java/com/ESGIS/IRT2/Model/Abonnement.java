package com.ESGIS.IRT2.Model;

import java.time.LocalTime;

import javax.annotation.processing.Generated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Abonnement {
    private LocalTime dureeAbonnement;

    public Abonnement(){
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_Abonnement;
        private
    }
}
