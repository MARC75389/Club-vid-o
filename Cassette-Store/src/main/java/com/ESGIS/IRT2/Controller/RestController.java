package com.ESGIS.IRT2.Controller;

import com.ESGIS.IRT2.Model.Abonne;
import com.ESGIS.IRT2.Model.Location;
import com.ESGIS.IRT2.Model.Retour;
import com.ESGIS.IRT2.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    AbonneServices abonneServices;
    @Autowired
    AdminServices adminServices;
    @Autowired
    CaissiereServices caissiereServices;
    @Autowired
    LocationServices locationServices;
    @Autowired
    RetourServices retourServices;

    @GetMapping("")
    public String acceuil(){
        return "Acceuil";
    }
    //Search User,Return Locations and Others
    @GetMapping("users")
    public Iterable<Abonne> afficherTousLesAbonne(){
        return abonneServices.dispalyAllAbonne();
    }

    @GetMapping("user/{username}")
    public Optional<Abonne> afficherUnAbonneByUserName(@PathVariable String username){
        return abonneServices.SearchUnClientByUserName(username);
    }
    @GetMapping("")
    public Iterable<Location> afficherToutesLesLoactions(){
        return locationServices.afficherLalisteDesLocations();
    }

    @GetMapping("")
    public Iterable<Retour> afficherLaListeDesRetours(){
       return retourServices.afficherLaListeDesRetours();
    }
    //on ne stocke que le dernier
    @GetMapping("")
    public Optional<Retour> afficherLaListeDesRetoursEffectuerParUnAbonne(String userName){
        return retourServices.afficherLeDernierRetourDunClient(userName);
    }
    //

    //Delete users
    @DeleteMapping("user/{username}")
    public void deleteAbonneWithUserName(@PathVariable String username){
        abonneServices.deleteByUserName(username);
    }

    @DeleteMapping("caissiere/{id}")
    public void deleteCaissiereById(@PathVariable Long id){
            caissiereServices.deleteCaissiereById(id);
    }
    @DeleteMapping("user/{id}")
    public void deleteAbonneById(@PathVariable Long id){
    }

    @GetMapping("/Locations")
    public Iterable<Location> listeDesAbonnes(){
        return locationServices.afficherLalisteDesLocations();
    }


}
