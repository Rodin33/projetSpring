package com.fichier.traitement.service;

import com.fichier.traitement.auth.AuthenticationRequest;
import com.fichier.traitement.auth.AuthenticationResponse;
import com.fichier.traitement.config.JtwService;
import com.fichier.traitement.dtos.AppUserDto;
import com.fichier.traitement.dtos.AppUserResponse;
import com.fichier.traitement.dtos.ResponseMessages;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Role;
import com.fichier.traitement.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthentificationService {
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JtwService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse saveUser(AppUserDto appUserDto) {
        AppUser user=new AppUser();
        user.setNom(appUserDto.getNom());
        user.setEmail(appUserDto.getEmail());
        user.setStatus(1);
        user.setEtat(0);
        user.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        if(appUserDto.getRole().equals("client")){
            user.setRole(Role.CLIENT);
        } else if (appUserDto.getRole().equals("admin")) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.BACKOFFICE);
        }

        AppUser userSave=repository.save(user);
        var jwt=jwtService.generateToken(userSave);
        ;        return AuthenticationResponse.builder()
                .token(jwt).build();

    }

    @Override
    public AuthenticationResponse authetificate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=repository.findByEmail(request.getEmail())
                .orElseThrow();
        AppUserResponse userResponse =new AppUserResponse();
        userResponse.setNom(user.getNom());
        userResponse.setRole(user.getRole());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setStatus(user.getStatus());
        userResponse.setEtat(user.getEtat());
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .appUser(userResponse)
                .build();
    }
    @Override
    public List<AppUser> listUser() {
        return repository.findAll();
    }
    public ResponseMessages modifierStatus(Long id){
        AppUser appUser=repository.findById(id).get();
        if(appUser.getStatus()==1){
            appUser.setStatus(0);
            repository.save(appUser);
            return ResponseMessages.builder().message("modification effectuée").build();
        }else {
            appUser.setStatus(1);
            repository.save(appUser);
            return ResponseMessages.builder().message("modification effectuée").build();
        }
    }

    @Override
    public void supprimeUser(Long id)  {
        AppUser appUser=repository.findById(id).get();
        repository.deleteById(id);
    }

    @Override
    public AppUser userByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public ResponseMessages modifierEtat(Long id) {
        AppUser appUser=repository.findById(id).get();
        if(appUser.getEtat()==1){
            appUser.setEtat(0);
            repository.save(appUser);
            return ResponseMessages.builder().message("modification effectuée").build();
        }else {
            appUser.setEtat(1);
            repository.save(appUser);
            return ResponseMessages.builder().message("modification effectuée").build();
        }
    }

    @Override
    public ResponseMessages modifierEtat1(Long id) {
        AppUser appUser=repository.findById(id).get();
        appUser.setEtat(1);
        repository.save(appUser);
        return ResponseMessages.builder().message("modification effectuée").build();

    }

    @Override
    public AppUser userById(Long id) {
        return repository.findById(id).get();
    }
}


