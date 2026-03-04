package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Caissiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaissiereRepository extends JpaRepository<Caissiere,Long> {
    Caissiere findByUserName(String username);
}
