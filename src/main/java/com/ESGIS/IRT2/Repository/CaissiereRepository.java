package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Abonne;
import com.ESGIS.IRT2.Model.Caissiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaissiereRepository extends JpaRepository<Caissiere,Long> {
    Caissiere findByUserName(String username);
}
