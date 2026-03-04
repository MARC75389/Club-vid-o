package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.Caissiere;
import com.ESGIS.IRT2.Repository.CaissiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretaireServices {
    @Autowired
    CaissiereRepository caissiereRepository;
    //Manipuler une Caissiere
    public void EnregistrerCaissiere(Caissiere user) {
        if (user.getRole() == "CAISSIERE") {
            caissiereRepository.save(user);
        };
    };
    public void EditUneCassiere(Caissiere user) {
        if (user.getRole() == "CAISSIERE") {
            caissiereRepository.save(user);
        };
    };
}
