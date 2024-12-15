package com.fichier.traitement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fichier.traitement.dtos.OperationEntreeRequest;
import com.fichier.traitement.dtos.ReceptionRequest;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;
import com.fichier.traitement.service.OperationEntreeService;
import com.fichier.traitement.service.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class ReceptionController {
   private ReceptionService service;

    @PostMapping("/createReception")
    public Reception saveReception(@RequestBody ReceptionRequest request){
        return service.saveReception(request);
    }
    @GetMapping("/list_recetion")
    public List<Reception> getList(){
        return service.listeReception();
    }

    @GetMapping("/getIdReception/{id}")
    public Reception getIdreception(@PathVariable("id") Long id){

        return service.receptionClient(id);
    }
    @GetMapping("/list_Receptions/{idAppUser}")
    public List<Reception> listReception(@PathVariable("idAppUser") Long idClient){
        return service.findByAppUser(idClient);
    }


}
