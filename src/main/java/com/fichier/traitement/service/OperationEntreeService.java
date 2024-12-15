package com.fichier.traitement.service;

import com.fichier.traitement.dtos.OperationEntreeRequest;
import com.fichier.traitement.dtos.SuppResponse;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public interface OperationEntreeService {
    String saveFile(MultipartFile file, Long id) throws IOException, UnsupportedAudioFileException;
    String saveFileAudio(MultipartFile file, Long id) throws IOException, UnsupportedAudioFileException;

    Long saveTask(OperationEntreeRequest request);
    List<OperationEntree> listeOPration();
    List<OperationEntree> findByAppUser(Long idAppUser);
    SuppResponse supprimerOperation(Long id) throws IOException;
    Long update(OperationEntreeRequest request,Long id);
    OperationEntree operation(Long id);


}
