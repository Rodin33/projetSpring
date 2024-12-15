package com.fichier.traitement.service;

import com.fichier.traitement.dtos.ReceptionRequest;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;

import java.util.List;
public interface ReceptionService {
    Reception saveReception(ReceptionRequest request);
    List<Reception> listeReception();
    Reception receptionClient(Long id);
    List<Reception> findByAppUser(Long idAppUser);
}
