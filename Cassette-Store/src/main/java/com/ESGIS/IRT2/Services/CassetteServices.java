package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.Cassette;
import com.ESGIS.IRT2.Repository.CassetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CassetteServices {
    @Autowired
    CassetteRepository cassetteRepository;
    //Enregistrement et Manipulation des Cassettes
    public void deleteCassette(Cassette cassette){
        cassetteRepository.delete(cassette);
    }
    //Supprimer une Cassette
    public void editCassette(Cassette cassette){
        cassetteRepository.save(cassette);
    }
    //Ajouter une Cassette
    public void addCassette(Cassette cassette){
        cassetteRepository.save(cassette);
    }
    public Iterable<Cassette> afficherLaListeDesCassettes(){
        return cassetteRepository.findAll();
    }
    public Iterable<Cassette> afficherLaListeDesCassettesParCategorie(String categorie){
        return cassetteRepository.findByCategorie(categorie);
    }
    public int afficherLaQuantiteDisponibleDuneCassette(String CassetteName){
        return cassetteRepository.findByQuantite(CassetteName);
    }
    public String afficherLaDsponibliteDuneCassette(String CassetteName){
        boolean disponiblite=cassetteRepository.findByDisponibilite(CassetteName);
        if(disponiblite){
            return "Cassette disponible";
        }
        else {
            return "Indisponible";
        }
    }
}
