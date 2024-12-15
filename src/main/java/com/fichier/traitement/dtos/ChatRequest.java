package com.fichier.traitement.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ChatRequest {

    private Long id;

    private Long idUserDes;

    private  Long idUserExp;
    private Long idExp;
    private String message;
    private Integer statusDes;
    private Date dateMsg;
    private Integer statusExp;
}
