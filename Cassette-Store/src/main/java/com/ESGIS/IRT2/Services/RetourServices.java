package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.Retour;
import com.ESGIS.IRT2.Repository.RetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetourServices {
    @Autowired
    RetourRepository retourRepository;
    public void enregistrerUnRetour(Retour retour) {
        retourRepository.save(retour);
    }
}
