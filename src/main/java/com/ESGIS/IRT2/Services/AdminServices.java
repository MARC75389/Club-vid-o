package com.ESGIS.IRT2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ESGIS.IRT2.Repository.AbonneRepository;
import com.ESGIS.IRT2.Repository.AdminRepository;
import com.ESGIS.IRT2.Repository.CaissiereRepository;
import com.ESGIS.IRT2.Repository.CassetteRepository;
import com.ESGIS.IRT2.Model.Utilisateur;

@Service
public class AdminServices {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CassetteRepository cassetteRepository;
    @Autowired
    CaissiereRepository caissiereRepository;
    @Autowired
    AbonneRepository abonneRepository;

    private Utilisateur CreerUnUtilisateur(Utilisateur user) {
        if (user.getRole == "ADMIN") {
            adminRepository.save(user);
        } else if (user.getRole == "CAISSIERE") {
            caissiereRepository.save(user);
        } else {
            abonneRepository.save(user);
        }
    }

    private Utilisateur EditerUtilisateur(Utilisateur user) {

        if (user.getRole == "ADMIN") {
            adminRepository.save(user);
        } else if (user.getRole == "CAISSIERE") {
            caissiereRepository.save(user);
        } else {
            abonneRepository.save(user);
        }
    }
    private void deleteUser(){
        
    }
}
