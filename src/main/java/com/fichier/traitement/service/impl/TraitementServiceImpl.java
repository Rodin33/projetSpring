package com.fichier.traitement.service.impl;

import com.fichier.traitement.dtos.TraitementRequest;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;
import com.fichier.traitement.entity.Traitement;
import com.fichier.traitement.repository.OperationEntreeRepository;
import com.fichier.traitement.repository.ReceptionRepository;
import com.fichier.traitement.repository.TraitementRepository;
import com.fichier.traitement.service.TraitementService;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
@AllArgsConstructor
public class TraitementServiceImpl implements TraitementService {
    private TraitementRepository traitementRepository;
    private ReceptionRepository receptionRepository;
    private OperationEntreeRepository operationEntreeRepository;
    public static final String DIRECTORY_DOC_TRAIT="/home/Download/uploads/docTraite";
    @Override
    public Long saveTraitement(TraitementRequest request) {
        Traitement traitement=new Traitement();
        traitement.setDateTraitement(new Date());
        Reception reception=receptionRepository.findById(request.getIdReception()).get();
        OperationEntree operationEntree=reception.getOperationEntree();
        traitement.setMot(request.getMot());
        traitement.setDuree(request.getDuree());
        traitement.setPage(request.getPage());
        traitement.setReception(reception);
        traitement.setMessage(request.getMessage());
        operationEntree.setStatus("En Attente");
        operationEntreeRepository.save(operationEntree);
        return traitementRepository.save(traitement).getId();
    }

    @Override
    public String saveFile(MultipartFile file, Long id) throws IOException {
        String erreur="Verifier votre fichier";
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        Traitement op=traitementRepository.findById(id).get();
        String extension="";
        if (fileName.contains(".")){
            int i=fileName.lastIndexOf('.');
            extension=i>0 ?fileName.substring(i+1):"";
            if (extension.equals("docx")){
                Path fileStorage=get(DIRECTORY_DOC_TRAIT,"doc_tache_taite_"+id+"."+extension).toAbsolutePath().normalize();
                Files.copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
            }else {
                return erreur;
            }
        }
        Traitement traitement=traitementRepository.findById(id).get();
        Reception rec=traitement.getReception();
        OperationEntree OpEntree=rec.getOperationEntree();
        int mot_initiale=OpEntree.getMot();
        Path fileStorage=get(DIRECTORY_DOC_TRAIT,"doc_tache_taite_"+id+"."+"docx").toAbsolutePath().normalize();
        String chemin=fileStorage.toString();
        File files = new File(chemin);
        FileInputStream fis = new FileInputStream(files.getAbsolutePath());
        XWPFDocument document = new XWPFDocument(fis);
        XWPFWordExtractor extractor = new XWPFWordExtractor(document);
        int pageCount = document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        String[] words = extractor.getText().split("\\s+");
        int wordCount = words.length;
        int mot_final=wordCount-mot_initiale;
        traitement.setMot((long) mot_final);
        traitement.setPage((long) pageCount);
        return fileName;
    }
    @Override
    public List<Traitement> listeTraitement() {
        return traitementRepository.findAll();
    }

    @Override
    public void supprimeTraitement(Long id) throws IOException {
        Traitement traitement=traitementRepository.findById(id).get();
        Reception reception=traitement.getReception();
        OperationEntree operationEntree=reception.getOperationEntree();
        String fileName1="doc_tache_taite_"+id+".docx";
        Path pathFile=get(DIRECTORY_DOC_TRAIT).toAbsolutePath().normalize().resolve(fileName1);
        boolean re=Files.deleteIfExists(pathFile);
        traitementRepository.deleteById(id);
        operationEntree.setStatus("En cours");
        operationEntreeRepository.save(operationEntree);

    }
    @Override
    public void validerTraitement(Long id) throws IOException {
        Traitement traitement=traitementRepository.findById(id).get();
        Reception reception=traitement.getReception();
        OperationEntree operationEntree=reception.getOperationEntree();
        operationEntree.setStatus("Traitée");
        operationEntreeRepository.save(operationEntree);
    }

    @Override
    public Long update(TraitementRequest request, Long id) {
        Traitement traitement=traitementRepository.findById(id).get();
        traitement.setPage(request.getPage());
        traitement.setMot(request.getMot());
        Traitement saveOp = traitementRepository.save(traitement);
        return saveOp.getId();
    }
}
