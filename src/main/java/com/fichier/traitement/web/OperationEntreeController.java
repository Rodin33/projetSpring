package com.fichier.traitement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fichier.traitement.auth.AuthenticationResponse;
import com.fichier.traitement.dtos.OperationEntreeRequest;
import com.fichier.traitement.dtos.SuppResponse;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.repository.OperationEntreeRepository;
import com.fichier.traitement.service.OperationEntreeService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class OperationEntreeController {
   private  OperationEntreeService service;
   private OperationEntreeRepository operationEntreeRepository;
    public static final String DIRECTORY="home/Download/uploads/";
    public static final String DIRECTORY_DOC="/home/Download/uploads/docx";
    public static final String DIRECTORY_DOC_TRAITE="/home/Download/uploads/docTraite";
    public static final String DIRECTORY_AUDIO="/home/Download/uploads/audio";

    @PostMapping("/createFile/{id}")
    public String saveFile(@RequestParam("files")MultipartFile file,@PathVariable("id")Long id) throws IOException, UnsupportedAudioFileException {
        return service.saveFile(file, id);
    }
    @PostMapping("/createFileAudio/{id}")
    public String saveFileAudio(@RequestParam("files")MultipartFile file,@PathVariable("id")Long id) throws IOException, UnsupportedAudioFileException {
        return service.saveFileAudio(file, id);
    }

    @PostMapping("/createTask")
    public Long saveTask(@RequestBody OperationEntreeRequest request){

        return service.saveTask(request);
    }
    @GetMapping("/list_task")
    public List<OperationEntree> getTask(){
        return service.listeOPration();
    }
   @PostMapping("/create_tasku")
    public String saveOp(@RequestParam("files")MultipartFile file, @RequestParam("tache")String data) throws IOException {
      System.out.println(data);
       OperationEntreeRequest request=new ObjectMapper().readValue(data,OperationEntreeRequest.class);
       return ""+request;
   }
    @GetMapping("teldoc/{filename}")
    public ResponseEntity<Resource> telechargerFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath=get(DIRECTORY_DOC).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)){
            throw  new FileNotFoundException(filename +" is not found on server");
        }
        Resource resource=new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("File_Name",filename);
        httpHeaders.add(CONTENT_DISPOSITION,"attachement;File_Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }

    @GetMapping("teldoc_traite/{filename}")
    public ResponseEntity<Resource> telechargeFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath=get(DIRECTORY_DOC_TRAITE).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)){
            throw  new FileNotFoundException(filename +" is not found on server");
        }
        Resource resource=new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("File_Name",filename);
        httpHeaders.add(CONTENT_DISPOSITION,"attachement;File_Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }

    @GetMapping("tel/{filename}")
    public ResponseEntity<Resource> telechargerFilesdd(@PathVariable("filename") String filename) throws IOException {
        Path filePath=get(DIRECTORY_AUDIO).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)){
            throw  new FileNotFoundException(filename +" is not found on server");
        }
        Resource resource=new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("File_Name",filename);
        httpHeaders.add(CONTENT_DISPOSITION,"attachement;File_Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
    @GetMapping("tel1/{filename}")
    public ResponseEntity<Resource> telechargerFilesd(@PathVariable("filename") String filename) throws IOException {
        Path filePath=get(DIRECTORY_AUDIO).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)){
            throw  new FileNotFoundException(filename +" is not found on server");
        }
        Resource resource=new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders=new HttpHeaders();
        //httpHeaders.add("File_Name",filename);
       /// httpHeaders.add(CONTENT_DISPOSITION,"attachement;File_Name=" + resource.getFilename());

        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDisposition(ContentDisposition.attachment().filename(filename).build());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);
    }

    ///Liste d'opertaion par client
    @GetMapping("/list_Taches/{idAppUser}")
    public List<OperationEntree> listOperation(@PathVariable("idAppUser") Long idClient){
        return service.findByAppUser(idClient);
    }
    @DeleteMapping("/deleteOperation/{id}")
    public ResponseEntity<SuppResponse> suppresion(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity.ok(service.supprimerOperation(id));
    }

    @PostMapping("/updateTask/{id}")
    public Long updateTask(@RequestBody OperationEntreeRequest request,@PathVariable("id")Long id){
        return service.update(request,id);
    }
    @GetMapping("/getIdOperation/{id}")
    public OperationEntree getId(@PathVariable("id") Long id){
        return service.operation(id);
    }

}
