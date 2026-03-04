//package com.ESGIS.IRT2.Services;
//
//import com.ESGIS.IRT2.Model.Caissiere;
//import com.ESGIS.IRT2.Repository.CaissiereRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserServices {
//    @Autowired
//    CaissiereRepository caissiereRepository;
//
//    //Creer ou Enregistrer une Nouvelle Caissiere
//    public void addCaissiere(Caissiere caissiere){
//        caissiereRepository.save(caissiere);
//    }
//    //Supprimer un User
//    public void deleteCaissiere(Caissiere caissiere){
//        caissiereRepository.delete(caissiere);
//    }
//    //Editer le profil d'une Caissiere
//    //Editer le profil d'une Caissiere
//    public void editCaissiere(Caissiere caissiere){
//        caissiereRepository.save(caissiere);
//    }
//    //Ajout des Droit ou Retrait de Droits a une Secretaire
//    public void editSecretaireRole(String role,Caissiere caissiere){
//        Caissiere caissiere1=caissiere;
//        caissiere1.setRole(role);
//        caissiereRepository.save(caissiere1);
//    }
//}
