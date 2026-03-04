package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.*;
import com.ESGIS.IRT2.Repository.AbonneRepository;
import com.ESGIS.IRT2.Repository.CassetteRepository;
import com.ESGIS.IRT2.Repository.LocationRepository;
import com.ESGIS.IRT2.Repository.RetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaissiereServices {

    @Autowired
    CassetteRepository cassetteRepository;
    @Autowired
    com.ESGIS.IRT2.Repository.CaissiereRepository caissiereRepository;
    @Autowired
    AbonneRepository abonneRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    RetourRepository retourRepository;

    public void deleteCaissiereById(Long id){
        caissiereRepository.deleteById(id);
    }
    public Caissiere createCaissiere(Caissiere caissiere){
        return caissiereRepository.save(caissiere);
    }
    public Caissiere editCaissiere(Caissiere caissiere){
        return caissiereRepository.save(caissiere);
    }
    public Optional<Caissiere> searchCaissiereById(Long id){
        return caissiereRepository.findById(id);
    }
    public Caissiere searchCaissiereByUseName(String userName){
        return caissiereRepository.findByUserName(userName);
    }
}
