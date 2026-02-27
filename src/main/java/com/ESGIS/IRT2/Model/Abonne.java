package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.ESGIS.IRT2.Model.CarteAbonne;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor //
public class Abonne extends CarteAbonne{
    private String role;

    private String password;

    private Date dateEnregistrementDansLaBaseDeDonne;

    private String typeAbonnement;

    private Boolean Apte;

    private CategorieAbonnement categorie;

    private Long NbreDeLocation;

    public Abonne(String nomAbonne, String password, List<GrantedAuthority> grantedAuthorities) {
    }
}
