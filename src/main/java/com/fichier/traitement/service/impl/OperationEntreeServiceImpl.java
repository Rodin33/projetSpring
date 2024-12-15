package com.fichier.traitement.service.impl;

import com.fichier.traitement.auth.AuthenticationResponse;
import com.fichier.traitement.dtos.OperationEntreeRequest;
import com.fichier.traitement.dtos.SuppResponse;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;
import com.fichier.traitement.repository.AppUserRepository;
import com.fichier.traitement.repository.OperationEntreeRepository;
import com.fichier.traitement.service.OperationEntreeService;
import jdk.dynalink.Operation;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
@AllArgsConstructor
public class OperationEntreeServiceImpl implements OperationEntreeService {
    private OperationEntreeRepository entRepose;
    private AppUserRepository appUserRepository;
    public static final String DIRECTORY_AUDIO="/home/Download/uploads/audio";
    public static final String DIRECTORY_DOC="/home/Download/uploads/docx";
    @Override
    public  String saveFile(MultipartFile file, Long id) throws IOException, UnsupportedAudioFileException {
        String erreur="Verifier votre fichier";
        String fileName=StringUtils.cleanPath(file.getOriginalFilename());
        OperationEntree op=entRepose.findById(id).get();
        String code=op.getCodeTache();
        String extension="";
        if (fileName.contains(".")){
            int i=fileName.lastIndexOf('.');
            extension=i>0 ?fileName.substring(i+1):"";
            if (extension.equals("docx")){
                Path fileStorage=get(DIRECTORY_DOC,"doc_tache_"+code+"."+"docx").toAbsolutePath().normalize();
                Files.copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
                op.setNameDoc(".docx");

                //////
                Path fileStorage1=get(DIRECTORY_DOC,"doc_tache_"+code+".docx").toAbsolutePath().normalize();
                String chemin=fileStorage1.toString();
                File files = new File(chemin);
                FileInputStream fis = new FileInputStream(files.getAbsolutePath());
                XWPFDocument document = new XWPFDocument(fis);
                XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                String[] words = extractor.getText().split("\\s+");
                int wordCount = words.length;
                op.setMot(wordCount);
                entRepose.save(op);
                //

            }else {
                return erreur;
            }

        }
        return fileName;
    }
   @Override
    public  String saveFileAudio(MultipartFile file, Long id) throws IOException, UnsupportedAudioFileException {
        String erreur="Verifier votre fichier";
        String fileName=StringUtils.cleanPath(file.getOriginalFilename());
        OperationEntree opi=entRepose.findById(id).get();
        String code=opi.getCodeTache();
        String extens="";
        if (fileName.contains(".")){
            int i=fileName.lastIndexOf('.');
            extens=i>0 ?fileName.substring(i+1):"";

            if (!(extens.equals("docx"))) {
                Path fileStorage=get(DIRECTORY_AUDIO,"audio_tache_"+id+"."+extens).toAbsolutePath().normalize();
                Files.copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
                System.out.println(extens);

            }
            opi.setNameAudio("."+extens);
            entRepose.save(opi);
        }
        return "hfhfh";
    }
    public Long saveTask(OperationEntreeRequest request) {
        OperationEntree operation = new OperationEntree();
        operation.setStatus("En instance");
        operation.setPriority(request.getPriority());
        operation.setDateOperation(request.getDateOperation());
        operation.setDescription(request.getDescription());
        operation.setNomDocument(request.getNomDocument());
        operation.setNomAudio(request.getNomAudio());
        operation.setNameDoc(request.getNameDoc());
        operation.setNameDoc(request.getNameAudio());
        var code = entRepose.findMaxId();
        int aneee=LocalDate.now().getYear();
        if (code == null) {
            int id = 1;
            operation.setCodeTache(aneee+"-"+"00001");
        } else {
            var code1 = code + 1;
            String formattedId = String.format("%05d", code1);
            operation.setCodeTache(aneee+"-"+formattedId);
        }
        AppUser user = appUserRepository.findById(request.getIdUser()).get();
        operation.setAppUser(user);
        OperationEntree saveOp = entRepose.save(operation);
        return saveOp.getId();
    }
    @Override
    public List<OperationEntree> listeOPration() {

        return entRepose.findAll();
    }
    @Override
    public List<OperationEntree> findByAppUser(Long idAppUser) {
        AppUser appUser=appUserRepository.findById(idAppUser).get();
        return entRepose.findByAppUser(appUser);
    }
    @Override
    public SuppResponse supprimerOperation(Long id) throws IOException {
        OperationEntree operationEntree=entRepose.findById(id).get();
        String status=operationEntree.getStatus();
        OperationEntree operationEntre=entRepose.findById(id).get();
        String ext2=operationEntre.getNameAudio();
        String fileName1="doc_tache_"+id+".docx";
        String fileName2="audio_tache_"+id+ext2;
        Path pathFile=get(DIRECTORY_DOC).toAbsolutePath().normalize().resolve(fileName1);
        Path pathFile2=get(DIRECTORY_AUDIO).toAbsolutePath().normalize().resolve(fileName2);

        if (status.equals("En instance")){
        entRepose.deleteById(id);
        boolean re=Files.deleteIfExists(pathFile);
        boolean re1=Files.deleteIfExists(pathFile2);
            return SuppResponse.builder()
                    .message("supression efectuée")
                    .build();
        }else {
            return SuppResponse.builder()
                    .message("Cette  tache est deja consignée")
                    .build();
        }
    }
    @Override
    public Long update(OperationEntreeRequest request,Long id) {
        OperationEntree operation=entRepose.findById(id).get();
        operation.setPriority(request.getPriority());
        operation.setDescription(request.getDescription());
        OperationEntree saveOp = entRepose.save(operation);
        return saveOp.getId();
    }
    @Override
    public OperationEntree operation(Long id) {
        OperationEntree v=entRepose.findById(id).get();
        return v;
    }
}
