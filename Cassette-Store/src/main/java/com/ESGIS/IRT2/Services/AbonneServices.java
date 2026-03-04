package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.Abonne;
import com.ESGIS.IRT2.Repository.AbonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbonneServices{
    @Autowired
    AbonneRepository abonneRepository;
    //Afficher tous les Clients
    public Iterable<Abonne> dispalyAllAbonne(){
        return abonneRepository.findAll();
    }
    //Delete un Client
    public void DeleteUnClientById(Abonne abonne,Long id){
        Abonne abn=abonneRepository.findById(id);
        abonneRepository.delete(abn);
    }
    //Consulter un Client par username
    public Optional<Abonne> SearchUnClientByUserName(String userName){
       return abonneRepository.findByUserName(userName);
    }
    //Consulter un Client par Id
    public void SearchUnClientById(Long userId){
        abonneRepository.findById(userId);
    }
    //Enregistrer un Client
    public void EnregistrerUnClient(Abonne abonne){
        abonneRepository.save(abonne);
    }
    //Modifier un Client
    public void editUnClient(Abonne abonne){
        abonneRepository.save(abonne);
    }
    //Supprimer un utlisateur par son UserName
    public void deleteByUserName(String username){
        abonneRepository.deleteByUserName(username);
    }

}
