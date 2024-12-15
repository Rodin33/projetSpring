package com.fichier.traitement.dtos;

import com.fichier.traitement.entity.Role;
import lombok.Data;

@Data
public class AppUserResponse {
    private Long id;
    private String nom;

    private String email;

    private Role role;
    private Integer status;
    private Integer etat;
}
