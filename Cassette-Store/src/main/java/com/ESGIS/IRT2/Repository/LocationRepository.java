package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Optional<Location> findByDateDeLaLocation(Date date);

}
