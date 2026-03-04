package com.ESGIS.IRT2.Controller;

import com.ESGIS.IRT2.Model.Abonne;
import com.ESGIS.IRT2.Model.Location;
import com.ESGIS.IRT2.Services.AbonneServices;
import com.ESGIS.IRT2.Services.AdminServices;
import com.ESGIS.IRT2.Services.LocationServices;
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
    LocationServices locationServices;

    @GetMapping("")
    public String acceuil(){
        return "Acceuil";
    }
    @GetMapping("users")
    public Iterable<Abonne> afficherTousLesAbonne(){
        return abonneServices.dispalyAllAbonne();
    }
    @GetMapping("user/{username}")
    public Optional<Abonne> afficherUnAbonneByUserName(@PathVariable String username){
        return abonneServices.SearchUnClientByUserName(username);
    }
    @DeleteMapping("user/{username}")
    public void deleteAbonneWithUserName(@PathVariable String username){
        abonneServices.deleteByUserName(username);
    }
    @DeleteMapping("user/{id}")
    public void deleteAbonneById(@PathVariable Long id){

    }
    @GetMapping("/Locations")
    public Iterable<Location> listeDesAbonnes(){
        return locationServices.afficherLalisteDesLocations();
    }
}
