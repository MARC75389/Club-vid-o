package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbonneRepository extends JpaRepository<Abonne,Long> {
    Abonne findByNomAbonne(String username);
}
