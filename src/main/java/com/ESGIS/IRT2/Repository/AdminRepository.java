package com.ESGIS.IRT2.Repository;

import com.ESGIS.IRT2.Model.Abonne;
import com.ESGIS.IRT2.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByUserName(String username);
}
