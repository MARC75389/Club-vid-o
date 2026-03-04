package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbonneRepository extends JpaRepository<Abonne,Long> {
    Optional<Abonne> findByUserName(String username);

    void deleteByUserName(String username);

}
