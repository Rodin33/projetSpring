package com.fichier.traitement.dtos;

import lombok.Data;

@Data
public class AddUserByGroup {
    private Long idUser;
    private Long idGroup;
}
