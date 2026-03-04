package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "abonnes")
@Data
public class Abonne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String role;

    // On utilise la COMPOSITION (Abonne possède une carte) et non l'héritage
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "carte_id")
    private CarteAbonne carte;
}