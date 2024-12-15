package com.fichier.traitement.dtos;

import lombok.Data;

import java.util.Date;
@Data
public class ChatGroupRequest {
    private Long id;
    private Long idUser;
    private Long idGroup;
    private Date dateMsg;
    private String message;
}
