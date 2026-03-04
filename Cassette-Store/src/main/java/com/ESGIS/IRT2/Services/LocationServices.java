package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.Location;
import com.ESGIS.IRT2.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class LocationServices {
    @Autowired
    LocationRepository locationRepository;

    //Rechercher une Location
    public Optional<Location> searchLocationByDate(Date date){
       return locationRepository.findByDateDeLaLocation(date);
    }
    //Faire une Location
    public Location FaireUneLocation(Location location){
        return locationRepository.save(location);
    }
    //Supprimer une Location
    public void deleteUneLocation(Location location){
        locationRepository.delete(location);
    }
    //Supprimer une Location
    public void editUneLocation(Location location){
        locationRepository.save(location);
    }
    //Afficher la liste des Locations
    public Iterable<Location> afficherLalisteDesLocations(){
       return locationRepository.findAll();
    }
    public Iterable<Location> afficherLaListeDesLocationDunAbonne(String userName){
        return null;
    }
    public Iterable<Location> afficherLaListeDesLocationsEffectuerParUneSecretaire(String Caissiere){
        return null;
    }
}
