package com.fichier.traitement.web;

import com.fichier.traitement.dtos.OperationEntreeRequest;
import com.fichier.traitement.dtos.TraitementRequest;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;
import com.fichier.traitement.entity.Traitement;
import com.fichier.traitement.service.ReceptionService;
import com.fichier.traitement.service.TraitementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class TraitementController {
    private TraitementService traitementService;
    @PostMapping("/envoieTraitement")
    public Long saveTraitement(@RequestBody TraitementRequest request){
        return traitementService.saveTraitement(request);
    }
    @PostMapping("/saveTraitement/{id}")
    public String saveFile(@RequestParam("files") MultipartFile file, @PathVariable("id")Long id)throws IOException {
        return traitementService.saveFile(file, id);
    }
    @GetMapping("/your_liste_traitement")
    public List<Traitement> getList(){
        return traitementService.listeTraitement();
    }
    @DeleteMapping("/delete_traitement/{id}")
    public void supresion(@PathVariable("id")Long id ) throws IOException {
        traitementService.supprimeTraitement(id);
    }
    @GetMapping ("/validerTraitement/{id}")
    public void updateTraitem(@PathVariable("id")Long id) throws IOException {
        traitementService.validerTraitement(id);
    }
    @PostMapping("/updateTraitement/{id}")
    public Long updateTraitement(@RequestBody TraitementRequest request, @PathVariable("id")Long id){
        return traitementService.update(request,id);
    }
}
