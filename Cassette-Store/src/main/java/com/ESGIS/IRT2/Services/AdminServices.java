package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.*;
import com.ESGIS.IRT2.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    LocationRepository locationRepository;

    //Il peut toujour manipuler lui meme les Clents mais dans ce projet nous allons eviter cela

    //Enregistrement et Manipulation des Cassettes

    //Create Admin
    public void createAdnin(Admin admin){
        adminRepository.save(admin);
    }
    public void editAdmin(Admin admin){
        adminRepository.save(admin);
    }
}
