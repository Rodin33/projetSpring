package com.fichier.traitement.dtos;

import com.fichier.traitement.entity.AppUser;
import lombok.Data;

@Data
public class MessageRequest {
    private Long id;
    private Long userInit;
    private Long userDest;
    private String message;
}
