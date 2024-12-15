package com.fichier.traitement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fichier.traitement.dtos.OperationEntreeRequest;
import com.fichier.traitement.entity.OperationEntree;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.Tika;
import java.io.File;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class testController {
    public static final String DIRECTORY_DOC = "/home/Download/uploads/doc";
    public static final String DIRECTORY_AUDIO = "/home/Download/uploads/audio";

    @PostMapping("/testField")
    public ResponseEntity<List<String>> uploadfiles(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path filestorage = get(DIRECTORY_AUDIO, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), filestorage, REPLACE_EXISTING);
            fileNames.add(filename);
        }
        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("telecharger/{filename}")
    public ResponseEntity<Resource> telechargerFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY_AUDIO).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " is not found on server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File_Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachement;File_Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }

    @PostMapping("/g")
    public String saveOp(@RequestParam("files") MultipartFile file) {
        var f = file.getOriginalFilename();
        return "ok" + f;
    }

    @PostMapping("/create_task")
    public String saveOp(@RequestParam("files") MultipartFile file, @RequestParam("tache") String data) throws IOException {
        System.out.println(data);
        // OperationEntreeRequest request=new ObjectMapper().readValue(data,OperationEntreeRequest.class);

        return "" + file;
    }

    @GetMapping("/testtgtgtgtg")
    public Long testt() throws IOException, TikaException, UnsupportedAudioFileException {

        File file = new File("C:/Users/JESUS/Download/uploads/audio/audio_tache_17.mp3");
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(file);
        Map properties = baseFileFormat.properties();
        Long duration = (Long) properties.get("duration");
        return duration;

    }
}