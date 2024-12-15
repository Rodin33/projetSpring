package com.fichier.traitement.service;

import com.fichier.traitement.auth.AuthenticationRequest;
import com.fichier.traitement.auth.AuthenticationResponse;
import com.fichier.traitement.dtos.AppUserDto;
import com.fichier.traitement.dtos.ResponseMessages;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Traitement;

import java.io.IOException;
import java.util.List;

public interface AuthentificationService {
    AuthenticationResponse saveUser(AppUserDto appUserDto);
    AuthenticationResponse authetificate(AuthenticationRequest request);
    List<AppUser> listUser();
    ResponseMessages modifierStatus(Long id);
    void supprimeUser(Long id);
    AppUser userByEmail(String email);
    ResponseMessages modifierEtat(Long id);
    ResponseMessages modifierEtat1(Long id);
    AppUser userById(Long id);
}
