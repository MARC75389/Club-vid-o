package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Retour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface RetourRepository extends JpaRepository<Retour,Long> {
    Iterable<Retour> findByDateRetour(Date date);
    Iterable<Retour> findByDateRetourPrevue(Date date);
}
