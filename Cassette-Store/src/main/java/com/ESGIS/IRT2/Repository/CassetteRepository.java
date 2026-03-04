package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Cassette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CassetteRepository extends JpaRepository<Cassette,Long> {
    Iterable<Cassette> findByCategorie(String catergorie);
    boolean findByDisponibilite(String cassetteName);
    int findByQuantite(String cassette);
}
