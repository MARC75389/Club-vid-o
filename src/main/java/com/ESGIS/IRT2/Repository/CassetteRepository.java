package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Abonne;
import com.ESGIS.IRT2.Model.Cassette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CassetteRepository extends JpaRepository<Cassette,Long> {

}
