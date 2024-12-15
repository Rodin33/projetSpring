package com.fichier.traitement.service;

import com.fichier.traitement.dtos.OperationEntreeRequest;
import com.fichier.traitement.dtos.ReceptionRequest;
import com.fichier.traitement.dtos.TraitementRequest;
import com.fichier.traitement.entity.Reception;
import com.fichier.traitement.entity.Traitement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TraitementService {
    Long saveTraitement(TraitementRequest request);
    String saveFile(MultipartFile file, Long id) throws IOException;
    List<Traitement> listeTraitement();
    void supprimeTraitement(Long id) throws IOException;
    void validerTraitement(Long id) throws  IOException;
    Long update(TraitementRequest request, Long id);
}
