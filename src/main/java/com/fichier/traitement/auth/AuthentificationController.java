package com.fichier.traitement.auth;

import com.fichier.traitement.dtos.AppUserDto;
import com.fichier.traitement.dtos.ResponseMessages;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Traitement;
import com.fichier.traitement.service.AuthenticationServiceImpl;
import com.fichier.traitement.service.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor()
@CrossOrigin("*")
public class AuthentificationController {
    private final AuthentificationService service;
    private AuthentificationService servi;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AppUserDto appUserDto)
    {
        return ResponseEntity.ok(service.saveUser(appUserDto));
    }
    @GetMapping("/modifieretat/{id}")
    public ResponseEntity<ResponseMessages> modification2(@PathVariable("id")Long id){
        return  ResponseEntity.ok(service.modifierEtat(id));
    }
    @GetMapping("/modifieretat1/{id}")
    public ResponseEntity<ResponseMessages> modification3(@PathVariable("id")Long id){
        return  ResponseEntity.ok(service.modifierEtat1(id));
    }
    @PostMapping("/connexion")
    public ResponseEntity<AuthenticationResponse> authetificate(@RequestBody AuthenticationRequest formAuthe){
        return ResponseEntity.ok(service.authetificate(formAuthe));
    }

    @GetMapping("/user_list")
    public List<AppUser> getList(){
        return service.listUser();
    }
    @GetMapping("/modifierstatus/{id}")
    public ResponseEntity<ResponseMessages> modification(@PathVariable("id")Long id){
        return  ResponseEntity.ok(service.modifierStatus(id));
    }
    @GetMapping("/utilisateurById/{id}")
    public AppUser userId(@PathVariable("id")Long id){
        return service.userById(id);
    }
    @GetMapping("/utilisateur_email/{email}")
    public AppUser userEmail(@PathVariable("email")String email){
        return service.userByEmail(email);
    }


    @DeleteMapping("/delete_user/{id}")
    public  void  supprimeUser(@PathVariable ("id") Long id){
        service.supprimeUser(id);
    }
}
