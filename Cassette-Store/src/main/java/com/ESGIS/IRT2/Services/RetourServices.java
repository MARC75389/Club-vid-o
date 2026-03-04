package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.Retour;
import com.ESGIS.IRT2.Repository.RetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class RetourServices {
    @Autowired
    RetourRepository retourRepository;
    public void enregistrerUnRetour(Retour retour) {
        retourRepository.save(retour);
    }
    public Iterable<Retour> afficherLaListeDesRetours(){
        return retourRepository.findAll();
    }
    public Optional<Retour> afficherUnRetourParId(Long id){
        return retourRepository.findById(id);
    }
    public Iterable<Retour> afficherUnRetourParDate(Date date){
        return retourRepository.findByDateRetour(date);
    }
    //On affichera que le dernier ok
    public Optional<Retour> afficherLeDernierRetourDunClient(String abonne){
        return Optional.empty();
    }
    public Iterable<Retour> afficherLaListeDesRetoursEnregistrerParUneCaissiere(){
        return  null;
    }
}
